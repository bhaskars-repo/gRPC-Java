/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyRateProvider {
    private final static Logger LOGGER = Logger.getLogger(CurrencyRateProvider.class.getName());

    private final static Map<String, List<CurrencyRate>> ratesTable = new HashMap<>();

    static {
        LOGGER.setLevel(Level.INFO);

        ratesTable.put("USD:CAD", Arrays.asList(new CurrencyRate("Alice", 1.30),
                new CurrencyRate("Bob", 1.302),
                new CurrencyRate("Dave", 1.31)));
        ratesTable.put("USD:EUR", Arrays.asList(new CurrencyRate("Alice", 0.85),
                new CurrencyRate("Charlie", 0.84)));
        ratesTable.put("USD:GBP", Arrays.asList(new CurrencyRate("Bob", 0.75),
                new CurrencyRate("Charlie", 0.751),
                new CurrencyRate("Eve", 0.74)));
    }

    private CurrencyRateProvider() {
    }

    public static List<CurrencyRate> getCurrencyRate(String from, String to) {
        String key = (from + ":" + to).toUpperCase();

        LOGGER.info(String.format("Currency rate request for key: %s", key));

        if (!ratesTable.containsKey(key)) {
            throw new RuntimeException(String.format("No rate for currency from: %s, to: %s", from, to));
        }

        List<CurrencyRate> rates = ratesTable.get(key);

        LOGGER.info(String.format("Currency rates for key: %s = %s", key, rates));

        return rates;
    }
}
