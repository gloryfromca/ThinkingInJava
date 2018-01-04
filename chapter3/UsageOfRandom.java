//:operators/UsageOfRandom.java
package chapter3;

import java.util.*;

/**
 * Usage of Random
 * 
 * @author ZhangHui
 * @version 1.0
 */
public class UsageOfRandom {

	public static void main(String[] args) {
		Random r = new Random(47L);
		Integer i = r.nextInt();
		Float f = r.nextFloat();
		System.out.println("Integer:" + i);
		System.out.println("Float:" + f);

	}

}/*
	 * Output: 
	 * Integer:-1172028779 
	 * Float:0.39982635
	 */// :~
