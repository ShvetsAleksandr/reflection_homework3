package reflection_homework3;

public class Person {

    @Save
    private String name;
    @Save
    private int age;
    @Save
    private boolean married;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return married;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setMarried(boolean married){
        this.married = married;
    }
}
