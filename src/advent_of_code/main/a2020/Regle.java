package advent_of_code.main.a2020;

/*
 * Si un cube est actif et qu'exactement 2 ou 3 de ses voisins sont également
 * actifs, le cube reste actif. Dans le cas contraire, le cube devient inactif.
 * Si un cube est inactif mais qu'exactement 3 de ses voisins sont actifs, le
 * cube devient actif. Sinon, le cube reste inactif.
 * 
 */
public class Regle {
	public boolean doitVivre(long nbVoisinesVivantes) {
		return nbVoisinesVivantes == 3;
	}

	public boolean doitMourrir(long nbVoisinesVivantes) {
		return nbVoisinesVivantes < 2 || nbVoisinesVivantes > 3;
	}
}