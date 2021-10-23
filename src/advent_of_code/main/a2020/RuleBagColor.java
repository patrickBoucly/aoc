package advent_of_code.main.a2020;

import java.util.Map;

public class RuleBagColor {
String color;
Map<String,Integer> contenu;
public RuleBagColor(String color, Map<String, Integer> contenu) {
	super();
	this.color = color;
	this.contenu = contenu;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public Map<String, Integer> getContenu() {
	return contenu;
}
public void setContenu(Map<String, Integer> contenu) {
	this.contenu = contenu;
}



}
