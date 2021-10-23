package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day23 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		System.out.println("solve172 :" + solve232());

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		
	}

	private static String solve232() {

		List<String> cups=new ArrayList<>();
		Integer nbMvtAFaire=100000;
		cups.addAll(Arrays.asList("1","8","6","5","2","4","9","7","3"));
		for(int j=10;j<=nbMvtAFaire;j++) {
			//System.out.println(j);
			cups.add(String.valueOf(j));
		}
		Integer nbElement=cups.size();
        int posActu=0;
        String valActu=cups.get(posActu);
        List<String> pickedUp;
        
        long nbMvt=0;
        while(nbMvt<nbMvtAFaire) {
        	     if(nbMvt % 10000 ==0) {
        	    	 System.out.println(nbMvt);
        	     }
        
        pickedUp=pickUp(cups,posActu);
     //   System.out.println("valeur actuelle :"+valActu);
    //    System.out.println("pickedUp :"+pickedUp);
        
        for(String i:pickedUp) {
        	cups.remove(i);
        }
        cups=replacer(cups,pickedUp,valActu,nbElement,posActu);
     //  System.out.println("valActu avt :"+valActu);
        valActu=selectNewValActu(posActu,nbElement,cups);
        posActu=selectNewPosActu(posActu,nbElement);
     //   System.out.println("valActu apres :"+valActu);
        nbMvt++;
      //  System.out.println("nbMvt :"+nbMvt);
      //  System.out.println();
        }
        afficherRes2(cups, nbMvtAFaire);
		return "";
       
	}

	private static void afficherRes2(List<String> cups,Integer nbMvt) {
		int posUn=0;
		for(int i=0;i<=nbMvt;i++) {
			if(cups.get(i).equals("1")) {
				posUn=i;
				System.out.println(cups.get(posUn+1));
				System.out.println(cups.get(posUn+2));
				break;
			}
		}
	}



	private static int selectNewPosActu(int posActu, Integer nbElement) {
		if(posActu==nbElement-1) {
			return 0;
		} else {
		return (posActu +1);
		}
	}

	private static String selectNewValActu(int posActu, Integer nbElement, List<String> cups) {
		if(posActu==nbElement-1) {
			return cups.get(0);
		} else {
		return cups.get(posActu +1);
		}
	}

	private static List<String> replacer(List<String> cups,List<String> pickedUp, String valActu, Integer nbElement, int posActu) {
		String destination=getDestination(cups,pickedUp,valActu);
		Integer positionDestination=getDestinationPosition(destination,cups,nbElement);
		
		
		
		//positionDestination=;
	/*
		cups.add(positionDestination+1, pickedUp.get(0));
		cups.add(positionDestination+2, pickedUp.get(1));
		cups.add(positionDestination+3, pickedUp.get(2));
		*/
		cups.addAll(positionDestination+1, pickedUp);
		cups=decalerCups(cups,posActu,valActu);
		return cups;
	}

	private static Integer getDestinationPosition(String destination, List<String> cups, Integer nbElement) {
		
		for( int i=0;i<nbElement-3;i++) {
			if(destination.equals(cups.get(i))) {
				return i;
			}
		}
		return null;
	}

	private static List<String> decalerCups(List<String> cups, int posActu, String valActu) {
		
		while(!cups.get(posActu).equals(valActu)){
			cups.add(cups.get(0));
			cups.remove(0);
		}
		return cups;
	}

	private static String getDestination(List<String> cupsRestantes,List<String> pickedUp, String valActu) {
		String valeurCandidate=String.valueOf((Integer.parseInt(valActu) -1));
		boolean trouve=false;
		while(!trouve) {
			if(valeurCandidate.equals("0")) {
				//System.out.println("destination :"+etiquetteMax(cupsRestantes));
				return String.valueOf(Collections.max(cupsRestantes));
			} else if(cupsRestantes.contains(valeurCandidate)) {
		//		System.out.println("destination :"+valeurCandidate);
				return valeurCandidate;
			} else {
				valeurCandidate=String.valueOf((Integer.parseInt(valeurCandidate) -1));
			}
		}
		return null;
	}

	

	private static List<String> pickUp(List<String> cups, int posActu) {
	//	System.out.println("cups "+cups);
	//	System.out.println("posactu "+posActu);
		Integer nbElement=cups.size();
		List<String> pickedUp= new ArrayList<>();
		while(pickedUp.size()<3) {
			if(posActu==nbElement-1) {
		//		System.out.println("add get(0)"+cups.get(0));
				pickedUp.add(cups.get(0));
				posActu=0;
			} else {
		//		System.out.println("add cups.get(posActu+1), posActu+1="+cups.get(posActu+1)+ " , "+posActu+1);
				
				pickedUp.add(cups.get(posActu+1));
				posActu=posActu+1;
			}
		}
		return pickedUp;
	}

}
