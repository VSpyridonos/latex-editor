package model;

public class Document {
	private String contents;
	private String type;
	
	public Document(String type, String contents) {
	    this.type = type;
	    this.contents = contents;
	}
	
	public Document(String contents) {
	    this.contents = contents;
	}

	public Document() {
		contents = "";
		type = "Empty";
		
	}

	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}	
}
