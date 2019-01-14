package javabasic.reflection;

import baseObj.Person;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

public class reflection
{

    public static void main(String[] args)
    {
    }

    public static void reflectionNewInstance()
    {
        Class<?> demo = null;
        try
        {
            demo = Class.forName("baseObj.Person");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Person per = null;
        try
        {
            per = (Person) demo.newInstance();
        } catch (InstantiationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        per.setName("Rollen");
        per.setAge(20);
        System.out.println(per);
    }

    public static void reflectionConstructor()
    {
        Class<?> demo = null;
        try
        {
            demo = Class.forName("Reflect.Person");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        Person per1 = null;
        Person per2 = null;
        Person per3 = null;
        Person per4 = null;
        // 取得全部的构造函数
        Constructor<?> cons[] = demo.getConstructors();
        try
        {
            per1 = (Person) cons[0].newInstance();
            per2 = (Person) cons[1].newInstance("Rollen");
            per3 = (Person) cons[2].newInstance(20);
            per4 = (Person) cons[3].newInstance("Rollen", 20);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);
    }

    public static void reflectionInterface()
    {
        Class<?> demo = null;
        try
        {
            demo = Class.forName("baseObj.Person");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // 保存所有的接口
        Class<?> intes[] = demo.getInterfaces();
        for (int i = 0; i < intes.length; i++)
        {
            System.out.println("实现的接口   " + intes[i].getName());
        }
    }

    public static void createArray()
    {
        Object array = Array.newInstance(String.class, 10); // 等价于 new
                                                            // String[10]
        Array.set(array, 0, "Hello"); // 等价于array[0] = "Hello"
        Array.set(array, 1, "World"); // 等价于array[1] = "World"
        System.out.println(Array.get(array, 0)); // 等价于array[0]
    }

    public static void javaField() throws NoSuchFieldException, SecurityException
    {
        Field field = Person.class.getDeclaredField("myList"); // myList的类型是List
        Type type = field.getGenericType();
        if (type instanceof ParameterizedType)
        {
            ParameterizedType paramType = (ParameterizedType) type;
            Type[] actualTypes = paramType.getActualTypeArguments();
            for (Type aType : actualTypes)
            {
                if (aType instanceof Class)
                {
                    Class clz = (Class) aType;
                    System.out.println(clz.getName()); // 输出java.lang.String
                }
            }
        }
    }

    public List getList(final List list)
    {
        return (List) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]
        { List.class }, new InvocationHandler()
        {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                if ("add".equals(method.getName()))
                {
                    throw new UnsupportedOperationException();
                } else
                {
                    return method.invoke(list, args);
                }
            }
        });
    }
}