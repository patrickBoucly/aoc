package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day23_clock {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		System.out.println("solve172 :" + solve232());

		long endTime = System.currentTimeMillis();

		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		
	}

	private static String solve232() {

		List<Integer> cups=new ArrayList<>();
		Integer nbMvtAFaire=100000;
		
		cups.addAll(Arrays.asList(1,8,6,5,2,4,9,7,3));
		Clock c=new Clock(cups);
		for(int j=10;j<=nbMvtAFaire;j++) {
			//System.out.println(j);
			c.cups.add(j);
		}
		
		
        
        long nbMvt=0;
        while(nbMvt<nbMvtAFaire) {
        	     if(nbMvt % 10000 ==0) {
        	    	 System.out.println(nbMvt);
        	     }
        List<Integer> pickedUp=c.pickUp();
        System.out.println(pickedUp);
        for(Integer i:pickedUp) {
        	cups.remove(i);
        }
        c.replacer(pickedUp);
     //  System.out.println("valActu avt :"+valActu);
        c.majValPosActu();
     //   System.out.println("valActu apres :"+valActu);
        nbMvt++;
        System.out.println("nbMvt :"+nbMvt);
      //  System.out.println();
        }
        c.afficherRes2(nbMvtAFaire);
		return "";
       
	}

	
	
}
