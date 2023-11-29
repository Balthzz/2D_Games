package Mypacman.Multi;

import Mypacman.Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;

public class UDPClient {

    private final DatagramSocket clientSocket;
    private final byte[] receiveData;
    private DatagramPacket receivePacket;
    private Game game;

    // Use a CountDownLatch for synchronization
    private final CountDownLatch gameInitializedLatch = new CountDownLatch(1);

    public UDPClient() throws SocketException {
        clientSocket = new DatagramSocket();
        // Start the thread to initialize the game object
        new Thread(() -> {
            game = new Game();
            // Countdown the latch to signal that the game object is initialized
            gameInitializedLatch.countDown();
        }).start();
        receiveData = new byte[1024];
    }

    public static void main(String[] args) throws IOException {
        UDPClient client = new UDPClient();
        try {
            client.gameInitializedLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.run();
    }


    public void run() {
        try {
            System.out.println("Waiting for server response...");
            if (game.isRunMulti()) {
                send("GO");
                receiveGo();
            }
            while (!clientSocket.isClosed()) {
                if (game.isRunMulti()) {
                    send(String.valueOf(game.getDirection()));
                    receive();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Move clientSocket.close() to the finally block
            if (!clientSocket.isClosed()) {
                clientSocket.close(); // Close the client socket when done
                System.out.println("Client closed");
            }
        }
    }

    private char receive() throws IOException {
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        char msg = bytesToChars(receivePacket);
        game.setDirectionMulti(msg);
        System.out.println("Client received: " + game.getDirection());
        return msg;
    }
    private boolean receiveGo() throws IOException {
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Client received: " + msg);
        if ("GO".equals(msg)) { // Use equals method for string comparison
            System.out.println("GO!!");
            return true;
        }
        return false;
    }


    private void send(String msg) throws IOException {
        System.out.println("Client sent: " + msg);
        byte[] sendData = msg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 5001);
        clientSocket.send(sendPacket);
    }

    private static char bytesToChars(DatagramPacket receivePacket) {
        String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        return clientMessage.charAt(0);
    }
}

