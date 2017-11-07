/*
 * 
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class SystemControl {

   final private javafx.stage.Stage mainStage;
   final private TreeView view;
   final private SystemModel model;

    public SystemControl(TreeView view, javafx.stage.Stage mainStage) {
        this.model = new SystemModel();
        this.view = view;
        this.mainStage = mainStage;
        

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

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Save Location");
            fileChooser.setInitialFileName(this.view.getRootPerson().toString());
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showSaveDialog(this.mainStage);
            this.model.saveTree(saveFile, this.view.getRootPerson());
        });

        loadBtn.setOnAction(e -> {

            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Tree to Load");
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Tree Files", "*.tree"));
            java.io.File saveFile = fileChooser.showOpenDialog(mainStage);

            this.view.setRootPerson(this.model.loadTree(saveFile));
            this.view.displayTree();

        });

        updateBtn.setOnAction(e -> {
            SystemUpdateClient client = this.model.update();
            client.start(mainStage);
        });

        return buttonBox;
    }

    
}
