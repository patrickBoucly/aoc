package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day24 {

	public static void main(String[] args) {
		HashMap<String,Hexa> planche = new HashMap<>();
		Sol s = new Sol(planche);
		
		List<String> instructions=getListStringUnParLigne("C:\\AOC\\input24");
		for(String inst:instructions) {
			s.modifierPlanche(inst);
		}
		System.out.println(s.planche.keySet().size());
		int tot=0;
		
		for(int j=0;j<110;j++) {
			
			System.out.println("jour "+j+ ": "+s.planche.values().stream().filter(x->x.isReturn).count());
			
			s=s.passerUnJour(s);
		}
		
		
		System.out.println("fin "+s.planche.values().stream().filter(x->x.isReturn).count());
       
	}
	
	public static List<String> getListStringUnParLigne(String path) {
		BufferedReader buffer;
		List<String> lignes = new ArrayList<>();
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			while ((line = buffer.readLine()) != null) {
				lignes.add(line);
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lignes;
	}

}
