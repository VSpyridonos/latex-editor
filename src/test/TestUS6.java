package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.LatexEditorController;
import model.Document;

class TestUS6 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		latexEditorController.getVersionsManager().switchVersionTracking();
		Document document = new Document("Report",latexEditorController.newDocumentCommand("Report"));
		latexEditorController.commitChangesCommand(document.getContents(), "C:\\Users\\User\\Desktop\\TestLatexFiles\\TestUS6");
		assertNotEquals(latexEditorController.getVersionsManager().getVersions().get(latexEditorController.getVersionsManager().getNumberOfVersions()),document.getContents());
		

	}
}
