/*
   @Author: Bhaskar S
   @Blog:   https://www.polarsparc.com
   @Date:   04 Dec 2020
*/

package com.polarsparc.gss.server;

public class CurrencyRate {
    private final String agent;
    private final Double rate;

    public CurrencyRate(String agent, Double rate) {
        this.agent = agent;
        this.rate = rate;
    }

    public String getAgent() {
        return this.agent;
    }

    public Double getRate() {
        return this.rate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "agent='" + agent + '\'' +
                ", rate=" + rate +
                '}';
    }
}
