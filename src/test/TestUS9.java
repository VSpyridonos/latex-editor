package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;

class TestUS9 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		String reportDocumentContents = latexEditorController.loadDocumentCommand("TestUS9LoadFile.tex");
		assertEquals(latexEditorController.newDocumentCommand("Report"), reportDocumentContents);
	}

}
