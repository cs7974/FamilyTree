/*
 * This is the class that contains the UI that will display family member
 * nodes and relationships .
 */
package familytreeanimation;

import javafx.geometry.Pos;
import javafx.scene.shape.Line;

/**
 *
 * @author User
 */
public class FTView extends javafx.scene.layout.GridPane {
// maybe we could use gridpane here for the arrangement 
//    of individuals on the family tree

    private int rowIndex = 10;
    private int columnIndex = 10;
    private int rowSpan = 2;
    private int columnSpan = 2;

    private Person root = new Person();
    private double vGap = 50; // Gap between two levels in a tree

    public FTView() {

    }

    public FTView(Person root) {
        this.root = root;

    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (root != null) {
            // Display tree recursively    
            displayTree(root, rowIndex, columnIndex, rowSpan, columnSpan);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(Person root,
            int column, int row, int hGap, int vGap) {
        if (root.getMaleSpouse() != null) {
            // Draw a line to the left node
            getChildren().add(new Line(row, column, row, column - 1));
            // Draw the left subtree recursively
            displayTree(root.getMaleSpouse(), row, column - 1, hGap, vGap);
        }

        if (root.getFemaleSpouse() != null) {
            // Draw a line to the right node
            getChildren().add(new Line(row, column, row, column - 1));
            // Draw the right subtree recursively
            displayTree(root.getFemaleSpouse(), row, column - 1, hGap, vGap);
        }

        if (root.getFather() != null) {
            // Draw a line to the upleft node
            getChildren().add(new Line(row, column, row - 1, column - 1));
            // Draw the upleft subtree recursively
            displayTree(root.getFather(), row - 1, column - 1, hGap, vGap);
        }

        if (root.getMother() != null) {
            // Draw a line to the upright node
            getChildren().add(new Line(row, column, row - 1, column + 1));
            // Draw the upright subtree recursively
            displayTree(root.getMother(), row - 1, column + 1, hGap, vGap);
        }

        if (!root.getKids().isEmpty()) {
            for (Person kid : root.getKids()) {
                // Draw a line to the upleft node
                getChildren().add(new Line(row, column, row + 1, column));
                // Draw the right subtree recursively
                displayTree(kid, row, column, hGap, vGap);
            }

        }

        // Display a node
        PersonBox pbox = new PersonBox(root);
        this.add(pbox, row, column, hGap, vGap);
        this.setAlignment(Pos.CENTER);
        pbox.setOnMouseClicked(e -> {

            System.out.println("You Clicked " + root.toString());

            UpdatePane updatePane = new UpdatePane(root, this);

            root.setfName(updatePane.getFnameTF());
            root.setmName(updatePane.getMnameTF());
            root.setlName(updatePane.getLnameTF());

            updatePane.showUpdatePane();

        });

    }
}
