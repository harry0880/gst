package nic.gst;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aayush on 7/24/2017.
 */

public class ExcGst extends Fragment {

    List<GST> gstList;
    GSTAdapter adapter;
    GstDatatype FinalBill;
    Switch gstswitch;
    EditText EtAmount;

    TextView et_final_Amount,et_final_sgct,et_final_cgst,et_final_igst,et_final_totalAmt;
    FloatingActionButton fab ;

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
        et_final_Amount=(TextView) view.findViewById(R.id.tvAmount);
        et_final_cgst=(TextView) view.findViewById(R.id.tvcgst) ;
        et_final_sgct=(TextView) view.findViewById(R.id.tvsgct);
        et_final_igst=(TextView) view.findViewById(R.id.tvigst);
        et_final_totalAmt=(TextView) view.findViewById(R.id.tvTotalAmount);
        fab=     (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EtAmount.setText("");
                gstswitch.setEnabled(true);
                gstList.clear();
                adapter.notifyDataSetChanged();
                et_final_Amount.setText("");
                et_final_cgst.setText("");
                et_final_sgct.setText("");
                et_final_totalAmt.setText("");
                et_final_igst.setText("");
                gstswitch.setEnabled(true);
                gstswitch.setChecked(false);
                FinalBill.clear();
                Snackbar.make(view, "All Data cleared", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        gstList = new ArrayList<>();
        adapter = new GSTAdapter();
        recyclerView.setAdapter(adapter);
        FinalBill = new GstDatatype();


        Button btnAdd = (Button) view.findViewById(R.id.btAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EtAmount = (EditText) view.findViewById(R.id.EtAmount);
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

                        // adapter.notifyItemInserted(gstList.size() - 1);


                        adapter.notifyDataSetChanged();
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



                        GstDatatype tempcalc = new GstDatatype(amount,sgst,Cgst,totalAmount);
                        FinalBill= FinalBill.add(tempcalc,false);

                        gstList.add(new GST(gstList.size(), percentage + "%", amount, sgst, Cgst, totalAmount));
                        adapter.notifyDataSetChanged();
                        // adapter.notifyItemInserted(gstList.size() - 1);
                        gstswitch.setEnabled(false);
                    } else {
                        Toast.makeText(getActivity(), "Wrong Values!", Toast.LENGTH_SHORT).show();

                    }
                }
                EtAmount.setText("");
                if(!gstswitch.isChecked()) {
                    et_final_Amount.setText(FinalBill.amt + "");
                    et_final_cgst.setText(FinalBill.t1 + "");
                    et_final_sgct.setText(FinalBill.t2 + "");
                    et_final_totalAmt.setText(FinalBill.total+"");
                }
                else {
                    et_final_Amount.setText(FinalBill.amt + "");
                    et_final_igst.setText(FinalBill.t1 + "");
                    et_final_totalAmt.setText(FinalBill.total+"");
                }
            }
        });

        return view;
    }


    private class GSTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VHItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.gst_cardview, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            GST gst = gstList.get((gstList.size()- position)-1);
            final VHItem item = (VHItem) holder;
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
            item.viewOptionsMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopupMenu popup = new PopupMenu(getContext(), item.viewOptionsMenu);
                    popup.getMenuInflater().inflate(R.menu.menu_view_student, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item1) {
                            int id = item1.getItemId();
                            GST gst = gstList.get(position);
                            switch (id) {
                                case R.id.editOption:
                                    EtAmount.setText(String.valueOf(gst.getTotalAmount()));
                                    if (gstswitch.isChecked()) {
                                        GstDatatype temp=new GstDatatype(gst.getAmount(),gst.t1(),gst.t2(),gst.getTotalAmount());
                                        FinalBill= FinalBill.sub(temp,true);
                                    } else {
                                        GstDatatype temp=new GstDatatype(gst.getAmount(),gst.t1(),gst.getTotalAmount());
                                        FinalBill= FinalBill.sub(temp,true);
                                    }
                                    gstList.remove(position);
                                    adapter.notifyDataSetChanged();
                                    break;

                                case R.id.removeOption:

                                    if (gstswitch.isChecked()) {
                                        GstDatatype temp=new GstDatatype(gst.getAmount(),gst.t1(),gst.t2(),gst.getTotalAmount());
                                        FinalBill= FinalBill.sub(temp,true);
                                        if(!gstswitch.isChecked()) {
                                            et_final_Amount.setText(FinalBill.amt + "");
                                            et_final_cgst.setText(FinalBill.t1 + "");
                                            et_final_sgct.setText(FinalBill.t2 + "");
                                            et_final_totalAmt.setText(FinalBill.total+"");
                                        }
                                        else {
                                            et_final_Amount.setText(FinalBill.amt + "");
                                            et_final_igst.setText(FinalBill.t1 + "");
                                            et_final_totalAmt.setText(FinalBill.total+"");
                                        }

                                    } else {
                                        GstDatatype temp=new GstDatatype(gst.getAmount(),gst.t1(),gst.getTotalAmount());
                                        FinalBill= FinalBill.sub(temp,true);
                                    }
                                    gstList.remove(position);
                                    adapter.notifyDataSetChanged();
                                    break;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });


        }

        @Override
        public int getItemCount() {
            return gstList.size();
        }

        private class VHItem extends RecyclerView.ViewHolder {

            TextView tvAmount, tvGstPercentage, tvsgct,tvcgct,tvigct, tvTotalAmount;
            ImageButton viewOptionsMenu;

            public VHItem(View itemView) {
                super(itemView);
                tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
                tvGstPercentage = (TextView) itemView.findViewById(R.id.tvGstPercent);
                tvsgct = (TextView) itemView.findViewById(R.id.tvsgct);
                tvcgct = (TextView) itemView.findViewById(R.id.tvcgst);
                tvigct = (TextView) itemView.findViewById(R.id.tvigst);
                tvTotalAmount = (TextView) itemView.findViewById(R.id.tvTotalAmount);
                viewOptionsMenu=(ImageButton) itemView.findViewById(R.id.viewOptionMenu);
            }
        }
    }
}
