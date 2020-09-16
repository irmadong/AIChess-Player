import java.util.ArrayList;
/**
 * The main method for initial state C
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class run2 {

	
	
	
	
	
	public static void main(String args[])
	{	
		
		
		System.out.println("---------------------------------------------");	
		System.out.println("The following is Initial State 	B");
		System.out.println("");
		//Construct state A
		Piece k = new Piece(2, 3, 'k');
		Piece p = new Piece(1, 4, 'p');
		
		
		
		
		Piece N1 = new Piece(0, 0, 'N');
		Piece N2 = new Piece(5, 0, 'N');
		Piece B1 = new Piece(1, 1, 'B');
		Piece P1 = new Piece(0, 2, 'P');
		Piece P2 = new Piece(5, 2, 'P');
		Piece K = new Piece(3, 5, 'K');
		Piece B2 = new Piece(2,7,'B');
		
//		Piece q = new Piece(7, 6, 'q');
//		Piece k = new Piece(7, 7, 'k');
//		Piece P1 = new Piece(5, 5, 'P');
//		Piece p = new Piece(5, 7, 'p');
//		Piece Q = new Piece(2, 6, 'Q');
//		Piece P2 = new Piece(2, 7, 'P');
//		Piece P3 = new Piece(1, 5, 'P');
//		Piece P4 = new Piece(1, 6, 'P');
//		Piece R = new Piece(0, 4, 'R');
//		Piece K = new Piece(0, 6, 'K');
		
		ArrayList<Piece> white1 = new ArrayList<Piece>();
		white1.add(N1);
		white1.add(N2);
		white1.add(B1);
		white1.add(P1);
		white1.add(P2);
		white1.add(K);
		white1.add(B2);
		
		ArrayList<Piece> black1 = new ArrayList<Piece>();
		
		black1.add(k);
		black1.add(p);
		
		
		
		State s1 = new State(true, white1, black1,0,true);
		

		State s2 = new State (true,white1,black1,0,false);

		//State temp2 = C_2.Hminimax(s2);
		
		//temp2.printBoard();
		
		
		
		
		
		
		//C_1.findBestMove(s1);
		
				
		AISolver C_1 = new AISolver();
////	
	AISolver C_2 = new AISolver();
	State temp = C_1.Hminimax(s1);
	
	State temp2 = C_2.Hminimax(s2);
//	
//
	
	System.out.println("The size of best value is for C_1 " + temp.value);
	C_1.bestPath.get(0).printBoard();
	


	System.out.println("C1 examined size"+C_1.examined.size());
	System.out.println("The size of prunning for c1  " + C_1.p);

	System.out.println("C_2");
	System.out.println("------------------------------------------");
	System.out.println("The best value FOR C_2 is " + temp2.value);
	System.out.println("C_2 examined size " + C_2.examined.size());
	
	C_2.bestPath.get(0).printBoard();
	System.out.println("The size of prunning for c2  " + C_2.p);
		
		
	}
}
