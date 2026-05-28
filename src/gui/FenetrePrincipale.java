package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import utilisateurs.Administrateur;
import utilisateurs.GestionUtilisateurs;
import utilisateurs.Utilisateur;
import vehicules.GestionVehicules;
import vehicules.Moto;
import vehicules.Vehicule;
import vehicules.Voiture;

public class FenetrePrincipale extends JFrame implements ActionListener {

    private GestionUtilisateurs gestionUtilisateurs;
    private GestionVehicules gestionVehicules;
    private Utilisateur utilisateurConnecte;

    private JTextArea zoneAnnonces;
    private JButton boutonActualiser;
    private JButton boutonContacter;
    private JTextField champNumeroAnnonce;

    private JTextField champMarque, champModele, champAnnee, champKm, champPrix;
    private JComboBox<String> comboType;
    private JTextField champExtra;
    private JButton boutonPublier;
    private JLabel labelExtra;

    private JTextArea zoneMesAnnonces;
    private JTextField champNewPrix;
    private JTextField champNumeroMesAnnonces;
    private JButton boutonModifier;

    private JTextArea zoneMessages;

    private JTextArea zoneAdmin;
    private JTextField champNumeroAdmin;
    private JButton boutonSupprimerAnnonce;
    private JButton boutonSupprimerUser;

    private JTextField champNumeroContacter;
    private JTextArea champMessage;
    private JButton boutonEnvoyer;

