package com.example.currencyexchanger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    Currency[] data;
    Context mContext;

    public CurrencyAdapter(@NonNull Context context, Currency[] data) {
        super(context, R.layout.currency_spinner);
        this.mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.currency_spinner, parent, false);
        }

        Currency curCurrency = data[position];
        TextView tvCurrencyName = convertView.findViewById(R.id.tvCurrencyName);
        TextView tvValueToDollar = convertView.findViewById(R.id.tvValueToDollar);
        ImageView ivFlag = convertView.findViewById(R.id.ivFlag);

        tvCurrencyName.setText(curCurrency.getName());
        tvValueToDollar.setText(String.format(Locale.getDefault(), "%.2f $", curCurrency.getValueToDollar()));
        ivFlag.setImageResource(curCurrency.getFlagResId());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
