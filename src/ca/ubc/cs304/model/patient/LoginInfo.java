package ca.ubc.cs304.model.patient;

/**
 * The intent for this class is to update/store information about a single patient's login info
 * (A PatientAccount BCNF)
 */
public class LoginInfo {
    private final String username;
    private String userPassword;

    public LoginInfo(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
