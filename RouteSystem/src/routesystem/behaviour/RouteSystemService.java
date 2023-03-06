package routesystem.behaviour;

import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.osgi.util.tracker.ServiceTracker;

import componentservicebus.behaviour.IComponentServiceBus;

public class RouteSystemService {
	private static final Logger LOG = Logger.getLogger(RouteSystemService.class.getName());

	private ServiceTracker<EventAdmin, EventAdmin> serviceTracker;
	private IComponentServiceBus componentServiceBus;

	public RouteSystemService(BundleContext context) {
		serviceTracker = new ServiceTracker<>(context, EventAdmin.class.getName(), null);
		serviceTracker.open();
		componentServiceBus = (IComponentServiceBus) serviceTracker.getService();

		// Sollte sicherstellen, dass ComponentBus gestartet ist
//		new Thread(() -> {
//			while (serviceTracker.getService() == null) {
//				System.out.println("WAIT FOR BUS");
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		registerRouteSystemInBusAsListener();
		serviceTracker.close();
		LOG.info("SERVICE: RouteSystemService verfügbar");
	}

	public void registerRouteSystemInBusAsListener() {

		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handleEvent(Event event) {
				System.out.println("3. RouteSystem nimmt Event entgegen: " + event.getTopic());
			}
		};
		componentServiceBus.registerEventHandler(eventHandler);
		System.out.println("REGISTER LISTENER");

	}

}
