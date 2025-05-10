package org.example.util;

public class Sub extends Base {
    static {
        System.out.printf("%s - %s - %s\n", "sub", "static", "block");
    }
    {
        System.out.printf("%s - %s - %s\n", "sub", "instance", "block");
    }

    public Sub() {
        System.out.printf("%s - %s\n", "sub", "constructor");
    }

    //    @PostConstruct
    public void init() {
        System.out.printf("%s - %s\n", "sub", "PostConstruct");
    }

    @Override
    public void hello() {
        // super.hello();
        System.out.printf("%s - %s\n", "sub", "method");
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.hello();
    }
}
