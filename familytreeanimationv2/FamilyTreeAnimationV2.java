/*
 * 
 */
package familytreeanimationv2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class FamilyTreeAnimationV2 extends Application {

    @Override
    public void start(Stage primaryStage) {

        Person rootPerson = new Person();
        rootPerson.setfName("Root");
        rootPerson.setmName("Family");
        rootPerson.setlName("Member");

        java.util.ArrayList<Person> personList = new java.util.ArrayList<>();

        personList.add(rootPerson);

        TreeView view = new TreeView(personList);
        
        SystemControl sc = new SystemControl(view, primaryStage);
        
        javafx.scene.layout.BorderPane bPane = new javafx.scene.layout.BorderPane();
        
        bPane.setCenter(view);
        bPane.setTop(sc.showButtonPanel());

        Scene scene = new Scene(bPane, 900, 750);

        primaryStage.setTitle("Family Tree Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.displayTree();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
