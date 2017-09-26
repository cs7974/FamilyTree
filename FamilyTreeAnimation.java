/*
 * 
 */
package familytreeanimation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class FamilyTreeAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {

        Person person = new Person();

        FTView view = new FTView(person);

        view.displayTree();

        javafx.scene.layout.BorderPane root
                = new javafx.scene.layout.BorderPane();
        root.setCenter(view);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Family Tree Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
