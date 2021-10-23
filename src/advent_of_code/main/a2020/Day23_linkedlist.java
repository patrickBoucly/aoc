package advent_of_code.main.a2020;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Day23_linkedlist {

	public static void main(String[] args) {

		// long startTime = System.currentTimeMillis();

		System.out.println("solve172 :" + solve232());

//		long endTime = System.currentTimeMillis();

		// System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}

	private static String solve232() {
		long startTime = System.currentTimeMillis();
		LinkedList<Integer> cups = new LinkedList<>();
		Integer nbMvtAFaire = 10000000;
		
		//cups.addAll(Arrays.asList(3,8,9,1,2,5,4,6,7));
		cups.addAll(Arrays.asList(1, 8, 6, 5, 2, 4, 9, 7, 3));
		for (int j = 10; j <= nbMvtAFaire; j++) {
			// System.out.println(j);
			cups.addLast(j);
		}
		//System.out.println(cups);
		LinkedList<Integer> pickedUp = new LinkedList<>();

		long nbMvt = 0;
		while (nbMvt < nbMvtAFaire) {
			
			if (nbMvt % 1000 == 0) {
				System.out.println(nbMvt);
				long endTime = System.currentTimeMillis();
				String sdf = new SimpleDateFormat("mm:ss:SSS").format(new Date(endTime - startTime));
				System.out.println("That took " + sdf);
			}
			System.out.println(nbMvt);
			//System.out.println("tour :"+nbMvt);
			cups.add(cups.poll());
			
			pickedUp = new LinkedList<>();
			while (pickedUp.size() < 3) {
				pickedUp.add(cups.poll());

			}
			Integer valPosDestination=getDestination(cups, pickedUp);
			
			cups = replacer(cups, pickedUp, valPosDestination);
			// System.out.println("valActu avt :"+valActu);
			
	
			nbMvt++;
			// System.out.println("nbMvt :"+nbMvt);
			// System.out.println();
		}
		afficherRes2(cups, nbMvtAFaire);
		return "";

	}

	private static void afficherRes2(List<Integer> cups, Integer nbMvt) {
		int posUn = 0;
		for (int i = 0; i <= nbMvt; i++) {
			if (cups.get(i) == 1) {
				posUn = i;
				
				break;
			}
		}
	}



	private static LinkedList<Integer> replacer(LinkedList<Integer> cups, LinkedList<Integer> pickedUp, Integer valPosDestination){
		cups.addAll(valPosDestination + 1, pickedUp);
		return cups;
	}

	

	private static Integer getDestination(LinkedList<Integer> cups, LinkedList<Integer> pickedUp) {
		Integer valeurCandidate = (cups.getLast() - 1);
		boolean trouve = false;
		while (!trouve) {
			if (valeurCandidate == 0) {
				List<Integer> cupsMax=Arrays.asList(10000000,9999999,9999998);
				cupsMax.removeAll(pickedUp);
				return cups.indexOf(Collections.max(cupsMax));
			} else if (cups.contains(valeurCandidate)) {
				return cups.indexOf(valeurCandidate);
			} else {
				valeurCandidate = valeurCandidate - 1;
			}
		}
		return null;
	}

	

}
