package project;

public class language extends Data{
	
	String description;
	
	public language(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder bd = new StringBuilder();
		bd.append(description);
		return bd.toString();
	}








}
