package Model;

public class SaleForm {

    private String plateCar;
    private String dniClient;

    public SaleForm(String plateCar, String dniClient) {
        this.plateCar = plateCar;
        this.dniClient = dniClient;
    }

    public String getPlateCar() {
        return plateCar;
    }

    public String getDniClient() {
        return dniClient;
    }

}
