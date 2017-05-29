package proxy;

import java.rmi.Remote;

public interface Adder<T> extends Remote {
	T add(T arg1, T arg2);
}
