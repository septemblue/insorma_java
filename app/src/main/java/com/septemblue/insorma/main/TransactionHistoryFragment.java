// Coded by Raffael Hizqya Bakhtiar Ali Maulana Tuasamu
// 2440117122
/*  This fragment purpose is to show all history of transactions done by user
    from the database.

    for improvement notes :
    - maybe in later improvement i will use recycler view, but here i jsut want to
    try listView

 */
package com.septemblue.insorma.main;

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
import com.septemblue.insorma.local.Cache;
import com.septemblue.insorma.local.Database;
import com.septemblue.insorma.local.Transaction;
import com.septemblue.insorma.local.Users;
import com.septemblue.insorma.storage.TransactionHelper;
import com.septemblue.insorma.storage.TransactionHistoryModel;
import com.septemblue.insorma.storage.UserHelper;
import com.septemblue.insorma.storage.UserModel;

import java.util.List;
import java.util.Objects;
import java.util.Vector;
// please read note above package
public class TransactionHistoryFragment extends Fragment {

    private TransactionHistoryViewModel viewModel;
    private FragmentTransactionHistoryBinding binding;
    TransactionHelper transactionHelper;
    UserHelper userHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        userHelper = new UserHelper(this.getContext());
        transactionHelper = new TransactionHelper(this.getContext());
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
        List<TransactionHistoryModel> histories = transactionHelper.getUserTransHistory(userHelper);
        int total = transactionHelper.getUserTransHistoryCount(userHelper);
        public TransHistoryAdapter(Context context) {
            this.context = context;
            if (total == 0) {
                Toast.makeText(context, "There are no transaction data", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getCount() {
            if (total != 0) return total;
            else return 0;
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

            transId.setText(String.format("%d", histories.get(position).transactionId));
            transFurnitureTitle.setText(histories.get(position).furnitureName);
            transFurnitureQuantity.setText(String.format("%d", histories.get(position).quantity));
            transFurniturePrice.setText(String.format("Rp. %d", histories.get(position).quantity * Integer.parseInt(histories.get(position).price)));
            transDate.setText(histories.get(position).date);

            return adapterView;
        }
    }

}