/*
 * This class will contain visual elements that will be displayed on each
 * node in the family tree. It will contain the picture, name, and anything
 * else that we would like to show the user when viewing the tree and all 
 * of the connected nodes. 
 */
package familytreeanimation;

import javafx.scene.control.ContentDisplay;

/**
 *
 * @author User
 */
public class PersonBox extends javafx.scene.layout.Pane {

    Person person;

    public PersonBox() {

    }

    public PersonBox(Person person) {

        this.person = person;

        javafx.scene.control.Label label
                = new javafx.scene.control.Label(this.person.toString(), this.person.getView());
        label.setContentDisplay(ContentDisplay.BOTTOM);

        this.getChildren().add(label);

    }

}
