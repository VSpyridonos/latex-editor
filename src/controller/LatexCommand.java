package controller;

import java.util.HashMap;

public class LatexCommand {
	HashMap<String, String> commandMap = new HashMap<String,String>();
	
	public LatexCommand() {
		commandMap.put("Chapter","\\chapter{...}");
		commandMap.put("Section", "\\section{}");
		commandMap.put("Subsection", "\\subsection{}");
		commandMap.put("Subsubsection", "\\subsubsection{}");
		commandMap.put("Unordered List", "\\begin{itemize}\r\n" + 
				"\\item ...\r\n" + 
				"\\item ...\r\n" + 
				"\\end{itemize}");
		commandMap.put("Ordered List", "\\begin{enumerate}\r\n" + 
				"\\item ...\r\n" + 
				"\\item ...\r\n" + 
				"\\end{enumerate}");
		commandMap.put("Table", "\\begin{table}\r\n" + 
				"\\caption{....}\\label{...}\r\n" + 
				"\\begin{tabular}{|c|c|c|}\r\n" + 
				"\\hline\r\n" + 
				"... &...&...\\\\\r\n" + 
				"... &...&...\\\\\r\n" + 
				"... &...&...\\\\\r\n" + 
				"\\hline\r\n" + 
				"\\end{tabular}\r\n" + 
				"\\end{table}");
		commandMap.put("Figure", "\\begin{figure}\r\n" + 
				"\\includegraphics[width=...,height=...]{...}\r\n" + 
				"\\caption{....}\\label{...}\r\n" + 
				"\\end{figure}");
	}
	
	public String getLatexCommand(String command, String type) {
		if (type.equals("Letter")) {
			return "Wrong Type";
		}
		
		if (command.equals("Chapter") && type.equals("Article")) {
			return "Wrong Type";
		}
		return commandMap.get(command);
	}

}

