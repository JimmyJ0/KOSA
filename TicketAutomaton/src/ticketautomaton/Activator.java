package ticketautomaton;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;

import ticketautomaton.behaviour.TicketAutomatonService;

public class Activator implements BundleActivator {

	private static final Logger LOG = Logger.getLogger(Activator.class.getName());

	private static BundleContext context;
	private ServiceTracker<EventAdmin, EventAdmin> serviceTracker;

	static BundleContext getContext() {
		return context;
	}

	// Stelle sicher, dass Service-Bus gestartet ist
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		serviceTracker = new ServiceTracker<EventAdmin, EventAdmin>(bundleContext, EventAdmin.class.getName(), null);
		serviceTracker.open();

		new Thread(() -> {
			while (serviceTracker.getService() == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// Wenn Service-Bus gestartet, registriere TicketAutomatonService
			LOG.info("Component Service Bus verfügbar");
			TicketAutomatonService ticketAutomatonService = new TicketAutomatonService(bundleContext);
			bundleContext.registerService(TicketAutomatonService.class, ticketAutomatonService, null);

		}).start();
//		serviceTracker.close();

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	
}
