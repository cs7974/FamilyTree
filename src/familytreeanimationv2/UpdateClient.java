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
public class UpdateClient extends Application {

    private String serverIP = "127.0.0.1";
    private int serverPort = 8000;
    private String fileOutput = "C:\\Program Files\\Family Tree\\Downloads\\update.java";

    double version;

    public UpdateClient(double version) {
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

        byte[] aByte = new byte[1];
        int bytesRead;

        // IO streams
        java.io.DataOutputStream toServer = null;
        java.io.InputStream fromServer = null;
        Socket socket = null;
        try {
            // Create a socket to connect to the server
            socket = new Socket(serverIP, serverPort);
            // Socket socket = new Socket("130.254.204.36", 8000);
            // Socket socket = new Socket("drake.Armstrong.edu", 8000);

            // Create an input stream to receive data from the server
            fromServer = socket.getInputStream();

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());

            ta.appendText("Attempting to connect to update server...\n");
        } catch (IOException ex) {
            ta.appendText(ex.toString() + '\n');
        }

        java.io.ByteArrayOutputStream bytesOut = new java.io.ByteArrayOutputStream();

        try {

            // write current version to server
            toServer.writeDouble(version);
            toServer.flush();

            ta.appendText("Connected! Current version sent to server. \n");

        } catch (IOException ex) {
            System.err.println(ex);
        }

        if (fromServer != null) {
            java.io.FileOutputStream fileOut = null;
            java.io.BufferedOutputStream buffOut = null;

            try {
                ta.appendText("Attempting to download update. \n");

                fileOut = new java.io.FileOutputStream(fileOutput);
                buffOut = new java.io.BufferedOutputStream(fileOut);
                bytesRead = fromServer.read(aByte, 0, aByte.length);

                do {
                    buffOut.write(aByte);
                    bytesRead = fromServer.read(aByte);

                } while (bytesRead != -1);

                buffOut.write(bytesOut.toByteArray());
                buffOut.flush();
                buffOut.close();
                socket.close();

                ta.appendText("Update downloaded successfully. Connection has been closed. \n");

            } catch (java.io.FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        } else {
            ta.appendText("No update available at this time. \n");

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
