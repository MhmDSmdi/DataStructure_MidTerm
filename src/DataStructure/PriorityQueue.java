package DataStructure;

import Models.OrderData;

public class PriorityQueue {
    private OrderData[] myQueue;
    private int size;
    private static final int FRONT = 1;

    public PriorityQueue(int maxsize) {
        this.size = 0;
        myQueue = new OrderData[maxsize + 1];
        myQueue[0] = new OrderData(null, Integer.MAX_VALUE, "");
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (myQueue[2 * pos] == null && myQueue[2 * pos + 1] == null)
            return true;
        else
            return false;
    }

    private void swap(int fpos,int spos) {
        OrderData temp;
        temp = myQueue[fpos];
        myQueue[fpos] = myQueue[spos];
        myQueue[spos] = temp;
    }

    private void maxHeapify(int pos) throws NullPointerException {
        if (!isLeaf(pos)) {
            if (myQueue[pos].getPriority() < findMaxNode(myQueue[leftChild(pos)], myQueue[rightChild(pos)]).getPriority()){
                int a = getNodePos(findMaxNode(myQueue[leftChild(pos)], myQueue[rightChild(pos)]));
                if (a > 0){
                    maxHeapify(a);
                    swap(pos, a);
                }
            }
            else if (myQueue[pos].getPriority() == findMaxNode(myQueue[leftChild(pos)], myQueue[rightChild(pos)]).getPriority()){
                if (myQueue[pos].getTime() > findMaxNode(myQueue[leftChild(pos)], myQueue[rightChild(pos)]).getTime()) {
                    int a = getNodePos(findMaxNode(myQueue[leftChild(pos)], myQueue[rightChild(pos)]));
                    if (a > 0){
                        maxHeapify(a);
                        swap(pos, a);
                    }
                }
            }
        }
    }

    private int getNodePos(OrderData data) {
        for (int i = 1 ; i <= size ; i++) {
            if (data.equals(myQueue[i]))
                return i;
        }
        return -1;
    }

    private OrderData findMaxNode(OrderData leftChild, OrderData rightChild) {
        if (rightChild == null)
            return leftChild;
        else if (leftChild.getPriority() > rightChild.getPriority())
            return leftChild;
        else if (leftChild.getPriority() < rightChild.getPriority())
            return rightChild;
        else {
            if (leftChild.getTime() < rightChild.getTime())
                return leftChild;
            else
                return rightChild;
        }
    }

    public void insert(OrderData element) {
        myQueue[++size] = element;
        int current = size;
        while(myQueue[current].getPriority() > myQueue[parent(current)].getPriority()) {
            swap(current,parent(current));
            current = parent(current);
        }
        while(myQueue[current].getPriority() == myQueue[parent(current)].getPriority() && myQueue[current].getTime() < myQueue[parent(current)].getTime()) {
            swap(current,parent(current));
            current = parent(current);
        }
    }

    public void enqueueList() {
        while(size >= 1) {
            OrderData temp = remove();
            System.out.println("CustomerName: " + temp.getCustomerName() /*+ "\tServiceName" + temp.getService().getDataName() */+ "\tOrderPriority: " + temp.getPriority() + "\tOrderTime: " + temp.getTime());
            maxHeap();
        }
    }

    public void maxHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            maxHeapify(pos);
        }
    }

    public OrderData remove() {
        OrderData poped = myQueue[FRONT];
        myQueue[FRONT] = myQueue[size--];
        try {
            maxHeapify(FRONT);
        }catch (NullPointerException e){
        }
        return poped;
    }
}