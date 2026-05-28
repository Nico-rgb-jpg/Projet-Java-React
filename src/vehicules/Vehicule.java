package vehicules;

import interfaces.Affichable;
import interfaces.Contactable;
import java.io.Serializable;

public abstract class Vehicule implements Affichable, Contactable, Serializable {
    private String marque;
    private String modele;
    private int annee;
    private int kilometrage;
    private double prix;
    private String vendeur;
    private String emailVendeur;
    private boolean vendu;

    public Vehicule(String marque, String modele, int annee, int kilometrage, double prix, String vendeur, String emailVendeur) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.kilometrage = kilometrage;
        this.prix = prix;
        this.vendeur = vendeur;
        this.emailVendeur = emailVendeur;
        this.vendu = false;
    }

    public abstract String getTypeVehicule();

    @Override
    public String getContact() {
        return emailVendeur;
    }

    @Override
    public void afficher() {
        String statut = vendu ? "[VENDU]" : "[DISPONIBLE]";
        System.out.println(statut + " [" + getTypeVehicule() + "] " + marque + " " + modele
                + " (" + annee + ") - " + kilometrage + " km - " + prix + " EUR"
                + " | Vendeur: " + vendeur);
    }

    public String getMarque()       { return marque; }
    public String getModele()       { return modele; }
    public int getAnnee()           { return annee; }
    public int getKilometrage()     { return kilometrage; }
    public double getPrix()         { return prix; }
    public String getVendeur()      { return vendeur; }
    public String getEmailVendeur() { return emailVendeur; }
    public boolean isVendu()        { return vendu; }

    public void setPrix(double prix) {
        if (prix > 0) {
            this.prix = prix;
        } else {
            System.out.println("Le prix doit être positif.");
        }
    }

    public void setVendu(boolean vendu) { this.vendu = vendu; }

    @Override
    public String toString() {
        return getTypeVehicule() + " " + marque + " " + modele + " " + annee;
    }
}
