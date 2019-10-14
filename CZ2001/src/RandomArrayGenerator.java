import java.util.*;

public class RandomArrayGenerator {
    public int[] generate(int arr_size) {
        int i;
        Random random = new Random();
        int[] array = new int[arr_size];
        for (i=0; i<arr_size; i++) {
            array[i] = random.nextInt(30);
        }
        return array;
    }
}
