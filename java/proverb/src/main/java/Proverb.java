import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {
    private String[] words;

    public Proverb(String[] words) {
        this.words = words;
    }

    public String recite() {
        return IntStream
            .range(0, words.length)
            .boxed()
            .map(i -> reciteLine(i))
            .collect(Collectors.joining("\n"));
    }

    private String reciteLine(int idx) {
        if (idx == words.length - 1) {
            return String.format("And all for the want of a %s.", words[0]);
        }
        return String.format("For want of a %s the %s was lost.", words[idx], words[idx + 1]);
    }
}
