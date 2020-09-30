package Model;

public class CovidData {

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public double getRecoveredPercentage() {
        return recoveredPercentage;
    }

    public void setRecoveredPercentage(double recoveredPercentage) {
        this.recoveredPercentage = recoveredPercentage;
    }

    public double getDeathsPercentage() {
        return deathsPercentage;
    }

    public void setDeathsPercentage(double deathsPercentage) {
        this.deathsPercentage = deathsPercentage;
    }

    public double getActivePercentage() {
        return activePercentage;
    }

    public void setActivePercentage(double activePercentage) {
        this.activePercentage = activePercentage;
    }
    
    public CovidData(String country) {
        this.country = country;
    }
    
    private String country;
    private long total, recovered, deaths, active;
    private double recoveredPercentage, deathsPercentage, activePercentage;
    
}
