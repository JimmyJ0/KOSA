package routesystem.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventHandler;
import org.osgi.util.tracker.ServiceTracker;

import componentservicebus.behaviour.ComponentServiceBus;
import componentservicebus.behaviour.IComponentServiceBus;
import routesystem.configuration.InputValidator;
import routesystem.structure.Locations;
import routesystem.structure.Route;
import routesystem.structure.Routes;

public class RouteSystemService {
	private static final Logger LOG = Logger.getLogger(RouteSystemService.class.getName());

	// Framework
	private ServiceTracker<EventAdmin, EventAdmin> serviceTracker;
	private IComponentServiceBus componentServiceBus;
	private BundleContext context;

	public RouteSystemService(BundleContext context) {
		this.context = context;
		serviceTracker = new ServiceTracker<>(context, EventAdmin.class.getName(), null);
		serviceTracker.open();
		componentServiceBus = (IComponentServiceBus) serviceTracker.getService();

		registerRouteSystemInBusAsListener();
		serviceTracker.close();
	}

	private void registerRouteSystemInBusAsListener() {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				runRouteSystemService();
				
			}
		};
		String nameEventHandler = "routeSystem";
		componentServiceBus.registerEventHandler(nameEventHandler, eventHandler);

	}
	
	private void disableConsoleForProjectTest() {
		System.out.println("DISABLE");
		Bundle[] bun = context.getBundles();
		long felixGogoShellBundleID = -1;
		
		for(int i = 0; i < bun.length; i++) {
			System.out.println(bun[i]);
			if(bun[i].getSymbolicName().equals("org.apache.felix.gogo.shell")) {
				felixGogoShellBundleID = bun[i].getBundleId();
			}
		}
		if(felixGogoShellBundleID > 0) {
			Bundle felixGogoShell = context.getBundle(felixGogoShellBundleID);
			try {
				felixGogoShell.stop();
			} catch (BundleException e) {
				e.printStackTrace();
			}
		}
	}
	

	// Creates a Route
	private void runRouteSystemService() {
//		disableConsoleForProjectTest(); 
		InputValidator iv = new InputValidator();
		HashMap<Integer, Locations> locations = new HashMap<Integer, Locations>();
		for (int i = 0; i < Locations.values().length; i++) {
			locations.put(i + 1, Locations.values()[i]);
		}

		System.out.println("\nWELCOME TO CRAZY TRAVEL \n ");
		System.out.println("Please select your starting position");
		for (int i = 0; i < locations.size(); i++) {
			System.out.println(i + 1 + ". " + locations.get(i + 1));
		}
		System.out.println("\n");

//		Locations startLocation = locations.get(iv.getSingleNumber(locations.size()));
		Locations startLocation = locations.get(1); //! ZUM TESTEN 

		List<Routes> availableRoutes = new ArrayList<>();

		switch (startLocation) {
		case HAMBURG: {
			availableRoutes.add(Routes.HAMBURG_BERLIN);
			availableRoutes.add(Routes.HAMBURG_FRANKFURT);
			availableRoutes.add(Routes.HAMBURG_LUENBURG);
			availableRoutes.add(Routes.HAMBURG_MUENCHEN);
			availableRoutes.add(Routes.HAMBURG_STUTTGART);
			break;
		}
		case BERLIN: {
			availableRoutes.add(Routes.BERLIN_FRANKFURT);
			availableRoutes.add(Routes.BERLIN_MUENCHEN);
			availableRoutes.add(Routes.BERLIN_STUTTGART);
			break;
		}

		case FRANKFURT: {
			availableRoutes.add(Routes.FRANKFURT_MUENCHEN);
			availableRoutes.add(Routes.FRANKFURT_STUTTGART);
			break;
		}
		case LUENEBURG: {
			availableRoutes.add(Routes.LUENBURG_BERLIN);
			availableRoutes.add(Routes.LUENBURG_FRANKFURT);
			availableRoutes.add(Routes.LUENBURG_MUENCHEN);
			availableRoutes.add(Routes.LUENBURG_STUTTGART);
			break;
		}

		case MUENCHEN: {
			availableRoutes.add(Routes.MUENCHEN_HAMBURG);
			break;
		}
		case STUTTGART: {
			availableRoutes.add(Routes.STUTTGART_MUENCHEN);
			break;
		}
		}

		System.out.println("There are following destinations available");
		for (int i = 0; i < availableRoutes.size(); i++) {
			System.out.println(i + 1 + ". "
					+ availableRoutes.get(i).toString().substring(availableRoutes.get(i).toString().indexOf("_") + 1));
		}
		System.out.println("Please pick your destination");
//		Routes chosenRoute = availableRoutes.get(iv.getSingleNumber(availableRoutes.size()) - 1);
		Routes chosenRoute = availableRoutes.get(1); //! ZUM TESTEN

		fireEvent(new Route(chosenRoute));

	}
	
	private void fireEvent(Route route)  {

		ServiceReference<EventAdmin> serviceRef = context.getServiceReference(EventAdmin.class);
		ComponentServiceBus csb = (ComponentServiceBus) context.getService(serviceRef);

		Map<String, String> eventData = new HashMap<String, String>();
		eventData.put("route", route.getRoute().toString());
		eventData.put("distance", String.valueOf(route.getDistance()));
		csb.sendEvent(new Event("RouteCreated", eventData));
        
	}


}
