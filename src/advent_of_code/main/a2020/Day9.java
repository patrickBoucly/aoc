package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {

	public static void main(String[] args) {
		System.out.println("solve91 :" + solve91(25));
		System.out.println("solve92 :" + solve92(25));
	}

	private static Long solve92(int pas) {
		List<Long> input = getData();
		List<Long> subList;
		List<Long> subListSolution = null;
		boolean trouve = false;
		Map<Boolean, List<Long>> res = new HashMap<>();
		int i = 0;
		while (i < input.size() && !trouve && i < 1000) {
			subList=input.subList(i, input.size());
			res = analyse92Sublist(subList, pas);
			if (res.containsKey(true)) {
				subListSolution=res.get(true);
				trouve=true;
			} else {
				i++;
			}
		}
		return (subListSolution.stream().min(Comparator.comparing(l->l.toString())).get()+subListSolution.stream().max(Comparator.comparing(l->l.toString())).get());
	}

	private static Map<Boolean, List<Long>> analyse92Sublist(List<Long> subList, int pas) {
		Long chiffreSolution91 = solve91(pas);
		
		long total = 0;
		Map<Boolean, List<Long>> res = new HashMap<>();
		for (int j = 0; j < subList.size(); j++) {
			total += subList.get(j);
			if (total == chiffreSolution91) {
				res.put(true, subList.subList(0, j+1));
				return res;
			} else if (total > chiffreSolution91) {
				res.put(false,  subList.subList(0, j+1));
				return res;
			}
		}
		res.put(false, null);
		return res;
	}

	private static Long solve91(int pas) {

		Long res = (long) 0;
		List<Long> input = getData();
		boolean solve = false;
		Map<Boolean, Long> lesAnalyse = new HashMap<>();
		while (!solve) {
			lesAnalyse.putAll(analyse(input, pas));
			res = lesAnalyse.get(false);
			solve = lesAnalyse.get(true) != null;
		}
		return res;
	}

	private static Map<Boolean, Long> analyse(List<Long> input, Integer pas) {
		Map<Boolean, Long> res = new HashMap<>();
		boolean toutBon = true;
		while (toutBon) {
			for (int i = 0; i < input.size() - pas; i++) {
				if (toutBon) {
					boolean resUnitaire = analyseUneChaine(input.subList(i, i + pas + 1));
					if (!resUnitaire) {
						toutBon = false;
						res.put(false, input.get(i + pas));
					}
				}
			}
			res.put(true, (long) 0);
			// pour finir le while, analyse terminee
			toutBon = false;
		}
		return res;
	}

	private static boolean analyseUneChaine(List<Long> subList) {
		Long chiffre = subList.get(subList.size() - 1);
		boolean res = false;
		for (int i = 0; i < subList.size(); i++) {
			if (!res) {
				for (int j = 0; j < subList.size(); j++) {
					if (!res) {
						if (subList.get(i) != subList.get(j)) {
							if (!res) {
								res = (subList.get(i) + subList.get(j) == chiffre);
							}
						}
					}
				}
			}
		}
		return res;
	}

	private static ArrayList<Long> getData() {
		Path path = Paths.get("C:\\AOC\\input9");
		FileReader fileReader;
		ArrayList<Long> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {

					res.add(Long.parseLong(bufferReader.readLine()));

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
