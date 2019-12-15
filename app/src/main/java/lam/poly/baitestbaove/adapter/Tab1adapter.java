package lam.poly.baitestbaove.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lam.poly.baitestbaove.R;
import lam.poly.baitestbaove.model.Sinhvien;
import lam.poly.baitestbaove.sqlite.SinhvienDAO;


public class Tab1adapter extends BaseAdapter {
    private Context context;
    private List<Sinhvien> list;

    public Tab1adapter(Context context, List<Sinhvien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitemsinhvien, viewGroup, false);
        TextView id = v.findViewById(R.id.tvmasv);
        TextView name = v.findViewById(R.id.tvname);
        TextView mmh = v.findViewById(R.id.tvmmh);
//        TextView diem = v.findViewById(R.id.tvdiem);
//        ImageView imgxoa = v.findViewById(R.id.imgdelete);
//        ImageView imgupdate = v.findViewById(R.id.imgupdate);


        final Sinhvien sinhvien = list.get(i);
        id.setText(sinhvien.masv);
        name.setText(sinhvien.name);
        mmh.setText(sinhvien.mamh);
        diem.setText(sinhvien.diem);
        imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                final EditText edt1 = dialog.findViewById(R.id.edtupdatename);
                final EditText edt2 = dialog.findViewById(R.id.edtupdatemmh);
                final EditText edt3 = dialog.findViewById(R.id.edtupdatediem);
                Button btndong = dialog.findViewById(R.id.btndong);
                Button btnupdate = dialog.findViewById(R.id.btnupdate);
                final String upten = edt1.getText().toString().trim();
                final String upmmh = edt2.getText().toString().trim();
                final String updiem = edt3.getText().toString().trim();
                btndong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "thoát dialog", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String upten = edt1.getText().toString().trim();
                        String upmmh = edt2.getText().toString().trim();
                        String updiem = edt3.getText().toString().trim();
                        if (upten.isEmpty() || upmmh.isEmpty() || updiem.isEmpty()) {
                            Toast.makeText(context, "không để rỗng", Toast.LENGTH_SHORT).show();
                        } else {
                            Sinhvien sinhvien1 = (Sinhvien) getItem(i);
                            sinhvien1.name = upten;
                            sinhvien1.mamh = upmmh;
                            sinhvien1.diem = updiem;
                            SinhvienDAO sinhvienDAO = new SinhvienDAO(context);
                            sinhvienDAO.sua(sinhvien);
                            Toast.makeText(context, "sửa thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }

        });
        imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_xoa);
                Button btncancel = dialog.findViewById(R.id.btncancel);
                Button btnok = dialog.findViewById(R.id.btnok);
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SinhvienDAO sinhvienDAO = new SinhvienDAO(context);
                        Sinhvien sinhvien = (Sinhvien) getItem(i);
                        sinhvienDAO.dellsv(sinhvien.getMasv());
                        list.remove(sinhvien);
                        notifyDataSetChanged();
                        Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return v;
    }
}
