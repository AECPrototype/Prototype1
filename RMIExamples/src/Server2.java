import java.io.FileWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server2 {
	public static void main(String[] args) {
		try {
			/* Instantiate the object that communicate with clients */
			ServerImplementation rc = new ServerImplementation();
			/* Register the object at the rmiregistry */
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("B", rc);
			Request r = null;
			Crud_Functions crud = new Crud_Functions();
			while (true) {
				if (rc.getRequest() != null) {
					r = rc.getRequest();
					switch (r.reqType) {
					case "c":

						crud.putData(r.key+ "s2", r.value);
						// Successful Replication
						Response rs = new Response(r.receiver, r.sender, true);
						rc.sendResponse(rs);
						// Sending data to other server

						r.sender = "rmi://localhost/B";
						r.receiver = "rmi://localhost/C";
						r.conType = "sync";
						r.Starter = r.Starter;
						System.out.println("Starter is:" + r.Starter);
						try {
							rc.setRequest(r);
							rc.sendRequest(r);
						} catch (Exception e) {
							System.out.println("Server 3 not Availble!");
						}

						break;
					case "d":
						crud.deleteData(r.key + "s2");
						break;
					case "u":
						crud.updateData(r.key + "s2", r.value);
						break;
					case "r":
						rc.setread(crud.readData(r.key +"s2"));	
						break;
					}
					break;
				} else {
					System.out.println("not connected!");
				}
			}
			// System.exit(0);

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public boolean checkRequest() {
		return false;
	}

}