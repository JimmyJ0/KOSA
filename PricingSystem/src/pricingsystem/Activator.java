package pricingsystem;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import pricingsystem.behaviour.PricingSystemService;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		PricingSystemService pricingSystemService = new PricingSystemService(bundleContext);
		bundleContext.registerService(PricingSystemService.class, pricingSystemService, null);
		
		Activator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
