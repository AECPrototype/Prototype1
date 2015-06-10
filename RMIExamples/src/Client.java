import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Client extends UnicastRemoteObject {

	// Defining Graphicaal Parts

	private ServerInterface mycs;
	private String message;
	private String messagekey;
	private String time;
	private JTextArea usermessage;
	private JButton messagesendbtn;
	private JTextArea messages;
	private JTextArea userlist;

	/*
	 * Constructor of RChatClient to initialize the most important values.
	 */
	public Client(ServerInterface cs, Request r)
			throws Exception {
		mycs = cs;
		mycs.setRequest(r);
		// mycs.setAcknowledge(n);
	}

	public static void main(String[] args) {

		try {
			String res = "";
			String servername = "";
			String key="";
			String operation="";
			System.out.println("Plese enter your choise \n"
					+ "c  for storing data\n"
					+ "u  for updating data\n"
					+ "d  for deleting data\n"
					+ "r  for reading data");
			// Checking for the entered username
			operation = JOptionPane.showInputDialog("What you want to do:");
			key = JOptionPane.showInputDialog("Please Enter key:");
			switch(operation){
			case "c":
				res = JOptionPane.showInputDialog("Please Enter value:");
				break;
			case "u":
				res = JOptionPane.showInputDialog("Please Enter value:");
				break;
				
			}
			
			servername = JOptionPane
					.showInputDialog("Please Enter Node Address:", "localhost/A");
			String url = "rmi://" + servername;
			/* connect to remote object */
			// Creating an object from RemoteChatServer and then starting the
			// thread for the user
			ServerInterface cs = (ServerInterface) Naming.lookup(url);
			Request r=new Request (url,"client", url,operation, key,res, null);
			new Client (cs, r);
			Thread.sleep(1000);
			if(operation.equals("r"))
				System.out.println("Requested data :\n " + cs.getread());			
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
