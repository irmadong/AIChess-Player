
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * The state class
 * @author Jing Dong
 * @author Ran Yan
 *
 */
public class State {


	//private variables

	char[][] board;
	PriorityQueue <State> childrenstates;
	boolean Player;	//True for white, flase for black
	double score;
	double value ;	//for Hminimax
	double movedvalue;
	boolean exploration;
	ArrayList<Piece> white; //pieces for white
	ArrayList<Piece> black; //pieces for black
	String infor;

	/**
	 * The constructor for State class
	 * @param  The arraylist of Vehicle that stores all vehicles for that state
	 * @param g The actual cost from initial state to that  state 
	 * @param moveinfo The move information that indicates from the parent state which car moves to where 
	 * @param explorationone The boolean argument, is explorationone is true, it means it is using first exploration function
	 * 		  else it uses the basic heuristic function

	 */
	public State(boolean i, ArrayList<Piece> w, ArrayList<Piece> bl, double movedvalue,boolean explorationone)
	{

		this.exploration = explorationone;
		board = new char[8][8];
		this.movedvalue = movedvalue;
		//initialize board
		for (int i1 = 0; i1 < 8; i1++) {
			for (int j = 0; j < 8; j++) {
				board[i1][j] = '_';
			}
		}

		//pass in pieces
		white = w;

		black = bl;
		//indicate player for the current turn
		Player = i;
		if(i)
		{
			this.value = Double.NEGATIVE_INFINITY;
		}
		else
		{
			this.value = Double.POSITIVE_INFINITY;
		}

		//construct board
		this.constructBoard();


		evaluation();






		//initialize priority queue based on player
		if(explorationone) {
			if(Player) {
				childrenstates = new PriorityQueue<State>(1,new scoreMaxComparator());
			}else {
				childrenstates = new PriorityQueue<State>(1,new scoreMinComparator());
			}
		}
		else
		{
			if(Player) {
				childrenstates = new PriorityQueue<State>(1,new maxdistance());
			}else {
				childrenstates = new PriorityQueue<State>(1,new mindistance());
			}
		}




	} 


	/**
	 * The constructor for board based on pieces coordinates
	 */
	public void constructBoard() 
	{

		for(Piece wp: white) 
		{
			int x = wp.x;
			int y = 7 - wp.y;
			board[y][x] = wp.name;


		}
		for(Piece bp: black) 
		{
			int x = bp.x;
			int y = 7 - bp.y;
			board[y][x] = bp.name;
		}
	}

