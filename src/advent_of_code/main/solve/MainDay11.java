package advent_of_code.main.solve;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import advent_of_code.main.utils.LectureFichiersUtils;


public class MainDay11 {

	public static void main(String[] args) {
		System.out.println("Question 1 - "+ getReponseQuestion1());
		System.out.println("Question 2 - "+ getReponseQuestion2());

	}

	private static long getReponseQuestion1() {
		Grille grille = new GrilleRule1(getData());
		grille.run();
		return grille.nbOccupied();
	}
		
	private static long getReponseQuestion2() {
		Grille grille = new GrilleRule2(getData());
		grille.run();
		return grille.nbOccupied();
	}

	
	
	private static List<List<String>> getData() {
		return LectureFichiersUtils.getData("input11").map(s -> Arrays.asList(s.split(""))).collect(Collectors.toList());
	}
}