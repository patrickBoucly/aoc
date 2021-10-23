package aoc2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day9 {
  public static void main(String[] args0) {
	  s1();
  }

private static void s1() {
	String input=read();
	System.out.println(input);
	
}

private static String read() {
	 Path path = Paths.get("C:\\Users\\patrick\\eclipse-workspace\\aoc2018\\input\\i9");
	 String content="";
     try {
         content = Files.readString(path);
     }catch (IOException e){
         e.printStackTrace();
     }
	return content;

}
}
