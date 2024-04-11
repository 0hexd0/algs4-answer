class Node<T> {
    T data;
    Node<T> next;

    // Constructor to initialize the node
    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T> {
    Node<T> head;

    // Method to insert a new node at the end of the linked list
    public void append(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Method to display the linked list
    public void display() {
        Node<T> current = head;

        if (current == null) {
            System.out.println("The linked list is empty.");
            return;
        }

        System.out.print("Linked List: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> integerList = new LinkedList<>();
        integerList.append(1);
        integerList.append(2);
        integerList.append(3);
        integerList.display();

        LinkedList<String> stringList = new LinkedList<>();
        stringList.append("Hello");
        stringList.append("World");
        stringList.display();
    }
}
