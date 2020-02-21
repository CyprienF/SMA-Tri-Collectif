import com.polytech.sma.model.Environement;
import com.polytech.sma.model.Agent;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Environement environement =new Environement(50,50, 20, 200);
        System.out.println(environement);
        //environement.pushLeft();
        AppWindow f1;
        f1= new AppWindow(environement);
        f1.setVisible(true);

        for(int i = 0; i<10000; i++){
            ArrayList<Agent> agents = environement.getAgentsList();

            for(int j = 0; j< environement.getNumberOfAgents(); j++) {
                environement.getAgentsList().get(j).perception();
                environement.getAgentsList().get(j).action();
            }
            environement.notifyView();
            try {
                Thread.sleep(100);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
