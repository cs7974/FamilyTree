/*
 * 
 */
package familytreeanimationv2;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class SystemUpdateClient extends Application {

    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    ObjectOutputStream objToServer = null;
    ObjectInputStream objFromServer = null;

    double version;

    public SystemUpdateClient(double version) {
        this.version = version;
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Panel p to hold the label and text field
        BorderPane mainPane = new BorderPane();
        // Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Update Client"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());

            ta.appendText("Attempting to connect to update server...\n");
        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }

        try {

            // write current version to server
            toServer.writeDouble(version);
            toServer.flush();

            boolean update = fromServer.readBoolean();

            if (update == true) {
                ta.appendText("An update is available.\n");
               
            }
            if (update == false) {
                ta.appendText("No update is available at this time.\n");
            }

        } catch (IOException ex) {
            System.err.println(ex);
        } 

    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
