package ds;

import java.util.TreeMap;
import java.util.Hashtable;

/**
 * Created by esziger on 2017-11-14.
 */
//Generic type is comparable
public class BasicBinaryTree<X extends Comparable<X>> {

    private Node root;
    private int size;


    public BasicBinaryTree(){
        this.root = null;
    }

    public int size(){
        return size;
    }

    public void add(X item){
        Node node = new Node(item);

        if(root == null)
        {
            this.root = node;
            System.out.println("Set root: " + node.getItem());
            size++;
        }
        else {
            insert(this.root, node);
        }
    }

//    public boolean contains(X item){
//        if(root.getItem() == item)
//            return true;
//        else{
//            return contains(root, item);
//        }
//    }

    //not working
//    private boolean contains(Node node, X item){
//        if(node.getItem().compareTo(item) == 0) {
//            return true;
//        }
//        else if(node.getItem().compareTo(item) < 0  ) {
//            if (node.getLeft() == null)
//                return false;
//            else
//                return contains(node.getLeft(), item);
//        }
//        else{
//            if(node.getRight() == null)
//                return false;
//            else
//                return contains(node.getRight(), item);
//        }
//    }

    public boolean contains(X item){
            Node currentNode = getNode(item);

        if(currentNode == null)
            return false;
        else
            return true;
    }

    public boolean delete(X item) {
        boolean deleted = false;

        // make sure the root isn't null meaning the tree is empty
        if (this.root == null) {
            return false;
        }

        // find the node to delete
        Node currentNode = getNode(item);

        if (currentNode != null) {
            // if the node to delete doesn't have any children, just delete it
            if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                unlink(currentNode, null);
                deleted = true;
            }
            // if the node to delete only has a right child, remove it in the hierarchy
            else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            // if the node to delete only has a left child, remove it in the hierarchy
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            // the node has both children, do a node swap to delete
            else {
                // you can swap out the node with the right most leaf node on the left side
                Node child = currentNode.getLeft();
                while(child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }

                //we have the right most leaf node.  We can replace the current node with this node
                child.getParent().setRight(null); // remove the leaf node from it's current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }

        if(deleted) {
            this.size--;
        }

        return deleted;
    }


    //Swaps out the currentNode with the newNode. So we can delete currentNode.
    private void unlink(Node currentNode, Node newNode){
        //newNode becomes the root node
        if(currentNode == this.root){
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        }
        else if(currentNode.getParent().getRight() == currentNode){
            currentNode.getParent().setRight(newNode);
        }
        else {
            currentNode.getParent().setLeft(newNode);
        }

    }

    private Node getNode(X item){
        Node currentNode = this.root;

        while(currentNode != null){
            int val = item.compareTo(currentNode.getItem());

            if(val == 0){
                return currentNode;
            }
            else if(val < 0){
                currentNode = currentNode.getLeft();
            }
            else{
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    private void insert(Node parent, Node child){

        if(child.getItem().compareTo(parent.getItem()) < 0){

            if(parent.getLeft() == null){
                parent.setLeft(child);
                child.setParent(parent);
                size++;
            }
            else {
                insert(parent.getLeft(), child);
            }
        }
        else if(child.getItem().compareTo(parent.getItem()) > 0){

            if(parent.getRight() == null){
                parent.setRight(child);
                child.setParent(parent);
                size++;
            }
            else {
                insert(parent.getRight(), child);
            }
        }

        //if the parent and child is equal, i don't do anything
        //the data is already exist and the data in a binary tree is assumed to be unique.
    }

    private class Node{
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node(X item){
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }


    public static void main(String[] args) {
        BasicBinaryTree<Integer> tree = new BasicBinaryTree<>();

        tree.add(5);
        tree.add(2);
        tree.add(3);
        tree.add(11);
        tree.add(9);

        System.out.println("Tree contains 9? " + tree.contains(9));
        System.out.println("Tree contains 15? " + tree.contains(15));
    }

}
