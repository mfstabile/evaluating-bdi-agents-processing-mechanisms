package perception.linear;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;

@Goal
public class FibonacciCalculateGoal {

	@GoalParameter
	protected int input;

	@GoalResult
	protected int output;

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public FibonacciCalculateGoal(int input) {
		this.setInput(input);
	}

	protected int getInput() {
		return input;
	}

	protected void setInput(int input) {
		this.input = input;
	}
	
	

}
