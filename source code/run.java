
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class the the execution class which only contains the main method 
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class run {
	public static void main(String args[])
	{	
		
		
		System.out.println("---------------------------------------------");	
		System.out.println("The following is Initial State A");
		System.out.println("");
		//Construct state A
		Piece q = new Piece(6, 7, 'q');
		Piece k = new Piece(7, 7, 'k');
		Piece P1 = new Piece(5, 5, 'P');
		Piece p = new Piece(7, 5, 'p');
		Piece Q = new Piece(6, 2, 'Q');
		Piece P2 = new Piece(7, 2, 'P'); //
		Piece P3 = new Piece(5, 1, 'P');
		Piece P4 = new Piece(6, 1, 'P');
		Piece R = new Piece(4, 0, 'R');
		Piece K = new Piece(6, 0, 'K');
	
		ArrayList<Piece> white1 = new ArrayList<Piece>();
		white1.add(P1);
		white1.add(P2);
		white1.add(P3);
		white1.add(P4);
		white1.add(Q);
		white1.add(R);
		white1.add(K);
		
		ArrayList<Piece> black1 = new ArrayList<Piece>();
		black1.add(q);
		black1.add(k);
		black1.add(p);
		
		
		
		State s1 = new State(true, white1, black1,0,true);
	
		

		State s2 = new State (true,white1,black1,0,false);
		
		
		AISolver C_1 = new AISolver();
////		
		AISolver C_2 = new AISolver();
		State temp = C_1.Hminimax(s1);
		
		State temp2 = C_2.Hminimax(s2);
//		
//	
		
		System.out.println("The size of best value is for C_1 " + temp.value);
		C_1.bestPath.get(0).printBoard();
		


		System.out.println("C1 examined size "+C_1.examined.size());
		System.out.println("The size of prunning for c1  " + C_1.p);

		System.out.println("C_2");
		System.out.println("------------------------------------------");
		System.out.println("The best value FOR C_2 is " + temp2.value);
		System.out.println("C_2 examined size " + C_2.examined.size());
		
		C_2.bestPath.get(0).printBoard();
		System.out.println("The size of prunning for c2  " + C_2.p);

		
		
		
		
		
		
		
//		for (State t : C_1.bestMove)
//		{
//			t.printBoard();
//		}
//		System.out.print(C_1.bestMove.size());
		
		// tester for minimax:
		
		
		
//		
		
		
		
	
		
	
	}

}
