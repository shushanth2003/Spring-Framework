package com.springframework;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beanconfig.class);
        Doctor docter1 = context.getBean(Doctor.class);
        docter1.assist();
    }
}