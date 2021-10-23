package advent_of_code.main.solve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

	public static void main(String[] args) {
		 part2();

	}
	
	private static void part2() {
		
        List<String> data = getListStringUnParLigne("C:\\AOC\\input13");
        List<Bus> buses = getFrom(data.get(0).split(",")).stream().collect(Collectors.toList());
        long timestamp = 0L;
        long timestampStepSize = buses.get(0).frequecy;
        for (int i = 1; i < buses.size(); i++) {
            while (true) {
                timestamp += timestampStepSize;
                if ((timestamp + buses.get(i).offsetWanted) % buses.get(i).frequecy == 0) {
                    timestampStepSize *= buses.get(i).frequecy;
                    break;
                }
            }
        }
        System.out.println(timestamp);
    }

    static List<Bus> getFrom(String[] buses) {
        List<Bus> list = new ArrayList<>();
        int offset = 0;
        for (String b : buses) {
            if (!b.equals("x")) {
                Bus bus = new Bus();
                bus.frequecy = Integer.parseInt(b);
                bus.offsetWanted = offset;
                list.add(bus);
            }
            offset++;
        }
        return list;
    }

    static class Bus {
        int frequecy;
        int offsetWanted;
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
