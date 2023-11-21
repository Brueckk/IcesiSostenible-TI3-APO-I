package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Controller {
    private Pillar[] pillars;
    private ArrayList<User> users;
    private ArrayList<User> test;
    private InterestPoint [][] map;

    public Controller(){
        this.pillars = new Pillar[]
        {new Pillar(PillarType.BIODIVERSITY), 
        new Pillar(PillarType.WATER), 
        new Pillar(PillarType.WASTE_TREATMENT), 
        new Pillar(PillarType.ENERGY)};

        this.users = new ArrayList<>();
        this.test = new ArrayList<>();
        
        this.map = new InterestPoint[20][20];
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                this.map[i][j] = null;
            }
        }
    }

    /**
     * The function registers a user visitant by creating a new Visitant object with the given username
     * and password, adding it to a list of users, and returning a success message.
     * 
     * @param userName The username of the visitant that is being registered. It is a String value.
     * @param password The password parameter is a String that represents the password chosen by the
     * user visitant during registration.
     * @return The method is returning a string message stating that the user visitant was registered.
     */
    public String registerUserVisitant(String userName, String password){
        Visitant visitant = new Visitant(userName, password);
        users.add(visitant);

        return "The user visitant was registered\n";
    }

    /**
     * The function registers a user information collector by creating an instance of the
     * InformationCollector class and adding it to a list of users.
     * 
     * @param userName The username of the user information collector. It is used to uniquely identify
     * the user.
     * @param password The password parameter is a String that represents the password of the user.
     * @param name The name parameter is a String that represents the name of the user.
     * @param email The email parameter is a string that represents the email address of the user.
     * @param phone The "phone" parameter is a String that represents the phone number of the user.
     * @return The method is returning a string message stating that the user information collector was
     * registered.
     */
    public String registerUserInformationCollector(String userName, String password, String name, String email, String phone){
        InformationCollector informationCollector = new InformationCollector(userName, password, name, email, phone);
        users.add(informationCollector);

        return "The user information collector was registered\n"; 
    }

    /**
     * The function registers a user researcher by creating a new Researcher object and adding it to a
     * list of users.
     * 
     * @param userName The username of the researcher being registered.
     * @param password The password parameter is a String that represents the password for the
     * researcher's account.
     * @param name The name parameter is a String that represents the name of the researcher.
     * @param email The email parameter is a string that represents the email address of the researcher
     * being registered.
     * @param phone The "phone" parameter is a string that represents the phone number of the
     * researcher being registered.
     * @param areaU The "areaU" parameter represents the area of expertise of the researcher. It could
     * be a specific field or subject that the researcher specializes in.
     * @param charge The "charge" parameter refers to the position or role of the researcher. It could
     * be their job title or the specific role they have within their research field.
     * @return The method is returning a string message stating that the user researcher was
     * registered.
     */
    public String registerUserResearcher(String userName, String password, String name, String email, String phone, String areaU, String charge){
        Researcher researcher = new Researcher(userName, password, name, email, phone, areaU, charge);
        users.add(researcher);

        return "The user researcher was registered\n";
    }

    /**
     * The function checks if a user with a given username is a visitant.
     * 
     * @param userName The parameter "userName" is a String that represents the username of a user.
     * @return The method is returning a boolean value. It returns true if there is a user with the
     * given username who is an instance of the Visitant class. Otherwise, it returns false.
     */
    public boolean identifyVisitant(String userName){

        for (User i : users){
            if (i.getUserName().equalsIgnoreCase(userName)){
                if (i instanceof Visitant){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * The function checks if a user with a given username is an instance of the InformationCollector
     * class.
     * 
     * @param userName The username of the user we want to check if they are an InformationCollector.
     * @return The method is returning a boolean value. It returns true if there is a user with the
     * given username who is an instance of the InformationCollector class. Otherwise, it returns
     * false.
     */
    public boolean identifyInformationCollector(String userName){

        for (User i : users){
            if (i.getUserName().equalsIgnoreCase(userName)){
                if (i instanceof InformationCollector){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * The function checks if a user with a given username is a researcher.
     * 
     * @param userName The username of the user we want to check if they are a researcher.
     * @return The method is returning a boolean value. It returns true if there is a user with the
     * given username who is also an instance of the Researcher class. Otherwise, it returns false.
     */
    public boolean identifyResearcher(String userName){

        for (User i : users){
            if (i.getUserName().equalsIgnoreCase(userName)){
                if (i instanceof Researcher){
                    return true;
                }
            } 
        }

        return false;
    }

    /**
     * The function takes a status as input and returns true if the status is "active", false if the
     * status is "inactive", and throws an exception if the status is invalid.
     * 
     * @param status The status parameter is a string that represents the current status. It can be
     * either "active" or "inactive".
     * @return The method is returning a boolean value. If the status is "active", it will return true.
     * If the status is "inactive", it will return false. If the status is neither "active" nor
     * "inactive", it will throw an IllegalArgumentException.
     */
    public boolean secureStatus(String status){

        switch (status.toLowerCase()){
            case "active" -> {return true;} 
            case "inactive" -> {return false;}
            default -> {throw new IllegalArgumentException("The status is invalid");}
        }
    }

    /**
     * The function "securePillar" checks if a given string matches one of the four specified pillars
     * and returns true if it does, otherwise it returns false.
     * 
     * @param pillar A string representing the pillar to be checked for security.
     * @return The method `securePillar` returns a boolean value.
     */
    public boolean securePillar(String pillar){

        switch(pillar.toLowerCase()){
            case "biodiversity" -> {return true;}
            case "water" -> {return true;}
            case "waste treatment" -> {return true;}
            case "energy" -> {return true;}
            default -> {return false;}
        }
    }

    /**
     * The function `registerProject` creates a new project with the given parameters and adds it to
     * the appropriate pillar based on the provided pillar name.
     * 
     * @param pillar The pillar parameter represents the category or area of focus for the project. It
     * can be one of the following values: "biodiversity", "water", "waste treatment", or "energy".
     * @param status The status of the project (e.g. "active", "inactive").
     * @param name The name of the project.
     * @param id The "id" parameter is a unique identifier for the project. It is used to distinguish
     * one project from another.
     * @param description The description parameter is a String that represents the description of the
     * project. It provides additional information about the project and its goals.
     * @param starDate The start date of the project.
     * @param finalDate The finalDate parameter is the date when the project is expected to be
     * completed or finished.
     * @return The method is returning a string message "The project was created successfully!\n".
     */
    public String registerProject(String pillar, String status, String name, String id, String description, String starDate, String finalDate){

        boolean stat = false;

        stat = (status.equalsIgnoreCase("active")) ? true : false;

        Project project = new Project(stat, name, id, description, null, null, null);
        project.passDate(starDate, finalDate);

        switch(pillar.toLowerCase()){
            case "biodiversity" -> pillars[0].addProject(project);
                
            case "water" -> pillars[1].addProject(project);
                
            case "waste treatment" -> pillars[2].addProject(project);
                
            case "energy" -> pillars[3].addProject(project);
          
            default -> {}
        }

        return "The project was created sucessfuly!\n";
    }

    /**
     * The function loginUser takes in a username and password, checks if the username exists in the
     * list of users, and if it does, calls the login method of that user with the given username and
     * password.
     * 
     * @param userName The username entered by the user trying to log in.
     * @param password The password parameter is a String that represents the password entered by the
     * user trying to log in.
     * @return The method is returning a boolean value. It returns true if the user is successfully
     * logged in, and false if the user is not found or the password is incorrect.
     */
    public boolean loginUser(String userName, String password){
        for (User i : users){
            if (i.getUserName().equals(userName)){
                return i.login(userName, password);
            }
        }

        return false;
    }

    /**
     * The function "listProjects" takes a pillar as input and returns a string containing the names of
     * the projects associated with that pillar.
     * 
     * @param pillar The parameter "pillar" is a String that represents the type of pillar. It can have
     * one of the following values: "biodiversity", "water", "waste treatment", or "energy".
     * @return The method is returning a string message that lists the names of the projects under the
     * specified pillar.
     */
    public String listProjects(String pillar){

        String msg = "";
        PillarType pillarT = null;

        if (pillar.equalsIgnoreCase("biodiversity")){
            pillarT = PillarType.BIODIVERSITY;
        } else if (pillar.equalsIgnoreCase("water")){
            pillarT = PillarType.WATER;
        } else if (pillar.equalsIgnoreCase("waste treatment")){
            pillarT = PillarType.WASTE_TREATMENT;
        } else if (pillar.equalsIgnoreCase("energy")){
            pillarT  = PillarType.ENERGY;
        }

        for (int i = 0; i < pillars.length; i++){
            if (pillars[i].getProjects() != null && pillars[i].getType().equals(pillarT)){
                for (int j = 0; j < pillars[i].getProjects().size(); j++){
                    msg += "The name of the project #" + (j+1) + ": " + pillars[i].getProjects().get(j).getName() + "\n";
                }
            }
        }

        if (msg.equals("")){
            msg = "This pillar doesn't has projects yet\n";
        }

        return msg;
    }

    /**
     * The function "listAtributesProject" takes a project name as input and returns the attributes of
     * the project if it exists, otherwise it returns an empty string.
     * 
     * @param nameP The name of the project you want to list the attributes for.
     * @return The method is returning a string representation of the attributes of a project with the
     * given name. If a project with the given name is found, the method returns the string
     * representation of the project's attributes using the `toStringImprove()` method. If no project
     * with the given name is found, an empty string is returned.
     */
    public String listAtributesProject(String nameP){

        for (int i = 0; i < pillars.length && pillars[i].getProjects() != null; i++){
            for (int j = 0; j < pillars[i].getProjects().size(); j++){
                if (pillars[i].getProjects().get(j).getName().equalsIgnoreCase(nameP)){
                    return pillars[i].getProjects().get(j).toStringImprove() + "\n";
                }
            }
        }

        return "";
    }

    /**
     * The function modifies a project's attribute based on the given name and attribute, and returns a
     * success message.
     * 
     * @param nameP The name of the project you want to modify.
     * @param atribute The attribute parameter is used to specify which attribute of the project needs
     * to be modified. It can take the following values:
     * @param newAt The new value for the specified attribute.
     * @return The method is returning a string message indicating that the modification was
     * successful.
     */
    public String modifyProject(String nameP, String atribute, String newAt){

        for (Pillar i : pillars){
            if (i.getProjects() != null){
                for (Project j : i.getProjects()){
                    if (j.getName().equalsIgnoreCase(nameP)){
                        switch(atribute.toLowerCase()){
                            case "status":
                                if (newAt.equalsIgnoreCase("active")){
                                    j.setStatus(true);
                                } else if (newAt.equalsIgnoreCase("inactive")){
                                    j.setStatus(false);
                                }
                            break;
                            case "name":
                                j.setName(newAt);

                            break;
                            case "id":
                                j.setId(newAt);

                            break;
                            case "description":
                                j.setDescription(newAt);
                            
                            break;
                            case "start date":
                                j.passDate(newAt, null);

                            break;
                            case "final date":
                                j.passDate(null, newAt);

                            break;
                            default:
                                return "Enter a valid option\n";
                        }
                    }
                }
            }
        }

        return "The modify was sucessfuly!" + "\n" + "";
    }

    /**
     * The function deletes a project from a specific pillar.
     * 
     * @param pillar The pillar parameter is a String that represents the type of pillar where the
     * project is located.
     * @param nameP The name of the project that you want to delete.
     * @return The method is returning a string message. If the project is successfully deleted, it
     * will return "The project was deleted successfully!\n". If the project is not found, it will
     * return "The project wasn't found\n".
     */
    public String deleteProject(String pillar, String nameP){

        PillarType [] types = PillarType.values();
        
        for (int i = 0; i < pillars.length; i++){
            if (pillar.equalsIgnoreCase(types[i].toString())){
                pillars[i].removeProject(nameP);
                return "The project was deleted sucessfuly!\n";
            }
        }

        return "The project wasn't found\n";
    }

    /**
     * The function `verifyEvidenceCollector` checks if the given type is "review" and returns true,
     * otherwise it returns false.
     * 
     * @param type A string representing the type of evidence collector.
     * @return The method is returning a boolean value. If the type is "review", it will return true.
     * If the type is "file" or any other value, it will return false.
     */
    public boolean verifyEvidenceCollector(String type){
        
        switch(type.toLowerCase()){
            case "review" -> {return true;}
            case "file" -> {return false;}
            default -> {return false;}
        }
    }

    /**
     * The function "addEvidence" adds evidence to a project based on the user's role (information
     * collector or researcher).
     * 
     * @param pillar The pillar is a String that represents the category or domain of the project or
     * evidence. It could be something like "Education", "Healthcare", "Environment", etc.
     * @param nameP The name of the project where the evidence will be added.
     * @param name The name parameter represents the name of the evidence being added.
     * @param description The description parameter is a String that represents the description of the
     * evidence being added.
     * @param date The date parameter is a string that represents the date when the evidence was added.
     * @param type The "type" parameter represents the type of evidence being added, such as
     * "document", "image", "video", etc.
     * @param typeFile The parameter "typeFile" represents the file type of the evidence being added.
     * It could be a string value such as "pdf", "jpg", "docx", etc.
     * @param url The "url" parameter is a string that represents the URL or file path of the evidence
     * being added. It is used to specify the location of the evidence file or resource.
     * @param userName The username of the person adding the evidence.
     * @param status The "status" parameter is used to indicate the status of the evidence being added.
     * It could be a string value representing the current status of the evidence, such as "pending",
     * "approved", "rejected", etc.
     * @return The method is returning a String message indicating whether the evidence was added
     * successfully or not.
     */
    public String addEvidence(String pillar, String nameP, String name, String description, String date, String type, String typeFile, String url, String userName, String status){
        
        if (identifyInformationCollector(userName) == true){
            for (Pillar i : pillars){
                for (Project j : i.getProjects()){
                    j.addEvidencesByCollector(nameP, name, description, date);
                }
            }
        } else if (identifyResearcher(userName) == true){
            for (Pillar i : pillars){
                for (Project j : i.getProjects()){
                    j.addEvidencesByResearcher(nameP, name, description, date, type, typeFile, url, status);
                }
            }
        }

        return "The evidence was added sucessfuly!\n";
    }

    /**
     * The function searches for a specific evidence in a project within a given pillar and returns its
     * characteristics.
     * 
     * @param pillar The pillar is a String representing the category or domain of the project. It is
     * used to search for the specific project within that pillar.
     * @param nameP The name of the project you want to search for evidence in.
     * @param nameE The name of the evidence you are searching for.
     * @return The method is returning either the string representation of the evidence object if it is
     * found, or the message "The evidence wasn't found" if it is not found.
     */
    public String listCaracteristicsEvidencesProject(String pillar, String nameP, String nameE){

        for (Pillar i : pillars){
            for (Project j : i.getProjects()){
                if (j.getName().equalsIgnoreCase(nameP)){
                    for (Evidence k : j.getEvidence()){
                        if (k.getName().equalsIgnoreCase(nameE)){
                            return k.toString() + "\n";
                        }
                    }
                }
            }
            
        }

        return "The evidence wasn't found\n";	
    }

    /**
     * The function "listEvidences" iterates through a list of pillars, projects, and evidence to
     * generate a message containing the names of all the evidence.
     * 
     * @return The method is returning a string that contains the names of all the evidences in the
     * projects. If there are no evidences, it will return a message stating that the project doesn't
     * have any evidences yet.
     */
    public String listEvidences(){
        int index = 0;
        String msg = "";

        for (Pillar i : pillars){
            if (i.getProjects() != null){
                for (Project j : i.getProjects()){
                    for (Evidence k : j.getEvidence()){
                        index += 1;
                        msg += "Name of the evidence #" + index + ": " + k.getName() + "\n";
                    }
                }
            }
        }

        if (msg.equals("")){
            return "The project doesn't has evidences yet\n";
        } else {
            return msg;
        }

    }

    /**
     * The function modifies the evidence of a project by searching for it in a list of pillars and
     * projects.
     * 
     * @param nameE The name of the evidence project that you want to modify.
     * @param caract The parameter "caract" is a string that represents the characteristic or attribute
     * of the evidence that you want to modify.
     * @param newC The new value that you want to set for the specified characteristic of the evidence
     * project.
     * @return The method is returning a String message.
     */
    public String modifyEvidenceProject(String nameE, String caract, String newC){
        String msg = "";

        for (Pillar i : pillars){
            for (Project j : i.getProjects()){
                msg = j.modifyEvidenceProject(nameE, caract, newC);
            }
        }

        msg = (msg.equals("")) ? "The evidence wasn't found\n" : msg;
        return msg;
    }

    /**
     * The function removes evidence from a project within a specific pillar.
     * 
     * @param pillar The pillar parameter is a String that represents the type of pillar.
     * @param nameP The name of the project from which you want to remove evidence.
     * @param nameE The name of the evidence that you want to remove from the project.
     * @return The method is returning a string message. If the evidence was successfully deleted, it
     * returns "The evidence was deleted successfully!\n". If the evidence was not found, it returns
     * "The evidence wasn't found\n".
     */
    public String removeEvidenceProject(String pillar, String nameP, String nameE){
        for (int i = 0; i < pillars.length; i++){
            if (pillars[i].getType().toString().equalsIgnoreCase(pillar)){
                for (Project j : pillars[i].getProjects()){
                    if (j.getName().equalsIgnoreCase(nameP)){
                        j.removeEvidenceProject(nameE);
                        return "The evidence was deleted sucessfuly!\n";
                    }
                }
            }
        }

        return "The evidence wasn't found\n";
    }

    /**
     * The function adds an interest point to a map based on specified parameters.
     * 
     * @param nameI The name of the interest point to be added.
     * @param nameE nameE is an ArrayList of Strings that represents the names of the evidence items.
     * @param x The x-coordinate of the interest point on the map.
     * @param y The parameter "y" represents the y-coordinate of the interest point on the map.
     * @param nameP The name of the project that the interest point belongs to.
     * @param pillar The parameter "pillar" is a String that represents the type of pillar.
     * @return The method is returning a string message indicating whether the interest point was added
     * successfully or not.
     */
    public String addInterestPoint(String nameI, ArrayList<String> nameE, int x, int y, String nameP, String pillar) {
        InterestPoint interestPoint = new InterestPoint(nameI, x, y);
        boolean interestPointAdded = false;
    
        for (Pillar i : pillars) {
            if (i.getType().toString().equalsIgnoreCase(pillar)) {
                for (Project j : i.getProjects()) {
                    if (j.getName().equalsIgnoreCase(nameP)) {
                        for (Evidence k : j.getEvidence()) {
                            for (String name : nameE) {
                                if (k.getName().equalsIgnoreCase(name)) {
                                    interestPoint.addEvidences(k);
                                }
                            }
                        }
    
                        // Agregar el punto de interés solo una vez todos los bucles hayan terminado
                        map[x][y] = interestPoint;
                        interestPointAdded = true;
                        break; // Salir del bucle del proyecto una vez se haya agregado el punto de interés
                    }
                }
                if (interestPointAdded) {
                    break; // Salir del bucle del pilar si se agregó el punto de interés
                }
            }
        }
    
        if (interestPointAdded) {
            return "The interest point was added successfully!\n";
        } else {
            return "The interest point wasn't added\n";
        }
    }
    

    /**
     * The function `getMap()` returns a string representation of a map, where each cell contains
     * information about the evidence present in that location.
     * 
     * @return The method is returning a String representation of the map.
     */
    public String getMap(){
        String msg = "";

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if (map[i][j] != null){
                    for (Evidence k : map[i][j].getEvidence()){
                        if (k instanceof Review){
                            if (((Review)k).getStatus() == true){
                                msg += "  R";
                            }
                        } else if (k instanceof File){
                            msg.charAt(msg.length()-1);
                            switch(((File)k).getType()){
                                case AUDIO -> {msg += "  A";}
                                case VIDEO -> {msg += "  V";}
                                case PICS -> {msg += "  P";}
                                case TEXT -> {msg += "  T";}
                                case RESULTS_REPORT -> {msg += "  RR";}
                            }
                        }
                    }

                } else {
                    msg += "  _";
                }
            }
            
            msg += "\n";
        }

        return msg + "\nIf you don't see some interest points is because was added by information collector and the researcher need to approve it\n";
    }

    /**
     * The function "listStatusEvidences" returns a string containing the names of all pending reviews.
     * 
     * @return The method is returning a string that contains the names of the reviews that have a
     * status of false. If there are no reviews with a false status, it will return the message "There
     * aren't evidences pending".
     */
    public String listStatusEvidences(){
        String msg = "";
        int index = 0;

        for (Pillar i : pillars){
            for (Project j : i.getProjects()){
                for (Evidence k : j.getEvidence()){
                    if (k instanceof Review){
                        if (((Review)k).getStatus() == false){
                            index += 1;
                            msg += "the name of the review #" + index + ": " + k.getName() + "\n";
                        }
                    }
                }
            }
        }

        if (msg.equals("")){
            return "There aren't evidences pending\n";
        } else {
            return msg;
        }
    }

    /**
     * The function "listCaracteristicsInterestP" returns the characteristics of an interest point at a
     * given row and column in a map.
     * 
     * @param row The row index of the interest point in the map array.
     * @param column The column parameter represents the column index of the interest point in the map.
     * @return The method is returning a string that contains the characteristics of an interest point.
     * If the interest point exists at the specified row and column in the map, the method returns the
     * name, X and Y coordinates, and a label for evidences. If the interest point does not exist, the
     * method returns a message stating that the interest point was not found.
     */
    public String listCaracteristicsInterestP(int row, int column){

        if (map[row][column] != null){
            return "Name: " + map[row][column].getName() + "\n" + "Ubication X: " 
            + map[row][column].getUbicationX() + "\n" + "Ubication Y: " 
            + map[row][column].getUbicationY() + "\n" + "Evidences\n";
        } else {
            return "The interest point wasn't found\n";
        }
    }

    /**
     * The function modifies various properties of an interest point in a map based on the given
     * parameters.
     * 
     * @param caract The parameter "caract" is a string that represents the characteristic of the
     * interest point that you want to modify. It can be "name", "ubication x", "ubication y", or
     * "evidences".
     * @param pillar The parameter "pillar" is a String that represents the type of pillar associated
     * with the interest point.
     * @param nameP The name of the project to which the interest point belongs.
     * @param nameE An ArrayList of Strings representing the names of the evidences to be added to the
     * interest point.
     * @param newC The new value for the specified characteristic (e.g., name, ubication x, ubication
     * y).
     * @param xy The parameter "xy" represents the new value for the x or y coordinate of the interest
     * point's location. It is used to update the ubicationX or ubicationY property of the interest
     * point.
     * @param row The row parameter represents the row index of the interest point in the map array.
     * @param column The column parameter represents the column index of the interest point in the map.
     * @return The method is returning a String message indicating the result of the modification. It
     * will return "The modify was successfully!" if the modification was successful, or it will return
     * an error message if an invalid option was entered or if there is already an interest point in
     * the specified location.
     */
    public String modifyInterestPoints(String caract, String pillar, String nameP, ArrayList <String> nameE, String newC, int xy, int row, int column){
        
        switch(caract.toLowerCase()){
            case "name" -> {
                map[row][column].setName(newC);
            } 
            case "ubication x" -> {
                map[row][column].setUbicationX(xy);
                if (map[xy][column] != null){
                    map[xy][column] = map[row][column];
                    map[row][column] = null;
                } else {
                    return "There are a interest point in this ubication\n";
                }
            }
            case "ubication y" -> {
                map[row][column].setUbicationY(xy);
                if (map[row][xy] != null){
                    map[row][xy] = map[row][column];
                    map[row][column] = null;
                } else {
                    return "There are a interest point in this ubication\n";
                }
            }
            case "evidences" -> {
                for (Pillar i : pillars){
                    if (i.getType().toString().equalsIgnoreCase(pillar)){
                        for (Project j : i.getProjects()){
                            if (j.getName().equalsIgnoreCase(nameP)){
                                for (Evidence k : j.getEvidence()){
                                    for (String name : nameE){
                                        if (k.getName().equalsIgnoreCase(name)){
                                            map[row][column].addEvidences(k);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            default -> {return "Enter a valid option\n";}
        }

        return "The modify was sucessfuly!\n";
    }

    /**
     * The removeInterestPoint function removes an interest point from a map at the specified row and
     * column.
     * 
     * @param row The row index of the interest point to be removed from the map.
     * @param column The column parameter represents the column index of the interest point that you
     * want to remove.
     * @return The method is returning a string message indicating that the interest point was deleted
     * successfully.
     */
    public String removeInterestPoint(int row, int column){
        map[row][column] = null;

        return "The interest point was deleted sucessfuly!\n";
    }

    /**
     * The function "listInterestPointsFilterPillar" takes a pillar as input and returns a sorted list
     * of interest points that belong to that pillar.
     * 
     * @param pillar The parameter "pillar" is a String that represents the type of pillar for
     * filtering the interest points.
     * @return The method is returning a string that contains the names of interest points that match
     * the specified pillar. If there are no interest points that match the pillar, the method returns
     * a message stating that there are no interest points.
     */
    public String listProjectsFilterPillar(String pillar){
        String msg = "";
        int index = 0;
        
        for (int i = 0; i < pillars.length; i++){
            if (pillars[i].getType().toString().equalsIgnoreCase(pillar)){
                for (Project j : pillars[i].getProjects()){
                    index += 1;
                    msg += "The name of the projects #" + index + " is: " + j.getName() + "\n";
                }
            }
        }

        return msg;
    }

    /**
     * The function sets comments for a specific interest point on a map.
     * 
     * @param comments A string representing the comments to be added to the interest point.
     * @param row The row parameter represents the row index of the interest point in the map array.
     * @param column The column parameter represents the column index of the interest point in the map.
     * It is used to locate the specific interest point in the map array.
     * @return The method is returning a string message indicating that the comments were added
     * successfully.
     */
    public String setCommentsInterestPoint(String comments, int row, int column){
        map[row][column].setComments(comments);

        return "The comments was added sucessfuly!\n";

    }

    /**
     * The testCases() function creates instances of different classes, registers projects, adds
     * evidence, and adds interest points.
     */
    public void testCases(){
        registerUserResearcher("ligth", "kira", "yagami", "@email", "3245234", "Edificio D", "dios");
        registerUserInformationCollector("ryuk",  "shinigami", "ryuk", "@email", "3434534");
        registerUserVisitant("random", "qwerty");

        registerProject("biodiversity", "active", "p1", "id", "description", "12/12/2020", "12/12/2020");
        addEvidence("biodiversity", "p1", "e1", "description", "12/12/2020", "review", null, "url", "ryuk", null);
        addInterestPoint("point 1", new ArrayList<String>(Arrays.asList("e1")), 12, 1, "p1", "biodiversity");

        registerProject("water", "active", "p2", "id", "description", "12/12/2020", "12/12/2020");
        addEvidence("water", "p2", "e2", "description", "12/12/2020", "file", "video", "url", "ligth", "active");
        addInterestPoint("point 2", new ArrayList<String>(Arrays.asList("e2")), 1, 9, "p2", "water");

        registerProject("waste treatment", "active", "p3", "id", "description", "12/12/2020", "12/12/2020");
        addEvidence("waste treatment", "p3", "e3", "description", "12/12/2020", "file", "pics", "url", "ligth", "active");
        addInterestPoint("point 3", new ArrayList<String>(Arrays.asList("e3")), 15, 15, "p3", "waste treatment");

        registerProject("energy", "active", "p4", "id", "description", "12/12/2020", "12/12/2020");
        addEvidence("energy", "p4", "e4", "description", "12/12/2020", "review", null, "url", "ryuk", null);
        addInterestPoint("point 4", new ArrayList<String>(Arrays.asList("e4")), 8, 0, "p4", "energy");

    }

    /**
     * The function "listEvidencesProject" returns a string containing information about a project,
     * including its name, id, description, status, start date, final date, and the quantity of reviews
     * and files associated with it.
     * 
     * @param pillar The pillar parameter is a String that represents the type of pillar. It is used to
     * filter the projects based on the pillar type.
     * @param nameP The name of the project for which you want to list the evidences.
     * @return The method is returning a string that contains the information of a project, including
     * its name, id, description, status, start date, and final date. It also includes the quantity of
     * reviews and files associated with the project.
     */
    public String listEvidencesProject(String pillar, String nameP){
        String msg = "";
        int review = 0;
        int file = 0;
        
        for (Pillar i : pillars){
            if (i.getType().toString().equalsIgnoreCase(pillar)){
                for (Project j : i.getProjects()){
                    if (j.getName().equalsIgnoreCase(nameP)){
                        msg += "The information of the project is: " + "Name: " + j.getName() + "\n" 
                        + "Id: " + j.getId() + "\n" + "Description: " + j.getDescription() + "\n" + "Status: " + j.getStatus() + "\n" 
                        + "Start date: " + j.getStartDate() + "\n" + "Final date: " + j.getFinalDate() + "\n";

                        for (Evidence k : j.getEvidence()){
                            if (k instanceof Review){
                                review += 1;
                            } else if (k instanceof File){
                                file += 1;
                            }
                        }


                    }
                }
            }
        }

        return msg += "The quantity of reviews is: " + review + "\n" + "The quantity of files is: " + file + "\n";
    }

    
    /**
     * The function "listCaracteristicsInterestPShow" returns the characteristics of an interest point
     * at a given row and column in a map, or a message indicating that the interest point was not
     * found.
     * 
     * @param row The row parameter represents the row index of the interest point in the map array.
     * @param column The column parameter represents the column index of the interest point in the map.
     * @return The method is returning a string that contains the characteristics of an interest point
     * at the specified row and column in a map. If an interest point is found at that location, the
     * method returns the name, X and Y coordinates, evidences, and QR code of the interest point. If
     * no interest point is found, the method returns a message stating that the interest point wasn't
     * found.
     */
    public String listCaracteristicsInterestPShow(int row, int column){

        if (map[row][column] != null){
            return "Name: " + map[row][column].getName() + "\n" + "Ubication X: " 
            + map[row][column].getUbicationX() + "\n" + "Ubication Y: " 
            + map[row][column].getUbicationY() + "\n" + "Evidences\n" + map[row][column].getComments() + "\n"
            + map[row][column].getQR() + "\n";
        } else {
            return "The interest point wasn't found\n";
        }
    }

   
}
