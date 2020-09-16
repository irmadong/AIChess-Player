import java.util.ArrayList;
/**
 * The AI Solver
 * @author Jing Dong
 * @author Ran Yan 
 *
 */

public class AISolver {
	ArrayList<State> examined = new ArrayList<State>();
	ArrayList<State> bestPath = new ArrayList<State>();
	int p ;
	
	
	/**
     * 
     * The general Hminimax function call
     * @param s the state
     * @return the best path state 
     */
    public State Hminimax(State s)
    
    {
    	examined.add(s);
    	State best = Maximizer(s,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,0);   	
    	
    	return best;
    	   	    		
    }
    
    /**
     * Recursion function for Maximizer
     * @param parent the current state
     * @param alpha 
     * @param beta
     * @param depth the current depth
     * @return the best choice for the current depth
     */
    
    public State Maximizer(State parent, double alpha, double beta,int depth) 
    {
    	
    	double currScore = Double.NEGATIVE_INFINITY;
    	
    	examined.add(parent);
    	parent.produceChildrenStates();
    	
    	
    	if(parent.isGoal())
    	{
    		
    		parent.value =  -1000*(5-depth);
    		
    		return parent;
    	}
    	
    	
    	if(depth ==4)
    	{
    		parent.value = parent.score+parent.childrenstates.size();
    		
    		return parent; 
    	}
    	
    	
    
    	
    	
    	
    
       State globalMax = null;
       State nextStep = null;
      
       
       
    	while(!parent.childrenstates.isEmpty())
    	{
    		
    		State max = parent.childrenstates.poll();
    		
    		
    		State m =  Minimizer(max, alpha, beta,depth+1);
    		
 
  		
    		if(m.value >= beta) {
    			p++;
    			parent.value = m.value;
    			
    			return max; 
    			
    		}
    	
    		if(m.value >= currScore) { 
    			
    			currScore = m.value;
    			
    			globalMax = m;
    			parent.value = m.value;
    			nextStep = max.copyState(max);
   		
    		}     		
    	
     		alpha = Math.max(currScore, alpha);
    		
    	
    	}  	
    	
    	
    	
 
    	if (depth == 0) {
    		
    		bestPath.add(nextStep);
    	}
    	
    	
    	return globalMax;
    }
    
    /**
     *  The recursion minimizer function
     * @param parent the current state
     * @param alpha
     * @param beta
     * @param depth the current depth
     * @return the best choice for the current node
     */
    public State Minimizer(State parent, double alpha, double beta, int depth) 
    {
    	
    	double currScore = Double.POSITIVE_INFINITY;
    	examined.add(parent);
    	parent.produceChildrenStates();
    	
        // cutoff test 	
    	if(parent.isGoal())
    	{
    		
    		parent.value =  1000*(5-depth); // Evaluation
    		
    		
    		
    		return parent;
    	}
    	
    	//cut off test 
    	if(depth == 4)
    	{
    		   	
    		parent.value = parent.score+parent.childrenstates.size(); // evaluation function
    		
    		return parent;
    	}
    	
    	
    
    	
    	State globalMin = null;
    	
    	while(!parent.childrenstates.isEmpty())
    	{
    		
    		
    		State min = parent.childrenstates.poll();
    		State m = Maximizer(min,alpha,beta, depth+1);
    		
    		// PRUNING 
    		if(m.value <= alpha) {
    			p++;
    			parent.value = m.value;
    			return min;
    		}   	  		 
    		     		
 		
    		if(m.value <= currScore) {
    			
    			currScore= m.value;
     			parent.value = m.value;
    			globalMin = m;
    			
    		}

    		
    		beta = Math.min(currScore, beta);
    		
    	}
    	
 
    	return globalMin;
    }
    
    
    


    
    
    
    

}
