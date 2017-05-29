package misc;

class Animal
{
	private Integer age;
	private static Animal instance = new Animal();
	private Animal(){};
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static Animal getInstance()
	{
		return instance;
	}
	public static Animal getThreadLocalInstance()
	{
		Animal a = getInstance();
		ThreadLocal<Animal> t = new ThreadLocal<Animal>();
		t.set(a);
		return t.get();
	}
}

class AgeTask implements Runnable
{
	private int ageToSet;
	private Animal animal;
	
	AgeTask(int ageToSet) {
		super();
		this.ageToSet = ageToSet;
		animal = Animal.getThreadLocalInstance();
		animal.setAge(ageToSet);
	}

	
	public int getAgeToSet() {
		return ageToSet;
	}


	public void setAgeToSet(int ageToSet) {
		this.ageToSet = ageToSet;
		animal.setAge(ageToSet);
	}


	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName()+" : "+animal.getAge());
	}
	
}
public class ThreadLocalTest {
	public static void main(String[] args) {
		AgeTask task = new AgeTask(10);
		new Thread(task).start();
		task.setAgeToSet(33);
		new Thread(task).start();
	}
}