	/**
	 *  This function produces the children states which means all possible states when there is one action from the parent state 
	 */
	public void produceChildrenStates()
	{
		if(Player)
		{

			//find the possible new state for each white piece
			for(Piece wp: white)
			{

				//copy all white pieces other than this one to new list


				//find the tentative coordinates for wp
				ArrayList<ArrayList<Integer>> fp = wp.canMove(board); 





				if(!fp.isEmpty())
				{
					for(int i = 0; i < fp.size(); i++)
					{
						/* Create a new state by copying each pieces and move the selected piece  */

						// Copy all pieces not equal to the current piece to the new list


						ArrayList<Piece> updatedWhite = new ArrayList<Piece>();
						for (Piece w: white)
						{
							if(!w.equals(wp)) {
								Piece neww = w.copyPiece(w);
								updatedWhite.add(neww); 
							}
						}
						int updatedx = fp.get(i).get(0);
						int updatedy = fp.get(i).get(1);

						//						
						Piece nw = new Piece(updatedx,7-updatedy,wp.name);
						updatedWhite.add(nw);
						infor = "This piece is " + nw.name + "move to x: " + updatedx + "y :" + updatedy;

						double distance = updatedy-(7-wp.y)+updatedx-wp.x;

						ArrayList<Piece> updatedBlack = new ArrayList<Piece>(); // set up a new arralyist of updated black

						if (board[updatedy][updatedx]!='_') //black is eaten in  the current position 
						{
							Piece deletedblack = new Piece(updatedx,7-updatedy,board[updatedy][updatedx]);

							for (Piece b: black)
							{
								//add all pieces but not the eaten one
								if(!b.equal(deletedblack)) {


									Piece newb = b.copyPiece(b);
									updatedBlack.add(newb);
								}
							} // check test 


						}
						else
						{

							for (Piece b: black)
							{
								Piece newb = b.copyPiece(b);
								updatedBlack.add(newb);
							}
						}


						// generate child state
						State child = new State(false, updatedWhite, updatedBlack,distance,this.exploration);


						if(isSafe(child.board, child.white,child.black,true)) {


							//evaluate the child before put in priority queue for pruning
							child.evaluation();
							this.childrenstates.add(child);



						}

					}
				}

			}
		}
		else
		{
			for(Piece bp: black)
			{

				//find the tentative coordinates for bp
				ArrayList<ArrayList<Integer>> fp = bp.canMove(board);

				if(!fp.isEmpty())
				{
					for(int i = 0; i < fp.size(); i++)
					{
						//copy all black pieces other than this one to new list
						ArrayList<Piece> updatedBlack = new ArrayList<Piece>();

						/* Create a new state by copying each pieces and move the selected piece  */

						// Copy all pieces not equal to the current piece to the new list
						for (Piece b: black)
						{
							if(!b.equals(bp))
							{
								Piece newb = b.copyPiece(b);
								updatedBlack.add(newb); 
							}
						}


						int updatedx =fp.get(i).get(0);
						int updatedy = fp.get(i).get(1);


						// update the distance for exploration two
						double distance = updatedy-(7-bp.y)+updatedx-bp.x;

						Piece nb = new Piece(updatedx,7-updatedy,bp.name); //new black piece
						updatedBlack.add(nb);

						//copy list of white pieces
						ArrayList<Piece> updatedWhite = new ArrayList<Piece>();

						// check whether white is attacked by black
						if (board[updatedy][updatedx]!='_') 

						{
							Piece deletedwhite = new Piece(updatedx,7-updatedy,board[updatedy][updatedx]);


							for (Piece w: white)
							{
								if(!w.equal(deletedwhite)) {
									Piece neww = w.copyPiece(w);
									updatedWhite.add(neww); 							
								}
							}

						}
						else
						{
							for(Piece w:white)
							{
								Piece neww = w.copyPiece(w);
								updatedWhite.add(neww); 
							}

						}



						State child = new State(true, updatedWhite, updatedBlack, distance,this.exploration);
						// checkmate check
						if(isSafe(child.board, child.black,child.white,false)) {



							child.evaluation();
							this.childrenstates.add(child);
							//System.out.println("the black is safe");



						}
					}
					//						else
					//						{
					//							System.out.println("It's not safe");
					//							child.printBoard();
					//						}

				}
			}

		}
	}




	/**
	 * The function checks whether the current state is in the win condition
	 * @return boolean statement true if it is the win condition and false if it is not
	 */
	public boolean isGoal()
	{

		// check whether it is the goalstate --> there is no valid move
		return childrenstates.isEmpty();
	}


	/**
	 * Print the current board
	 */
	public void printBoard()
	{
		for(int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++)
			{
				System.out.print(board[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------");
	}

	/**
	 * Calculate the score for current state
	 */
	public void evaluation() 
	{
		//produceChildrenStates();

		double blacktotal = 0;
		double whitetotal = 0;
		for(Piece p : black)
		{
			blacktotal += p.value;
			//	this.score = whitetotal;
		}

		for(Piece p : white)
		{
			whitetotal += p.value;
			//this.score = blacktotal;
		}
		this.score = whitetotal - blacktotal;
	}


	/**
	 * 
	 * @return the children states 
	 */
	public PriorityQueue<State> getChildrenStates()
	{
		return childrenstates;
	}

	/**
	 * 
	 * @param tempboard the current board
	 * @param player1 the first list of player
	 * @param player2 the second list of player
	 * @param iswhite whether it is white
	 * @return
	 */
	public boolean isSafe(char[][] tempboard, ArrayList<Piece> player1, ArrayList<Piece> player2, boolean iswhite)
	{
		int x = 0 ;
		int y = 0 ;


		if(iswhite) {

			for (Piece w:player1)
			{
				if (w.name=='K')
				{

					x = w.x;
					y = w.y;
				}
			}
			//columb down
			int currenty = 7-y;
			currenty++;
			int currentx=x;


			while (currenty<=7&&(!Character.isUpperCase(tempboard[currenty][x])))
			{

				// column check 

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='r'))
				{
					return false;
				}
				currenty++;



			}

			//column up
			currenty = 7-y;
			currenty--;
			currentx=x;
			while (currenty>=0 &&!Character.isUpperCase(tempboard[currenty][currentx]) )
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='r'))
				{
					return false;
				}
				currenty--;


			}

