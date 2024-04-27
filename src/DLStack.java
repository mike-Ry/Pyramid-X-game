
/**
 * CS 1027  
 * @author Chuanxi wang 
 * Assignment 3 
 */


public class DLStack<T> implements DLStackADT<T> {
    private DoubleLinkedNode<T> top; // a reference to the node at the top of the stack.
    private int numItems;  // the number of data items stored in the stack.

    /**
     *  constructor method, to build an empty stack
     */
    public DLStack() {
        this.top = null; // set top to null
        this.numItems = 0; // set numItems to zero
    }

    /**
     * Adds the given dataItem to the top of the stack
     * @param element element to be pushed onto stack
     */
    @Override
    public void push(T element) {
    	// create a DoubleLinkedNode that contains element
        DoubleLinkedNode<T> node = new DoubleLinkedNode<>(element); 
        // Set the top node of the current stack as the predecessor of the node node
        node.setPrevious(top);
        // check if the top node is null
        if (top != null) {
            // if top node is not null, set node node be the back node of the top node 
            top.setNext(node);
        }
        // Point the pointer of the top node to the node node
        top = node;
        // numItems = numItems + 1
        numItems++;
    }

    /**
     * Removes and returns the data item at the top of the stack. 
     * An EmptyStackException is thrown if the stack is empty.
     */
    @Override
    public T pop() throws EmptyStackException {
        if (top == null || numItems == 0) {
            // An EmptyStackException is thrown if the stack is empty.
            throw new EmptyStackException("");
        }
        // Declare an prevTop node that points to the top node of the stack
        DoubleLinkedNode<T> prevTop = top;
        // The top node of the stack points to the predecessor of the top node of the stack
        top = top.getPrevious();
        if (top != null) {
            // if current stack's top node is not empty, point the posterior node of the top node to null 
            top.setNext(null); 
        }
        // numItems = numItems -1
        numItems--;
        // returns the removed data items.
        return prevTop.getElement();
    }

    /**
     * Returns the data item at the top of the stack without removing it. 
     * An EmptyStackException is thrown if the stack is empty.
     * @return
     */
    @Override
    public T peek() {
        if (top == null || numItems == 0) {
            // if top is null or numItems is zero, throw EmptyStackException
            throw new EmptyStackException("");
        }
        // Returns the data item at the top of the stack
        return top.getElement();
    }

    /**
     * Removes and returns the k-th data item from the top of the stack
     * @param k
     * @return
     * @throws InvalidItemException
     */
    @Override
    public T pop(int k) throws InvalidItemException {
        if (k > numItems || k <= 0) {
            // if value k greater than numItems or value k smaller or equal to zero, throw InvalidItemException
            throw new InvalidItemException("");
        }
        if (k == 1) {
        	// if value k equal one, must remove the data item at the top of the stack
            return this.pop();
        }
        // declare a newNode node point to the top node of the stack
        DoubleLinkedNode<T> newNode = top;
        // Point the newNode to the kth node
        for (int i = 1; i < k; i++) {
            newNode = newNode.getPrevious();
        }
        // The posterior node of the kth node
        DoubleLinkedNode<T> next = newNode.getNext();
        // The Predecessor node of the kth node
        DoubleLinkedNode<T> previous = newNode.getPrevious();
        
    
        // Delete the kth node 
        // connect the predecessor node of the kth node to the posterior node of the kth node
        
        if (previous != null) {
            previous.setNext(next);
        }
        if (next != null) {
        	next.setPrevious(previous);
        }    
        
        // return the kth node's data item
        return newNode.getElement();
    }

    /**
     * if the stack is empty return true, otherwise return false
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top == null || numItems == 0;
    }

    /**
     * return the size of the data items of the stack
     * @return
     */
    @Override
    public int size() {
        return numItems;
    }

    /**
     * return top
     * @return
     */
    @Override
    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    /**
     * Returns a string of the form “[data1 data2 … datan]”  where data1 is the data item at the top of the stack
     * datan is the data item at the bottom of the stack.
     *
     * @return
     */
    @Override
    public String toString() {
    	//declare a node node point to the top node of the stack
        DoubleLinkedNode<T> node = top;
        String string = "[";
        for (int i = 0; i < numItems; i++) {
            string = string + node.getElement();
            node = node.getPrevious();
        }
        string = string + "]";
        return string;
    }
}
