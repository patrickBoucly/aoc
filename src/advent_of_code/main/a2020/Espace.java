package advent_of_code.main.a2020;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Espace {
	private Map<String, Cube> cubes;

	private static final String SEP = "#";

	private int taille;

	public Espace() {
		cubes = new HashMap<>();
	}

	public Espace(int taille) {

		this.taille = taille;
	}

	public Espace(Espace espace) {
		cubes = new HashMap<>(espace.cubes);
		taille = espace.taille;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Collection<Cube> getCubes() {
		return cubes.values();
	}

	public void setCubes(Collection<Cube> newCubes) {
		this.cubes = new HashMap<>();
		for (Cube c : newCubes) {
			cubes.put(getKey(c.getX(), c.getY(), c.getZ()), c);
		}
	}

	private String getKey(int x, int y, int z) {
		return x + SEP + y + SEP + z;
	}

	/**
	 * Ajoute une cellule en position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void add(Cube c) {
		taille = Math.max(taille, Math.max(Math.max(Math.abs(c.getX()), Math.abs(c.getY())),Math.abs(c.getZ())));
		cubes.put(getKey(c.getX(), c.getY(), c.getZ()), c);
	}

	public void addAll(List<Cube> cubes) {
		cubes.forEach(this::add);
	}

	/**
	 * Envlève la cellule en position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void remove(int x, int y, int z) {
		cubes.remove(getKey(x, y, z));
	}

	public Optional<Cube> getCube(int x, int y, int z) {
		return Optional.ofNullable(cubes.get(getKey(x, y, z)));
	}

	public void affiche() {
		
		for (int z = -taille; z <= taille; z++) {
			System.out.println("z =" + z);

			for (int y =-taille; y <= taille; y++) {
				for (int x = -taille;x <= taille; x++) {
					System.out.print(getCube(x, y, z).isPresent() ? "# ": "_ ");
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cubes == null) ? 0 : cubes.hashCode());
		result = prime * result + taille;
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
		Espace other = (Espace) obj;
		if (cubes == null) {
			if (other.cubes != null)
				return false;
		} else if (!cubes.equals(other.cubes))
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}

}
