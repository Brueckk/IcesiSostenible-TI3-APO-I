package model;


public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * The login function checks if the provided username and password match the stored username and
     * password, and returns true if they match, false otherwise.
     * 
     * @param userName The username entered by the user trying to log in.
     * @param password The password parameter is a String that represents the password entered by the
     * user during the login process.
     * @return The method is returning a boolean value. If the provided username and password match the
     * stored username and password, it will return true. Otherwise, it will return false.
     */
    public boolean login(String userName, String password){

        if (this.userName.equals(userName) && this.password.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



   

    
}
