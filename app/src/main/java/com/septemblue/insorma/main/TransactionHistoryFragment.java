package com.septemblue.insorma.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.septemblue.insorma.R;
import com.septemblue.insorma.databinding.FragmentTransactionHistoryBinding;
import com.septemblue.insorma.local.Account;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.History;

import java.util.Objects;
import java.util.Vector;

public class TransactionHistoryFragment extends Fragment {

    private TransactionHistoryViewModel viewModel;
    private FragmentTransactionHistoryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.transactionHistoryListview.setAdapter(new TransHistoryAdapter(this.getContext()));

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem item = menu.findItem(R.id.toolbar_username);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private class TransHistoryAdapter extends BaseAdapter {
        Context context;

        public TransHistoryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            int total = Objects.requireNonNull(Database.transactionHistory.getValue()).size();
            if (total != 0) return total;
            else {
                Toast.makeText(context, "There are no transaction data", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View adapterView = getLayoutInflater().inflate(R.layout.transaction_history_listview, parent, false);
            TextView transId = adapterView.findViewById(R.id.transaction_history_transid);
            TextView transFurnitureTitle = adapterView.findViewById(R.id.transaction_history_furniture_title);
            TextView transFurniturePrice = adapterView.findViewById(R.id.transaction_history_furniture_price);
            TextView transDate = adapterView.findViewById(R.id.transaction_history_furniture_date);
            TextView transFurnitureQuantity = adapterView.findViewById(R.id.transaction_history_furniture_quantity);

            Vector<History> histories = Database.transactionHistory.getValue();

            transId.setText(histories.get(position).transId);
            transFurnitureTitle.setText(histories.get(position).furniture.title);
            transFurnitureQuantity.setText(histories.get(position).quantity);
            transFurniturePrice.setText(String.format("Rp. %s", histories.get(position).totalPrice));
            transDate.setText(histories.get(position).transDate.toString());

            return adapterView;
        }
    }

}