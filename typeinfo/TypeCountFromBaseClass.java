package typeinfo;

import java.util.HashMap;

class base {
}

class extend1 extends base {
}

class extend2 extends base {
}

class extend3 extends extend1 {
}

public class TypeCountFromBaseClass {
	private Class<?> baseclass;
	private HashMap<Class<?>, Integer> typesMap = new HashMap<Class<?>, Integer>();

	public TypeCountFromBaseClass(Class<?> baseclass) {
		this.baseclass = baseclass;
	}

	public void count(Class<?> type) {
		if (!baseclass.isAssignableFrom(type)) {
			System.out.println(type);
			System.out.println(baseclass);
			System.out.println(type + " will not be counted in!");

		} else {
			countAllTypes(type);
		}
	}
	
	private void countAllTypes(Class<?> type) {
		add(type);
		Class<?> superClass = type.getSuperclass();
		if ((superClass != null) && (baseclass.isAssignableFrom(superClass)))
			countAllTypes(superClass);
	}

	private void add(Class<?> className) {
		Integer numClassName = typesMap.get(className);
		if (numClassName == null)
			typesMap.put(className, 1);
		else {
			typesMap.put(className, numClassName + 1);
		}

	}

	public void showMap() {
		System.out.println(typesMap);
	}

	public static void main(String[] args) {
		TypeCountFromBaseClass tc = new TypeCountFromBaseClass(base.class);
		tc.count(extend3.class);
		tc.count(extend2.class);
		tc.count(extend1.class);
		tc.showMap();
		
	}

}
