/*
 * 
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class SystemControl {

    private javafx.stage.Stage mainStage;
    private TreeView view;

    public SystemControl(TreeView view, javafx.stage.Stage mainStage) {
        this.view = view;
        this.mainStage = mainStage;

    }

    public void saveTree(java.io.File file) {

        try (java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            output.writeObject(this.view.getPersonList());
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

    }

    public java.util.ArrayList loadTree(java.io.File file) {

        try (java.io.ObjectInputStream input = new java.io.ObjectInputStream(new java.io.FileInputStream(file))) {
            this.view.setPersonList((java.util.ArrayList<Person>) input.readObject());

            this.view.displayTree();
            return this.view.getPersonList();
            
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return this.view.getPersonList();

    }

    public javafx.scene.layout.HBox showButtonPanel() {

        javafx.scene.layout.HBox buttonBox = new javafx.scene.layout.HBox(10.0);

        buttonBox.setStyle("-fx-border-color: black; -fx-background-color: red");

        javafx.scene.control.Button saveBtn
                = new javafx.scene.control.Button("Save Tree to File");

        javafx.scene.control.Button loadBtn
                = new javafx.scene.control.Button("Load Tree from File");

        javafx.scene.control.Button updateBtn
                = new javafx.scene.control.Button("Check for Updates");

        javafx.scene.image.Image saveImage = new javafx.scene.image.Image("images/synaptic.png");
        javafx.scene.image.ImageView saveView = new javafx.scene.image.ImageView(saveImage);

        javafx.scene.image.Image loadImage = new javafx.scene.image.Image("images/system-file-manager.png");
        javafx.scene.image.ImageView loadView = new javafx.scene.image.ImageView(loadImage);

        javafx.scene.image.Image updateImage = new javafx.scene.image.Image("images/transmission.png");
        javafx.scene.image.ImageView updateView = new javafx.scene.image.ImageView(updateImage);

        saveBtn.setGraphic(saveView);
        loadBtn.setGraphic(loadView);
        updateBtn.setGraphic(updateView);
        buttonBox.setPadding(new javafx.geometry.Insets(10.0, 10.0, 10.0, 10.0));
        buttonBox.getChildren().addAll(saveBtn, loadBtn, updateBtn);

        saveBtn.setOnAction(e -> {

            java.util.Calendar date = new java.util.GregorianCalendar();

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Save Location");
            fileChooser.setInitialFileName(this.view.getPersonList().get(0).toString());
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showSaveDialog(this.mainStage);
            saveTree(saveFile);
        });

        loadBtn.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Tree to Load");
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showOpenDialog(mainStage);

            this.view.setPersonList(loadTree(saveFile));

        });

        updateBtn.setOnAction(e -> {
            /// needs todo here ////
        });

        return buttonBox;
    }

}
