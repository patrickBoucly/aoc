package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day19 {
	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));

		// System.out.println("solve181 :" + solve181("C:\\AOC\\input18",1));
		solve191("C:\\AOC\\input19");
	}

	private static long solve191(String monFic) {
		List<String> messages = getMessages(monFic);
		HashMap<Integer, String> rules = getRules(monFic);
		long res = 0;
		int posA = 0;
		int posB = 0;
		HashMap<Integer, String> regleConnue = new HashMap<>();
		for (Integer i : rules.keySet()) {
			if (rules.get(i).contains("a")) {
				posA = i;
			} else if (rules.get(i).contains("b")) {
				posB = i;
			}

		}
		rules.put(posA, " a ");
		rules.put(posB, " b ");
		regleConnue.put(posA, " a ");
		regleConnue.put(posB, " b ");

		while (regleConnue.size() < rules.size()) {
			for (Integer i : rules.keySet()) {
				if (!isConnue(rules.get(i))) {

					for (Integer numRegleC : regleConnue.keySet()) {
						if (rules.get(i).contains("" + numRegleC + "")) {
							if (numRegleC == posB) {

								rules.put(i, rules.get(i).replaceAll(" " + String.valueOf(numRegleC) + " ", " b "));
							} else if (numRegleC == posA) {

								rules.put(i, rules.get(i).replaceAll(" " + String.valueOf(numRegleC) + " ", " a "));
							} else {

								rules.put(i, rules.get(i).replaceAll(" " + String.valueOf(numRegleC) + " ",
										" (" + rules.get(numRegleC) + ") "));

							}
						}
					}
				} else {

					regleConnue.put(i, rules.get(i));
				}
			}
		}

		for (Integer i : rules.keySet()) {

			System.out.println("i :" + i + " rule : " + rules.get(i));
			rules.put(i, clean(rules.get(i)));
			System.out.println("i :" + i + " rule : " + rules.get(i));

		}

		for (Integer i : regleConnue.keySet()) {

			System.out.println("i :" + i + " ruleConnue : " + regleConnue.get(i));

		}

		String regle0 = rules.get(0).replaceAll(" ", "");
		System.out.println(regle0);
		Integer nbPipe = compterPipe(regle0);
		System.out.println(nbPipe);
		List<String> m = new ArrayList<String>();
		m.add(regle0);
		int cpt = 0;
		boolean firstRound = true;
		while (firstRound || (m.get(0).contains("|"))) {
			cpt++;
			System.out.println(m);
			List<String> aJouter = removeUnPipe(m);
			System.out.println("A ajouter " + aJouter);
			// m.addAll(aJouter);
			m.add(aJouter.get(0));
			System.out.println("nbPipe " + nbPipe);
			if (firstRound) {
				firstRound = false;
				m.remove(0);
			} else {

				for (int j = m.size() - 1; j >= 0; j--) {
					System.out.println("compterPipe(m.get(j)) " + compterPipe(m.get(j)));
					System.out.println((compterPipe(m.get(j)).equals(nbPipe)));
					if (compterPipe(m.get(j)).equals(nbPipe)) {
						System.out.println("A retirer : " + m.get(j));
						m.remove(j);
					}
				}
			}
			System.out.println("taille de m : " + m.size());
			nbPipe = compterPipe(m.get(0));
			cpt++;
			System.out.println("nbPipe " + nbPipe);
			System.out.println("m.get0.size: " + m.get(0).length());
			System.out.println(m.get(0));
		}
		int tot = 0;
		for (String message : messages) {
			for (String messageOk : m.stream().distinct().collect(Collectors.toList())) {
				if (message.equals(messageOk)) {
					System.out.println(messageOk);
					tot++;
				}
			}
		}
		System.out.println("tot : " + tot);

		return res;
	}

	private static String clean(String rule) {
		String cleanRule = rule;
		boolean isACleaner = aCleaner(rule);
		while (isACleaner) {
			cleanRule = cleanOne(cleanRule);
			isACleaner = aCleaner(cleanRule);
		}
		return cleanRule;
	}

	private static String cleanOne(String cleanRule) {
		String res = "";
		String cleanRule1 = cleanRule.replaceAll(" ", "");
		int j = 0;
		int k = 0;
		boolean fini = false;
		while (!fini) {
			if (cleanRule1.subSequence(j, j + 1).equals("(")) {
				k = j;
				if (k < cleanRule1.length()) {
					while (!cleanRule1.subSequence(k, k + 1).equals(")")) {
						k = k + 1;
					}
					if (!cleanRule1.substring(j, k + 1).contains("|")) {
						fini = true;
						return cleanRule1.substring(0, j) + cleanRule1.substring(j + 1, k)
								+ cleanRule1.substring(k + 1);

					}
				}
			}
			j++;

		}
		return cleanRule.substring(0, j) + cleanRule.substring(k + 1);
	}

	private static boolean aCleaner(String cleanRule) {
		boolean res = false;
		int j = 0;
		int l = cleanRule.length();
		for (int i = 0; i < l - 2; i++) {
			if (cleanRule.subSequence(i, i + 1).equals("(")) {
				j = i;

				while (!cleanRule.subSequence(j, j + 1).equals(")")) {
					j = j + 1;
				}
				if (!cleanRule.substring(i, j + 1).contains("|")) {
					return true;
				}
			}
		}
		return res;
	}

	private static Integer compterPipe(String regle0) {
		Integer res = 0;
		for (int i = 0; i < regle0.length(); i++) {
			if (regle0.subSequence(i, i + 1).equals("|")) {
				res++;
			}
		}

		return res;
	}

	private static List<String> removeUnPipe(List<String> m) {
		System.out.println("m :" + m);
		List<Integer> debPipefin = new ArrayList<>();
		List<String> res = new ArrayList<>();
		debPipefin = trouverPipe(m);
		System.out.println("!!!!!!!!!!!!!!!!!!!!" + debPipefin);
		if (debPipefin == null) {
			return m;
		}
		String s1 = "";
		String s2 = "";
		System.out.println("msize :" + m.size());
		System.out.println(debPipefin);
		for (String s : m) {

			s1 = "";
			s2 = "";
			System.out.println("s" + s);
			System.out.println(debPipefin);
			s1 = s1 + s.substring(0, debPipefin.get(0)) + s.substring(debPipefin.get(0) + 1, debPipefin.get(1))
					+ s.substring(debPipefin.get(2) + 1);
			s2 = s2 + s.substring(0, debPipefin.get(0)) + s.substring(debPipefin.get(1) + 1, debPipefin.get(2))
					+ s.substring(debPipefin.get(2) + 1);
			res.add(s1);
			res.add(s2);

		}
		return res;
	}

	private static List<Integer> trouverPipe(List<String> m) {
		System.out.println("Message : " + m);
		String d = m.get(0);
		int i = -1;
		boolean pipeOK = true;
		int w = -2;
		System.out.println("trouver un pipe longueur du message :" + d.length());
		while (i < d.length() - 1) {
			pipeOK = true;
			while (pipeOK && (i < d.length() - 2)) {
				i++;
				System.out.println(d + " " + i + " " + d.length());
				if (d.subSequence(i, i + 1).equals("|")) {
					// System.out.println("pipe en i " + i);
					// System.out.println(d.length());
					// System.out.println(i);
					int v = 1;
					while (w == -2) {
						if (i - v == -1) {
							w = -1;
						} else {
							if (d.subSequence(i - v, i - v + 1).equals("(")) {
								w = i - v;
							}
						}
						v++;
					}
					if (w != -1) {
						int j = i + 1;
						while (j < d.length() && pipeOK) {
							// System.out.println("j " + j);
							if (d.subSequence(j, j + 1).equals("(")) {
								pipeOK = false;
								// System.out.println("pipeOK est faux");
							}
							if (d.subSequence(j, j + 1).equals(")")) {
								System.out.println(") en j " + j);
								System.out.println("i :" + i + " j : " + j);
								System.out.println(d);
								return Arrays.asList(w, i, j);

							}
							j++;
						}
					} else {
						int j = i + 1;
						int t=0;
						while (!d.subSequence(j, j + 1).equals("|")) {
							j++;
						}
						return Arrays.asList(0, i, j);
					}
				}

			}

		}
		return null;
	}

	private static boolean isConnue(String string) {

		return !string.matches(".*\\d.*");
	}

	private static long match0(String message, HashMap<Integer, String> rules) {
		String rule = rules.get(0);
		List<Integer> numRegleAVerifier = new ArrayList<Integer>();
		String[] numbers = rule.split(" ");
		for (String s : numbers) {
			if (!s.equals("")) {
				numRegleAVerifier.add(Integer.parseInt(s));
			}
		}

		for (Integer i : numRegleAVerifier) {
			System.out.println(i);
		}
		StringBuilder messageAttendu = new StringBuilder();
		for (Integer i : numRegleAVerifier) {
			messageAttendu.append(i + " ");
		}

		boolean messageOK = analyser(numRegleAVerifier, rules, message, messageAttendu);
		if (messageOK) {
			return 1;
		} else {
			return 0;
		}
	}

	private static boolean analyser(List<Integer> numRegleAVerifier, HashMap<Integer, String> rules, String message,
			StringBuilder messageAttendu) {

		messageAttendu = construireMessageAttendu(numRegleAVerifier, messageAttendu, rules);

		return false;
	}

	private static StringBuilder construireMessageAttendu(List<Integer> numRegleAVerifier, StringBuilder messageAttendu,
			HashMap<Integer, String> rules) {
		String messageAtt = "";
		for (int i = 0; i < numRegleAVerifier.size(); i++) {
			if (!rules.get(i).contains("\"")) {
				messageAttendu.append(alimenterJusquaLettre(i, rules, messageAttendu));
			} else {
				messageAtt = messageAttendu.toString();
				messageAtt.replaceAll(String.valueOf(i), rules.get(i).replaceAll("\"", ""));
				messageAttendu = new StringBuilder(messageAtt);
			}
		}
		while (messageAtt.matches(".*\\d.*")) {
			messageAtt = construireSuiteMessageAttendu(messageAttendu, rules);
		}
		System.out.println(messageAttendu);
		return null;
	}

	private static String construireSuiteMessageAttendu(StringBuilder messageAttendu, HashMap<Integer, String> rules) {
		System.out.println(messageAttendu);
		return null;
	}

	private static Object alimenterJusquaLettre(int i, HashMap<Integer, String> rules, StringBuilder messageAttendu) {

		String messageAtt = "";
		if (!rules.get(i).contains("|")) {
			System.out.println("i " + i);
			messageAtt = messageAttendu.toString();
			messageAtt.replaceAll(i + " ", " " + rules.get(i) + " ");
			messageAttendu = new StringBuilder(messageAtt);
		} else {
			String rule = rules.get(i);
			rule.replaceAll("|", ")|(");
			messageAtt = messageAttendu.toString();
			messageAtt.replaceAll(" " + i + " ", " (" + rule + ") ");
			messageAttendu = new StringBuilder(messageAtt);
		}
		System.out.println("fin alimJ :" + messageAttendu);
		return messageAttendu.toString();
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
					rules.put(Integer.parseInt(line.split(":")[0]), line.split(":")[1]);
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rules;
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
}
