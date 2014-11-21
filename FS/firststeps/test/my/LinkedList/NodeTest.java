package my.LinkedList;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertSame;

public class NodeTest {

    @Test
    public void getNull() {
        Node node = new Node<String>(null);
        assertNull(node.getData());
    }

    @Test
    public void getBoolean() {
        Node<Boolean> node = new Node<Boolean>(true);
        assertNotNull(node.getData());
        assertTrue(node.getData());
    }

    @Test
    public void getNullPrev() {
        Node node = new Node<String>("A");
        assertNull(node.getPrev());
    }

    @Test(expected = RuntimeException.class)
    public void insertBeforeHimself() {
        Node<String> node = new Node<String>("A");
        node.insertBefore(node);
    }

    @Test
    public void insertBefore() {
        Node<String> node = new Node<String>("A");
        Node<String> prevNode = new Node<String>("B");
        node.insertBefore(prevNode);
        assertSame(node.getPrev(), prevNode);
    }

    @Test
    public void insertBefore2() {
        Node<String> nodeA = new Node<String>("A");
        Node<String> nodeB = new Node<String>("B");
        Node<String> nodeC = new Node<String>("C");
        nodeA.insertBefore(nodeC);
        nodeA.insertBefore(nodeB);
        assertSame(nodeA.getPrev(), nodeB);
        assertSame(nodeB.getPrev(), nodeC);
    }

    @Test
    public void getDefaultNext() {
        Node node = new Node<String>("A");
        assertNull(node.getNext());
    }

    @Test
    public void getNext() {
        Node<String> node = new Node<String>("A");
        Node<String> nextNode = new Node<String>("B");
        node.insertAfter(nextNode);
        assertSame(node.getNext(), nextNode);
    }

    @Test(expected = RuntimeException.class)
    public void insertAfterHimself() {
        Node<String> node = new Node<String>("A");
        node.insertAfter(node);
    }

}
