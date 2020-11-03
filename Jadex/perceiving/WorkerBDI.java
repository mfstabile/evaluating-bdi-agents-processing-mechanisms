package perception.linear;

import perception.FibonacciCalculateGoal;
import perception.IFibonacciService;

import java.util.HashMap;
import java.util.Map;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Goals;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Publish;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.IPlan;
import jadex.bridge.component.IExecutionFeature;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentFeature;

@Agent
@Service
@Goals({
	@Goal(clazz=FibonacciCalculateGoal.class,publish=@Publish(type=IFibonacciService.class)),
	@Goal(clazz=UpdateCountersGoal.class,publish=@Publish(type=ICounterService.class))
})
public class WorkerBDI implements IFibonacciService, ICounterService{

	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	@Belief
	protected Environment environment = Environment.getInstance();
	
	@Belief
	protected Map counters = new HashMap<Integer, Integer>();
	
	protected HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
	
	@Belief
	private int current = 0;
	
	@Plan(trigger=@Trigger(goals=UpdateCountersGoal.class))
	protected IFuture<Void> updateCounters(IPlan rplan)
	{
		System.out.println("Updated beliefs:" + environment.counters.size());
		counters = new HashMap<Integer, Integer>(environment.getCounters());
		return IFuture.DONE;
	}

	
	@Plan(trigger=@Trigger(goals=FibonacciCalculateGoal.class))
	protected int calculate(int input)
	{
		if (input == 0){
			results.put(0, 0);
			return 0;
		}
		if (input == 1) {
			results.put(0, 0);
			return 1;
		}
		int a;
		if (results.containsKey(input-1)){
			a = results.get(input-1);
		}else{
			a = (Integer)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input-1)).get();
		}
		int b;
		
		if (results.containsKey(input-2)){
			b = results.get(input-2);
		}else{
			b = (Integer)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input-2)).get();
		}
		
		results.put(input, a+b);
//		System.out.println(a+b);
		return a+b;
	}

	public IFuture<Integer> calculateFibonacci(int input) {
		System.out.println("ponto1");
		IFuture<Integer> iFuture = (IFuture<Integer>)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input)).get();
		return iFuture;
	}


	@Override
	public IFuture<Void> updateCounter() {
		return IFuture.DONE;
	}

}
