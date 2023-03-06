package routesystem;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import routesystem.behaviour.RouteSystemService;

public class Activator implements BundleActivator {

	private static final Logger LOG = Logger.getLogger(Activator.class.getName());

	private static BundleContext context;
//	private ServiceRegistration<RouteSystemService> registration;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {

		RouteSystemService routeSystemService = new RouteSystemService(bundleContext);
//		routeSystemService.registerEventListener();
		bundleContext.registerService(RouteSystemService.class, routeSystemService, null);

		Activator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
