package utilisateurs;

import interfaces.Affichable;
import java.io.Serializable;

public abstract class Utilisateur implements Affichable, Serializable {
    private String nom;
    private String email;
    private String motDePasse;

    public Utilisateur(String nom, String email, String motDePasse) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public abstract String getRole();

    @Override
    public void afficher() {
        System.out.println("[" + getRole() + "] " + nom + " - " + email);
    }

    public String getNom()         { return nom; }
    public String getEmail()       { return email; }
    public String getMotDePasse()  { return motDePasse; }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + nom + " (" + email + ")";
    }
}
