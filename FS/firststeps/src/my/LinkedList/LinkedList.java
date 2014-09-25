package my.LinkedList;

import org.slf4j.*;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedList.class);

    private Node<T> head;

    private Node<T> findNode(T value) {
        Node<T> node = head;
        while (node != null) {
            if (node.getData() == null && value == null || node.getData() != null && node.getData().equals(value)) {
                return node;
            }
            node = node.getNext();
        }
        throw new NodeNotFoundException("List doesn't contain \"" + value.toString() + "\" value.");
    }

    public void insertAtBegin(T value) {
        Node<T> insertingNode = new Node<T>(value);
        if (head == null) {
            head = insertingNode;
        } else {
            head.insertBefore(insertingNode);
            head = insertingNode;
        }
    }

    public void insertAtEnd(T value) {
        Node<T> insertingNode = new Node<T>(value);
        if (head == null) {
            head = insertingNode;
        } else {
            Node<T> lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.insertAfter(insertingNode);
        }
    }

    public void insertBefore(T insertingValue, T existingValue) {
        Node<T> existingNode = findNode(existingValue);
        Node<T> insertingNode = new Node<T>(insertingValue);
        existingNode.insertBefore(insertingNode);
        if (head == existingNode) {
            head = insertingNode;
        }
    }

    public void insertAfter(T insertingValue, T existingValue) {
        Node<T> existingNode = findNode(existingValue);
        Node<T> insertingNode = new Node<T>(insertingValue);
        existingNode.insertAfter(insertingNode);
    }

    public void delete(T deletingValue) {
        Node<T> existingNode = findNode(deletingValue);
        existingNode.delete();
        if (head == existingNode) {
            head = existingNode.getNext();
        }
    }

    public void printYourself() {
        if (head != null) {
            Node node = head;
            while (node != null) {
                String outStr = (node.getData() != null ? node.getData().toString() : "NULL") +
                        "[" + (node.getPrev() != null ? (node.getPrev().getData() != null ? node.getPrev().getData().toString() : "NULL") : "null") +
                        ", " + (node.getNext() != null ? (node.getNext().getData() != null ? node.getNext().getData().toString() : "NULL") : "null") + "]" +
                        (node.getNext() != null ? " -> " : ".");
                LOGGER.info(outStr);
                node = node.getNext();
            }
            LOGGER.info("");
        } else {
            LOGGER.info("List is empty.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDefaultIterator<T>(head);
    }

}
