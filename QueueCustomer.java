
public class QueueCustomer extends LinkedList
{
    public QueueCustomer()
    {  }
    
    public void enqueue(Customer v)
    {insertAtBack(v); }
    
    public Customer dequeue()
    { return removeFromFront(); }
    
    public Customer getFront()
    { return getFirst(); }
    
    public Customer getEnd()
    { 
        Customer v = removeFromBack();
        insertAtBack (v);
        return v;
    }
    
    public boolean isEmpty()
    { 
        return super.isEmpty();
    }
    
    public void clear() {
        super.clear();
    }

    public int size() {
        return super.size();
    }

    public Customer get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        return super.get(index);
    }
    
        public void sort() {
        bubbleSort();
    }
}