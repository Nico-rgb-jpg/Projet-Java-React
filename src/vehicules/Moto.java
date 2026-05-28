package vehicules;

public class Moto extends Vehicule {

    private String typeMoto;

    public Moto(String marque, String modele, int annee, int kilometrage, double prix,
                String vendeur, String emailVendeur, String typeMoto) {
        super(marque, modele, annee, kilometrage, prix, vendeur, emailVendeur);
        this.typeMoto = typeMoto;
    }

    @Override
    public String getTypeVehicule() {
        return "Moto";
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("   -> Type : " + typeMoto);
    }

    public String getTypeMoto() { return typeMoto; }
}
