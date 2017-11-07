/*
 * 
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeNodePane extends javafx.scene.layout.StackPane {

    private Person person;
    private javafx.scene.image.Image image;
    private javafx.scene.image.ImageView imageView;
    private javafx.scene.control.Label label;

    public TreeNodePane(Person person) {
        this.person = person;
        this.image = new javafx.scene.image.Image(person.getImagePath());
        javafx.scene.image.ImageView temp = new javafx.scene.image.ImageView(image);
        temp.setPreserveRatio(true);
        temp.setFitWidth(100);
        temp.setFitHeight(100);
        image = temp.snapshot(null, null);
        this.imageView = new javafx.scene.image.ImageView(image);
        this.label = new javafx.scene.control.Label(person.toString(), this.imageView);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);
        this.getChildren().add(label);

        this.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
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
