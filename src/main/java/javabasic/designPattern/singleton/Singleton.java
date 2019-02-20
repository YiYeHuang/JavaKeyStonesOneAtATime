package javabasic.designPattern.singleton;

public class Singleton
{
    private static Singleton instance = new Singleton();
    private Singleton() {
        System.out.print("Singleton is created");
    }

    public static Singleton getInstance() {
        return instance;
    }
}
