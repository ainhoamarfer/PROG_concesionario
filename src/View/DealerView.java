package View;

import Model.CarDTO;
import Model.ClientDTO;
import Model.SalesDTO;

import java.util.List;
import java.util.Scanner;

public class DealerView {
    //1. Añadir coches al concesionario
    //2. Mostar los coches disponibles
    //3. Buscar coches usando: a. Marca b. Rango de precios c. Año
    //4. Registrar un nuevo cliente, mostrando un error si ya existe un cliente con el
    //mismo dni
    //5. Registrar una venta, indicándole el cliente y el coche involucrados
    //6. Listar ventas

    Scanner sc = new Scanner(System.in);

    public DealerView() {

    }

    public int menu() {
        System.out.println("\nBienvenidxs al concesionario Carricoche, ¿Qué desea hacer?");
        System.out.println("1. Añadir coches al concesionario");
        System.out.println("2. Mostar los coches disponibles");
        System.out.println("3. Buscar coches por marca, rango de precios o año.");
        System.out.println("4. Registrar un nuevo cliente");
        System.out.println("5. Registrar una venta");
        System.out.println("6. Listar ventas\n");

        int opcion = -1;
        while (true) {
            System.out.println("Selecciona una opción");
            opcion = sc.nextInt();
            //Es para corregir un comportamiento raro en nextInt donde no consume el /n
            sc.nextLine();
            if (opcion >= 1 && opcion <= 7) {
                break;
            }

            System.err.println("Introduce una opción valida");
        }
        return opcion - 1;
    }

    public CarDTO registerCar() {
        System.out.println("Introduce la marca del coche a registrar");
        String label = sc.nextLine();

        System.out.println("El modelo");
        String model = sc.nextLine();

        System.out.println("La matricula");
        String carPlate = sc.nextLine();

        System.out.println("Su precio");
        double price = Double.parseDouble(sc.nextLine());

        System.out.println("Cuantos años tiene");
        int years = Integer.parseInt(sc.nextLine());

        System.out.println("Y sus kilómetros");
        double km = Double.parseDouble(sc.nextLine());

        return new CarDTO(label, model, carPlate, price, years, km);
    }



    public void showAvaliableCars(List<CarDTO> cars) {

        if (cars.isEmpty()) {
            System.out.println("No hay coches disponibles");
            return;
        }else System.out.print("Hay " + cars.size() + " coches disponibles:\n");
        for (int i = 0; i < cars.size(); i++){
            CarDTO car = cars.get(i);
            System.out.println((i + 1)+ "." + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                    car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");

        }

    }

    public int typeOfCarSearch() {
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

   public void searchCarByLabel(List<CarDTO> cars) {

       System.out.println("Cuál es la matricula del coche que estas buscando?");
       String carPlate = sc.nextLine();

       CarDTO car = null;
       for (int i = 0; i < cars.size(); i++) {
           car = cars.get(i);
           if (car.getCarPlate().equals(carPlate)) {
               System.out.println("Coche encontrado: " + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                       car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
           } else System.err.println("No tenemos ningún coche registrado con esa matrícula, lo siento.");
       }
   }

    public void searchCarByPrice(List<CarDTO> cars) {

        System.out.println("Sobre que precio ronda el coche que estas buscando?");
        double price = Double.parseDouble(sc.nextLine());

        CarDTO car = null;
        for (int i = 0; i < cars.size(); i++) {
            car = cars.get(i);
            if (car.getPrice() == price) {
                System.out.println("Coche encontrado: " + car.getLabel() + " | Modelo: " + car.getModel() + " | Matrícula: " +
                        car.getCarPlate() + " | Precio: " + car.getPrice() + " euros | Año: " + car.getYears() + " | " + car.getKm() + " km.");
            } else System.err.println("No tenemos ningún coche registrado con ese precio, lo siento.");
        }

    }

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

    public ClientDTO registerClient() {

        System.out.println("Introduce el DNI del nuevo cliente");
        String dni = sc.nextLine();

        System.out.println("Introduce el nombre del cliente");
        String name = sc.nextLine();

        System.out.println("Y su teléfono móvil");
        int tel = Integer.parseInt(sc.nextLine());

        return new ClientDTO(name, dni, tel);
    }

    public SalesDTO registerSale() {
        return null;
    }

    public void showListSales(List<SalesDTO> sales) {
        for (SalesDTO sale : sales) {
            System.out.println(sale);
        }
    }

    public void errorMsg(String msg) {
        System.err.println(msg);
    }

    public void msgConffirmation(String msg) {
        System.out.println(msg);
    }
}
