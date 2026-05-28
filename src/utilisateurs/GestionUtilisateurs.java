package utilisateurs;

import java.util.ArrayList;

public class GestionUtilisateurs {

    private ArrayList<Utilisateur> utilisateurs;
    private Utilisateur utilisateurConnecte;

    public GestionUtilisateurs() {
        utilisateurs = new ArrayList<>();
        utilisateurConnecte = null;

        utilisateurs.add(new Administrateur("Admin", "admin@auto.fr", "1234"));
        utilisateurs.add(new UtilisateurSimple("Marie", "marie@mail.fr", "1234"));
        utilisateurs.add(new UtilisateurSimple("Lucas", "lucas@mail.fr", "1234"));
    }

    public boolean inscrire(String nom, String email, String mdp) {
        for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }
        utilisateurs.add(new UtilisateurSimple(nom, email, mdp));
        return true;
    }

    public Utilisateur connecter(String email, String mdp) {
        for (Utilisateur u : utilisateurs) {
            if (u.getEmail().equals(email) && u.getMotDePasse().equals(mdp)) {
                utilisateurConnecte = u;
                return u;
            }
        }
        return null;
    }

    public boolean supprimerUtilisateur(int index) {
        if (index >= 0 && index < utilisateurs.size()) {
            if (utilisateurs.get(index) instanceof Administrateur) {
                System.out.println("Impossible de supprimer un administrateur !");
                return false;
            }
            utilisateurs.remove(index);
            return true;
        }
        return false;
    }

    public void afficherUtilisateurs() {
        System.out.println("\n--- Liste des utilisateurs --");
        for (int i = 0; i < utilisateurs.size(); i++) {
            System.out.print((i + 1) + ". ");
            utilisateurs.get(i).afficher();
        }
    }

    public ArrayList<Utilisateur> getUtilisateurs() { return utilisateurs; }
    public Utilisateur getUtilisateurConnecte()     { return utilisateurConnecte; }
    public void deconnecter()                       { utilisateurConnecte = null; }
}
