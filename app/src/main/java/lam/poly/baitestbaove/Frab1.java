package lam.poly.baitestbaove;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lam.poly.baitestbaove.model.Sinhvien;
import lam.poly.baitestbaove.sqlite.SinhvienDAO;


public class Frab1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frab1_layout, container, false);
        Button btnadd = view.findViewById(R.id.btnadd);
        final EditText edtmasv, edtname, edtmmh, edtdiem;

        edtmasv = view.findViewById(R.id.edtmsv);
        edtname = view.findViewById(R.id.edtname);
        edtmmh = view.findViewById(R.id.edtmmh);
        edtdiem = view.findViewById(R.id.edtdiem);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtmasv.getText().toString().trim();
                String name = edtname.getText().toString().trim();
                String mmh = edtmmh.getText().toString().trim();
                String diem = edtdiem.getText().toString().trim();
                SinhvienDAO sinhvienDAO = new SinhvienDAO(getContext());
                if (id.isEmpty() || name.isEmpty() || mmh.isEmpty() || diem.isEmpty()) {
                    Toast.makeText(getContext(), "yêu cầu nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    Sinhvien sinhvien = new Sinhvien();
                    sinhvien.masv = id;
                    sinhvien.name = name;
                    sinhvien.mamh = mmh;
                    sinhvien.diem = diem;
                    long re = sinhvienDAO.insertSV(sinhvien);
                    if (re > 0) {
                        Toast.makeText(getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
                        edtmasv.setText("");
                        edtname.setText("");
                        edtmmh.setText("");
                        edtdiem.setText("");
                        ((MainActivity) getContext()).recreate();

                    } else {
                        Toast.makeText(getContext(), "thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

}
