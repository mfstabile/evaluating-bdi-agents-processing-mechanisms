package perception.linear;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Started");
		Thread.sleep(20000);
		PlatformConfiguration configuration = PlatformConfiguration.getDefaultNoGui();
		System.out.println("Initializing");
		callAgents(configuration);
		
		final long startTime = System.nanoTime();
		Starter.createPlatform(configuration).get();
		final long duration = System.nanoTime() - startTime;
		System.out.println("Duration: "+duration);
	}
	
	static void callAgents(PlatformConfiguration configuration) {
		configuration.addComponent("perception.linear.WorkerBDI.class");
		configuration.addComponent("perception.CounterBDI.class");
	}
}
