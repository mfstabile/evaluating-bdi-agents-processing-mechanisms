package perception.linear;

import java.util.HashMap;

import jadex.commons.SimplePropertyChangeSupport;

public class Environment {

	protected static Environment instance;
	
	protected HashMap<Integer, Integer> counters;
	
	public SimplePropertyChangeSupport pcs;
	
	public int iteration = 0;
	
	public Environment()
	{
		this.counters = new HashMap<Integer, Integer>();
		this.pcs = new SimplePropertyChangeSupport(this);
	}
	
	public static synchronized Environment getInstance()
	{
		if(instance==null)
		{
			instance = new Environment();
		}
		return instance;
	}

	public synchronized HashMap<Integer, Integer> getCounters() {
		return counters;
	}

	public int getIteration() {
		return iteration;
	}

	public synchronized void setCounters(HashMap<Integer, Integer> counters, int index) {
		this.counters = new HashMap<>(counters);
		this.iteration += 1;
//		this.pcs.firePropertyChange("environment.counters", null, index);
	}
	
	
}
