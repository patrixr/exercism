class CollatzCalculator {

    public int computeStepCount(int n) throws IllegalArgumentException {
        if (n <= 0) throw new IllegalArgumentException("Only natural numbers are allowed");
        if (n == 1) return 0;
        return 1 + computeStepCount(n % 2 == 0 ? n / 2 : 3 * n + 1);
    }
}
