package model;

import java.util.Date;

public class File extends Evidence{
    private FileType type;
    private String url;

    public File(String name, String description, Date registerDate, String url, FileType type){
        super(name, description, registerDate);
        this.url = url;
        this.type = type;
    }

    public void passDate(String date){
        super.passDate(date);
    }

    @Override
    public String toString(){ 
        return "Name: " + super.getName() + "\nDescription: " + super.getDescription() + "\nRegister Date: " + super.getRegisterDate() + "\nURL: " + url + "\nType: " + type;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
