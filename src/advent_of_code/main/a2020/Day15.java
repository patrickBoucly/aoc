package advent_of_code.main.a2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

	public static void main(String[] args) {
		System.out.println("solve151 :" + solve151("C:\\\\AOC\\\\input15"));
		// System.out.println("solve152 :" + solve152("input15_sample"));
		// System.out.println("solve152 :" + solve152("C:\\AOC\\input15"));
		// System.out.println("solve102 :" + solve152("input15"));

	}

	private static String solve151(String string) {
		List<String> ex1 = Arrays.asList("2","15","0","9","1","20");
		Map<String, Integer> dejaDit = new HashMap<>();
		Map<String, Integer> dejaDitSuivant = new HashMap<>();
		String motAdire = "";
		String prochainMot = "";
		int tour = 1;
		for (String s : ex1) {
			dejaDit.put(s, tour);
			tour++;
		}
		dejaDitSuivant=dejaDit;
		
		String motADire="0";
		while (tour < 30000000) {
			dejaDit=dejaDitSuivant;
			
			if(dejaDit.containsKey(motADire)) {
			//	System.out.println("deja dit!");
			//	System.out.println("nous sommes au tour :"+tour);
			//	System.out.println("Mot à dire "+motADire+" déjà dit au tour n°"+dejaDit.get(motADire));
				prochainMot=String.valueOf(tour - dejaDit.get(motADire));
			//	System.out.println("prochain mot "+prochainMot);
				dejaDitSuivant.put(motADire,tour);
				motADire=prochainMot;
			//	System.out.println("Le prochain mot à dire sera : "+motADire);
			} else {
			//	System.out.println("jamais dit!");
				dejaDitSuivant.put(motADire,tour);
				motADire="0";
			//	System.out.println("Le prochain mot à dire sera : "+motADire);
			}
			tour++;
			System.out.println(tour);
		}
		
		return motADire;
	}

	

}
