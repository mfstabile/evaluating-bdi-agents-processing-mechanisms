package multiplier;

import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentFeature;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@Agent
@Service
@ProvidedServices(@ProvidedService(type=IMultiplierService.class))
@Plans(@Plan(body=@Body(MultiplicationPlan.class)))
public class MultiplierBDI implements IMultiplierService{

	int tableLimit = 0;
	
	@AgentFeature 
	protected IBDIAgentFeature bdiFeature;
	
//	@AgentBody
//	public void body()
//	{
//	  bdiFeature.adoptPlan(new MultiplicationPlan());
//	}

	public IFuture<Integer> multiplyTable(int tableLimit) {
		return bdiFeature.adoptPlan(new MultiplicationPlan(tableLimit));
	}
	

}
