import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PalindromeCalculator {

    private boolean isPalindrome(Integer n) {
        String str = n.toString();
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private boolean equalPairs(List<Integer> pair1, List<Integer> pair2) {
        return (
            (Objects.equals(pair1.get(0), pair2.get(0)) && Objects.equals(pair1.get(1), pair2.get(1))) ||
            (Objects.equals(pair1.get(0), pair2.get(1)) && Objects.equals(pair1.get(1), pair2.get(0)))
        );
    }

    private void matrix(int start, int end, BiConsumer<Integer, Integer> consumer) {
        int[] range = IntStream.rangeClosed(start, end).toArray();
        for (int n : range) {
            for (int m : range) {
                consumer.accept(n, m);
            }
        }
    }

    private <K, V> SortedMap<K, V> pickKeys(SortedMap<K, V> map, List<K> keys) {
        return new TreeMap<K, V>() {{
            keys.forEach(k -> put(k, map.get(k)));
        }};
    }

    public SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int start, int end) throws IllegalArgumentException {
        if (end < start) throw new IllegalArgumentException("invalid input: min must be <= max");

        SortedMap<Long, List<List<Integer>>> results = new TreeMap<>();

        matrix(start, end, (n, m) -> {
            if (!isPalindrome(m * n)) return;

            Long palindrome = (long) m * n;
            List<Integer> pair = Arrays.asList(n, m);
            List<List<Integer>> factors = results.computeIfAbsent(palindrome, (k) -> new LinkedList<>());

            if (factors.stream().anyMatch((p) -> equalPairs(p, pair))) return;

            factors.add(pair);
        });

        if (results.size() > 2) {
            return pickKeys(results, Arrays.asList(results.firstKey(), results.lastKey()));
        }
        return results;
    }
}