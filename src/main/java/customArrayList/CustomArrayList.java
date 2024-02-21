package customArrayList;

import java.util.*;

public class CustomArrayList<T>  {
    private int size = 0;
    private Object[] arr = new Object[10];

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size(); i++) {
            if(o.equals(arr[i])){
                return true;
            }
        }
        return false;
    }
    private class It implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return (T) arr[index++];
        }


    }
    public Iterator<T> iterator() {
        return new It();
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        if (size() >= 0) {
            System.arraycopy(arr, 0, array, 0, size());
        }
        return array;
    }

    public <T> T[] toArray(T[] a) {
        Object[] array = new Object[size()];
        if (size() >= 0) System.arraycopy(arr, 0, array, 0, size());
        return (T[])array;
    }

    public boolean add(T t) {
        if (size >= arr.length){
            arr = Arrays.copyOf(arr, (int) (arr.length*1.5));
        }
        arr[size++] = t;
        return true;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if (c.contains(arr[i])){
                count++;
            }
        }
        return count == c.size();
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Object[] newArray = new Object[size + c.size()];
        System.arraycopy(arr, 0, newArray, 0, index);
        int newIndex = index;
        for (T element : c) {
            newArray[newIndex++] = element;
        }
        System.arraycopy(arr, index, newArray, newIndex, size - index);
        arr = newArray;
        size += c.size();
        return true;
    }
    public boolean removeAll(Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            if(c.contains(arr[i])){
                remove(arr[i--]);
            }
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        int size = size();
        for (int i = 0; i < size(); i++) {
            if(!c.contains(arr[i])){
                remove(arr[i--]);
            }
        }
        return size != size();
    }

    public void clear() {
        arr = new Object[10];
        size = 0;
    }

    public T get(int index) {
        if(index >= size) throw new IndexOutOfBoundsException();
        return (T) arr[index];
    }

    public T set(int index, T element) {
        if(index > size()) throw new IndexOutOfBoundsException();
        arr[index] = element;
        return (T) arr[index];
    }

    public void add(int index, T element) {
        //10 20 30 40 50
        //10 -10 20 30 40 50
        if(index >= arr.length) throw new IndexOutOfBoundsException();
        System.arraycopy(arr,index,arr,index+1,size() - index);
        arr[index] = element;
    }

    public T remove(int index) {
        if(index >= size) throw new IndexOutOfBoundsException();
        T removed = (T)arr[index];
        System.arraycopy(arr,index+1,arr,index,size() - index - 1);
        size--;
        return removed;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if(o.equals(arr[i])){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size() ; i >= 0; i--) {
            if(o.equals(arr[i])) return i;
        }
        return -1;
    }
    private class ListItr implements ListIterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return (T) arr[index++];
        }

        @Override
        public boolean hasPrevious() {
            return index >=0;
        }

        @Override
        public T previous() {
            return (T)arr[index--];
        }

        @Override
        public int nextIndex() {
            if(index == size()){
                return size();
            }
            return ++index;
        }

        @Override
        public int previousIndex() {
            if(index == 0){
                return -1;
            }
            return --index;
        }

        @Override
        public void remove() {
            System.arraycopy(arr,index+1,arr,index,size() - index - 1);
        }

        @Override
        public void set(T t) {

        }

        @Override
        public void add(T t) {
            System.arraycopy(arr,index,arr,index+1,size() - index);
            arr[index] = t;
        }
    }
    public ListIterator<T> listIterator() {
        return new ListItr();
    }

    public CustomArrayList<T> subList(int fromIndex, int toIndex) {
        CustomArrayList<T> tMyArrayList = new CustomArrayList<>();
        if(fromIndex == toIndex) return tMyArrayList;
        if(fromIndex > toIndex) throw new IllegalArgumentException();
        if(toIndex >= size()) throw new IndexOutOfBoundsException();
        for (int i = fromIndex; i < toIndex ; i++) {
            tMyArrayList.add((T) arr[i]);
        }
        return tMyArrayList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
