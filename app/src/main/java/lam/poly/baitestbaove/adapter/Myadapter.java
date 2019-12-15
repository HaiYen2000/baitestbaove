package lam.poly.baitestbaove.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import lam.poly.baitestbaove.R;
import lam.poly.baitestbaove.model.Sinhvien;
import lam.poly.baitestbaove.sqlite.SinhvienDAO;


public class Myadapter extends RecyclerView.Adapter<Myadapter.Viewhodel> {
    Context context;
    List<Sinhvien> list;

    public Myadapter(Context context, List<Sinhvien> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public Myadapter.Viewhodel onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        ImageView imgxoa = view.findViewById(R.id.imgxoa);
        ImageView imgupdate = view.findViewById(R.id.imgsua);
        imgupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                final EditText edt1 = dialog.findViewById(R.id.edtupdatename);
//                final EditText edt2=dialog.findViewById(R.id.edtupdatedaichi);
                Button btndong = dialog.findViewById(R.id.btndong);
                Button btnupdate = dialog.findViewById(R.id.btnupdate);

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
//                        String updc=edt2.getText().toString().trim();
                        if (upten.isEmpty()) {
                            Toast.makeText(context, "không để rỗng", Toast.LENGTH_SHORT).show();
                        } else {
                            Sinhvien sinhvien1 = list.get(viewType);
                            sinhvien1.masv = list.get(viewType).getMasv();
                            sinhvien1.name = upten;
//                            sinhvien1.address = updc;
                            SinhvienDAO sinhvienDAO = new SinhvienDAO(context);
                            sinhvienDAO.sua(sinhvien1);
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
                        Sinhvien sinhvien = list.get(viewType);
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
        return new Myadapter.Viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.Viewhodel holder, int position) {
        holder.tv1.setText(list.get(position).getMasv());
        holder.tv2.setText(list.get(position).getName());
//        holder.tv3.setText(list.get(position).getAddress());
    }

    //tính số lượng
    @Override
    public int getItemCount() {
        return list.size();
    }
    //gán dữ liệu


    public class Viewhodel extends RecyclerView.ViewHolder {

        public TextView tv1, tv2, tv3;
        public ImageView imgxoa, imgsua;

        public Viewhodel(View view) {
            super(view);
            tv1 = view.findViewById(R.id.tvmasv);
            tv2 = view.findViewById(R.id.tvname);
            tv3 = view.findViewById(R.id.tvdiachi);
            imgxoa = view.findViewById(R.id.imgxoa);
            imgsua = view.findViewById(R.id.imgsua);

        }
    }
}
