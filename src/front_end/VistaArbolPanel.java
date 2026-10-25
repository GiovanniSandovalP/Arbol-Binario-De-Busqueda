package front_end;

import back_end.ArbolBST;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VistaArbolPanel extends JPanel {

    private JTextArea txtVistaArbol;

    public VistaArbolPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Vista del árbol"));

        txtVistaArbol = new JTextArea();
        txtVistaArbol.setEditable(false);
        txtVistaArbol.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(txtVistaArbol);
        add(scroll, BorderLayout.CENTER);

        limpiar();
    }

    public void mostrarArbol(ArbolBST arbol) {
        if (arbol == null || arbol.estaVacio()) {
            txtVistaArbol.setText("Árbol vacío.");
            return;
        }

        String estructura = arbol.mostrarEstructura();
        if (estructura == null || estructura.trim().isEmpty()) {
            txtVistaArbol.setText("Árbol vacío.");
            return;
        }

        txtVistaArbol.setText(estructura);
    }

    public void limpiar() {
        txtVistaArbol.setText("Árbol vacío.");
    }
}
