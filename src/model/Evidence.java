package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evidence {
    private String name;
    private String description;
    private Date registerDate;
    
    public Evidence(String name, String description, Date registerDate){
        this.name = name; 
        this.description = description;
        this.registerDate = registerDate;
    }

    /**
     * The function takes a string representing a date in the format "dd/MM/yyyy" and converts it to a
     * Date object.
     * 
     * @param date The parameter "date" is a string representing a date in the format "dd/MM/yyyy".
     */
    public void passDate(String date){

        Date dateFinalFormat = null;
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");    
            dateFinalFormat = dateFormat.parse(date);

        } catch (ParseException e){
            e.printStackTrace();
        }

       this.registerDate = dateFinalFormat;
    }

    public String toString(){
        return "Name: " + name + "\nDescription: " + description + "\nRegister Date: " + registerDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

}
