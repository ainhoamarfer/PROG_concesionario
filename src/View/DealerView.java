package View;

import Model.CarDTO;
import Model.ClientDTO;
import Model.SaleForm;
import Model.SaleDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Interfaz/contrato de la vista del concesionario.
 * Contiene los métodos que la capa de presentación debe implementar
 * para interactuar con el `DealerController`.
 */
public class DealerView {

    public static final int PRICE_SCOPE = 5000;
    Scanner sc = new Scanner(System.in);

    /**
     * Muestra el menú principal y devuelve la opción seleccionada por el usuario.
     *
     * @return índice de la opción elegida (correspondiente a `EnumOptions` del controlador)
     */
    public int menu() {
        System.out.println("\n+++++++ Concesionario Carricoche +++++++");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Añadir coches al concesionario");
        System.out.println("2. Mostar los coches disponibles");
        System.out.println("3. Buscar coches por marca, rango de precios o año.");
        System.out.println("4. Registrar un nuevo cliente");
        System.out.println("5. Registrar una venta");
        System.out.println("6. Listar ventas");
        System.out.println("7. Estadísticas");
        System.out.println("8. Salir\n");

        int opcion = -1;
        while (true) {
            System.out.println("Selecciona una opción");
            opcion = sc.nextInt();
            //Es para corregir un comportamiento raro en nextInt donde no consume el /n
            sc.nextLine();
            if (opcion >= 1 && opcion <= 8) {
                break;
            }

            System.err.println("Introduce una opción valida");

        }
        return opcion - 1;
    }

    /**
     * Solicita al usuario los datos de un coche y devuelve un DTO con la información.
     *
     * @return `CarDTO` con los datos introducidos por el usuario
     */
    public CarDTO registerCar() {
        System.out.println("··············REGISTRAR COCHE··············");

        System.out.println("Introduce la marca del coche a registrar");
        String label = sc.nextLine();

        System.out.println("El modelo");
        String model = sc.nextLine();

        System.out.println("La matricula");
        String carPlate = sc.nextLine();

        while (carPlate.isEmpty()) {
            System.err.println("Es obligatorio introducir este campo");
            carPlate = sc.nextLine();
        }

        System.out.println("Su precio");
        double price = Double.parseDouble(sc.nextLine());

        System.out.println("¿De qué año es?");
        int years = Integer.parseInt(sc.nextLine());

        System.out.println("Y sus kilómetros");
        double km = Double.parseDouble(sc.nextLine());

        boolean sold = false;

        return new CarDTO(label, model, carPlate, price, years, km, sold);
    }

