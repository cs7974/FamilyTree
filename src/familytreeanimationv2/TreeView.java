/*
 *
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeView extends javafx.scene.control.ScrollPane {

    private Person person;
    private double vGap = 200;
    private double hGap = 200;

    public TreeView() {
    }

    public TreeView(Person person) {
        this.person = person;

    }

    public void displayTree() {

        this.getChildren().clear();
        if (this.person != null) {

            displayTree(person, this.getWidth() / 2, this.getHeight() / 2);
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
        return person;
    }

    public void setRootPerson(Person person) {
        this.person = person;
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
    
    private class TreeNodePane extends javafx.scene.layout.StackPane {

    final private Person person;
    final private javafx.scene.image.Image image;
    private javafx.scene.image.ImageView imageView;
    final private javafx.scene.control.Label label;

    public TreeNodePane(Person person) {
        this.person = person;

        javafx.scene.image.ImageView temp = new javafx.scene.image.ImageView(this.person.getImagePath());
        temp.setPreserveRatio(true);
        temp.setFitWidth(100);
        temp.setFitHeight(100);
        this.image = temp.snapshot(null, null);
        this.imageView = new javafx.scene.image.ImageView(image);

        this.label = new javafx.scene.control.Label(person.toString(), this.imageView);
        this.label.setFont(new javafx.scene.text.Font("Georgia", 12));
        this.label.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);

        this.getChildren().add(this.label);

        this.label.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        this.label.setStyle("-fx-border-color: palegreen; -fx-background-color: #b7d09e");
    }

    public Person getPerson() {
        return this.person;
    }

    public javafx.scene.control.Label getLabel() {
        return label;
    }

    public javafx.scene.image.ImageView getView() {
        return imageView;
    }

    public void setView(javafx.scene.image.ImageView view) {
        this.imageView = view;
    }

}


}
