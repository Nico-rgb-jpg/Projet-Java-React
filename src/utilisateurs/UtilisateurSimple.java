package utilisateurs;

public class UtilisateurSimple extends Utilisateur {

    public UtilisateurSimple(String nom, String email, String motDePasse) {
        super(nom, email, motDePasse);
    }

    @Override
    public String getRole() {
        return "user";
    }
}
