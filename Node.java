import java.util.LinkedList;
import java.util.Queue;

public class Node {
	//movement
	private String move = ""; 
	//for IDA*
	public boolean isOut = false;
	
	private Node parent;
	//STATE
	private char state[][];
	//A board of the grandfather's Node
	public String board;
	//STATE COST
	private int cost;
	//FOR A*  , IDA* BFBnB
	private int depth=0;
	//total price  = the price until the curr son
	private int toatal_price;
	//for hashtable
	public String key;
	//heuristic value 
	private int heru_value;
	//cost + heuristic
	private int F_G;
	
	static int count = 0;
	
	public Node(Node son) 
	{
		this.move = son.move;
		this.isOut = son.isOut;
		this.parent = son.parent;
		this.state = this.copy_array(son.state);
		this.cost = son.cost;
		this.depth = son.depth;
		this.toatal_price = son.toatal_price;
		this.heru_value = son.heru_value; 
		this.F_G = son.F_G;
	}


	public Node(char[][] board) {
		this.state = board;
		this.key = this.toStringarr(board);
	}

	private char[][] copy_array(char[][] curr_arr) {
		char arr [] []  =  new char [curr_arr.length][curr_arr[0].length]; 
		for(int i = 0;i < curr_arr.length; i++) {
			for(int j = 0 ;j < curr_arr[0].length; j++) {
				arr [i][j] = curr_arr[i][j];
			}
		}
		return arr;
	}

	private void swap(char[][] new_array,int i,int j,String move) {
		char temp;
		switch(move) {
		case "left":
			//SAVE THE CURR 0
			temp = new_array[i][j-1];
			new_array[i][j-1] = new_array[i][j];
			new_array[i][j] = temp;
			break;
		case "right":
			temp = new_array[i][j+1];
			new_array[i][j+1] = new_array[i][j];
			new_array[i][j] = temp;
			break;
		case "up":
			temp = new_array[i-1][j];
			new_array[i-1][j] = new_array[i][j];
			new_array[i][j] = temp;
			break;
		case "down":
			temp = new_array[i+1][j];
			new_array[i+1][j] = new_array[i][j];
			new_array[i][j] = temp;
			break;
		default:
			System.err.println("cant move anywhere");	  
		}
	}

