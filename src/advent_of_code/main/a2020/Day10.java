package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {

	public static void main(String[] args) {
		 System.out.println("solve101 :" + solve101("input10"));
		//System.out.println("solve102 :" + solve102("input10_sample"));
		//System.out.println("solve102 :" + solve102("input10_sample2"));
		System.out.println("solve102 :" + solve102("input10"));

	}

	private static Long solve102(String nomFic) {
		List<Integer> input = getData(nomFic).stream().sorted().collect(Collectors.toList());
		input.add(0, 0);
		Map<Integer, Integer> mapOccurenceLongueur = getNBOccurenceNCons(input);
		Long res=(long)1;
		
		for(Integer i:mapOccurenceLongueur.keySet()) {
			switch(i) {
			case 1:
				  res=(long) (res*Math.pow(1,mapOccurenceLongueur.get(1)));
			    break;
			  case 2:
				  res=(long) (res*Math.pow(1,mapOccurenceLongueur.get(2)));
			    break;
			  case 3:
				  res=(long) (res*Math.pow(2,mapOccurenceLongueur.get(3)));
			    break;
			  case 4:
				  res=(long) (res*Math.pow(4,mapOccurenceLongueur.get(4)));
				    break;
				  case 5:
					  res=(long) (res*Math.pow(7,mapOccurenceLongueur.get(5)));
				    break;
				 
			  default:
			   System.out.println("pb!!!!!!!!!!!");
			}	
		}
		
		return res;
	}

	private static Map<Integer, Integer> getNBOccurenceNCons(List<Integer> input) {
		Map<Integer, Integer> res = new HashMap<>();
		int longueurChaineActuelle = 1;
		int i = 0;
		while (i < input.size() -1) {
			i++;
			if (input.get(i-1 ) == (input.get(i) - 1)) {
				longueurChaineActuelle++;
				
			} else {
				if (res.get(longueurChaineActuelle) == null) {
					res.put(longueurChaineActuelle, 0);
					
				}
				res.put(longueurChaineActuelle, res.get(longueurChaineActuelle) + 1);
				if(longueurChaineActuelle==5) {
				}
				longueurChaineActuelle = 1;
			}
		}
		if (res.get(longueurChaineActuelle) == null) {
			res.put(longueurChaineActuelle, 0);
			
		}
		res.put(longueurChaineActuelle, res.get(longueurChaineActuelle) + 1);
		if(longueurChaineActuelle==5) {
		}
		return res;
	}

	private static Integer solve101(String nomFic) {
		List<Integer> input = getData(nomFic).stream().sorted().collect(Collectors.toList());
		input.add(0, 0);
		Integer lastValue = input.get(input.size() - 1) + 3;
		input.add(input.size(), lastValue);
		int cpt1 = 0;
		int cpt3 = 0;
		int diff = 0;
		for (int i = 1; i < input.size() ; i++) {
			diff = input.get(i ) - input.get(i-1);
			if (diff == 1) {
				cpt1++;
			} else if (diff == 3) {
				cpt3++;
			} else {
				System.out.println("PROBLEME");
			}

		}
		return cpt1 * cpt3;
	}


	private static ArrayList<Integer> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\"+nomFic);
		FileReader fileReader;
		ArrayList<Integer> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {

					res.add(Integer.parseInt(bufferReader.readLine()));

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
