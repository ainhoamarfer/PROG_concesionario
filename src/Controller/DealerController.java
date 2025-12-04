package Controller;

import Model.CarDTO;
import Model.ClientDTO;
import Model.SalesDTO;
import View.DealerView;

import java.util.ArrayList;
import java.util.List;

public class DealerController {
    //1. Añadir coches al concesionario
    //2. Mostar los coches disponibles
    //3. Buscar coches usando:
    //a. Marca
    //b. Rango de precios
    //c. Año
    //4. Registrar un nuevo cliente, mostrando un error si ya existe un cliente con el
    //mismo dni
    //5. Registrar una venta, indicándole el cliente y el coche involucrados
    //6. Listar ventas



    private List<CarDTO> cars;
    private List<ClientDTO> clients;
    private List<SalesDTO> sales;

    private DealerView view;

    public static final int INITIAL_CARS = 5;
    public static final int INITIAL_CLIENTS = 2;
    public static final int INITIAL_SALES = 0;

    public DealerController(DealerController view) {

        //Memoria del programa
        this.view = view;
        cars = new ArrayList<>(INITIAL_CARS);
        clients = new ArrayList<>(INITIAL_CLIENTS);
        sales = new ArrayList<>(INITIAL_SALES);
        loadCars();
        loadClients();
        run();
        int carCount = 0;
    }

    /**
     *
     * @return una lista de coches
     */
    public List<CarDTO> loadCars(){
        cars.add(new CarDTO("Toyota", "T542", "123654WFT", 95000, 2000, 152000));
        cars.add(new CarDTO("Honda", "R125", "129545ERG", 1000000, 2012, 20000));
        cars.add(new CarDTO("Toyota", "G159", "129754JUT", 45000, 1995, 48000));
        cars.add(new CarDTO("Subaru", "H987", "917354EFT", 78000, 2005, 62000));
        cars.add(new CarDTO("Suzuki", "W547", "554871PDN", 32000, 2000, 85000));

        return cars;
    }

    /**
     *
     * @return lista de clientes
     *
     */
    public List<ClientDTO> loadClients(){
        clients.add(new ClientDTO("Sara", "12396584C", 659887741));
        clients.add(new ClientDTO("Pablo", "65988521C", 986552148));

        return clients;
    }

    public CarDTO addCar(CarDTO car){
        cars.add(car);
        return car;//ver si devuelvo boolean, void o cardto para comprobar que no hay un coche con la misma matricula
    }

    enum EnumOptions {
        ADD, SHOW, LOOK_FOR, REGISTER_CLIENT, REGISTER_SALE, LIST_SALES,
    }

    public void run(){
        int op;
        while(true){
            op = view.menu();
            EnumOptions option = EnumOptions.values()[op];

            if(option ==  EnumOptions.ADD){
                CarDTO car = view.addCar();

                //addCar();
            }
            if(option == EnumOptions.SHOW){
                List<CarDTO> carsAvaliable = view.showAvaliableCars();

            }
            if(option == EnumOptions.LOOK_FOR){
                CarDTO foundCar = view.lookForCar();

                //view.mostrarAlumnos(alumnos);
            }
            if(option == EnumOptions.REGISTER_CLIENT){
                String dni = view.registerClient();


            }
            if(option == EnumOptions.REGISTER_SALE){
                Model.SalesDTO newSale = view.registerSale();

            }
            if(option == EnumOptions.LIST_SALES){
                List<SalesDTO> sales = view.showListSales();
            }
            return;
        }
    }

    public boolean verifyNewClientDNI(ClientDTO client, String dni){
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getDni().equals(dni)){
                System.err.println("El cliente ya existe");
                return false;
            }
        }
    }


}
