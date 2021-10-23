package advent_of_code.main.a2019;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class A2019Day2 {
	public static void main(String[] args) {
		System.out.println("solve021 :" + solve1("2019_2_1"));
		System.out.println("solve022 :" + solve2("2019_2_1"));
	}

	private static Integer solve2(String nomFic) {
		List<Integer> listI = getData(nomFic);
		Integer name = 0;
		Integer verbe = 0;
		Integer res =19690720 ;
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				listI = getData(nomFic);
				listI.set(1, i);
				listI.set(2, j);
				Integer calcul=analyseIntCode(listI).get(0);
				System.out.println("i :"+i+" j :"+j+" calcul :"+calcul);
				if ( calcul.equals(res)) {
					name = i;
					verbe = j;
					break;
				}
			}

		}
		Integer reponse = 100 * name + verbe;
		return reponse;
	}

	private static Integer solve1(String nomFic) {
		List<Integer> listI = getData(nomFic);
		listI.set(1, 12);
		listI.set(2, 2);
		return analyseIntCode(listI).get(0);
	}

	public static List<Integer> analyseIntCode(List<Integer> list) {
		ArrayList<Integer> listI = new ArrayList<>();
		for (Integer i : list) {
			listI.add(i);
		}
		int opcode = listI.get(0);
		for (int pos = 0; pos < listI.size() - 4; pos = pos + 4) {
			opcode = listI.get(pos);
			if (opcode == 1) {
				listI.set(listI.get(pos + 3), listI.get(listI.get(pos + 2)) + listI.get(listI.get(pos + 1)));
			} else if (opcode == 2) {
				listI.set(listI.get(pos + 3), listI.get(listI.get(pos + 2)) * listI.get(listI.get(pos + 1)));
			} else if (opcode == 99) {
				return listI;
			} else {
				return null;
			}
		}
		return listI;
	}

	private static ArrayList<Integer> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		ArrayList<Integer> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				while (bufferReader.ready()) {
					String[] listS = bufferReader.readLine().split(",");
					for (String s : listS) {
						res.add(Integer.parseInt(s));
					}

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
