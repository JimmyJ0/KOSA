package componentservicebus.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;

public class ComponentServiceBus implements EventAdmin, IComponentServiceBus {
	private static final Logger LOG = Logger.getLogger(ComponentServiceBus.class.getName());

	private List<EventHandler> eventHandlers = new ArrayList<>();
	
	public ComponentServiceBus() {
		LOG.info("SERVICE: ComponentService gestartet!");

	}
	@Override
	public void registerEventHandler(EventHandler eventHandler) {
		eventHandlers.add(eventHandler);
		
	}
	
	// Asynchron: Platziert Event einfach in der Event-Pipe und verabschiedet sich dann
	@Override
	public void postEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	// Synchron: Platziert Event in der Event-Pipe und wartet darauf, dass Event verarbeitet wurde
	@Override
	public void sendEvent(Event event) {
		System.out.println("2. Bus empfängt Event");
//		System.out.println(event.containsProperty("Listener"));
//		System.out.println(event.getTopic());
		
		eventHandlers.forEach(x -> System.out.println(x.toString()));
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
		
	}



}
