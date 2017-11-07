package lambdas;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lambdas.LambdaTest.Employee;


public class FunctionTest 
{
	public static void main(String[] args) {
		Function<String, Integer> f = Integer::parseInt; //method reference to a static method
		System.out.println(f.apply("20")); // parseInt
		
		Supplier<java.util.List<String>> listCreator = ArrayList::new;
		System.out.println(listCreator.get()); // []
		
		Supplier<Employee> employeeCreator = Employee::new;
		System.out.println(employeeCreator.get()); //calls the default constructor : Employee [name=DEFAULT, id=DEFAULT_ID, age=0, address=null, salary=0.0]
		
		BiFunction<String, String, Employee> employeeOverloaded = Employee::new;
		System.out.println(employeeOverloaded.apply("1","Rahul"));
	}
}
