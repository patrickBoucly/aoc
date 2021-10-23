package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clock {
	List<Integer> cups = new ArrayList<>();
	Integer valActu;
	Integer posActu;

	public Clock(List<Integer> cups) {
		super();
		this.cups = cups;
		this.posActu = 0;
		this.valActu = cups.get(0);
	}

	public List<Integer> getCups() {
		return cups;
	}

	public void setCups(List<Integer> cups) {
		this.cups = cups;
	}

	public Integer getValActu() {
		return valActu;
	}

	public void setValActu(Integer valActu) {
		this.valActu = valActu;
	}

	public Integer getPosActu() {
		return posActu;
	}

	public void setPosActu(Integer posActu) {
		this.posActu = posActu;
	}

	public void afficherRes2(Integer nbMvt) {
		int posUn = 0;
		for (int i = 0; i <= nbMvt; i++) {
			if (cups.get(i) == 1) {
				posUn = i;
				System.out.println(cups.get(posUn + 1));
				System.out.println(cups.get(posUn + 2));
				break;
			}
		}
	}

	public void selectNewPosActu() {
		if (posActu == cups.size() - 1) {
			this.posActu = 0;
		} else {
			this.posActu++;
		}
	}
	/*
	 * public Integer selectNewValActu() { if(posActu==nbElement-1) { this.valActu=
	 * 0; } else { this.valActu++; } }
	 */

	public List<Integer> pickUp() {
		// System.out.println("cups "+cups);
		// System.out.println("posactu "+posActu);
		Integer nbElement = this.cups.size();
		List<Integer> pickedUp = new ArrayList<>();
		while (pickedUp.size() < 3) {
			if (posActu == nbElement - 1) {
				// System.out.println("add get(0)"+cups.get(0));
				pickedUp.add(cups.get(0));
				posActu = 0;
			} else {
				// System.out.println("add cups.get(posActu+1), posActu+1="+cups.get(posActu+1)+
				// " , "+posActu+1);
				pickedUp.add(cups.get(posActu + 1));
				posActu = posActu + 1;
			}
		}
		return pickedUp;
	}

	private Integer getDestination(List<Integer> pickedUp) {
		System.out.println("valActu "+valActu);
		
		Integer valeurCandidate = (valActu - 1);
		boolean trouve = false;
		while (!trouve) {
			if (valeurCandidate == 0) {
				// System.out.println("destination :"+etiquetteMax(cupsRestantes));
				return Collections.max(cups);
			} else if (cups.contains(valeurCandidate)) {
				// System.out.println("destination :"+valeurCandidate);
				return valeurCandidate;
			} else {
				valeurCandidate = valeurCandidate - 1;
			}
		}
		return null;
	}

	public void replacer(List<Integer> pickedUp) {
		Integer destination = getDestination(cups);
		Integer positionDestination = getDestinationPosition(destination);
		System.out.println("des "+destination);
		System.out.println("posDes "+positionDestination);
		cups.addAll(positionDestination + 1, pickedUp);
		cups = decalerCups();
	}

	private Integer getDestinationPosition(Integer destination) {

		for (int i = 0; i < cups.size() ; i++) {
		//	System.out.println("c "+cups.get(i));
		//	System.out.println("d "+destination);
		//	System.out.println((cups.get(i) ==destination));
			if (cups.get(i) ==destination) {
			//	System.out.println("ici");
				return i;
			}
		}
		return null;
	}

	public List<Integer> decalerCups() {

		while (!cups.get(posActu).equals(valActu)) {
			cups.add(cups.get(0));
			cups.remove(0);
		}
		return cups;
	}

	public void majValPosActu() {

		if (posActu == cups.size() - 1) {
			this.posActu = 0;
		} else {
			this.posActu++;
		}

		this.valActu = cups.get(posActu);

	}

}