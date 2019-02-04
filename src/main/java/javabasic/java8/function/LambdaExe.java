package javabasic.java8.function;

public class LambdaExe {

    /**
     * inline
     */
    public void inLineLambda() {
        // runnable
        Runnable runfun = () -> System.out.println("lambda");
        lambdaAsParameter(runfun);
        lambdaAsParameter(() -> System.out.println("lambda param"));
    }

    public void lambdaAsParameter(Runnable runPara) {
        // do nothing
    }

    /**
     * lambda functional interface
     */
    interface Checkable<TestObj> {
        boolean check(TestObj t);
    }

    Checkable<TestObj> check = (t) -> t.checkLambda();
}
