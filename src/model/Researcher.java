package model;

public class Researcher extends User {
    private String name;
    private String email;
    private String phone;
    private String areaU;
    private String charge;

    public Researcher(String userName, String password, String name, String email, String phone, String areaU, String charge){
        super(userName, password);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.areaU = areaU;
        this.charge = charge;   
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAreaU() {
        return areaU;
    }

    public void setAreaU(String areaU) {
        this.areaU = areaU;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }



   
    
    
}
