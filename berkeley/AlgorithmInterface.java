import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalTime;

public interface AlgorithmInterface extends Remote {

    public long getTimeDifference(LocalTime lt) throws RemoteException;

    public void timeAdjustment(long time) throws RemoteException;
}