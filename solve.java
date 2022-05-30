public class solve {
	
	char board [][] ;
	char board_goal [][] ;	
	outPut out ;
	String algo ;
	boolean time_ ;
	static double time = 0.0;
	public solve(char[][]brd ,char[][] goal , String algo , boolean time, boolean open ) 
	{
		this.board  = brd ;
		this.board_goal = goal ;
		this.algo = algo;
		this.time_= time ;
		this.out = new outPut(open,time);
		run_algo();
	}
	private void run_algo() {
		Node start = new Node(this.board);
		start.board = "";
		Node finish = new Node(this.board_goal);
		time = System.currentTimeMillis();
		switch(this.algo) {
		case "BFS":
			BFS bfs_algo = new BFS(start,finish,this.out);
			bfs_algo.BFS_algo();
			break;
		case "DFID":
			DFID DFID_algo = new DFID(start,finish,this.out);
			DFID_algo.DFID_algo();
			break;
		case "A*":
			aStar aStar = new aStar(start , finish ,this.out);
			aStar.aStar_algo();
			break;
		case "IDA*":
			IDA ida = new IDA(start , finish ,this.out);
			ida.IDA_algo();
			break;
		case "DFBnB":
			DFBnB dfbnb = new DFBnB(start , finish ,this.out);
			String a = dfbnb.DFBnB_algo();
			break;
		default:
			System.err.println("no Algorithm has choosen");	  
		}
	}
}