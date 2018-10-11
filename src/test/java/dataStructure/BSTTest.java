package dataStructure;

import dataStructure.bst.BinarySearchTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BSTTest {

    BinarySearchTree bst;

    @Before
    public void initTree() {
        bst = new BinarySearchTree();
    }

    @Test
    public void testInitTree() {
        Assert.assertEquals(0, bst.getHeight());
        Assert.assertEquals(0, bst.getSize());
    }

    @Test
    public void testInsertRoot() {
        bst.insert(10);
        Assert.assertEquals(1, bst.getHeight());
        Assert.assertEquals(1, bst.getSize());
        Assert.assertEquals(10, bst.findMax().intValue());
        Assert.assertEquals(10, bst.findMin().intValue());
        Assert.assertEquals(true, bst.search(10));
    }

    @Test
    public void test5Insert() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(4);
        bst.insert(12);
        bst.insert(15);
        Assert.assertEquals(3, bst.getHeight());
        Assert.assertEquals(5, bst.getSize());
        Assert.assertEquals(15, bst.findMax().intValue());
        Assert.assertEquals(4, bst.findMin().intValue());
        Assert.assertEquals(true, bst.search(15));
    }

    @Test
    public void testDeleteRootWithoutNode() {
        bst.insert(10);
        bst.delete(10);
        Assert.assertEquals(0, bst.getHeight());
        Assert.assertEquals(0, bst.getSize());
        Assert.assertEquals(null, bst.findMax());
        Assert.assertEquals(null, bst.findMin());
        Assert.assertEquals(false, bst.search(10));
    }

    @Test
    public void testDeleteRootWithNode() {
        bst.insert(10);
        bst.insert(5);
        bst.insert(4);
        bst.insert(12);
        bst.insert(15);
        bst.delete(10);
        bst.printTree();

        Assert.assertEquals(3, bst.getHeight());
        Assert.assertEquals(4, bst.getSize());
        Assert.assertEquals(15, bst.findMax().intValue());
        Assert.assertEquals(4, bst.findMin().intValue());
        Assert.assertEquals(false, bst.search(10));
    }
}
