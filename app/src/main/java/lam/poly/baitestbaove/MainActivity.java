package lam.poly.baitestbaove;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import lam.poly.baitestbaove.model.Sinhvien;
import lam.poly.baitestbaove.sqlite.SinhvienDAO;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    SinhvienDAO sinhvienDAO;
    List<Sinhvien> sinhvienList = new ArrayList<>();
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tbl);
        Tablayout1 tablayout1 = new Tablayout1(getSupportFragmentManager());
        viewPager = findViewById(R.id.vp);
        viewPager.setAdapter(tablayout1);
        tabLayout.setupWithViewPager(viewPager);
        sinhvienDAO = new SinhvienDAO(MainActivity.this);
        sinhvienList = sinhvienDAO.getAllSV();
    }

    // hàm để bắt sự kiện click khi chuyển tab
    public void addFrag(View view) {
        //neeys context khi bắt sự kiện vào nút button getall or nút chuyển
        if (view.getId() == R.id.btngetall) {
//        List<Sinhvien> list=new ArrayList<>();
//        for (int i=0;i<list.size();)ư
            TextView tv = findViewById(R.id.tvdiemtb);
            if (sinhvienList.size() == 0) {
                tv.setText("0");
            } else {
                double tong = 0;
                ;
                for (int i = 0; i < sinhvienList.size(); i++) {
                    tong += Double.parseDouble(sinhvienList.get(i).getDiem());
                }
                tv.setText(String.valueOf(tong / sinhvienList.size()));

                // thì viewpage sẽ nhận dữ liệu của tab 2 ...chỉ số của tab tính từ 0 nên tab 2 chỉ số là 1


            }
            viewPager.setCurrentItem(1);
        }

    }


}


