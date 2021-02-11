package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controller.LatexEditorController;

class TestUS5 {

	@Test
	final void test() {
		LatexEditorController latexEditorController = new LatexEditorController();
		String strategy = latexEditorController.changeVersionStrategyCommand();
		assertEquals("Stable", strategy);
		strategy = latexEditorController.changeVersionStrategyCommand();
		assertEquals("Volatile", strategy);
	
	}
	
}	
