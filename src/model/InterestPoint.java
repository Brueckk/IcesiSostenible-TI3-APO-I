package model;

import java.util.Random;
import java.util.ArrayList;

public class InterestPoint {
    private String name; 
    private String QR; 
    private ArrayList <Evidence> evidence;
    private int ubicationX;
    private int ubicationY;
    String comments;

    public InterestPoint(String name, int ubicationX, int ubicationY){
        this.name = name;
        this.ubicationX = ubicationX;
        this.ubicationY = ubicationY;
        this.QR = generateQR();
        this.evidence = new ArrayList<>();
    }

    /**
     * The function generates a random 12-character alphanumeric string, which can be used as a QR
     * code.
     * 
     * @return The method is returning a randomly generated string of length 12, consisting of
     * lowercase letters (a-z), uppercase letters (A-Z), and numbers (0-9).
     */
    public String generateQR(){
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        String str2 = str.toUpperCase();
        str = str + str2; 

        Random random = new Random();
        StringBuilder qrCode = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(str.length());
            char randomChar = str.charAt(index);
            qrCode.append(randomChar);
        }

        return qrCode.toString();
    }

    /**
     * The function adds an evidence object to a list of evidence.
     * 
     * @param evidence The "evidence" parameter is a list or collection of Evidence objects.
     */
    public void addEvidences(Evidence evidence){
        this.evidence.add(evidence);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQR() {
        return QR;
    }

    public void setQR(String qR) {
        QR = qR;
    }

    public int getUbicationX() {
        return ubicationX;
    }

    public void setUbicationX(int ubicationX) {
        this.ubicationX = ubicationX;
    }

    public int getUbicationY() {
        return ubicationY;
    }

    public void setUbicationY(int ubicationY) {
        this.ubicationY = ubicationY;
    }

    public void setEvidence(ArrayList<Evidence> evidence) {
        this.evidence = evidence;
    }

    public ArrayList<Evidence> getEvidence() {
        return evidence;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
