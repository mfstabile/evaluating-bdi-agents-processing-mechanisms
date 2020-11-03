// Environment code for project perceptions.mas2j

import jason.asSyntax.*;
import jason.environment.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.*;

public class World extends Environment {
	private static int MAX = 3000;
	private int count = 0;

    private Logger logger = Logger.getLogger("environmentJason."+World.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
		
        
        
        //List<Literal> percepts = new LinkedList<Literal>(); 
        for (int i = 0; i < MAX; i++) {
        	addPercept("calculator",Literal.parseLiteral("counter("+ i +"," + count + ")"));
        }
        informAgsEnvironmentChanged("calculator");
    }
    
    private void updateCounter() {
    	count++;
		
    	clearPercepts("calculator");
    	for (int i = 0; i < MAX; i++) {
        	addPercept("calculator",Literal.parseLiteral("counter("+ i +"," + count + ")"));
        }
        informAgsEnvironmentChanged("calculator");
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        if (action.getFunctor().equals("inc")) {
        	//System.out.println("*INC* BEGIN!");
        	updateCounter();
    	} else {
        	logger.info("executing: "+action+", but not implemented!");
        }
        
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
    
}
