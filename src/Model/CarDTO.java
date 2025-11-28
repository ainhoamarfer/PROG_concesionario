package Model;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {
    //Coches que como mínimo tengan marca, modelo, matricula, precio, año y kilómetros
    //5 coches y 2 clientes.

    private String labels;
    private String model;
    private String carPlate;
    private double price;
    private int years;
    private double km;

    public CarDTO(String labels, String model, String carPlate, double price, int years, double km) {
        this.labels = labels;
        this.model = model;
        this.carPlate = carPlate;
        this.price = price;
        this.years = years;
        this.km = km;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }
}
