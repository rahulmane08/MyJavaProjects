package lambdas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaTest {
    static class Employee implements Comparable<Employee>
    {
        private final String name;
        private final String id;
        private int age;
        private String address;
        private double salary;

        
        public Employee() {
			super();
			this.name="NO_NAME";
			this.id="DEFAULT_ID";
		}
		public Employee(String id,String name) {
            super();
            this.id = id;
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        @Override
        public int compareTo(Employee o) {

            return -1*this.id.compareToIgnoreCase(o.id);
        }


        @Override
        public String toString() {
            return "Employee [name=" + name + ", id=" + id + ", age=" + age + ", address=" + address + ", salary="
                    + salary + "]";
        }
        public double getSalary() {
            return salary;
        }
        public void setSalary(double salary) {
            this.salary = salary;
        }
        public boolean highlyPaid()
        {
            return salary>10000000;
        }
    }

    static class NameComparator implements Comparator<Employee>
    {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    public static void main(String[] args)
    {
        //anonymous IC
        new Thread(()->System.out.println("sample lambda thread")).start();

        //method references
        List<String> list = Arrays.asList(new String[] {"d","c","b","a"});
        Collections.sort(list, String::compareToIgnoreCase);
        System.out.println(list);

        //method references for user defined objects
        List<Employee> employees = new ArrayList<>();
        for(int i=10;i>0;i--)
        {
            Employee e = new Employee(String.valueOf(i),"Rahul"+i);
            e.setSalary(200000*i);
            employees.add(e);
        }

        Collections.sort(employees,Employee::compareTo);
        System.out.println(employees);
        Collections.sort(employees,(o1,o2)->o1.name.compareTo(o2.name));
        System.out.println("After name asc sort : \n"+employees);

        //list interface itself now has a sort method
        employees.sort((o1,o2)->o2.name.compareTo(o1.name));
        System.out.println("After name desc sort : \n"+employees);

        //java 8 introduces the comparing method in the comparators that accept lambda functions
        employees.sort(Comparator.comparing(Employee::getSalary));
        System.out.println("After salary asc sort : \n"+employees);

        //two forms of lambdra expressions.
        //(parameters) -> expression
        //(parameters) -> { statements;}
        // in the below example hte listFiles method takes a FileFilter object which is a functional interface
        //containing only one method accept(File f)
        //hence we can use a lambda expression by simply passing a f1-> //filter criteria
        //f1 here points to each file in the array
        File f = new File("/Users/rmane");
        File[] childFiles = f.listFiles(f1->f1.isFile());
        System.out.println("All child files in "+f.getAbsolutePath());
        Arrays.asList(childFiles).forEach(file->System.out.println(file.getName()));

        File[] childDirectories = f.listFiles(d->d.isDirectory());
        System.out.println("\nAll child directories in "+f.getAbsolutePath());
        Arrays.asList(childDirectories).forEach(file->System.out.println(file.getName()));


        //we can also use method references as the accept() takes a file object and hence we can pass a
        //method in the file class that returns a boolean
        File[] hiddenDirectories = f.listFiles(File::isHidden);
        System.out.println("\nAll hidden ßchild directories in "+f.getAbsolutePath());
        Arrays.asList(hiddenDirectories).forEach(file->System.out.println(file.getName()));

        //1. method reference to a static method
        /**This is a functional interface whose functional method is apply(Object).
         Type Parameters:
         <T> the type of the input to the function
         <R> the type of the result of the function
         **/
        Function<String, Integer> intParseFunction = Integer::parseInt;
        System.out.println(intParseFunction.apply("300"));

        //2. method references for instance methods of an existing object
        Consumer<Object> println = System.out::println;


        //3. method references on constructors
        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> l1 = listSupplier.get();
        l1.add("a");
        List<String> l2 = listSupplier.get();
        l2.add("b");
        System.out.println(l1+","+l2);

    }
}
