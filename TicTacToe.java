package tictactoe;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}
	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}



	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null) return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) { }
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}

public class TicTacToe {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		boolean win = false;
		int placement = 0;
		boolean xTurn = true;
		int size = 4;
		int[] grid = new int[size*size];
		//0 = empty, 1 = X, -1 = O
		System.out.printf("Please enter a number for where you want to make a move (0-%d)\n", (size*size -1));
		//X starts first
		printGrid(grid);
		while (!win){
			placement = io.getInt();
			if (placement>-1&&placement<size*size){
				if(grid[placement]==0){
					if(xTurn){
						grid[placement] = 1;
						printGrid(grid);
						xTurn = false;
						if(checkWin(grid)>0){
							win = true;
							System.out.printf("Player %d wins!", checkWin(grid));
						}
					}else{
						grid[placement] = -1;
						printGrid(grid);
						xTurn = true;
						if(checkWin(grid)>0){
							win = true;
							System.out.printf("Player %d wins!", checkWin(grid));
						}
					}
				}else{
					System.out.println("That spot is already filled.");
				}
			}else{
				System.out.println("Please enter a valid number.");
			}

		}
		io.close();
	}

	static void printGrid(int[] grid){
		int line = 0;
		double size = Math.sqrt((double) grid.length);
		int read;
		System.out.printf("Current grid: \n");
		while(line<size){
			for(int i = 0; i<size; i++){
				read = grid[line*(int) size + i];
				if(read==0){
					System.out.printf("_ ");
				}else if(read==1){
					System.out.printf("X ");
				}else{
					System.out.printf("O ");
				}
			}
			System.out.printf("\n");
			line++;
		}
	}
	static int checkWin(int[] grid){
		int size = (int) Math.sqrt((double) grid.length);
		//check horizontal win condition
		int sum = 0;
		for(int i = 0; i<size; i++){
			sum = 0;
			for (int x = 0; x<size; x++){
				sum += grid[size*i + x];
			}
			if (sum==size){
				return 1;
			}else if(sum==-1 * size){
				return 2;
			}
		}
		//check vertical win condition
		for(int i = 0; i<size;i++){
			sum = 0;
			for (int x=0; x<size; x++){
				sum += grid[size*x + i];
			}
			if(sum == size){
				return 1;
			}else if(sum == -1*size){
				return 2;
			}
		}
		//check diagonal win conditions
		sum = 0;
		for (int i = 0; i<size; i++){
			sum += grid[size*i+i];
			if(sum == size){
				return 1;
			}else if(sum == -1*size){
				return 2;
			}
		}
		sum = 0;
		for (int i = 0; i<size; i++){
			sum += grid[size*i + size - 1 - i];
			if(sum == size){
				return 1;
			}else if(sum == -1*size){
				return 2;
			}
		}
		//no winner
		return 0;
	}


}
