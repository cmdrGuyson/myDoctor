package Model;

//This interface will be realized by all concrete subjects in the system
public interface Subject {
    
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
    
}