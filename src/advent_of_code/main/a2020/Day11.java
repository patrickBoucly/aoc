package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

	public static void main(String[] args) {
	//	 System.out.println("solve111 :" + solve111("input11_sample"));
		System.out.println("solve112 :" + solve112("input11_sample"));
	//	 System.out.println("solve111 :" + solve111("input11"));
	//		System.out.println("solve112 :" + solve112("input11"));

	}



	
	private static int solve112(String nomFic) {
		List<Emplacement> emplacements=fromStringToEmplacement(getData(nomFic));
		
		//emplacements=resoudre2(resoudre2(emplacements));
		
		emplacements=stabiliser2(emplacements);
		int nbLigne=91;
		int nbCol=95;
		
		if(emplacements.size() == 100) {
			nbLigne=10;
			nbCol=10;
		}
		String[][] tab=new String[nbLigne][nbCol];
		for(Emplacement e:emplacements) {
			
			tab[e.colonne][e.ligne]=e.etat;
		}
		for(int j=0;j<nbCol;j++) {
			System.out.println(tab[0][j]);
		}
		System.out.println((new Emplacement(1, 0, tab[0][1]).getNbVisibles(emplacements)));
		System.out.println(tab[0][1]);
		Plan p = new Plan(emplacements);
		p.afficher();
	
		
		
		
	//Plan p = new Plan(emplacements);
	//p.afficher();
	return compteOccupes(emplacements);

	
	}




	private static List<Emplacement> stabiliser2(List<Emplacement> emplacements) {
				
		    	List<Emplacement> s1= resoudre2(emplacements);
				List<Emplacement> s2=resoudre2(s1);
				List<Emplacement> s3=resoudre2(s2);
				int nbX=compteOccupes(s1);
				int nbY=compteOccupes(s2);
				int nbZ=compteOccupes(s3);
				while(nbX != nbY && nbX != nbZ) {
					s1= resoudre2(s3);
					s2=resoudre2(s1);
					s3=resoudre2(s2);
					nbX=compteOccupes(s1);
					nbY=compteOccupes(s2);
					nbZ=compteOccupes(s3);
				}

				return s3;
			}
	




	private static List<Emplacement> resoudre2(List<Emplacement> emplacementsInitiaux) {
		List<Emplacement> res= new ArrayList<>();
		for(Emplacement e: emplacementsInitiaux) {
			res.add(new Emplacement(e.ligne, e.colonne, e.etat));
		}
		for(Emplacement e:res) {
			
			
			if(e.getEtat().equals("L") && e.getNbVisibles(emplacementsInitiaux) == 0) {
				e.setEtat("#");
			} else if(e.getEtat().equals("#") && e.getNbVisibles(emplacementsInitiaux)>3) {
				
				e.setEtat("L");
			}
		}
		return res;
	}
	




	private static Integer solve111(String nomFic) {
		List<Emplacement> emplacements=fromStringToEmplacement(getData(nomFic));
	    emplacements=stabiliser(emplacements);
		Plan p = new Plan(emplacements);
		p.afficher();
		return compteOccupes(emplacements);
	}


	private static List<Emplacement> stabiliser(List<Emplacement> emplacementsInitiaux) {

	//	List<Emplacement> s1= resoudre(emplacementsInitiaux);
	//	List<Emplacement> s2=resoudre(s1);
		
    	List<Emplacement> s1= resoudre(emplacementsInitiaux);
		List<Emplacement> s2=resoudre(s1);
		List<Emplacement> s3=resoudre(s2);
		int nbX=compteOccupes(s1);
		int nbY=compteOccupes(s2);
		int nbZ=compteOccupes(s3);
		while(nbX != nbY && nbX != nbZ) {
			s1= resoudre(s3);
			s2=resoudre(s1);
			s3=resoudre(s2);
			nbX=compteOccupes(s1);
			nbY=compteOccupes(s2);
			nbZ=compteOccupes(s3);
		}
	
	//	for(int i=0;i<50;i++) {
	//		s1= resoudre(s2);
	//		s2=resoudre(s1);
	//	}
		return s2;
	}




	private static int compteOccupes(List<Emplacement> s1) {
		
		return (int) s1.stream().filter(x->x.getEtat().equals("#")).count();
	}




	private static List<Emplacement> resoudre(List<Emplacement> emplacementsInitiaux) {
		List<Emplacement> res= new ArrayList<>();
		for(Emplacement e: emplacementsInitiaux) {
			res.add(new Emplacement(e.ligne, e.colonne, e.etat));
		}
		for(Emplacement e:res) {
			if(e.getEtat().equals("L") && e.getNbVoisins(emplacementsInitiaux) == 0) {
				e.setEtat("#");
			} else if(e.getEtat().equals("#") && e.getNbVoisins(emplacementsInitiaux)>3) {
				e.setEtat("L");
			}
		}
		
		return res;
	}




	private static List<Emplacement> fromStringToEmplacement(Map<Integer,String> input) {
		List<Emplacement> res= new ArrayList<>();
		String ligne;
		for(Integer numLigne:input.keySet()) {
			ligne=input.get(numLigne);
			for(int i=0;i<ligne.length();i++) {
				res.add(new Emplacement(numLigne, i, ligne.substring(i,i+1)));
			}
		}
		
		return res;
	}


	private static Map<Integer,String> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\"+nomFic);
		FileReader fileReader;
		Map<Integer,String> res =new HashMap<Integer,String>();
		
		Integer numLigne=0;
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				
				while (bufferReader.ready()) {
					res.put(numLigne,bufferReader.readLine());
                		   numLigne++;

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
