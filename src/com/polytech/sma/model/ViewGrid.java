package com.polytech.sma.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Stefano
 */
public class ViewGrid extends JComponent implements KeyListener {

    private Environement myEnvironement;
    private int sizeSlot;

    public ViewGrid(Environement myEnvironement) {
        this.myEnvironement = myEnvironement;
        this.sizeSlot = 800 / myEnvironement.getSizeM();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public static Color getColor(int value) {
        if (value == 0) {
            return Color.WHITE;
        } else {
            return new Color((value * 1000) % 256, (value + 100) % 256, (value + 200) % 256, 200);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        sizeSlot = 800 / myEnvironement.getSizeM();

        g2D.setStroke(new BasicStroke(3));
        g2D.setFont(new Font("Times", Font.BOLD, sizeSlot / 4));
        for (int i = 0; i < myEnvironement.getSizeM(); i++) {
            for (int j = 0; j < myEnvironement.getSizeN(); j++) {
                g2D.setColor(getColor(myEnvironement.getSlot(i, j)));
                g2D.fillRect(j * sizeSlot, i * sizeSlot, sizeSlot, sizeSlot);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(j * sizeSlot, i * sizeSlot, sizeSlot, sizeSlot);
                if (myEnvironement.getSlot(i, j) > 0) {
                    g2D.drawString(Integer.toString(myEnvironement.getSlot(i, j)), j * sizeSlot + sizeSlot / 2, i * sizeSlot + sizeSlot / 2);

                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(sizeSlot * myEnvironement.getSizeM(), sizeSlot * myEnvironement.getSizeN());
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
        //         myEnvironement = new Environement(sizeTab);
        //         this.repaint();

        //     }
        // } else if (ke.getKeyChar() == '+') {
        //     sizeTab++;
        //     myEnvironement = new Environement(sizeTab);

        //     this.repaint();
        // } else if (ke.getExtendedKeyCode() == ke.VK_UP) {

        //     myEnvironement.pushUp();

        //     myEnvironement.controle0();
        //     System.out.println(myEnvironement);
        //     this.repaint();

        // } else if (ke.getExtendedKeyCode() == ke.VK_DOWN) {

        //     myEnvironement.pushDown();

        //     myEnvironement.controle0();
        //     System.out.println(myEnvironement);
        //     this.repaint();
        // } else if (ke.getExtendedKeyCode() == ke.VK_LEFT) {
        //     myEnvironement.pushLeft();
        //     myEnvironement.controle0();
        //     System.out.println(myEnvironement);
        //     this.repaint();

        // } else if (ke.getExtendedKeyCode() == ke.VK_RIGHT) {
        //     myEnvironement.pushRight();
        //     myEnvironement.controle0();
        //     System.out.println(myEnvironement);
        //     this.repaint();
        // }
        // if (myEnvironement.gagne2048()) {
        //     if ((JOptionPane.showConfirmDialog(this, "Vous avez gagné \n Voulez vous rejouer?", "", JOptionPane.YES_NO_OPTION)) == 0) {
        //         myEnvironement.reset(sizeTab);

        //         this.repaint();

        //     } else {
        //         System.exit(0);
        //     }

        // }
        
        //End game

        // if (myEnvironement.pasdecoup()) {
        //     if ((JOptionPane.showConfirmDialog(this, "Vous avez perdu \n Voulez vous rejouer?", "", JOptionPane.YES_NO_OPTION)) == 0) {
        //         myEnvironement.reset(sizeTab);

        //         this.repaint();

        //     } else {
        //         System.exit(0);
        //     }
        // }
    }
}
