/**
 * <p>Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, Materialien zum schulinternen Lehrplan Informatik SII</p>
 *
 * @version 2014-03-13
 */
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
    private Button btnDraw = new Button();      // Baum zeichnen


    public GUI(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dispose();
            }
        });

        int frameWidth = 493;
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setResizable(false);

        Panel cp = new Panel(null);
        add(cp);

        btnDraw.setBounds(352, 208, 115, 25);
        btnDraw.setLabel("zeichne Baum");
        btnDraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDraw_ActionPerformed(evt);
            }
        });
        cp.add(btnDraw);
        setVisible(true);
    }

    public void btnDraw_ActionPerformed(ActionEvent evt) {
        BaumZeichner zeichner = new BaumZeichner(1000, 400, Parser.termbaum);
    }

    public static void main(String[] args) {
        new GUI("GUI");
    }

}