    public FenetrePrincipale(GestionUtilisateurs gu, Utilisateur u) {
        this.gestionUtilisateurs = gu;
        this.utilisateurConnecte = u;
        this.gestionVehicules = new GestionVehicules();

        gestionVehicules.chargerAnnonces("annonces.ser");
        if (gestionVehicules.getNombreAnnonces() == 0) {
            gestionVehicules.ajouterAnnonce(new Voiture("Renault", "Clio", 2019, 45000, 9500, "Marie", "marie@mail.fr", 5));
            gestionVehicules.ajouterAnnonce(new Voiture("Peugeot", "308", 2020, 32000, 14900, "Lucas", "lucas@mail.fr", 5));
            gestionVehicules.ajouterAnnonce(new Moto("Yamaha", "MT-07", 2021, 12000, 7500, "Marie", "marie@mail.fr", "Sportive"));
        }

        setTitle("AutoP2P - Connecté en tant que : " + u.getNom() + " [" + u.getRole() + "]");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestionVehicules.sauvegarderAnnonces("annonces.ser");
                System.exit(0);
            }
        });

        setLayout(new BorderLayout());

        JPanel panelNav = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNav.setBackground(new Color(30, 100, 200));
        JLabel navLabel = new JLabel("AutoP2P  |  " + u.getNom());
        navLabel.setForeground(Color.WHITE);
        navLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panelNav.add(navLabel);

        JButton boutonDeconnexion = new JButton("Déconnexion");
        boutonDeconnexion.addActionListener(ev -> {
            gestionVehicules.sauvegarderAnnonces("annonces.ser");
            dispose();
            new FenetreLogin(gestionUtilisateurs);
        });
        panelNav.add(boutonDeconnexion);
        add(panelNav, BorderLayout.NORTH);

        JTabbedPane onglets = new JTabbedPane();

        onglets.addTab("Annonces", creerPanelAnnonces());
        onglets.addTab("Publier", creerPanelPublier());
        onglets.addTab("Mes annonces", creerPanelMesAnnonces());
        onglets.addTab("Messages", creerPanelMessages());

        if (u instanceof Administrateur) {
            onglets.addTab("⚙ Admin", creerPanelAdmin());
        }

        add(onglets, BorderLayout.CENTER);

        setSize(750, 550);
        setLocationRelativeTo(null);
        setVisible(true);

        actualiserAnnonces();
    }

    private JPanel creerPanelAnnonces() {
        JPanel panel = new JPanel(new BorderLayout());

        zoneAnnonces = new JTextArea();
        zoneAnnonces.setEditable(false);
        zoneAnnonces.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(zoneAnnonces);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new FlowLayout());
        boutonActualiser = new JButton("Actualiser");
        boutonActualiser.addActionListener(this);

        panelBas.add(new JLabel("N° annonce :"));
        champNumeroContacter = new JTextField(5);
        panelBas.add(champNumeroContacter);

        boutonContacter = new JButton("Contacter le vendeur");
        boutonContacter.addActionListener(this);
        panelBas.add(boutonActualiser);
        panelBas.add(boutonContacter);

        champMessage = new JTextArea(3, 30);
        champMessage.setBorder(BorderFactory.createTitledBorder("Votre message :"));
        panelBas.add(new JScrollPane(champMessage));

        boutonEnvoyer = new JButton("Envoyer");
        boutonEnvoyer.addActionListener(this);
        panelBas.add(boutonEnvoyer);

        panel.add(panelBas, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creerPanelPublier() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(8, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        form.add(new JLabel("Type :"));
        comboType = new JComboBox<>(new String[]{"Voiture", "Moto"});
        comboType.addActionListener(e -> {
            boolean estVoiture = comboType.getSelectedItem().equals("Voiture");
            labelExtra.setText(estVoiture ? "Nb portes :" : "Type moto :");
        });
        form.add(comboType);

        form.add(new JLabel("Marque :"));
        champMarque = new JTextField();
        form.add(champMarque);

        form.add(new JLabel("Modèle :"));
        champModele = new JTextField();
        form.add(champModele);

        form.add(new JLabel("Année :"));
        champAnnee = new JTextField();
        form.add(champAnnee);

        form.add(new JLabel("Kilométrage :"));
        champKm = new JTextField();
        form.add(champKm);

        form.add(new JLabel("Prix (EUR) :"));
        champPrix = new JTextField();
        form.add(champPrix);

        labelExtra = new JLabel("Nb portes :");
        form.add(labelExtra);
        champExtra = new JTextField();
        form.add(champExtra);

        panel.add(form, BorderLayout.CENTER);

        JPanel panelBouton = new JPanel(new FlowLayout());
        boutonPublier = new JButton("Publier l'annonce");
        boutonPublier.setBackground(new Color(30, 150, 50));
        boutonPublier.setForeground(Color.WHITE);
        boutonPublier.addActionListener(this);
        panelBouton.add(boutonPublier);
        panel.add(panelBouton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creerPanelMesAnnonces() {
        JPanel panel = new JPanel(new BorderLayout());

        zoneMesAnnonces = new JTextArea();
        zoneMesAnnonces.setEditable(false);
        zoneMesAnnonces.setFont(new Font("Monospaced", Font.PLAIN, 13));
        panel.add(new JScrollPane(zoneMesAnnonces), BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new FlowLayout());
        panelBas.add(new JLabel("N° annonce :"));
        champNumeroMesAnnonces = new JTextField(5);
        panelBas.add(champNumeroMesAnnonces);

        panelBas.add(new JLabel("Nouveau prix :"));
        champNewPrix = new JTextField(8);
        panelBas.add(champNewPrix);

        boutonModifier = new JButton("Modifier le prix");
        boutonModifier.addActionListener(this);
        panelBas.add(boutonModifier);

        panel.add(panelBas, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel creerPanelMessages() {
        JPanel panel = new JPanel(new BorderLayout());

        zoneMessages = new JTextArea();
        zoneMessages.setEditable(false);
        zoneMessages.setFont(new Font("Monospaced", Font.PLAIN, 13));
        panel.add(new JScrollPane(zoneMessages), BorderLayout.CENTER);

        JButton boutonRefresh = new JButton("Actualiser mes messages");
        boutonRefresh.addActionListener(e -> actualiserMessages());
        JPanel p = new JPanel(new FlowLayout());
        p.add(boutonRefresh);
        panel.add(p, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel creerPanelAdmin() {
        JPanel panel = new JPanel(new BorderLayout());

        zoneAdmin = new JTextArea();
        zoneAdmin.setEditable(false);
        zoneAdmin.setFont(new Font("Monospaced", Font.PLAIN, 13));
        panel.add(new JScrollPane(zoneAdmin), BorderLayout.CENTER);

        JPanel panelBas = new JPanel(new FlowLayout());
        panelBas.add(new JLabel("N° :"));
        champNumeroAdmin = new JTextField(5);
        panelBas.add(champNumeroAdmin);

        boutonSupprimerAnnonce = new JButton("Supprimer annonce");
        boutonSupprimerAnnonce.setForeground(Color.RED);
        boutonSupprimerAnnonce.addActionListener(this);
        panelBas.add(boutonSupprimerAnnonce);

        boutonSupprimerUser = new JButton("Supprimer utilisateur");
        boutonSupprimerUser.setForeground(Color.RED);
        boutonSupprimerUser.addActionListener(this);
        panelBas.add(boutonSupprimerUser);

        JButton boutonVoirTout = new JButton("Voir tout");
        boutonVoirTout.addActionListener(e -> actualiserAdmin());
        panelBas.add(boutonVoirTout);

        panel.add(panelBas, BorderLayout.SOUTH);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boutonActualiser) {
            actualiserAnnonces();

        } else if (e.getSource() == boutonEnvoyer) {
            envoyerMessage();

        } else if (e.getSource() == boutonContacter) {
            envoyerMessage();

        } else if (e.getSource() == boutonPublier) {
            publierAnnonce();

        } else if (e.getSource() == boutonModifier) {
            modifierAnnonce();

        } else if (e.getSource() == boutonSupprimerAnnonce) {
            supprimerAnnonce();

        } else if (e.getSource() == boutonSupprimerUser) {
            supprimerUtilisateur();
        }
    }

    private void actualiserAnnonces() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Vehicule> liste = gestionVehicules.getAnnonces();
        if (liste.isEmpty()) {
            sb.append("Aucune annonce disponible.");
        } else {
            for (int i = 0; i < liste.size(); i++) {
                Vehicule v = liste.get(i);
                sb.append((i + 1)).append(". ");
                sb.append("[").append(v.isVendu() ? "VENDU" : "DISPO").append("] ");
                sb.append("[").append(v.getTypeVehicule()).append("] ");
                sb.append(v.getMarque()).append(" ").append(v.getModele());
                sb.append(" (").append(v.getAnnee()).append(") - ");
                sb.append(v.getKilometrage()).append(" km - ");
                sb.append(v.getPrix()).append(" EUR");
                sb.append(" | Vendeur: ").append(v.getVendeur()).append("\n");
            }
        }
        zoneAnnonces.setText(sb.toString());
        actualiserMesAnnonces();
        actualiserMessages();
    }

    private void actualiserMesAnnonces() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Vehicule> liste = gestionVehicules.getAnnonces();
        int num = 1;
        for (Vehicule v : liste) {
            if (v.getEmailVendeur().equals(utilisateurConnecte.getEmail())) {
                sb.append(num).append(". ").append(v.getMarque()).append(" ").append(v.getModele());
                sb.append(" - ").append(v.getPrix()).append(" EUR\n");
            }
            num++;
        }
        if (sb.length() == 0) sb.append("Vous n'avez pas d'annonces.");
        zoneMesAnnonces.setText(sb.toString());
    }

    private void actualiserMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Vos messages ---\n");
        sb.append("(Les messages reçus s'affichent ici)");
        zoneMessages.setText(sb.toString());
    }

    private void actualiserAdmin() {
        if (zoneAdmin == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append("=== ANNONCES ===\n");
        ArrayList<Vehicule> annonces = gestionVehicules.getAnnonces();
        for (int i = 0; i < annonces.size(); i++) {
            Vehicule v = annonces.get(i);
            sb.append((i + 1)).append(". ").append(v.getMarque()).append(" ").append(v.getModele())
              .append(" - ").append(v.getVendeur()).append(" - ").append(v.getPrix()).append(" EUR\n");
        }
        sb.append("\n=== UTILISATEURS ===\n");
        for (int i = 0; i < gestionUtilisateurs.getUtilisateurs().size(); i++) {
            sb.append((i + 1)).append(". ").append(gestionUtilisateurs.getUtilisateurs().get(i)).append("\n");
        }
        zoneAdmin.setText(sb.toString());
    }

    private void envoyerMessage() {
        try {
            int idx = Integer.parseInt(champNumeroContacter.getText().trim()) - 1;
            String texte = champMessage.getText().trim();
            if (texte.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Écrivez un message !");
                return;
            }
            gestionVehicules.envoyerMessage(idx, utilisateurConnecte.getNom(), texte);
            JOptionPane.showMessageDialog(this, "Message envoyé !");
            champMessage.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrez un numéro d'annonce valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void publierAnnonce() {
        try {
            String type = (String) comboType.getSelectedItem();
            String marque = champMarque.getText().trim();
            String modele = champModele.getText().trim();
            int annee = Integer.parseInt(champAnnee.getText().trim());
            int km = Integer.parseInt(champKm.getText().trim());
            double prix = Double.parseDouble(champPrix.getText().trim());

            if (marque.isEmpty() || modele.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Remplissez tous les champs !");
                return;
            }

            if (type.equals("Voiture")) {
                int portes = champExtra.getText().isEmpty() ? 5 : Integer.parseInt(champExtra.getText().trim());
                gestionVehicules.ajouterAnnonce(new Voiture(marque, modele, annee, km, prix,
                        utilisateurConnecte.getNom(), utilisateurConnecte.getEmail(), portes));
            } else {
                String typeMoto = champExtra.getText().trim().isEmpty() ? "Routière" : champExtra.getText().trim();
                gestionVehicules.ajouterAnnonce(new Moto(marque, modele, annee, km, prix,
                        utilisateurConnecte.getNom(), utilisateurConnecte.getEmail(), typeMoto));
            }

            JOptionPane.showMessageDialog(this, "Annonce publiée !");
            champMarque.setText(""); champModele.setText(""); champAnnee.setText("");
            champKm.setText(""); champPrix.setText(""); champExtra.setText("");
            actualiserAnnonces();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vérifiez les champs numériques.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifierAnnonce() {
        try {
            int idx = Integer.parseInt(champNumeroMesAnnonces.getText().trim()) - 1;
            double nvPrix = Double.parseDouble(champNewPrix.getText().trim());

            ArrayList<Vehicule> liste = gestionVehicules.getAnnonces();
            if (idx < 0 || idx >= liste.size()) {
                JOptionPane.showMessageDialog(this, "Numéro invalide.");
                return;
            }
            Vehicule v = liste.get(idx);
            if (!v.getEmailVendeur().equals(utilisateurConnecte.getEmail())) {
                JOptionPane.showMessageDialog(this, "Ce n'est pas votre annonce !");
                return;
            }
            gestionVehicules.modifierPrix(idx, nvPrix);
            JOptionPane.showMessageDialog(this, "Prix modifié !");
            actualiserAnnonces();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valeurs invalides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void supprimerAnnonce() {
        try {
            int idx = Integer.parseInt(champNumeroAdmin.getText().trim()) - 1;
            if (gestionVehicules.supprimerAnnonce(idx)) {
                JOptionPane.showMessageDialog(this, "Annonce supprimée.");
                actualiserAdmin();
                actualiserAnnonces();
            } else {
                JOptionPane.showMessageDialog(this, "Numéro invalide.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrez un numéro.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void supprimerUtilisateur() {
        try {
            int idx = Integer.parseInt(champNumeroAdmin.getText().trim()) - 1;
            if (gestionUtilisateurs.supprimerUtilisateur(idx)) {
                JOptionPane.showMessageDialog(this, "Utilisateur supprimé.");
                actualiserAdmin();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrez un numéro.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
