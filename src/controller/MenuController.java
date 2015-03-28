package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

public class MenuController {
	
    // Reference to the main application.
    private MainApp mainApp;

    @FXML
    private MenuItem saveFile;
    
    @FXML
    private MenuItem openFile;
    
    @FXML
    private MenuItem newFile;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
    
    @FXML
    private void handleOpenFileAction(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            openFile(file);
        }
    }
    
    @FXML
    private void handleSaveFileAction(ActionEvent event) {

    	File file = mainApp.getCurrentFile();
    	String s = mainApp.getJavaKeywords().getCodeArea().getText();
    	try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()), Charset.defaultCharset())) {
    	    writer.write(s, 0, s.length());
    	} catch (IOException x) {
    	    System.err.format("IOException: %s%n", x);
    	}
    }
	public void openFile(File file) {
		mainApp.setCurrentFile(file);
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = new String(encoded, Charset.defaultCharset());
		mainApp.getJavaKeywords().getCodeArea().replaceText(text);
		
	}
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
    

}
