package advent_of_code.main.a2020;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Hyperespace {
	private Map<String, Hypercube> hypercubes;

	private static final String SEP = "#";

	private int taille;

	public Hyperespace() {
		hypercubes = new HashMap<>();
	}

	public Hyperespace(int taille) {

		this.taille = taille;
	}

	public Hyperespace(Hyperespace hyperespace) {
		hypercubes = new HashMap<>(hyperespace.hypercubes);
		taille = hyperespace.taille;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Collection<Hypercube> getHypercubes() {
		return hypercubes.values();
	}

	public void setCubes(Collection<Hypercube> newHypercubes) {
		this.hypercubes = new HashMap<>();
		for (Hypercube h : newHypercubes) {
			hypercubes.put(getKey(h.getX(), h.getY(), h.getZ(), h.getW()), h);
		}
	}

	private String getKey(int x, int y, int z, int w) {
		return x + SEP + y + SEP + z + SEP + w;
	}

	/**
	 * Ajoute une cellule en position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void add(Hypercube h) {
		taille = Math.max(taille, Math.max(
				Math.max(Math.max(Math.abs(h.getX()), Math.abs(h.getY())), Math.abs(h.getZ())), Math.abs(h.getW())));
		hypercubes.put(getKey(h.getX(), h.getY(), h.getZ(), h.getW()), h);
	}

	public void addAll(List<Hypercube> hypercubes) {
		hypercubes.forEach(this::add);
	}

	/**
	 * Envlève la cellule en position (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public void remove(int x, int y, int z, int w) {
		hypercubes.remove(getKey(x, y, z, w));
	}

	public Optional<Hypercube> getHypercube(int x, int y, int z, int w) {
		return Optional.ofNullable(hypercubes.get(getKey(x, y, z, w)));
	}

	public void affiche() {
		for (int w = -taille; w <= taille; w++) {
			System.out.println("w =" + w);
			for (int z = -taille; z <= taille; z++) {
				System.out.println("z =" + z);

				for (int y = -taille; y <= taille; y++) {
					for (int x = -taille; x <= taille; x++) {
						System.out.print(getHypercube(x, y, z, w).isPresent() ? "# " : "_ ");
					}
					System.out.println();
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hypercubes == null) ? 0 : hypercubes.hashCode());
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
		Hyperespace other = (Hyperespace) obj;
		if (hypercubes == null) {
			if (other.hypercubes != null)
				return false;
		} else if (!hypercubes.equals(other.hypercubes))
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}

}
