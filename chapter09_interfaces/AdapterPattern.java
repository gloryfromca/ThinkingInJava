package chapter09_interfaces;

class Typer{
	
	public String process(String input) {
		// TODO Auto-generated method stub
		return input;
	}

}

class TyperAdapter implements Processor{
	Typer typer;
	public TyperAdapter(Typer typer) {
		this.typer = typer;
	}
	
	private String name;
	public String name(String name) {
		this.name=name;
		return name;
	}
	@Override
	public Object process(Object input) {
		
		return typer.process((String) input);
		
	}
	
}

public class AdapterPattern {

	public static void main(String[] args) {
		TyperAdapter tp = new TyperAdapter(new Typer());
		Apply.process(tp, "zhanghui");
		
	}

}
