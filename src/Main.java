import com.polytech.sma.model.Environement;
import com.polytech.sma.model.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
       Environement environement =new Environement(50,50, 20, 200,2);
       //Environement environement =new Environement(10,10, 10, 10);

        System.out.println(environement);
        //environement.pushLeft();
        AppWindow f1;
        f1= new AppWindow(environement);
        f1.setVisible(true);

        for(int i = 0; i<100000; i++){
            List<Integer> range= IntStream.range(0, environement.getAgentsList().size()).boxed().collect(Collectors.toList());

            ArrayList<Agent> agents = environement.getAgentsList();
            for(int j = 0; j< environement.getNumberOfAgents(); j++) {
                int alea = (int) (Math.random() * range.size());

                environement.getAgentsList().get(range.get(alea)).perception();
                environement.getAgentsList().get(range.get(alea)).action();

                range.remove(alea);
            }

            environement.notifyView();
            try {
                Thread.sleep(20);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
