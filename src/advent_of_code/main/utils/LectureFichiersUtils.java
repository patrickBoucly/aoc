package advent_of_code.main.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LectureFichiersUtils {

  private static final String RESOURCES = "C:\\AOC";
  
  public static Stream<String> getData(String file){
    Path p = Paths.get(RESOURCES, file);
    try {
      return Files.lines(p);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
 
  
}