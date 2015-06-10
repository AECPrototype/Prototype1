public class Conf {
	String comType;
	String nodeName;

	String starter;
	String sender1;
	String sender2;
	String receiver1;
	String receiver2;
	String conType1;
	String conType2;

	public void configuration(String starter)
	{
		switch(this.starter){
		case "A":
		 sender1="A";
		 receiver1="B";
		 conType1="sync";
		 
		 sender1="B";
		 receiver1="C";
		 conType1="async";
		 break;
		 
		case "C":
			 sender1="A";
			 receiver1="B";
			 conType1="async";
			 
			 sender1="A";
			 receiver1="C";
			 conType1="async";
			 break;
		}
 
	}

}
