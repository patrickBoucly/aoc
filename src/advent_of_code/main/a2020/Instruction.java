package advent_of_code.main.a2020;

public class Instruction {
String command;
Integer accu;
public Integer getPosition() {
	return position;
}

public void setPosition(Integer position) {
	this.position = position;
}
Integer position;
public Instruction(String command, Integer accu,Integer position) {
	super();
	this.command = command;
	this.accu = accu;
	this.position=position;
}

public String getCommand() {
	return command;
}
public void setCommand(String command) {
	this.command = command;
}
public Integer getAccu() {
	return accu;
}
public void setAccu(Integer accu) {
	this.accu = accu;
}
}
