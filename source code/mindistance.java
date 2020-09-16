/**
 * The comparator for exploration function two
 * @author Jing Dong
 * @author Ran Yan
 */
import java.util.Comparator;

public class mindistance implements Comparator<State> {
	
	

		@Override
		public int compare(State s1, State s2) {
			
			if(s1.movedvalue>s2.movedvalue)
			{
				return 1;
			}
			else if (s1.movedvalue < s2.movedvalue)
			{
				return -1;
			}
			
			return 0;
			
		}
		

		
	}



