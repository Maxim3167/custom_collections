package hashmap;

import java.util.LinkedList;

public class CustomHashMap <K,V>{
    private LinkedList<Node>[] buckets = new LinkedList[12];
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void put(K key, V value) {
        int index = Math.abs(key.hashCode())% buckets.length;
        Node newNode = new Node(key, value);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
            buckets[index].add(newNode);
            size++;
        }
        else if (!buckets[index].contains(new Node(key))){
            buckets[index].add(newNode);
            size++;
        }
    }

    public boolean containsKey(K key) {
        int index = Math.abs(key.hashCode())% buckets.length;
        if (buckets[index] == null) return false;
        return (V) buckets[index].get(0).key == key;
    }

    public V get(K key) {
        int index = Math.abs(key.hashCode())% buckets.length;
        if (buckets[index] == null) return null;
        return (V) buckets[index].get(0).value;
    }

    public boolean remove(K key){
        int index = Math.abs(key.hashCode())% buckets.length; //рассчитываем хэш код (индекс) и приводим его значение в диапазон размера масива
        if (buckets[index] == null) return false;
        boolean remove = buckets[index].remove(new Node(key));
        size--;
        return remove;
    }

    public void clear() {
        for (LinkedList<Node> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }
        size = 0;
    }


    public boolean contains(K key){
        int index = key.hashCode() % buckets.length;
        return buckets[index] != null && buckets[index].contains(new Node(key));
    }

    public int size() {
        return size;
    }
}
