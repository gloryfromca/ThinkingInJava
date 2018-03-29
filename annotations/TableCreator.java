package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

	private static String getConstraints(Constraints css) {
		String constraints = "";
		if (!css.allowNull()) {
			constraints += " NOT NULL";
		}
		if (css.primaryKey()) {
			constraints += " PRIMARY KEY";
		}
		if (css.unique()) {
			constraints += " UNIQUE";
		}
		return constraints;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> cl = Class.forName("chapter20.Member");
		DBTable dbTable = cl.getAnnotation(DBTable.class);
		String tableName = dbTable.name();
		List<String> columnDefs = new ArrayList<String>();
		System.out.println("table name is " + tableName);
		for (Field field : cl.getDeclaredFields()) {
			System.out.println("declaredField is " + field);
			String columnname = null;
			Annotation[] anns = field.getAnnotations();
			if (anns.length < 1)
				continue;
			System.out.println("anns[0] is " + anns[0]);
			if (anns[0] instanceof SQLInteger) {
				SQLInteger sInt = (SQLInteger) anns[0];
				if (sInt.name().length() < 1)
					columnname = field.getName().toUpperCase();
				else
					columnname = sInt.name();
				columnDefs.add(columnname + " INT" + getConstraints(sInt.constraints()));
			}
			if (anns[0] instanceof SQLString) {
				SQLString sInt = (SQLString) anns[0];
				if (sInt.name().length() < 1)
					columnname = field.getName().toUpperCase();
				else
					columnname = sInt.name();
				columnDefs.add(columnname + " VARCHAR(" + sInt.value() + ")" + getConstraints(sInt.constraints()));
			}
		}
		StringBuilder creatCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
		for (String columndef : columnDefs) {
			creatCommand.append("\n    " + columndef + ",");
		}
		creatCommand.append("\n)");
		System.out.println(creatCommand.toString());
	}

}
