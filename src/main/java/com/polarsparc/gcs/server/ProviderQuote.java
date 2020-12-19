/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   12 Dec 2020
*/

package com.polarsparc.gcs.server;

public class ProviderQuote {
    private final String provider;
    private final int ageLow;
    private final int ageHigh;
    private final double price;

    public ProviderQuote(String provider, int ageLow, int ageHigh, double price) {
        this.provider = provider;
        this.ageLow = ageLow;
        this.ageHigh = ageHigh;
        this.price = price;
    }

    public boolean inRange(int age) {
        return age >= this.ageLow && age <= this.ageHigh;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProviderQuote{" +
                "provider='" + provider + '\'' +
                ", ageLow=" + ageLow +
                ", ageHigh=" + ageHigh +
                ", price=" + price +
                '}';
    }
}
