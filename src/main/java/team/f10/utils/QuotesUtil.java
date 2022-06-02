package team.f10.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Component
public class QuotesUtil {

    @Getter
    @AllArgsConstructor
    private static class Quote {
        private String text;
        private String author;
    }

    private static final String JOKE_SERVICE_URL = "https://type.fit/api/quotes";

    private final Map<Integer, Quote> quotes;

    public QuotesUtil() {
        quotes = new HashMap<>();
        try {
            JSONObject jsonObject = readJsonFromUrl(JOKE_SERVICE_URL);
            JSONArray arr = jsonObject.getJSONArray("result");

            for (int i = 0; i < arr.length(); i++) {
                Quote quote = new Quote(arr.getJSONObject(i).get("text").toString(),
                        arr.getJSONObject(i).get("author").toString());
                quotes.put(i, quote);
            }
        } catch (Exception e) {
            log.error("Error while getting quotes : " + e.getMessage(), e);
        }
    }

    public Pair<String, String> getQuote() {

        if (quotes.isEmpty())
            return Pair.of("No quotes available :(", "Site administration");

        List<Integer> keysAsArray = new ArrayList(quotes.keySet());
        Random r = new Random();

        Quote quote = quotes.get(keysAsArray.get(r.nextInt(keysAsArray.size())));

        return Pair.of(quote.getText(), quote.getAuthor());
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = "{ \"result\": " + readAll(rd) + " }";
            return new JSONObject(jsonText);
        }
    }


}
