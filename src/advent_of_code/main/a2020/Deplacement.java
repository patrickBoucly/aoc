package advent_of_code.main.a2020;

public class Deplacement {
   String direction;
   Integer quantite;
public String getDirection() {
	return direction;
}
public void setDirection(String direction) {
	this.direction = direction;
}
public Integer getQuantite() {
	return quantite;
}
public void setQuantite(Integer quantite) {
	this.quantite = quantite;
}
public Deplacement(String direction, Integer quantite) {
	super();
	this.direction = direction;
	this.quantite = quantite;
}
	@Override
		public String toString() {
			
			return "direction: "+this.direction+", quantité :"+this.getQuantite();
		}

}
