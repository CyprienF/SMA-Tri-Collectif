import com.polytech.sma.model.Grid;

public class Main {

    public static void main(String[] args) {
        Grid grid=new Grid(5, 22);
        System.out.println(grid);
        //grid.pushLeft();
        System.out.println(grid);
        AppWindow f1;
        f1= new AppWindow(grid);
        f1.setVisible(true);
    }
}
