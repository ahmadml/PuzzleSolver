import java.util.Hashtable;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
public class IDA {
	//start node
	Node start;
	//finish node
	Node board_goal;
	//finish node
	outPut out;
	int num = 1  ;
	public IDA(Node start, Node finish, outPut out)
	{
		this.start = start;
		this.start.setParent(null);
		this.board_goal = finish;
		this.out = out; 
	}
	public void IDA_algo() 
	{
		heurstic heur = new heurstic();
		//create openList
		Hashtable<String,Node> H = new Hashtable<String,Node>();  
		//create stack 	
		Stack<Node> myStack = new Stack<Node>();
		int minF = 0 ;
		Node dad = new Node(this.start.getState());
		dad.board="";
		int t = heur.manhattan_algorithmD(dad.getState(),board_goal.getState());
		dad.setF_G(t + dad.getCost());
		t = dad.getF_G();
		dad.isOut = false; 
		while(t < Integer.MAX_VALUE){					
			minF = Integer.MAX_VALUE;
			H.put(dad.key, dad);
			myStack.add(dad);
			while(!myStack.isEmpty()) 
			{ 
				if(this.out.isOpen)				
					printOpenList(H);
				dad = myStack.pop();
				if(dad.isOut) {
					H.remove(dad.key);
				}else {
					dad.isOut = true; 
					myStack.add(dad);
					Queue<Node> new_ = dad.findStates();

					for(Node son : new_) {
						num++;
						son.setToatal_price((int) (son.getCost()+ dad.getToatal_price()));

						son.setParent(dad);

						son.setF_G((son.getToatal_price() +  heur.manhattan_algorithmD(son.getState() , board_goal.getState())));

						if(son.getF_G() > t ) {
							minF = Math.min(minF, son.getF_G());
							continue;
						}

						if(H.get(son.key)!= null && H.get(son.key).isOut) {
							continue;
						}

						if(H.get(son.key) != null && !H.get(son.key).isOut) {
							if(H.get(son.key).getF_G() > son.getF_G()) {
								myStack.remove(H.get(son.key));
								H.remove(son.key);
							}
							else {
								continue;
							}
						}
						if(son.isGoal(board_goal)) {
							double end = System.currentTimeMillis();
							double sec = (end - solve.time) / 1000.0;
							this.out.setRuntime(sec);
							this.write(son,num,true);
							return;
						}
						myStack.add(son);
						H.put(son.key, son);
					}
				}
			}
			dad.isOut = false;
			t = minF;
		}
		double end = System.currentTimeMillis();
		double sec = (end - solve.time) / 1000.0;
		this.out.setRuntime(sec);
		this.write(null,num,false);
		return;	
	}
	private void printOpenList(Hashtable<String, Node> openList) 
	{
		System.out.println();
		Set<String> my_Set = openList.keySet();
		for (String part : my_Set) {
			System.out.println(part);
			System.out.println("----Next---\n");
		}
	}
	private void write(Node node,int n,boolean has_path) 
	{
		calculate(node);
		out.setNum(n);
		this.out.writeToFile(has_path);	
	}	
	private void calculate(Node son) {
		//moves
		String moves="";
		//cost
		int cost = 0;
		Stack<Node> my_stack = new Stack<Node>();
		while(son!=null) 
		{
			my_stack.push(son);
			son = son.getParent();
		}
		if (!my_stack.isEmpty()) {
			my_stack.pop();
		}

		while(!my_stack.isEmpty()) 
		{	
			Node iter1 = my_stack.pop();
			if (my_stack.size() == 0)
				moves = moves + iter1.getMove();
			else
				moves = moves + iter1.getMove()+"--";
			cost += iter1.getCost();

		}

		out.setMoves(moves);
		out.setCost(cost);
	}
}
