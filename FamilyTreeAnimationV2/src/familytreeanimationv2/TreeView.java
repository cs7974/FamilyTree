/*
 *
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeView extends javafx.scene.layout.Pane {

    private Person rootPerson;
    private java.util.ArrayList<Person> personList;
    private double vGap = 200;
    private double hGap = 200;
    private int FChildNum = 1;
    private int MChildNum = 1;
    public TreeView() {
        this.setStyle("-fx-border-color: black; -fx-background-color: purple");
    }

    public TreeView(Person person) {
        this.rootPerson = person;
        this.setStyle("-fx-border-color: black; -fx-background-color: purple");

    }

    public void displayTree() {

        this.getChildren().clear();
        if (this.rootPerson != null) {

            displayTree(rootPerson, this.getWidth() / 2, this.getHeight() / 2);
        }
    }

    public void displayTree(Person root, double x, double y) {

        // first lay down lines
        if (root.getMother() != null) {
            getChildren().add(new javafx.scene.shape.Line(x - hGap, y - vGap, x, y));
            displayTree(root.getMother(), x - hGap, y - vGap);
        }
        if (root.getFather() != null) {
            getChildren().add(new javafx.scene.shape.Line(x + hGap, y - vGap, x, y));
            displayTree(root.getFather(), x + hGap, y - vGap);

        }
        if (root.getFemaleSpouse() != null) {
            getChildren().add(new javafx.scene.shape.Line(x - hGap, y, x, y));
            displayTree(root.getFemaleSpouse(), x - hGap, y);
            
        }
        if (root.getMaleSpouse() != null) {
            getChildren().add(new javafx.scene.shape.Line(x + hGap, y, x, y));
            displayTree(root.getMaleSpouse(), x + hGap, y);

        }
        if (root.getKids().isEmpty() == false) {
            for(int i = 0; i < root.getKids().size(); i++){
                    getChildren().add(new javafx.scene.shape.Line(x + (hGap * i), y + vGap, x, y));
                    displayTree(root.getKids().get(i), x + (hGap * i), y + vGap);
            }
        }
            

        // place nodes onto view
        TreeNodePane nodePane = new TreeNodePane(root);

        nodePane.setOnMouseClicked(e -> {
            TreeControl control = new TreeControl(root, this);
            control.showInputPane();
        });

        this.getChildren().add(nodePane);

        /*
        *  hShiftLeft and vShiftUp are a patch to compensate for the image's topleft
        *  corner being connected to lines. This finds the center of each image
        *  so we can shift its center to be positioned over the endpoints of lines
         */
        double hShiftLeft = nodePane.getView().getImage().getWidth() / 2;
        double vShiftUp = nodePane.getView().getImage().getHeight() / 2;

        nodePane.setLayoutX(x - hShiftLeft);
        nodePane.setLayoutY(y - vShiftUp);
    }

    public Person getRootPerson() {
        return rootPerson;
    }

    public void setRootPerson(Person rootPerson) {
        this.rootPerson = rootPerson;
    }

    public double getvGap() {
        return vGap;
    }

    public void setvGap(double vGap) {
        this.vGap = vGap;
    }

    public double gethGap() {
        return hGap;
    }

    public void sethGap(double hGap) {
        this.hGap = hGap;
    }

}
