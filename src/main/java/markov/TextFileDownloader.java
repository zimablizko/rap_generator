package markov;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public final class TextFileDownloader {

    private final InputStream urlText;

    public TextFileDownloader(InputStream urlText) {
        this.urlText = urlText;
    }

    public String download() {

           // URL url = new URL(urlText);
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(urlText);

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();
    }
}
