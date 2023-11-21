package model;

import java.util.ArrayList;

public class Pillar {
    private PillarType type;
    private ArrayList<Project> projects;

    public Pillar(PillarType type){
        this.projects = new ArrayList<>();
        this.type = type;
    }

    /**
     * The addProject function adds a project to a list of projects.
     * 
     * @param project The parameter "project" is of type Project.
     */
    public void addProject(Project project){
        this.projects.add(project);
    }

    /**
     * The function removes a project from a list of projects based on its name and returns true if
     * successful, false otherwise.
     * 
     * @param nameP The name of the project that you want to remove.
     * @return The method is returning a boolean value. It returns true if a project with the specified
     * name is found and successfully removed from the list of projects. It returns false if no project
     * with the specified name is found.
     */
    public boolean removeProject(String nameP){
        
        for (Project i: projects){
            if (i != null){
                if (i.getName().equalsIgnoreCase(nameP)){
                    projects.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    public PillarType getType() {
        return type;
    }

    public void setType(PillarType type) {
        this.type = type;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
