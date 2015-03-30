package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import plic.AntlrDrinkListener;
import plic.HelloLexer;
import plic.HelloParser;
import plic.HelloParser.DrinkSentenceContext;

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
    @FXML
    private void handleRunAction(ActionEvent event) {
    	/**
    	 * EXAMPLE try to replace cup 
    	 * after replace the string by the mainApp.getJavaKeywords().getCodeArea().getText()
    	 */
    	
        // Get our lexer
        HelloLexer lexer = new HelloLexer(new ANTLRInputStream("a cup of tea"));
     
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
     
        // Pass the tokens to the parser
        HelloParser parser = new HelloParser(tokens);
     

     
        //debug
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, org.antlr.v4.runtime.RecognitionException e) {
                System.err.println("failed to parse at line " + line + " due to " + msg);
            }
            
        });
        
        // Specify our entry point
        DrinkSentenceContext drinkSentenceContext = parser.drinkSentence();
        
        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        AntlrDrinkListener listener = new AntlrDrinkListener();
        walker.walk(listener, drinkSentenceContext);
        
        //print
        System.out.println("Analyse depuis la grammaire:"+parser.getGrammarFileName());
    	System.out.println("Nombre d'erreurs: "+parser.getNumberOfSyntaxErrors());
    	
    	
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
