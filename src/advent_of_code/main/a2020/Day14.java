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

public class Day14 {

	public static void main(String[] args) {
		// System.out.println("solve141 :" + solve141("C:\\\\AOC\\\\input14"));
		// System.out.println("solve142 :" + solve142("input14_sample"));
		 System.out.println("solve142 :" + solve142("C:\\AOC\\input14"));
		// System.out.println("solve102 :" + solve142("input14"));
		

	}

	private static long solve142(String nomFic) {
		List<String> data = getListStringUnParLigne(nomFic);
		long tot = 0;
		
		ArrayList<Program> input = getInput(data);
		List<String> floatToWrite =new ArrayList<>();
		HashMap<Long,String> valeurEnMemoire = new HashMap<Long,String>();
		
		for (Program p : input) {
			floatToWrite =new ArrayList<>();
			floatToWrite.addAll(writeFloating(p, firstApplieResult(p)));
			
			for(String s:floatToWrite) {
				System.out.println(s);
				String valeur=String.valueOf(p.instructionValeur);
				long index =Long.parseLong(s,2);
				valeurEnMemoire.put(index,valeur);
			}
				
			
			// valeurEnMemoire[p.getInstructionPosMem()]=firstApplieResult(p);
		}
		
		for (Long v:valeurEnMemoire.keySet()) {

			tot=tot+Long.parseLong(valeurEnMemoire.get(v));
			
		}


		return tot;
	}

	private static List<String> writeFloating(Program p, String firstApplieResult) {
		List<String> res = new ArrayList<>();
		List<String> resTmp = res;
		String copie = firstApplieResult;
		String copie0 = firstApplieResult;
		String copie1 = firstApplieResult;
System.out.println("far :"+firstApplieResult);
		copie0 = copie.replaceFirst("X", "0");
		copie1 = copie.replaceFirst("X", "1");
		res.add(copie1);
		res.add(copie0);
		
		while (res.stream().anyMatch(s->s.contains("X"))) {
			for (int i = 0; i < res.size(); i++) {
				if (res.get(i).contains("X")) {
					//System.out.println("res.get(i): " + res.get(i));
					copie = res.get(i);
					copie0 = res.get(i);
					copie1 = res.get(i);
					resTmp.remove(i);
					copie0 = copie.replaceFirst("X", "0");
					copie1 = copie.replaceFirst("X", "1");
					resTmp.add(copie1);
					resTmp.add(copie0);

				}
				res = resTmp;
			}
			
			
		}
		return res;
	}

	private static String firstApplieResult(Program p) {
		String stringValueTmp = "000000000000000000000000000000000000"
				+ Integer.toBinaryString(p.getInstructionPosMem());
		String stringValue = stringValueTmp.substring(stringValueTmp.length() - 36);
		StringBuilder res = new StringBuilder();
		for (int cursor = 0; cursor < p.getMask().length(); cursor++) {
			if (p.getMask().substring(cursor, cursor + 1).equals("0")) {
				if (cursor != p.getMask().length() - 1) {
					res.append(stringValue.substring(cursor, cursor + 1));
				} else {
					res.append(stringValue.substring(cursor));
				}
			} else if (p.getMask().substring(cursor, cursor + 1).equals("X")) {
				res.append("X");
			} else {
				res.append("1");
			}
		}
		return res.toString();

	}

	private static long solve141(String nomFic) {
		List<String> data = getListStringUnParLigne(nomFic);

		ArrayList<Program> input = getInput(data);

		String[] valeurEnMemoire = new String[100000];
		for (int i = 0; i < 100000; i++) {
			valeurEnMemoire[i] = "0";
		}
		for (Program p : input) {
			valeurEnMemoire[p.getInstructionPosMem()] = getResult(p);
		}
		long tot = 0;
		for (String s : valeurEnMemoire) {
			tot += Long.parseLong(s, 2);
		}

		return tot;
	}

