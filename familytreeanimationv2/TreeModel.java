/*
 *
 */
package familytreeanimationv2;

/**
 *
 * @author Chris
 */
public class TreeModel {

    public TreeModel() {

    }

    public void changePictureIcon(Person person, java.io.File picFile) {
        String filePath = picFile.getAbsolutePath();
        String winPath = java.util.regex.Matcher.quoteReplacement(filePath);
        filePath = "file:" + winPath.replaceFirst("C", "c");

        System.out.println(filePath);

        person.setImagePath(filePath);
    }

    public Person addMother(Person person) {
        Person mother = new Person();
        mother.setfName("");
        mother.setmName("");
        mother.setlName("");
        mother.setSex(2);

        person.setMother(mother);
        return mother;
    }

    public Person addFather(Person person) {
        Person father = new Person();
        father.setfName("");
        father.setmName("");
        father.setlName("");
        father.setSex(1);

        person.setFather(father);
        return father;
    }

    public Person addMaleSpouse(Person person) {
        Person maleSpouse = new Person();
        maleSpouse.setfName("");
        maleSpouse.setmName("");
        maleSpouse.setlName("");
        maleSpouse.setSex(1);

        person.setMaleSpouse(maleSpouse);
        return maleSpouse;
    }

    public Person addFemaleSpouse(Person person) {
        Person femaleSpouse = new Person();
        femaleSpouse.setfName("");
        femaleSpouse.setmName("");
        femaleSpouse.setlName("");
        femaleSpouse.setSex(2);

        person.setFemaleSpouse(femaleSpouse);
        return femaleSpouse;
    }
    
//    public java.util.ArrayList addKid() {
//        
//    }
}
