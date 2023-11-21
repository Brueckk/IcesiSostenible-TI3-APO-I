package model;

public class Visitant extends User{
    
    public Visitant(String userName, String password){
        super(userName, password);
    }

    public boolean login(String userName, String password){
        return super.login(userName, password);
    }

    
}
