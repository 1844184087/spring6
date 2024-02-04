package com.nau.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateTest {
    private String name = "test";
    public String getName() {
        return name;
    }
}
class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        Constructor constructor = PrivateTest.class.getConstructor();
        Object o = constructor.newInstance();
        System.out.println(o);
       Field f = Class.forName("com.nau.reflection.PrivateTest").getDeclaredField("name");
       f.setAccessible(true);
       f.set(o,"123");
        System.out.println(o);
        Method method = Class.forName("com.nau.reflection.PrivateTest").getDeclaredMethod("getName");
        method.setAccessible(true);
        method.invoke(o);

    }
}
