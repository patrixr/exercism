import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
public class ComputeStrategyFactory {

  static ComputeStrategy createCounterStrategy(int val) {
    return (int[] dice) -> Arrays
      .stream(dice)
      .reduce(0, (count, die) -> count + (die == val ? val : 0));
  }

  static ComputeStrategy createSumStrategy() {
    return (int[] dice) -> Arrays
      .stream(dice)
      .reduce(0, (sum, die) -> sum + die);
  }

  static ComputeStrategy createFullHouseStrategy() {
    return (int[] dice) -> {
      if (toSet(dice).size() == 2) {
        long countOne = Arrays
          .stream(dice)
          .filter(n -> n == dice[0])
          .count();

        if (countOne == 2 || countOne == 3) {
          return createSumStrategy().compute(dice);
        }
      }
      return 0;
    };
  }

  public static ComputeStrategy createEqualityStrategy(int[] expected, int score) {
    return (int[] dice) -> arrayEquals(dice, expected) ? score : 0;
  }

  public static ComputeStrategy createFourStrategy() {
    return (int[] dice) -> {
      Set<Integer> set = toSet(dice);

      if (set.size() > 2) {
        return 0;
      }

      Integer val = set
        .stream()
        .filter(n -> count(n, dice) >= 4)
        .findFirst()
        .orElse(0);

     return val * 4;
    };
  }

  public static ComputeStrategy createYachtStrategy() {
    return (int[] dice) -> toSet(dice).size() == 1 ? 50 : 0;
  }

  // ----------------------------
  // ~ Helpers
  // ----------------------------

  private static Set<Integer> toSet(int[] dice) {
    return Arrays
      .stream(dice)
      .boxed()
      .collect(Collectors.toSet());
  }

  private static long count(int num, int[] dice) {
    return Arrays
      .stream(dice)
      .filter(n -> n == num)
      .count();
  }

  private static boolean arrayEquals(int[] left, int[] right) {
    int[] sortedLeft = Arrays.stream(left).sorted().toArray();
    int[] sortedRight = Arrays.stream(right).sorted().toArray();
    return Arrays.equals(sortedLeft, sortedRight);
  }
}
