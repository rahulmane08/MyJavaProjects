package proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IntAdderServiceImpl extends UnicastRemoteObject implements Adder<Integer> {

	protected IntAdderServiceImpl() throws RemoteException {
		super();		
	}
	private static final long serialVersionUID = 1L;
	@Override
	public Integer add(Integer arg1, Integer arg2) {
		return arg1+arg2;
	}

}