	public Queue<Node> findStates() {
		Queue<Node> new_ = new  LinkedList<Node>();
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if(state[i][j]==('_')) {
					moveUp(new_,i,j);
					moveDown(new_,i,j);
					moveLeft(new_,i,j);
					moveRight(new_,i,j);
				}
			}
		}
		return new_;
	}

	private void moveDown(Queue<Node> new_, int i, int j) {
		if(i > 0  && state[i-1][j]!=('_')) 
		{
			
			char new_array[][] = copy_array(state);
			swap(new_array,i,j,"up");
			if(!board.equals(this.toStringarr(new_array))) {
				count++;
				Node copy_Node = new Node(new_array);
				copy_Node.board = this.toString();
				if(new_array[i][j]==('R')) copy_Node.setCost(1);
				else if(new_array[i][j]==('Y')) copy_Node.setCost(1);
				else if(new_array[i][j]==('B')) copy_Node.setCost(2);
				else if(new_array[i][j]==('G')) copy_Node.setCost(10);
				int bx = i;
				int by = j+1;
				int ax = i+1;
				int ay = j+1;
				copy_Node.setMove("("+ bx + "," + by + "):" + state[i-1][j] + ":(" + ax + "," + ay + ")");
				new_.add(copy_Node);
			} 
		}		
	}
	private void moveRight(Queue<Node> new_, int i, int j) {
		if(j > 0  && state[i][j-1]!=('_')) 
		{
		
			char new_array[][] = copy_array(state);
			swap(new_array,i,j,"left");
			if(!board.equals(this.toStringarr(new_array))) {
				count++;
				Node copy_Node = new Node(new_array);
				copy_Node.board = this.toString();
				if(new_array[i][j]==('R')) copy_Node.setCost(1);
				else if(new_array[i][j]==('Y')) copy_Node.setCost(1);
				else if(new_array[i][j]==('B')) copy_Node.setCost(2);
				else if(new_array[i][j]==('G')) copy_Node.setCost(10);
				int bx = i+1;
				int by = j;
				int ax = i+1;
				int ay = j+1;
				copy_Node.setMove("("+ bx + "," + by + "):" + state[i][j-1] + ":(" + ax + "," + ay + ")");;
				new_.add(copy_Node);
			}
		}
	}
	private void moveUp(Queue<Node> new_, int i, int j) {
		if(i < state.length-1 && state[i+1][j]!=('_')) 
		{
			
			char new_array[][] = copy_array(state);
			swap(new_array,i,j,"down");
			if(!board.equals(this.toStringarr(new_array))) {
				count++;
				Node copy_Node = new Node(new_array);
				copy_Node.board = this.toString();
				if(new_array[i][j]==('R')) copy_Node.setCost(1);
				else if(new_array[i][j]==('Y')) copy_Node.setCost(1);
				else if(new_array[i][j]==('B')) copy_Node.setCost(2);
				else if(new_array[i][j]==('G')) copy_Node.setCost(10);
				int bx = i+2;
				int by = j+1;
				int ax = i+1;
				int ay = j+1;
				copy_Node.setMove("("+ bx + "," + by + "):" + state[i+1][j] + ":(" + ax + "," + ay + ")");
				new_.add(copy_Node);
			}
		}
	}
	private void moveLeft(Queue<Node> new_,int i , int j)
	{
		if(j  < state[0].length-1 && state[i][j+1]!=('_')) 
		{
		
			char new_array[][] = copy_array(state);
			swap(new_array,i,j,"right");
			if(!board.equals(this.toStringarr(new_array))) {
				count++;
				Node copy_Node = new Node(new_array);
				copy_Node.board = this.toString();
				if(new_array[i][j]==('R')) copy_Node.setCost(1);
				else if(new_array[i][j]==('Y')) copy_Node.setCost(1);
				else if(new_array[i][j]==('B')) copy_Node.setCost(2);
				else if(new_array[i][j]==('G')) copy_Node.setCost(10);
				int bx = i+1;
				int by = j+2;
				int ax = i+1;
				int ay = j+1;
				copy_Node.setMove("("+ bx + "," + by + "):" + state[i][j+1] + ":(" + ax + "," + ay + ")");
				new_.add(copy_Node);
			}
		}
	}

	
	public String toString() {
		String result ="";
		for(int i = 0;i<this.state.length;i++) {
			for(int j = 0; j<this.state[0].length;j++) {
				result+=this.state[i][j] + ",";
			}
			result+='\n';
		}

		return result;
	}
	
	public String toStringarr(char[][] c) {
		String result ="";
		for(int i = 0;i<c.length;i++) {
			for(int j = 0; j<c.length;j++) {
				result+=c[i][j] + ",";
			}
			result+='\n';
		}

		return result;
	}



	public String getMove() {
		return move;
	}


	public void setMove(String move) {
		this.move = move;
	}


	public boolean isOut() {
		return isOut;
	}


	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}


	public Node getParent() {
		return parent;
	}


	public void setParent(Node parent) {
		this.parent = parent;
	}


	public char[][] getState() {
		return state;
	}


	public void setState(char[][] state) {
		this.state = state;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	public int getToatal_price() {
		return toatal_price;
	}


	public void setToatal_price(int toatal_price) {
		this.toatal_price = toatal_price;
	}


	public int getHeru_value() {
		return heru_value;
	}


	public void setHeru_value(int heru_value) {
		this.heru_value = heru_value;
	}


	public int getF_G() {
		return F_G;
	}


	public void setF_G(int f_G) {
		F_G = f_G;
	}


	public boolean isGoal(Node goal){
		if(goal.toString().equals(this.toString())) {
			return true;
		}
		return false;
	} 

}


