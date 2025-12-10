package Model;

import java.util.Date;

public class SaleDTO {
    //• Ventas con idVenta, cliente, coche, fecha y precio de venta.

    private int idSales;
    private ClientDTO client;
    private CarDTO car;
    private Date date;
    private double price;

    public SaleDTO(int idSales, ClientDTO client, CarDTO car, Date date, double price) {
        this.idSales = idSales;
        this.client = client;
        this.car = car;
        this.date = date;
        this.price = price;
    }

    public int getIdSales() {
        return idSales;
    }

    public void setIdSales(int idSales) {
        this.idSales = idSales;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
