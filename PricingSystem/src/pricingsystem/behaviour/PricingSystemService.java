package pricingsystem.behaviour;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.osgi.util.tracker.ServiceTracker;

import componentservicebus.behaviour.ComponentServiceBus;
import componentservicebus.behaviour.IComponentServiceBus;

public class PricingSystemService {

	private BundleContext context;
	private IComponentServiceBus componentServiceBus;
	private ServiceTracker<EventAdmin, EventAdmin> serviceTracker;

	public PricingSystemService(BundleContext bundleContext) {
		this.context = bundleContext;
		serviceTracker = new ServiceTracker<>(bundleContext, EventAdmin.class.getName(), null);
		serviceTracker.open();
		componentServiceBus = (IComponentServiceBus) serviceTracker.getService();
		registerPricingSystemInBusAsListener();
		serviceTracker.close();
	}

	private void registerPricingSystemInBusAsListener() {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				runPricingSystem(event);

			}
		};
		String nameEventHandler = "pricingSystem";
		componentServiceBus.registerEventHandler(nameEventHandler, eventHandler);

	}

	private void runPricingSystem(Event event) {
		System.out.println("\n\n");
		System.out.println("TOPIC: " + event.getTopic());
		System.out.println("ROUTE: " + event.getProperty("route"));
		System.out.println("DISTANCE: " + event.getProperty("distance"));
		
		//TODO: Hier die PricingSystem-Logik zusammenbasteln

	}

	// Ignore
	private void fireEvent() {

		ServiceReference<EventAdmin> serviceRef = context.getServiceReference(EventAdmin.class);
		ComponentServiceBus csb = (ComponentServiceBus) context.getService(serviceRef);

		csb.sendEvent(null);

	}

}
