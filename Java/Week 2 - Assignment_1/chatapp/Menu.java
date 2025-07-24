package chatapp;

import java.util.List;

public class Menu {
    public static void main(String[] args) {

        Chatroom room = new Chatroom(1, "Java Developers Chat");
        System.out.println("Chat Room '" + room.getRoomName() + "' created.");

        User userOne = new User(1, "Tanvi");
        User userTwo = new User(2, "Dnyanesh");
        User userThree = new User(3, "Amey");
        User userFour = new User(4, "Zoe");

        room.joinRoom(userOne);
        room.joinRoom(userTwo);
        room.joinRoom(userThree);
        room.joinRoom(userFour);

        room.printActiveUsers();

        System.out.println("\n--- Sending Messages ---");
        room.sendMessage(1, userOne, "Hello everyone! Welcome to the chat.", "text");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        } // Small delay for timestamp difference
        room.sendMessage(2, userTwo, "Hey Tanvi! How are you doing?", "text");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        room.sendMessage(3, userThree, "I'm good, thanks! Just sharing a cool image.", "text");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        room.sendMessage(4, userThree, "https://example.com/cool-image.png", "image"); // Image message
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        room.sendMessage(5, userOne, "Nice image, Amey!", "text");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        room.sendMessage(6, userFour, "Hello from Zoe! Glad to be here.", "text");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        room.sendMessage(7, userTwo, "Did anyone see the new Java release notes?", "text");

        System.out.println("\n--- Individual Message Histories (Sorted by Timestamp) ---");

        List<Message<?>> tanviHistory = room.getMessageHistory(userOne);
        System.out.println("\nTanvi's messages:");
        if (tanviHistory.isEmpty()) {
            System.out.println("No messages from Tanvi.");
        } else {
            tanviHistory.forEach(msg -> System.out
                    .println("  " + msg.getTimestamp() + " [" + msg.getType() + "]: " + msg.getContent()));
        }

        List<Message<?>> ameyHistory = room.getMessageHistory(userThree);
        System.out.println("\nAmey's messages:");
        if (ameyHistory.isEmpty()) {
            System.out.println("No messages from Amey.");
        } else {
            ameyHistory.forEach(msg -> System.out
                    .println("  " + msg.getTimestamp() + " [" + msg.getType() + "]: " + msg.getContent()));
        }

        room.printAllMessageHistory();

        System.out.println("\n--- User Exiting ---");
        room.exitRoom(userTwo);
        room.printActiveUsers();

        room.sendMessage(8, userTwo, "I'm trying to send a message after exiting!", "text");

        System.out.println("\n--- Final Message History After Exit ---");
        room.printAllMessageHistory();
    }
}