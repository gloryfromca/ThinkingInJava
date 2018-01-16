package chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

interface Null{}
interface Operation{
	public void description() ;
	public void operation() ;
}
interface Robot{
	void name();
	List<Operation> operations();
	
}
class CleanRobot implements Robot{

	@Override
	public void name() {
		System.out.println("CleanRobot");
	}

	@Override
	public List<Operation> operations() {
		return Arrays.asList(
				new Operation() {

					@Override
					public void description() {
						System.out.println( "can clean bed");
					}

					@Override
					public void operation() {
						System.out.println("cleaning bed...");
					}
					
				}
				);
	}
	
}
class NullRobotProxy implements Robot, Null{
	private String nullName;
	
	public NullRobotProxy(String name) {
		this.nullName = name + " NullRobot";
	}
	@Override
	public void name() {
		System.out.println(nullName);
	}

	@Override
	public List<Operation> operations() {
		return Arrays.asList(
			new Operation() {				
				@Override
				public void operation() {
					System.out.println("doNothing");
				}
				
				@Override
				public void description() {
					System.out.println("Null");
				}
			
		}) ;
	}
}

class NullObjectProxyHandler implements InvocationHandler{
	
	private Robot Proxied;
	public NullObjectProxyHandler(Class<? extends Robot> type) {
		Proxied = new NullRobotProxy(type.getSimpleName());
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(Proxied, args);
	}
	
}

public class DynamicProxyNullObject {

	public static void main(String[] args) {
		Robot cleanNullRobot = (Robot)Proxy.newProxyInstance(
				DynamicProxy.class.getClassLoader(), 
				new Class[]{Null.class, Robot.class},
				new NullObjectProxyHandler(CleanRobot.class)
				);
		cleanNullRobot.name();
		for (Operation op: cleanNullRobot.operations()) {
			op.description();
			op.operation();
		}
		
		
	}

}
