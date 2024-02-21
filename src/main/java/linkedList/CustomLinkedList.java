package linkedList;

public class CustomLinkedList<T>{
    private Node<T> head;
    private Node<T> last;
    private int size;

    public void addLast(T val){ //добавление в конец
        Node<T> newNode = new Node<>(val);
        if (isEmpty()) {
            head = newNode;
            last = newNode;
        }
        else {
            newNode.prevNode = last;
            last.nextNode = newNode;
            last = newNode;
        }
        size++;
    }

    public void addFirst(T val){ //добавление в начало
        Node<T> newNode = new Node<>(val);
        if (isEmpty()){
            head = newNode;
            last = newNode;
        }
        else {
            newNode.nextNode = head;
            head.prevNode = newNode;
            head = newNode;
        }
        size++;
    }

    public void add(int index,T val) { //вставка
        Node<T> newNode = new Node<>(val);
        if (index == 0 || size() == 0) {
            addFirst(val);
        } else if (index >= size()) {
            addLast(val);
        }
        else {
            Node<T> currentValue = getNode(index);
            currentValue.prevNode.nextNode = newNode; //Обращаемся к элементу, который будет стоять перед новым и ссылаем его на новый
            newNode.prevNode = currentValue.prevNode; // нашу новую ноду ссылаем на то, на что ссылался правый элемент(проще говоря ссылка влево)
            currentValue.prevNode = newNode;
            newNode.nextNode = currentValue;
            size++;
        }
    }


    public boolean contains(Object obj){
        Node<T> currentNode = head;
        for (int i = 0; i < size(); i++) {
            if (obj.equals(currentNode.value)){
                return true;
            }
            currentNode = currentNode.nextNode;
        }
        return false;
    }

    public void print(){
        Node<T> currentNode = head;
        for (int i = 0; i < size(); i++) {
            System.out.println(currentNode.value + " ");
            currentNode = currentNode.nextNode;
        }
    }

    private Node<T> getNode(int index){
        Node<T> currentNode = head;
        for (int i = 0; i < index ; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    public T get(int index){
        Node<T> currentNode = head;
        for (int i = 0; i < index ; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.value;
    }

    public void remove(T val) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(val)) {
                if (currentNode == head) {
                    head = currentNode.nextNode;
                    if (head != null) {
                        head.prevNode = null;
                    } else {
                        last = null;
                    }
                } else if (currentNode == last) {
                    last = currentNode.prevNode;
                    last.nextNode = null;
                } else {
                    currentNode.prevNode.nextNode = currentNode.nextNode;
                    currentNode.nextNode.prevNode = currentNode.prevNode;
                }
                size--;
                return;
            }
            currentNode = currentNode.nextNode;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    class Node<T>{
        private Node<T> nextNode;
        private Node<T> prevNode;
        private T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
