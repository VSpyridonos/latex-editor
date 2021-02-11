package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;

class TestUS2 {

	@Test
	final void test() {
		
		LatexEditorController latexEditorController = new LatexEditorController();
		Document document = new Document("Report", latexEditorController.newDocumentCommand("Report"));
		String oldContents = document.getContents();
		document.setContents("Random Text");
		assertNotEquals(oldContents, document.getContents());
	}

}
