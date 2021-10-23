package advent_of_code.main.a2020;

import java.util.HashMap;

public class Tuile {
  HashMap<String,String> position=new HashMap<>();
  String titre;
public HashMap<String, String> getPosition() {
	return position;
}
public void setPosition(HashMap<String, String> position) {
	this.position = position;
}
public String getTitre() {
	return titre;
}
public void setTitre(String titre) {
	this.titre = titre;
}
public Tuile(HashMap<String, String> position, String titre) {
	super();
	this.position = position;
	this.titre = titre;
}
public Tuile rotationO() {
	 HashMap<String,String> newP=new HashMap<>();
	 newP.put("N",this.position.get("O"));
	 newP.put("E",this.position.get("N"));
	 newP.put("S",this.position.get("E"));
	 newP.put("O",this.position.get("S"));
	return new Tuile(newP,this.titre);
	
}
public Tuile flipNS() {
	 HashMap<String,String> newP=new HashMap<>();
	 StringBuilder newN= new StringBuilder(this.position.get("S"));
	 StringBuilder newS= new StringBuilder(this.position.get("N"));
	 newP.put("N",newN.reverse().toString());
	 newP.put("E",this.position.get("E"));
	 newP.put("S",newS.reverse().toString());
	 newP.put("O",this.position.get("O"));
	return new Tuile(newP,this.titre);
	
}
}
