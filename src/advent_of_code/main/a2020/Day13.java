package advent_of_code.main.a2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day13 {

	public static void main(String[] args) {
		// System.out.println("solve131 :" + solve131("input13_a"));
	//	System.out.println("solve132 :" + solve132("input13_sample"));
		// System.out.println("solve102 :" + solve102("input10_sample2"));
	System.out.println("solve102 :" + solve132("input13"));

	}
	

	private static BigInteger solve132(String nomFic) {
		Map<Integer, Integer> input = getData2(nomFic);
		System.out.println(input);
		
		
		boolean trouve=false;
		BigInteger test=BigInteger.valueOf(0);
		
		test=test.add(new BigInteger("402251700158076"));
		//test=403649648146897;
		///////454138575711491
		//apres 50829210686943
		System.out.println("coef :"+new BigInteger("427048630545343").divide(new BigInteger("215441")));
		System.out.println("coef VERIF: "+BigInteger.valueOf(50232).add(new BigInteger("1982206871").multiply(new BigInteger("215441"))));
		
		System.out.println(test);
		/*
		List<BigInteger> e1 = new ArrayList<>();
		List<BigInteger> e2 = new ArrayList<>();
		List<BigInteger> e3 = new ArrayList<>();
		List<BigInteger> e4 = new ArrayList<>();
		for(int i=0;i<600000;i=i+23) {
			
			//e1.add(new BigInteger(String.valueOf((23*(i+1)))));
			e2.add(new BigInteger(String.valueOf((431*(i+1)-23))));
			e3.add(new BigInteger(String.valueOf((409*(i+1)-54))));
		//	e4.add(new BigInteger(String.valueOf((29*(i+1)-83))));
		}
		for(BigInteger i:e2) {
			if(e3.contains(i) ) {
			System.out.println("i commun"+i);
			}
		} //&& test.compareTo(new BigInteger("1068783"))<0
		*/
		while(!trouve &&test.compareTo(new BigInteger("402251700158078"))<0) {
			test=test.add(BigInteger.valueOf(1));
			System.out.println(test);
			boolean candidat=true;
			//System.out.println("test :"+test);
			for(Integer numBus:input.keySet()) {
			System.out.println("key:"+numBus+" ok:"+(calculerDistance(numBus,test).equals(BigInteger.ZERO)));
			System.out.println("fonction :"+calculerDistance(numBus,test));
			System.out.println("input :"+input.get(numBus));
			if(!(calculerDistance(numBus,test).equals(new BigInteger(input.get(numBus).toString())))) {
					candidat=false;
				}
			}
			//System.out.println(candidat);
			if(candidat) {
				trouve=true;
				System.out.println("trouvé!!");
			}
		}
		
		return test;
	}

	private static BigInteger calculerDistance(Integer numBus, BigInteger test) {
		System.out.println("test mod:"+test.mod(new BigInteger(numBus.toString())));
		
		if(test.mod(new BigInteger(numBus.toString())).equals(BigInteger.ZERO)) {
			System.out.println(0);
			return BigInteger.ZERO;
		}
		return ((new BigInteger(numBus.toString())).subtract(test.mod(new BigInteger(numBus.toString())))) ;
		
	}

	private static int solve131(String nomFic) {
		Map<Integer, ArrayList<Integer>> input = getData(nomFic);
		Integer monChiffre = 0;
		for (Integer i : input.keySet()) {
			monChiffre = i;
		}
		System.out.println(getData(nomFic));
		Map<Integer, Integer> ligneDistance = new HashMap<>();
		for (Integer numBus : input.get(monChiffre)) {
			ligneDistance.put(numBus, calculerDistance(numBus, monChiffre));
		}
		Integer min = 1000;
		Integer indice = -1;
		for (Integer key : ligneDistance.keySet()) {
			if (ligneDistance.get(key) < min) {
				indice = key;
				min = ligneDistance.get(key);
			}
		}

		return min * indice;
	}

	private static Integer calculerDistance(Integer numBus, Integer monChiffre) {
		if(monChiffre%numBus==0) {
			return 0;
		}
		return (numBus - (monChiffre % numBus)) ;
	}

	private static Map<Integer, ArrayList<Integer>> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		Map<Integer, ArrayList<Integer>> res = new HashMap<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			int numL = 0;
			Integer key = 0;
			ArrayList<Integer> value = new ArrayList<Integer>();
			String line = "";
			try {
				while (bufferReader.ready()) {
					if (numL == 0) {
						key = Integer.parseInt(bufferReader.readLine());
						numL++;
					} else {
						line = bufferReader.readLine();

						String chiffres[] = line.split(",");
						for (String c : chiffres) {
							value.add(Integer.parseInt(c));
						}
					}
					res.put(key, value);
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

	private static Map<Integer, Integer> getData2(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;

		Map<Integer, Integer> res = new HashMap<>();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			String[] infos = null;
			try {
				while (bufferReader.ready()) {
					infos = bufferReader.readLine().split((","));
					int i = 0;
					for (String s : infos) {
						if (!s.equals("x")) {
							res.put(Integer.parseInt(s), i);
						}
						i++;
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
