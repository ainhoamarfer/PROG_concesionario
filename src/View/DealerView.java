package View;

import Model.CarDTO;
import Model.ClientDTO;
import Model.SalesDTO;

import javax.swing.plaf.synth.SynthOptionPaneUI;
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
        System.out.println("Menu Principal");
        System.out.println("1. Añadir coches al concesionario");
        System.out.println("2. Mostar los coches disponibles");
        System.out.println("3. Buscar coches por marca, rango de precios o año.");
        System.out.println("4. Registrar un nuevo cliente");
        System.out.println("5. Registrar una venta");
        System.out.println("6. Listar ventas");

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

    public CarDTO addCar() {
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

    public List<CarDTO> showAvaliableCars() {

        return List.of();
    }

    public CarDTO lookForCar(CarDTO carToLookFor) {

        return null;
    }

    public boolean registerClient(List<ClientDTO> clients) {
        System.out.println("Introduce el nombre del cliente");
        String name = sc.nextLine();

        System.out.println("Su DNI");
        String dni = sc.nextLine();

        System.out.println("Y su teléfono móvil");
        String tel = sc.nextLine();

        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getDni().equals(dni)){
                return false;
            }
        }
        return true;//ver si necesito dos funciones, una de validar y otra de añadir.
    }

    public SalesDTO registerSale() {
        return null;
    }

    public List<SalesDTO> showListSales() {
    }
}
