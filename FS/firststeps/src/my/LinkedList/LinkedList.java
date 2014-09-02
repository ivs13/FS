package my.LinkedList;

import com.sun.corba.se.impl.logging.ORBUtilSystemException;

public class LinkedList<T> {

    private Node head;

    private Node findNode(T value) throws NodeNotFoundException {
        Node node = head;
        while (node != null) {
            if (node.data == null && value == null || node.data != null && node.data.equals(value)) {
                return node;
            }
            node = node.next;
        }
        throw new NodeNotFoundException("List doesn't contain \"" + value.toString() + "\" value.");
    }
    
    public void InsertAtBegin(T value) {
        Node insertingNode = new Node(value);
        if (head == null) {
            head = insertingNode;
        } else {
            head.InsertBefore(insertingNode);
            head = insertingNode;
        }
    }

    public void InsertAtEnd(T value) {
        Node insertingNode = new Node(value);
        if (head == null) {
            head = insertingNode;
        } else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.InsertAfter(insertingNode);
        }
    }

    public void InsertBefore(T insertingValue, T existingValue) throws NodeNotFoundException {
        Node existingNode = findNode(existingValue);
        Node insertingNode = new Node(insertingValue);
        existingNode.InsertBefore(insertingNode);
        if (head == existingNode) {
            head = insertingNode;
        }
    }

    public void InsertAfter(T insertingValue, T existingValue) throws NodeNotFoundException {
        Node existingNode = findNode(existingValue);
        Node insertingNode = new Node(insertingValue);
        existingNode.InsertAfter(insertingNode);
    }

    public void Delete(T deletingValue) throws NodeNotFoundException {
        Node existingNode = findNode(deletingValue);
        existingNode.Delete();
        if (head == existingNode) {
            head = existingNode.next;
        }
    }

    public void PrintYourself() {
        if (head != null) {
            Node node = head;
            while (node != null) {
                String outStr = (node.data != null ? node.data.toString() : "NULL") +
                        "[" + (node.prev != null ? (node.prev.data != null ? node.prev.data.toString() : "NULL") : "null") +
                        ", " + (node.next != null ? (node.next.data != null ? node.next.data.toString() : "NULL") : "null") + "]" +
                        (node.next != null ? " -> " : ".");
                System.out.print(outStr);
                node = node.next;
            }
            System.out.println();
        } else {
            System.out.println("List is empty.");
        }
    }

}
