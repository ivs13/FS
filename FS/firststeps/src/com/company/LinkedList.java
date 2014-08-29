package com.company;

public class LinkedList<T> {

    private class Node {
        private T data;
        private Node next = null;

        private Node(T data) {
            this.data = data;
        }

        private T getData() {
            return data;
        }

        private void setData(T data) {
            this.data = data;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;

}
