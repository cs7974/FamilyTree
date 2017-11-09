/*
 * The person class contains everything that defines a person on the family tree.
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class Person implements java.io.Serializable {

    private int sex = 0;
    /**
     * zero for default. one for male. two for female
     */

    private String fName;
    private String mName;
    private String lName;

    private String imagePath;

    private int birthDate;
    private int birthMonth;
    private int birthYear;

    private int deathDate;
    private int deathMonth;
    private int deathYear;

    private Person mother;
    private Person father;
    private Person maleSpouse;
    private Person femaleSpouse;

    private java.util.ArrayList<Person> kids
            = new java.util.ArrayList<Person>();

    /**
     * list of people back to root
     */

    public Person() {

        this.imagePath = "images/x-office-address-book.png";

    }

    public String toString() {
        return this.fName + " " + this.mName + " " + this.lName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public java.util.ArrayList<Person> getKids() {
        java.util.ArrayList<Person> copyList = new java.util.ArrayList<>();
        copyList.addAll(this.kids);
        return copyList;
    }

    public void setKids(java.util.ArrayList<Person> kids) {
        this.kids.addAll(kids);
    }

    public void setKid(Person kid) {
        this.kids.add(kid);
    }

    /**
     * The getPath returns a copy of this Person list. This way each Person's
     * list stays in tact. ( ie not return path itself because other Person may
     * jack it up)
     */
//    public java.util.ArrayList<Person> getPath() {
//        java.util.ArrayList<Person> copyList = new java.util.ArrayList<Person>();
//        copyList.addAll(this.path);
//        return copyList;
//    }

    /**
     * The setPath copies the passed path to this Person list. This way each
     * Person's list stays in tact. ( ie not setting this list to some other
     * person list because some other Person may jack it up)
     */
//    public void setPath(java.util.ArrayList<Person> path) {
//
//        this.path.addAll(path);
//    }
//
//    public void addLeafToPath(Person person) {
//        this.path.add(person);
//    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
