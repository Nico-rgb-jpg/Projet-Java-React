package vehicules;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class GestionVehicules {

    private ArrayList<Vehicule> annonces;

    private HashMap<String, ArrayList<String>> messages;

    private Stack<String> historiqueActions;

    public GestionVehicules() {
        annonces = new ArrayList<>();
        messages = new HashMap<>();
        historiqueActions = new Stack<>();
    }

    public void ajouterAnnonce(Vehicule v) {
        annonces.add(v);
        historiqueActions.push("Ajout annonce: " + v.toString());
    }

    public void afficherAnnonces() {
        System.out.println("\n--- Liste des annonces (" + annonces.size() + ") --");
        if (annonces.isEmpty()) {
            System.out.println("Aucune annonce.");
            return;
        }
        for (int i = 0; i < annonces.size(); i++) {
            System.out.print((i + 1) + ". ");
            annonces.get(i).afficher();
        }
    }

    public boolean supprimerAnnonce(int index) {
        if (index >= 0 && index < annonces.size()) {
            String info = annonces.get(index).toString();
            annonces.remove(index);
            historiqueActions.push("Suppression: " + info);
            return true;
        }
        return false;
    }

    public boolean modifierPrix(int index, double nouveauPrix) {
        if (index >= 0 && index < annonces.size()) {
            annonces.get(index).setPrix(nouveauPrix);
            historiqueActions.push("Modification prix: " + annonces.get(index));
            return true;
        }
        return false;
    }

    public void envoyerMessage(int indexAnnonce, String expediteur, String texte) {
        if (indexAnnonce < 0 || indexAnnonce >= annonces.size()) {
            System.out.println("Annonce introuvable.");
            return;
        }
        Vehicule v = annonces.get(indexAnnonce);
        String emailVendeur = v.getContact();

        if (!messages.containsKey(emailVendeur)) {
            messages.put(emailVendeur, new ArrayList<>());
        }
        String msg = "De: " + expediteur + " | Re: " + v + " | " + texte;
        messages.get(emailVendeur).add(msg);
        historiqueActions.push("Message envoyé à " + v.getVendeur());
        System.out.println("Message envoyé à " + v.getVendeur() + " !");
    }

    public void afficherMessages(String emailVendeur) {
        ArrayList<String> msgs = messages.get(emailVendeur);
        System.out.println("\n--- Vos messages ---");
        if (msgs == null || msgs.isEmpty()) {
            System.out.println("Aucun message.");
            return;
        }
        for (String m : msgs) {
            System.out.println("- " + m);
        }
    }

    public void afficherHistorique() {
        System.out.println("\n--- Historique (dernières actions) --");
        if (historiqueActions.isEmpty()) {
            System.out.println("Aucune action.");
            return;
        }
        Stack<String> copie = (Stack<String>) historiqueActions.clone();
        while (!copie.isEmpty()) {
            System.out.println("- " + copie.pop());
        }
    }

    public void sauvegarderAnnonces(String fichier) {
        try {
            FileOutputStream fos = new FileOutputStream(fichier);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(annonces);
            oos.close();
            fos.close();
            System.out.println("Annonces sauvegardées dans " + fichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerAnnonces(String fichier) {
        try {
            FileInputStream fis = new FileInputStream(fichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            annonces = (ArrayList<Vehicule>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Annonces chargées depuis " + fichier);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Pas de sauvegarde trouvée, démarrage vide.");
        }
    }

    public <T extends Vehicule> ArrayList<T> rechercherParPrixMax(ArrayList<T> liste, double prixMax) {
        ArrayList<T> resultats = new ArrayList<>();
        for (T v : liste) {
            if (v.getPrix() <= prixMax) {
                resultats.add(v);
            }
        }
        return resultats;
    }

    public ArrayList<Vehicule> getAnnonces() { return annonces; }
    public int getNombreAnnonces()           { return annonces.size(); }
}
