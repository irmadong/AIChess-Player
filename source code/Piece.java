import java.util.ArrayList;
/**
 * The piece class
 * @author Jing Dong
 * @author Ran Yan
 *
 */

public class Piece {
	char name; //state 
	int x; // x coordinate
	int y; // y coordinate
	String player;
	
	int value; // 
	


	/**
	 * The constructor for piece 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param name
	 */
	public Piece (int x, int y, char name) {
		this.x = x;
		this.y= y;
		
		this.name = name;
		
		if(Character.isLowerCase(name))
		{
			player = "black";
		}
		else 
		{
			player = "white";
		}


		if(Character.toLowerCase(name)=='q')
		{
			value = 9;
		}
		else if (Character.toLowerCase(name)=='r')
		{
			value = 5;
		}
		else if (Character.toLowerCase(name) =='b' |Character.toLowerCase(name) == 'n')
		{
			value = 3;
		}
		else if (Character.toLowerCase(name) == 'p')
		{
			value = 1;
		}
		else
		{
			value = 10; //king is 10 
			
		}
	}

	
	/**
	 * The method that calcaulte all possible moves 
	 * @param board the current board
	 * @return the list of all possible positions
	 */
	public ArrayList<ArrayList<Integer>> canMove(char[][]board)
	{
		ArrayList<ArrayList<Integer>> position = new ArrayList<ArrayList<Integer>>();
		
		int newx = this.x;
		int newy = this.y;
		if(Character.toLowerCase(name)=='q'||Character.toLowerCase(name)=='r'||Character.toLowerCase(name)=='b')
		{
			
			if(Character.toLowerCase(name)=='q'||Character.toLowerCase(name)=='r') {
			newx++;
			newy = 7-this.y;
			// in the same row 
			while(newx < 8 && ((board[newy][newx]=='_')||(getPlayer(board[newy][newx])!=this.player &&board[newy][newx]!='_'&&board[newy][newx]!='_'))) //edge?? // black, white how to deal with??
			{
				
					ArrayList<Integer> singleposition = new ArrayList();
					
					if(getPlayer(board[newy][newx])!=this.player &&board[newy][newx]!='_'&&board[newy][newx]!='_'){
						singleposition.add(newx);
						singleposition.add(newy);
						position.add(singleposition);
						break;
						
					}
					else {
					singleposition.add(newx);
					singleposition.add(newy);
					position.add(singleposition);
					newx ++;
					}
			}
			newx = this.x; // reset 
			newx--;
			while (newx>= 0 && (board[newy][newx]=='_'||(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_'))) // move to left 
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy);
				position.add(singleposition);
				if(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_') {
					break;
				}
				else {
				newx--;
				}
				
			}
			newx = this.x;
			newy = 7-this.y;
			newy++;
			
			// column move
			while(newy<8 && ((board[newy][this.x]=='_')||(getPlayer(board[newy][this.x])!=this.player&&board[newy][this.x]!='_'))) // move down
			{
				ArrayList<Integer> singleposition = new ArrayList();
				
				singleposition.add(this.x);
				singleposition.add(newy);
				position.add(singleposition);
				
				if(getPlayer(board[newy][this.x])!=this.player&&board[newy][this.x]!='_'){

					break;
				}

				else {
				newy++;
				}
				
			}
			newy = 7-this.y;
			newy--;
			while(newy>=0 && ((board[this.x][newy]=='_')||(getPlayer(board[this.x][newy])!=this.player&&board[this.x][newy]!='_'))) // move up 
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(this.x);
				singleposition.add(newy);
				position.add(singleposition);
				if(getPlayer(board[this.x][newy])!=this.player&&board[this.x][newy]!='_') {
					break;
					
				}
				else {
				newy--;
				}
			}
			}
			
			if(Character.toLowerCase(name)=='q'||Character.toLowerCase(name)=='b') {
			// diagonal move
			newx = this.x;
			newy = 7-this.y;
			newx++;
			newy--;
			while(newy>=0 && newx<8 && (board[newy][newx]=='_'||(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy);
				position.add(singleposition);
				
				if(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_') {
					break;
				}
				else {
					
				newx++;
				newy--;
				}
			}
			
			newx = this.x;
			newy = 7-this.y;
			newx--;
			newy++;
			while(newy<8 && newx>=0 && (board[newy][newx]=='_'||(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]=='_'&&board[newy][newx]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy);
				position.add(singleposition);
				
				if(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]=='_'&&board[newy][newx]!='_') {
					break;
				}
				else {	
				newx--;
				newy++;
				}
			}
			newx = this.x;
			newy = 7-this.y;
			newx--;
			newy--;
			while(newy>=0 && newx>=0 &&(board[newy][newx]=='_'||(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy);
				position.add(singleposition);
				
				if(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_') {
					break;
				}
				else {
				newx--;
				newy--;
				}
			}
			newx = this.x;
			newy = 7-this.y;
			newx++;
			newy++;
			while(newy<8 && newx<8 && (board[newy][newx]=='_'||(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy);
				position.add(singleposition);
				if(getPlayer(board[newy][newx])!=this.player&&board[newy][newx]!='_') {
					break;
				}
				else {
				newx++;
				newy++;
				}
			}
			
			
			}	
			
			
		}
		else if (Character.toLowerCase(name)=='n') // for knight move in L
		{
			newx = this.x;
			newy = 7-this.y;
			if(newy+2<=7 && newx-1>=0 &&( board[newy+2][newx-1]=='_'||(getPlayer(board[newy+2][newx-1])!=this.player&& board[newy+2][newx-1]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy+2);
				position.add(singleposition);
			}
			if (newy+1<=7 && newx-2>=0 && (board[newy+1][newx-2]=='_'||(getPlayer(board[newy+1][newx-2])!=this.player&& board[newy+1][newx-2]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-2);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			
			if(newy+2<=7 && newx+1<=7 &&(board[newy+2][newx+1]=='_'||(getPlayer(board[newy+2][newx+1])!=this.player&&board[newy+2][newx+1]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy+2);
				position.add(singleposition);
			}
			
			if(newy+1<=7 && newx+2<=7 &&(board[newy+1][newx+2]=='_'||(getPlayer(board[newy+1][newx+2])!=this.player&&board[newy+1][newx+2]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+2);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			
			
			if(newy-1>=0 && newx-2>=0 && (board[newy-1][newx-2]=='_'||(getPlayer(board[newy-1][newx-2])!=this.player&&board[newy-1][newx-2]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-2);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			if(newy-2>=0 && newx-1>=0 && (board[newy-2][newx-1]=='_'||(getPlayer(board[newy-2][newx-1])!=this.player&& board[newy-2][newx-1]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy-2);
				position.add(singleposition);
			}
			if(newy-1>=0 && newx+2<=7 &&(board[newy-1][newx+2]=='_'||(getPlayer(board[newy-1][newx+2])!=this.player&&board[newy-1][newx+2]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+2);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			if(newy-2>=0 && newx+1<=7 &&(board[newy-2][newx+1]=='_'||(getPlayer(board[newy-2][newx+1])!=this.player&&board[newy-2][newx+1]!='_')))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy-2);
				position.add(singleposition);
			}
			
			// eight scenarior
			
		}
		
		
		else if(Character.toLowerCase(name)=='k') // king move 
		{
			newx = this.x;
			newy = 7-this.y;
			if(newx+1<=7 &&(board[newy][newx+1] == '_' ||(getPlayer(board[newy][newx+1])!=this.player&&board[newy][newx+1] == '_'))) // move to right 
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy);
				position.add(singleposition);
			}
			
			if(newy-1>=0 &&(board[newy-1][newx] == '_' ||(getPlayer(board[newy-1][newx])!=this.player&&board[newy-1][newx] != '_'))) // move up 
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			if(newx-1>=0 &&(board[newy][newx-1] == '_' ||(getPlayer(board[newy][newx-1])!=this.player&&board[newy][newx-1] != '_'))) // move to left
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy);
				position.add(singleposition);
			}
			if(newy+1<=7 &&( board[newy+1][newx] == '_' ||(getPlayer(board[newy+1][newx])!=this.player&&board[newy+1][newx] != '_'))) // move down 
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			if(newy+1<=7 && newx+1<=7 &&(board[newy+1][newx+1] == '_' ||getPlayer(board[newy+1][newx+1])!=this.player&&(board[newy+1][newx+1] != '_')))
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			if(newy-1>=0 && newx+1<=7 &&( board[newy-1][newx+1] == '_' ||(getPlayer(board[newy-1][newx+1])!=this.player&&board[newy-1][newx+1] != '_')))
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			if(newy+1<=7 && newx-1>=0&&(board[newy+1][newx-1] == '_' ||(getPlayer(board[newy+1][newx-1])!=this.player&&board[newy+1][newx-1] != '_')))
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			if(newy-1>=0 && newx-1>=0 && (board[newy-1][newx-1] == '_' ||(getPlayer(board[newy-1][newx-1])!=this.player&&board[newy-1][newx-1] != '_')))
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			
			
			
			// move one grid 
		}
		else if (name == 'p')// pawn for black 
		{
			newx = this.x;
			newy = 7-this.y;
			if(newy+1<=7 &&board[newy+1][newx]=='_')
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			if(newy+1<=7&&newx-1>=0&&(getPlayer(board[newy+1][newx-1])!=this.player&&board[newy+1][newx-1]!='_'))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			if(newy+1<=7 && newx+1<=7&&(getPlayer(board[newy+1][newx+1])!=this.player&&board[newy+1][newx+1]!='_'))
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy+1);
				position.add(singleposition);
			}
			
			// a pawn, if diagonal 
		}
		else  //pawn for white
		{
			
			newx = this.x;
			newy = 7-this.y;
			
			if(newy-1>=0 && board[newy-1][newx]=='_')
			{
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx);
				singleposition.add(newy-1);
				position.add(singleposition);
				// System.out.print("What's up ");
			}
			if(newy-1>=0&&newx-1>=0 && getPlayer(board[newy-1][newx-1])!=this.player &&board[newy-1][newx-1]!='_')
			{	
				
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx-1);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
			if(newy-1>=0 && newx+1<=7 && getPlayer(board[newy-1][newx+1])!=this.player&&board[newy-1][newx+1]!='_')
			{
				
				ArrayList<Integer> singleposition = new ArrayList();
				singleposition.add(newx+1);
				singleposition.add(newy-1);
				position.add(singleposition);
			}
		// king !!	
		
		}
		return position;
	}

	

	
	/**
	 * The getter for the type of the player
	 * @param piece the piece of the player
	 * @return
	 */
		
	public String getPlayer(char piece)
	{
		
		if (Character.isUpperCase(piece))
		{
			
			return "white";
		}
		return "black";
	}
	
	
	/**
	 * The equal method
	 * @param c the piece to be compared 
	 * @return boolean whether it is equal
	 */
	public boolean equal(Piece c)
	{
		return c.name == this.name && c.x==this.x && c.player ==this.player;
		
	}
	
	
	/**
	 * The deep copy of the piece 
	 * @param v the new piece 
	 * @return
	 */
	public Piece copyPiece(Piece v) {
		return new Piece(v.x,v.y,v.name);
		
	}


}

