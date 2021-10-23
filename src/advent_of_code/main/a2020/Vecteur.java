package advent_of_code.main.a2020;

public class Vecteur {
float abscisse;
float ordonne;
float longueur;
public float getAbscisse() {
	return abscisse;
}
public void setAbscisse(int abscisse) {
	this.abscisse = abscisse;
}
public float getOrdonne() {
	return ordonne;
}
public void setOrdonne(int ordonne) {
	this.ordonne = ordonne;
}
public float getLongueur() {
	return longueur;
}
public void setLongueur(float longueur) {
	this.longueur = longueur;
}
public Vecteur(float f, float g) {
	super();
	this.abscisse = f;
	this.ordonne = g;
	this.longueur=(float) Math.sqrt(f*f+g*g);
}

public static boolean sontColineaire(Vecteur v1, Vecteur v2) {
	return (v1.getAbscisse() * v2.getOrdonne()-v2.getAbscisse() * v1.getOrdonne() == 0);
}
@Override
	public String toString() {
	String res="("+getAbscisse()+";"+getOrdonne()+")";
		return res;
	}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Float.floatToIntBits(abscisse);
	result = prime * result + Float.floatToIntBits(longueur);
	result = prime * result + Float.floatToIntBits(ordonne);
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
	Vecteur other = (Vecteur) obj;
	if (Float.floatToIntBits(abscisse) != Float.floatToIntBits(other.abscisse))
		return false;
	if (Float.floatToIntBits(longueur) != Float.floatToIntBits(other.longueur))
		return false;
	if (Float.floatToIntBits(ordonne) != Float.floatToIntBits(other.ordonne))
		return false;
	return true;
}

}
