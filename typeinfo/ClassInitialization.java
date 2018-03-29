package typeinfo;

import java.util.Random;

class Initable{
	static int NotFinal =  0;
	static {
		System.out.println("initializing Initable... ");
	}
}

class Initable1{
	final static int isFinal =  1;
	static {
		System.out.println("initializing Initable1... ");
	}
}

class Initable2{
	final static int isFinalAndNotConstant = 
			ClassInitialization.random.nextInt();
	static {
		System.out.println("initializing Initable2... ");
	}
}

class Initable3{
	final static int isFinalAndNotConstant = 
			ClassInitialization.random.nextInt();
	static {
		System.out.println("initializing Initable3... ");
	}
}

public class ClassInitialization {
	static Random random = new Random(47);

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("//Behaviors of accessing inner field of class:");
		System.out.println(Initable.NotFinal);
		System.out.println(Initable1.isFinal);
		System.out.println(Initable2.isFinalAndNotConstant);
		
		System.out.println("//Different way for getting class' ref:");
		// `.class` will not lead to initialization of class object.
		Class initable = Initable.class;
		//System.out.println(initable.NotFinal);
		Class initable1 = Initable1.class;
		//System.out.println(initable1.isFinal);
		// `forName()` will lead to initialization of Class object. 
		Class initable3 =  Class.forName("chapter14.Initable3");	
		//System.out.println(initable3.isFinalAndNotConstant);
		
		System.out.println("//Print class:");
		// can't print Initable directly.
		//System.out.println(Initable);
		System.out.println(initable);
		//type of `initable` is `Class`. 
		System.out.println(initable.getClass());
		//type of `Class` is `Class`.
		System.out.println(initable.getClass().getClass());
		
		

	}

}
