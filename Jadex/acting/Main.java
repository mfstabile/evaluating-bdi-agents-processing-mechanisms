package multiplier;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Started");
		Thread.sleep(20000);
		PlatformConfiguration configuration = PlatformConfiguration.getDefaultNoGui();

		callMultiply(configuration);
		
		final long startTime = System.nanoTime();
		Starter.createPlatform(configuration).get();
		final long duration = System.nanoTime() - startTime;
		System.out.println("Duration: "+duration);
	}
	
	static void callMultiply(PlatformConfiguration configuration) {
		configuration.addComponent("multiplier.MultiplierBDI.class");
		configuration.addComponent("multiplier.CallerMBDI.class");
	}
}
