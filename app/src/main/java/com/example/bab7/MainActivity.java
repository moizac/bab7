package com.example.bab7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id, nim, nama, kelas;
    Button bt_insert, bt_update, bt_delete, bt_view;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        kelas = findViewById(R.id.kelas);

        bt_insert = findViewById(R.id.bt_insert);
        bt_update = findViewById(R.id.bt_insert);
        bt_delete = findViewById(R.id.bt_delete);
        bt_view = findViewById(R.id.bt_view);

        db = new DataHelper(this);

        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nimTXT = nim.getText().toString();
                String namaTXT = nama.getText().toString();
                String kelasTXT = kelas.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(idTXT, nimTXT, namaTXT, kelasTXT);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();
                String nimTXT = nim.getText().toString();
                String namaTXT = nama.getText().toString();
                String kelasTXT = kelas.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(idTXT, nimTXT, namaTXT, kelasTXT);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = id.getText().toString();

                Boolean deleteuserdata = db.deletedata(idTXT);
                if (deleteuserdata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id :" + res.getString(0)+"\n");
                    buffer.append("NIM :" + res.getString(0)+"\n");
                    buffer.append("Nama :" + res.getString(0)+"\n");
                    buffer.append("Kelas :" + res.getString(0)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}