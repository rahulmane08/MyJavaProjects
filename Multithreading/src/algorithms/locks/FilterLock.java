package algorithms.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class FilterLock implements Lock {

	 /* Due to Java Memory Model, int[] not used for level and victim variables.
    Java programming language does not guarantee linearizability, or even sequential consistency,
    when reading or writing fields of shared objects
    [The Art of Multiprocessor Programming. Maurice Herlihy, Nir Shavit, 2008, pp.61.]
    */
   private AtomicInteger[] level;
   private AtomicInteger[] victim;

   private int n;

   /**
    * Constructor for Filter lock
    *
    * @param n thread count
    */
   public FilterLock(int n) {
       this.n = n;
       level = new AtomicInteger[n];
       victim = new AtomicInteger[n];
       for (int i = 0; i < n; i++) {
           level[i] = new AtomicInteger();
           victim[i] = new AtomicInteger();
       }
   }

   /**
    * Acquires the lock.
    */
   @Override
   public void lock() {
       int me = Integer.parseInt(Thread.currentThread().getName());
       for (int i = 1; i < n; i++) {
           level[me].set(i);
           victim[i].set(me);
           for (int k = 0; k < n; k++) {
               while ((k != me) && (level[k].get() >= i && victim[i].get() == me)) {
                  System.out.println("thread"+me+" is waiting");
               }
           }
       }
       System.out.println("thread"+me+" gets the lock");
   }

   /**
    * Releases the lock.
    */
   @Override
   public void unlock() {
       int me = Integer.parseInt(Thread.currentThread().getName());
       level[me].set(0);
       System.out.println("thread"+me+" releases the lock");
   }

}
