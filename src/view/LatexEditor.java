package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.LatexEditorController;

public class LatexEditor {
	LatexEditorController latexEditorController = new LatexEditorController();
	private JFrame frame;
	private boolean edit,versionTracking = true;
	private int x, y, caretPosition;
	private String storage = "Volatile", latexCommand ="" , path = "None", contentsOfPreviousVersion;
	private String[] types = {"Report", "Book", "Article", "Letter"};
	private String[] latexCommands = {"Chapter", "Section", "Subsection", "Subsubsection", "Unordered List", "Ordered List", "Table", "Figure"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LatexEditor window = new LatexEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LatexEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JTextArea textArea = new JTextArea();
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("C:\\Users"));
		frame = new JFrame();
		frame.setBounds(100, 100, 1136, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea.setBounds(0, 32, 1120, 659);
		frame.getContentPane().add(textArea);
		
		//New
		JButton btnNew = new JButton("New");
		btnNew.setBackground(UIManager.getColor("Button.background"));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x = JOptionPane.showOptionDialog(null, "What type of document do you want to create?",
		                "New",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, types, types[0]);
				
				textArea.setText(latexEditorController.newDocumentCommand(types[x]));
			}
		});
		btnNew.setBounds(10, 5, 60, 23);
		frame.getContentPane().add(btnNew);
		
		//Load
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					textArea.setText(latexEditorController.loadDocumentCommand(chooser.getSelectedFile()+""));
				}
			}
		});
		
		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int retrieval = chooser.showSaveDialog(null);
				if (retrieval == JFileChooser.APPROVE_OPTION) {
					latexEditorController.saveAsCommand(textArea.getText(),chooser.getSelectedFile()+".tex");
				}
				
				
			}
		});
		btnSaveAs.setBounds(156, 5, 89, 23);
		frame.getContentPane().add(btnSaveAs);
		
		btnLoad.setBounds(80, 5, 66, 23);
		frame.getContentPane().add(btnLoad);
		
		//Enable/Disable the text from being edited
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (edit == false) {
					edit = true;
				}else {
					edit = false;
				}
				textArea.setEditable(edit);
			}
		});
		btnEdit.setBounds(255, 5, 60, 23);
		frame.getContentPane().add(btnEdit);
		
		//Add Latex Command
		JButton btnAddLatexCommand = new JButton("Add Latex Command");
		btnAddLatexCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caretPosition = textArea.getCaretPosition();
				y = JOptionPane.showOptionDialog(null, "Which Latex command would you like to add?",
				        "Add Latex Command",
				        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, latexCommands, latexCommands[0]);
				latexCommand = latexEditorController.addLatexCommand(latexCommands[y],types[x]);
				if (latexCommand.equals("Wrong Type")) {
					JOptionPane.showMessageDialog(null, "This command cannot be added in a "+types[x]+".");
				}else {
						textArea.insert(latexCommand, caretPosition);
				}	
			}
		});
		btnAddLatexCommand.setBounds(325, 5, 154, 23);
		frame.getContentPane().add(btnAddLatexCommand);
		
		//Commit Changes
		JButton btnCommitChanges = new JButton("Commit Changes");
		btnCommitChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (versionTracking == true) {
					if (path.equals("None")) {
						int retrieval = chooser.showSaveDialog(null);
						if (retrieval == JFileChooser.APPROVE_OPTION) {
							path = chooser.getSelectedFile()+".tex";
						}
					}
					versionTracking = latexEditorController.commitChangesCommand(textArea.getText() , path);
					JOptionPane.showMessageDialog(null, "The current contents were commited.");
				}else {
					JOptionPane.showMessageDialog(null, "Version tracking is not enabled.");
				}
			}
		});
		btnCommitChanges.setBounds(489, 5, 136, 23);
		frame.getContentPane().add(btnCommitChanges);
		
		//Change Version Strategy
		JButton btnChangeVersionStrategy = new JButton("Change Version Strategy");
		btnChangeVersionStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				storage = latexEditorController.changeVersionStrategyCommand();
				JOptionPane.showMessageDialog(null, "Save strategy changed to "+storage+".");
			}
		});
		btnChangeVersionStrategy.setBounds(635, 5, 177, 23);
		frame.getContentPane().add(btnChangeVersionStrategy);
		
		//Previous Version
		JButton btnPreviousVersion = new JButton("Previous Version");
		btnPreviousVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentsOfPreviousVersion = latexEditorController.goToPreviousVersion();
				textArea.setText(contentsOfPreviousVersion);
			}
		});
		btnPreviousVersion.setBounds(822, 5, 136, 23);
		frame.getContentPane().add(btnPreviousVersion);
				
		//Enable/Disable Version Tracking
		JButton btnVersionTracking = new JButton("Version Tracking");
		btnVersionTracking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				versionTracking = latexEditorController.switchVersionTrackingCommand();
				if (versionTracking == true) {
					JOptionPane.showMessageDialog(null, "Version Tracking is enabled.");
				}else {
					JOptionPane.showMessageDialog(null, "Version Tracking is disabled.");
				}
			}
		});
		btnVersionTracking.setBounds(968, 5, 141, 23);
		frame.getContentPane().add(btnVersionTracking);	
	}
}