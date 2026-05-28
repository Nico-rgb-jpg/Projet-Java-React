package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilisateurs.GestionUtilisateurs;
import utilisateurs.Utilisateur;

public class FenetreLogin extends JFrame implements ActionListener {
    private JTextField champEmail;
    private JPasswordField champMotDePasse;
    private JButton boutonConnexion;
    private JButton boutonInscrire;
    private JLabel labelMessage;

    private GestionUtilisateurs gestionUtilisateurs;

    public FenetreLogin(GestionUtilisateurs gu) {
        this.gestionUtilisateurs = gu;

        super.setTitle("AutoP2P - Connexion");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel panelTitre = new JPanel();
        JLabel titre = new JLabel("AutoP2P - Vente de véhicules");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setForeground(new Color(30, 100, 200));
        panelTitre.add(titre);
        add(panelTitre, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        panelForm.add(new JLabel("Email :"));
        champEmail = new JTextField(20);
        panelForm.add(champEmail);

        panelForm.add(new JLabel("Mot de passe :"));
        champMotDePasse = new JPasswordField(20);
        panelForm.add(champMotDePasse);

        labelMessage = new JLabel("");
        labelMessage.setForeground(Color.RED);
        panelForm.add(labelMessage);
        panelForm.add(new JLabel(""));

        panelForm.add(new JLabel("Pas encore inscrit ?"));
        boutonInscrire = new JButton("S'inscrire");
        boutonInscrire.addActionListener(this);
        panelForm.add(boutonInscrire);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel(new FlowLayout());
        boutonConnexion = new JButton("Se connecter");
        boutonConnexion.setBackground(new Color(30, 100, 200));
        boutonConnexion.setForeground(Color.WHITE);
        boutonConnexion.addActionListener(this);
        panelSouth.add(boutonConnexion);

        JLabel hint = new JLabel("Comptes test : admin@auto.fr / marie@mail.fr | mdp : 1234");
        hint.setFont(new Font("Arial", Font.ITALIC, 11));
        hint.setForeground(Color.GRAY);
        panelSouth.add(hint);

        add(panelSouth, BorderLayout.SOUTH);

        setSize(500, 280);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonConnexion) {
            String email = champEmail.getText().trim();
            String mdp = new String(champMotDePasse.getPassword());

            Utilisateur u = gestionUtilisateurs.connecter(email, mdp);
            if (u == null) {
                labelMessage.setText("Email ou mot de passe incorrect !");
            } else {
                labelMessage.setText("");
                dispose();
                new FenetrePrincipale(gestionUtilisateurs, u);
            }
        } else if (e.getSource() == boutonInscrire) {
            dispose();
            new FenetreInscription(gestionUtilisateurs);
        }
    }
}
