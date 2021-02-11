package controller;

import model.DocumentManager;
import model.VersionsManager;

public class LatexEditorController{
	
	DocumentManager documentManager = new DocumentManager();
	VersionsManager versionsManager = new VersionsManager();
	LatexCommand latexCommand = new LatexCommand();
	private String contents = "" , type = "Empty";

	public String newDocumentCommand(String typeSelected) {
		type = typeSelected;
		contents = documentManager.createDocument(type);
		return contents;
	}
	
	public String loadDocumentCommand(String path) {
		return versionsManager.loadDocument(path);
	}
	
	public void saveAsCommand(String textAreaContents, String path ) {
		versionsManager.saveAs(textAreaContents, path);
	}
	
	public String addLatexCommand(String com, String t) {
		return latexCommand.getLatexCommand(com,t);		
	}
	
	public boolean commitChangesCommand(String textAreaContents, String path) {
		return versionsManager.setLatestCommit(type, textAreaContents, path);
	}
	
	public String changeVersionStrategyCommand() {
		return versionsManager.changeVersionStrategy();
	}
	
	public String goToPreviousVersion() {
		return versionsManager.rollBackToPreviousVersion();
	}
	
	public boolean switchVersionTrackingCommand() {
		return versionsManager.switchVersionTracking();
	}
	
	public DocumentManager getDocumentManager() {
		return documentManager;	
	}
	
	public VersionsManager getVersionsManager() {
		return versionsManager;
	}
}

