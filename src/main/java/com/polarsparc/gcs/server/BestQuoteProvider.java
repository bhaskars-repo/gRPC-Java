/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.server;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BestQuoteProvider {
    private final static Logger LOGGER = Logger.getLogger(BestQuoteProvider.class.getName());

    private final static Map<String, List<ProviderQuote>> quotesTable = new HashMap<>();

    static {
        LOGGER.setLevel(Level.INFO);

        List<ProviderQuote> p1 = Arrays.asList(new ProviderQuote("Alice", 20, 30, 1000.00),
                new ProviderQuote("Alice", 31, 45, 1500.00),
                new ProviderQuote("Alice", 46, 55, 2000.00));
        quotesTable.put("Alice", p1);

        List<ProviderQuote> p2 = Arrays.asList(new ProviderQuote("Bob", 20, 30, 1100.00),
                new ProviderQuote("Bob", 31, 45, 1475.00),
                new ProviderQuote("Bob", 46, 55, 1950.00));
        quotesTable.put("Bob", p2);

        List<ProviderQuote> p3 = Arrays.asList(new ProviderQuote("Charlie", 20, 30, 975.00),
                new ProviderQuote("Charlie", 31, 45, 1525.00),
                new ProviderQuote("Charlie", 46, 55, 2050.00));
        quotesTable.put("Charlie", p3);
    }

    private BestQuoteProvider() {
    }

    public static ProviderQuote getBestQuote(String provider, int age) {
        LOGGER.info(String.format("Request for provider: %s, age: %d", provider, age));

        if (!quotesTable.containsKey(provider)) {
            throw new RuntimeException(String.format("Specified provider %s invalid", provider));
        }

        ProviderQuote quote = null;

        List<ProviderQuote> quotes = quotesTable.get(provider);
        for (ProviderQuote pq : quotes) {
            if (pq.inRange(age)) {
                quote = pq;
                break;
            }
        }

        if (quote == null) {
            throw new RuntimeException(String.format("No Quote for the specified provider %s and age %d",
                    provider, age));
        }

        LOGGER.info(String.format("Quote by provider %s at price %.02f", provider, quote.getPrice()));

        return quote;
    }
}
