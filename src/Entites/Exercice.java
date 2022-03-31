/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author USER
 */
public class Exercice {
    int id,numero;
    String niveau,question,correction,enonce;

    public Exercice(int numero, String niveau, String question, String correction, String enonce) {
        this.numero = numero;
        this.niveau = niveau;
        this.question = question;
        this.correction = correction;
        this.enonce = enonce;
    }

    public Exercice() {
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrection() {
        return correction;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
    
}
