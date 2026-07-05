package com.library;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class LibraryManagementApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("--- Testing Annotation-Based Configuration ---");
        BookService annotationService = (BookService) context.getBean("bookService");
        List<String> books = annotationService.getAllBooks();
        System.out.println("Books in repository: " + books);
        annotationService.addBook("Design Patterns Elements of Reusable Object-Oriented Software");
        System.out.println("Books after addition: " + annotationService.getAllBooks());

        System.out.println("\n--- Testing XML-Based Constructor Injection ---");
        BookService constructorService = (BookService) context.getBean("xmlBookServiceConstructor");
        System.out.println("Books via constructor injection service: " + constructorService.getAllBooks());

        System.out.println("\n--- Testing XML-Based Setter Injection ---");
        BookService setterService = (BookService) context.getBean("xmlBookServiceSetter");
        System.out.println("Books via setter injection service: " + setterService.getAllBooks());

        context.close();
    }
}
