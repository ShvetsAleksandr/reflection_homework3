package reflection_homework3;

public class Main {
    public static void main(String[] args) throws Exception {

        Person person = new Person();
        Person person2;
        person.setName("Sasha");
        person.setAge(27);
        person.setMarried(false);

        SerializeDeserialize.serialize("A:\\person.txt", person);
        person2 = SerializeDeserialize.deserialize("A:\\person.txt", Person.class);
        System.out.printf(" Name : %s %n age : %d %n married %b", person2.getName(),
                                                                  person2.getAge(),
                                                                  person2.isMarried());
    }
}
