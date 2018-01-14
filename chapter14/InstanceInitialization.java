package chapter14;

import java.util.Random;
class R{
	
	Random innerRandom = new Random(); 
	{
		System.out.println("new Instance of R... ");
	}
}
class Instance{
	final static int i = 0;
	
	static int s = 1;
	static {
		System.out.println("after static s: "+ s);
	}
	static {
		System.out.println("before static sr: ");
	}
	static int sr = new R().innerRandom.nextInt();
	
	int n = 2;
	{
		System.out.println("n of instance: "+n);
	}

	
}
public class InstanceInitialization {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		System.out.println(Instance.i);
		System.out.println(Instance.s);
		System.out.println(Instance.class.newInstance().n);

	}

}
