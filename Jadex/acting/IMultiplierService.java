package multiplier;

import jadex.bridge.service.annotation.Timeout;
import jadex.commons.future.IFuture;

@Timeout(value = 500000)
public interface IMultiplierService {
	public IFuture<Integer> multiplyTable(int tableLimit);
}
