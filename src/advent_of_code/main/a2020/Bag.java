package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.List;

public class Bag {
	
	public boolean isDernier() {
		return dernier;
	}

	public void setDernier(boolean dernier) {
		this.dernier = dernier;
	}

	String color;
	List<Bag> contenue;
	boolean dernier=false; 

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Bag> getContenue() {
		return contenue;
	}

	public void setContenue(List<Bag> contenue) {
		this.contenue = contenue;
	}

	public Bag(String color, List<Bag> contenue) {
		super();
		this.color = color;
		this.contenue = contenue;
		this.dernier=false;
	}

	public void addBagsFromRule(RuleBagColor rule) {
		if (rule != null) {
			for (String color : rule.getContenu().keySet()) {
				int nbBagColor=rule.getContenu().get(color);
				for (int nbBag = 0; nbBag < nbBagColor; nbBag++) {
					this.getContenue().add(new Bag(color, new ArrayList<Bag>()));
				}
			}
		} else {
			dernier=true;
		}
	}
}
