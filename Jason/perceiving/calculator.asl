// Agent calculator in project perceptions.mas2j

/* Initial beliefs and rules */

fib(0,0).
fib(1,1).

/* Initial goals */

!start.

/* Plans */

+!start: true <-
	Start = system.time;
	?fibonacci(45,X);
	//Conf,Agents,Intentions,ResponseTime
	//.print(system.time); 
	.print("tempo:", system.time-Start);
	.print("result:", X);
	.send(counter,tell,finished("Multiplier")).

+?fibonacci(N,X): fib(N,X) <- true.
+?fibonacci(N,X) <- ?fibonacci(N-1,A);
						  ?fibonacci(N-2,B);
						  X = A+B;
						  +fib(N,X).

