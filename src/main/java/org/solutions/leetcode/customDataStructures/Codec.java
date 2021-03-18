package org.solutions.leetcode.customDataStructures;

import java.util.*;

public class Codec {
    List<Character> chars = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    Random random = new Random();

    public Codec() {
        for (int i = 65; i <= 122; i++) {
            if (i > 90 && i < 97) continue;
            chars.add((char) (i));
        }
        for (int i = 0; i <= 9; i++)
            chars.add((char) (i + '0'));
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 6; i++)
            sb.append(random.nextInt(62));

        String key = "http://tinyurl.com/" + sb.toString();
        map.put(key, longUrl);

        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.getOrDefault(shortUrl, "");
    }
}
