package lam.poly.baitestbaove.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentSql extends SQLiteOpenHelper {
    public static final String T_NAME = "Student";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static final String C_MMH = "MHH";
    public static final String C_DIEM = "DIEM";
    public static final String CREATE_TABLE = "CREATE TABLE " + T_NAME + " (" + C_ID + " VARCHAR PRIMARY KEY," + C_NAME + " NVARCHAR," + C_MMH + " NVARCHAR," + C_DIEM + " NVARCHAR)";

    public StudentSql(Context context) {
        super(context, "Student.dp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
