package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day19bis {

	public static void main(String[] args) {
		System.out.println(	solve191("C:\\AOC\\input19_sample"));
	}

	private static long solve191(String monFic) {
		List<String> messages = getMessages(monFic);
		HashMap<Integer, String> rules = getRules(monFic);
		long tot=0;
		for(String m:messages) {
			if(respectR0(m, rules)) {
				tot++;
			}
		}
		return tot;

	}

	private static boolean respectR0(String m, HashMap<Integer, String> rules) {
		boolean correct = true;
		String r0 = rules.get(0);
		String[] r0t = r0.split(" ");
		int pos=0;

		for (String s : r0t) {
			correct = respectRN(Integer.valueOf(s), rules, m,pos);
			if (!correct) {
				return false;
			}
		}
		return correct;
	}

	private static boolean respectRN(Integer ruleNb, HashMap<Integer, String> rules, String m, int pos) {
		boolean resultat = true;
		if (rules.get(ruleNb).contains("|")) {
			String tmp = rules.get(ruleNb).trim();
			String[] rule = tmp.split("|");
			boolean droite = false;
			String ruleg = "";
			String ruled = "";
			for (String s : rule) {
				if (s.equals("|")) {
					droite = true;
				} else {

					if (!droite) {
						ruleg += s;
					} else {
						ruled += s;
					}
				}
			}
			if(ruled.subSequence(0, 1).equals(" ")) {
				ruled=ruled.substring(1);
			}
			String[] rulegT = ruleg.split(" ");
			String[] ruledT = ruled.split(" ");
			if (rulegT.length == 1) {
				if (!respectRN(Integer.valueOf(rulegT[0]), rules, m,pos)) {
					return false;
				}
			} else {
				if (!respectRN(Integer.valueOf(rulegT[0]), rules, m,pos)
						|| !respectRN(Integer.valueOf(rulegT[1]), rules, m,pos)) {
					return false;
				}
			}
			if (ruledT.length == 1) {
				if (!respectRN(Integer.valueOf(ruledT[0]), rules, m,pos)) {
					return false;
				}
			} else {
				if (!respectRN(Integer.valueOf(ruledT[0]), rules, m,pos)
						|| !respectRN(Integer.valueOf(ruledT[1]), rules, m,pos)) {
					return false;
				}

			}
		} else if (!(rules.get(ruleNb).contains("a") || rules.get(ruleNb).contains("b"))) {
			String[] ruleT = rules.get(ruleNb).split(" ");
			if (ruleT.length == 1) {
				if (!respectRN(Integer.valueOf(ruleT[0]), rules, m,pos)) {
					return false;
				}
			} else {
				if (!respectRN(Integer.valueOf(ruleT[0]), rules, m,pos)
						|| !respectRN(Integer.valueOf(ruleT[1]), rules, m,pos)) {
					return false;
				}
			}
		} else {
			String x = "";
			if (rules.get(ruleNb).contains("a")) {
				x = "a";
			} else {
				x = "b";
			}
			resultat = (m.substring(pos,pos+ 1).equals(x));
			if(!resultat) {
				return resultat;
			} else {
				if(m.length()<=pos) {
					return resultat;		
				} else {
					respectRN(ruleNb,rules, m,pos+1);
				}
			}
		}
		return resultat;
	}

	private static List<String> getMessages(String monFic) {
		BufferedReader buffer;
		List<String> lignes = new ArrayList<>();
		boolean commencer = false;
		try {
			buffer = new BufferedReader(new FileReader(monFic));
			String line;
			while ((line = buffer.readLine()) != null) {
				if (commencer) {
					lignes.add(line.replaceAll("\\s+", ""));
				}
				if (line.contains("@")) {
					commencer = true;
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}

	public static List<String> getListStringUnParLigne(String path) {
		BufferedReader buffer;
		List<String> lignes = new ArrayList<>();
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			while ((line = buffer.readLine()) != null) {
				lignes.add(line.replaceAll("\\s+", ""));
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}

	private static HashMap<Integer, String> getRules(String monFic) {
		BufferedReader buffer;
		HashMap<Integer, String> rules = new HashMap<>();
		boolean arreter = false;
		try {
			buffer = new BufferedReader(new FileReader(monFic));
			String line;
			while (((line = buffer.readLine()) != null) && !arreter) {
				if (line.contains("@")) {
					arreter = true;
				}
				if (!arreter) {
					rules.put(Integer.parseInt(line.split(":")[0]), line.split(":")[1].substring(1));
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rules;
	}
}
