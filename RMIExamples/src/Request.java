import java.io.Serializable;

public class Request implements Serializable{
	String conType;
	String sender;
	String receiver;
	String reqType;
	String key;
	String value;
	String Starter;

	public Request(String starter, String sender, String receiver,
			String reqType, String key, String value,String conType) {
		this.Starter=starter;
		this. conType=conType;
		this. sender=sender;
		this. receiver=receiver;
		this. reqType=reqType;
		this. key=key;
		this. value=value;

	}
}
