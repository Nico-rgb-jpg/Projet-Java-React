import gui.FenetreLogin;
import utilisateurs.GestionUtilisateurs;

import javax.swing.SwingUtilities;  // Cours 6 - Swing : bonne pratique de lancement

public class Main {
    public static void main(String[] args) {
        GestionUtilisateurs gu = new GestionUtilisateurs();

        // Cours 6 - Swing : lancement de l'interface graphique dans l'EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            new FenetreLogin(gu);
        });
    }
}
