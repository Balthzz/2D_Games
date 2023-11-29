package Mypacman.Multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Stack;

public class UDPServer {

    private final DatagramSocket serverSocket;
    private final byte[] receiveData;
    private Stack<DatagramPacket> requestStack = new Stack<>();
    private LinkedList<DatagramPacket> clients = new LinkedList<>();

    public UDPServer() throws SocketException {
        serverSocket = new DatagramSocket(5001);
        receiveData = new byte[1024];
    }

    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer();
            server.run();
        } catch (IOException e) {
            // Log the exception instead of printing the stack trace directly
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println("Waiting for clients...");
            waitForClients();

            // Start a new thread for continuous communication with clients
            new Thread(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        receive();
                        send();
                    } catch (IOException e) {
                        // Log the exception instead of throwing a RuntimeException
                        e.printStackTrace();
                    }
                }
            }).start();

            // Keep the main thread alive or perform other tasks if needed
            Thread.sleep(Long.MAX_VALUE);
        } catch (IOException | InterruptedException e) {
            // Log the exception instead of printing the stack trace directly
            e.printStackTrace();
        } finally {
            closeSocket();
        }
    }

    // Method to wait for two clients to connect
    private void waitForClients() throws IOException {
        while (clients.size() < 2) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            if (clients.isEmpty() || clients.getLast().getPort() != receivePacket.getPort()) {
                clients.add(receivePacket);
            }
        }
        sendResponse();
    }
    private void sendResponse() throws IOException {
        // Sending a response to a specific client (for example, Client 1)
        if (clients.size() >= 2) {
            DatagramPacket client1 = clients.getFirst();
            DatagramPacket client2 = clients.getLast();

            String responseMessage = "Go!!!";
            byte[] sendData = responseMessage.getBytes();

            DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length,
                    client1.getAddress(), client1.getPort());
            serverSocket.send(sendPacket1);
            DatagramPacket sendPacket2 = new DatagramPacket(sendData, sendData.length,
                    client2.getAddress(), client2.getPort());
            serverSocket.send(sendPacket2);

        }
    }

    // Method to receive messages from clients
    private void receive() throws IOException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        requestStack.push(receivePacket);
    }

    // Method to send responses back to clients
    private void send() throws IOException {
        if (requestStack.size() >= 2) {
            DatagramPacket clientPacket1 = requestStack.pop();
            DatagramPacket clientPacket2= requestStack.pop();

            String sendMsg1 = bytesToString(clientPacket1);
            byte[] sendData1 = sendMsg1.getBytes();
            DatagramPacket sendPacket1 = new DatagramPacket(sendData1, sendData1.length,
                    clientPacket2.getAddress(), clientPacket2.getPort());
            serverSocket.send(sendPacket1);

            String sendMsg2 = bytesToString(clientPacket2);
            byte[] sendData2 = sendMsg2.getBytes();
            DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length,
                    clientPacket1.getAddress(), clientPacket1.getPort());
            serverSocket.send(sendPacket2);

        }
    }

    // Method to convert received bytes to a String
    private static String bytesToString(DatagramPacket receivePacket) {
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }

    // Method to close the server socket
    private void closeSocket() {
        if (!serverSocket.isClosed()) {
            serverSocket.close();
            System.out.println("Server socket closed");
        }
    }
}



