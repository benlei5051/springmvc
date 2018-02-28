package org.java.util;

/**
 * Created by jh on 2017/8/3.
 */
public class TestAb {
    public static void main(String[] args){
        ParameAbstract parame=new ParameAbstract() {
            @Override
            protected void clear() {
               System.out.println("我是子类的clear方法");
            }
        };
        parame.clear();

    }
}
