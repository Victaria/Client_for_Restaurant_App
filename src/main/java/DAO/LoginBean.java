package DAO;

public class LoginBean {
    private String email;
    private String password;
    private String userName;

    public String getEmail() {
        return email;
    }
    public void setEmail(String mail) {
        this.email = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        this.userName = name;
    }
}
