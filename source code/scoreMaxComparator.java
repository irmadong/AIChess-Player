/*
 *  This class is designed for the comparator of Priority Queue in the rule of ascending cost of the f(n) cost 
 *  which is actual cost + heuristic function cost for each states.
 *  It  means the state with smaller f(n) cost will be extracted first.
 */

import java.util.Comparator;


public class scoreMaxComparator  implements Comparator<State>{
	
	/*
	 * The rule for max comparator
	 * @parameter s1 the first state 
	 * @parameter s2 the second state
	 */
	
	public int compare(State s1, State s2)
	{
		if(s1.score<s2.score)
		{
			return 1;
		}
		else if (s1.score > s2.score)
		{
			return -1;
		}
		return 0;
	}

	
	
}
