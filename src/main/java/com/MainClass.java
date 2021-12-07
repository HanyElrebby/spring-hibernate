package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);
        System.out.println(employeeDao.get(1));
    }
}
