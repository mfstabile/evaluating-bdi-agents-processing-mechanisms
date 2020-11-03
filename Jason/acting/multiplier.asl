// Agent multiplier in project multiplication.mas2j

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */

!start.

+!start: true <-
	//Start = system.time;
	!multiplication(300);
	//Conf,Agents,Intentions,ResponseTime
	//.print(system.time); 
	//.print("tempo:", system.time-Start).
	.send(counter,tell,finished("Multiplier")).

+!multiplication(N) <-
	Start = system.time;
	for (.range(X,1,N)) {
		for (.range(Y,1,N)) {
			.print(X * Y);
		};
	};
	.print("tempo:", system.time-Start).
