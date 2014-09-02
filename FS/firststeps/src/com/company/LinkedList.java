package com.company;

public class LinkedList<T> {

    private class Node {
        public T data;
        public Node next = null;
        public Node prev = null;
        
        public Node(T value) {
            this.data = value;
        }
    }

    private static class NodeNotFoundException extends RuntimeException {}

    private Node head;

    private Node findNode(T value) throws NodeNotFoundException {
        Node node = head;
        while (node != null) {
            if (node.data == null && value == null || node.data != null && node.data.equals(value)) {
                return node;
            }
            node = node.next;
        }
        throw new NodeNotFoundException();
    }
    
    public void AddAtBegin(T value) {
        Node newNode = new Node(value);
        newNode.next = head;
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        head = newNode;
    }

    public void AddAtEnd(T value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = newNode;
            newNode.prev = lastNode;
        }
    }

    public void AddBefore(T inseringValue, T existingValue) throws NodeNotFoundException {
        Node node = findNode(existingValue);
        
    }

    public void AddAfter(T inseringValue, T existingValue) {
        Node node = findNode(existingValue);
    }

}
