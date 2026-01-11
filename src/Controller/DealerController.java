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



    private final List<CarDTO> cars;
    private final List<ClientDTO> clients;
    private final List<SaleDTO> sales;

    private final DealerView view;

    public static final int INITIAL_CARS = 5;
    public static final int INITIAL_CLIENTS = 2;
    public static final int INITIAL_SALES = 2;
    int salesCount = 2;

    /**
     * Controlador principal de la aplicación del concesionario.
     * Gestiona colecciones de coches, clientes y ventas, y coordina
     * la interacción con la vista (`DealerView`).
     */
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
     * Carga una lista por defecto de coches en memoria.
     * Este método devuelve la lista actual para facilitar pruebas.
     *
     * @return lista de `CarDTO` cargados
     */
    public List<CarDTO> loadCars(){
        cars.add(new CarDTO("Toyota", "T542", "123654WFT", 25000, 2000, 152000, true));
        cars.add(new CarDTO("Honda", "R125", "129545ERG", 40000, 2012, 20000, true));
        cars.add(new CarDTO("Toyota", "G159", "129754JUT", 45000, 1995, 48000, false));
        cars.add(new CarDTO("Subaru", "H987", "917354EFT", 38000, 2005, 62000, false));
        cars.add(new CarDTO("Suzuki", "W547", "554871PDN", 32000, 2000, 85000, false));

        return cars;
    }

    /**
     * Carga una lista por defecto de clientes en memoria.
     *
     * @return lista de `ClientDTO` cargados
     */
    public List<ClientDTO> loadClients(){
        clients.add(new ClientDTO("Sara", "12396584C", "+36 659887741"));
        clients.add(new ClientDTO("Pablo", "65988521C", "+36 986552148"));

        return clients;
    }
    /**
     * Carga una lista por defecto de ventas en memoria.
     *
     * @return lista de `SaleDTO` cargadas
     */
    public List<SaleDTO> loadSales(){
        sales.add(new SaleDTO(1, clients.get(1), cars.get(1), new Date(), 62000));
        sales.add(new SaleDTO(2, clients.get(0), cars.get(0), new Date(), 54000));

        return sales;
    }

    /**
     * Opciones del menú principal del controlador.
     */
    enum EnumOptions {
        ADD, SHOW, LOOK_FOR, REGISTER_CLIENT, REGISTER_SALE, LIST_SALES, STATISTICS, EXIT
    }

    /**
     * Bucle principal del controlador que muestra el menú y ejecuta
     * las acciones solicitadas por el usuario a través de la vista.
     *
     * Nota: el método entra en un bucle infinito hasta que la vista
     * gestione una salida (esto reproduce el comportamiento original).
     */
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
            if(option == EnumOptions.STATISTICS){
                double averagePrice = statisticsAveragePriceSoldCars(sales);
                double mostExpensivePrice = statisticsMostExpensiveCar(sales);
                List<CarDTO> soldCars = statisticsCountCarsSold(sales);
                view.showStatistics(averagePrice, mostExpensivePrice, soldCars);
            }
            if(option == EnumOptions.EXIT){
                view.msgConffirmation("Chao chao");
                break;
            }
        }
    }

    /**
     * Verifica que el DNI del nuevo cliente no exista ya en la lista.
     *
     * @param client cliente a verificar
     * @return `true` si el DNI no existe y se puede añadir; `false` si ya existe
     */
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

    /**
     * Verifica que la matrícula del nuevo coche no exista ya en la lista.
     *
     * @param car coche a verificar
     * @return `true` si la matrícula no existe y se puede añadir; `false` si ya existe
     */
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

    /**
     * Añade un nuevo cliente a la colección y notifica a la vista.
     *
     * @param newClient cliente a añadir
     */
    public void addClient(ClientDTO newClient){
        clients.add(newClient);
        view.msgConffirmation("El cliente fue registrado correctamente");
    }

    /**
     * Añade un nuevo coche a la colección y notifica a la vista.
     *
     * @param newCar coche a añadir
     */
    public void addCar(CarDTO newCar){
        cars.add(newCar);
        view.msgConffirmation("El coche fue registrado correctamente");
    }

    /**
     * Muestra el submenú de búsqueda de coches y delega la búsqueda
     * a la vista según el tipo seleccionado.
     */
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

    /**
     * Registra una venta a partir de los datos del formulario.
     * Busca el coche por matrícula y el cliente por DNI; marca el coche
     * como vendido y añade la venta a la colección si todo es válido.
     *
     * @param form formulario con los datos de la venta
     */
    public void registerSale(SaleForm form){

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

        for(SaleDTO sale : sales){
            if(sale.getIdSales() == form.getPrice()){
                view.errorMsg("El ID de esta venta no es valido");
            }
        }

        sales.add(new SaleDTO(salesCount + 1, saleClient, saleCar, new Date(), saleCar.getPrice()));
        view.msgConffirmation("La venta se realizó correctamente");

    }

    public double statisticsAveragePriceSoldCars(List<SaleDTO> sales) {
        double averagePrice = 0.0;
        double priceSoldCar = 0.0;
        int countSoldCars = 0;

        for (int i = 0; i < sales.size(); i++) {
            SaleDTO sale = sales.get(i);
            CarDTO car = sale.getCar();
            priceSoldCar = car.getPrice();
            averagePrice = priceSoldCar + averagePrice;
            countSoldCars++;
        }

        averagePrice = averagePrice / countSoldCars;

        return averagePrice;
    }

    public double statisticsMostExpensiveCar(List<SaleDTO> sales) {
        double priceMostExpensive = 0.0;

        for (int i = 0; i < sales.size(); i++) {
            SaleDTO sale = sales.get(i);
            CarDTO car = sale.getCar();
            if(priceMostExpensive < car.getPrice()){
                priceMostExpensive =  car.getPrice();
            }
            priceMostExpensive = car.getPrice();
        }
        return priceMostExpensive;
    }

    public List<CarDTO> statisticsCountCarsSold(List<SaleDTO> sales) {
        List<CarDTO> soldCars = new ArrayList<>();

        for (int i = 0; i < sales.size(); i++) {
            SaleDTO sale = sales.get(i);
            CarDTO car = sale.getCar();
            soldCars.add(car);
        }
        return soldCars;
    }
}
