import Controller.DealerController;
import Model.CarDTO;
import Model.ClientDTO;
import View.DealerView;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {


        DealerView dv = new DealerView();
        DealerController dealer = new DealerController(dv);

        dealer.loadCars();
        dealer.loadClients();
        dealer.loadSales();
        dealer.run();





    }


}