enum YachtCategory {
    YACHT(ComputeStrategyFactory.createYachtStrategy()),
    ONES(ComputeStrategyFactory.createCounterStrategy(1)),
    TWOS(ComputeStrategyFactory.createCounterStrategy(2)),
    THREES(ComputeStrategyFactory.createCounterStrategy(3)),
    FOURS(ComputeStrategyFactory.createCounterStrategy(4)),
    FIVES(ComputeStrategyFactory.createCounterStrategy(5)),
    SIXES(ComputeStrategyFactory.createCounterStrategy(6)),
    FULL_HOUSE(ComputeStrategyFactory.createFullHouseStrategy()),
    FOUR_OF_A_KIND(ComputeStrategyFactory.createFourStrategy()),
    LITTLE_STRAIGHT(ComputeStrategyFactory.createEqualityStrategy(new int[]{1, 2, 3, 4, 5}, 30)),
    BIG_STRAIGHT(ComputeStrategyFactory.createEqualityStrategy(new int[]{2, 3, 4, 5, 6}, 30)),
    CHOICE(ComputeStrategyFactory.createSumStrategy());

    private ComputeStrategy strategy;

    private YachtCategory(final ComputeStrategy strategy) {
        this.strategy = strategy;
    }

    public int computeScore(int[] dice) {
        return this.strategy.compute(dice);
    }
}
