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
        mother.setSex(2);

        person.setMother(mother);
        return mother;
    }

    public Person addFather(Person person) {
        Person father = new Person();
        father.setSex(1);

        person.setFather(father);
        return father;
    }

    public Person addMaleSpouse(Person person) {
        Person maleSpouse = new Person();
        maleSpouse.setSex(1);

        person.setMaleSpouse(maleSpouse);
        return maleSpouse;
    }

    public Person addFemaleSpouse(Person person) {
        Person femaleSpouse = new Person();
        femaleSpouse.setSex(2);

        person.setFemaleSpouse(femaleSpouse);
        return femaleSpouse;
    }

    public Person addKid(Person person) {
        Person kid = new Person();
        person.setKid(kid);
        return kid;
    }
}
