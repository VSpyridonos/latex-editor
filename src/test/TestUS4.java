package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.LatexEditorController;
import model.Document;

class TestUS4 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		Document document = new Document("Report", latexEditorController.newDocumentCommand("Report"));
		String oldContents = document.getContents();
		latexEditorController.commitChangesCommand(oldContents, "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS4");
		document.setContents("Random text");
		latexEditorController.commitChangesCommand(document.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS4");
		assertEquals(oldContents, latexEditorController.goToPreviousVersion());
		
		latexEditorController.changeVersionStrategyCommand();
		latexEditorController.getVersionsManager().getVersions().clear();
		Document document2 = new Document("Report", latexEditorController.newDocumentCommand("Report"));
		oldContents = document2.getContents();
		latexEditorController.commitChangesCommand(oldContents, "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS4");
		document2.setContents("Random text 2");
		latexEditorController.commitChangesCommand(document2.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS4");
		assertEquals(oldContents, latexEditorController.goToPreviousVersion());
		
		
	}

}
