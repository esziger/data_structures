package ds;

/**
 * Created by esziger on 2017-11-13.
 */
public class BasicLinkedList<X> {

    private Node first;
    private Node last;

    private int size;

    public BasicLinkedList(){
        first = null;
        last = null;
        size = 0;
    }

    //O(1) runtime
    public void add(X data){
        if(first == null)
        {
            first = new Node(data);
            last = first;
        }
        else {
            Node newNode = new Node(data);
            last.setNextNode(newNode);
            last = newNode;
        }
        size++;
    }

    //O(n) runtime
    public void insert(X item, int position){

        if(position > size())
            throw new IllegalArgumentException("The position is not exist");

        Node currentNode = first;

        //starts 1 because we are already on the first node
        for(int i = 1; i < position && currentNode != null; i++)
        {
            currentNode = first.getNextNode();
        }

        Node newNode = new Node(item);

        Node originalNextNode = currentNode.getNextNode();
        newNode.setNextNode(originalNextNode);

        currentNode.setNextNode(newNode);

        size++;
    }

    //O(n) runtime
    public X removeAt(int position){

        if(position > size())
            throw new IllegalArgumentException("The position is not exist");

        Node currentNode = first;

        //starts 1 because we are already on the first node
        for(int i = 1; i < position-1 && currentNode != null; i++)
        {
            currentNode = first.getNextNode();
        }

        Node deletedNode = currentNode.getNextNode();

        currentNode.setNextNode(deletedNode.getNextNode());

        X data = currentNode.getData();

        size--;

        return data;
    }

    //remove the first element
    //O(1) runtime
    public X remove(){
        if(size() == 0){
            throw new IllegalStateException("The linked list is empty I can not remove any element");
        }

        X nodeItem = first.getData();
        size--;
        return nodeItem;
    }

    public int size(){
        return size;
    }

    //O(n) runtime
    public X get(int position){
        if(first == null)
            throw new IllegalStateException("The linkedList is empty.");

        Node currentNode = first;

        //starts 1 because we are already on the first node
        for(int i = 1; i < size() && currentNode != null; i++)
        {
            if(i == position)
                return currentNode.getData();

            currentNode = first.getNextNode();
        }

        return null;
    }

    //O(n) runtime
    //returns the position of the item in the list
    public int find(X item){
        if(first == null)
            throw new IllegalStateException("The linkedList is empty.");

        Node currentNode = first;

        //starts 1 because we are already on the first node
        for(int i = 1; i < size() && currentNode != null; i++)
        {
            if(currentNode.getData().equals(item)){
                return i;
            }

            currentNode = first.getNextNode();
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuffer contents = new StringBuffer();
        Node curNode = first;

        while(curNode != null){
            contents.append(curNode.getData());
            curNode = curNode.getNextNode();

            if(curNode != null)
                    contents.append(", ");
        }
        return contents.toString();
    }

    public class Node{

        private X data;
        private Node nextNode;

        public Node(X data){
            this.data = data;
            this.nextNode = null;
        }

        public X getData() {
            return data;
        }

        public void setData(X data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

    }


}
