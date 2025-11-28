package View;

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
}
