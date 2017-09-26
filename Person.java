/*
 * The person class contains everything that defines a person on the family tree.
 */
package familytreeanimation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Person implements Comparable<Person> {

    private String fName = "New";
    private String mName = "";
    private String lName = "Person";

    private int birthDate = 0;
    private int birthMonth = 0;
    private int birthYear = 0;

    private int deathDate = 0;
    private int deathMonth = 0;
    private int deathYear = 0;

    private Person mother;
    private Person father;
    private Person maleSpouse;
    private Person femaleSpouse;

    private List<Person> kids = new ArrayList<>();

    private List<Person> path = new ArrayList<>(); //

    private javafx.scene.image.Image image = new javafx.scene.image.Image("images/default.png");
    private javafx.scene.image.ImageView view = new javafx.scene.image.ImageView(image);

    public Person() {
        path.add(this);
    }

    /*
     * This will check the current person to another and 
     * assign a int based on lineage. (Self is 0, maleSpouse is -1, 
     * father is -2, mother is -3, femaleSpouse is -4, and kids will be assigned
     * a neg int depending on which kid it is. It will return 404 if not found (for debugging).
     */
    @Override
    public int compareTo(Person o) {

        if (o.equals(this)) {
            return 0;
        } else if (o.path.contains(this.maleSpouse)) {
            return -1;
        } else if (o.path.contains(this.father)) {
            return -2;
        } else if (o.path.contains(this.mother)) {
            return -3;
        } else if (o.path.contains(this.femaleSpouse)) {
            return -4;
        } else {
            for (Person kid : kids) {
                if (o.path.contains(kid)) {
                    return kids.indexOf(kid) + 1;
                }
            }
        }
        return 404;
    }

    @Override
    public String toString() {
        return this.fName + " " + this.mName + " " + this.lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(int deathDate) {
        this.deathDate = deathDate;
    }

    public int getDeathMonth() {
        return deathMonth;
    }

    public void setDeathMonth(int deathMonth) {
        this.deathMonth = deathMonth;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMaleSpouse() {
        return maleSpouse;
    }

    public void setMaleSpouse(Person maleSpouse) {
        this.maleSpouse = maleSpouse;
    }

    public Person getFemaleSpouse() {
        return femaleSpouse;
    }

    public void setFemaleSpouse(Person femaleSpouse) {
        this.femaleSpouse = femaleSpouse;
    }

    public List<Person> getKids() {
        return kids;
    }

    public void setKids(List<Person> kids) {
        this.kids = kids;
    }

    public void setKid(Person kid) {
        this.kids.add(kid);
    }

    public List<Person> getPath() {
        return path;
    }

    public void setPath(List<Person> path) {
        this.path = path;
    }

    public javafx.scene.image.Image getImage() {
        return image;
    }

    public void setImage(javafx.scene.image.Image image) {
        this.image = image;
    }

    public javafx.scene.image.ImageView getView() {
        return view;
    }

    public void setView(javafx.scene.image.ImageView view) {
        this.view = view;
    }

}
