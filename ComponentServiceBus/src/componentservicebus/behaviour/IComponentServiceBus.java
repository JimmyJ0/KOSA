package componentservicebus.behaviour;
import org.osgi.service.event.EventHandler;


public interface IComponentServiceBus {
	void registerEventHandler(EventHandler eventHandler);

}
