public class LinkedListDeque<Item> {
    private int size;
    private ListNode sentinel;

    public class ListNode{
        public Item item;
        public ListNode next;
        public ListNode prev;

        public ListNode(ListNode p, Item i, ListNode n){
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    public void addFirst(Item x){
        ListNode newfirstnode = new ListNode(sentinel, x, sentinel.next);
        sentinel.next.prev = newfirstnode;
        sentinel.next = newfirstnode;
        size++;
    }

    public void addLast(Item x){
        ListNode newlastnode = new ListNode(sentinel.prev, x, sentinel);
        sentinel.prev.next = newlastnode;
        sentinel.prev = newlastnode;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        ListNode cur = sentinel.next;
        while(cur != sentinel){
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
    }

    public Item removeFirst(){
        if(isEmpty()){
            return null;
        }
        Item firstitem = sentinel.next.item;
        ListNode newfirstnode = sentinel.next.next;
        sentinel.next = newfirstnode;
        newfirstnode.prev = sentinel;
        size--;
        return firstitem;
    }


    public Item removeLast(){
        if(isEmpty()){
            return null;
        }
        Item lastitem = sentinel.prev.item;
        ListNode newlastnode = sentinel.prev.prev;
        sentinel.prev = newlastnode;
        newlastnode.next = sentinel;
        size--;
        return lastitem;
    }
    public Item get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        ListNode pointer = sentinel.next;
        while(index > 0){
            pointer = pointer.next;
            index--;
        }
        return pointer.item;
    }

    public Item getRecursive(int index){
        if(index >= size || index < 0){
            return null;
        }
        return helper(sentinel.next, index);
    }

    private Item helper(ListNode cur, int index){
        if(index == 0){
            return cur.item;
        }
        return helper(cur.next, index - 1);

    }


}