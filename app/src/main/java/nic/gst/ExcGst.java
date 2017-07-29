package nic.gst;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ExcGst extends Fragment{

    List<GST> gstList;
    GSTAdapter adapter;
    GstDatatype FinalBill;
    Switch gstswitch;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_exc_gst, null);
        gstswitch= (Switch)view.findViewById(R.id.switch1);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        gstList = new ArrayList<>();
        adapter = new GSTAdapter();
        recyclerView.setAdapter(adapter);
        FinalBill = new GstDatatype();


        Button btnAdd = (Button) view.findViewById(R.id.btAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText EtAmount = (EditText) view.findViewById(R.id.EtAmount);
                if (EtAmount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.percentRg);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                int percentage = -1;
                if (selectedId == R.id.rb5)
                    percentage = 5;
                else if (selectedId == R.id.rb12)
                    percentage = 12;
                else if (selectedId == R.id.rb18)
                    percentage = 18;
                else
                    percentage = 28;

                if(gstswitch.isChecked())
                {
                    Calculator calc = new Calculator();
                    String output = calc.calculate_Exclusive(Double.parseDouble(EtAmount.getText().toString()), percentage, true);


                    String[] temp = output.split("#");

                    if (!(temp[0].equals("Wrong Values"))) {

                        float amount = Float.parseFloat(temp[0]);
                        float igst = Float.parseFloat(temp[1]);
                        float totalAmount = Float.parseFloat(temp[2]);

                        GstDatatype tempcalc = new GstDatatype(amount,igst,totalAmount);
                        FinalBill= FinalBill.add(tempcalc,true);

                        gstList.add(new GST(gstList.size(), percentage + "%", amount, igst, totalAmount));

                        adapter.notifyItemInserted(gstList.size() - 1);
                        gstswitch.setEnabled(false);
                    } else {
                        Toast.makeText(getActivity(), "Wrong Values!", Toast.LENGTH_SHORT).show();

                    }

                }
                else {
                    Calculator calc = new Calculator();
                    String output = calc.calculate_Exclusive(Double.parseDouble(EtAmount.getText().toString()), percentage, false);


                    String[] temp = output.split("#");

                    if (!(temp[0].equals("Wrong Values"))) {
                        float amount = Float.parseFloat(temp[0]);
                        float sgst = Float.parseFloat(temp[1]);
                        float Cgst = Float.parseFloat(temp[2]);
                        float totalAmount = Float.parseFloat(temp[3]);

                        GstDatatype tempcalc = new GstDatatype(amount,sgst,sgst,totalAmount);
                        FinalBill= FinalBill.add(tempcalc,false);

                        gstList.add(new GST(gstList.size(), percentage + "%", amount, sgst, Cgst, totalAmount));

                        adapter.notifyItemInserted(gstList.size() - 1);
                        gstswitch.setEnabled(false);
                    } else {
                        Toast.makeText(getActivity(), "Wrong Values!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        return view;
    }

   private class GSTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ExcGst.GSTAdapter.VHItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.gst_cardview, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GST gst = gstList.get(position);
            ExcGst.GSTAdapter.VHItem item = (ExcGst.GSTAdapter.VHItem) holder;
            item.tvAmount.setText(String.valueOf(gst.getAmount()));
            item.tvGstPercentage.setText(String.valueOf(gst.getPercentage()));

            if (gstswitch.isChecked()) {
                item.tvigct.setText(String.valueOf(gst.t1()));
                item.tvsgct.setText("");
                item.tvcgct.setText("");
            } else {
                item.tvigct.setText("");
                item.tvsgct.setText(String.valueOf(gst.t1()));
                item.tvcgct.setText(String.valueOf(gst.t2()));
            }
            item.tvTotalAmount.setText(String.valueOf(gst.getTotalAmount()));
        }

        @Override
        public int getItemCount() {
            return gstList.size();
        }

        private class VHItem extends RecyclerView.ViewHolder {

            TextView tvAmount, tvGstPercentage, tvsgct,tvcgct,tvigct, tvTotalAmount;

            public VHItem(View itemView) {
                super(itemView);
                tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
                tvGstPercentage = (TextView) itemView.findViewById(R.id.tvGstPercent);
                tvsgct = (TextView) itemView.findViewById(R.id.tvsgct);
                tvcgct = (TextView) itemView.findViewById(R.id.tvcgst);
                tvigct = (TextView) itemView.findViewById(R.id.tvigst);
                tvTotalAmount = (TextView) itemView.findViewById(R.id.tvTotalAmount);
            }
        }
    }



}
