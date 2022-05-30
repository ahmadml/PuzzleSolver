import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
public class Ex1 {

	public static void main(String[] args) throws Exception {
		//read from file
		File myFile = new File("src\\input.txt");
		Scanner input = new Scanner(myFile);
		//insert file to vector
		Vector<String> input_arguments = new Vector<String>(); 
		while(input.hasNext()) input_arguments.add(input.nextLine());
		run(input_arguments);
	}
	public static void run(Vector<String> input) {
		//which algo---------------
		String algo= input.get(0);
		//-------------------------
		boolean open = false;
		String isOpen = input.get(1);
		if(isOpen.contains("no")) open = false;
		else if(isOpen.equals("with open")) open = true;
		
		String size = input.get(2);
		int n = 0; 
		if(size.equals("small")) n = 3;
		else if(size.equals("big")) n = 5;
		char bord [][] = new char [n][n];
		char bord_goal [][] = new char[n][n];

		if(size.equals("small")) {
			String[] row1 = input.get(3).split(",");
			String[] row2 = input.get(4).split(",");
			String[] row3 = input.get(5).split(",");
			String[] rowg1 = input.get(7).split(",");
			String[] rowg2 = input.get(8).split(",");
			String[] rowg3 = input.get(9).split(",");
			for (int i = 0; i < bord.length; i++) {
				for (int j = 0; j < bord.length; j++) {
					if(i == 0) {bord[i][j] = row1[j].charAt(0);bord_goal[i][j] = rowg1[j].charAt(0);}
					if(i == 1) {bord[i][j] = row2[j].charAt(0);bord_goal[i][j] = rowg2[j].charAt(0);}
					if(i == 2) {bord[i][j] = row3[j].charAt(0);bord_goal[i][j] = rowg3[j].charAt(0);} 
				}
			}
		}
		else if(size.equals("big")) {
			String[] row1 = input.get(3).split(",");
			String[] row2 = input.get(4).split(",");
			String[] row3 = input.get(5).split(",");
			String[] row4 = input.get(6).split(",");
			String[] row5 = input.get(7).split(",");
			String[] rowg1 = input.get(9).split(",");
			String[] rowg2 = input.get(10).split(",");
			String[] rowg3 = input.get(11).split(",");
			String[] rowg4 = input.get(12).split(",");
			String[] rowg5 = input.get(13).split(",");
			for (int i = 0; i < bord.length; i++) {
				for (int j = 0; j < bord.length; j++) {
					if(i == 0) {bord[i][j] = row1[j].charAt(0);bord_goal[i][j] = rowg1[j].charAt(0);}
					if(i == 1) {bord[i][j] = row2[j].charAt(0);bord_goal[i][j] = rowg2[j].charAt(0);}
					if(i == 2) {bord[i][j] = row3[j].charAt(0);bord_goal[i][j] = rowg3[j].charAt(0);}
					if(i == 3) {bord[i][j] = row4[j].charAt(0);bord_goal[i][j] = rowg4[j].charAt(0);} 
					if(i == 4) {bord[i][j] = row5[j].charAt(0);bord_goal[i][j] = rowg5[j].charAt(0);} 
				}
			}
		}


		start(bord,bord_goal,algo,true,open);
	}
	private static void start(char[][] bord, char[][] bord_goal,String algo,boolean time,boolean isOpen) {
		solve slv = new solve(bord, bord_goal, algo,time, isOpen);
	}
}
