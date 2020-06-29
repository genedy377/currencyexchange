package com.example.currencyexchanger;

import android.content.Context;

public class Currency {
    private String name;
    private int flagResId;
    private float valueToDollar;

    public Currency(String name, int flagResId, float valueToDollar) {
        this.name = name;
        this.flagResId = flagResId;
        this.valueToDollar = valueToDollar;
    }

    public String getName() {
        return name;
    }

    public int getFlagResId() {
        return flagResId;
    }

    public float getValueToDollar() {
        return valueToDollar;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", flagResId=" + flagResId +
                ", valueToDollar=" + valueToDollar +
                '}';
    }

    static Currency[] getCurrenciesData(Context context) {
        return new Currency[] {
                new Currency(context.getString(R.string.cur_dollar), R.drawable.flag_us2, 1),
                new Currency(context.getString(R.string.cur_egp), R.drawable.flag_eg, 0.062f),
                new Currency(context.getString(R.string.cur_euro), R.drawable.flag_ge2, 1.12f),
                new Currency(context.getString(R.string.cur_AED), R.drawable.flag_ae, 0.27f)
        };
    }
}
