package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilisateurs.GestionUtilisateurs;

public class FenetreInscription extends JFrame implements ActionListener {

    private JTextField champNom;
    private JTextField champEmail;
    private JPasswordField champMdp;
    private JButton boutonValider;
    private JButton boutonAnnuler;
    private JLabel labelMessage;

    private GestionUtilisateurs gestionUtilisateurs;

    public FenetreInscription(GestionUtilisateurs gu) {
        this.gestionUtilisateurs = gu;

        setTitle("AutoP2P - Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel panelTitre = new JPanel();
        JLabel titre = new JLabel("Créer un compte");
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setForeground(new Color(30, 100, 200));
        panelTitre.add(titre);
        add(panelTitre, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        panelForm.add(new JLabel("Nom :"));
        champNom = new JTextField(20);
        panelForm.add(champNom);

        panelForm.add(new JLabel("Email :"));
        champEmail = new JTextField(20);
        panelForm.add(champEmail);

        panelForm.add(new JLabel("Mot de passe :"));
        champMdp = new JPasswordField(20);
        panelForm.add(champMdp);

        labelMessage = new JLabel("");
        labelMessage.setForeground(Color.RED);
        panelForm.add(labelMessage);
        panelForm.add(new JLabel(""));

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBoutons = new JPanel(new FlowLayout());
        boutonValider = new JButton("S'inscrire");
        boutonValider.setBackground(new Color(30, 150, 50));
        boutonValider.setForeground(Color.WHITE);
        boutonValider.addActionListener(this);

        boutonAnnuler = new JButton("Annuler");
        boutonAnnuler.addActionListener(this);

        panelBoutons.add(boutonValider);
        panelBoutons.add(boutonAnnuler);
        add(panelBoutons, BorderLayout.SOUTH);

        setSize(450, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonValider) {
            String nom = champNom.getText().trim();
            String email = champEmail.getText().trim();
            String mdp = new String(champMdp.getPassword());

            if (nom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                labelMessage.setText("Tous les champs sont obligatoires !");
                return;
            }

            if (gestionUtilisateurs.inscrire(nom, email, mdp)) {
                JOptionPane.showMessageDialog(this, "Inscription réussie ! Connectez-vous.");
                dispose();
                new FenetreLogin(gestionUtilisateurs);
            } else {
                labelMessage.setText("Cet email est déjà utilisé !");
            }
        } else if (e.getSource() == boutonAnnuler) {
            dispose();
            new FenetreLogin(gestionUtilisateurs);
        }
    }
}
