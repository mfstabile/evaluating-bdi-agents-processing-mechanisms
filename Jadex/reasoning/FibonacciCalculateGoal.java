package fibonacci.linear;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;

@Goal
public class FibonacciCalculateGoal {

	@GoalParameter
	protected long input;

	@GoalResult
	protected long output;

	public long getOutput() {
		return output;
	}

	public void setOutput(long output) {
		this.output = output;
	}

	public FibonacciCalculateGoal(long input) {
		this.setInput(input);
	}

	protected long getInput() {
		return input;
	}

	protected void setInput(long input) {
		this.input = input;
	}
	
	

}
