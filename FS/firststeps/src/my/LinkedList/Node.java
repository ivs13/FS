package my.LinkedList;

class Node<T> {
    private T data;
    private Node<T> next = null;
    private Node<T> prev = null;

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    Node(T value) {
        this.data = value;
    }

    void insertBefore(Node<T> insertingNode) {
        insertingNode.prev = this.prev;
        insertingNode.next = this;
        if (insertingNode.prev != null) {
            insertingNode.prev.next = insertingNode;
        }
        this.prev = insertingNode;
    }

    void insertAfter(Node<T> insertingNode) {
        insertingNode.prev = this;
        insertingNode.next = this.next;
        if (insertingNode.next != null) {
            insertingNode.next.prev = insertingNode;
        }
        this.next = insertingNode;
    }

    void delete() {
        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
    }

}
