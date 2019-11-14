package com.tavisca.training.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        while(true){
            System.out.println("=======================================");
            System.out.println("               Welcome                 ");
            System.out.println("=======================================");

            Item item1 = new Item(1, "A");
            Item item2 = new Item(2, "B");

            Method[] methods = Class.forName("com.tavisca.training.client.RMI").getDeclaredMethods();
            int index = 1;
            for(Method method : methods){
                System.out.println(index++ + " " + method.getName());
            }
            Scanner sc = new Scanner(System.in);
            index = sc.nextInt();

            RMIImplementation rmi = new RMIImplementation();
            methods[index-1].invoke(rmi,item1);
        }
    }
}
