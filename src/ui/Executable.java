package ui; 

import java.util.Scanner;
import model.Controller;

import java.util.ArrayList;

public class Executable{
    private Scanner sc;
    private Controller controller;

    public Executable(){
        this.sc = new Scanner(System.in);
        this.controller = new Controller();
    }

    /**
     * The menu function displays a menu of options and allows the user to select an option to perform
     * various actions in the app.
     */
    public void menu(){
        System.out.println("Welcome to the app: Icesi Sostenible!");
        System.out.println("Please select an option: ");
        boolean status = false;

        while(!status){
            System.out.println("1. Register user\n" + "2. Manage projects\n" + "3. Manage evidences\n" 
            + "4. Manage interest points\n" + "5. Test cases\n" + "6. Consult information of pillar \n"
            + "7. Consult the information of project and quantity of evidences\n" + "8. Show the map\n" + "9. Add comments to interst point\n" + "10. get information of interest point" + "11. Exit");

            int sel = sc.nextInt();
            switch(sel){
                case 1:
                    registerUser();
                break;
                case 2:
                    manageProject();
                break;
                case 3:
                    manageEvidences();
                break;
                case 4:
                    manageInterestPoints();
                break;
                case 5:
                    testCases();
                break;
                case 6:
                    listProjectsPillar();
                break;
                case 7:
                    listEvidencesProject();
                break;
                case 8:
                    getMap();
                break;
                case 9:
                    addComments();
                break;
                case 10:
                    getInfoInterestPoint();
                break;
                case 11: 
                    status = true;
                    System.out.println("Come back soon!");
                    System.exit(0);
                default:
                    System.out.println("Please select a valid option");
                break;
            }
        }
    }

    /**
     * The function `registerUser()` prompts the user to enter information based on the type of user
     * (visitant, information collector, or researcher) and then calls the appropriate method in the
     * `controller` object to register the user.
     */
    public void registerUser(){
        System.out.println("Enter a type of user: (Visitant, Information collector or Researcher)");
        String user = sc.nextLine();
        if (user.isEmpty()) {user = sc.nextLine();}

        if (user.equalsIgnoreCase("visitant")){
            System.out.println("Enter the user name: ");
            String userName = sc.nextLine();

            System.out.println("Enter the password: ");
            String password = sc.nextLine();

            System.out.println(controller.registerUserVisitant(userName, password));
        } else if (user.equalsIgnoreCase("information collector")){
            System.out.println("Enter the user name: ");
            String userName = sc.nextLine();
            if (userName.isEmpty()) {userName = sc.nextLine();}

            System.out.println("Enter the password: ");
            String password = sc.nextLine();

            System.out.println("Enter the real name of the user: ");
            String name = sc.nextLine();

            System.out.println("Enter the email: ");
            String email = sc.nextLine();

            System.out.println("Enter the phone: ");
            String phone = sc.nextLine();

            System.out.println(controller.registerUserInformationCollector(userName, password, name, email, phone));
        } else if (user.equalsIgnoreCase("researcher")){
            System.out.println("Enter the user name: ");
            String userName = sc.nextLine();
            if (userName.isEmpty()) {userName = sc.nextLine();}

            System.out.println("Enter the password: ");
            String password = sc.nextLine();

            System.out.println("Enter the real name: ");
            String name = sc.nextLine();

            System.out.println("Enter the email: ");
            String email = sc.nextLine();

            System.out.println("Enter the phone: ");
            String phone = sc.nextLine();

            System.out.println("Enter the area where the user belongs: ");
            String areaU = sc.nextLine();

            System.out.println("Enter the charge: ");
            String charge = sc.nextLine();

            System.out.println(controller.registerUserResearcher(userName, password, name, email, phone, areaU, charge));
        }
    }

