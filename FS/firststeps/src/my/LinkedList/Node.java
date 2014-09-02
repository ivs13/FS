package my.LinkedList;

class Node<T> {
    T data;
    Node next = null;
    Node prev = null;

    Node(T value) {
        this.data = value;
    }

    void InsertBefore(Node insertingNode) {
        insertingNode.prev = this.prev;
        insertingNode.next = this;
        if (insertingNode.prev != null) {
            insertingNode.prev.next = insertingNode;
        }
        this.prev = insertingNode;
    }

    void InsertAfter(Node insertingNode) {
        insertingNode.prev = this;
        insertingNode.next = this.next;
        if (insertingNode.next != null) {
            insertingNode.next.prev = insertingNode;
        }
        this.next = insertingNode;
    }

    void Delete() {
        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
    }

}
