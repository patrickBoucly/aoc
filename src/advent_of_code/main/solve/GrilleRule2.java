package advent_of_code.main.solve;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GrilleRule2 extends Grille {

	public GrilleRule2(List<List<String>> datas) {
		super(datas);
	}

	@Override
	public boolean haveToLeave(int i, int j) {
		return nbVisible(i,j) > 4;
	}

	@Override
	public boolean haveToSit(int i, int j) {
		return nbVisible(i,j) == 0;
	}
	
	private long nbVisible(int i, int j) {
		String[] visibles = new String[8];
	
		for(int k = 1; k < getHeight(); k++) {
			visibles[0] = visibles[0] != null ? visibles[0] : get(i-k, j).orElse(null);	
			visibles[1] = visibles[1] != null ? visibles[1] : get(i+k, j).orElse(null);
			visibles[2] = visibles[2] != null ? visibles[2] : get(i, j-k).orElse(null);
			visibles[3] = visibles[3] != null ? visibles[3] : get(i, j+k).orElse(null);
			visibles[4] = visibles[4] != null ? visibles[4] : get(i-k, j-k).orElse(null);
			visibles[5] = visibles[5] != null ? visibles[5] : get(i-k, j+k).orElse(null);
			visibles[6] = visibles[6] != null ? visibles[6] : get(i+k, j+k).orElse(null);
			visibles[7] = visibles[7] != null ? visibles[7] : get(i+k, j-k).orElse(null);			
		}
		return Arrays.asList(visibles).stream()
				.filter(Objects::nonNull)
				.filter(s -> s.equals("#"))
				.count()
				;
		
	}

}