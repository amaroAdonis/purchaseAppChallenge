package entities;

public class User {

    private String name;
    private Integer id;

    public User(){
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return getName()
                + ","
                +getId();

    }
}