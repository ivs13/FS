package com.company;

public class LinkedList<T>
{

    private class Node
    {
        private T data;
        private Node next = null;

        private Node(T data)
        {
            this.data = data;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T data)
        {
            this.data = data;
        }

        private Node getNext()
        {
            return next;
        }

        private void setNext(Node next)
        {
            this.next = next;
        }
    }

    public static class NodeNotFoundException extends RuntimeException {}

    private Node head;

    private void checkParams(T... values)
    {
        for (T value: values)
            if (value == null)
                throw new IllegalArgumentException();
    }

    private Node findNode(T value) throws NodeNotFoundException
    {
        Node result = head;
        while (result != null)
        {
            if (result.getData().equals(value))
                return result;
            result = result.getNext();
        }
        throw new NodeNotFoundException();
    }

    public void Add(T value)
    {
        checkParams(value);
        Node newNode = new Node(value);
        if (head == null)
        {
            head = newNode;
        } else {
            Node lastNode = head;
            while (lastNode.next != null)
                lastNode = lastNode.next;
            lastNode.next = newNode;
        }
    }

    public void AddBefore(T inseringValue, T existingValue) throws NodeNotFoundException
    {
        checkParams(inseringValue, existingValue);
        Node node = findNode(existingValue);
    }

    public void AddAfter(T value, T node)
    {
        checkParams(value, node);

    }

}
