/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.lavarapido;
import br.com.lavarapido.view.TelaPrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author lauanesouza
 */
public class SistemaLavaRapido {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        });

    }
}
    