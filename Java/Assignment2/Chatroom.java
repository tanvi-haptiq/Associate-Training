package chatapp;

import java.time.LocalDateTime;
import java.util.*;

public class Chatroom {
    // Active users stored in a TreeSet to ensure alphabetical sorting
    Set<User> activeUsers = new TreeSet<>();
    // Message history stored in a HashMap with User as key and List of Messages as value
    Map<User, List<Message<?>>> messageHistory = new HashMap<>(); // Use Message<?> for generic type

    private int roomId;
    private String roomName;
    private LocalDateTime creationTimestamp; // Renamed for clarity

    public Chatroom(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.creationTimestamp = LocalDateTime.now();
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    /**
     * Adds a user to the chat room.
     * @param user The user to join.
     */
    public void joinRoom(User user) {
        activeUsers.add(user);
        // Initialize an empty list for message history if the user is new
        messageHistory.putIfAbsent(user, new ArrayList<>());
        System.out.println(user.getUsername() + " has joined the chat room.");
    }

    /**
     * Removes a user from the chat room.
     * @param user The user to exit.
     * @return true if the user was successfully removed, false otherwise.
     */
    public boolean exitRoom(User user) {
        boolean removed = activeUsers.remove(user); // Directly use remove
        if (removed) {
            System.out.println(user.getUsername() + " has exited the chat room.");
        } else {
            System.out.println(user.getUsername() + " was not found in the active users.");
        }
        return removed;
    }

    /**
     * Sends a message from a sender.
     * @param <T> The type of the message content.
     * @param messageId The unique ID of the message.
     * @param sender The user sending the message.
     * @param content The content of the message.
     * @param type The type of the message (e.g., "text", "image").
     */
    public <T> void sendMessage(int messageId, User sender, T content, String type) {
        if (!activeUsers.contains(sender)) {
            System.out.println("Error: " + sender.getUsername() + " is not an active user and cannot send messages.");
            return;
        }

        Message<T> msg = new Message<>(messageId, content, type);
        // Add the message to the sender's history.
        // computeIfAbsent is perfect for this: it gets the list if it exists,
        // otherwise it creates a new ArrayList and then adds the message.
        messageHistory.computeIfAbsent(sender, k -> new ArrayList<>()).add(msg);
        System.out.println(sender.getUsername() + " sent: " + msg.getContent() + " (" + msg.getType() + ")");
    }

    /**
     * Retrieves and prints the currently active users.
     */
    public void printActiveUsers() {
        System.out.println("\n--- Active Users ---");
        if (activeUsers.isEmpty()) {
            System.out.println("No active users in the chat room.");
            return;
        }
        activeUsers.forEach(u -> System.out.println("- " + u.getUsername()));
        System.out.println("--------------------");
    }

    /**
     * Retrieves the message history for a specific user, sorted by timestamp.
     * @param user The user whose message history is to be retrieved.
     * @return A sorted list of messages for the user, or an empty list if no history.
     */
    public List<Message<?>> getMessageHistory(User user) {
        List<Message<?>> userMessages = messageHistory.getOrDefault(user, Collections.emptyList());

        List<Message<?>> sortedMessages = new ArrayList<>(userMessages);
        Collections.sort(sortedMessages); 
        return sortedMessages;
    }
    public void printAllMessageHistory() {
        System.out.println("\n--- Full Message History ---");
        if (messageHistory.isEmpty() || messageHistory.values().stream().allMatch(List::isEmpty)) {
            System.out.println("No messages sent in this chat room yet.");
            return;
        }

        List<User> sortedUsers = new ArrayList<>(messageHistory.keySet());
        Collections.sort(sortedUsers);

        for (User user : sortedUsers) {
            List<Message<?>> messages = getMessageHistory(user);
            if (!messages.isEmpty()) {
                System.out.println("\nMessages from " + user.getUsername() + ":");
                messages.forEach(msg -> System.out.println("  " + msg.getTimestamp() + " [" + msg.getType() + "]: " + msg.getContent()));
            }
        }
        System.out.println("----------------------------");
    }
}