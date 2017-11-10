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
        rootPerson.setfName("Click");
        rootPerson.setlName("Here");

        TreeView view = new TreeView(rootPerson);

        ServiceControl sc = new ServiceControl(view, primaryStage);

        javafx.scene.layout.BorderPane bPane = new javafx.scene.layout.BorderPane();

        view.getStyleClass().add("brdpane");
        bPane.setCenter(view);
        bPane.setTop(sc.showButtonPanel());
        bPane.setMinSize(900, 750);
        javafx.scene.layout.HBox hb = new javafx.scene.layout.HBox(bPane);
        javafx.scene.control.ScrollPane scrollPane = new javafx.scene.control.ScrollPane(hb);
        //scrollPane.setFitToHeight(true);
        //scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        Scene scene = new Scene(scrollPane, 900, 750);

        scene.getStylesheets().add(getClass().getResource("/css/FamilyTreeCSS.css").toExternalForm());
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
