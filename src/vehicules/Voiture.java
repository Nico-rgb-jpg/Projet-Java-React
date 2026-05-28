package vehicules;

public class Voiture extends Vehicule {

    private int nombrePortes;

    public Voiture(String marque, String modele, int annee, int kilometrage, double prix,
                   String vendeur, String emailVendeur, int nombrePortes) {
        super(marque, modele, annee, kilometrage, prix, vendeur, emailVendeur);
        this.nombrePortes = nombrePortes;
    }

    @Override
    public String getTypeVehicule() {
        return "Voiture";
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("   -> Nombre de portes : " + nombrePortes);
    }

    public int getNombrePortes() { return nombrePortes; }
}
