/*
 * This is the update pane
 */
package familytreeanimationv2;

import java.util.regex.Matcher;

/**
 *
 * @author Chris
 */
public class TreeControl {

    private Person rootPerson;

    private TreeView view;


    private String title;

    private javafx.scene.control.TextField fnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField mnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField lnameTF
            = new javafx.scene.control.TextField();

    private javafx.scene.control.RadioButton maleRadio
            = new javafx.scene.control.RadioButton("Male");
    private javafx.scene.control.RadioButton femaleRadio
            = new javafx.scene.control.RadioButton("Female");

    private javafx.scene.control.Button changePictureBtn
            = new javafx.scene.control.Button("Change Picture");
    private javafx.scene.control.Button addMaleSpouseBT
            = new javafx.scene.control.Button("Add Husband");
    private javafx.scene.control.Button addFemaleSpouseBT
            = new javafx.scene.control.Button("Add Wife");
    private javafx.scene.control.Button addFatherBT
            = new javafx.scene.control.Button("Add Father");
    private javafx.scene.control.Button addMotherBT
            = new javafx.scene.control.Button("Add Mother");
    private javafx.scene.control.Button addKidsBT
            = new javafx.scene.control.Button("Add Kids");
    private javafx.scene.control.Button saveBT
            = new javafx.scene.control.Button("Save");

    public TreeControl(Person person, TreeView tv) {

        this.rootPerson = person;
        this.view = tv;

        this.fnameTF.setText(person.getfName());
        this.mnameTF.setText(person.getmName());
        this.lnameTF.setText(person.getlName());

        this.title = "Edit: " + person.toString();

    }
    
