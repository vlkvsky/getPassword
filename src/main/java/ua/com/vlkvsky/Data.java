package ua.com.vlkvsky;

public class Data {

    private String Source;
    private String Login;
    private String Password;

    public Data() {
        Source = "";
        Login = "";
        Password = "";
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        this.Source = source;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}