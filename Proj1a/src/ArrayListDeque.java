public class ArrayListDeque<Item> {

    private int size;
    private Item[] arr;
    private int nextFirst;
    private int nextLast;
    private static final int R_FACTOR = 2;

    public ArrayListDeque(){
        size = 0;
        arr = (Item[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    private int minusOne(int index){
        if(index - 1 < 0){
            return arr.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index){
        if(index + 1 > arr.length - 1){
            return 0;
        }
        return index + 1;
    }

    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        int current = plusOne(nextFirst);
        for(int i = 0; i < size; i++){
            copy[i] = arr[current];
            current = plusOne(current);
        }
        arr = copy;
        nextFirst = arr.length - 1;
        nextLast = size;

    }

    public void addFirst(Item x){
        if(size == arr.length){
            resize(R_FACTOR * size);
        }
        arr[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(Item x){
        if(size == arr.length){
            resize(R_FACTOR * size);
        }
        arr[nextLast] = x;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = plusOne(nextFirst);
        while(i != nextLast){
            System.out.print(arr[i] + " ");
            i = plusOne(i);
        }
    }

    private void shrink(){
        if(size >= 16 && size < arr.length/4){
            resize(arr.length/2);
        }
    }

    public Item removeFirst(){
        if(size == 0) return null;
        Item firstitem = arr[plusOne(nextFirst)];
        arr[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        shrink();
        return firstitem;
    }

    public Item removeLast(){
        if(size == 0) return null;
        Item lastitem = arr[minusOne(nextLast)];
        arr[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size--;
        shrink();
        return lastitem;
    }

    public Item get(int index){
        if(index >= size || index < 0){
            return null;
        }
        int current = plusOne(nextFirst);
        for(int i = 0; i < index; i++){
            current = plusOne(current);
        }
        return arr[current];
    }
}
