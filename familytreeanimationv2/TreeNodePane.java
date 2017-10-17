/*
 * 
 */
package familytreeanimationv2;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
        this.imageView = new javafx.scene.image.ImageView(image);
        this.label = new javafx.scene.control.Label(person.toString(), this.imageView);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);

        this.getChildren().add(label);

        this.setStyle("-fx-border-color: black; -fx-background-color: lightblue");
    }

    public Person getPerson() {
        return this.person;
    }

    public Label getLabel() {
        return label;
    }

    public ImageView getView() {
        return imageView;
    }

    public void setView(ImageView view) {
        this.imageView = view;
    }

}
