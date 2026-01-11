package Model;

public class SaleForm {

    private String plateCar;
    private String dniClient;
    private double price;

    public SaleForm(String plateCar, String dniClient, double price) {
        this.plateCar = plateCar;
        this.dniClient = dniClient;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPlateCar() {
        return plateCar;
    }

    public String getDniClient() {
        return dniClient;
    }

}
