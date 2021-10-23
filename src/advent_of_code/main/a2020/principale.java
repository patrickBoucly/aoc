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

public class principale {
	public static void main(String[] args) {
		System.out.println("Solution J6E1 :" + solve62());
//		System.out.println("Solution J1E1 :" + solve31(3,1));
//		System.out.println("Solution J1E1 :" + solve31(1,1));
//		System.out.println("Solution J1E1 :" + solve31(5,1));
//		System.out.println("Solution J1E1 :" + solve31(7,1));
//		System.out.println("Solution J1E1 :" + solve31(1, 2));
//		System.out.println();
//System.out.println("Solution J1E2 :"+solve12());
//System.out.println("Solution J2E1 :"+solve21());
//System.out.println("Solution J12E2 :"+solve22());
		// System.out.println("Solution J10_2019 :" + solve2019_10_1());

	}
	private static int solve62() {
		Path path = Paths.get("C:\\AOC\\input6");
		FileReader fileReader;
		int res = 0;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					boolean groupeEnCours = true;
					List<String> lettreGroupe = new ArrayList<>();
					int nbPersGroupe=0;
					while (groupeEnCours) {
						String line = bufferReader.readLine();
						if (line != null) {
							if (line.length() == 0) {
								for(String lettre:lettreGroupe.stream().distinct().collect(Collectors.toList())) {
									if(lettreGroupe.stream().filter(l->l.equals(lettre)).count() == nbPersGroupe) {
										res++;
									}	
								}
								groupeEnCours = false;
							} else {
								nbPersGroupe++;
								for (int i = 0; i < line.length(); i++) {
									lettreGroupe.add(line.substring(i, i + 1));
								}
							}
						} else {
							groupeEnCours = false;
						}
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
	private static int solve61() {
		Path path = Paths.get("C:\\AOC\\input6");
		FileReader fileReader;
		int res = 0;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					boolean groupeEnCours = true;
					Set<String> lettreGroupe = new HashSet<>();
					while (groupeEnCours) {
						String line = bufferReader.readLine();
						if (line != null) {
							if (line.length() == 0) {
								res = res + lettreGroupe.size();
								groupeEnCours = false;
							} else {
								for (int i = 0; i < line.length(); i++) {

									lettreGroupe.add(line.substring(i, i + 1));

								}
							}
						} else {
							groupeEnCours = false;
						}
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

	private static String solve52() {
		Path path = Paths.get("C:\\AOC\\input5");
		FileReader fileReader;
		List<Seat> listeSeat = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					listeSeat.add(new Seat(bufferReader.readLine()));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Integer> listSeatID = new ArrayList<>();
		for (Seat s : listeSeat) {
			listSeatID.add(s.getSeatID());
		}
		listSeatID.stream().sorted().forEach(s -> System.out.println(s));
		List<Integer> listSeatID2 = new ArrayList<>();
		for (int i = 70; i < 939; i++) {
			if (!listSeatID.contains(i)) {
				System.out.println(i);
			}
		}
		return "";
	}

	private static int solve51() {
		Path path = Paths.get("C:\\AOC\\input5");
		FileReader fileReader;
		List<Seat> listeSeat = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					listeSeat.add(new Seat(bufferReader.readLine()));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int res = 0;
		for (Seat s : listeSeat) {
			if (s.getSeatID() > res) {
				res = s.getSeatID();
			}
		}
		return res;
	}

	private static int solve41() {
		Path path = Paths.get("C:\\AOC\\input4");
		FileReader fileReader;
		List<Passport> listePassport = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					boolean passportEnCours = true;
					Map<String, String> informations = new HashMap<>();
					while (passportEnCours) {
						String line = bufferReader.readLine();
						if (line != null) {
							if (line.length() == 0) {
								listePassport.add(new Passport(informations));
								passportEnCours = false;
							} else {
								String[] cleValeur = line.split(" ");
								for (String s : cleValeur) {
									informations.put(s.split(":")[0], s.split(":")[1]);
								}
							}
						} else {
							passportEnCours = false;
						}
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
		return (int) listePassport.stream().filter(c -> c.isValid()).count();
	}

	private static int solve31(int avance, int descends) {
		int res = 0;
		Path path = Paths.get("C:\\AOC\\input3");
		FileReader fileReader;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				int y = 0;
				int abs = 0;
				System.out.println(240 * 70 * 68 * 67 * 37);
				while (bufferReader.ready()) {

					if (y % descends == 0) {
						String line = bufferReader.readLine();
						for (int j = 0; j < 20; j++) {
							line = line + line;
						}
						if (line.charAt(abs) == '#') {
							res++;
						}
						abs = abs + avance;

					} else {
						String line = bufferReader.readLine();
					}
					y++;

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

	private static Integer solve2019_10_1() {

		Path path = Paths.get("C:\\AOC\\input_j10_2019");
		FileReader fileReader = null;
		List<Asteroide> asteroides = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferReader = new BufferedReader(fileReader);
		List<Integer> nbAsteroideVisible = new ArrayList<>();

		int y = 0;
		try {
			while (bufferReader.ready()) {
				String line = bufferReader.readLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '#') {
						asteroides.add(new Asteroide(i, y));
					}
				}
				y++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < asteroides.size(); i++) {

			List<Vecteur> vecteursAsteroideI = new ArrayList<>();

			List<Asteroide> autresAsteroides = new ArrayList<>();
			for (Asteroide as : asteroides) {
				autresAsteroides.add(as);
			}
			autresAsteroides.remove(i);

			for (int j = 0; j < autresAsteroides.size(); j++) {

				Vecteur v = new Vecteur(autresAsteroides.get(j).getX() - asteroides.get(i).getX(),
						asteroides.get(i).getY() - autresAsteroides.get(j).getY());

				Vecteur vn = new Vecteur(Math.round(v.getAbscisse() / v.getLongueur() * 1000),
						Math.round(v.getOrdonne() / v.getLongueur() * 1000));
				// System.out.println(vn);
				vecteursAsteroideI.add(vn);
				if (vecteursAsteroideI.stream().distinct().count() == 286)
					System.out.println(" le bon? " + vecteursAsteroideI.stream().distinct().count() + "   "
							+ autresAsteroides.get(j).getX() + "  " + autresAsteroides.get(j).getY());

			}

			nbAsteroideVisible.add((int) vecteursAsteroideI.stream().distinct().count());
		}
		System.out.println(nbAsteroideVisible);
		int res = 0;
		int rang = 0;
		for (Integer i : nbAsteroideVisible) {
			rang++;
			if (i > res) {
				System.out.println(rang);
				res = i;
			}
		}
		// (24,26)

		List<Vecteur> vecteursAsteroideI = new ArrayList<>();

		List<Asteroide> autresAsteroides = new ArrayList<>();
		for (Asteroide as : asteroides) {
			autresAsteroides.add(as);
		}

		return res;
	}

	private static int solve22() {
		int nbValidPWD = 0;
		Path path = Paths.get("C:\\AOC\\input2");
		FileReader fileReader;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					String consigne = line.split(":")[0];
					Consigne c = new Consigne(consigne);
					String pwd = line.split(":")[1].replaceAll("\\s+", "");
					// System.out.println(c.getLettre());
					boolean a = pwd.charAt(c.getMin() - 1) == c.getLettre().charAt(0);
					boolean b = pwd.charAt(c.getMax() - 1) == c.getLettre().charAt(0);
					if ((a && !b) || ((!a && b))) {
						nbValidPWD++;
						System.out.println("la lettre est " + c.getLettre() + ", la regle :[" + c.getMin() + "-"
								+ c.getMax() + "]");
						System.out.println(pwd);
						System.out.println("valide!!");

					} else {
						System.out.println("la lettre est " + c.getLettre() + ", la regle :[" + c.getMin() + "-"
								+ c.getMax() + "]");
						System.out.println(pwd);
						System.out.println("NON valide!!");
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
		return nbValidPWD;
	}

	private static int solve21() {
		int nbValidPWD = 0;
		Path path = Paths.get("C:\\AOC\\input2");
		FileReader fileReader;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					String consigne = line.split(":")[0];
					Consigne c = new Consigne(consigne);
					String pwd = line.split(":")[1];
					// System.out.println(c.getLettre());
					long cpt = pwd.chars().filter(ch -> ch == c.getLettre().charAt(0)).count();
					int cptInt = (int) cpt;
					// System.out.println("apparait "+cpt+" fois");
					if (cptInt <= c.getMax() && cptInt >= c.getMin()) {
						System.out.println(
								"apparait " + cpt + " fois, fourchette :[" + c.getMin() + "-" + c.getMax() + "]");
						System.out.println("valide!!");
						nbValidPWD++;
					} else {
						System.out.println(
								"apparait " + cpt + " fois, fourchette :[" + c.getMin() + "-" + c.getMax() + "]");
						System.out.println("NON valide!!");
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
		return nbValidPWD;
	}

	public static Integer solve12() {
		Integer res = 0;
		for (Integer i : getListIntegerJ1E1()) {
			for (Integer j : getListIntegerJ1E1()) {
				for (Integer k : getListIntegerJ1E1()) {
					if (i + j + k == 2020) {
						return i * j * k;
					}
				}
			}
		}
		return res;
	}

	public static Integer solve11() {
		Integer res = 0;
		for (Integer i : getListIntegerJ1E1()) {
			for (Integer j : getListIntegerJ1E1()) {
				if (i + j == 2020) {
					return i * j;
				}
			}
		}
		return res;
	}

	public static List<Integer> getListIntegerJ1E1() {
		List<Integer> input = new ArrayList<>();
		Path path = Paths.get("C:\\AOC\\input1");
		FileReader fileReader;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				while (bufferReader.ready()) {
					input.add(Integer.parseInt(bufferReader.readLine()));

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}

}
