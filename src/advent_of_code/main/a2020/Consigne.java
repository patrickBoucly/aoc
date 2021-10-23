package advent_of_code.main.a2020;

public class Consigne {
int min;
int max;
String lettre;
public Consigne(String consigne) {
	System.out.println(consigne);
	lettre=consigne.substring(consigne.length()-1);
	min=Integer.parseInt(consigne.split("-")[0]);
	max=Integer.parseInt(consigne.substring(consigne.indexOf("-") + 1, consigne.indexOf(" ")));
}
public int getMin() {
	return min;
}
public void setMin(int min) {
	this.min = min;
}
public int getMax() {
	return max;
}
public void setMax(int max) {
	this.max = max;
}
public String getLettre() {
	return lettre;
}
public void setLettre(String lettre) {
	this.lettre = lettre;
}
	
	
}
