/*
 * 
 */
package systemupdateserver;

import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class SystemUpdateServer extends Application {
    // Text area for displaying contents

    private TextArea ta = new TextArea();

    // Number a client
    private int clientNo = 0;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Thread thread = new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                ta.appendText("Update Server started at "
                        + new Date() + '\n');

                while (true) {
                    // Listen for a new connection request
                    Socket socket = serverSocket.accept();

                    // Increment clientNo
                    clientNo++;

                    Platform.runLater(() -> {
                        // Display the client number
                        ta.appendText("Starting thread for client " + clientNo
                                + " at " + new Date() + '\n');

                        // Find the client's host name, and IP address
                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText("Client " + clientNo + "'s host name is "
                                + inetAddress.getHostName() + "\n");
                        ta.appendText("Client " + clientNo + "'s IP Address is "
                                + inetAddress.getHostAddress() + "\n");
                    });

                    // Create and start a new thread for the connection
                    new Thread(new HandleAClient(socket)).start();
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        });
        thread.setDaemon(true);
        thread.start();

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("Update Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {

        private Socket socket; // A connected socket

        /**
         * Construct a thread
         */
        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /**
         * Run a thread
         */
        public void run() {
            try {
                // Create data input and output streams
                DataInputStream inputFromClient = new DataInputStream(
                        socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive version from the client
                    double version = inputFromClient.readDouble();

                    ta.appendText("The client is reporting version " + version + "\n");

                    // Compare current version from client to latest version in repo
                    boolean updateReq = needsUpdate(version);

                    // Send result back to the client
                    outputToClient.writeBoolean(updateReq);

                   
                    Platform.runLater(() -> {
                        ta.appendText("Update available for client? " + updateReq + "\n");
                    });
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean needsUpdate(double version) {
        boolean needed = false;

        File folder = new File("C://Program Files//Family Tree");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                String stripToken = listOfFiles[i].getName().substring(listOfFiles[i].getName().indexOf('V') + 1,
                        listOfFiles[i].getName().indexOf('.'));
                System.out.println(stripToken);

                double parsedToken = Double.parseDouble(stripToken.replace('_', '.'));

                System.out.println(parsedToken);
                if (version < parsedToken) {
                    needed = true;
                }

            } else if (listOfFiles[i].isDirectory()) {
                // System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        return needed;
    }

    /**
     * The main method is only needed for the IDE with limited JavaFX support.
     * Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
