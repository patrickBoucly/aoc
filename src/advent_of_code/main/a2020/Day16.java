package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day16 {

	public static void main(String[] args) {
		// System.out.println("solve161 :" + solve161("input16"));
		System.out.println("solve162 :" + solve162("input16"));
		// System.out.println("solve162 :" + solve162("C:\\AOC\\input16"));
		// System.out.println("solve102 :" + solve162("input16"));

	}

	private static double solve162(String nomFic) {
		List<Rule> rules = getRules(nomFic);
		List<Ticket> autresTickets = getTickets(nomFic);
		Ticket monTicket = getMonTickets(nomFic);
		List<Ticket> ticketsValide = new ArrayList<>();
		HashMap<String, List<Integer>> reglePositionOK = new HashMap<>();
		int id = 0;
		for (Ticket t : autresTickets) {
			if (analyserTicket(t, rules) == 0) {
				id++;
				ticketsValide.add(new Ticket(id, t.getChiffres()));

			}
		}
		Integer nbTvalide = ticketsValide.size();
		List<Rule> ruleOrdonne = new ArrayList<>();
		for (Rule r : rules) {
			ruleOrdonne.add(r);
		}

		for (Rule r : rules) {

			HashMap<Integer, List<Integer>> positionRespectantLaRegleRParTicket = new HashMap<>();
			for (Ticket t : ticketsValide) {
				List<Integer> positionRespectantLaRegleRduTicketT = new ArrayList<Integer>();
				Integer pos = -1;
				for (Integer i : t.chiffres) {
					pos++;
					if (respectLaRegle(i, r) == 0) {
						positionRespectantLaRegleRduTicketT.add(pos);
					}
				}
				if (positionRespectantLaRegleRduTicketT != null) {
					positionRespectantLaRegleRParTicket.put(t.id, positionRespectantLaRegleRduTicketT);
				}
				System.out.println("m" + positionRespectantLaRegleRParTicket.get(t.id));
			}
			List<Integer> positionsCandidate = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
					17, 18, 19, 20);
			List<Integer> positionsOK = new ArrayList<>();

			for (Integer i : positionsCandidate) {
				if (positionRespectantLaRegleRParTicket.values().stream().allMatch(l -> l.contains(i))) {

					positionsOK.add(i);
				}
			}
			System.out.println("regle " + r.nom);

			reglePositionOK.put(r.nom, positionsOK);

		}
		HashMap<Integer,String> positionNom =new HashMap<>();
		List<Integer> dejaPris =new ArrayList<>();
		List<Integer> positionsCandidate = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17, 18, 19, 20);
		for(int t=1;t<20;t++) {
			for (String nomR : reglePositionOK.keySet()) {
				if (reglePositionOK.get(nomR).size() == 1) {
					positionNom.put(reglePositionOK.get(nomR).get(0), nomR);
					dejaPris.add(reglePositionOK.get(nomR).get(0));
					} else if (reglePositionOK.get(nomR).size() == t) { 
					Integer monInt=-1;
					for(Integer i:reglePositionOK.get(nomR)) {
						if(!dejaPris.contains(i)) {
							monInt=i;
						}
					}
					positionNom.put(monInt, nomR);
					dejaPris.add(monInt);
					
				}
				if (reglePositionOK.get(nomR).size() == 0) {
					positionNom.put(20, nomR);
					
				} 
			}
		}
		for(Integer s:positionNom.keySet()) {
			System.out.println(s+" "+positionNom.get(s));
		}
			
		
		BigInteger monRes = BigInteger.valueOf(1);
		
		int nbVal = 0;
		for (int j = 0; j < ruleOrdonne.size(); j++) {
			if (nbVal < 6) {
				if (positionNom.get(j).contains("departure")) {
					nbVal++;
					System.out.println(j+1);
					System.out.println(monTicket.chiffres.get(j ));
					monRes = monRes.multiply(BigInteger.valueOf(monTicket.chiffres.get(j)));
					System.out.println("monRes"+monRes);
				}
			}
		}
		
		// too low 104124416
		// too low 17907120
		return 0;
	}

	private static int solve161(String nomFic) {
		List<Rule> rules = getRules(nomFic);
		List<Ticket> autresTickets = getTickets(nomFic);
		Ticket monTicket = getMonTickets(nomFic);
		/*
		 * for(Rule r:rules) { for(Plage p:r.plages) {
		 * System.out.println(r.nom+" min:"+p.min+" max "+p.max); } }
		 * 
		 * System.out.println("monTicket"); for(Integer i:monTicket) {
		 * System.out.println(i); } System.out.println("autresTickets"); for(Integer
		 * i:autresTickets) { System.out.println(i); }
		 */
		int res = 0;
		for (Ticket t : autresTickets) {
			System.out.println(res);
			res += analyserTicket(t, rules);
		}
		return res;
	}

	private static int analyserTicket(Ticket t, List<Rule> rules) {
		int res = 0;

		for (Integer i : t.chiffres) {
			res += analyserUnChiffre(i, rules);
		}
		return res;
	}

	private static int analyserUnChiffre(Integer i, List<Rule> rules) {
		int res = 0;
		boolean valide = false;
		for (Rule r : rules) {
			if (respectLaRegle(i, r) == 0) {
				valide = true;
			}
		}
		if (!valide) {
			res = i;
		}
		return res;
	}

	private static int respectLaRegle(Integer i, Rule r) {
		int res = 0;
		boolean dansUnePlage = false;
		boolean valide = false;
		for (Plage p : r.plages) {
			dansUnePlage = p.contient(i);
			if (dansUnePlage) {
				valide = true;
			}
		}
		if (!valide) {
			res = i;
		}

		return res;
	}

	static class Rule {
		String nom;
		List<Plage> plages;

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public List<Plage> getPlages() {
			return plages;
		}

		public void setPlages(List<Plage> plages) {
			this.plages = plages;
		}

		public Rule(String nom, List<Plage> plages) {
			super();
			this.nom = nom;
			this.plages = plages;
		}

	}

	static class Ticket {
		ArrayList<Integer> chiffres;
		int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Ticket(ArrayList<Integer> chiffres) {
			super();
			this.chiffres = chiffres;
		}

		public Ticket(int id, ArrayList<Integer> chiffres) {
			super();
			this.id = id;
			this.chiffres = chiffres;
		}

		public ArrayList<Integer> getChiffres() {
			return chiffres;
		}

		public void setChiffres(ArrayList<Integer> chiffres) {
			this.chiffres = chiffres;
		}

	}

	static class Plage {
		Integer min;
		Integer max;

		public Integer getMin() {
			return min;
		}

		public void setMin(Integer min) {
			this.min = min;
		}

		public Integer getMax() {
			return max;
		}

		public void setMax(Integer max) {
			this.max = max;
		}

		public Plage(Integer min, Integer max) {
			super();
			this.min = min;
			this.max = max;
		}

		public boolean contient(Integer chiffre) {
			return ((chiffre <= max) && (chiffre >= min));
		}
	}

	private static List<Rule> getRules(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;

		ArrayList<Rule> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			boolean continuer = true;
			try {
				while (bufferReader.ready() && continuer) {
					List<Plage> mesPlages = new ArrayList<Plage>();
					String line = bufferReader.readLine();
					if (!(line.length() == 0)) {
						String nom = line.split(":")[0];
						String textPlages = line.split(":")[1].replaceAll("\\s+", "");
						String[] plages = textPlages.split("or");
						for (String s : plages) {
							mesPlages.add(
									new Plage(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1])));

						}
						res.add(new Rule(nom, mesPlages));
					} else {
						continuer = false;
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

	private static List<Ticket> getTickets(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;

		ArrayList<Ticket> res = new ArrayList<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			boolean commencer = false;
			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					if (commencer) {
						ArrayList<Integer> chiffres = new ArrayList<Integer>();
						String[] mesChiffres = line.split(",");
						for (String s : mesChiffres) {
							chiffres.add(Integer.parseInt(s));
						}
						res.add(new Ticket(chiffres));
					}
					if (line.contains("nearby")) {
						commencer = true;
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

	private static Ticket getMonTickets(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;

		Ticket res = null;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			boolean commencer = false;
			boolean arreter = false;
			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine();
					if (commencer && !arreter) {
						String[] mesChiffres = line.split(",");
						ArrayList<Integer> chiffres = new ArrayList<Integer>();

						for (String s : mesChiffres) {
							chiffres.add(Integer.parseInt(s));
						}
						arreter = true;
						res = new Ticket(chiffres);
					}
					if (line.contains("your ticket")) {
						commencer = true;
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
