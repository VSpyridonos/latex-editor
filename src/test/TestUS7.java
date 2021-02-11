package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.LatexEditorController;
import model.Document;

class TestUS7 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		Document document = new Document("Report",latexEditorController.newDocumentCommand("Report"));
		latexEditorController.commitChangesCommand(document.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS7");
		document.setContents("Random Text");
		latexEditorController.commitChangesCommand(document.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS7");
		assertNotEquals(latexEditorController.getVersionsManager().getVersions().get(latexEditorController.getVersionsManager().getNumberOfVersions()), latexEditorController.goToPreviousVersion());
	}

}