    /**
     * Muestra la lista de coches disponibles.
     *
     * @param cars lista de coches a mostrar
     */
    public void showAvaliableCars(List<CarDTO> cars) {
        System.out.println("······································COCHES DEL CONCESIONARIO······································");

        if (cars.isEmpty()) {
            System.out.println("No hay coches disponibles");
            return;
        }else System.out.print("Hay " + cars.size() + " coches disponibles:\n");
        for (int i = 0; i < cars.size(); i++){
            CarDTO car = cars.get(i);
            if (!car.isSold()) {
                System.out.println((i + 1)+ "." + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
                System.out.println("----------------------------------------------------------------------------------------------");
            }
        }
    }


    /**
     * Muestra el submenú de búsqueda de coches y devuelve la opción seleccionada.
     *
     * @return índice de la opción de búsqueda seleccionada (correspondiente a `TypeCarSearch`)
     */
    public int typeOfCarSearch() {
        System.out.println("··············BUSCAR COCHE··············");
        System.out.println("¿Cómo prefieres buscar el coche?: ");
        System.out.println("1. Por marca");
        System.out.println("2. Por rango de precios");
        System.out.println("3. Por año");

        int option = -1;
        while (true) {
            System.out.println("Selecciona una opción");
            option = sc.nextInt();

            sc.nextLine();
            if (option >= 1 && option <= 4) {
                break;
            }

            System.err.println("Introduce una opción valida");
        }
        return option - 1;
    }

    /**
     * Realiza una búsqueda por marca/etiqueta y muestra los resultados.
     *
     * @param cars lista de coches donde buscar
     */
   public void searchCarByLabel(List<CarDTO> cars) {

       System.out.println("Cuál es la matricula del coche que estas buscando?");
       String carPlate = sc.nextLine();
        boolean foundCar = false;

       CarDTO car = null;
       for (int i = 0; i < cars.size(); i++) {
           car = cars.get(i);
           if (car.getCarPlate().equals(carPlate)) {
               System.out.println("Coche encontrado: " + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                       car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
               foundCar = true;
           } else foundCar = false;
       }

       if (!foundCar) {
           System.err.println("No tenemos ningún coche registrado con esa matrícula, lo siento.");
       }
   }

    /**
     * Realiza una búsqueda por precio y muestra los resultados.
     *
     * @param cars lista de coches donde buscar
     */
    public void searchCarByPrice(List<CarDTO> cars) {

        System.out.println("Sobre que precio ronda el coche que estas buscando?");
        double price = Double.parseDouble(sc.nextLine());

        CarDTO car = null;
        for (int i = 0; i < cars.size(); i++) {
            car = cars.get(i);
            if (car.getPrice() < price + PRICE_SCOPE && car.getPrice() > price - PRICE_SCOPE && !car.isSold()) {
                System.out.println("Coches encontrados: " + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                        car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
            } else System.err.println("No tenemos ningún coche registrado que ronde ese precio, lo siento.");
        }

    }

    /**
     * Realiza una búsqueda por año y muestra los resultados.
     *
     * @param cars lista de coches donde buscar
     */
    public void searchCarByYear(List<CarDTO> cars) {

        System.out.println("De qué año es el coche que estas buscando?");
        int year = sc.nextInt();

        CarDTO car = null;
        for (int i = 0; i < cars.size(); i++) {
            car = cars.get(i);
            if (car.getYears() == year) {
                System.out.println("Coche encontrado: " + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                        car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
            } else System.err.println("No tenemos ningún coche registrado con ese año, lo siento.");
        }
    }

    /**
     * Solicita al usuario los datos de un nuevo cliente y devuelve un DTO.
     *
     * @return `ClientDTO` con los datos del cliente
     */
    public ClientDTO registerClientData() {
        System.out.println("··············REGISTRAR NUEVO CLIENTE··············");

        System.out.println("Introduce el DNI del nuevo cliente");
        String dni = sc.nextLine();

        while (dni.length() != 9 && dni.isEmpty()) {
            System.err.println("Ese DNI no es valido");
            dni = sc.nextLine();
        }

        System.out.println("Introduce el nombre del cliente");
        String name = sc.nextLine();

        System.out.println("Y su teléfono móvil");
        String tel = sc.nextLine();

        return new ClientDTO(name, dni, tel);
    }

    /**
     * Solicita al usuario los datos de una venta y devuelve un formulario con la información.
     *
     * @return `SaleForm` con los datos necesarios para registrar una venta
     */
    public SaleForm registerSaleData() {
        System.out.println("··············REGISTRAR VENTA··············");

        System.out.println("Dime la matricula del coche a vender");
        String plate = sc.nextLine();

        while(plate.length() != 9 && plate.isEmpty()) {
            System.err.println("Esta matrícula no es valida");
            plate = sc.nextLine();
        }

        System.out.println("Ahora el DNI del comprador");
        String dni = sc.nextLine();

        while(dni.length() != 9 && dni.isEmpty()) {
            System.err.println("Ese DNI no es valido");
            dni = sc.nextLine();
        }

        //tengo dudas de la validación del precio, se que no esta bien
        System.out.println("Por último el precio de venta");
        String p = sc.nextLine().trim();

        while(p.isEmpty()) {
            System.err.println("Ese precio no es valido");
            p = sc.nextLine().trim();
        }

        double price = Double.parseDouble(p);

        while(price <= 0) {
            System.err.println("Ese DNI no es valido");
            price = Double.parseDouble(sc.nextLine());
        }

        return new SaleForm(plate, dni, price);
    }

    /**
     * Muestra la lista de ventas registradas.
     *
     * @param sales lista de `SaleDTO` a mostrar
     */
    public void showListSales(List<SaleDTO> sales) {
        System.out.println("········································LISTA DE VENTAS········································");

        if (sales.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }else System.out.print("Hay " + sales.size() + " ventas registradas:\n");

        for (int i = 0; i < sales.size(); i++){
            SaleDTO sale = sales.get(i);
            CarDTO car = sale.getCar();
            String plate = car.getCarPlate();
            ClientDTO client = sale.getClient();
            String dni = client.getDni();
            System.out.println((i + 1)+ ". ID venta: " + sale.getIdSales() + " | Matrícula coche: " + plate + " | DNI Cliente: " +
                    dni + " | Fecha compra: " + sale.getDate() + " | Precio: " + sale.getPrice() + " euros");
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }

    /**
     * Muestra un mensaje de error al usuario.
     *
     * @param msg texto del error a mostrar
     */
    public void errorMsg(String msg) {
        System.err.println(msg);
    }

    /**
     * Muestra un mensaje de confirmación al usuario.
     *
     * @param msg texto de confirmación a mostrar
     */
    public void msgConffirmation(String msg) {
        System.out.println(msg);
    }

    public void showStatistics(Double average, double mostExpensivePrice, List<CarDTO> soldCars) {
        System.out.println("················ESTADÍSTICAS················");
        System.out.println("Precio medio de los coches vendidos: " + average);
        System.out.println("--------------------------------------------");
        System.out.println("Coche más caro vendido: " + mostExpensivePrice);
        System.out.println("--------------------------------------------");
        System.out.println("Coches vendidos: ");

        for (CarDTO car : soldCars) {
            String plate = car.getCarPlate();
            System.out.println(plate);
        }

        int countSoldCars = soldCars.size();
        double totalPriceSoldCars = 0;
        for (CarDTO car : soldCars) {
            totalPriceSoldCars = totalPriceSoldCars + car.getPrice();
        }

        System.out.println("--------------------------------------------");
        System.out.println("Se han vendido " + countSoldCars + " en total");
        System.out.println("--------------------------------------------");
        System.out.println("Suma total de precios de coches vendidos: " + totalPriceSoldCars);
        System.out.println("·············FIN DE ESTADÍSTICAS·············");
    }

}