    public void showInputPane() {
        javafx.stage.Stage stage = new javafx.stage.Stage();

        javafx.scene.layout.GridPane gridPane
                = new javafx.scene.layout.GridPane();

        javafx.scene.control.ToggleGroup sexGroup
                = new javafx.scene.control.ToggleGroup();
        this.maleRadio.setToggleGroup(sexGroup);
        this.femaleRadio.setToggleGroup(sexGroup);

         javafx.scene.layout.GridPane namePane
                = new javafx.scene.layout.GridPane();
        javafx.scene.layout.VBox imagePane 
                = new javafx.scene.layout.VBox();
        javafx.scene.layout.HBox sexPane 
                = new javafx.scene.layout.HBox();
        javafx.scene.layout.VBox dataPane
                = new javafx.scene.layout.VBox();
        javafx.scene.layout.HBox btPane1 
                = new javafx.scene.layout.HBox();
        javafx.scene.layout.HBox btPane2 
                = new javafx.scene.layout.HBox();
        
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        namePane.setHgap(15);
        namePane.setVgap(5);
        namePane.getRowConstraints().add(new javafx.scene.layout.RowConstraints(30));
        namePane.getRowConstraints().add(new javafx.scene.layout.RowConstraints(30));
        namePane.getRowConstraints().add(new javafx.scene.layout.RowConstraints(30));
        //Insets(top, right, bottom, left)
        imagePane.setPadding(new javafx.geometry.Insets(5, 15, 5, 5));
        sexPane.setPadding(new javafx.geometry.Insets(10, 20, 5, 5));
        sexPane.setSpacing(25);
        btPane1.setPadding(new javafx.geometry.Insets(5, 0, 5, 5));
        btPane1.setSpacing(10);
        btPane1.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        btPane2.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        btPane2.setSpacing(10);
        
        javafx.scene.control.Label l1 = new javafx.scene.control.Label("First Name: ");
        l1.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(l1, 0, 0);
        fnameTF.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(fnameTF, 1, 0);
        javafx.scene.control.Label l2 = new javafx.scene.control.Label("Middle Name: ");
        l2.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(l2, 0, 1);
        mnameTF.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(mnameTF, 1, 1);
        javafx.scene.control.Label l3 = new javafx.scene.control.Label("Last Name: ");
        l3.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(l3, 0, 2);
        lnameTF.setFont(new javafx.scene.text.Font("Georgia", 12));
        namePane.add(lnameTF, 1, 2);
        //dataPane.add(maleRadio, 0, 3);
        if (this.rootPerson.getSex() == 1) 
            maleRadio.setSelected(true);
        //dataPane.add(femaleRadio, 1, 3);
        if (this.rootPerson.getSex() == 2) 
            femaleRadio.setSelected(true);
        femaleRadio.setFont(new javafx.scene.text.Font("Georgia", 12));
        maleRadio.setFont(new javafx.scene.text.Font("Georgia", 12));
        sexPane.getChildren().addAll(maleRadio, femaleRadio);
        sexPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        dataPane.getChildren().addAll(namePane, sexPane);
        
        
        changePictureBtn.getStyleClass().add("shinygreen");
        changePictureBtn.setStyle("-fx-font-size: 10px");
        addMotherBT.getStyleClass().add("shinygreen");
        addFatherBT.getStyleClass().add("shinygreen");
        addMaleSpouseBT.getStyleClass().add("shinygreen");
        addFemaleSpouseBT.getStyleClass().add("shinygreen");
        addKidsBT.getStyleClass().add("shinygreen");
        saveBT.getStyleClass().add("shinygreen");
        
        imagePane.getChildren().addAll(new javafx.scene.image.ImageView(
                new javafx.scene.image.Image(this.rootPerson.getImagePath())),
                changePictureBtn);
        imagePane.setAlignment(javafx.geometry.Pos.CENTER);
        
        btPane1.getChildren().addAll(addMotherBT, addFatherBT);
        btPane2.getChildren().addAll(addMaleSpouseBT, addFemaleSpouseBT, addKidsBT);
        
        gridPane.add(imagePane, 0, 0);
        gridPane.add(dataPane, 1, 0);
        //need to add in an Additional Info label and text area
        gridPane.add(btPane1, 0, 5);
        gridPane.add(btPane2, 1, 5);

        gridPane.add(saveBT, 1, 12);

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        this.fnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        this.mnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        this.lnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

        javafx.scene.layout.GridPane.setHalignment(saveBT,
                javafx.geometry.HPos.RIGHT);
        javafx.scene.layout.GridPane.setValignment(saveBT,
                javafx.geometry.VPos.BOTTOM);

        this.maleRadio.setOnAction(e -> {

            this.rootPerson.setSex(1);
        });

        this.femaleRadio.setOnAction(e -> {

            this.rootPerson.setSex(2);
        });

        this.changePictureBtn.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Choose a Tree to Load");
            fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            java.io.File picFile = fileChooser.showOpenDialog(stage);

            String filePath = picFile.getAbsolutePath();
            String winPath = Matcher.quoteReplacement(filePath);
            filePath = "file:" + winPath.replaceFirst("C", "c");
            
            System.out.println(filePath);

            this.rootPerson.setImagePath(filePath);

            this.view.displayTree();

        });

        this.addMotherBT.setOnAction(e -> {
            Person mother = new Person();
            mother.setfName("");
            mother.setmName("");
            mother.setlName("");
            mother.setSex(2);

            this.rootPerson.setMother(mother);

            TreeControl motherControl = new TreeControl(mother, this.view);
            motherControl.addFatherBT.setDisable(true);
            motherControl.addMotherBT.setDisable(true);
            motherControl.addFemaleSpouseBT.setDisable(true);
            motherControl.addMaleSpouseBT.setDisable(true);
            motherControl.addKidsBT.setDisable(true);
            motherControl.femaleRadio.setDisable(true);
            motherControl.maleRadio.setDisable(true);
            motherControl.setTitle("Edit: " + rootPerson.toString() + "'s Mother");

            motherControl.showInputPane();

            stage.close();

        });

        this.addFatherBT.setOnAction(e -> {
            Person father = new Person();
            father.setfName("");
            father.setmName("");
            father.setlName("");
            father.setSex(1);

            this.rootPerson.setFather(father);

            TreeControl fatherControl = new TreeControl(father, this.view);
            fatherControl.addFatherBT.setDisable(true);
            fatherControl.addMotherBT.setDisable(true);
            fatherControl.addFemaleSpouseBT.setDisable(true);
            fatherControl.addMaleSpouseBT.setDisable(true);
            fatherControl.addKidsBT.setDisable(true);
            fatherControl.femaleRadio.setDisable(true);
            fatherControl.maleRadio.setDisable(true);
            fatherControl.setTitle("Edit: " + rootPerson.toString() + "'s Father");

            fatherControl.showInputPane();
            stage.close();

        });

        this.addMaleSpouseBT.setOnAction(e -> {

            Person maleSpouse = new Person();
            maleSpouse.setfName("");
            maleSpouse.setmName("");
            maleSpouse.setlName("");
            maleSpouse.setSex(1);

            this.rootPerson.setMaleSpouse(maleSpouse);


            TreeControl maleSpouseControl = new TreeControl(maleSpouse, this.view);
            maleSpouseControl.addFatherBT.setDisable(true);
            maleSpouseControl.addMotherBT.setDisable(true);
            maleSpouseControl.addFemaleSpouseBT.setDisable(true);
            maleSpouseControl.addMaleSpouseBT.setDisable(true);
            maleSpouseControl.addKidsBT.setDisable(true);
            maleSpouseControl.femaleRadio.setDisable(true);
            maleSpouseControl.maleRadio.setDisable(true);
            maleSpouseControl.setTitle("Edit: " + rootPerson.toString() + "'s Spouse");
            maleSpouseControl.showInputPane();
            stage.close();

        });

        this.addFemaleSpouseBT.setOnAction(e -> {

            Person femaleSpouse = new Person();
            femaleSpouse.setfName("");
            femaleSpouse.setmName("");
            femaleSpouse.setlName("");
            femaleSpouse.setSex(2);

            this.rootPerson.setFemaleSpouse(femaleSpouse);

            TreeControl femaleSpouseControl = new TreeControl(femaleSpouse, this.view);
            femaleSpouseControl.addFatherBT.setDisable(true);
            femaleSpouseControl.addMotherBT.setDisable(true);
            femaleSpouseControl.addFemaleSpouseBT.setDisable(true);
            femaleSpouseControl.addMaleSpouseBT.setDisable(true);
            femaleSpouseControl.addKidsBT.setDisable(true);
            femaleSpouseControl.femaleRadio.setDisable(true);
            femaleSpouseControl.maleRadio.setDisable(true);
            femaleSpouseControl.setTitle("Edit: " + rootPerson.toString() + "'s Spouse");
            femaleSpouseControl.showInputPane();
            stage.close();

        });

        this.addKidsBT.setOnAction(e -> {
            //////////////////////////////////// NEED WORK HERE////////////////
        });

        this.saveBT.setOnAction(e -> {

            this.rootPerson.setfName(fnameTF.getText());
            this.rootPerson.setmName(mnameTF.getText());
            this.rootPerson.setlName(lnameTF.getText());
            stage.close();
            this.view.displayTree();

        });

        // Disable buttons if they already have that relative
        if (this.rootPerson.getMother() != null) {
            this.addMotherBT.setDisable(true);
        }
        if (this.rootPerson.getFather() != null) {
            this.addFatherBT.setDisable(true);
        }
        if (this.rootPerson.getMaleSpouse() != null) {
            this.addMaleSpouseBT.setDisable(true);
        }
        if (this.rootPerson.getFemaleSpouse() != null) {
            this.addFemaleSpouseBT.setDisable(true);
        }

        javafx.scene.Scene scene = new javafx.scene.Scene(gridPane, 400, 250);
        scene.getStylesheets().add(getClass().getResource("FamilyTreeCSS.css").toExternalForm());
        stage.setTitle(this.title);
        stage.setScene(scene);
        stage.setHeight(350);
        stage.setWidth(450);
        stage.show();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
