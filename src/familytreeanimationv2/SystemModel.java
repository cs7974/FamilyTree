/*
 * 
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class SystemModel {

    private double version = 1.0;

    public SystemModel() {

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

    public SystemUpdateClient update() {
        SystemUpdateClient client = new SystemUpdateClient(this.version);
        return client;
    }

}
