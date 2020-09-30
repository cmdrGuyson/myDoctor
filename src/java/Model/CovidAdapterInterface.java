package Model;

//Interface used by CovidAdapter class
public interface CovidAdapterInterface {

    public long getTotalCases();

    public long getActiveCases();

    public long getTotalDeaths();

    public long getTotalRecovered();
    
    public double getActiveCasesPercentage();
    
    public double getTotalDeathsPercentage();
    
    public double getTotalRecoveredPercentage();
    
    public void setCountry(String country);

}
