package my.LinkedList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {

    private Node head;

    private Node findNode(T value) {
        Node node = head;
        while (node != null) {
            if (node.getData() == null && value == null || node.getData() != null && node.getData().equals(value)) {
                return node;
            }
            node = node.getNext();
        }
        throw new NodeNotFoundException("List doesn't contain \"" + value.toString() + "\" value.");
    }

    public void insertAtBegin(T value) {
        Node insertingNode = new Node(value);
        if (head == null) {
            head = insertingNode;
        } else {
            head.insertBefore(insertingNode);
            head = insertingNode;
        }
    }

    public void insertAtEnd(T value) {
        Node insertingNode = new Node(value);
        if (head == null) {
            head = insertingNode;
        } else {
            Node lastNode = head;
            while (lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
            lastNode.insertAfter(insertingNode);
        }
    }

    public void insertBefore(T insertingValue, T existingValue) {
        Node existingNode = findNode(existingValue);
        Node insertingNode = new Node(insertingValue);
        existingNode.insertBefore(insertingNode);
        if (head == existingNode) {
            head = insertingNode;
        }
    }

    public void insertAfter(T insertingValue, T existingValue) {
        Node existingNode = findNode(existingValue);
        Node insertingNode = new Node(insertingValue);
        existingNode.insertAfter(insertingNode);
    }

    public void delete(T deletingValue) {
        Node existingNode = findNode(deletingValue);
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
                System.out.print(outStr);
                node = node.getNext();
            }
            System.out.println();
        } else {
            System.out.println("List is empty.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DefaultIterator<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    class DefaultIterator<T> implements Iterator<T> {

        private LinkedList<T> list;

        public DefaultIterator(LinkedList<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return list.head
        }

        @Override
        public T next() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void remove() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
