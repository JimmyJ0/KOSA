package ticketautomaton.behaviour;

import java.util.HashMap;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.tracker.ServiceTracker;

public class TicketAutomatonService {
	private static final Logger LOG = Logger.getLogger(TicketAutomatonService.class.getName());
	
	private ServiceTracker<EventAdmin,EventAdmin> serviceTracker;
	
	public TicketAutomatonService(BundleContext bundleContext) {
		serviceTracker = new ServiceTracker<EventAdmin, EventAdmin>(bundleContext, EventAdmin.class.getName(), null);
		serviceTracker.open();
		
		LOG.info("SERVICE: TicketAutomatonService gestartet!");
		run();
		serviceTracker.close();
	}
	

	public void run() {
		HashMap<String, String> eventMap = new HashMap<String, String>();
		eventMap.put("Listener", "RouteSystem");
		
		System.out.println("TICKET AUTOMATON GESTARTET \n");
		
		EventAdmin ea = (EventAdmin) serviceTracker.getService();
		if(ea != null) {
			System.out.println("1. Ticket Automaton sended Event ");
			ea.sendEvent(new Event("TicketAutomatonStarted", eventMap));
		}
		serviceTracker.close();
	}
	
}
