/*
 * This is the section at top
 */
package familytreeanimationv2;

import javafx.geometry.Pos;

/**
 *
 * @author Chris
 */
public class SystemControl {

   final private javafx.stage.Stage mainStage;
   final private TreeView view;
    private double version = 1.0;

    public SystemControl(TreeView view, javafx.stage.Stage mainStage) {
        this.view = view;
        this.mainStage = mainStage;

    }

    public javafx.scene.layout.HBox showButtonPanel() {

        javafx.scene.layout.HBox buttonBox = new javafx.scene.layout.HBox(10.0);

        buttonBox.setStyle("-fx-background-color: rgba(143,188,143,0.75);"
                + "-fx-background-radius: 15; -fx-border-radius: 15");

        javafx.scene.control.Button saveBtn
                = new javafx.scene.control.Button("Save Tree to File");

        javafx.scene.control.Button loadBtn
                = new javafx.scene.control.Button("Load Tree from File");

        javafx.scene.control.Button updateBtn
                = new javafx.scene.control.Button("Check for Updates");

        javafx.scene.image.Image saveImage = new javafx.scene.image.Image("images/Save.png");
        javafx.scene.image.ImageView saveView = new javafx.scene.image.ImageView(saveImage);

        javafx.scene.image.Image loadImage = new javafx.scene.image.Image("images/system-file-manager.png");
        javafx.scene.image.ImageView loadView = new javafx.scene.image.ImageView(loadImage);

        javafx.scene.image.Image updateImage = new javafx.scene.image.Image("images/transmission.png");
        javafx.scene.image.ImageView updateView = new javafx.scene.image.ImageView(updateImage);

        saveBtn.setGraphic(saveView);
        loadBtn.setGraphic(loadView);
        updateBtn.setGraphic(updateView);
        saveBtn.getStyleClass().add("leafygreen");
        loadBtn.getStyleClass().add("leafygreen");
        updateBtn.getStyleClass().add("leafygreen");
        buttonBox.setPadding(new javafx.geometry.Insets(10.0, 10.0, 10.0, 10.0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(90);
        buttonBox.getChildren().addAll(saveBtn, loadBtn, updateBtn);
        
        buttonBox.getStylesheets().add(getClass().getResource("FamilyTreeCSS.css").toExternalForm());

        saveBtn.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Save Location");
            fileChooser.setInitialFileName(this.view.getRootPerson().toString());
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showSaveDialog(this.mainStage);
            saveTree(saveFile);
        });

        loadBtn.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Tree to Load");
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showOpenDialog(mainStage);

            this.view.setRootPerson(loadTree(saveFile));

        });

        updateBtn.setOnAction(e -> {
            SystemUpdateClient client = new SystemUpdateClient(this.version);
            client.start(mainStage);
        });

        return buttonBox;
    }

    public void saveTree(java.io.File file) {

        try (java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            output.writeObject(this.view.getRootPerson());
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

    }

    public Person loadTree(java.io.File file) {

        try (java.io.ObjectInputStream input = new java.io.ObjectInputStream(new java.io.FileInputStream(file))) {
            this.view.setRootPerson((Person) input.readObject());

            this.view.displayTree();
            return this.view.getRootPerson();

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return this.view.getRootPerson();
    }
}
