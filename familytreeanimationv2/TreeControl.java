/*
 * 
 */
package familytreeanimationv2;

import java.util.Arrays;
import java.util.regex.Matcher;

/**
 *
 * @author Chris
 */
public class TreeControl {

    private Person rootPerson;

    private TreeView view;

    private java.util.ArrayList<Person> personList;

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

    public TreeControl(Person person, TreeView tv, java.util.ArrayList<Person> personList) {

        this.rootPerson = person;
        this.view = tv;
        this.personList = personList;

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

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new javafx.scene.control.Label("First Name: "), 0, 0);
        gridPane.add(fnameTF, 1, 0);
        gridPane.add(new javafx.scene.control.Label("Middle Name: "), 0, 1);
        gridPane.add(mnameTF, 1, 1);
        gridPane.add(new javafx.scene.control.Label("Last Name: "), 0, 2);
        gridPane.add(lnameTF, 1, 2);
        gridPane.add(maleRadio, 0, 3);
        gridPane.add(femaleRadio, 1, 3);
        gridPane.add(changePictureBtn, 0, 4);
        gridPane.add(addMotherBT, 0, 5);
        gridPane.add(addFatherBT, 1, 5);
        gridPane.add(addMaleSpouseBT, 0, 6);
        gridPane.add(addFemaleSpouseBT, 1, 6);
        gridPane.add(addKidsBT, 0, 7);

        gridPane.add(saveBT, 1, 10);

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        this.fnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        this.mnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        this.lnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

        javafx.scene.layout.GridPane.setHalignment(saveBT,
                javafx.geometry.HPos.RIGHT);

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

            String filePath = "";
            
            try(java.io.ObjectInputStream input = new java.io.ObjectInputStream(new java.io.FileInputStream(picFile))){
           String tempString = picFile.getCanonicalPath();
           filePath = Matcher.quoteReplacement(tempString);
            }
            
            catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
            
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

            this.personList.add(mother);

            TreeControl motherControl = new TreeControl(mother, this.view, personList);
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

            this.personList.add(father);

            TreeControl fatherControl = new TreeControl(father, this.view, personList);
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

            this.personList.add(maleSpouse);

            TreeControl maleSpouseControl = new TreeControl(maleSpouse, this.view, personList);
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

            this.personList.add(femaleSpouse);

            TreeControl femaleSpouseControl = new TreeControl(femaleSpouse, this.view, personList);
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
        stage.setTitle(this.title);
        stage.setScene(scene);
        stage.show();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
