package Model;

public class CarDTO {
    //Coches que como mínimo tengan marca, modelo, matricula, precio, año y kilómetros
    //5 coches y 2 clientes.

    private String label;
    private String model;
    private String carPlate;
    private double price;
    private int years;
    private double km;
    private boolean sold;

    public CarDTO(String label, String model, String carPlate, double price, int years, double km, boolean sold) {
        this.label = label;
        this.model = model;
        this.carPlate = carPlate;
        this.price = price;
        this.years = years;
        this.km = km;
        this.sold = false;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
