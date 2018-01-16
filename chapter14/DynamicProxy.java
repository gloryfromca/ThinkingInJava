package chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface DoSomething {
	void doSomething();

	int returnInt();

	void setName(String name);
}

class Interesting implements DoSomething {

	private String name;

	@Override
	public void doSomething() {
		System.out.println("something has been done!");
	}

	@Override
	public int returnInt() {
		System.out.println("return a Int!");
		return 0;
	}

	@Override
	public void setName(String name) {
		System.out.println("set name!");
		this.name = name;
	}

}

class DynamicProxyHandler implements InvocationHandler {

	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().equals("doSomething")) {
			System.out.println("doSomething is prohabited!");
			return null;
		}
		return method.invoke(proxied, args);
	}

}

public class DynamicProxy {
	public static void simpleConsumer(DoSomething ds) {
		ds.doSomething();
		ds.setName("proxied");
		ds.returnInt();
	}

	public static void main(String[] args) {
		DoSomething proxy = (DoSomething) Proxy.newProxyInstance(DoSomething.class.getClassLoader(),
				new Class[] { DoSomething.class }, new DynamicProxyHandler(new Interesting()));

		// all methods that you can invoke is from interfaces' Class[].
		proxy.doSomething();
		proxy.returnInt();
		proxy.setName("proxied");

	}

}
