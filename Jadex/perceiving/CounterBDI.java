package perception.linear;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Goals;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
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
@RequiredServices({
		@RequiredService(name = "calcserv", type = IFibonacciService.class, binding = @Binding(scope = RequiredServiceInfo.SCOPE_PLATFORM)),
		@RequiredService(name = "updateserv", type = ICounterService.class, binding = @Binding(scope = RequiredServiceInfo.SCOPE_PLATFORM)) })
@Goals({ @Goal(clazz = FibonacciCalculateGoal.class), @Goal(clazz = UpdateCountersGoal.class) })
@Plans({ @Plan(trigger = @Trigger(goals = FibonacciCalculateGoal.class), body = @Body(service = @ServicePlan(name = "calcserv"))),
		@Plan(trigger = @Trigger(goals = UpdateCountersGoal.class), body = @Body(service = @ServicePlan(name = "updateserv"))),
		@Plan(body = @Body(CounterBDI.IncreaseCounterPlan.class)) })
public class CounterBDI {

	@Agent
	protected IInternalAccess agent;

	@AgentFeature
	protected IExecutionFeature execFeature;

	@AgentFeature
	protected IBDIAgentFeature bdiFeature;

	@Belief
	protected Environment environment = Environment.getInstance();

	@Belief
	protected Map counters = new HashMap<Integer, Integer>();

	int fibonacci = 45;

	int counterNumber = 3000;

	int maxUpdate = 10;

	int currentUpdate = 0;

	@AgentBody
	public void body() {
		Random rand = new Random();
		for (int i = 0; i < counterNumber; i++) {
			counters.put(i, rand.nextInt(50));
		}
		
		final long startTime = System.currentTimeMillis();

		bdiFeature.adoptPlan(new IncreaseCounterPlan());

		System.out.println("Starting agent");
		final int result = (Integer) bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(fibonacci)).get();

		final long duration = System.currentTimeMillis() - startTime;
		System.out.println("Input:" + fibonacci + " Result:" + result);
		System.out.println("Duration Agent: " + duration);
	}

	@Plan
	public class IncreaseCounterPlan {
		/**
		 * Plan body invoke once when plan is activated.
		 */
		@PlanBody
		public void increase() {
			if (currentUpdate < maxUpdate) {
				System.out.println("increasing counter: " + currentUpdate);
				HashMap<Integer, Integer> newCounters = new HashMap<Integer, Integer>();
				counters.keySet().forEach(value -> newCounters.put((Integer) value, (Integer) counters.get(value) + 1));
				environment.setCounters(newCounters, currentUpdate);
				currentUpdate += 1;
				bdiFeature.dispatchTopLevelGoal(new UpdateCountersGoal()).get();
				bdiFeature.adoptPlan(new IncreaseCounterPlan());
			}
		}
	}
}