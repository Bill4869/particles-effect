package project;

public class person extends Data{
	String status;
	String language;


	public person (String name, String status, String language) {
		this.name = name;
		this.status = status;
		this.language = language;


	}

	@Override
	public String toString() {

		return status + " : " +language ;
	}

}
