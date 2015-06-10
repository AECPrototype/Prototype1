import java.io.FileWriter;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;

public class ServerImplementation extends UnicastRemoteObject implements
		ServerInterface {
	boolean checkRequest;
	boolean finished;
	Request r=null;
	Response response=null;
	int qcounter=0;
	static String Data;
	protected ServerImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * @Override public void read_Reply(String url, ServerImplementation rc)
	 * throws Exception { // TODO Auto-generated method stub //connect to remote
	 * object // Creating an object from RemoteChatServer and then starting the
	 * // thread for the user ServerInterface cs = (ServerInterface)
	 * Naming.lookup(url); while (true) { System.out.println("acknowledge is:" +
	 * cs.getAcknowledge()); System.out.println("data is:" + cs.getData());
	 * //cs.setReply(1); Thread.sleep(5000); // System.in.read(); } }
	 */
	/*
	 * @Override public int read_and_store(ServerImplementation rc) throws
	 * Exception { // TODO Auto-generated method stub
	 * 
	 * 
	 * while (true) { if (rc.getData() != null) {
	 * System.out.println("This is data " + rc.getData()); FileWriter fw=new
	 * FileWriter (data[0]+".txt");
	 * 
	 * fw.write(data[1]); fw.close(); break; }
	 * 
	 * Thread.sleep(5000); } return 1;
	 * 
	 * }
	 */


	@Override
	public void sendRequest(Request r) throws Exception {
		// TODO Auto-generated method stub
		String sender=r.sender;
		String receiver=r.receiver;
		String reqType=r.reqType;
		String key=r.key;
		String value=r.value;
		String conType=r.conType;
		/*
		 * Connecting to receiver
		 */
		ServerInterface si=connect(receiver);
		si.setRequest(r);
		
	}
	public ServerInterface connect (String reciever) throws Exception{
		String url =  reciever;
		/* connect to remote object */
		// Creating an object from RemoteChatServer and then starting the
		// thread for the user
		ServerInterface cs = (ServerInterface) Naming.lookup(url);
		return cs;
		
	}
	
	@Override
	public void setRequest(Request r) {
		// TODO Auto-generated method stub
		this.r=r;
	}

	@Override
	public Request getRequest() {
		// TODO Auto-generated method stub
		return this.r;
	}

	@Override
	public void setResponse(Response r) {
		// TODO Auto-generated method stub
		this.response=r;
	}

	@Override
	public Response getResponse() {
		// TODO Auto-generated method stub
		return this.response;
	}

	@Override
	public void sendResponse(Response rs) throws Exception {
		// TODO Auto-generated method stub
		ServerInterface si=connect(rs.receiver);
		si.setResponse(rs);
		qcounter++;
		si.setCounter(qcounter);
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return qcounter;
	}

	@Override
	public void setCounter(int count) throws Exception, RemoteException {
		// TODO Auto-generated method stub
		this.qcounter+=count;
	}

	@Override
	public void setread(String data) throws Exception, RemoteException {
		// TODO Auto-generated method stub
		ServerImplementation.Data=data;
		
	}

	@Override
	public String getread() throws Exception, RemoteException {
		// TODO Auto-generated method stub
		return ServerImplementation.Data;
	}
}