package advent_of_code.main.a2020;

import java.util.List;

public class Plan {
List<Emplacement> plan;

public List<Emplacement> getPlan() {
	return plan;
}

public void setPlan(List<Emplacement> plan) {
	this.plan = plan;
}

public Plan(List<Emplacement> plan) {
	super();
	this.plan = plan;
}
public void afficher() {
	
		for(int i=0;i<96;i++) {
			String line="";
			for(int j=0;j<91;j++) {
				for(Emplacement e:plan) {
					if(e.colonne == j && e.ligne ==i) {
						line+=e.getEtat();
					}
				}
			}
			System.out.println(line);
				
		}
	
}
}
