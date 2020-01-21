import com.polytech.sma.model.Environement;
import com.polytech.sma.model.ViewGrid;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Stefano
 */
public class AppWindow extends JFrame {
    Environement environement;
    public AppWindow(Environement environement){
        this.environement = environement;
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ViewGrid view;
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        view=new ViewGrid(environement);
        environement.setView(view);
        pano.add(view);
        this.setContentPane(pano);
        this.pack();
    }
}
