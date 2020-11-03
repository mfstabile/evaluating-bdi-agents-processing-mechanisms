package fibonacci.linear;

import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Goals;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.ServicePlan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bridge.IInternalAccess;
import jadex.bridge.component.IExecutionFeature;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.Binding;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

@Agent
@RequiredServices(@RequiredService(name="calcserv", type=IFibonacciService.class, 
binding=@Binding(scope=RequiredServiceInfo.SCOPE_PLATFORM)))
@Goals(@Goal(clazz=FibonacciCalculateGoal.class))
@Plans(@Plan(trigger=@Trigger(goals=FibonacciCalculateGoal.class), body=@Body(service=@ServicePlan(name="calcserv"))))
public class CallerFBDI {

	@Agent
	protected IInternalAccess agent;

	@AgentFeature
	protected IExecutionFeature execFeature;

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	int fibonacci = 90;

	@AgentBody
	public void body() {
		final long startTime = System.currentTimeMillis();
		
		final long result = (Long)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(fibonacci)).get();
		
		final long duration = System.currentTimeMillis() - startTime;
		System.out.println("Input:"+fibonacci+" Result:" + result);
		System.out.println("Duration Agent: " + duration);

	}
}
