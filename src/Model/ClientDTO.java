package Model;

import java.util.List;

public class ClientDTO {
    //• Clientes con dni, nombre y teléfono

    private String name;
    private String dni;
    private int telNumber;

    public ClientDTO(String name, String dni, int telNumber) {
        this.name = name;
        this.dni = dni;
        this.telNumber = telNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }
}
