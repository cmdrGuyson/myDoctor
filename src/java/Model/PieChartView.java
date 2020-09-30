package Model;

public class PieChartView implements Observer {
    
    private double recoveredPercentage, deathsPercentage, activePercentage;

    @Override
    public void update(CovidData covidData) {
        recoveredPercentage = covidData.getRecoveredPercentage();
        deathsPercentage = covidData.getDeathsPercentage();
        activePercentage = covidData.getActivePercentage();
    }
    
    public double getRecoveredPercentage() {
        return recoveredPercentage;
    }

    public double getDeathsPercentage() {
        return deathsPercentage;
    }

    public double getActivePercentage() {
        return activePercentage;
    }
    
}
