package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.LatexEditorController;


class TestUS1 {
	
	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		assertEquals(latexEditorController.newDocumentCommand("Report"), latexEditorController.getDocumentManager().getMap().get("Report"));
		assertEquals(latexEditorController.newDocumentCommand("Book"), latexEditorController.getDocumentManager().getMap().get("Book"));
		assertEquals(latexEditorController.newDocumentCommand("Article"), latexEditorController.getDocumentManager().getMap().get("Article"));
		assertEquals(latexEditorController.newDocumentCommand("Letter"), latexEditorController.getDocumentManager().getMap().get("Letter"));
	}

}
