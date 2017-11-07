/*
 * The person class contains everything that defines a person on the family tree.
 */
package familytreeanimationv2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    private int birthDay =0;
    private int birthMonth =0;
    private int birthYear =0;
    private LocalDate birthDate;
    
    private int deathDay =0;
    private int deathMonth =0;
    private int deathYear =0;
    private LocalDate deathDate;
    private long Age =0;
    
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

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDate) {
        this.birthDay= birthDate;
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

    public int getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(int deathDate) {
        this.deathDay = deathDate;
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
       return this.kids;
    }

    public void setKids(java.util.ArrayList<Person> kids) {
        this.kids.addAll(kids);
    }

    public void setKid(Person kid) {
        this.kids.add(kid);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
     public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
        this.birthDate = date;
    }
     public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate date) {
        this.deathDate = date;
    }
     public long getAge() {
        return Age;
        
    }

    public void setAge() {
        if(birthDay == 0 && birthMonth == 0 && birthYear == 0){
            Age = 0;
        }else if(deathDay == 0 && deathMonth == 0 && deathYear == 0){
            Age = ChronoUnit.YEARS.between(this.birthDate, LocalDate.now());
        }else{
            Age = ChronoUnit.YEARS.between(this.birthDate, this.deathDate);
        }  
        System.out.print(Age + "\n");
    }
}
