package advent_of_code.main.a2020;

import java.util.HashMap;

public class Sol {
	HashMap<String, Hexa> planche = new HashMap<>();

	public HashMap<String, Hexa> getPlanche() {
		return planche;
	}

	public void setPlanche(HashMap<String, Hexa> planche) {
		this.planche = planche;
	}

	public Sol(HashMap<String, Hexa> planche) {
		super();
		this.planche = planche;

	}

	private void initialise() {
		for (int i = -100; i <= 100; i++) {
			for (int j = -100; j <= 100; j++) {
				this.planche.put(i + "_" + j, new Hexa(i, j, false));
			}
		}
		// System.out.println("initialisé");
	}

	public void modifierPlanche(String instructions) {
		int posInst = 0;
		int posI = 0;
		int posJ = 0;
		String lastIns = "";
		while (posInst < instructions.length()) {
			if (instructions.substring(posInst, posInst + 1).equals("e")) {
				posI++;
				posInst++;
				lastIns = "e";
				// // System.out.println("position :"+posI+" , "+posJ);
			} else if (instructions.substring(posInst, posInst + 1).equals("w")) {
				posI--;
				posInst++;
				lastIns = "w";
				// // System.out.println("position :"+posI+" , "+posJ);
			} else if (instructions.substring(posInst, posInst + 1).equals("n")) {
				if (instructions.substring(posInst + 1, posInst + 2).equals("e")) {

					posI++;
					posJ--;
					lastIns = "ne";
					// // System.out.println("position :"+posI+" , "+posJ);
				} else {

					posJ--;
					lastIns = "nw";
					// // System.out.println("position :"+posI+" , "+posJ);
				}
				posInst++;
				posInst++;
			} else if (instructions.substring(posInst, posInst + 1).equals("s")) {
				if (instructions.substring(posInst + 1, posInst + 2).equals("e")) {

					posJ++;
					lastIns = "se";
					// // System.out.println("position :"+posI+" , "+posJ);
				} else {
					lastIns = "sw";
					posI--;
					posJ++;
					// // System.out.println("position :"+posI+" , "+posJ);
				}
				posInst++;
				posInst++;
			}
		}

		if (this.planche.get(String.valueOf(posI) + "_" + String.valueOf(posJ)) != null) {
			boolean etat = this.planche.get(String.valueOf(posI) + "_" + String.valueOf(posJ)).isReturn;
			this.planche.put(String.valueOf(posI) + "_" + String.valueOf(posJ), new Hexa(posI, posJ, !etat));
		} else {
			this.planche.put(String.valueOf(posI) + "_" + String.valueOf(posJ), new Hexa(posI, posJ, true));

		}
	}

	public Integer getNbVoisinsNoir(HashMap<String, Hexa> planche, int i, int j, String couleur) {
		Integer res = 0;

		// System.out.println("####");
		if (planche.containsKey((i) + "_" + (j + 1))) {
			if (planche.get((i) + "_" + (j + 1)).isReturn) {
				// System.out.println("voisin : " + i + "_" + (j + 1));
				res++;
			}
		}

		if (planche.containsKey((i - 1) + "_" + (j + 1))) {
			if (planche.get((i - 1) + "_" + (j + 1)).isReturn) {
				// System.out.println("voisin : " + (i - 1) + "_" + (j + 1));
				res++;
			}
		}

		if (planche.containsKey(i + "_" + (j - 1))) {
			if (planche.get(i + "_" + (j - 1)).isReturn) {
				// System.out.println("voisin : " + i + "_" + (j - 1));
				res++;
			}
		}
		if (planche.containsKey((i - 1) + "_" + j)) {
			if (planche.get((i - 1) + "_" + j).isReturn) {
				// System.out.println("voisin : " + (i - 1) + "_" + j);
				res++;
			}
		}
		if (planche.containsKey((i + 1) + "_" + (j - 1))) {
			if (planche.get((i + 1) + "_" + (j - 1)).isReturn) {

				// System.out.println("voisin : " + (i + 1) + "_" + (j - 1));
				res++;
			}
		}
		if (planche.containsKey((i + 1) + "_" + j)) {
			if (planche.get((i + 1) + "_" + j).isReturn) {
				// System.out.println("voisin : " + (i + 1) + "_" + j);
				res++;
			}
		}
		// System.out.println("hexa " + couleur + " " + i + " _" + j + " à " + res + " voisin");

		return res;
	}

	public Sol passerUnJour(Sol sol) {
		HashMap<String, Hexa> copiePlanche = new HashMap<>();
		for (String s : sol.planche.keySet()) {
			copiePlanche.put(s,
					new Hexa(sol.planche.get(s).posI, sol.planche.get(s).posJ, sol.planche.get(s).isReturn));
		}
		Integer IMAX = getIndiceMax(copiePlanche);
		for (int i = -(IMAX + 10); i < IMAX + 10; i++) {
			for (int j = -(IMAX + 10); j < IMAX + 10; j++) {
				// // System.out.println("position "+i+" "+j);
				if (copiePlanche.containsKey(i + "_" + j)) {
					if (((getNbVoisinsNoir(copiePlanche, i, j, "noir") == 0
							|| (getNbVoisinsNoir(copiePlanche, i, j, "noir") > 2))
							&& copiePlanche.get(i + "_" + j).isReturn)) {
						sol.planche.put(i + "_" + j, new Hexa(i, j, false));
						// System.out.println("contient " + sol.planche.containsKey(i + "_" + j));
						// System.out.println(i + " " + j + " noir passe en blanc");
					} else if (!copiePlanche.get(i + "_" + j).isReturn
							&& (getNbVoisinsNoir(copiePlanche, i, j, "blanc") == 2)) {
						sol.planche.put(i + "_" + j, new Hexa(i, j, true));

						// System.out.println(i + "_" + j + " blanc passe en noir");
					}
				}
				if (!copiePlanche.containsKey(i + "_" + j) && (getNbVoisinsNoir(copiePlanche, i, j, "blanc") == 2)) {
					sol.planche.put(i + "_" + j, new Hexa(i, j, true));

					// System.out.println(i + "_" + j + " blanc passe en noir");
				}

				// // System.out.println("décompte :
				// +"+sol.planche.values().stream().filter(r->r.isReturn).count());
			}
		}
		return sol;
	}

	private Integer getIndiceMax(HashMap<String, Hexa> planche2) {
		Integer res = 0;
		for (String s : planche2.keySet()) {
			Integer i = Integer.parseInt(s.split("_")[0]);
			Integer j = Integer.parseInt(s.split("_")[0]);

			if (i < 0 && -i > res) {
				res = -i;
			}
			if (i > 0 && i > res) {
				res = i;
			}
			if (j < 0 && -j > res) {
				res = -j;
			}
			if (j > 0 && j > res) {
				res = j;
			}
		}
		// // System.out.println("indiceMAx" +res);
		return res;
	}
}
