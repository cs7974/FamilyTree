/*
 * This is the individual person's pane
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeNodePane extends javafx.scene.layout.StackPane {

    final private Person person;
    final private javafx.scene.image.Image image;
     private javafx.scene.image.ImageView imageView;
    final private javafx.scene.control.Label label;

    public TreeNodePane(Person person) {
        this.person = person;

        this.image = new javafx.scene.image.Image(person.getImagePath());
        this.imageView = new javafx.scene.image.ImageView(image);
        this.label = new javafx.scene.control.Label(person.toString(), this.imageView);
        label.setFont(new javafx.scene.text.Font("Georgia", 12));
        label.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);

        this.getChildren().add(label);

        this.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        this.setStyle("-fx-border-color: palegreen; -fx-background-color: #b7d09e");
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
