package com.tavisca.training.registry;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Registry {
    public static void main(String[] args) throws IOException {

        Map<String, Socket> socketMapper = new HashMap<>();
        List<String> serverNames =  new ArrayList<>();

        
        new Thread(() -> {
            ServerSocket serverRegistry = null;
            try {
                serverRegistry = new ServerSocket(8083);
                while(true){
                    Socket socket = serverRegistry.accept();
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    String serverName = dis.readUTF();
                    socketMapper.put(serverName.trim(), socket);
                    serverNames.add(serverName.trim());

                    System.out.println("Server Added with name " + serverName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        ServerSocket clientRegistry = new ServerSocket(8082);

        while(true){
            Socket socket = clientRegistry.accept();
            DataOutputStream clientDos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream clientDis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            clientDos.writeUTF(serverNames.toString());
            clientDos.flush();

            String inputFromClient = clientDis.readUTF();
            String[] inputs = inputFromClient.split("[$]");
            System.out.println(inputFromClient);
            Socket mySocket = socketMapper.get(serverNames.get(Integer.parseInt(inputs[1])));

            DataOutputStream disMyServer = new DataOutputStream(new BufferedOutputStream(mySocket.getOutputStream()));
            disMyServer.writeUTF(inputs[0]+ "$" + inputs[2]);
            disMyServer.flush();
        }

    }
}
