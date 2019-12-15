package lam.poly.baitestbaove;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import lam.poly.baitestbaove.adapter.Tab1adapter;
import lam.poly.baitestbaove.model.Sinhvien;
import lam.poly.baitestbaove.sqlite.SinhvienDAO;

public class Frag2 extends Fragment {
    RecyclerView recyclerView;
    List<Sinhvien> sinhvienList = new ArrayList<>();
    SinhvienDAO sinhvienDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.row, container, false);
        final TextView tvdiemtb = view.findViewById(R.id.tvdiemtb);
        ListView lv = view.findViewById(R.id.lv);


        sinhvienDAO = new SinhvienDAO(getContext());
        sinhvienList = sinhvienDAO.getAllSV();
//                recyclerView=view.findViewById(R.id.car);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        Myadapter myadapter=new Myadapter(getContext(),sinhvienList);
//        recyclerView.setAdapter(myadapter);
        Tab1adapter adapter = new Tab1adapter(getContext(), sinhvienList);
        lv.setAdapter(adapter);
        return view;
    }
}
