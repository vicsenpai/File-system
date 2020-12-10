/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package FileSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import FileSystem.UiComponents.ExplorerCell;
import FileSystem.Utilities.Directorio;
import FileSystem.Utilities.FileSystem;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class App extends Application {
    FileSystem fileSystem = FileSystem.getInstance();

    @Override
    public void start(Stage stage) {
        Button navigateUpButton = new Button("Up");
        Button navigateGoButton = new Button("Go");
        ToolBar toolBar = new ToolBar(
            navigateUpButton,
            navigateGoButton,
            new Separator(),
            new Button("Copy"),
            new Button("Paste")
        );
       
        
        ListView<String> list = new ListView<String>();
        Directorio actualDir = fileSystem.ChangeDirUp();
        ObservableList<String> items = FXCollections.observableArrayList (
        //    "Single", "Double", "Suite", "Family App"
        actualDir.getHashMap().keySet()
            );
        list.setItems(items);

        list.setCellFactory(new Callback<ListView<String>, 
            ListCell<String>>() {
                @Override 
                public ListCell<String> call(ListView<String> list) {
                    return new ExplorerCell();
                }
            }
        );

        BorderPane border = new BorderPane();
        border.setTop(toolBar);
        border.setCenter(list);
        
        Scene scene = new Scene(border, 640, 480);

        JMetro jMetro = new JMetro(scene, Style.DARK);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
