package advent_of_code.main.a2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intcodecomputer {
	List<Integer> intCode = new ArrayList<>();

public List<Integer> getIntCode() {
	return intCode;
}

public void setIntCode(List<Integer> intCode) {
	List<Integer> monIntCode = new ArrayList<>();
	for(int i=0;i<intCode.size();i++) {
		monIntCode.add(i);
	}
	this.intCode = monIntCode;
}

public Intcodecomputer(List<Integer> intCode) {
	this.setIntCode(intCode);
}
public static ArrayList<Integer> analyseIntCode(List<Integer> list) {
	ArrayList<Integer> listI = new ArrayList<>();
	for(Integer i:list) {
		listI.add(i);
	}
	int opcode = listI.get(0);
	for (int pos = 0; pos < listI.size() - 4; pos = pos + 4) {
		opcode = listI.get(pos);
		if (opcode == 1) {
			listI.set(listI.get(pos + 3), listI.get(listI.get(pos + 2)) + listI.get(listI.get(pos + 1)));
		} else if (opcode == 2) {
			listI.set(listI.get(pos + 3), listI.get(listI.get(pos + 2)) * listI.get(listI.get(pos + 1)));
		} else if (opcode == 99) {
			return listI;
		} else {
			return null;
		}
	}
	return listI;
	
	
}

}
