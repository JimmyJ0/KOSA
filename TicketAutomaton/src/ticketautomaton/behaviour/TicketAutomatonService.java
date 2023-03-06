package ticketautomaton.behaviour;

import java.util.HashMap;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;

public class TicketAutomatonService {
	private static final Logger LOG = Logger.getLogger(TicketAutomatonService.class.getName());

	private ServiceTracker<EventAdmin, EventAdmin> serviceTracker;

	public TicketAutomatonService(BundleContext bundleContext) {
		serviceTracker = new ServiceTracker<EventAdmin, EventAdmin>(bundleContext, EventAdmin.class.getName(), null);

		run();
	}

	public void run() {

//		disableConsoleForProjectTest();
//		 BundleContext context = FrameworkUtil.getBundle(TicketAutomatonService.class).getBundleContext();
//		 Bundle[] bundles = context.getBundles();
//		 System.out.println(bundles.length);
//		 for(Bundle bundle: bundles) {
//			 System.out.println(bundle.getState());
//		 }

		serviceTracker.open();
//		HashMap<String, String> eventMap = new HashMap<String, String>();
//		eventMap.put("Listener", "RouteSystem");

		EventAdmin ea = (EventAdmin) serviceTracker.getService();
		if (ea != null) {
			ea.sendEvent(new Event("TicketAutomatonStarted", new HashMap<String,String>()));
		}
		serviceTracker.close();
		
	}

	private void disableConsoleForProjectTest() {
		System.out.println("DISABLE");
		BundleContext context = FrameworkUtil.getBundle(TicketAutomatonService.class).getBundleContext();
		Bundle bundleToStop = context.getBundle(12); // org.apache.felix.gogo.shell_1.1.4 [12] 
		try {
			System.out.println("Deactivated Bundle: " + bundleToStop.getSymbolicName());
			bundleToStop.stop();
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

}
