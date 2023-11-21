package model;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Project {
    private boolean status;
    private String name;
    private String id;
    private String description;
    private Date startDate; 
    private Date finalDate;
    private ArrayList<Evidence> evidence;

    public Project(boolean status, String name, String id, String description, Date startDate, Date finalDate, Evidence evidence){
        this.status = status;
        this.name = name;
        this.id = id; 
        this.description = description;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.evidence = new ArrayList<>();
    }

    /**
     * The function takes two date strings as input, converts them to Date objects using a specified
     * date format, and assigns them to instance variables.
     * 
     * @param startDate The startDate parameter is a string representing the start date in the format
     * "dd/MM/yyyy".
     * @param finalDate The finalDate parameter is a string representing the final date.
     */
    public void passDate(String startDate, String finalDate){

        Date startDateFormat = null;
        Date finalDateFormat = null;
    
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
    
            if (startDate != null){
                startDateFormat = dateFormat.parse(startDate);
            }
    
            if (finalDate != null){
                finalDateFormat = dateFormat.parse(finalDate);
            }
    
        } catch (ParseException e){
            e.printStackTrace();
        }
    
        if (startDateFormat != null){
            this.startDate = startDateFormat;
        }
    
        if (finalDateFormat != null){
            this.finalDate = finalDateFormat;
        }
    }

    /**
     * The function adds a new review as evidence, with a given name, description, and date, for a
     * specific collector.
     * 
     * @param nameP The name of the collector to whom the evidence is being added.
     * @param name The name of the collector who is adding the evidence.
     * @param description The description parameter is a String that represents the description of the
     * evidence being added.
     * @param date The "date" parameter is a string that represents the date when the evidence was
     * collected.
     */
    public void addEvidencesByCollector(String nameP, String name, String description, String date){

        if (this.name.equalsIgnoreCase(nameP)){
            Review review = new Review(false, name, description, null);
            review.passDate(date);

            evidence.add(review);
        }
    }

    /**
     * The function `addEvidencesByResearcher` adds evidences (files or reviews) to a collection if the
     * researcher's name matches and the evidence type and status are valid.
     * 
     * @param nameP The name of the researcher who is adding the evidence.
     * @param name The name of the evidence being added.
     * @param description The description parameter is a String that represents the description of the
     * evidence being added.
     * @param date The date parameter is a string representing the date when the evidence was added.
     * @param type The type parameter specifies the type of evidence being added. It can be either
     * "file" or "review".
     * @param typeFile The type of file being added as evidence. It can be "audio", "video", "pics",
     * "text", or "results report".
     * @param url The "url" parameter is a string that represents the URL or file path of the evidence
     * being added.
     * @param status The "status" parameter is used to indicate the status of the evidence. It can be
     * either "active" or "inactive".
     */
    public void addEvidencesByResearcher(String nameP, String name, String description, String date, String type, String typeFile, String url, String status){
       
        if (type.equalsIgnoreCase("file")){
            if (this.name.equalsIgnoreCase(nameP)){
                FileType file = null;
                switch(typeFile.toLowerCase()){
                    case "audio" -> {file = FileType.AUDIO;}
                    case "video" -> {file = FileType.VIDEO;}
                    case "pics" -> {file = FileType.PICS;}
                    case "text" -> {file = FileType.TEXT;}
                    case "results report" -> {file = FileType.RESULTS_REPORT;}
                }

                File fileO = new File(name, description, null, url, file);
                fileO.passDate(date);

                evidence.add(fileO);
            }

        } else if (type.equalsIgnoreCase("review")){

            if (this.name.equalsIgnoreCase(nameP)){
                boolean statusAct = false;

                switch(status.toLowerCase()){
                    case "active" -> {statusAct = true;}
                    case "inactive" -> {statusAct = false;}
                }

                Review review = new Review(statusAct, name, description, null);
                review.passDate(date);

                evidence.add(review);
            }
        }
    }

    /**
     * The function `modifyEvidenceProject` modifies the characteristics of an evidence object based on
     * the provided name, characteristic, and new value.
     * 
     * @param nameE The name of the evidence project that you want to modify.
     * @param caract The `caract` parameter represents the characteristic of the evidence that you want
     * to modify. It can be one of the following values: "name", "description", "register date",
     * "status", "type", or "url".
     * @param newC The new value that you want to set for the specified characteristic of the evidence
     * project.
     * @return The method is returning a String message indicating whether the evidence was modified
     * successfully or if the characteristic provided is not valid.
     */
    public String modifyEvidenceProject(String nameE, String caract, String newC){

        for (Evidence i : evidence){
            if (i.getName().equalsIgnoreCase(nameE)){
                switch(caract.toLowerCase()){
                    case "name" -> {i.setName(newC);}
                    case "description" -> {i.setDescription(newC);}
                    case "register date" -> {i.passDate(newC);}
                    case "status" -> {
                        Review review = null;
                        if(i instanceof Review){
                            review = (Review) i;
                        }

                        if (newC.equalsIgnoreCase("active")){
                            review.setStatus(true);
                        } else {
                            review.setStatus(false);
                        }
                    }
                    case "type" -> {
                        File file = null;
                        if (i instanceof File){
                            file = (File) i;
                        }

                        if (newC.equalsIgnoreCase("audio")){
                            file.setType(FileType.AUDIO);
                        } else if (newC.equalsIgnoreCase("video")){
                            file.setType(FileType.VIDEO);
                        } else if (newC.equalsIgnoreCase("pics")){
                            file.setType(FileType.PICS);
                        } else if (newC.equalsIgnoreCase("text")){
                            file.setType(FileType.TEXT);
                        } else {
                            file.setType(FileType.RESULTS_REPORT);
                        }
                    }
                    case "url" -> {
                        if (i instanceof File){
                            File file = (File) i;
                            file.setUrl(newC);
                        }
                    } 
                    default -> {return "The caracteristic isn't valid";}
                }
            }
        }

        return "The evidence was modified successfully\n";
    }

    /**
     * The function removes an evidence object from a list of evidence based on its name.
     * 
     * @param nameE The name of the evidence project that you want to remove.
     */
    public void removeEvidenceProject(String nameE){

        for (Evidence i : evidence){
            if (i.getName().equalsIgnoreCase(nameE)){
                evidence.remove(i);
                break;
            }
        }
    }

    public String toStringImprove(){
        return "Status: " + status + "\nName: " + name + "\nId: " + id 
        + "\nDescription: " + description + "\nStart date: " + startDate 
        + "\nFinal date: " + finalDate; 
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public ArrayList<Evidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(ArrayList<Evidence> evidence) {
        this.evidence = evidence;
    }

}
