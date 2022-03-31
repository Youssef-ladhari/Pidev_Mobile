/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;


public class Cours {
    int id;
    String titre, myfile,matiere,annee;

    public Cours(String titre, String matiere, String annee) {
        this.titre = titre;
        this.matiere = matiere;
        this.annee = annee;
    }

    public Cours() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMyfile() {
        return myfile;
    }

    public void setMyfile(String myfile) {
        this.myfile = myfile;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
}
