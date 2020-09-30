package Model;

public class BarChartView implements Observer {
    
    private long total, recovered, deaths, active;

    public long getTotal() {
        return total;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getActive() {
        return active;
    }

    @Override
    public void update(CovidData covidData) {
        total = covidData.getTotal();
        recovered = covidData.getRecovered();
        deaths = covidData.getDeaths();
        active = covidData.getActive();
    }
}