	private static String getResult(Program p) {
		String stringValueTmp = "000000000000000000000000000000000000"
				+ Integer.toBinaryString(p.getInstructionValeur());
		String stringValue = stringValueTmp.substring(stringValueTmp.length() - 36);
		StringBuilder res = new StringBuilder();
		for (int cursor = 0; cursor < p.getMask().length(); cursor++) {
			if (p.getMask().substring(cursor, cursor + 1).equals("X")) {
				if (cursor != p.getMask().length() - 1) {
					res.append(stringValue.substring(cursor, cursor + 1));
				} else {
					res.append(stringValue.substring(cursor));
				}
			} else if (p.getMask().substring(cursor, cursor + 1).equals("0")) {
				res.append("0");
			} else {
				res.append("1");
			}
		}
		return res.toString();
	}

	private static ArrayList<Program> getInput(List<String> data) {
		ArrayList<Program> res = new ArrayList<>();
		int pos = 0;
		int numOrdreInstruction = 1;
		String mask = "";
		int numProgram = 0;
		Program p = new Program();
		for (String line : data) {

			if (line.contains("mask")) {
				pos++;
				mask = line.substring(line.indexOf("=") + 1);
				numProgram = pos;
				numOrdreInstruction = 1;
			} else {
				p = new Program();

				p.setMask(mask);
				p.setNumProgram(numProgram);
				p.instructionPosMem = (Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]"))));
				p.instructionValeur = Integer.parseInt(line.substring(line.indexOf("=") + 1));
				p.setNumOrdreInstruction(numOrdreInstruction);
				// System.out.println("PNOI" + p.getNumOrdreInstruction());
				// System.out.println("NOI" + numOrdreInstruction);
				res.add(p);
				numOrdreInstruction++;

			}
		}
		return res;
	}

	static class Program {
		int instructionPosMem;
		int instructionValeur;
		String mask = "";
		int numProgram = 0;
		int numOrdreInstruction = 0;

		public int getNumProgram() {
			return numProgram;
		}

		public void setNumProgram(int numProgram) {
			this.numProgram = numProgram;
		}

		public int getNumOrdreInstruction() {
			return numOrdreInstruction;
		}

		public void setNumOrdreInstruction(int numOrdreInstruction) {
			this.numOrdreInstruction = numOrdreInstruction;
		}

		public Program(int instructionPosMem, int instructionValeur, String mask, int numProgram,
				int numOrdreInstruction) {
			super();
			this.instructionPosMem = instructionPosMem;
			this.instructionValeur = instructionValeur;
			this.mask = mask;
			this.numProgram = numProgram;
			this.numOrdreInstruction = numOrdreInstruction;
		}

		public int getInstructionPosMem() {
			return instructionPosMem;
		}

		public void setInstructionPosMem(int instructionPosMem) {
			this.instructionPosMem = instructionPosMem;
		}

		public int getInstructionValeur() {
			return instructionValeur;
		}

		public void setInstructionValeur(int instructionValeur) {
			this.instructionValeur = instructionValeur;
		}

		public String getMask() {
			return mask;
		}

		public void setMask(String mask) {
			this.mask = mask;
		}

		public Program() {
			// TODO Auto-generated constructor stub
		}

	}

	private static ArrayList<Program> getData(String nomFic) {
		Path path = Paths.get("C:\\AOC\\" + nomFic);
		FileReader fileReader;
		int pos = 0;
		int numOrdreInstruction = 1;
		ArrayList<Program> res = new ArrayList<>();
		Program p = new Program();
		try {
			fileReader = new FileReader(path.toString());
			BufferedReader bufferReader = new BufferedReader(fileReader);
			try {
				while (bufferReader.ready()) {
					String line = bufferReader.readLine().replaceAll("\\s+", "");
					if (line.contains("mask")) {
						pos++;
						p = new Program();
						p.setMask(line);
						p.setNumProgram(pos);
						numOrdreInstruction = 1;
					} else {
						p.instructionPosMem = (Integer
								.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]"))));
						p.instructionValeur = Integer.parseInt(line.substring(line.indexOf("=") + 1));
						p.setNumOrdreInstruction(numOrdreInstruction);

						res.add(p);
						numOrdreInstruction++;

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
