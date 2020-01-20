import com.polytech.sma.model.Grid;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Stefano
 */
public class AppWindow extends JFrame {
    Grid grid;
    public AppWindow(Grid grid){
        this.grid=grid;
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ViewGrid view;
        JPanel pano=new JPanel();
        pano.setLayout(new GridBagLayout());

        view=new ViewGrid(grid);
        pano.add(view);
        this.setContentPane(pano);
        this.pack();
    }
}
