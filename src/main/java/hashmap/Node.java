package hashmap;

import java.util.Objects;

public class Node<K,V>{
    K key;
    V value;

    public Node(K key) {
        this.key = key;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(key, node.key);
    }


}
