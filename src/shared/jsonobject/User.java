package shared.jsonobject;

/**
 * Created by Josh on 1/31/2016.
 */
public class User {
    private String username, password;
    public User(String givenUser, String givenPass){
        username = givenUser;
        password = givenPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
