/**
 * <p>Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, Materialien zum schulinternen Lehrplan Informatik SII</p>
 *
 * @version 2014-03-13
 */
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
    private Button btnDraw = new Button();
    private TextField inputField = new TextField();


    public GUI(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dispose();
            }
        });

        int frameWidth = 300;
        int frameHeight = 350;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setResizable(false);

        Panel cp = new Panel(null);
        add(cp);

        inputField.setBounds(20,20,120,25);
        inputField.addActionListener(e -> paintTree(inputField.getText()));
        cp.add(inputField);

        btnDraw.setBounds(20, 60, 120, 25);
        btnDraw.setLabel("zeichne Baum");
        btnDraw.addActionListener(e -> {
            paintTree(inputField.getText());
        });
        cp.add(btnDraw);
        setVisible(true);
    }

    public static void paintTree(String input) {
        if (input.isEmpty()) {
            paintTree(Parser.defaultInput);
        } else {
            BaumZeichner zeichner = new BaumZeichner(1000, 400, Parser.parseString(input));
        }
    }


    public static void main(String[] args) {
        new GUI("GUI");
    }

}
