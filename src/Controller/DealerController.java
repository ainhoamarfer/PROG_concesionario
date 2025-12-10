package Controller;

import Model.CarDTO;
import Model.ClientDTO;
import Model.SaleForm;
import Model.SaleDTO;
import View.DealerView;
import View.TypeCarSearch;

import java.util.ArrayList;
import java.util.Date;
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
    private List<SaleDTO> sales;

    private DealerView view;

    public static final int INITIAL_CARS = 5;
    public static final int INITIAL_CLIENTS = 2;
    public static final int INITIAL_SALES = 0;
    int salesCount = 2;

    public DealerController(DealerView view) {

        //Memoria del programa
        this.view = view;
        cars = new ArrayList<>(INITIAL_CARS);
        clients = new ArrayList<>(INITIAL_CLIENTS);
        sales = new ArrayList<>(INITIAL_SALES);
        loadCars();
        loadClients();
        loadSales();
        run();
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
    /**
     *
     * @return lista de ventas
     *
     */
    public List<SaleDTO> loadSales(){
        sales.add(new SaleDTO(1, clients.get(1), cars.get(1), new Date(), 62000));
        sales.add(new SaleDTO(2, clients.get(0), cars.get(0), new Date(), 54000));

        return sales;
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
                CarDTO car = view.registerCar();
                boolean allowedCar = verifyNewCar(car);
                if(allowedCar){
                    addCar(car);
                }
            }

            if(option == EnumOptions.SHOW){
                view.showAvaliableCars(cars);
            }

            if(option == EnumOptions.LOOK_FOR){
                menuSearchCar();
            }

            if(option == EnumOptions.REGISTER_CLIENT){
                ClientDTO client= view.registerClientData();
                boolean allowedClient = verifyNewClientDNI(client);
                if(allowedClient){
                    addClient(client);
                }
            }
            if(option == EnumOptions.REGISTER_SALE){
               SaleForm newSale = view.registerSaleData();
               registerSale(newSale);

            }
            if(option == EnumOptions.LIST_SALES){
                view.showListSales(sales);
            }
        }
    }

    public boolean verifyNewClientDNI(ClientDTO client){
        String dni = client.getDni();

        for (ClientDTO clientDTO : clients) {
            if (clientDTO.getDni().equals(dni)) {
                view.errorMsg("El cliente ya existe");
                return false;
            }
        }
        return true;
    }

    public boolean verifyNewCar(CarDTO car){
        String plate = car.getCarPlate();

        for (CarDTO carDTO : cars) {
            if (carDTO.getCarPlate().equals(plate)) {
                view.errorMsg("El coche ya existe");
                return false;
            }
        }
        return true;
    }

    public void addClient(ClientDTO newClient){
        clients.add(newClient);
        view.msgConffirmation("El cliente fue registrado correctamente");
    }

    public void addCar(CarDTO newCar){
        cars.add(newCar);
        view.msgConffirmation("El coche fue registrado correctamente");
    }

    public void menuSearchCar(){
        int op;
        while(true){
            op = view.typeOfCarSearch();
            TypeCarSearch option = TypeCarSearch.values()[op];

            if(option == TypeCarSearch.LABEL){
                view.searchCarByLabel(cars);
            }

            if(option == TypeCarSearch.PRICE){
                view.searchCarByPrice(cars);
            }

            if(option == TypeCarSearch.YEAR){
                view.searchCarByYear(cars);
            }
            run();
        }
    }

    public void registerSale(SaleForm form){

        CarDTO saleCar = null;
        for (CarDTO car : cars) {
            if(car.getCarPlate().equals(form.getPlateCar()) && !car.isSold()){
                saleCar = car;
                car.setSold(true);
                break;
            }
        }

        if(saleCar == null){
            view.errorMsg("El coche no existe");
            return;
        }

        ClientDTO saleClient = null;
        for (ClientDTO client : clients) {
            if(client.getDni().equals(form.getDniClient())){
                saleClient = client;
                break;
            }
        }

        if(saleClient == null){
            view.errorMsg("El cliente no existe");
            return;
        }

        sales.add(new SaleDTO(salesCount + 1, saleClient, saleCar, new Date(), saleCar.getPrice()));
        view.msgConffirmation("La venta se realizó correctamente");

    }
}
