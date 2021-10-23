package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21 {
	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));

		// System.out.println("solve181 :" + solve181("C:\\AOC\\input18",1));
		solve211("C:\\AOC\\input21");
	}

	private static void solve211(String monFic) {
		List<Food> foods = getFoods(monFic);
		HashMap<String, List<Integer>> allergenesDansFood = new HashMap<>();

		for (Food f : foods) {

			for (String al : f.allergenes) {

				if (allergenesDansFood.containsKey(al)) {
					List<Integer> a = allergenesDansFood.get(al);
					a.add(f.id);
					allergenesDansFood.put(al, a);
				} else {
					allergenesDansFood.put(al, new ArrayList<Integer>());
					List<Integer> a = allergenesDansFood.get(al);
					a.add(f.id);
					allergenesDansFood.put(al, a);

				}
			}
		}

		HashMap<String, Set<String>> ingredientPossiblesPArAllergene = new HashMap<String, Set<String>>();
		HashMap<String, Set<String>> ingredientPossiblesPArAllergeneEvo = new HashMap<String, Set<String>>();
		Set<String> listeIngredients = new HashSet<String>();
		Set<String> listeIngredientsProduitSansEgg = new HashSet<String>();
		for (String al : allergenesDansFood.keySet()) {
			if (!ingredientPossiblesPArAllergene.containsKey(al)) {
				Set<String> ingredientPossiblesDeAl = new HashSet<String>();
				ingredientPossiblesPArAllergene.put(al, ingredientPossiblesDeAl);
				Set<String> ingredientPossiblesDeAlEvo = new HashSet<String>();
				ingredientPossiblesPArAllergeneEvo.put(al, ingredientPossiblesDeAlEvo);

			}
			for (Integer idFood : allergenesDansFood.get(al)) {
				ingredientPossiblesPArAllergene.get(al).addAll(foods.get(idFood).ingredients);
				ingredientPossiblesPArAllergeneEvo.get(al).addAll(foods.get(idFood).ingredients);
				listeIngredients.addAll(foods.get(idFood).ingredients);
				if (!al.equals("eggs")) {
					listeIngredientsProduitSansEgg.addAll(foods.get(idFood).ingredients);
				}
			}
			System.out.println(al + " " + ingredientPossiblesPArAllergene.get(al));
		}

		Set<String> ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}

		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("shellfish").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas shellfish " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}

		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("nuts").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas nuts " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}
		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("fish").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas fish " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}
		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("sesame").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas sesame " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}
		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("peanuts").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas peanuts " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}
		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("soy").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas soy " + ingEggs);
		ingEggs = new HashSet<>();
		for (String s : ingredientPossiblesPArAllergene.get("eggs")) {
			ingEggs.add(s);
		}
		for (String ing : ingredientPossiblesPArAllergene.get("eggs")) {
			if (ingredientPossiblesPArAllergene.get("dairy").contains(ing)) {
				ingEggs.remove(ing);
			}
		}

		System.out.println("ingEggs pas dairy " + ingEggs);

		for (String ing : listeIngredients) {

			for (String al : ingredientPossiblesPArAllergene.keySet()) {
				if (al.equals("fish")) {
					if (!ingredientPossiblesPArAllergene.get(al).contains(ing)) {
						System.out.println(ing + " est pas dans la liste de " + al);
					}
				}
			}
		}
		/*
		 * for(String ing:listeIngredients) { System.out.println(ing); int tot=0;
		 * for(String al2:ingredientPossiblesPArAllergene.keySet()) {
		 * System.out.println(al2);
		 * if(!ingredientPossiblesPArAllergene.get(al2).contains(ing)) {
		 * System.out.println(ing +" pas dans "+al2); tot++; } }
		 * System.out.println(tot);
		 * 
		 * }
		 */

		for (String al : ingredientPossiblesPArAllergene.keySet()) {
			for (String ing : ingredientPossiblesPArAllergene.get(al)) {
				for (String al2 : ingredientPossiblesPArAllergene.keySet()) {
					if (!al.equals(al2)) {
						if (!ingredientPossiblesPArAllergene.get(al2).contains(ing)) {
							// System.out.println(al +" : L'ing "+ing+" n'est pas present dansla liste d'
							// "+al2);
						}
					}
				}
			}
		}
		// System.out.println("nb ingredients "+listeIngredients.size());
		Set<String> ingSansAl = new HashSet<String>();
		int totPasPArtout = 0;
		for (String ing : listeIngredients) {
			int dsXlist = 0;
			for (String al : ingredientPossiblesPArAllergene.keySet()) {
				if (ingredientPossiblesPArAllergene.get(al).contains(ing)) {
					dsXlist++;
				}
			}

			if (dsXlist != 8) {
				totPasPArtout++;
				// System.out.println(ing+ " pas partout");
			}

		}
		HashMap<String,Set<String>> allPossibleParIng =new HashMap<>();
		HashMap<String,Set<String>> allPossibleParIngEvo =new HashMap<>();
		for(String ing:listeIngredients) {
			if(!allPossibleParIng.containsKey(ing)){
				HashSet<String> all= new HashSet<>();
				allPossibleParIng.put(ing, all);
				HashSet<String> allevo= new HashSet<>();
				allPossibleParIngEvo.put(ing, allevo);
			} 
			for(String al:ingredientPossiblesPArAllergene.keySet()) {
				if(ingredientPossiblesPArAllergene.get(al).contains(ing)) {
					allPossibleParIng.get(ing).add(al);
					allPossibleParIngEvo.get(ing).add(al);
				}
			}
		}
		int tot=0;
		for(String ing:allPossibleParIng.keySet()) {
			if(allPossibleParIng.get(ing).size()<8) {
			System.out.println(ing+ " allergene possible :"+allPossibleParIng.get(ing));
			tot++;
			}
		}
		System.out.println(tot);
		
		for(Food f:foods) {
			for(String alFood:f.allergenes) {
				for(String ingSuspect:ingredientPossiblesPArAllergene.get(alFood)) {
					if(!f.ingredients.contains(ingSuspect)) {
						ingredientPossiblesPArAllergeneEvo.get(alFood).remove(ingSuspect);
					}
				}
			}
		}
		
		
		Set<String> listeIngredientsCoupables = new HashSet<String>();
		for(String al:ingredientPossiblesPArAllergeneEvo.keySet()) {
			if(!al.equals("nuts")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("spl");
					
				}
			if(!al.equals("soy")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("tpnnkc");
					
				}
			if(!al.equals("peanuts")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("hsksz");
					
				}
			if(!al.equals("dairy")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("bxjvzk");
					
				}
			if(!al.equals("sesame")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("qzzzf");
					
				}
			if(!al.equals("fish")) {
				ingredientPossiblesPArAllergeneEvo.get(al).remove("sp");
					
				}
			listeIngredientsCoupables.addAll(ingredientPossiblesPArAllergeneEvo.get(al));
		}
		for(String s:ingredientPossiblesPArAllergeneEvo.keySet()) {
			System.out.println(s+" "+ingredientPossiblesPArAllergeneEvo.get(s));
		}
		int res=0;
		for(Food f:foods) {
			for(String ing:f.ingredients) {
				if(!listeIngredientsCoupables.contains(ing)) {
					res++;
				}
			}
		}
		System.out.println("RES: "+res);
		listeIngredientsCoupables.stream().sorted().forEach(s->System.out.println(s));
		
		
		// System.out.println(totPasPArtout);
		/*
		 * int tot=0; for (Food f : foods) { for(String ing:f.ingredients) {
		 * if(ingSansAl.contains(ing)) { tot++; } } }
		 * System.out.println(ingSansAl.size()); System.out.println(tot);
		 */
		HashMap<String, HashSet<String>> IngredientALPossible = new HashMap<String, HashSet<String>>();
		for (String ing : listeIngredients) {
			if (!IngredientALPossible.containsKey(ing)) {
				HashSet<String> ListeAL = new HashSet<String>();
				IngredientALPossible.put(ing, ListeAL);
			}
			for (Food f : foods) {
				if (f.ingredients.contains(ing)) {
					IngredientALPossible.get(ing).addAll(f.allergenes);
				}
			}

			// System.out.println(ing+ " : "+IngredientALPossible.get(ing));

		}

	}

	private static List<Food> getFoods(String monFic) {
		BufferedReader buffer;
		List<Food> res = new ArrayList<>();
		try {
			buffer = new BufferedReader(new FileReader(monFic));
			String line;
			int i = 0;
			while (((line = buffer.readLine()) != null)) {

				List<String> ingredients = new ArrayList<>();
				List<String> allergenes = new ArrayList<>();
				line.replace("contains ", "");
				line.replace(")", "");
				String ing = line.split("@")[0];
				String[] ings = ing.split(" ");
				for (String s : ings) {
					ingredients.add(s);
				}

				String allerg = line.split("@")[1];
				allerg = allerg.replaceAll(" ", "");
				String[] allergs = allerg.split(",");
				for (String s : allergs) {
					allergenes.add(s);
				}

				res.add(new Food(i, ingredients, allergenes));
				i++;
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	private static List<String> getMessages(String monFic) {
		BufferedReader buffer;
		List<String> lignes = new ArrayList<>();
		boolean commencer = false;
		try {
			buffer = new BufferedReader(new FileReader(monFic));
			String line;
			while ((line = buffer.readLine()) != null) {
				if (commencer) {
					lignes.add(line.replaceAll("\\s+", ""));
				}
				if (line.contains("@")) {
					commencer = true;
				}

			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}

	public static List<String> getListStringUnParLigne(String path) {
		BufferedReader buffer;
		List<String> lignes = new ArrayList<>();
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			while ((line = buffer.readLine()) != null) {
				lignes.add(line.replaceAll("\\s+", ""));
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}
}
