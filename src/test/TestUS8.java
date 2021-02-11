package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.Document;

class TestUS8 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		Document document = new Document("Report", latexEditorController.newDocumentCommand("Report"));
		latexEditorController.saveAsCommand(document.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS8\\TestSaveFile.tex");
		assertEquals(document.getContents(), latexEditorController.loadDocumentCommand("C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS8\\TestSaveFile.tex"));
	}

}
