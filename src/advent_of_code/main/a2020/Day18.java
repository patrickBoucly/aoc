package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day18 {
	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));

		// System.out.println("solve181 :" + solve181("C:\\AOC\\input18",1));
		System.out.println("solve181 :" + solve181("C:\\AOC\\input18", 2));
	}

	private static String solve181(String string, int partie) {
		List<String> input = getListStringUnParLigne(string);
		List<String> res = new ArrayList<String>();
		String resLigne = "";
		for (String s2 : input) {
			System.out.println("ligne :" + s2);

			resLigne = (resoudreUneLigne2(s2, partie));

			System.out.println("resligne :" + resLigne);
			res.add(resLigne);

		}
		BigInteger tot = new BigInteger("0");
		System.out.println("#########");
		for (String s : res) {
			System.out.println(s);
			tot = tot.add(new BigInteger(s));
		}
		System.out.println(tot);
		return String.valueOf(tot);
	}

	private static String resoudreUneLigne(String s2, int partie) {

		String lastP = "";

		while (s2.contains("(")) {
			int posParenthesOuvrante = -1;
			boolean casTraite = false;
			int j = 0;
			while (!casTraite && j < s2.length()) {
				if (s2.substring(j, j + 1).equals("(") && lastP.equals("")) {
					lastP = "(";
					posParenthesOuvrante = j;
				} else if (s2.substring(j, j + 1).equals("(")) {
					posParenthesOuvrante = j;
				} else if (s2.substring(j, j + 1).equals(")") && posParenthesOuvrante != -1) {
					int posParenthesFermante = j;

					String substring = s2.substring(posParenthesOuvrante + 1, posParenthesFermante);
					String a = s2.substring(0, posParenthesOuvrante);

					String b = "";
					if (partie == 1) {
						b = evaluerDansUneParenthese(substring);

					} else {
						b = evaluerDansUneParenthese2(substring);
					}
					// System.out.println("b"+b);
					String c = s2.substring(posParenthesFermante + 1);
					// System.out.println("c"+c);
					s2 = a + b + c;
					System.out.println(s2);
					// System.out.println("s2 :" + s2);
					casTraite = true;
				}
				j++;
			}
		}
		System.out.println(s2);
		if (partie == 1) {
			return evaluerDansUneParenthese(s2);
		} else {
			return evaluerDansUneParenthese2(s2);
		}
	}

	private static String resoudreUneLigne2(String s2, int partie) {

		String lastP = "";

		while (s2.contains("(")) {
			int posParenthesOuvrante = -1;
			boolean casTraite = false;
			int j = 0;
			while (!casTraite && j < s2.length()) {
				if (s2.substring(j, j + 1).equals("(") && lastP.equals("")) {
					lastP = "(";
					posParenthesOuvrante = j;
				} else if (s2.substring(j, j + 1).equals("(")) {
					posParenthesOuvrante = j;
				} else if (s2.substring(j, j + 1).equals(")") && posParenthesOuvrante != -1) {
					int posParenthesFermante = j;

					String substring = s2.substring(posParenthesOuvrante + 1, posParenthesFermante);
					String a = s2.substring(0, posParenthesOuvrante);

					String b = "";
					if (partie == 1) {
						b = evaluerDansUneParenthese(substring);
					} else {
						b = evaluerDansUneParenthese2(substring);
					}
					String c = s2.substring(posParenthesFermante + 1);
					s2 = a + b + c;
					System.out.println(s2);
					casTraite = true;
				}
				j++;
			}
		}
System.out.println("presque fini");
		if (partie == 1) {
			return evaluerDansUneParenthese(s2);
		} else {
			System.out.println("!!!!!!!!!!!" + s2);
			return evaluerDansUneParenthese2(s2);
		}
	}

	private static String evaluerDansUneParenthese2(String dansParenthese) {
		System.out.println("ICI" + dansParenthese);
		while (compteOperateurPlus(dansParenthese) > 0) {

			dansParenthese = traiter1erPlus(dansParenthese);
			System.out.println(dansParenthese);
		}
		while (compteOperateur(dansParenthese) > 0) {
			dansParenthese = traiter1erFois(dansParenthese);
			System.out.println(dansParenthese);
		}

		return dansParenthese;
	}

	private static String traiter1erPlus(String dansParenthese) {
		int debC1 = 0;
		int finC2 = 0;
		int posPlus = 0;
		BigInteger chiffre1;
		BigInteger chiffre2;
System.out.println("traiter1erPlus");
System.out.println(dansParenthese);
		for (int j = 0; j < dansParenthese.length(); j++) {
			if (dansParenthese.substring(j, j + 1).equals("+") && posPlus == 0) {
				posPlus = j;
			}
		}
		for (int j = 0; j < posPlus; j++) {
			if (dansParenthese.substring(j, j + 1).equals("*")) {
				debC1 = j + 1;
			}
		}

		if (compteOperateur(dansParenthese.substring(posPlus + 1)) == 0) {
			finC2 = -1;

		} else {

			boolean fini = false;
			for (int j = posPlus + 1; j < dansParenthese.length(); j++) {
System.out.println(dansParenthese.substring(j, j + 1));
				if ((dansParenthese.substring(j, j + 1).equals("*") || dansParenthese.substring(j, j + 1).equals("+"))
						&& !fini) {
					System.out.println(posPlus+" rentré!"+j);
					finC2 = j - 1;
					fini = true;
				}
			}

		}
		System.out.println("finC2 " + finC2);
        System.out.println("traiterPlus :"+dansParenthese+ "d "+ debC1+"pos "+ posPlus+" fin "+ finC2);
		dansParenthese = traiterPlus(dansParenthese, debC1, posPlus, finC2);
		return dansParenthese;
	}

	private static String traiterPlus(String dansParenthese, int debC1, int posPlus, int finC2) {
		BigInteger c1;
		BigInteger c2;
		String a = "";
		String b;
		String c = "";
		if (debC1 != 0) {
			a = dansParenthese.substring(0, debC1);
		}
		
		System.out.println(dansParenthese);
		System.out.println("adeb " + debC1 + " posPlus " + posPlus);
		c1 = new BigInteger(dansParenthese.substring(debC1, posPlus));
		if (finC2 != -1) {
			System.out.println(dansParenthese);
			System.out.println(posPlus + " " + finC2);
			c2 = new BigInteger(dansParenthese.substring(posPlus+1, finC2+1));
			c = dansParenthese.substring(finC2+1);
		} else {
			c2 = new BigInteger(dansParenthese.substring(posPlus+1));
		}
		b = String.valueOf(c2.add(c1));
System.out.println("a "+a);
System.out.println("b "+b);
System.out.println("c "+c);
		return a + b + c;
	}

	private static String traiter1erFois(String dansParenthese) {
		int debC1 = 0;
		int finC2 = 0;
		int posPlus = 0;
		BigInteger chiffre1;
		BigInteger chiffre2;
		boolean firstFoisTrouve = false;
		for (int j = 0; j < dansParenthese.length(); j++) {
			if (dansParenthese.substring(j, j + 1).equals("*") && !firstFoisTrouve) {
				posPlus = j;
				firstFoisTrouve = true;
			}
		}

		if (compteOperateur(dansParenthese.substring(posPlus + 1)) == 0) {
			finC2 = -1;

		} else {

			boolean fini = false;
			for (int j = posPlus + 1; j < dansParenthese.length(); j++) {

				if ((dansParenthese.substring(j, j + 1).equals("*") || dansParenthese.substring(j, j + 1).equals("+"))
						&& !fini) {
					finC2 = j - 1;
					fini = true;
				}
			}

		}
		System.out.println(dansParenthese);
		System.out.println("deb " + debC1 + " op " + posPlus + " fin " + finC2);
		dansParenthese = traiterFois(dansParenthese, debC1, posPlus, finC2);
		return dansParenthese;

	}

	private static String traiterFois(String dansParenthese, int debC1, int posPlus, int finC2) {
		BigInteger c1;
		BigInteger c2;
		String a = "";
		String b;
		String c = "";
		if (debC1 != 0) {
			a = dansParenthese.substring(0, debC1);
		}
		System.out.println(dansParenthese);
		System.out.println("deb " + debC1 + " op " + posPlus + " finc2 " + finC2);
		c1 = new BigInteger(dansParenthese.substring(debC1, posPlus));
		if (finC2 != -1) {
			c2 = new BigInteger(dansParenthese.substring(posPlus + 1, finC2 + 1));
			c = dansParenthese.substring(finC2 + 1);
		} else {
			c2 = new BigInteger(dansParenthese.substring(posPlus + 1));
		}
		b = String.valueOf(c2.multiply(c1));

		return a + b + c;
	}

	private static int getPositionDuFoisApresPremierPlus(String dansParenthese) {
		int res = 0;
		for (int j = 0; j < dansParenthese.length(); j++) {
			if (dansParenthese.subSequence(j, j + 1).equals("+") && res == 0) {
				res = j;
			} else if ((dansParenthese.subSequence(j, j + 1).equals("*")
					|| dansParenthese.subSequence(j, j + 1).equals("+")) && res != 0) {
				return j;
			}
		}
		return 0;
	}

	private static int getPositionDuFoisAvantPremierPlus(String dansParenthese) {
		int res = 0;
		for (int j = 0; j < dansParenthese.length(); j++) {
			if (dansParenthese.subSequence(j, j + 1).equals("*")) {
				res = j;
			} else if (dansParenthese.subSequence(j, j + 1).equals("+")) {
				return res;
			}
		}
		return 0;
	}

	private static boolean foisAvantPlus(String dansParenthese) {
		boolean res = true;
		for (int j = 0; j < dansParenthese.length(); j++) {
			if (dansParenthese.subSequence(j, j + 1).equals("*")) {
				return true;
			} else if (dansParenthese.subSequence(j, j + 1).equals("+")) {
				return false;
			}
		}
		return res;
	}

	private static int compteOperateurPlus(String dansParenthese) {
		int res = 0;
		for (int i = 0; i < dansParenthese.length() - 1; i++) {
			if (dansParenthese.substring(i, i + 1).contains("+")) {
				res++;
			}
		}
		return res;

	}

	private static String evaluerDansUneParenthese(String dansParenthese) {

		while (compteOperateur(dansParenthese) > 1) {

			int posOperateur = 0;
			int posOperateurSuivant = 0;
			String operateur = "";
			boolean traite = false;
			int i = 0;
			while (!traite && i < dansParenthese.length()) {
				while (posOperateurSuivant == 0) {

					if (dansParenthese.substring(i, i + 1).equals("+")) {
						if (posOperateur == 0) {
							operateur = "+";
							posOperateur = i;
						} else {
							posOperateurSuivant = i;
							traite = true;
						}
					} else if (dansParenthese.substring(i, i + 1).equals("*")) {
						if (posOperateur == 0) {
							posOperateur = i;
							operateur = "*";
						} else {
							posOperateurSuivant = i;
							traite = true;
						}

					}
					i++;
				}
			}
			BigInteger chiffre1 = new BigInteger(dansParenthese.substring(0, posOperateur));
			BigInteger chiffre2 = new BigInteger(dansParenthese.substring(posOperateur + 1, posOperateurSuivant));
			if (operateur.contains("+")) {
				dansParenthese = String.valueOf(chiffre1.add(chiffre2)) + dansParenthese.substring(posOperateurSuivant);
			} else {
				dansParenthese = String.valueOf(chiffre1.multiply(chiffre2))
						+ dansParenthese.substring(posOperateurSuivant);
			}
		}

		int posOperateur = 0;
		String op = "";
		int j = 0;
		while (posOperateur == 0) {
			if (dansParenthese.substring(j, j + 1).equals("+")) {
				if (posOperateur == 0) {
					op = "+";
					posOperateur = j;
				}
			} else if (dansParenthese.substring(j, j + 1).equals("*")) {
				if (posOperateur == 0) {
					posOperateur = j;
					op = "*";
				}
			}
			j++;
		}

		BigInteger chiffre1 = new BigInteger(dansParenthese.substring(0, posOperateur));
		BigInteger chiffre2 = new BigInteger(dansParenthese.substring(posOperateur + 1));
		BigInteger chiffre3;
		if (op.contains("+")) {
			chiffre3 = chiffre1.add(chiffre2);
			dansParenthese = chiffre3.toString();

		} else {
			chiffre3 = chiffre1.multiply(chiffre2);
			dansParenthese = chiffre3.toString();
		}

		return dansParenthese;
	}

	private static int compteOperateur(String dansParenthese) {
		int res = 0;
		for (int i = 0; i < dansParenthese.length() - 1; i++) {
			if (dansParenthese.substring(i, i + 1).contains("+") || dansParenthese.substring(i, i + 1).contains("*")) {
				res++;
			}
		}
		return res;
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
