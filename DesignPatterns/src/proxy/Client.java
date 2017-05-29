package proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
		IntAdderServiceImpl serviceObj = (IntAdderServiceImpl) Naming.lookup("rmi://localhost:7070/IntAdderService");
		System.out.println("add = "+serviceObj.add(1, 2));
	}
}
