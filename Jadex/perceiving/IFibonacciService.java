package perception.linear;

import jadex.bridge.service.annotation.Timeout;
import jadex.commons.future.IFuture;

@Timeout(value = 999000000)
public interface IFibonacciService {
	public IFuture<Integer> calculateFibonacci(int input);
}
