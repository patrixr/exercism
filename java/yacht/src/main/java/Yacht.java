class Yacht {
    private int[] dice;
    private YachtCategory category;

    Yacht(int[] rolledDice, YachtCategory yachtCategory) {
        category = yachtCategory;
        dice = rolledDice;
    }

    int score() {
       return category.computeScore(dice);
    }

}
