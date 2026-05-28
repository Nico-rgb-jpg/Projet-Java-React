package utilisateurs;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
    }

    @Override
    public String getRole() {
        return "admin";
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("   -> Droits : suppression annonces et utilisateurs");
    }
}
