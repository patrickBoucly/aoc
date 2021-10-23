package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Emplacement {
	int ligne;
	int colonne;
	String etat;

	public Emplacement(int ligne, int colonne, String etat) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
		this.etat = etat;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getNbVoisins(List<Emplacement> liste) {
		int res = 0;
		List<Emplacement> voisins = new ArrayList<>();
		for (Emplacement e : liste) {
			if (e.getColonne() <= this.colonne + 1 && e.colonne >= this.colonne - 1 && e.ligne <= this.ligne + 1
					&& e.ligne >= this.ligne - 1) {
				voisins.add(e);
			}
		}
		voisins.remove(this);
		return (int) voisins.stream().filter(x -> x.etat.equals("#")).count();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colonne;
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
		result = prime * result + ligne;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emplacement other = (Emplacement) obj;
		if (colonne != other.colonne)
			return false;
		if (etat == null) {
			if (other.etat != null)
				return false;
		} else if (!etat.equals(other.etat))
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}

	@Override
	public String toString() {

		return "(" + this.getLigne() + "," + this.getColonne() + "):" + this.getEtat();
	}

	public int getNbVisibles(List<Emplacement> emplacementsInitiaux) {
		int nbLigne = 91;
		int nbCol = 95;

		if (emplacementsInitiaux.size() == 100) {
			nbLigne = 10;
			nbCol = 10;
		}
		String[][] tab = new String[nbLigne][nbCol];
		for (Emplacement e : emplacementsInitiaux) {

			tab[e.ligne][e.colonne] = e.etat;
		}

		List<String> direction = Arrays.asList("N", "NE", "E", "SE", "S", "SO", "O", "NO");
		int res = 0;
		for (String dir : direction) {
			if (dir.equals("N")) {
				res += libreN(tab);

			} else if (dir.equals("NE")) {
				res += libreNE(tab, nbCol);
			} else if (dir.equals("E")) {
				res += libreE(tab, nbCol);

			} else if (dir.equals("SE")) {
				res += libreSE(tab, nbCol, nbLigne);

			} else if (dir.equals("S")) {
				res += libreS(tab, nbLigne);

			} else if (dir.equals("SO")) {
				res += libreSO(tab, nbLigne);

			} else if (dir.equals("O")) {
				res += libreO(tab, nbCol);

			} else if (dir.equals("NO")) {
				res += libreNO(tab);

			}
		}
		return res;
	}

	private int libreNO(String[][] tab) {
		int res = 0;
		if (!(this.getLigne() == 0 || this.getColonne() == 0)) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getLigne() - i >= 0 && this.getColonne() - i >= 0 && !reponseObtenue) {
				String etat = tab[this.ligne - i][this.colonne - i];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					res++;
					System.out.println("NO");
				}
			}
		}
		return res;
	}

	private int libreO(String[][] tab, int nbCol) {
		int res = 0;
		if (this.getColonne() != 0) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getColonne() - i >= 0 && !reponseObtenue) {
				String etat = tab[this.ligne][this.colonne - i];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					res++;
					System.out.println("O");
				}
			}
		}
		return res;
	}

	private int libreSO(String[][] tab, int nbLigne) {
		int res = 0;
		if (!(this.getColonne() == 0 || this.getLigne() == nbLigne)) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getColonne() - i >= 0 && this.getLigne() + i < nbLigne && !reponseObtenue) {
				String etat = tab[this.ligne + i][this.colonne - i];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println("SO");
					res++;
				}
			}
		}
		return res;
	}

	private int libreS(String[][] tab, int nbLigne) {
		int res = 0;
		if (!(this.getLigne() == nbLigne)) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getLigne() + i < nbLigne && !reponseObtenue) {
				String etat = tab[this.ligne + i][this.colonne];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println("S");
					res++;
				}
			}
		}
		return res;
	}

	private int libreSE(String[][] tab, int nbCol, int nbLigne) {
		int res = 0;
		if (!(this.getLigne() == nbLigne || this.getColonne() == nbCol)) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getLigne() + i < nbLigne && this.getColonne() + i < nbCol && !reponseObtenue) {
				String etat = tab[this.ligne + i][this.colonne + i];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println("SE");
					res++;
				}
			}
		}
		return res;
	}

	private int libreE(String[][] tab, int nbCol) {
		int res = 0;
		if (this.getColonne() != nbCol) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getColonne() + i < nbCol && !reponseObtenue) {

				String etat = tab[this.ligne][this.colonne + i];

				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println("E");
					res++;
				}
			}
		}
		return res;
	}

	private int libreNE(String[][] tab, int nbCol) {
		int res = 0;
		if (!(this.getLigne() == 0 || this.getColonne() == nbCol)) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getLigne() - i >= 0 && this.getColonne() + i < nbCol && !reponseObtenue) {

				String etat = tab[this.ligne - i][this.colonne + i];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println(etat);
					System.out.println(this.ligne - i);
					System.out.println(this.colonne + i);
					System.out.println("NE");
					res++;
				}
			}
		}
		return res;
	}

	private int libreN(String[][] tab) {
		int res = 0;
		if (this.getLigne() != 0) {
			int i = 1;
			boolean reponseObtenue = false;

			while (this.getLigne() - i >= 0 && !reponseObtenue) {
				String etat = tab[this.ligne - i][this.colonne];
				if (etat.equals(".")) {
					i++;
				} else if (etat.equals("L")) {
					reponseObtenue = true;
				} else {
					reponseObtenue = true;
					System.out.println("N");
					res++;
				}

			}
		}
		return res;
	}
}
