package multiplier;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.IResultListener;
import jadex.commons.future.IntermediateDefaultResultListener;
import jadex.commons.gui.future.SwingResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class CallerMBDI {

	@Agent
	protected IInternalAccess agent;
	
	int tableLimit = 300;

	@AgentBody
	public void body() {
		final long startTime = System.currentTimeMillis();

		SServiceProvider.getServices(agent, IMultiplierService.class, RequiredServiceInfo.SCOPE_PLATFORM)
				.addResultListener(new IntermediateDefaultResultListener<IMultiplierService>() {
					public void intermediateResultAvailable(IMultiplierService ts) {
						ts.multiplyTable(tableLimit)
								.addResultListener(new SwingResultListener<Integer>(new IResultListener<Integer>() {
									public void resultAvailable(Integer mult) {
										final long duration = System.currentTimeMillis() - startTime;
										System.out.println("Result:" + mult);
										System.out.println("Duration Agent: " + duration);
									}

									public void exceptionOccurred(Exception exception) {
										exception.printStackTrace();
									}
								}));
					}
				});

	}
}
