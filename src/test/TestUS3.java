package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;

class TestUS3 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		Document document = new Document("Report", latexEditorController.getDocumentManager().createDocument("Report"));
		String oldContents = document.getContents();
		document.setContents(latexEditorController.addLatexCommand("Chapter", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Section", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Subsection", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Subsubsection", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Unordered List", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Ordered List", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Table", "Report"));
		assertNotEquals(oldContents, document.getContents());
		document.setContents(latexEditorController.addLatexCommand("Figure", "Report"));
		assertNotEquals(oldContents, document.getContents());
	}

}
