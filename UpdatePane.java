/*
 * This is the class for a pane that will pop up when a family member 
 * needs be created or updated.
 *
 */
package familytreeanimation;

/**
 *
 * @author Chris
 */
public class UpdatePane {

    private Person person = new Person();
    private FTView view = new FTView();

    private javafx.scene.control.TextField fnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField mnameTF
            = new javafx.scene.control.TextField();
    private javafx.scene.control.TextField lnameTF
            = new javafx.scene.control.TextField();
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

    public UpdatePane() {

    }

    public UpdatePane(Person person, FTView view) {
        this.person = person;
        this.view = view;
        setFnameTF(person.getfName());
        setMnameTF(person.getmName());
        setLnameTF(person.getlName());

    }

    public Person showUpdatePane() {

        javafx.stage.Stage stage = new javafx.stage.Stage();

        javafx.scene.layout.GridPane gridPane
                = new javafx.scene.layout.GridPane();

        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new javafx.scene.control.Label("First Name: "), 0, 0);
        gridPane.add(fnameTF, 1, 0);
        gridPane.add(new javafx.scene.control.Label("Middle Name: "), 0, 1);
        gridPane.add(mnameTF, 1, 1);
        gridPane.add(new javafx.scene.control.Label("Last Name: "), 0, 2);
        gridPane.add(lnameTF, 1, 2);

        gridPane.add(addMotherBT, 0, 5);
        gridPane.add(addFatherBT, 1, 5);
        gridPane.add(addMaleSpouseBT, 0, 6);
        gridPane.add(addFemaleSpouseBT, 1, 6);
        gridPane.add(addKidsBT, 0, 7);

        gridPane.add(saveBT, 1, 10);

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        fnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        mnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        lnameTF.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

        javafx.scene.layout.GridPane.setHalignment(saveBT,
                javafx.geometry.HPos.RIGHT);

        addMotherBT.setOnAction(e -> {

            Person mother = new Person();

            person.setMother(mother);

            UpdatePane motherUpdate = new UpdatePane(mother, this.view);
            motherUpdate.showUpdatePane();
            view.displayTree();
            stage.close();
        });

        saveBT.setOnAction(e -> {

            person.setfName(fnameTF.getText());
            person.setmName(mnameTF.getText());
            person.setlName(lnameTF.getText());
            view.displayTree();
            stage.close();
        });

        javafx.scene.Scene scene = new javafx.scene.Scene(gridPane, 400, 250);
        stage.setTitle("Update Person");
        stage.setScene(scene);
        stage.show();

        return person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFnameTF() {
        return fnameTF.getText();
    }

    public void setFnameTF(String fName) {
        this.fnameTF.setText(fName);
    }

    public String getMnameTF() {
        return mnameTF.getText();
    }

    public void setMnameTF(String mName) {

        this.mnameTF.setText(mName);
    }

    public String getLnameTF() {
        return lnameTF.getText();
    }

    public void setLnameTF(String lName) {

        this.lnameTF.setText(lName);
    }

}
