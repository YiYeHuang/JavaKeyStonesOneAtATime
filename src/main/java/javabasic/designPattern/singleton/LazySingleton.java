package javabasic.designPattern.singleton;

public class LazySingleton
{
    private static LazySingleton instance = null;
    private LazySingleton() {
        System.out.print("Singleton is created");
    }

    public static synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
