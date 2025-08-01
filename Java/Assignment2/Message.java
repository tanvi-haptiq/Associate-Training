package chatapp;

import java.time.LocalDateTime; // Use LocalDateTime for more precise timestamps
import java.util.Objects; // Import Objects for utility methods

public class Message<T> implements Comparable<Message<T>> {
    private int messageId;
    private T content; // Renamed 'message' to 'content' for clarity
    private String type;
    private LocalDateTime timestamp; // Changed to LocalDateTime

    public Message(int messageId, T content, String type) {
        this.messageId = messageId;
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now(); // Set timestamp upon creation
    }

    public int getMessageId() {
        return messageId;
    }

    public T getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Message<T> other) {
        // Sort messages primarily by timestamp, then by messageId if timestamps are the
        // same
        int timestampComparison = this.timestamp.compareTo(other.timestamp);
        if (timestampComparison != 0) {
            return timestampComparison;
        }
        return Integer.compare(this.messageId, other.messageId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Message<?> message = (Message<?>) obj;
        // Messages are equal if their messageId and timestamp are the same
        return messageId == message.messageId && Objects.equals(timestamp, message.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, timestamp);
    }

    @Override
    public String toString() {
        return "Message [id=" + messageId + ", type=" + type + ", content=" + content + ", timestamp=" + timestamp
                + "]";
    }
}