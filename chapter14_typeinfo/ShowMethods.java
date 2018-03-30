package chapter14_typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class ShowMethods {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> type = Class.forName("chapter14.ShowMethods");
		Method[] methods = type.getMethods();
		Constructor[] ctors = type.getConstructors();
		for (Method method : methods) {
			System.out.println(method);
		}
		for (Constructor ctor : ctors) {
			System.out.println(ctor);
		}

	}

}
