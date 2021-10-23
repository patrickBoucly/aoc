package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Piece {
	int id;
	List<Pixel> image;
	Map<String,List<Integer>> voisinage;
	boolean flip=false;
	int rot=0;
    public Piece(Piece p) {
    	this.id=p.id;
    	this.image=p.image;
    	this.voisinage=p.voisinage;
    	this.flip=p.flip;
    	this.rot=p.rot;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Pixel> getImage() {
		return image;
	}

	public void setImage(List<Pixel> image) {
		this.image = image;
	}

	public Piece(int id, List<Pixel> image) {
		super();
		this.id = id;
		this.image = image;
	}

	public void rotation() {
		ArrayList<Pixel> newImage = new ArrayList<>();
		for (Pixel p : image) {
			newImage.add(rotationPixel(p));
		}
		image = newImage;
		rot++;
	}

	private Pixel rotationPixel(Pixel p) {
		return new Pixel(11 - p.getY(), p.getX());
	}

	public void flipNS() {
		ArrayList<Pixel> newImage = new ArrayList<>();
		for (Pixel p : image) {
			newImage.add(filpPixel(p));
		}
		image = newImage;
		flip=!flip;
	}

	private Pixel filpPixel(Pixel p) {
		return new Pixel(11-p.getX(), p.getY());
	}

	public void afficher() {
		System.out.println("Tile "+id+":");
		for (int x = 1; x < 11; x++) {
			StringBuilder line = new StringBuilder();
			for (int y = 1; y < 11; y++) {
				if (image.contains(new Pixel(x, y))) {
					line.append("#");
				} else {
					line.append(".");
				}
			}
			System.out.println(line);
		}
	}
	
	public Map<String,String> infoBord(){
		HashMap<String,String> infoBord = new HashMap<>();
		StringBuilder lineN = new StringBuilder();
		StringBuilder lineS = new StringBuilder();
		StringBuilder lineW = new StringBuilder();
		StringBuilder lineE = new StringBuilder();
		for (int y = 1; y < 11; y++) {
			if (image.contains(new Pixel(1, y))) {
				lineN.append("1");
			} else {
				lineN.append("0");
			}
			if (image.contains(new Pixel(10, y))) {
				lineS.append("1");
			} else {
				lineS.append("0");
			}
		}
		
		for (int x = 1; x < 11; x++) {
			if (image.contains(new Pixel(x, 1))) {
				lineW.append("1");
			} else {
				lineW.append("0");
			}
			if (image.contains(new Pixel(x, 10))) {
				lineE.append("1");
			} else {
				lineE.append("0");
			}
		}
		infoBord.put("N",lineN.toString());
		infoBord.put("E",lineE.toString());
		infoBord.put("W",lineW.toString());
		infoBord.put("S",lineS.toString());
		return infoBord;
	}
	
	public Map<String,List<Integer>> bordsCompatible(Piece p,List<Piece> pieces) {
		List<Integer> LN = new ArrayList<>();
		List<Integer> LE = new ArrayList<>();
		List<Integer> LW = new ArrayList<>();
		List<Integer> LS = new ArrayList<>();
		Map<String,List<Integer>> res= new HashMap<>();
		for(Piece autrePiece:pieces) {
			if(p.id != autrePiece.id) {
				LN.addAll(bordsCompatible(p,autrePiece).get("N"));
				LE.addAll(bordsCompatible(p,autrePiece).get("E"));
				LW.addAll(bordsCompatible(p,autrePiece).get("W"));
				LS.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LN.addAll(bordsCompatible(p,autrePiece).get("N"));
				LE.addAll(bordsCompatible(p,autrePiece).get("E"));
				LW.addAll(bordsCompatible(p,autrePiece).get("W"));
				LS.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LN.addAll(bordsCompatible(p,autrePiece).get("N"));
				LE.addAll(bordsCompatible(p,autrePiece).get("E"));
				LW.addAll(bordsCompatible(p,autrePiece).get("W"));
				LS.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LN.addAll(bordsCompatible(p,autrePiece).get("N"));
				LE.addAll(bordsCompatible(p,autrePiece).get("E"));
				LW.addAll(bordsCompatible(p,autrePiece).get("W"));
				LS.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				autrePiece.flipNS();
				LS.addAll(bordsCompatible(p,autrePiece).get("N"));
				LW.addAll(bordsCompatible(p,autrePiece).get("E"));
				LE.addAll(bordsCompatible(p,autrePiece).get("W"));
				LN.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LS.addAll(bordsCompatible(p,autrePiece).get("N"));
				LW.addAll(bordsCompatible(p,autrePiece).get("E"));
				LE.addAll(bordsCompatible(p,autrePiece).get("W"));
				LN.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LS.addAll(bordsCompatible(p,autrePiece).get("N"));
				LW.addAll(bordsCompatible(p,autrePiece).get("E"));
				LE.addAll(bordsCompatible(p,autrePiece).get("W"));
				LN.addAll(bordsCompatible(p,autrePiece).get("S"));
				autrePiece.rotation();
				LS.addAll(bordsCompatible(p,autrePiece).get("N"));
				LW.addAll(bordsCompatible(p,autrePiece).get("E"));
				LE.addAll(bordsCompatible(p,autrePiece).get("W"));
				LN.addAll(bordsCompatible(p,autrePiece).get("S"));
			}
		}
		res.put("N",LN);
		res.put("E",LE);
		res.put("W",LW);
		res.put("S",LS);
		return res;
	}
	
	
	
	private Map<String,List<Integer>> bordsCompatible(Piece p, Piece autrePiece) {
		List<Integer> LN = new ArrayList<>();
		List<Integer> LE = new ArrayList<>();
		List<Integer> LW = new ArrayList<>();
		List<Integer> LS = new ArrayList<>();
		 Map<String,List<Integer>> res= new HashMap<>();
		HashMap<String,String> infoBordP1 = (HashMap<String, String>) p.infoBord();
		HashMap<String,String> infoBordP2 =  (HashMap<String, String>) autrePiece.infoBord();
		if(infoBordP1.get("N").equals(infoBordP2.get("S"))) {
			LN.add(autrePiece.getId());
		}
		if(infoBordP1.get("S").equals(infoBordP2.get("N"))) {
			LS.add(autrePiece.getId());
		}
		if(infoBordP1.get("E").equals(infoBordP2.get("W"))) {
			LE.add(autrePiece.getId());
		}
		if(infoBordP1.get("W").equals(infoBordP2.get("E"))) {
			LW.add(autrePiece.getId());
		}
		res.put("N",LN);
		res.put("E",LE);
		res.put("W",LW);
		res.put("S",LS);
		return res;
	}
	public int nbv(Piece p, List<Piece> pieces) {
		Map<String,List<Integer>> bordsCompatibles =p.bordsCompatible(p, pieces);
		int res=0;
		for(String dir:bordsCompatibles.keySet()) {
		res+=bordsCompatibles.get(dir).size();
		}
		return res;
	}
	
}
