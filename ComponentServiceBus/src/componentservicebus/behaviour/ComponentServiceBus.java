package componentservicebus.behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

public class ComponentServiceBus implements EventAdmin, IComponentServiceBus {
	private static final Logger LOG = Logger.getLogger(ComponentServiceBus.class.getName());

	private Map<String, EventHandler> eventHandlers = new HashMap<>();
	
	@Override
	public void registerEventHandler(String nameEventHandler, EventHandler eventHandler) {
		eventHandlers.put(nameEventHandler,eventHandler);
		
	}
	
	// Asynchron: Platziert Event einfach in der Event-Pipe und verabschiedet sich dann
	@Override
	public void postEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	// Synchron: Platziert Event in der Event-Pipe und wartet darauf, dass Event verarbeitet wurde
	@Override
	public void sendEvent(Event event) {
		String eventType = event.getTopic();
		EventHandler eventHandler = null;
		switch(eventType) {
		case "TicketAutomatonStarted": {
			eventHandler = eventHandlers.get("routeSystem");
			eventHandler.handleEvent(event);
			break;
		}
		case "RouteCreated" : {
			System.out.println("Route Created Event KOMMT AN");
			break;
		}
		}
		
//		eventHandlers.values().forEach(x -> System.out.println(x.toString()));
//        for (EventHandler eventHandler : eventHandlers.values()) {
//            eventHandler.handleEvent(event);
//        }
		
	}



}
