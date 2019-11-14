package com.tavisca.training.servers;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ServerA {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Socket socket = new Socket(InetAddress.getByName("localhost"), 8083);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        dos.writeUTF("A");
        dos.flush();
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        while(true){
//            System.out.println("Line 1");
            String input = dis.readUTF();
//            System.out.println("Line 2");
            String[] inputs = input.split("[$]");

            Method[] methods = Class.forName("com.tavisca.training.servers.RMI").getDeclaredMethods();

            Method myMethod = Arrays.stream(methods).filter(method -> method.getName().equals(inputs[1])).collect(Collectors.toList()).get(0);
            String[] values = inputs[0].split(",");
            RMIImplementation rmi = new RMIImplementation();
            System.out.println(myMethod.getName());
            System.out.println("It is working");
            myMethod.invoke(rmi,new Item(Integer.parseInt(values[0]), values[1] ));
        }
    }
}
