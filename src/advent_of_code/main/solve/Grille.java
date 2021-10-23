
package advent_of_code.main.solve;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Grille {
	
	private List<List<String>> datas;	

	public Grille(List<List<String>> datas) {
		this.datas = datas;
	}


	public Optional<String> get(int i, int j) {
		if(i<0 || j < 0 || i>=datas.size() || j>= datas.get(i).size() || datas.get(i).get(j).equals(".")) {
			return Optional.empty();
		}
		return Optional.of(datas.get(i).get(j));
	}
	
	public int getHeight() {
		return datas.size();
	}
	
	public int getWidth() {
		return datas.get(0).size();
	}
	
	public void setDatas(List<List<String>> datas) {
		this.datas = datas;
	}

	public long nbOccupied() {
		return datas.stream().flatMap(List::stream).filter(s -> s.equals("#")).count();
	}

	public void run() {
		boolean move = true;
		while(move) {
			List<List<String>> newDatas = new ArrayList<>();
			move = false;
			for(int i = 0; i < getHeight(); i++) {
				List<String> ligne = new ArrayList<>();
				for(int j =0; j< getWidth(); j++) {
					String seat = get(i,j).orElse(".");
					if(seat.equals("L") && haveToSit(i, j)) {
						move = true;
						ligne.add("#");
					}else if(seat.equals("#") && haveToLeave(i, j)) {
						move = true;
						ligne.add("L");
					}
					else {			
						ligne.add(seat);
					}
				}
				newDatas.add(ligne);
			}
			setDatas(newDatas);
		}
	}
	
	public abstract boolean haveToLeave(int i, int j);

	public abstract boolean haveToSit(int i, int j) ;

}

    