			//row right
			//row check
			currenty = 7-y;
			currentx = x;
			currentx++;

			while (currentx<=7&&(!Character.isUpperCase(tempboard[currenty][currentx])))
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='r'))
				{
					return false;
				}
				currentx++;

				//				
				//				
			}


			//row left
			currenty = 7-y;
			currentx = x;
			currentx--;
			while (currentx>=0 &&!Character.isUpperCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='r'))
				{
					return false;
				}
				currentx--;
				//				
				//				
			}


			// diagonal check 
			currenty = 7-y;
			currentx = x;
			currentx--;
			currenty ++;
			while (currenty<=7 && currentx>=0 &&!Character.isUpperCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='b'))
				{
					return false;
				}
				currentx--;
				currenty ++;

				//				
				//				
			}

			currenty = 7-y;
			currentx = x;
			currentx--;
			currenty --;


			while (currenty>=0&&currentx>=0&&!Character.isUpperCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='b'))
				{
					return false;
				}
				currentx--;
				currenty --;

				//				
				//				
			}


			currenty = 7-y;
			currentx = x;
			currentx++;
			currenty ++;
			while (currenty<=7&&currentx<=7&&!Character.isUpperCase(tempboard[currenty][currentx]) )
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='b'))
				{
					return false;
				}
				currentx++;
				currenty ++;

				//				
				//				
			}

			currenty = 7-y;
			currentx = x;
			currentx++;
			currenty --;
			while (currenty>=0&&currentx<=7&&!Character.isUpperCase(tempboard[currenty][currentx]) )
			{

				if((tempboard[currenty][currentx]=='q' ||tempboard[currenty][currentx]=='b'))
				{
					return false;
				}
				currentx++;
				currenty --;

				//				
				//				
			}

			// L check 


			currenty = 7-y;
			currenty+=2;
			currentx = x;
			currentx-=1;

			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}


			currenty = 7-y;
			currenty+=2;
			currentx = x;
			currentx+=1;
			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}

			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx+=2;
			if( currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}


			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx-=2;

			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}


			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx+=2;

			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='n' )
				{
					return false;
				}
			}

			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx-=2;

			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}

			currenty = 7-y;
			currenty-=2;
			currentx = x;
			currentx+=1;
			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}

			currenty = 7-y;
			currenty-=2;
			currentx = x;
			currentx-=1;

			if( currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='n')
				{
					return false;
				}
			}

			// check pawn:

			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx+=1;

			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='p')
				{
					return false;
				}
			}

			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx-=1;
			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='p')
				{
					return false;
				}
			}


			//CHECK FOR KING

			currenty = 7-y;
			currentx = x;

			currenty++;
			currentx--;
			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty++;
			currentx++;
			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}



			currenty = 7-y;
			currentx = x;

			currenty --;
			currentx++;
			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty --;
			currentx--;
			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty ++;
			//currentx--;
			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			//currenty ++;
			currentx--;
			if(currentx>=0) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			//currenty ++;
			currentx++;
			if(currentx<=7) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty --;
			//currentx--;


			if(currenty>=0 ) {
				if(tempboard[currenty][currentx]=='k')
				{
					return false;
				}
			}






			//return true;



		}

		/*
		 * Checkmate condition for black player
		 */

		else
		{
			for (Piece w:player1)
			{
				if (w.name=='k')
				{

					x = w.x;
					y = w.y;
					break;
				}
			}





			int currenty = 7-y;
			currenty++;
			int currentx = x;
			while (currenty<=7 && !Character.isLowerCase(tempboard[currenty][currentx]))
			{

				// column check 

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='R'))
				{

					return false;
				}
				currenty++;
				//currenty = 7-y;


			}

			currenty = 7-y;
			currenty--;

			while (currenty>=0&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='R'))
				{

					return false;
				}
				currenty--;


			}
			//row check doesnt work????????????
			currenty = 7-y;
			currentx = x;
			currentx++;
			while (currentx<=7&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='R'))
				{

					return false;
				}
				currentx++;

				//			
				//			
			}

			currenty = 7-y;
			currentx = x;
			currentx--;
			while ( currentx>=0&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='R'))
				{

					return false;
				}
				currentx--;

				//			
				//			
			}

			// diagonal check 
			currenty = 7-y;
			currentx = x;
			currentx--;
			currenty ++;
			while (currenty<=7&&currentx>=0&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='B'))
				{

					return false;
				}
				currentx--;
				currenty ++;

				//			
				//			
			}

			currenty = 7-y;
			currentx = x;
			currentx--;
			currenty--;
			while (currenty>=0&&currentx>=0&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='B'))
				{

					return false;
				}
				currentx--;
				currenty --;

				//			
				//			
			}


			currenty = 7-y;
			currentx = x;
			currentx++;
			currenty ++;
			while (currenty<=7&&currentx<=7&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='B'))
				{
					//Testor

					return false;
				}
				currentx++;
				currenty ++;

				//			
				//			
			}

			currenty = 7-y;
			currentx = x;
			currentx++;
			currenty --;

			while (currenty>=0&&currentx<=7&&!Character.isLowerCase(tempboard[currenty][currentx]))
			{

				if((tempboard[currenty][currentx]=='Q' ||tempboard[currenty][currentx]=='B'))
				{
					//Testor

					return false;

				}
				currentx++;
				currenty --;

				//			
				//			
			}

			// L check 


			currenty = 7-y;
			currenty+=2;
			currentx = x;
			currentx-=1;

			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='N')
				{
					//Testor

					return false;
				}
			}



			currenty = 7-y;
			currenty+=2;

			currentx = x;
			currentx+=1;
			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='N')
				{
					//Testor

					return false;
				}
			}
			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx+=2;

			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='N' )
				{

					return false;
				}
			}


			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx-=2;

			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='N' )
				{

					return false;
				}
			}

			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx+=2;

			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='N')
				{

					return false;
				}
			}

			currenty = 7-y;
			currenty-=1;
			currentx = x;
			currentx-=2;

			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='N')
				{

					return false;
				}
			}

			currenty = 7-y;
			currenty-=2;
			currentx = x;
			currentx+=1;
			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='N')
				{

					return false;
				}
			}

			currenty = 7-y;
			currenty-=2;
			currentx = x;
			currentx-=1;

			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='N')
				{

					return false;
				}
			}

			// check pawn:

			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx+=1;

			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='P')
				{

					return false;
				}
			}

			currenty = 7-y;
			currenty+=1;
			currentx = x;
			currentx-=1;
			if(currenty<=7 && currentx>=0) {
				if(tempboard[currenty][currentx]=='P')
				{

					return false;
				}
			}




			//CHECK FOR KING
			currenty = 7-y;
			currentx = x;

			currenty++;
			currentx--;
			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty++;
			currentx++;
			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty --;
			currentx++;
			if(currenty>=0 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty --;
			currentx--;
			if(currenty>=0 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty ++;
			//currentx--;
			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			//currenty ++;
			currentx--;
			if(currenty<=7 &&currentx>=0) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			//currenty ++;
			currentx++;
			if(currenty<=7 &&currentx<=7) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}

			currenty = 7-y;
			currentx = x;

			currenty --;
			//currentx--;

			if(currenty>=0 ) {
				if(tempboard[currenty][currentx]=='K')
				{
					return false;
				}
			}
			//return true;
		}





		return true;
	}

	/**
	 * 
	 * @param v the state needs to be copied
	 * @return the deep copy of the state
	 */
	public State copyState(State v)
	{
		return new State(v.Player, v.white,v.black,v.movedvalue,v.exploration);
	}


}
