package advent_of_code.main.a2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Passport {
	Map<String, String> information=new HashMap<>();
	String byr;
	String iyr;
	String eyr;
	String hgt;
	String hcl;
	String ecl;
	String pid;
	String cid;
	boolean valide;
	public static final List<String> digitEclOK = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
	public static final List<String> digitHclOK = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e","f");
	public static final List<String> listeCleNecessaire = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
	
	public Passport(Map<String, String> passport) {
		for (String cle : passport.keySet()) {
			if (cle.equals("byr")) {
				this.setByr(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("iyr")) {
				this.setIyr(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("eyr")) {
				this.setEyr(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("hgt")) {
				this.setHgt(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("hcl")) {
				this.setHcl(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("ecl")) {
				this.setEcl(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("pid")) {
				this.setPid(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else if (cle.equals("cid")) {
				this.setCid(passport.get(cle));
				this.information.put(cle, passport.get(cle));
			} else {
				System.out.println("Clef inconnue!");
			}
			this.valide = isValid();
		}
	}

	public Passport() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getInformation() {
		return information;
	}

	public void setInformation(Map<String, String> information) {
		this.information = information;
	}

	public String getByr() {
		return byr;
	}

	public void setByr(String byr) {
		this.byr = byr;
	}

	public String getIyr() {
		return iyr;
	}

	public void setIyr(String iyr) {
		this.iyr = iyr;
	}

	public String getEyr() {
		return eyr;
	}

	public void setEyr(String eyr) {
		this.eyr = eyr;
	}

	public String getHgt() {
		return hgt;
	}

	public void setHgt(String hgt) {
		this.hgt = hgt;
	}

	public String getHcl() {
		return hcl;
	}

	public void setHcl(String hcl) {
		this.hcl = hcl;
	}

	public String getEcl() {
		return ecl;
	}

	public void setEcl(String ecl) {
		this.ecl = ecl;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public boolean isCorrectBYR() {
		boolean passportValide = true;
		try {
			Integer birthYear = Integer.parseInt(this.getByr());
			if (birthYear < 1920 || birthYear > 2002) {
				passportValide = false;
			}
		} catch (final NumberFormatException e) {
			passportValide = false;
		}
		return passportValide;
	}

	public boolean isCorrectIYR() {
		boolean passportValide = true;
		try {
			Integer iyr = Integer.parseInt(this.getIyr());
			if (iyr < 2010 || iyr > 2020) {
				passportValide = false;
			}
		} catch (final NumberFormatException e) {
			passportValide = false;
		}
		return passportValide;
	}

	public boolean isCorrectHGT() {
		boolean passportValide = true;
		String hgt = this.getHgt();
		if (hgt.length() < 4) {
			return false;
		}
		String unite = hgt.substring(hgt.length() - 2);
		String valeur = hgt.substring(0, hgt.length() - 2);

		if (unite.equals("cm")) {
			try {
				Integer valeurTaille = Integer.parseInt(valeur);
				if (valeurTaille < 150 || valeurTaille > 193) {
					passportValide = false;
				}
			} catch (final NumberFormatException e) {
				passportValide = false;
			}
		} else if (unite.equals("in")) {
			try {
				Integer valeurTaille = Integer.parseInt(valeur);
				if (valeurTaille < 59 || valeurTaille > 76) {
					passportValide = false;
				}
			} catch (final NumberFormatException e) {
				passportValide = false;
			}
		} else {
			passportValide = false;
		}
		return passportValide;
	}

	public boolean isCorrectEYR() {
		boolean passportValide = true;
		try {
			Integer eyr = Integer.parseInt(this.getEyr());
			if (eyr < 2020 || eyr > 2030) {
				passportValide = false;
			}
		} catch (final NumberFormatException e) {
			passportValide = false;
		}
		return passportValide;
	}

	public boolean isCorrectHCL() {
		boolean passportValide = true;
		String hcl = this.getHcl();
		if(hcl !=null){
		if (!hcl.subSequence(0, 1).equals("#") || hcl.length() != 7) {
			passportValide = false;
		}
		if (passportValide) {
			for (int pos = 1; pos < 7; pos++) {
				if (!digitHclOK.contains(hcl.substring(pos, pos + 1))) {
					passportValide = false;
				}
			}

		}
		} else {
			return false;
		}
		return passportValide;
	}

	public boolean isCorrectECL() {
		boolean passportValide = true;
		
		String ecl = this.getEcl();
		if (!digitEclOK.contains(ecl)) {
			passportValide = false;
		}

		return passportValide;
	}

	public boolean isCorrectPID() {
		boolean passportValide = true;
		String pid = this.getPid();
		if(pid !=null){
		if (pid.length() != 9) {
			passportValide = false;
		}
		try {
			Integer.parseInt(pid);
		} catch (final NumberFormatException e) {
			passportValide = false;
		}
		} else {
			passportValide = false;	
		}
		return passportValide;
	}

	public boolean isValid() {
		boolean aLesCleNecessaires=this.information.keySet().containsAll(listeCleNecessaire);
		boolean valeursCorrectes =(isCorrectBYR() && isCorrectEYR() && isCorrectIYR() && isCorrectHCL() && isCorrectHGT() && isCorrectPID()
				&& isCorrectECL());
		return (aLesCleNecessaires && valeursCorrectes );

	}
}
