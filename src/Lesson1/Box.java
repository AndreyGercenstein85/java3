package Lesson1;


import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private ArrayList<T> items;

    public Box(T... items) {
        this.items = new ArrayList<T>(Arrays.asList(items));
    }

    public void add(T... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public void remove(T... items){
        for(T item: items) this.items.remove(item);
    }

    private float getWeight(){
        float sumWeight = 0f;
        if(items.size() == 0) return 0f;
        for (T item: items) sumWeight += item.getWeight();
        return  sumWeight;
    }

    public boolean compare(Box anotherBox) {
        return (Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.00001);
    }
    private void clear() {
        items.clear();
    }

    public void transfer(Box<T> anotherBox){
        if (anotherBox.items.isEmpty()){
            System.out.println("anotherBox is empty");
            return;
        }
        if (anotherBox.items.equals(this.items)) {
            System.out.println("anotherBox -> box");
            return;
        }
        anotherBox.items.addAll(this.items);
        clear();
    }
}
