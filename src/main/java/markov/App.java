package markov;

import java.util.Scanner;

public class App {

    private static final int WORDS_PER_STATE = 2;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        TextFileDownloader fileDownloader =
                new TextFileDownloader(
                        App.class.getResourceAsStream("/songs/vim.txt"));

        String text = fileDownloader.download();
        long end = System.currentTimeMillis();

        outputWithDuration("Downloaded War and Peace in ", start, end);

        start = System.currentTimeMillis();
        String[] words = text.split(" ");
        setWordsToLowerCase(words);
        end = System.currentTimeMillis();

        outputWithDuration("Text preprocessing took ", start, end);

        start = System.currentTimeMillis();
        MarkovChain mc = new MarkovChain(words, WORDS_PER_STATE);
        end = System.currentTimeMillis();

        outputWithDuration("Building Markov chain took ", start, end);
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        while (scanner.hasNextInt()) {
            int sentenceLengthInWords = scanner.nextInt();
            System.out.println(concat(mc.compose(sentenceLengthInWords)));
            System.out.print("> ");
        }
    }

    private static void outputWithDuration(String text, long start, long end) {
        System.out.println(text + (end - start) + " milliseconds.");
    }

    private static String concat(String... strings) {
        StringBuilder sb = new StringBuilder();
        String separator = "";

        for (String string : strings) {
            sb.append(separator);
            separator = " ";
            sb.append(string);
        }

        return sb.toString();
    }

    private static void setWordsToLowerCase(String[] words) {
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].toLowerCase();
        }
    }
}
