package com.example.currencyexchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Currency[] currencies;
    EditText etSrcAmount;
    EditText etDstAmount;
    Spinner spSrcCurrency;
    Spinner spDstCurrency;
    boolean srcChanged = false;
    boolean dstChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencies = Currency.getCurrenciesData(this);

        spSrcCurrency = findViewById(R.id.spSrcCurrency);
        spDstCurrency = findViewById(R.id.spDstCurrency);
        spSrcCurrency.setAdapter(new CurrencyAdapter(this, currencies));
        spDstCurrency.setAdapter(new CurrencyAdapter(this, currencies));
        spSrcCurrency.setOnItemSelectedListener(this);
        spDstCurrency.setOnItemSelectedListener(this);

        etSrcAmount = findViewById(R.id.etSrcAmount);
        etDstAmount = findViewById(R.id.etDstAmount);
        etSrcAmount.addTextChangedListener(new SrcTextWatcher());
        etDstAmount.addTextChangedListener(new DstTextWatcher());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        EditText editText;
        onSrcTextChanged();
        if (parent.getId() == R.id.spSrcCurrency) {
            editText = findViewById(R.id.etSrcAmount);
        } else {
            editText = findViewById(R.id.etDstAmount);
        }

        editText.setEnabled(true);
        editText.setHint(this.getString(R.string.type_currency_amount) + currencies[position].getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        EditText editText;
        if (parent.getId() == R.id.spSrcCurrency) {
            editText = findViewById(R.id.etSrcAmount);
        } else {
            editText = findViewById(R.id.etDstAmount);
        }
        editText.setEnabled(false);
        editText.setHint(this.getString(R.string.currency_not_selected));
    }

    void onSrcTextChanged() {
        if (srcChanged) {
            srcChanged = false;
            return;
        }

        Currency srcCurrency = currencies[spSrcCurrency.getSelectedItemPosition()];
        Currency dstCurrency = currencies[spDstCurrency.getSelectedItemPosition()];
        dstChanged = true;
        try {
            float value  = Float.parseFloat(etSrcAmount.getText().toString());
            float newAmount = srcCurrency.getValueToDollar()/dstCurrency.getValueToDollar()*value;
            etDstAmount.setText(String.format(Locale.getDefault(), "%.2f", newAmount));
        } catch (Exception e) {
            etDstAmount.setText("0");
        }
    }

    void onDstTextChanged() {
        if (dstChanged) {
            dstChanged = false;
            return;
        }

        Currency srcCurrency = currencies[spSrcCurrency.getSelectedItemPosition()];
        Currency dstCurrency = currencies[spDstCurrency.getSelectedItemPosition()];
        srcChanged = true;
        try {
            float value  = Float.parseFloat(etDstAmount.getText().toString());
            float newAmount = dstCurrency.getValueToDollar()/srcCurrency.getValueToDollar()*value;
            etSrcAmount.setText(String.format(Locale.getDefault(), "%.2f", newAmount));
        } catch (Exception e) {
            etSrcAmount.setText("0");
        }
    }

    private class SrcTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            onSrcTextChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class DstTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            onDstTextChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
