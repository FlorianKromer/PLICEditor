/**
 * Sample Skeleton for 'Editor.fxml' Controller Class
 */

package controller;

import java.lang.reflect.Array;

import org.fxmisc.richtext.CodeArea;

import view.JavaKeywordsAsync;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;


public class EditorController {

    // Reference to the main application.
    private MainApp mainApp;


    @FXML
    private TextArea console;
    
    @FXML
    private AnchorPane codeAreaPane;
    @FXML
    private SplitPane splitPane;
    
    private JavaKeywordsAsync javaKeywords;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EditorController() {
    	javaKeywords = new JavaKeywordsAsync();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    	javaKeywords.getCodeArea().setPrefHeight(splitPane.prefHeightProperty().doubleValue()*splitPane.getDividerPositions()[0]-10);
    	javaKeywords.getCodeArea().setPrefWidth(splitPane.prefWidthProperty().doubleValue());

    	codeAreaPane.getChildren().add(javaKeywords.getCodeArea());
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }



	/**
	 * @return the console
	 */
	public TextArea getConsole() {
		return console;
	}

	/**
	 * @param console the console to set
	 */
	public void setConsole(TextArea console) {
		this.console = console;
	}

	/**
	 * @return the javaKeywords
	 */
	public JavaKeywordsAsync getJavaKeywords() {
		return javaKeywords;
	}

	/**
	 * @param javaKeywords the javaKeywords to set
	 */
	public void setJavaKeywords(JavaKeywordsAsync javaKeywords) {
		this.javaKeywords = javaKeywords;
	}
}
