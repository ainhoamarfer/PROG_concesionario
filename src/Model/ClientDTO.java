package Model;

import java.util.List;

public class ClientDTO {
    //• Clientes con dni, nombre y teléfono

    private String name;
    private String dni;
    private String telNumber;

    public ClientDTO(String name, String dni, String telNumber) {
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

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
