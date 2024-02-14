public class LinkedList
{
    private NodeCustomer first;
    private NodeCustomer current;
    private NodeCustomer last;
    
    public LinkedList()
    {
        first = null;
        current = null;
        last = null;
    }
    
    public boolean isEmpty()
    {
        return (first == null);
    }
    
    public void insertAtFront(Customer insertItem)
     {
            NodeCustomer newNode = new NodeCustomer(insertItem);

         if (isEmpty())
         {       first = newNode;
                last = newNode;
          }
         else
            {
                newNode.next = first;
                first = newNode;
        }
    }
    
    public void insertAtBack(Customer v)
    {
        NodeCustomer newNodeCustomer = new NodeCustomer(v);
        if(isEmpty())
        {
            first = newNodeCustomer;
            last = newNodeCustomer;
        }
        else
        {
            last.next = newNodeCustomer;
            last = newNodeCustomer;
        }
    }
    
    public Customer removeFromFront()
    {   
        Customer removeItem = null;
        if (isEmpty())
        {   
            return removeItem;
        }
         
        removeItem = first.data;
        if (first == last)
        {   
            first = null;
            last = null;
        }
        else
            first = first.next;
            
        return removeItem;
    }
    
    public Customer getFirst()
    {
        if(isEmpty())
            return null;
        else
        {
            current = first;
            return current.data;
        }
    }
    public Customer getNext()
    {   
        if (current == last)
            return null;
        else
        {   current = current.next;
            return current.data;
        }
}
    public Customer removeFromBack()
    {
        Customer removeItem = null;
        
        if(isEmpty())
            return removeItem;
        
        removeItem = last.data;
        if(first == last)
        {
            first = null;
            last = null;
        }
        else
        {
            current = first;
            while(current.next != last)
                current = current.next;
            
            last = current;
            last.next = null;
        }
        
        return removeItem;
    }
     public void clear() {
        while (first != null) {
            removeFromFront();
        }
    }
    
     public int size() {
        int count = 0;
        current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
        public Customer get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }
    
        public void bubbleSort() {
        if (first == null || first == last) {
            // The list is empty or has only one element, no need to sort
            return;
        }

        boolean swapped;
        NodeCustomer temp;

        do {
            swapped = false;
            current = first;

            while (current.next != null) {
                if (current.data.getCustId() > current.next.data.getCustId()) {
                    // Swap nodes
                    temp = current;
                    current = current.next;
                    temp.next = current.next;
                    current.next = temp;

                    if (temp == first) {
                        first = current;
                    } else {
                        // Update previous node's next pointer
                        NodeCustomer prev = first;
                        while (prev.next != temp) {
                            prev = prev.next;
                        }
                        prev.next = current;
                    }

                    swapped = true;
                }

                current = current.next;
            }
        } while (swapped);
    }
public void remove(Customer C) {
        NodeCustomer current = first;
        NodeCustomer previous = null;

        while (current != null) {
            if (current.data.equals(C)) {
                if (previous == null) {
                    // If the value is in the first node
                    first = current.next;
                } else {
                    // If the value is in a node other than the first
                    previous.next = current.next;
                }
                return; // Value found and removed, exit the method
            }

            previous = current;
            current = current.next;
        }
    }
}