import java.io.Serializable;


public class Response implements Serializable{
	String sender;
	String receiver;
	Boolean responded;

	public Response( String sender, String receiver,
			boolean r) {
		this. sender=sender;
		this. receiver=receiver;
		this. responded=r;

	}
}
