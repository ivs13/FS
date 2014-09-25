package my.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListDefaultIterator<T> implements Iterator<T> {

    private Node<T> cursorNode;

    LinkedListDefaultIterator(Node headNode) {
        this.cursorNode = headNode;
    }

    @Override
    public boolean hasNext() {
        return cursorNode != null;
    }

    @Override
    public T next() {
        if (cursorNode == null)
            throw new NoSuchElementException();
        T result = cursorNode.getData();
        cursorNode = cursorNode.getNext();
        return result;
    }



}
