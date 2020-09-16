/**
 * The main method for initial state c
 * @author Jing Dong
 * @author Ran Yan
 */
import java.util.ArrayList;

public class run3 {
	public static void main (String args[]) {
		System.out.println("---------------------------------------------");	
		System.out.println("The following is Initial State C");
		System.out.println("");
		//Construct state A
		Piece k = new Piece(3, 4, 'k');
		Piece r = new Piece(4, 4, 'r');
		Piece p = new Piece(4,3,'p');
		Piece b = new Piece(5,3,'b');





		Piece N1 = new Piece(5, 0, 'N');
		Piece N2 = new Piece(3, 3, 'N');
		Piece R = new Piece(2, 5, 'R');
		Piece P1 = new Piece(4, 2, 'P');
		Piece P2 = new Piece(1, 4, 'P');
		Piece P3 = new Piece(4,5,'P');
		Piece K = new Piece(3,6, 'K');

		//	Piece q = new Piece(7, 6, 'q');
		//	Piece k = new Piece(7, 7, 'k');
		//	Piece P1 = new Piece(5, 5, 'P');
		//	Piece p = new Piece(5, 7, 'p');
		//	Piece Q = new Piece(2, 6, 'Q');
		//	Piece P2 = new Piece(2, 7, 'P');
		//	Piece P3 = new Piece(1, 5, 'P');
		//	Piece P4 = new Piece(1, 6, 'P');
		//	Piece R = new Piece(0, 4, 'R');
		//	Piece K = new Piece(0, 6, 'K');

		ArrayList<Piece> white1 = new ArrayList<Piece>();
		white1.add(N1);
		white1.add(N2);
		white1.add(R);
		white1.add(P1);
		white1.add(P2);
		white1.add(P3);
		white1.add(K);


		ArrayList<Piece> black1 = new ArrayList<Piece>();

		black1.add(k);
		black1.add(r);
		black1.add(p);
		black1.add(b);



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





		//C_1.findBestMove(s1);


		System.out.println(C_1.examined.size());
		//System.out.println(C_1.p);

		System.out.println(C_2.examined.size());
		//System.out.println(C_2.p);

	}

}
