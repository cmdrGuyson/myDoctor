package Model;

//This is a concrete subject class that will contain covid19 data taken from the covid19 adapter for a certain country

import java.util.ArrayList;
import java.util.List;

public class CovidDataSubject implements Subject {
    
    private final List<Observer> observers = new ArrayList<>();
    private final CovidAdapterInterface covidAdapter;
    private final CovidData covidData;
    
    public CovidDataSubject() {
        covidAdapter = new CovidAdapter();
        covidData = new CovidData("global");
        getData();
    }
    
    public void setCountry(String country) {
        covidAdapter.setCountry(country);
        covidData.setCountry(country);
        getData();
        notifyObservers();
    }
    
    private void getData() {
        covidData.setTotal(covidAdapter.getTotalCases());
        covidData.setRecovered(covidAdapter.getTotalRecovered());
        covidData.setActive(covidAdapter.getActiveCases());
        covidData.setDeaths(covidAdapter.getTotalDeaths());
        covidData.setActivePercentage(covidAdapter.getActiveCasesPercentage());
        covidData.setDeathsPercentage(covidAdapter.getTotalDeathsPercentage());
        covidData.setRecoveredPercentage(covidAdapter.getTotalRecoveredPercentage());
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(covidData);
        }
    }
}
