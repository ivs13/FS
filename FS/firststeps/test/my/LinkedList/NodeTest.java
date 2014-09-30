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
    public void getDefaultPrev() {
        Node node = new Node<String>("A");
        assertNull(node.getPrev());
    }

    @Test
    public void getPrev() {
        Node node = new Node<String>("A");
        Node prevNode = new Node("B");
        node.insertBefore(prevNode);
        assertSame(node.getPrev(), prevNode);
    }

    @Test(expected = RuntimeException.class)
    public void insertBeforeHimself() {
        Node node = new Node<String>("A");
        node.insertBefore(node);
    }

}
