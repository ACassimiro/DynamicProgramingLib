package lib;

/**
 * Created by Marcelo on 05/10/2016.
 */
public class Item {

    private int value;
    private int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    int getValue() {
        return value;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }
}
