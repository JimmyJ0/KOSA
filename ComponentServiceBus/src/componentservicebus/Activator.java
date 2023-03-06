package componentservicebus;

import java.util.logging.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventAdmin;

import componentservicebus.behaviour.ComponentServiceBus;

public class Activator implements BundleActivator {

	private static final Logger LOG = Logger.getLogger(Activator.class.getName());
	
	private static BundleContext context;
//	private ServiceRegistration<EventAdmin> registration;


	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		
		ComponentServiceBus componentServiceBus = new ComponentServiceBus();
		bundleContext.registerService(EventAdmin.class, componentServiceBus, null);
		Activator.context = bundleContext;
		
	

	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
