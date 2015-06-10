import java.io.FileWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server1 {
	static Request r = null;
	static int count = 0;

	public static void main(String[] args) {

		try {
			/* Instantiate the object that communicate with clients */
			ServerImplementation rc = new ServerImplementation();
			/* Register the object at the rmiregistry */
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("A", rc);

			Crud_Functions crud = new Crud_Functions();
			Conf conf = new Conf();
			while (true) {
				if (rc.getRequest() != null) {
					r = rc.getRequest();

					r.conType = "quorum";

					switch (r.reqType) {
					case "c":
						replication(rc, r.conType);
						crud.putData(r.key + "s1", r.value);

						break;
					case "d":
						replication(rc, r.conType);
						crud.deleteData(r.key + "s1");
						break;
					case "u":
						replication(rc, r.conType);
						crud.updateData(r.key + "s1", r.value);
						break;
					case "r":
						rc.setread(crud.readData(r.key + "s1"));
						
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

	public static void replication(ServerImplementation rc, String conType)
			throws Exception {
		if (r.conType.equals("sync")) {
			r.sender = r.sender;
			r.sender = "rmi://localhost/A";
			r.receiver = "rmi://localhost/B";
			r.conType = "sync";
			rc.setRequest(r);
			rc.sendRequest(r);
			System.out.println("starter is :" + r.Starter);
			while (true) {
				if (rc.getResponse() != null) {
					System.out.println("Replication done!!!");
					rc.setResponse(null);
					break;
				}
				Thread.sleep(3000);
			}
		} else if (r.conType.equals("quorum")) {
			r.sender = "rmi://localhost/A";
			r.receiver = "rmi://localhost/B";
			r.conType = "quorum";
			rc.setRequest(r);
			rc.sendRequest(r);

			r.sender = "rmi://localhost/A";
			r.receiver = "rmi://localhost/C";
			r.conType = "quorum";
			try {
				rc.setRequest(r);
				rc.sendRequest(r);
			} catch (Exception e) {
				System.out.println("Server 3 Not Available. ");
			}

			while (true) {
				if (rc.getCounter() == count) {
					System.out.println("Replication done!!!");
					rc.setResponse(null);
					rc.qcounter = 0;
					break;
				}
				Thread.sleep(3000);
			}

		}
		else if (r.conType.equals("async")) {
			r.sender = "rmi://localhost/A";
			r.receiver = "rmi://localhost/B";
			r.conType = "async";
			rc.setRequest(r);
			rc.sendRequest(r);

			r.sender = "rmi://localhost/A";
			r.receiver = "rmi://localhost/C";
			r.conType = "async";
			rc.setRequest(r);
			rc.sendRequest(r);
		}

	}

}