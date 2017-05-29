package proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		IntAdderServiceImpl serviceObj = (IntAdderServiceImpl) UnicastRemoteObject.exportObject(new IntAdderServiceImpl(), 7070);
		Naming.rebind("IntAdderService", serviceObj);
	}
}
