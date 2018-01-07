package chapter9;

interface Processor{
	String name(String name);
	Object process(Object input);
}
class Apply{
	public static void process(Processor p, Object s){
		System.out.println("in Apply.process() ...");
		System.out.println(p.process(s));
	}
}
abstract class StringProcessor implements Processor{
	private String name;
	public String name(String name) {
		this.name = name;
		return name;
	};
	// if you want implements an interface, you must make 
	// method implemented has same name and input-type.
	// and you can return Covariant.
	public abstract String process(Object inputString) ;
	
}

class Upcase extends StringProcessor {
	
	public String process(Object inputString) {
		return ((String)inputString).toUpperCase();
	}
}
class Lowcase extends StringProcessor{

	@Override
	public String process(Object inputString) {
		return ((String)inputString).toLowerCase();
	}
	
}


public class InterfaceStrategyPattern {
	
	static String string = "ZhangHui";
	public static void main(String[] args) {
		Apply.process(new Upcase(), string);
		Apply.process(new Lowcase(), string);
		
	}

}
