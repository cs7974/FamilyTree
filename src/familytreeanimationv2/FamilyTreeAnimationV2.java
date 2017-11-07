/*
 * 
 */
package familytreeanimationv2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
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

        TreeView view = new TreeView(rootPerson);

        SystemControl sc = new SystemControl(view, primaryStage);

        javafx.scene.layout.BorderPane bPane = new javafx.scene.layout.BorderPane();
        
        bPane.setCenter(view);
        bPane.setTop(sc.showButtonPanel());
        bPane.setMinSize(900, 750);
        HBox hb = new HBox(bPane);
        ScrollPane scrollPane = new ScrollPane(hb);
        //scrollPane.setFitToHeight(true);
        //scrollPane.setFitToWidth(true);
        
        scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        Scene scene = new Scene(scrollPane, 900, 750);

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
