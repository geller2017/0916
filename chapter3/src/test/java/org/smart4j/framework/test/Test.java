package org.smart4j.framework.test;

import java.util.Iterator;
import java.util.Set;

import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ClassUtil;

public class Test {
	public static void main(String[] args) {
		/*String packageName = "org.smart4j.framework";*/
		Set<Class<?>> classes = ClassHelper.getClassSet();
		Iterator<Class<?>> classIter = classes.iterator();
		while(classIter.hasNext()){
			System.out.println(classIter.next().getName());
		}
	}
}
