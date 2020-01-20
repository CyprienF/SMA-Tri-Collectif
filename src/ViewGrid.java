import com.polytech.sma.model.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Stefano
 */
public class ViewGrid extends JComponent implements KeyListener {

    private Grid myGrid;
    private int sizeSlot;
    private int sizeTab;

    public ViewGrid(Grid myGrid) {
        this.myGrid = myGrid;
        this.sizeSlot = 600 / myGrid.getSize();
        this.sizeTab = myGrid.getSize();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public static Color getColor(int value) {
        if (value == 0) {
            return Color.WHITE;
        } else {
            return new Color((value * 1000) % 258, (value + 100) % 256, (value + 200) % 256, 200);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        sizeSlot = 600 / myGrid.getSize();

        g2D.setStroke(new BasicStroke(7));
        g2D.setFont(new Font("Times", Font.BOLD, sizeSlot / 4));
        for (int i = 0; i < myGrid.getSize(); i++) {
            for (int j = 0; j < myGrid.getSize(); j++) {
                g2D.setColor(getColor(myGrid.getSlot(i, j)));
                g2D.fillRect(j * sizeSlot, i * sizeSlot, sizeSlot, sizeSlot);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(j * sizeSlot, i * sizeSlot, sizeSlot, sizeSlot);
                if (myGrid.getSlot(i, j) > 0) {
                    g2D.drawString(Integer.toString(myGrid.getSlot(i, j)), j * sizeSlot + sizeSlot / 2, i * sizeSlot + sizeSlot / 2);

                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(sizeSlot * myGrid.getSize(), sizeSlot * myGrid.getSize());
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // if (ke.getKeyChar() == '-') {
        //     if (sizeTab > 2) {
        //         sizeTab--;
        //         myGrid = new Grid(sizeTab);
        //         this.repaint();

        //     }
        // } else if (ke.getKeyChar() == '+') {
        //     sizeTab++;
        //     myGrid = new Grid(sizeTab);

        //     this.repaint();
        // } else if (ke.getExtendedKeyCode() == ke.VK_UP) {

        //     myGrid.pushUp();

        //     myGrid.controle0();
        //     System.out.println(myGrid);
        //     this.repaint();

        // } else if (ke.getExtendedKeyCode() == ke.VK_DOWN) {

        //     myGrid.pushDown();

        //     myGrid.controle0();
        //     System.out.println(myGrid);
        //     this.repaint();
        // } else if (ke.getExtendedKeyCode() == ke.VK_LEFT) {
        //     myGrid.pushLeft();
        //     myGrid.controle0();
        //     System.out.println(myGrid);
        //     this.repaint();

        // } else if (ke.getExtendedKeyCode() == ke.VK_RIGHT) {
        //     myGrid.pushRight();
        //     myGrid.controle0();
        //     System.out.println(myGrid);
        //     this.repaint();
        // }
        // if (myGrid.gagne2048()) {
        //     if ((JOptionPane.showConfirmDialog(this, "Vous avez gagné \n Voulez vous rejouer?", "", JOptionPane.YES_NO_OPTION)) == 0) {
        //         myGrid.reset(sizeTab);

        //         this.repaint();

        //     } else {
        //         System.exit(0);
        //     }

        // }
        
        //End game

        // if (myGrid.pasdecoup()) {
        //     if ((JOptionPane.showConfirmDialog(this, "Vous avez perdu \n Voulez vous rejouer?", "", JOptionPane.YES_NO_OPTION)) == 0) {
        //         myGrid.reset(sizeTab);

        //         this.repaint();

        //     } else {
        //         System.exit(0);
        //     }
        // }
    }
}
