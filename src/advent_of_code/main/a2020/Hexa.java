package advent_of_code.main.a2020;

public class Hexa {
int posI;
int posJ;
boolean isReturn=false;
public int getPosI() {
	return posI;
}
public void setPosI(int posI) {
	this.posI = posI;
}
public int getPosJ() {
	return posJ;
}
public void setPosJ(int posJ) {
	this.posJ = posJ;
}
public boolean isReturn() {
	return isReturn;
}
public void setReturn(boolean isReturn) {
	this.isReturn = isReturn;
}
public Hexa(int posI, int posJ, boolean isReturn) {
	super();
	this.posI = posI;
	this.posJ = posJ;
	this.isReturn = isReturn;
}
@Override
	public String toString() {
		String couleur="white";
		if(isReturn) {
			couleur="black";
		}
		return "("+posI+","+posJ+") est de couleur "+couleur ;
	}
}
