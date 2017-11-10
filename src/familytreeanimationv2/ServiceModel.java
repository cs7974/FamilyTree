/*
 *  Service model handles all file write, read, and transfer opperations
 *  for the overall system.
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class ServiceModel {

    private double version = 1.0;

    public ServiceModel() {

    }

    public void saveTree(java.io.File file, Person person) {

        try (java.io.ObjectOutputStream output = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            output.writeObject(person);
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        }

    }

    public Person loadTree(java.io.File file) {

        Person root = new Person();
        try (java.io.ObjectInputStream input = new java.io.ObjectInputStream(new java.io.FileInputStream(file))) {
            root = (Person) input.readObject();

            return root;

        } catch (java.io.IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return root;
    }

    public UpdateClient update() {
        UpdateClient client = new UpdateClient(this.version);
        return client;
    }

}
