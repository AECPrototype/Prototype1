import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Interface which is shared just by the clients of the system
 */
public interface ServerInterface extends Remote {

	
	void sendRequest (Request r) throws Exception, RemoteException;
	void setRequest (Request r)throws Exception, RemoteException;
	Request getRequest () throws Exception, RemoteException;
	void setResponse(Response r)throws Exception, RemoteException;
	Response getResponse()throws Exception, RemoteException;
	void sendResponse (Response rs)throws Exception, RemoteException ;
	int getCounter() throws Exception, RemoteException;
	void setCounter(int count) throws Exception, RemoteException;
	void setread(String data) throws Exception, RemoteException;
	String getread() throws Exception, RemoteException;
}
