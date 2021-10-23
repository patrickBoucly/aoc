package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Day17 {

	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));
		
		long startTime = System.currentTimeMillis();

		System.out.println("solve172 :" + solve172("input17"));

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		// System.out.println("solve102 :" + solve172("input17"));

	}

	private static long solve172(String nomFic) {
		Collection<Hypercube> newHyperubes = getDataH(nomFic);
		Hyperespace hyperespace = new Hyperespace();
		hyperespace.addAll((List<Hypercube>) newHyperubes);
		Regle r = new Regle();
		for(int i =0;i<6;i++) {
			System.out.println(i+" "+hyperespace.getHypercubes().stream().count());
			hyperespace=resoudreH(hyperespace, r);
		}
		
		return hyperespace.getHypercubes().stream().count();
	}

	

	private static String solve171(String nomFic) {
		Collection<Cube> newCubes = getData(nomFic);
		Espace espace = new Espace();
		Collection<Cube> newCubes2 = new ArrayList<>();
		newCubes2.addAll(Arrays.asList(new Cube(-1, -1, -1), new Cube(1, 0, -1), new Cube(0, 1, -1),
				new Cube(-1, -1, 0), new Cube(1, -1, 0), new Cube(0, 0, 0), new Cube(1, 0, 0), new Cube(0, 1, 0),
				new Cube(-1, -1, 1), new Cube(1, 0, 1), new Cube(0, 1, 1)));
		// espace.addAll((List<Cube>) newCubes2);
		espace.addAll((List<Cube>) newCubes);
		Regle r = new Regle();

		System.out.println("comptage final :" + espace.getCubes().stream().count());
		/*
		 * for(Cube c:getVoisines(0,0,0,
		 * espace).distinct().collect(Collectors.toList())){ System.out.println(c); }
		 * 
		 * Espace espace2 = new Espace(); espace2.addAll((List<Cube>) newCubes2);
		 * espace2.affiche();
		 */
		Espace espace0 = new Espace();
		espace0.addAll((List<Cube>) newCubes);

		/*
		 * for(Cube c:espace3.getCubes()) {
		 * 
		 * System.out.println(c.toString()+ "a "+getVoisines(c,
		 * espace3).count()+" voisins"); for(Cube v:getVoisines(c,
		 * espace3).collect(Collectors.toList())) { System.out.println(v); } }
		 */
		System.out.println("##############");
		/*
		 * for (int i = 1; i < 4; i++) {
		 * System.out.println("comptage avant iteration n°" + i + " :" +
		 * espace3.getCubes().stream().count()); espace3 = resoudre(espace, r);
		 * espace3.affiche(); System.out.println("comptage apres iteration n°" + i +
		 * " :" + espace3.getCubes().stream().count()); }
		 */
		Espace espace1 = new Espace(resoudre(espace0, r));
		Espace espace2 = new Espace(resoudre(espace1, r));
		Espace espace3 = new Espace(resoudre(espace2, r));
		Espace espace4 = new Espace(resoudre(espace3, r));
		Espace espace5 = new Espace(resoudre(espace4, r));
		Espace espace6 = new Espace(resoudre(espace5, r));
		System.out.println(espace0.getCubes().stream().count());
		System.out.println(espace1.getCubes().stream().count());
		System.out.println(espace2.getCubes().stream().count());
		System.out.println(espace3.getCubes().stream().count());
		System.out.println(espace4.getCubes().stream().count());
		System.out.println(espace5.getCubes().stream().count());
		System.out.println(espace6.getCubes().stream().count());
		return null;
	}
	private static Stream<Hypercube> getVoisinesH(Hypercube h, Hyperespace hyperespace) {
		int x=h.getX();
		int y=h.getY();
		int z=h.getZ();
		int w=h.getW();
		 return Arrays.asList(hyperespace.getHypercube(x - 1, y - 1, z - 1, w-1), hyperespace.getHypercube(x - 1, y - 1, z, w-1), 
				 hyperespace.getHypercube(x - 1, y - 1, z + 1, w-1),
				 hyperespace.getHypercube(x - 1, y, z - 1, w-1), hyperespace.getHypercube(x - 1, y, z, w-1), 
				 hyperespace.getHypercube(x - 1, y, z + 1, w-1), hyperespace.getHypercube(x - 1, y + 1, z - 1, w-1), hyperespace.getHypercube(x - 1, y + 1, z, w-1), hyperespace.getHypercube(x - 1, y + 1, z + 1, w-1), hyperespace.getHypercube(x + 1, y - 1, z - 1, w-1), hyperespace.getHypercube(x + 1, y - 1, z, w-1), hyperespace.getHypercube(x + 1, y - 1, z + 1, w-1), hyperespace.getHypercube(x + 1, y, z - 1, w-1), hyperespace.getHypercube(x + 1, y, z, w-1), hyperespace.getHypercube(x + 1, y, z + 1, w-1), hyperespace.getHypercube(x + 1, y + 1, z - 1, w-1), hyperespace.getHypercube(x + 1, y + 1, z, w-1), hyperespace.getHypercube(x + 1, y + 1, z + 1, w-1), hyperespace.getHypercube(x, y - 1, z - 1, w-1), hyperespace.getHypercube(x, y - 1, z, w-1), hyperespace.getHypercube(x, y - 1, z + 1, w-1), hyperespace.getHypercube(x, y + 1, z - 1, w-1), hyperespace.getHypercube(x, y + 1, z, w-1), hyperespace.getHypercube(x, y + 1, z + 1, w-1), hyperespace.getHypercube(x, y, z - 1, w-1), hyperespace.getHypercube(x, y, z , w-1), hyperespace.getHypercube(x, y, z + 1, w-1), hyperespace.getHypercube(x - 1, y - 1, z - 1, w), hyperespace.getHypercube(x - 1, y - 1, z, w), hyperespace.getHypercube(x - 1, y - 1, z + 1, w), hyperespace.getHypercube(x - 1, y, z - 1, w), hyperespace.getHypercube(x - 1, y, z, w), hyperespace.getHypercube(x - 1, y, z + 1, w), hyperespace.getHypercube(x - 1, y + 1, z - 1, w), hyperespace.getHypercube(x - 1, y + 1, z, w), hyperespace.getHypercube(x - 1, y + 1, z + 1, w), hyperespace.getHypercube(x + 1, y - 1, z - 1, w), hyperespace.getHypercube(x + 1, y - 1, z, w), hyperespace.getHypercube(x + 1, y - 1, z + 1, w), hyperespace.getHypercube(x + 1, y, z - 1, w), hyperespace.getHypercube(x + 1, y, z, w), hyperespace.getHypercube(x + 1, y, z + 1, w), hyperespace.getHypercube(x + 1, y + 1, z - 1, w), hyperespace.getHypercube(x + 1, y + 1, z, w), hyperespace.getHypercube(x + 1, y + 1, z + 1, w), hyperespace.getHypercube(x, y - 1, z - 1, w), hyperespace.getHypercube(x, y - 1, z, w), hyperespace.getHypercube(x, y - 1, z + 1, w), hyperespace.getHypercube(x, y + 1, z - 1, w), hyperespace.getHypercube(x, y + 1, z, w), hyperespace.getHypercube(x, y + 1, z + 1, w), hyperespace.getHypercube(x, y, z - 1, w), hyperespace.getHypercube(x, y, z + 1, w), hyperespace.getHypercube(x - 1, y - 1, z - 1, w+1), hyperespace.getHypercube(x - 1, y - 1, z, w+1), hyperespace.getHypercube(x - 1, y - 1, z + 1, w+1), hyperespace.getHypercube(x - 1, y, z - 1, w+1), hyperespace.getHypercube(x - 1, y, z, w+1), hyperespace.getHypercube(x - 1, y, z + 1, w+1), hyperespace.getHypercube(x - 1, y + 1, z - 1, w+1), hyperespace.getHypercube(x - 1, y + 1, z, w+1), hyperespace.getHypercube(x - 1, y + 1, z + 1, w+1), hyperespace.getHypercube(x + 1, y - 1, z - 1, w+1), hyperespace.getHypercube(x + 1, y - 1, z, w+1), hyperespace.getHypercube(x + 1, y - 1, z + 1, w+1), hyperespace.getHypercube(x + 1, y, z - 1, w+1), hyperespace.getHypercube(x + 1, y, z, w+1), hyperespace.getHypercube(x + 1, y, z + 1, w+1), hyperespace.getHypercube(x + 1, y + 1, z - 1, w+1), hyperespace.getHypercube(x + 1, y + 1, z, w+1), hyperespace.getHypercube(x + 1, y + 1, z + 1, w+1), hyperespace.getHypercube(x, y - 1, z - 1, w+1), hyperespace.getHypercube(x, y - 1, z, w+1), hyperespace.getHypercube(x, y - 1, z + 1, w+1), hyperespace.getHypercube(x, y + 1, z - 1, w+1), hyperespace.getHypercube(x, y + 1, z, w+1), hyperespace.getHypercube(x, y + 1, z + 1, w+1), hyperespace.getHypercube(x, y, z - 1, w+1), hyperespace.getHypercube(x, y, z , w+1), hyperespace.getHypercube(x, y, z + 1, w+1)

				 ).stream().filter(Optional::isPresent).map(Optional::get);
	}
	private static Stream<Cube> getVoisines(Cube c, Espace espace) {
		return Arrays.asList(espace.getCube(c.getX() - 1, c.getY() - 1, c.getZ() - 1),
				espace.getCube(c.getX() - 1, c.getY() - 1, c.getZ()),
				espace.getCube(c.getX() - 1, c.getY() - 1, c.getZ() + 1),
				espace.getCube(c.getX() - 1, c.getY(), c.getZ() - 1), espace.getCube(c.getX() - 1, c.getY(), c.getZ()),
				espace.getCube(c.getX() - 1, c.getY(), c.getZ() + 1),
				espace.getCube(c.getX() - 1, c.getY() + 1, c.getZ() - 1),
				espace.getCube(c.getX() - 1, c.getY() + 1, c.getZ()),
				espace.getCube(c.getX() - 1, c.getY() + 1, c.getZ() + 1),
				espace.getCube(c.getX() + 1, c.getY() - 1, c.getZ() - 1),
				espace.getCube(c.getX() + 1, c.getY() - 1, c.getZ()),
				espace.getCube(c.getX() + 1, c.getY() - 1, c.getZ() + 1),
				espace.getCube(c.getX() + 1, c.getY(), c.getZ() - 1), espace.getCube(c.getX() + 1, c.getY(), c.getZ()),
				espace.getCube(c.getX() + 1, c.getY(), c.getZ() + 1),
				espace.getCube(c.getX() + 1, c.getY() + 1, c.getZ() - 1),
				espace.getCube(c.getX() + 1, c.getY() + 1, c.getZ()),
				espace.getCube(c.getX() + 1, c.getY() + 1, c.getZ() + 1),
				espace.getCube(c.getX(), c.getY() - 1, c.getZ() - 1), espace.getCube(c.getX(), c.getY() - 1, c.getZ()),
				espace.getCube(c.getX(), c.getY() - 1, c.getZ() + 1),
				espace.getCube(c.getX(), c.getY() + 1, c.getZ() - 1), espace.getCube(c.getX(), c.getY() + 1, c.getZ()),
				espace.getCube(c.getX(), c.getY() + 1, c.getZ() + 1), espace.getCube(c.getX(), c.getY(), c.getZ() - 1),
				espace.getCube(c.getX(), c.getY(), c.getZ() + 1)).stream().filter(Optional::isPresent)
				.map(Optional::get);
	}

	public static Espace resoudre(Espace espace, Regle regle) {
		Espace clone = new Espace(espace);
		for (int x = -(espace.getTaille() + 1); x <= espace.getTaille() + 1; x++) {
			for (int y = -(espace.getTaille() + 1); y <= espace.getTaille() + 1; y++) {
				for (int z = -(espace.getTaille() + 1); z <= espace.getTaille() + 1; z++) {
					// System.out.println("etude de :(" + x + "," + y + "," + z + ")");
					/*
					 * for(Cube c:getVoisines(0,0,0,
					 * espace).distinct().collect(Collectors.toList())){ System.out.println(c); }
					 */

					long nbVoisinesVivantes = getVoisines(x, y, z, espace).count();
					/*
					 * System.out.println("present? " + espace.getCube(x, y, z).isPresent());
					 * System.out.println("nbV :" + nbVoisinesVivantes);
					 * System.out.println("Si absent, doit naitre? " +
					 * regle.doitVivre(nbVoisinesVivantes));
					 * System.out.println("Si present, doit mourrir? " +
					 * regle.doitMourrir(nbVoisinesVivantes));
					 */
					if ((!(espace.getCube(x, y, z).isPresent()) && regle.doitVivre(nbVoisinesVivantes))) {
						System.out.println("ajouter :(" + x + "," + y + "," + z + ")");
						clone.add(new Cube(x, y, z));

					} else if (espace.getCube(x, y, z).isPresent() && regle.doitMourrir(nbVoisinesVivantes)) {
						System.out.println("retirer :(" + x + "," + y + "," + z + ")");
						clone.remove(x, y, z);
					}
				}
			}
		}

		return clone;
	}

	public static Hyperespace resoudreH(Hyperespace hyperespace, Regle regle) {
		Hyperespace clone = new Hyperespace(hyperespace);
		for (int x = -(hyperespace.getTaille() + 1); x <= hyperespace.getTaille() + 1; x++) {
			for (int y = -(hyperespace.getTaille() + 1); y <= hyperespace.getTaille() + 1; y++) {
				for (int z = -(hyperespace.getTaille() + 1); z <= hyperespace.getTaille() + 1; z++) {
					for (int w = -(hyperespace.getTaille() + 1); w <= hyperespace.getTaille() + 1; w++) {
						// System.out.println("etude de :(" + x + "," + y + "," + z + ")");
						/*
						 * for(Cube c:getVoisines(0,0,0,
						 * espace).distinct().collect(Collectors.toList())){ System.out.println(c); }
						 */

						long nbVoisinesVivantes = getVoisinesH(x, y, z, w, hyperespace).count();
						/*
						 * System.out.println("present? " + espace.getCube(x, y, z).isPresent());
						 * System.out.println("nbV :" + nbVoisinesVivantes);
						 * System.out.println("Si absent, doit naitre? " +
						 * regle.doitVivre(nbVoisinesVivantes));
						 * System.out.println("Si present, doit mourrir? " +
						 * regle.doitMourrir(nbVoisinesVivantes));
						 */
						if ((!(hyperespace.getHypercube(x, y, z, w).isPresent())
								&& regle.doitVivre(nbVoisinesVivantes))) {
							//System.out.println("ajouter :(" + x + "," + y + "," + z + ")");
							clone.add(new Hypercube(x, y, z, w));

						} else if (hyperespace.getHypercube(x, y, z, w).isPresent()
								&& regle.doitMourrir(nbVoisinesVivantes)) {
							//System.out.println("retirer :(" + x + "," + y + "," + z + ")");
							clone.remove(x, y, z, w);
						}
					}
				}
			}
		}

		return clone;
	}

	private static Stream<Hypercube> getVoisinesH(int x, int y, int z, int w,Hyperespace hyperespace) {
		return Arrays.asList(hyperespace.getHypercube(x - 1, y - 1, z - 1, w-1), hyperespace.getHypercube(x - 1, y - 1, z, w-1), hyperespace.getHypercube(x - 1, y - 1, z + 1, w-1), hyperespace.getHypercube(x - 1, y, z - 1, w-1), hyperespace.getHypercube(x - 1, y, z, w-1), hyperespace.getHypercube(x - 1, y, z + 1, w-1), hyperespace.getHypercube(x - 1, y + 1, z - 1, w-1), hyperespace.getHypercube(x - 1, y + 1, z, w-1), hyperespace.getHypercube(x - 1, y + 1, z + 1, w-1), hyperespace.getHypercube(x + 1, y - 1, z - 1, w-1), hyperespace.getHypercube(x + 1, y - 1, z, w-1), hyperespace.getHypercube(x + 1, y - 1, z + 1, w-1), hyperespace.getHypercube(x + 1, y, z - 1, w-1), hyperespace.getHypercube(x + 1, y, z, w-1), hyperespace.getHypercube(x + 1, y, z + 1, w-1), hyperespace.getHypercube(x + 1, y + 1, z - 1, w-1), hyperespace.getHypercube(x + 1, y + 1, z, w-1), hyperespace.getHypercube(x + 1, y + 1, z + 1, w-1), hyperespace.getHypercube(x, y - 1, z - 1, w-1), hyperespace.getHypercube(x, y - 1, z, w-1), hyperespace.getHypercube(x, y - 1, z + 1, w-1), hyperespace.getHypercube(x, y + 1, z - 1, w-1), hyperespace.getHypercube(x, y + 1, z, w-1), hyperespace.getHypercube(x, y + 1, z + 1, w-1), hyperespace.getHypercube(x, y, z - 1, w-1), hyperespace.getHypercube(x, y, z , w-1), hyperespace.getHypercube(x, y, z + 1, w-1), hyperespace.getHypercube(x - 1, y - 1, z - 1, w), hyperespace.getHypercube(x - 1, y - 1, z, w), hyperespace.getHypercube(x - 1, y - 1, z + 1, w), hyperespace.getHypercube(x - 1, y, z - 1, w), hyperespace.getHypercube(x - 1, y, z, w), hyperespace.getHypercube(x - 1, y, z + 1, w), hyperespace.getHypercube(x - 1, y + 1, z - 1, w), hyperespace.getHypercube(x - 1, y + 1, z, w), hyperespace.getHypercube(x - 1, y + 1, z + 1, w), hyperespace.getHypercube(x + 1, y - 1, z - 1, w), hyperespace.getHypercube(x + 1, y - 1, z, w), hyperespace.getHypercube(x + 1, y - 1, z + 1, w), hyperespace.getHypercube(x + 1, y, z - 1, w), hyperespace.getHypercube(x + 1, y, z, w), hyperespace.getHypercube(x + 1, y, z + 1, w), hyperespace.getHypercube(x + 1, y + 1, z - 1, w), hyperespace.getHypercube(x + 1, y + 1, z, w), hyperespace.getHypercube(x + 1, y + 1, z + 1, w), hyperespace.getHypercube(x, y - 1, z - 1, w), hyperespace.getHypercube(x, y - 1, z, w), hyperespace.getHypercube(x, y - 1, z + 1, w), hyperespace.getHypercube(x, y + 1, z - 1, w), hyperespace.getHypercube(x, y + 1, z, w), hyperespace.getHypercube(x, y + 1, z + 1, w), hyperespace.getHypercube(x, y, z - 1, w), hyperespace.getHypercube(x, y, z + 1, w), hyperespace.getHypercube(x - 1, y - 1, z - 1, w+1), hyperespace.getHypercube(x - 1, y - 1, z, w+1), hyperespace.getHypercube(x - 1, y - 1, z + 1, w+1), hyperespace.getHypercube(x - 1, y, z - 1, w+1), hyperespace.getHypercube(x - 1, y, z, w+1), hyperespace.getHypercube(x - 1, y, z + 1, w+1), hyperespace.getHypercube(x - 1, y + 1, z - 1, w+1), hyperespace.getHypercube(x - 1, y + 1, z, w+1), hyperespace.getHypercube(x - 1, y + 1, z + 1, w+1), hyperespace.getHypercube(x + 1, y - 1, z - 1, w+1), hyperespace.getHypercube(x + 1, y - 1, z, w+1), hyperespace.getHypercube(x + 1, y - 1, z + 1, w+1), hyperespace.getHypercube(x + 1, y, z - 1, w+1), hyperespace.getHypercube(x + 1, y, z, w+1), hyperespace.getHypercube(x + 1, y, z + 1, w+1), hyperespace.getHypercube(x + 1, y + 1, z - 1, w+1), hyperespace.getHypercube(x + 1, y + 1, z, w+1), hyperespace.getHypercube(x + 1, y + 1, z + 1, w+1), hyperespace.getHypercube(x, y - 1, z - 1, w+1), hyperespace.getHypercube(x, y - 1, z, w+1), hyperespace.getHypercube(x, y - 1, z + 1, w+1), hyperespace.getHypercube(x, y + 1, z - 1, w+1), hyperespace.getHypercube(x, y + 1, z, w+1), hyperespace.getHypercube(x, y + 1, z + 1, w+1), hyperespace.getHypercube(x, y, z - 1, w+1), hyperespace.getHypercube(x, y, z , w+1), hyperespace.getHypercube(x, y, z + 1, w+1)
				
				).stream().filter(Optional::isPresent).map(Optional::get);
	}

	private static Stream<Cube> getVoisines(int x, int y, int z, Espace espace) {
		return Arrays.asList(espace.getCube(x - 1, y - 1, z - 1), espace.getCube(x - 1, y - 1, z),
				espace.getCube(x - 1, y - 1, z + 1), espace.getCube(x - 1, y, z - 1), espace.getCube(x - 1, y, z),
				espace.getCube(x - 1, y, z + 1), espace.getCube(x - 1, y + 1, z - 1), espace.getCube(x - 1, y + 1, z),
				espace.getCube(x - 1, y + 1, z + 1), espace.getCube(x + 1, y - 1, z - 1),
				espace.getCube(x + 1, y - 1, z), espace.getCube(x + 1, y - 1, z + 1), espace.getCube(x + 1, y, z - 1),
				espace.getCube(x + 1, y, z), espace.getCube(x + 1, y, z + 1), espace.getCube(x + 1, y + 1, z - 1),
				espace.getCube(x + 1, y + 1, z), espace.getCube(x + 1, y + 1, z + 1), espace.getCube(x, y - 1, z - 1),
				espace.getCube(x, y - 1, z), espace.getCube(x, y - 1, z + 1), espace.getCube(x, y + 1, z - 1),
				espace.getCube(x, y + 1, z), espace.getCube(x, y + 1, z + 1), espace.getCube(x, y, z - 1),
				espace.getCube(x, y, z + 1)).stream().filter(Optional::isPresent).map(Optional::get);
	}

	private static Collection<Cube> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		ArrayList<Cube> res = new ArrayList<>();

		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				int y = -4;
				while (bufferReader.ready()) {
					y++;
					String line = bufferReader.readLine();
					if (!(line.length() == 0)) {
						for (int i = 0; i < line.length(); i++) {
							if (line.substring(i, i + 1).equals("#")) {
								res.add(new Cube(i - 3, y, 0));
							}
						}
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static Collection<Hypercube> getDataH(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		ArrayList<Hypercube> res = new ArrayList<>();

		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				int y = -4;
				while (bufferReader.ready()) {
					y++;
					String line = bufferReader.readLine();
					if (!(line.length() == 0)) {
						for (int i = 0; i < line.length(); i++) {
							if (line.substring(i, i + 1).equals("#")) {
								res.add(new Hypercube(i - 3, y, 0, 0));
							}
						}
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static Collection<Cube> getData_sample(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		ArrayList<Cube> res = new ArrayList<>();

		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				int y = -2;
				while (bufferReader.ready()) {
					y++;
					String line = bufferReader.readLine();
					if (!(line.length() == 0)) {
						for (int i = 0; i < line.length(); i++) {
							if (line.substring(i, i + 1).equals("#")) {
								res.add(new Cube(i - 1, y, 0));
							}
						}
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static Collection<Hypercube> getDataHsample(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		ArrayList<Hypercube> res = new ArrayList<>();

		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);

			try {
				int y = -2;
				while (bufferReader.ready()) {
					y++;
					String line = bufferReader.readLine();
					if (!(line.length() == 0)) {
						for (int i = 0; i < line.length(); i++) {
							if (line.substring(i, i + 1).equals("#")) {
								res.add(new Hypercube(i - 1, y, 0, 0));
							}
						}
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
