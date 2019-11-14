package com.tavisca.training.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RMIImplementation implements RMI {

    @Override
    public String process(Item item) throws IOException {


        //Socket Code
        //Socket for registry address
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket(InetAddress.getByName("localhost"),8082);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String array = dis.readUTF();
        String[] serverNames = array.substring(1,array.length()-1).split(",");
        System.out.println("(Testing)"  + Arrays.stream(serverNames).collect(Collectors.joining(",")));

        System.out.println("Which server you want?");
        for(int i =0; i < serverNames.length; i++){
            System.out.println((i+1) + " " + serverNames[i]);
        }

        int index = sc.nextInt();


        // Sending data to registry
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        dos.writeUTF(item.toString()+ "$" + (index-1) + "$process" );
        dos.flush();
        //Serialization of Item object


        return null;
    }

    @Override
    public String delete(Item item) throws IOException {

        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket(InetAddress.getByName("localhost"),8082);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        String array = dis.readUTF();
        String[] serverNames = array.substring(1,array.length()-1).split(",");
        System.out.println("(Testing)"  + Arrays.stream(serverNames).collect(Collectors.joining(",")));

        System.out.println("Which server you want?");
        for(int i =0; i < serverNames.length; i++){
            System.out.println((i+1) + " " + serverNames[i]);
        }

        int index = sc.nextInt();


        // Sending data to registry
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        dos.writeUTF(item.toString()+ "$" + (index-1) + "$delete"  );
        dos.flush();
        //Serialization of Item object


        return null;
    }
}
