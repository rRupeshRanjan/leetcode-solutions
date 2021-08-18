package org.solutions.leetcode.customClassDesign;

import java.util.*;

/**
 * Q. 535 Encode and Decode TinyURL
 * <p>
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and
 * it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 * <p>
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be
 * encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given
 * shortUrl was encoded by the same object.
 * <p>
 * tags:: design
 */
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

        String key = "http://tinyurl.com/" + sb;
        map.put(key, longUrl);

        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.getOrDefault(shortUrl, "");
    }
}
