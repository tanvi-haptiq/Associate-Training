package chatapp;

import java.util.Objects;

public class User implements Comparable<User> {
    private int userId;
    private String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.userName;
    }

    @Override
    public int compareTo(User o) {

        return this.userName.compareToIgnoreCase(o.userName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + "]";
    }
}