package Eshop;

public class LoginResult {

    private final boolean successfulLogin;
    private final boolean isAdmin;

    public LoginResult(boolean successfulLogin, boolean isAdmin) {
        this.successfulLogin = successfulLogin;
        this.isAdmin = isAdmin;
    }

    public boolean isSuccessfulLogin() {
        return successfulLogin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
