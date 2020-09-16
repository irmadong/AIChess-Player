
import java.util.Comparator;
public class scoreMinComparator implements Comparator<State>{
	
	/*
	 * The rule for max comparator
	 * @parameter s1 the first state 
	 * @parameter s2 the second state
	 */
	
	public int compare(State s1, State s2)
	{
		if(s1.score >s2.score)
		{
			return 1;
		}
		else if (s1.score < s2.score)
		{
			return -1;
		}
		return 0;
	}

	
	
}

	


