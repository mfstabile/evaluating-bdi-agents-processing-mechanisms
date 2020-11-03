package fibonacci.linear;

import java.util.HashMap;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Goals;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Publish;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bridge.component.IExecutionFeature;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentFeature;

@Agent
@Service
@Goals(@Goal(clazz=FibonacciCalculateGoal.class,publish=@Publish(type=IFibonacciService.class)))
public class FibonacciBDI implements IFibonacciService{

	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	protected HashMap<Long, Long> results = new HashMap<Long, Long>();
	
	@Belief
	private long current = 0;

	@Plan(trigger=@Trigger(goals=FibonacciCalculateGoal.class))
	protected long calculate(long input)
	{
		if (input == 0){
			results.put(0L, 0L);
			return 0;
		}
		if (input == 1) {
			results.put(0L, 0L);
			return 1;
		}
		long a;
		if (results.containsKey(input-1)){
			a = results.get(input-1);
		}else{
			a = (Long)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input-1)).get();
		}
		long b;
		
		if (results.containsKey(input-2)){
			b = results.get(input-2);
		}else{
			b = (Long)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input-2)).get();
		}
		
		results.put(input, a+b);
		System.out.println(input + ": "+ (a+b));
		return a+b;
	}

	public IFuture<Long> calculateFibonacci(long input) {
		IFuture<Long> iFuture = (IFuture<Long>)bdiFeature.dispatchTopLevelGoal(new FibonacciCalculateGoal(input)).get();
//		System.out.println(iFuture.get());
		return iFuture;
	}

}
