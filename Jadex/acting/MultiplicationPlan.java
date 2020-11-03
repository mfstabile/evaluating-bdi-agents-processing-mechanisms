package multiplier;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

@Plan
public class MultiplicationPlan {
	
	protected int tableLimit;
	
	/**
	 *  Create a new TranslatePlan. 
	 */
	public MultiplicationPlan(int tableLimit)
	{
		this.tableLimit = tableLimit;
	}

	@PlanBody
	public int multiplicationTable() {
		int k = 0;
		for (int i = 0; i < tableLimit; i++) {
			for (int j = 0; j < tableLimit; j++) {
				k = i * j;
				System.out.println(k);
			}
		}
		return k;
	}
}
