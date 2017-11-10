/*
 * 
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
