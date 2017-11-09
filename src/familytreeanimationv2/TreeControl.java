/*
 * 
 */
package familytreeanimationv2;

import java.time.LocalDate;
import java.util.regex.Matcher;

/**
 *
 * @author Chris
 */
public class TreeControl {

    private Person rootPerson;

    private TreeView view;
    
    private TreeModel model;

    private String title;

    private javafx.scene.control.TextField fnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField mnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField lnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField BirthYearTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField BirthMonthTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField BirthDayTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField DeathYearTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField DeathMonthTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField DeathDayTF
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
    private javafx.scene.control.Label warning
            = new javafx.scene.control.Label();
    private javafx.scene.control.Button removeBT
            = new javafx.scene.control.Button("Remove Person");

    public TreeControl(Person person, TreeView tv) {

        this.rootPerson = person;
        this.view = tv;
        this.model = new TreeModel();
        this.fnameTF.setText(person.getfName());
        this.mnameTF.setText(person.getmName());
        this.lnameTF.setText(person.getlName());
        this.BirthDayTF.setText(Integer.toString(person.getBirthDay()));
        this.BirthMonthTF.setText(Integer.toString(person.getBirthMonth()));
        this.BirthYearTF.setText(Integer.toString(person.getBirthYear()));
        this.DeathDayTF.setText(Integer.toString(person.getDeathDay()));
        this.DeathMonthTF.setText(Integer.toString(person.getDeathMonth()));
        this.DeathYearTF.setText(Integer.toString(person.getDeathYear()));
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
        gridPane.add(new javafx.scene.control.Label("Birth Date (Y, M, D) "), 0, 8);
        gridPane.add(BirthYearTF, 1, 8);
        gridPane.add(BirthMonthTF, 2, 8);
        gridPane.add(BirthDayTF, 3, 8);
        gridPane.add(new javafx.scene.control.Label("Date of Death (Y, M, D) "), 0, 9);
        gridPane.add(DeathYearTF, 1, 9);
        gridPane.add(DeathMonthTF, 2, 9);
        gridPane.add(DeathDayTF, 3, 9);
        gridPane.add(removeBT, 0, 10);
        gridPane.add(saveBT, 1, 10);
        gridPane.add(warning, 0, 11);
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
            String filePath = picFile.getAbsolutePath();
            String winPath = Matcher.quoteReplacement(filePath);
            filePath = "file:" + winPath.replaceFirst("C", "c");

            System.out.println(filePath);

            this.rootPerson.setImagePath(filePath);

            this.view.displayTree();
        });
        
        this.addMotherBT.setOnAction(e -> {
            Person mother = this.model.addMother(rootPerson);


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
            Person father = model.addFather(rootPerson);


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

            Person maleSpouse = model.addMaleSpouse(rootPerson);

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

            Person femaleSpouse = model.addFemaleSpouse(rootPerson);

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
            Person Child = model.addKid(rootPerson);

            TreeControl ChildControl = new TreeControl(Child, this.view);
            ChildControl.addFatherBT.setDisable(true);
            ChildControl.addMotherBT.setDisable(true);
            ChildControl.addFemaleSpouseBT.setDisable(true);
            ChildControl.addMaleSpouseBT.setDisable(true);
            ChildControl.addKidsBT.setDisable(true);
            ChildControl.femaleRadio.setDisable(false);
            ChildControl.maleRadio.setDisable(false);
            ChildControl.setTitle("Edit: " + rootPerson.toString() + "'s Child");
            ChildControl.showInputPane();

            stage.close();
        });

        this.removeBT.setOnAction(e -> {
            if (this.rootPerson.getMother() == null && this.rootPerson.getFather() == null
                    && this.rootPerson.getMaleSpouse() == null && this.rootPerson.getFemaleSpouse() == null
                    && this.rootPerson.getKids().isEmpty()) {
                warning.setText("You can't remove the only person in the tree.");
            } else if (this.rootPerson.getMother() != null) {
                if (this.rootPerson.getFather() != null || this.rootPerson.getMaleSpouse() != null
                        || this.rootPerson.getFemaleSpouse() != null || !this.rootPerson.getKids().isEmpty())  {
                    this.rootPerson.setImagePath("images/x-office-address-book.png");
                    this.rootPerson.setBirthDay(0);
                    this.rootPerson.setBirthMonth(0);
                    this.rootPerson.setBirthYear(0);
                    this.rootPerson.setDeathDay(0);
                    this.rootPerson.setDeathMonth(0);
                    this.rootPerson.setDeathYear(0);
                    this.rootPerson.setAge();
                    this.rootPerson.setSex(0);
                    this.rootPerson.setfName("");
                    this.rootPerson.setlName("");
                    this.rootPerson.setmName("");
                    stage.close();
                    this.view.displayTree();
                } else {
                    System.out.print("Needs to be implemented.");
                }
            } else if (this.rootPerson.getFather() != null) {
                if (this.rootPerson.getMother() != null || this.rootPerson.getMaleSpouse() != null
                        || this.rootPerson.getFemaleSpouse() != null || !this.rootPerson.getKids().isEmpty())  {
                    this.rootPerson.setImagePath("images/x-office-address-book.png");
                    this.rootPerson.setBirthDay(0);
                    this.rootPerson.setBirthMonth(0);
                    this.rootPerson.setBirthYear(0);
                    this.rootPerson.setDeathDay(0);
                    this.rootPerson.setDeathMonth(0);
                    this.rootPerson.setDeathYear(0);
                    this.rootPerson.setAge();
                    this.rootPerson.setSex(0);
                    this.rootPerson.setfName("");
                    this.rootPerson.setlName("");
                    this.rootPerson.setmName("");
                    stage.close();
                    this.view.displayTree();
                } else {
                    System.out.print("Needs to be implemented.");
                }
            } else if (this.rootPerson.getMaleSpouse() != null) {
                if (this.rootPerson.getFather() != null || this.rootPerson.getMother() != null
                        || this.rootPerson.getFemaleSpouse() != null || !this.rootPerson.getKids().isEmpty()) {
                    this.rootPerson.setImagePath("images/x-office-address-book.png");
                    this.rootPerson.setBirthDay(0);
                    this.rootPerson.setBirthMonth(0);
                    this.rootPerson.setBirthYear(0);
                    this.rootPerson.setDeathDay(0);
                    this.rootPerson.setDeathMonth(0);
                    this.rootPerson.setDeathYear(0);
                    this.rootPerson.setAge();
                    this.rootPerson.setSex(0);
                    this.rootPerson.setfName("");
                    this.rootPerson.setlName("");
                    this.rootPerson.setmName("");
                    stage.close();
                    this.view.displayTree();
                } else {
                    System.out.print("Needs to be implemented.");
                }
            } else if (this.rootPerson.getFemaleSpouse() != null) {
                if (this.rootPerson.getFather() != null || this.rootPerson.getMaleSpouse() != null
                        || this.rootPerson.getMother() != null || !this.rootPerson.getKids().isEmpty())  {
                    this.rootPerson.setImagePath("images/x-office-address-book.png");
                    this.rootPerson.setBirthDay(0);
                    this.rootPerson.setBirthMonth(0);
                    this.rootPerson.setBirthYear(0);
                    this.rootPerson.setDeathDay(0);
                    this.rootPerson.setDeathMonth(0);
                    this.rootPerson.setDeathYear(0);
                    this.rootPerson.setAge();
                    this.rootPerson.setSex(0);
                    this.rootPerson.setfName("");
                    this.rootPerson.setlName("");
                    this.rootPerson.setmName("");
                    stage.close();
                    this.view.displayTree();
                } else {
                    System.out.print("Needs to be implemented.");
                }
            } else if(!this.rootPerson.getKids().isEmpty()) {
                if (this.rootPerson.getFather() != null || this.rootPerson.getMaleSpouse() != null
                        || this.rootPerson.getMother() != null || this.rootPerson.getFemaleSpouse() != null)  {
                    this.rootPerson.setImagePath("images/x-office-address-book.png");
                    this.rootPerson.setBirthDay(0);
                    this.rootPerson.setBirthMonth(0);
                    this.rootPerson.setBirthYear(0);
                    this.rootPerson.setDeathDay(0);
                    this.rootPerson.setDeathMonth(0);
                    this.rootPerson.setDeathYear(0);
                    this.rootPerson.setAge();
                    this.rootPerson.setSex(0);
                    this.rootPerson.setfName("");
                    this.rootPerson.setlName("");
                    this.rootPerson.setmName("");
                    stage.close();
                    this.view.displayTree();
                } else {
                    System.out.print("Needs to be implemented.");
                }
            }
        });

        this.saveBT.setOnAction(e -> {
            if (Integer.parseInt(BirthDayTF.getText()) < 0 || Integer.parseInt(BirthDayTF.getText()) > 31 || Integer.parseInt(BirthMonthTF.getText()) < 0
                    || Integer.parseInt(BirthMonthTF.getText()) > 12 || Integer.parseInt(BirthYearTF.getText()) < 0) {
                warning.setText("Please Enter a Valid Date");
            } else if (Integer.parseInt(DeathDayTF.getText()) < 0 || Integer.parseInt(DeathDayTF.getText()) > 31 || Integer.parseInt(DeathMonthTF.getText()) < 0
                    || Integer.parseInt(DeathMonthTF.getText()) > 12 || Integer.parseInt(DeathYearTF.getText()) < 0) {
                warning.setText("Please Enter a Valid Date");
            } else {
                this.rootPerson.setfName(fnameTF.getText());
                this.rootPerson.setmName(mnameTF.getText());
                this.rootPerson.setlName(lnameTF.getText());
                if (Integer.parseInt(BirthDayTF.getText()) > 0 && Integer.parseInt(BirthMonthTF.getText()) > 0 && Integer.parseInt(BirthYearTF.getText()) > 0) {
                    this.rootPerson.setBirthDay(Integer.parseInt(BirthDayTF.getText()));
                    this.rootPerson.setBirthMonth(Integer.parseInt(BirthMonthTF.getText()));
                    this.rootPerson.setBirthYear(Integer.parseInt(BirthYearTF.getText()));
                    this.rootPerson.setBirthDate(LocalDate.of(Integer.parseInt(BirthYearTF.getText()), Integer.parseInt(BirthMonthTF.getText()), Integer.parseInt(BirthDayTF.getText())));
                }
                if (Integer.parseInt(DeathDayTF.getText()) > 0 && Integer.parseInt(DeathMonthTF.getText()) > 0 && Integer.parseInt(DeathYearTF.getText()) > 0) {
                    this.rootPerson.setDeathDay(Integer.parseInt(DeathDayTF.getText()));
                    this.rootPerson.setDeathMonth(Integer.parseInt(DeathMonthTF.getText()));
                    this.rootPerson.setDeathYear(Integer.parseInt(DeathYearTF.getText()));
                    this.rootPerson.setDeathDate(LocalDate.of(Integer.parseInt(DeathYearTF.getText()), Integer.parseInt(DeathMonthTF.getText()), Integer.parseInt(DeathDayTF.getText())));
                }
                this.rootPerson.setAge();
                if (maleRadio.isSelected()) {
                    this.rootPerson.setSex(1);
                } else if (femaleRadio.isSelected()) {
                    this.rootPerson.setSex(2);
                } else {
                    this.rootPerson.setSex(1);
                }
                stage.close();
                this.view.displayTree();
            }
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
            this.addFemaleSpouseBT.setDisable(true);
        }
        if (this.rootPerson.getFemaleSpouse() != null) {
            this.addFemaleSpouseBT.setDisable(true);
            this.addMaleSpouseBT.setDisable(true);
        }

        javafx.scene.Scene scene = new javafx.scene.Scene(gridPane, 600, 350);
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
