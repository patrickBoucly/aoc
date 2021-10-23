package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day12 {

	public static void main(String[] args) {
	//	 System.out.println("solve121 :" + solve121("input12_sample"));
	//	System.out.println("solve122 :" + solve122("input12_sample"));
	//	 System.out.println("solve121 :" + solve121("input12"));
			System.out.println("solve122 :" + solve122("input12"));

	}



	
	private static String solve122(String nomFic) {
		ArrayList<Deplacement> deplacements= getData(nomFic);
		ShipWayPoint s= new ShipWayPoint(0,0,10,1);
		for(Deplacement d:deplacements) {
			s.deplacer(d);
			System.out.println();
			System.out.println(d);
			System.out.println(s);
			System.out.println();
		}
		Integer res=Math.abs(s.latitudeShip)+Math.abs(s.longitudeShip);
		System.out.println(res);
		return "ok";
	}




	private static String solve121(String nomFic) {
		ArrayList<Deplacement> deplacements= getData(nomFic);
		Ship s= new Ship(0,0,"E");
		for(Deplacement d:deplacements) {
			s.deplacer(d);
			System.out.println();
			System.out.println(d);
			System.out.println(s);
			System.out.println();
		}
		Integer res=Math.abs(s.latitude)+Math.abs(s.longitude);
		System.out.println(res);
		return "ok";
	}




	private static ArrayList<Deplacement> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\"+nomFic);
		FileReader fileReader;
		ArrayList<Deplacement> res =new ArrayList<Deplacement>();
		
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				
				while (bufferReader.ready()) {
					String line=bufferReader.readLine();
					res.add(new Deplacement(line.subSequence(0, 1).toString(),Integer.parseInt(line.substring(1))));
                		   

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
