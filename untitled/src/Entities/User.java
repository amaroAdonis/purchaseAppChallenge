package Entities;

public class User {

    private String userName;
    private Integer userId;

    public User(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User name: "
                + userName
                + '\''
                + ", User ID: " + userId;
    }
}