    /**
     * The function "manageProject" allows the user to register, modify, or remove a project based on
     * their user role and input.
     */
    public void manageProject(){
        System.out.println("Enter the function you want\n");
        System.out.println("1. Register project\n2. Modify project\n3. Remove project\n");
        int option = sc.nextInt();

        switch(option){
            case 1:
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't create projects\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't create projects\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("Enter the type of pillar you desire to add the project (biodiversity, water, waste treatment or energy): ");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while(controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        System.out.println("Enter the status of the project (active/inactive): ");
                        String status;
                        
                        while(true){
                            try {
                                status = sc.nextLine();
                                controller.secureStatus(status);
                                break;
                            } catch (IllegalArgumentException e) {
                                System.out.println("Enter a valid option");
                            }
                        }

                        System.out.println("Enter the name of the project: ");
                        String name = sc.nextLine();

                        System.out.println("Enter the id of the project: ");
                        String id = sc.nextLine();

                        System.out.println("Enter the description of the project: ");
                        String description = sc.nextLine();

                        System.out.println("Enter the start date (Format: dd/MM/yyyy): ");
                        String startDate = sc.nextLine();
                    
                        System.out.println("Enter the final date (Format: dd/MM/yyyy): ");
                        String finalDate = sc.nextLine();

                        System.out.println(controller.registerProject(pillar, status, name, id, description, startDate, finalDate));
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }

                break;

                case 2:
                    System.out.println("Login system\n-----------------------------------------");
                    System.out.println("Enter the user name: ");
                    userName = sc.nextLine();
                    if (userName.isEmpty()) {userName = sc.nextLine();}

                    System.out.println("Enter the password: ");
                    password = sc.nextLine();

                    if (controller.identifyVisitant(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("------------------------------------\nThis user account is visitant and it can't create projects\n");
                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }
            
                    } else if (controller.identifyInformationCollector(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("------------------------------------\nThis user account is information collector and it can't create projects\n");
                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }

                    } else if (controller.identifyResearcher(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("Enter the name of the pillar to know the projects inside (biodiversity, water, waste treatment or energy): ");
                            String pillar = sc.nextLine();
                            if (pillar.isEmpty()) {pillar = sc.nextLine();}

                            while(controller.securePillar(pillar) == false){
                                System.out.println("Enter a valid option");
                                pillar = sc.nextLine();
                            }

                            System.out.println(controller.listProjects(pillar));
                            if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                System.out.println("Enter the name of the project you want to modify: ");
                                String nameP = sc.nextLine();

                                System.out.println("There is a list with the caracteristics of the project selected");
                                System.out.println("----------------------------------------------------------");
                                System.out.println(controller.listAtributesProject(nameP));
                
                                System.out.println("Enter the caracteristic you want to modify: ");
                                String atribute = sc.nextLine();

                                String newAt = "";
                                if (atribute.equalsIgnoreCase("status")){
                                    System.out.println("Enter the new status (active or inactive): ");
                                    
                                    while(true){
                                        try {
                                            newAt = sc.nextLine();
                                            controller.secureStatus(newAt);
                                            break;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Enter a valid option");
                                        }
                                    }

                                } else if (atribute.equalsIgnoreCase("start date")){
                                    System.out.println("Enter the new start date (Format: dd/MM/yyyy): ");
                                    newAt = sc.nextLine();

                                } else if (atribute.equalsIgnoreCase("final date")){
                                    System.out.println("Enter the new final date (Format: dd/MM/yyyy): ");
                                    newAt = sc.nextLine();
                                } else if (atribute.equalsIgnoreCase("name")){
                                    System.out.println("Enter the new name: ");
                                    newAt = sc.nextLine();
                                } else if (atribute.equalsIgnoreCase("description")){
                                    System.out.println("Enter the new description: ");
                                    newAt = sc.nextLine();
                                } else if (atribute.equalsIgnoreCase("id")){
                                    System.out.println("Enter the new id: ");
                                    newAt = sc.nextLine();
                                }

                                System.out.println(controller.modifyProject(nameP, atribute, newAt));
                            }

                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }
                    } else {
                        System.out.println("------------------------------------\nThe user doesn't exist\n");
                    }

                break;

                case 3:
                    System.out.println("Login system\n-----------------------------------------");
                    System.out.println("Enter the user name: ");
                    userName = sc.nextLine();
                    if (userName.isEmpty()) {userName = sc.nextLine();}

                    System.out.println("Enter the password: ");
                    password = sc.nextLine();

                    if (controller.identifyVisitant(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("------------------------------------\nThis user account is visitant and it can't create projects\n");
                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }
            
                    } else if (controller.identifyInformationCollector(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("------------------------------------\nThis user account is information collector and it can't create projects\n");
                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }

                    } else if (controller.identifyResearcher(userName) == true){
                        if (controller.loginUser(userName, password) == true){
                            System.out.println("Enter the name of the pillar to know the projects inside (biodiversity, water, waste treatment or energy): ");
                            String pillar = sc.nextLine();
                            if (pillar.isEmpty()) {pillar = sc.nextLine();}

                            while(controller.securePillar(pillar) == false){
                                System.out.println("Enter a valid option");
                                pillar = sc.nextLine();
                            }

                            System.out.println(controller.listProjects(pillar));
                            if (!controller.listProjects(pillar).equals("This pillar doesn't has projects yet\n")){
                                System.out.println("Enter the name of the project you want to remove: ");
                                String nameP = sc.nextLine();

                                System.out.println(controller.deleteProject(pillar, nameP));
                            } 

                        } else {
                            System.out.println("------------------------------------\nThe password is incorrect\n");
                        }
                    } else {
                        System.out.println("------------------------------------\nThe user doesn't exist\n");
                    }

                break;
                default:
                System.out.println("Enter a valid option");

                break;
        }
    }

    /**
     * The function "manageEvidences" allows users to register, modify, remove, and change the status
     * of evidences in a project.
     */
    public void manageEvidences(){
        System.out.println("Enter the function you want\n");
        System.out.println("1. Register evidence\n2. Modify evidence\n3. Remove evidence\n4. Change status of evidence created by information collector\n");
        int option = sc.nextInt();

        switch(option){
            case 1:
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't create evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                       System.out.println("What type of evidence you want to add in a project? (File or review): ");
                       String type = sc.nextLine();

                       if (!controller.verifyEvidenceCollector(type) == false){
                            System.out.println("Enter the name of the review: ");
                            String name = sc.nextLine();

                            System.out.println("Enter the description of the review: ");
                            String description = sc.nextLine();

                            System.out.println("Enter the register date of the review (Format: dd/MM/yyyy): ");
                            String date = sc.nextLine();

                            System.out.println("Enter the name of the pillar where is located the project you want to add the review (biodiversity, water, waste treatment, energy): ");
                            String pillar = sc.nextLine();

                            if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                System.out.println(controller.listProjects(pillar));

                                System.out.println("Enter the name of the project you want to add the review: ");
                                String nameP = sc.nextLine();

                                System.out.println(controller.addEvidence(pillar, nameP, name, description, date, type, null, null, userName, null));

                            } else {
                                System.out.println(controller.listProjects(pillar));
                            }

                        } else {
                            System.out.println("------------------------------------\nThis user account is information collector and it can't create files\n");
                        }
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("What type of evidence you want to add in a project? (File or review): ");
                        String type = sc.nextLine();

                        if (type.equalsIgnoreCase("file")){
                            System.out.println("What type of file you want to add? (Audio, Video, Pics, Text or Results report): ");
                            String typeFile = sc.nextLine();

                            System.out.println("Enter the name of the file: ");
                            String name = sc.nextLine();

                            System.out.println("Enter the description of the file: ");
                            String description = sc.nextLine();

                            System.out.println("Enter the register date of the file: ");
                            String date = sc.nextLine();

                            System.out.println("Enter the url of the file: ");
                            String url = sc.nextLine();

                            System.out.println("Enter the name of the pillar where is located the project you want to add the review (biodiversity, water, waste treatment, energy): ");
                            String pillar = sc.nextLine();

                            if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                System.out.println(controller.listProjects(pillar));

                                System.out.println("Enter the name of the project you want to add the review: ");
                                String nameP = sc.nextLine();

                                System.out.println(controller.addEvidence(pillar, nameP, name, description, date, type, typeFile, url, userName, null));
                            } else {
                                System.out.println(controller.listProjects(pillar));
                            }

                        } else {
                            System.out.println("Enter the name of the review: ");
                            String name = sc.nextLine();

                            System.out.println("Enter the status of the review (active or inactive): ");
                            String status;

                            while(true){
                                try {
                                    status = sc.nextLine();
                                    controller.secureStatus(status);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Enter a valid option");
                                }
                            }

                            System.out.println("Enter the description of the review: ");
                            String description = sc.nextLine();

                            System.out.println("Enter the register date of the review (Format: dd/MM/yyyy): ");
                            String date = sc.nextLine();

                            System.out.println("Enter the name of the pillar where is located the project you want to add the review (biodiversity, water, waste treatment, energy): ");
                            String pillar = sc.nextLine();

                            if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                System.out.println(controller.listProjects(pillar));

                                System.out.println("Enter the name of the project you want to add the review: ");
                                String nameP = sc.nextLine();

                                System.out.println(controller.addEvidence(pillar, nameP, name, description, date, type, null, null, userName, status));
                            } else {
                                System.out.println(controller.listProjects(pillar));
                            
                            }
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
            
            break;
            case 2:
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't modify evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't modify evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        
                        System.out.println("\nThis is only the evidence modifier for project");
                        System.out.println("if you want to modify an evidence inside a interest point, choose 'Manage interest points'\n");
                        System.out.println("------------------------------------------------------\n");

                        String nameE = "";
                        String caract = "";
                        String newC = "";

                        System.out.println("Enter the type of pillar where is located the evidence you desire to modify " + "\n(biodiversity, water, waste treatment or energy): ");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while(controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        String nameP = "";

                        System.out.println(controller.listProjects(pillar));
                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                            System.out.println("There is a list with the projects of the pillar selected");
                            System.out.println("----------------------------------------------------------");
                            System.out.println(controller.listProjects(pillar));
                                
                            System.out.println("Enter the name of the project where is the evidence: ");
                            nameP = sc.nextLine();

                            System.out.println("\nThere is a list with the evidences of the project selected");
                            System.out.println("----------------------------------------------------------");
                            System.out.println(controller.listEvidences());
                            if (!controller.listEvidences().equalsIgnoreCase("The project doesn't has evidences yet\n")){
                                System.out.println("Enter the name of the evidence: ");
                                nameE = sc.nextLine();   
                            }

                            if (!controller.listCaracteristicsEvidencesProject(pillar, nameP, nameE).equalsIgnoreCase("The evidence wasn't found\n")){
                                System.out.println("There is a list with the caracteristics of the evidence selected");
                                System.out.println("----------------------------------------------------------");
                                System.out.println(controller.listCaracteristicsEvidencesProject(pillar, nameP, nameE));
                                    
                                System.out.println("Enter the caracteristic you want to modify: ");
                                caract = sc.nextLine();
                                    
                                if (caract.equalsIgnoreCase("name")){
                                    System.out.println("Enter the new name: ");
                                    newC = sc.nextLine();

                                } else if (caract.equalsIgnoreCase("description")){
                                    System.out.println("Enter the new description: ");
                                    newC = sc.nextLine();

                                } else if (caract.equalsIgnoreCase("register date")){
                                    System.out.println("Enter the new register date: ");
                                    newC = sc.nextLine();

                                } else if (caract.equalsIgnoreCase("status")){
                                    System.out.println("Enter the new status: ");
                                    newC = sc.nextLine();
                                    
                                }

                                System.out.println(controller.modifyEvidenceProject(nameE, caract, newC));

                            } else {
                                System.out.println(controller.listCaracteristicsEvidencesProject(pillar, nameP, nameE));
                            }
                        } 
                        
                            

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
                    

            break;
            case 3:
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't modify evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't modify evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("\nEnter the name of the pillar where is located the evidence you desire to remove " + "\n(biodiversity, water, waste treatment or energy): ");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while(controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        String nameP = "";
                        String nameE = "";

                        System.out.println(controller.listProjects(pillar));
                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                            System.out.println("There is a list with the projects of the pillar selected");
                            System.out.println("----------------------------------------------------------");
                            System.out.println(controller.listProjects(pillar));
                                
                            System.out.println("Enter the name of the project where is the evidence: ");
                            nameP = sc.nextLine();

                            System.out.println("\nThere is a list with the evidences of the project selected");
                            System.out.println("----------------------------------------------------------");
                            System.out.println(controller.listEvidences());
                            if (!controller.listEvidences().equalsIgnoreCase("The project doesn't has evidences yet\n")){
                                System.out.println("Enter the name of the evidence: ");
                                nameE = sc.nextLine();   

                                System.out.println(controller.removeEvidenceProject(pillar, nameP, nameE));
                            } 

                        } else {
                            System.out.println(controller.listProjects(pillar));
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
                        
            break;
            case 4:
                
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't change status of evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't change status of evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("There are a list with the evidences in status inactive");
                        System.out.println("------------------------------------------------------");
                        System.out.println(controller.listStatusEvidences());

                        System.out.println("Enter the name of the evidence you want to change the status: ");
                        String nameE = sc.nextLine();

                        System.out.println(controller.modifyEvidenceProject(nameE, "status", "active"));

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
                        
            break;
            default:
                System.out.println("Enter a valid option");
            break;
        }
    }

    /**
     * The function "manageInterestPoints" allows the user to register, modify, or remove interest
     * points based on their user role and login credentials.
     */
    public void manageInterestPoints(){
        System.out.println("Enter the function you want\n");
        System.out.println("1. Register interest point\n2. Modify interest point\n3. Remove interest point\n");
        int option = sc.nextInt();

        switch(option){
            case 1: 
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();
                
                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't create interest points\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't create interest points\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("Enter the name of the interest point: ");
                        String nameI = sc.nextLine();
                        if (nameI.isEmpty()) {nameI = sc.nextLine();}

                        System.out.println("Enter the option if you want to add evidences");
                        boolean flag = false;
                        ArrayList <String> nameE = new ArrayList<>();

                        while(!flag){
                            System.out.println("\n1. Add a evidence\n2. exit");
                            int sel = sc.nextInt();
                                switch(sel){
                                    case 1: 
                                        System.out.println("Enter the pillar where is located the proyect which has the evidence to add: ");
                                        System.out.println("Biodiversity, Water, Waste treatment or Energy");
                                        String pillar = sc.nextLine();
                                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                                        while(controller.securePillar(pillar) == false){
                                            System.out.println("Enter a valid option");
                                            pillar = sc.nextLine();
                                        }

                                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                            System.out.println(controller.listProjects(pillar));
                                            System.out.println("Enter the name of the project where is the evidence to add: ");
                                            String nameP = sc.nextLine();

                                            if (!controller.listEvidences().equalsIgnoreCase("The project doesn't has evidences yet\n")){
                                                System.out.println("There is a list with the evidences of the project selected");
                                                System.out.println(controller.listEvidences());

                                                System.out.println("Enter the name of the evidence to add: ");
                                                nameE.add(sc.nextLine());
                                
                                                System.out.println("Enter the ubication X of the interest point where you going to add: ");
                                                int x = sc.nextInt();

                                                System.out.println("Enter the ubication Y of the interest point where you going to add:");
                                                int y = sc.nextInt();

                                                System.out.println(controller.addInterestPoint(nameI, nameE, x, y, nameP, pillar));
                                            } else {
                                                System.out.println(controller.listEvidences());
                                            }
                                        } else {
                                            System.out.println(controller.listProjects(pillar));
                                        }
                        
                                    break;

                                    case 2:
                                        flag = true;
                                    break;

                                    default: 
                                        System.out.println("Enter a valid option");
                                    break;

                                }
                        }
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
                
            break;
            case 2: 
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't modify the interest points\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't modify the interest points\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("Launching the map");
                        System.out.println("------------------------------------------------");
                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------------");

                        System.out.println("Enter the number of row where is located the interest point you want to modify: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the number of column where is located the interest point you want to modify: ");
                        int column = sc.nextInt();

                        System.out.println("There is a list with the caracteristics of the interest point selected");
                        System.out.println("----------------------------------------------------------");
                        System.out.println(controller.listCaracteristicsInterestP(row, column));

                        if (!controller.listCaracteristicsInterestP(row, column).equals("The interest point wasn't found\n")){
                            System.out.println("Enter the caracteristic you want to modify: ");
                            String caract = sc.nextLine();
                            if (caract.isEmpty()) {caract = sc.nextLine();}

                            String newC = "";
                            int xy = -1;
                            String pillar = "";
                            String nameP = "";
                            ArrayList <String> nameE = new ArrayList<>();

                            switch(caract.toLowerCase()){
                                case "name" -> {
                                    System.out.println("Enter the new name: ");
                                    newC = sc.nextLine();
                                } 
                                case "ubication x" -> {
                                    System.out.println("Enter the new ubication X: ");
                                    xy = sc.nextInt();
                                }
                                case "ubication y" -> {
                                    System.out.println("Enter the new ubication Y: ");
                                    xy = sc.nextInt();
                                } 
                                case "evidences" -> {
                                    System.out.println("Enter the pillar where is located the proyect which has the evidence to add: ");
                                    System.out.println("Biodiversity, Water, Waste treatment or Energy");
                                    pillar = sc.nextLine();
                                    if (pillar.isEmpty()) {pillar = sc.nextLine();}

                                    while(controller.securePillar(pillar) == false){
                                        System.out.println("Enter a valid option");
                                        pillar = sc.nextLine();
                                    }

                                    if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                                        System.out.println(controller.listProjects(pillar));
                                        System.out.println("Enter the name of the project where is the evidence to add: ");
                                        nameP = sc.nextLine();

                                        if (!controller.listEvidences().equalsIgnoreCase("The project doesn't has evidences yet\n")){
                                            System.out.println("There is a list with the evidences of the project selected");
                                            System.out.println(controller.listEvidences());

                                            System.out.println("Enter the name of the evidence to add: ");
                                            nameE.add(sc.nextLine());

                                        } else {
                                            System.out.println(controller.listEvidences());
                                        }
                                    } else {
                                        System.out.println(controller.listProjects(pillar));
                                    }
                                }

                                default -> System.out.println("Enter a valid option");
                            }

                            System.out.println(controller.modifyInterestPoints(caract, pillar, nameP, nameE, newC, xy, row, column));
                        }

                        

                        

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
            break;
            case 3:
                System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is visitant and it can't change status of evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("------------------------------------\nThis user account is information collector and it can't change status of evidences\n");
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("Launching the map");
                        System.out.println("------------------------------------------------");
                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------------");

                        System.out.println("Enter the row where is located: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located: ");
                        int column = sc.nextInt();

                        System.out.println(controller.removeInterestPoint(row, column));

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
                      
            break;
            default:

            break;
        }
    }

    /**
     * The function prompts the user to enter their username and password, and then asks for a type of
     * pillar (Biodiversity, Water, Waste treatment, or Energy) and lists the projects filtered by that
     * pillar.
     */
    public void listProjectsPillar(){
          System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();

                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        System.out.println(controller.listProjectsFilterPillar(pillar));
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }
            
                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                         System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        System.out.println(controller.listProjectsFilterPillar(pillar));
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        System.out.println(controller.listProjectsFilterPillar(pillar));

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
    }

    /**
     * The function "listEvidencesProject" allows users to login and list the evidence for a specific
     * project in a chosen pillar.
     */
    public void listEvidencesProject(){
          System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();
                
                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while (controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                            System.out.println(controller.listProjects(pillar));
                            System.out.println("Enter the name of the project you want to know the information: ");
                            String nameP = sc.nextLine();

                            System.out.println(controller.listEvidencesProject(pillar, nameP));
                        } else {
                            System.out.println(controller.listProjects(pillar));
                        }
    
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while (controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                            System.out.println(controller.listProjects(pillar));
                            System.out.println("Enter the name of the project you want to know the information: ");
                            String nameP = sc.nextLine();

                            System.out.println(controller.listEvidencesProject(pillar, nameP));
                        } else {
                            System.out.println(controller.listProjects(pillar));
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println("Enter a type of pillar: Biodiversity, Water, Waste treatment or Energy");
                        String pillar = sc.nextLine();
                        if (pillar.isEmpty()) {pillar = sc.nextLine();}

                        while (controller.securePillar(pillar) == false){
                            System.out.println("Enter a valid option");
                            pillar = sc.nextLine();
                        }

                        if (!controller.listProjects(pillar).equalsIgnoreCase("This pillar doesn't has projects yet\n")){
                            System.out.println(controller.listProjects(pillar));
                            System.out.println("Enter the name of the project you want to know the information: ");
                            String nameP = sc.nextLine();

                            System.out.println(controller.listEvidencesProject(pillar, nameP));
                        } else {
                            System.out.println(controller.listProjects(pillar));
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
    }

    /**
     * The function allows users to add comments to specific interest points on a map after logging in
     * with their username and password.
     */
    public void addComments(){
        System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();
                
                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------");
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        System.out.println("Enter the comment: ");
                        String comment = sc.nextLine();
                        if (comment.isEmpty()) {comment = sc.nextLine();}

                        System.out.println(controller.setCommentsInterestPoint(comment, row, column));
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println(controller.getMap());
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        System.out.println("Enter the comment: ");
                        String comment = sc.nextLine();
                        if (comment.isEmpty()) {comment = sc.nextLine();}

                        System.out.println(controller.setCommentsInterestPoint(comment, row, column));

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                       System.out.println(controller.getMap());
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        System.out.println("Enter the comment: ");
                        String comment = sc.nextLine();
                        if (comment.isEmpty()) {comment = sc.nextLine();}

                        System.out.println(controller.setCommentsInterestPoint(comment, row, column));
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
    }

    /**
     * The function prompts the user to enter their username and password, verifies their credentials,
     * and then allows them to enter the row and column of an interest point to display its
     * characteristics.
     */
    public void getInfoInterestPoint(){
        System.out.println("Login system\n-----------------------------------------");
                System.out.println("Enter the user name: ");
                String userName = sc.nextLine();
                if (userName.isEmpty()) {userName = sc.nextLine();}

                System.out.println("Enter the password: ");
                String password = sc.nextLine();
                
                if (controller.identifyVisitant(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------");
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        if (!controller.listCaracteristicsInterestPShow(row, column).equals("The interest point wasn't found\n")){
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        } else {
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyInformationCollector(userName) == true){
                    if (controller.loginUser(userName, password) == true){
                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------");
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        if (!controller.listCaracteristicsInterestPShow(row, column).equals("The interest point wasn't found\n")){
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        } else {
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        }

                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else if (controller.identifyResearcher(userName) == true){
                    if (controller.loginUser(userName, password) == true){

                        System.out.println(controller.getMap());
                        System.out.println("------------------------------------------");
                        System.out.println("Enter the row where is located the interest point: ");
                        int row = sc.nextInt();

                        System.out.println("Enter the column where is located the interest point: ");	
                        int column = sc.nextInt();

                        if (!controller.listCaracteristicsInterestPShow(row, column).equals("The interest point wasn't found\n")){
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        } else {
                            System.out.println(controller.listCaracteristicsInterestPShow(row, column));
                        }
                    } else {
                        System.out.println("------------------------------------\nThe password is incorrect\n");
                    }

                } else {
                    System.out.println("------------------------------------\nThe user doesn't exist\n");
                }
    }

    /**
     * The function "testCases" calls the "testCases" method of the "controller" object.
     */
    public void testCases(){
        controller.testCases();
    }

    /**
     * The function "getMap" prints the map returned by the "getMap" method of the "controller" object.
     */
    public void getMap(){
        System.out.println(controller.getMap());
    }

    public static void main(String[] args){
        Executable executable = new Executable();
        executable.menu();
    }
}