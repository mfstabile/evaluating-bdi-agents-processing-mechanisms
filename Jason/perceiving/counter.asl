// Agent counter in project perceptions.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!inc(10).

/* Plans */

+!inc(0).
+!inc(N) <-
	inc;
	!inc(N-1).

+finished(_): .count(finished(_),1) <- .stopMAS.

