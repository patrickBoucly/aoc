package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Day22 {
	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));

		// System.out.println("solve181 :" + solve181("C:\\AOC\\input18",1));
		
		// Creating a File object that represents the disk file. 
  
		
		solve222("C:\\AOC\\input22_sample");

	}

	private static String solve222(String string) {
		
		
		
	      PrintStream o = null;
			try {
				o = new PrintStream(new File("C:\\AOC\\output22.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  
	        // Store current System.out before assigning a new value 
	        PrintStream console = System.out; 
	  
	        // Assign o to output stream 
	     //   System.setOut(o); 
	        System.out.println("This will be written to the text file"); 
	  
	     
		
		
		
		
		
		List<Integer> lj1 = getListJ1(string);
		List<Integer> lj2 = getListJ2(string);
		// List<Integer> lj1 = Arrays.asList(1,43,19);
		// List<Integer> lj2 = Arrays.asList(3,2,29,14);
		Stack<Integer> j1 = new Stack<>();

		Stack<Integer> j2 = new Stack<>();
		for (int j = lj1.size() - 1; j >= 0; j--) {
			j1.add(lj1.get(j));

		}
		for (int j = lj2.size() - 1; j >= 0; j--) {

			j2.add(lj2.get(j));
		}

		Integer numG = 1;
		Integer numR = 1;
		while (j1.size() > 0 && j2.size() > 0) {
			System.out.println("-- Round " + numR + " (Game " + numG + ") --");

			System.out.println("Player 1's deck:" + j1);
			System.out.println("Player 2's deck:" + j2);
			System.out.println("Player 1 plays: " + j1.peek());
			System.out.println("Player 2 plays: " + j2.peek());

			//if (j1.peek() < (j1.size()) && j2.peek() < (j2.size())) {
			if (!(j1.peek() > (j1.size()-1) || j2.peek() > (j2.size()-1))) {
				System.out.println("sub game!");
				boolean j1WinSubGame = j1winSubGamr(j1, j2, numG);
				if (j1WinSubGame) {
					System.out.println("j1 win subgame " + (numG + 1));
					j1.add(0, j1.pop());
					j1.add(0, j2.pop());
				} else {
					System.out.println("j2 win subgame " + (numG + 1));
					j2.add(0, j2.pop());
					j2.add(0, j1.pop());
				}
				numR++;
			} else {

				System.out.println("Pas de sub game");
				if (j1.peek() > j2.peek()) {
					System.out.println("Player 1 wins Round " + numR + " Game " + numG + "!");
					j1.add(0, j1.pop());
					j1.add(0, j2.pop());
				} else {
					System.out.println("Player 2 wins Round " + numR + " Game " + numG + "!");
					j2.add(0, j2.pop());
					j2.add(0, j1.pop());
				}
				numR++;
			}
		}
		System.out.println(j1);
		System.out.println(j2);

		int tot = 0;
		int coef = 1;
		if (j1.size() > 0) {

			tot = total(j1);
		} else {

			tot = total(j2);
		}

		System.out.println(tot);
		System.out.println(j1);
		return "";

	}

	private static boolean j1winSubGamr(Stack<Integer> j1o, Stack<Integer> j2o, Integer numG) {
		Stack<Integer> j1 = new Stack<>();
		Stack<Integer> j2 = new Stack<>();
		Stack<Integer> j1c = new Stack<>();
		Stack<Integer> j2c = new Stack<>();
		j1.addAll(j1o);
		j2.addAll(j2o);
		j1.pop();
		j2.pop();
		Integer numR = 1;
		Integer newG = numG + 1;
		HashMap<Integer, List<Stack>> h = null;
		ArrayList<Stack> m = new ArrayList<>();

		while (j1.size() > 0 && j2.size() > 0) {

			if (h != null) {
				// System.out.println(h);
				for (Integer numeroR : h.keySet()) {

					if (h.get(numeroR).get(0).equals(j1) && h.get(numeroR).get(1).equals(j2)) {
						
						  System.out.println("boucle infini! j1 gagne :)");
						  System.out.println(numeroR); System.out.println(numR);
						  System.out.println(j1); System.out.println(h.get(numeroR).get(0));
						  System.out.println(j2); System.out.println(h.get(numeroR).get(1));
						 
						return true;
					}
				}
			} else {
				h = new HashMap<Integer, List<Stack>>();
			}
			
			m = new ArrayList<>();
			j1c = new Stack<>();
			j2c = new Stack<>();
			j1c.addAll(j1);
			j2c.addAll(j2);
			m.add(j1c);
			m.add(j2c);
			h.put(numR, m);
			
			 System.out.println(h); System.out.println("numR :"+numR );
			  
			  System.out.println("-- Round " + numR + " (Game " + newG + ") --");
			  System.out.println("Player 1's deck:" + j1);
			  System.out.println("Player 2's deck:" + j2);
			  System.out.println("Player 1 plays: " + j1.peek());
			  System.out.println("Player 2 plays: " + j2.peek());
			 
			if (j1.peek() < (j1.size()) && j2.peek() < (j2.size())) {
				 System.out.println("sub game!" + (newG + 1) + " va démarrer!");
				boolean j1WinSubGame = j1winSubGamr(j1, j2, newG);
				if (j1WinSubGame) {
					j1.add(0, j1.pop());
					j1.add(0, j2.pop());
					 System.out.println("Player 1 wins sugG Round " + numR + " Game " + (newG + 1)
					 + "!");
				} else {
					j2.add(0, j2.pop());
					j2.add(0, j1.pop());

					 System.out.println("Player 2 wins sugG Round " + numR + " Game " + (newG + 1)
					 + "!");
				}
				System.out.println("increment round");
				numR++;
				 System.out.println(numR);
			} else {
				 System.out.println("Pas de sub game");
				if (j1.peek() > j2.peek()) {
					j1.add(0, j1.pop());
					j1.add(0, j2.pop());

					 System.out.println("Player 1 wins Round " + numR + " Game " + newG + "!");
				} else {
					j2.add(0, j2.pop());
					j2.add(0, j1.pop());

					 System.out.println("Player 2 wins Round " + numR + " Game " + newG + "!");
				}
				 System.out.println("increment round");
				numR++;
				
				 System.out.println(numR);
				 System.out.println("Taille histo :"+h.size());
				
			}
		}
		if (j2.size() == 0) {
			 System.out.println("Player 1 wins sub Game " + newG + "!");
		} else {
			 System.out.println("Player 2 wins sub Game " + newG + "!");
		}
		return (j2.size() == 0);
	}

	private static void solve221(String string) {
		List<Integer> lj1 = getListJ1(string);
		List<Integer> lj2 = getListJ2(string);
		Stack<Integer> j1 = new Stack<>();
		Stack<Integer> j2 = new Stack<>();
		for (int j = lj1.size() - 1; j >= 0; j--) {
			j1.add(lj1.get(j));
			j2.add(lj2.get(j));
		}

		System.out.println(j2.peek());

		while (j1.size() > 0 && j2.size() > 0) {
			if (j1.peek() > j2.peek()) {
				j1.add(0, j1.pop());
				j1.add(0, j2.pop());
			} else {
				j2.add(0, j2.pop());
				j2.add(0, j1.pop());
			}
		}

		int tot = 0;
		int coef = 1;
		if (j1.size() > 0) {
			System.out.println(j1);
			tot = total(j1);
		} else {
			System.out.println(j2);
			tot = total(j2);
		}

		System.out.println(tot);

	}

	private static int total(Stack<Integer> j) {
		int tot = 0;
		int coef = j.size();
		while (j.size() > 0) {
			tot = tot + coef * j.pop();
			coef--;
		}
		return tot;
	}

	public static List<Integer> getListJ1(String path) {
		List<Integer> list = new ArrayList<>();
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			boolean commencer = false;

			while ((!(line = buffer.readLine()).equals("@"))) {
				if (commencer) {
					list.add(Integer.parseInt(line));
				}
				if (line.contains("Player 1")) {
					commencer = true;
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Integer> getListJ2(String path) {
		List<Integer> list = new ArrayList<>();
		BufferedReader buffer;
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			boolean commencer = false;

			while ((line = buffer.readLine()) != null) {
				if (commencer) {
					list.add(Integer.parseInt(line));
				}
				if (line.contains("Player 2")) {
					commencer = true;
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
