import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Knapsack {

    public int maximumValue(int maxBagWeight, ArrayList<Item> items) {
        int maxValue = 0;

        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);

            if (it == null || it.getWeight() > maxBagWeight) {
                continue;
            }

            items.set(i, null);

            int value = it.getValue() + maximumValue(maxBagWeight - it.getWeight(), items);

            maxValue = value > maxValue ? value : maxValue;

            items.set(i, it);
        }

        return maxValue;
    }
}