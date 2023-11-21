package model;

import java.util.Date;

public class Review extends Evidence{
    private boolean status;

    public Review(boolean status, String name, String description, Date registerDate){
        super(name, description, registerDate);
        this.status = status;
    }

    /**
     * The function passDate in Java passes a date parameter to a superclass.
     * 
     * @param date The "date" parameter is a string that represents a date.
     */
    public void passDate(String date){
        super.passDate(date);
    }

    @Override
    public String toString(){
        return "Name: " + super.getName() + "\nDescription: " + super.getDescription() + "\nRegister Date: " + super.getRegisterDate() + "\nStatus: " + status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
