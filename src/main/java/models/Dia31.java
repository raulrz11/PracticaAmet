package models;

public class Dia31 {
    private String localidad;
    private String provincia;
    private double temp_max;
    private String h_temp_max;
    private double temp_min;
    private String h_temp_min;
    private double precipitaion;

    public Dia31(String localidad, String provincia, double temp_max, String h_temp_max, double temp_min, String h_temp_min, double precipitaion) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.temp_max = temp_max;
        this.h_temp_max = h_temp_max;
        this.temp_min = temp_min;
        this.h_temp_min = h_temp_min;
        this.precipitaion = precipitaion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public String getH_temp_max() {
        return h_temp_max;
    }

    public void setH_temp_max(String h_temp_max) {
        this.h_temp_max = h_temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public String getH_temp_min() {
        return h_temp_min;
    }

    public void setH_temp_min(String h_temp_min) {
        this.h_temp_min = h_temp_min;
    }

    public double getPrecipitaion() {
        return precipitaion;
    }

    public void setPrecipitaion(double precipitaion) {
        this.precipitaion = precipitaion;
    }

    @Override
    public String toString() {
        return "Dia31{" +
                "localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", temp_max=" + temp_max +
                ", h_temp_max='" + h_temp_max + '\'' +
                ", temp_min=" + temp_min +
                ", h_temp_min='" + h_temp_min + '\'' +
                ", precipitaion=" + precipitaion +
                '}';
    }
}
