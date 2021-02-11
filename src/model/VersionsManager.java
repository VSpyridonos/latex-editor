package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VersionsManager {
	
	ArrayList<Document> versions = new ArrayList<Document>();
	private int i, currentVersion, stringLength , numberOfVersions=0;
	private boolean enabled , flag = false;
	private String strategy, path, previousPath = "None" , location , filename = "None" , versionNumber , finalPath, loadDocumentContents = "";
	Document latestCommit = new Document();
	Document empty = new Document();
	
	public VersionsManager() {
		path = "None";
		strategy = "Volatile";
		enabled = true;
		versions.add(empty);
	}
	
	public String loadDocument(String path){
		Scanner loadDocumentScanner = null; 
		try {
			loadDocumentScanner = new Scanner(new File(path));
			loadDocumentContents = loadDocumentScanner.useDelimiter("\\Z").next();
			loadDocumentScanner.close();
		}catch (IOException e) {
            System.err.println(e);
        }
	    return loadDocumentContents;
	}
	
	public void saveAs(String textAreaContents , String path) {
		FileWriter fw;
	    try {
	           fw = new FileWriter(new File(path));
	           fw.write(textAreaContents);
	           fw.close();
	    } catch (IOException ex) {
	            ex.printStackTrace();
	    }
	}
	
	public void storeDocument(String path) {
		this.path = path;
		FileWriter fw;
	    try {
	           fw = new FileWriter(new File(path));
	           fw.write(latestCommit.getContents());
	           fw.close();
	    } catch (IOException ex) {
	            ex.printStackTrace();
	    }
	}
	
	public boolean storeEvolutionHistory(String path, int version) {
		if (!filename.equals("None"))
			if (enabled == true) {
				this.path = path;
				FileWriter fw;
				try {
					fw = new FileWriter(new File(path));
					fw.write(versions.get(version).getContents());
					fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		return enabled;	
	}
	
	public boolean setLatestCommit(String type, String textAreaContents, String path) {
		this.path = path;
		latestCommit.setType(type);
		latestCommit.setContents(textAreaContents);
		if (enabled == true) {
			numberOfVersions++;
			currentVersion = numberOfVersions;
			if (strategy.equals("Volatile")) {
				Document temporaryDocument = new Document(type , latestCommit.getContents());
				versions.add(temporaryDocument);
				
			}else if(strategy.equals("Stable")){
				setPath(numberOfVersions);
				storeDocument(finalPath);
			}
		}
		return enabled;
	}

	public String changeVersionStrategy() {
		if (strategy.equals("Volatile")){
			strategy = "Stable";
			for (int i = 0; i <=numberOfVersions; i++) {
				setPath(i);
				storeEvolutionHistory(finalPath , i);
			}
		}else {
			strategy = "Volatile";
			versions.clear();
			versions.add(empty);
			for (int i = 1; i <=numberOfVersions; i++) {
				setPath(i);
				Document temporaryDocument = new Document(loadDocument(finalPath));
				versions.add(temporaryDocument);
			}
			
		}
		return strategy;
	}
	
	public String rollBackToPreviousVersion() {
		if (currentVersion > 0) {
			currentVersion--;
		}
		if (strategy.equals("Volatile")) {
			return versions.get(currentVersion).getContents();
		}else {
			if (currentVersion > 0) {
				previousPath = setPath(currentVersion);
				return loadDocument(previousPath);
			}else {
				return "";
			}
			
		}
		
	}
	
	public boolean switchVersionTracking() {
		if (enabled == true) {
			enabled = false;
		}else {
			enabled = true;
		}
		return enabled;
	}
	
	public String setPath(int version) {
		stringLength = path.length();
		i = stringLength-1;
		while (flag==false && i>=0) {
			if (path.charAt(i) == '\\'){
				location = path.substring(0,i+1);
				filename = path.substring(i+1,stringLength-4);
				flag = true;
			}
			i--;
		}
		versionNumber = String.valueOf(version);
		finalPath = location+filename+versionNumber+".tex";
		return finalPath;
	}
	
	public ArrayList<Document> getVersions(){
		return versions;
	}
	
	public int getNumberOfVersions() {
		return numberOfVersions;
	}
	
}