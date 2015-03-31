package controller;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import view.JavaKeywordsAsync;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private File currentFile;
    private JavaKeywordsAsync javaKeywords;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PLIC Editor");
//        Font.loadFont(JavaKeywordsAsync.class.getResource("OPEN.TTF").toExternalForm(), 14);
        
        initRootLayout();

        addEditorView();
        
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Menu.fxml"));
            rootLayout = (BorderPane) loader.load();
            // create the menu controller 
            MenuController controller = loader.getController();
            controller.setMainApp(this);
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void addEditorView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Editor.fxml"));
            AnchorPane editorOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(editorOverview);
            
            //creathe editor controller
            EditorController controller = loader.getController();
            
            controller.setMainApp(this);
            setJavaKeywords(controller.getJavaKeywords());

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


	public File getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	public JavaKeywordsAsync getJavaKeywords() {
		return javaKeywords;
	}

	public void setJavaKeywords(JavaKeywordsAsync javaKeywords) {
		this.javaKeywords = javaKeywords;
	}


}
