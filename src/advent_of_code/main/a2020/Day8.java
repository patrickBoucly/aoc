package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {

	public static void main(String[] args) {
		System.out.println("solve81 :"+solve81());
		System.out.println("solve82 :"+solve82());
		

	}

	private static Integer solve82() {
		Integer res = 0;
		ArrayList<Instruction> data = getData();
		List<Instruction> sousListJmp = data.stream().filter(s -> s.getCommand().equals("jmp"))
				.collect(Collectors.toList());
		List<Instruction> sousListNop = data.stream().filter(s -> s.getCommand().equals("nop"))
				.collect(Collectors.toList());
		boolean resolu = false;
		ArrayList<Instruction> alterData = getData();
		;
		// Map<Boolean,Integer> solution=resolve(alterData);
		while (!resolu) {
			for (Instruction unJmp : sousListJmp) {
				if (!resolu) {
					alterData = getData();
					alterData.remove((int) unJmp.getPosition());
					alterData.add((int) unJmp.getPosition(),
							new Instruction("nop", unJmp.getAccu(), unJmp.getPosition()));

					Map<Boolean, Integer> solution = resolve(alterData);
					resolu = solution.containsKey(true);
					if (resolu) {
						res = solution.get(true);
					}
				}
			}
			for (Instruction unNop : sousListNop) {
				if (!resolu) {
					alterData = getData();
					;
					alterData.remove((int) unNop.getPosition());
					alterData.add((int) unNop.getPosition(),
							new Instruction("jmp", unNop.getAccu(), unNop.getPosition()));

					Map<Boolean, Integer> solution = resolve(alterData);
					resolu = solution.containsKey(true);
					if (resolu) {
						res = solution.get(true);
					}
				}
			}

		}
		//System.out.println("Résolu :" + resolu);
		return res;
	}

	private static Map<Boolean, Integer> resolve(ArrayList<Instruction> alterData) {
		Map<Boolean, Integer> res = new HashMap<>();
		int lastInstruction = alterData.size() - 1;
		Integer accumulateur = 0;
		Set<Integer> dejaVisite = new HashSet<>();
		int pos = 0;
		boolean fini = false;
		while (!fini) {
			if (dejaVisite.contains(pos)) {
				fini = true;
				res.put(false, accumulateur);
			} else if (pos == lastInstruction) {
				fini = true;
				res.put(true, accumulateur);
			} else {

				dejaVisite.add(pos);
				if (alterData.get(pos).getCommand().equals("nop")) {
					pos++;
				} else if (alterData.get(pos).getCommand().equals("acc")) {
					accumulateur += alterData.get(pos).getAccu();
					pos++;
				} else if (alterData.get(pos).getCommand().equals("jmp")) {
					pos += alterData.get(pos).getAccu();
					if (pos < 0 || pos > lastInstruction) {
						fini = true;
						res.put(false, accumulateur);
					}
				} else {
					System.out.println("invalide commande");
				}

			}
		}

		return res;
	}

	private static Integer solve81() {
		ArrayList<Instruction> data = getData();
		Integer accumulateur = 0;
		Set<Integer> dejaVisite = new HashSet<>();
		// System.out.println(data.stream().filter(s->s.getCommand().equals("jmp")).count());
		int pos = 0;
		while (true) {
			if (dejaVisite.contains(pos)) {
				return accumulateur;
			} else {
				dejaVisite.add(pos);
				if (data.get(pos).getCommand().equals("nop")) {
					pos++;
				} else if (data.get(pos).getCommand().equals("acc")) {
					accumulateur += data.get(pos).getAccu();
					pos++;
				} else if (data.get(pos).getCommand().equals("jmp")) {
					pos += data.get(pos).getAccu();
				} else {
					System.out.println("invalide commande");
				}
			}
		}
	}

	private static ArrayList<Instruction> getData() {
		Path path = Paths.get("C:\\AOC\\input8");
		FileReader fileReader;
		ArrayList<Instruction> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				Integer position = 0;
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					res.add(new Instruction(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]), position));
					position++;
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
