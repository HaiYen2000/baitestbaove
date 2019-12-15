package lam.poly.baitestbaove.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import lam.poly.baitestbaove.model.Sinhvien;

import static lam.poly.baitestbaove.sqlite.StudentSql.C_DIEM;
import static lam.poly.baitestbaove.sqlite.StudentSql.C_ID;
import static lam.poly.baitestbaove.sqlite.StudentSql.C_MMH;
import static lam.poly.baitestbaove.sqlite.StudentSql.C_NAME;
import static lam.poly.baitestbaove.sqlite.StudentSql.T_NAME;


public class SinhvienDAO {
    private StudentSql studentSql;

    public SinhvienDAO(Context context) {

        studentSql = new StudentSql(context);

    }

    public long insertSV(Sinhvien sinhvien) {
        SQLiteDatabase sqLiteDatabase = studentSql.getWritableDatabase();

        // b2 : ghep cap du lieu
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_ID, sinhvien.masv);
        contentValues.put(C_NAME, sinhvien.name);
        contentValues.put(C_MMH, sinhvien.mamh);
        contentValues.put(C_DIEM, sinhvien.diem);
        // b3 : su dung cau lenh insert
        long result = sqLiteDatabase.insert(T_NAME, null, contentValues);


        // b4 : dong ket noi
        sqLiteDatabase.close();

        return result;


    }

    public int dellsv(String ID) {
        SQLiteDatabase sqLiteDatabase = studentSql.getWritableDatabase();

        int result = sqLiteDatabase.delete(T_NAME, C_ID + "=?", new String[]{ID});

        sqLiteDatabase.close();

        return result;

    }

    public List<Sinhvien> getAllSV() {

        List<Sinhvien> sinhvienList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = studentSql.getWritableDatabase();

        String select = "SELECT * FROM " + T_NAME;

        // b3 : su dung cau lenh rawQuery
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Sinhvien sv = new Sinhvien();
                sv.masv = cursor.getString(0);
                sv.name = cursor.getString(1);
                sv.mamh = cursor.getString(2);
                sv.diem = cursor.getString(3);

                sinhvienList.add(sv);

            } while (cursor.moveToNext());

            // dong ket noi con tro
            cursor.close();
        }
        // dong ket noi DB
        sqLiteDatabase.close();
        return sinhvienList;

    }

    public long sua(Sinhvien sinhvien) {
        SQLiteDatabase sqLiteDatabase = studentSql.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, sinhvien.name);
        contentValues.put(C_MMH, sinhvien.mamh);
        contentValues.put(C_DIEM, sinhvien.diem);
        long result = sqLiteDatabase.update(T_NAME, contentValues, C_ID + "=?", new String[]{sinhvien.masv});
        sqLiteDatabase.close();
        return result;
    }
}

