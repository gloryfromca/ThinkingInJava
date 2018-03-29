package io;

import java.util.prefs.Preferences;

public class PreferencesDemo {
	public static void main(String[] args) {
		Preferences prefs = Preferences.userNodeForPackage(Preferences.class);
		prefs.putInt("zhanghui", 0);
		int count = prefs.getInt("Count", 0);
		count++;
		prefs.putInt("Count", count);
		System.out.println(prefs.getInt("Count", 0));

	}
}
