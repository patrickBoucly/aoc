package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day20 {
	public static void main(String[] args) {
		// System.out.println("solve171 :" + solve171("input17"));
		// System.out.println("solve172 :" + solve172("input17"));

		// System.out.println("solve181 :" + solve181("C:\\AOC\\input18",1));
		//solve201("C:\\AOC\\input20_sample");
		List<Piece> pieces=getPieces("C:\\AOC\\input20");
		BigInteger res=BigInteger.valueOf(1);
		for(Piece p:pieces) {
			if(p.nbv(p, pieces) ==4) {
			System.out.println(p.id+"  "+p.bordsCompatible(p, pieces));
			Integer integer = Integer.valueOf(p.id);
			res=res.multiply(BigInteger.valueOf(integer.intValue()));
			}	
		}
		System.out.println(res);
		
		
		
		
		
	}
/*
	private static void //solve201(String string) {
		// TODO Auto-generated method stub
		
	}
*/
	

	public static List<Piece> getPieces(String path) {
		BufferedReader buffer;
		List<Piece> pieces = new ArrayList<>();
		List<Pixel> image = new ArrayList<>();
		int x=0;
		int id=0;
		try {
			buffer = new BufferedReader(new FileReader(path));
			String line;
			line = buffer.readLine();
			while (!line.equals("$")) {
				if(line.equals("@")) {
					pieces.add(new Piece(id,image));
				} else if(line.substring(0,2).equals("Ti")) {
					id=Integer.parseInt(line.substring(5,9));
					image = new ArrayList<>();
					x=1;
				} else {
					for(int j=0;j<line.length();j++) {
						if(line.substring(j, j+1).equals("#")) {
							image.add(new Pixel(x,j+1));
						}
					}
					x++;
				}
				line = buffer.readLine();
				}
			
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pieces;
	}
}
