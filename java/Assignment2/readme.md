
This project simulates a real-time chat room, demonstrating core functionalities like user management, message exchange, and message history storage. It uses generic data structures for diverse message types and ensures efficient, sorted organization of users and messages.

Features - 
User Management: Active users are stored in a Set.

Generic Message Handling: A custom Message<T> class supports various message types (text, image, etc.).

Message History: Messages are stored in a Map<User, List<Message>>.

Sorted Display: Users are sorted alphabetically; messages by timestamp.