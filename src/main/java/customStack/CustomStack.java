package customStack;

import java.util.Arrays;

public class CustomStack<T> {
    private Object[] arr = new Object[10];
    private int size;

   public boolean empty(){
        return size == 0;
    }
   public Object peek(){
        return arr[0];
    }
   public boolean remove(Object o){
        for (int i = 0; i < size(); i++) {
            if(o.equals(arr[i])){
                System.arraycopy(arr,i+1,arr,i,size() - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

   public int size(){
        return size;
    }

   public Object pop(){
        T returned = (T) arr[size()-1];
        remove(returned);
        return returned;
    }

   public boolean add(Object element){
        if(size() >= arr.length){
            arr = Arrays.copyOf(arr, (int) (arr.length*1.5));
        }
        arr[size++] = element;
        return true;
    }

   public int search(Object element){
        for (int i = 0; i < arr.length; i++) {
            if(element.equals(arr[i])){
                return size() - i; //смещение от вершины
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(arr[i]).append(" ");
        }
        return sb.toString();
    }
}
