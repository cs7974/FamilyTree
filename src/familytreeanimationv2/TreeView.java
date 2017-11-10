/*
 *
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeView extends javafx.scene.control.ScrollPane {

    private Person rootPerson;
    private double vGap = 200;
    private double hGap = 200;

    public TreeView() {
    }

    public TreeView(Person person) {
        this.rootPerson = person;

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
            javafx.scene.shape.Line l = new javafx.scene.shape.Line(x - hGap, y - vGap, x, y);
            l.setStroke(javafx.scene.paint.Color.DARKOLIVEGREEN);
            l.setStrokeWidth(3);
            l.setSmooth(true);
            getChildren().add(l);
            displayTree(root.getMother(), x - hGap, y - vGap);
        }
        if (root.getFather() != null) {
            javafx.scene.shape.Line l = new javafx.scene.shape.Line(x + hGap, y - vGap, x, y);
            l.setStroke(javafx.scene.paint.Color.DARKOLIVEGREEN);
            l.setStrokeWidth(3);
            l.setSmooth(true);
            getChildren().add(l);
            displayTree(root.getFather(), x + hGap, y - vGap);

        }
        if (root.getFemaleSpouse() != null) {
            javafx.scene.shape.Line l = new javafx.scene.shape.Line(x - hGap, y, x, y);
            l.setStroke(javafx.scene.paint.Color.DARKOLIVEGREEN);
            l.setStrokeWidth(3);
            l.setSmooth(true);
            getChildren().add(l);
            displayTree(root.getFemaleSpouse(), x - hGap, y);

        }
        if (root.getMaleSpouse() != null) {
            javafx.scene.shape.Line l = new javafx.scene.shape.Line(x + hGap, y, x, y);
            l.setStroke(javafx.scene.paint.Color.DARKOLIVEGREEN);
            l.setStrokeWidth(3);
            l.setSmooth(true);
            getChildren().add(l);
            displayTree(root.getMaleSpouse(), x + hGap, y);

        }
        if (root.getKids().isEmpty() == false) {
            for (int i = 0; i < root.getKids().size(); i++) {
                javafx.scene.shape.Line l = new javafx.scene.shape.Line(x + (hGap * i), y + vGap, x, y);
                l.setStroke(javafx.scene.paint.Color.DARKOLIVEGREEN);
                l.setStrokeWidth(3);
                l.setSmooth(true);
                getChildren().add(l);
                displayTree(root.getKids().get(i), x + (hGap * i), y + vGap);
            }
        }

        // place nodes onto view
        TreeNodePane nodePane = new TreeNodePane(root);

        
        nodePane.setOnMouseClicked(e -> {
            TreeControl control = new TreeControl(root, this);
            control.showInputPane();
        });


        /*
        *  hShiftLeft and vShiftUp are a patch to compensate for the image's topleft
        *  corner being connected to lines. This finds the center of each image
        *  so we can shift its center to be positioned over the endpoints of lines
         */
//        double hShiftLeft = nodePane.getView().getImage().getWidth() / 2;
//        double vShiftUp = nodePane.getView().getImage().getHeight() / 2;

        nodePane.setLayoutX(x);
        nodePane.setLayoutY(y);
        
                this.getChildren().add(nodePane);

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
