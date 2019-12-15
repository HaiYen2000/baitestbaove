package lam.poly.baitestbaove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class dangnhap extends AppCompatActivity {
EditText edtten,edtmatkhau;
Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edtten=findViewById(R.id.edtten);
        edtmatkhau=findViewById(R.id.edtmk);
        btnlogin=findViewById(R.id.btnlogin);
       btnlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String ten=edtten.getText().toString().trim();
               String mk=edtmatkhau.getText().toString().trim();
               if(ten.isEmpty()||mk.isEmpty()){
                   Toast.makeText(dangnhap.this,"yêu cầu nhập thông tin",Toast.LENGTH_SHORT).show();
               }
              else if (!ten.equals("tunvph08768")&& !mk.equals("08768")){
                   Toast.makeText(dangnhap.this,"tài khoản hoặc mật khẩu sai",Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(dangnhap.this,"đăng nhập thành công",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(dangnhap.this,MainActivity.class);
                   startActivity(intent);
               }
           }
       });
    }
}
