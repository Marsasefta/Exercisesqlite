package com.example.exercisesqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMhs extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private com.example.exercisesqlite.DBHelper mydb ;

    EditText email;
    EditText phone;
    EditText nama;
    EditText al;

    int id_To_Update = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tambah_data);
        al = (EditText) findViewById(R.id.editTextalmt);
        nama = (EditText) findViewById(R.id.editTextName);
        phone = (EditText) findViewById(R.id.editTextelp);
        email = (EditText) findViewById(R.id.editTextmail);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value; rs.moveToFirst();

                String name =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String almt =
                        rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                String mail = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String telp = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_Telp));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);
                nama.setText((CharSequence)name);
                nama.setFocusable(false);
                nama.setClickable(false);

                email.setText((CharSequence)mail);
                email.setFocusable(false);
                email.setClickable(false);

                phone.setText((CharSequence)telp);
                phone.setFocusable(false);
                phone.setClickable(false);

                al.setText((CharSequence)almt);
                al.setFocusable(false);
                al.setClickable(false);
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.menu_display,menu);
            }
        }
        return true;
    }

    public void run (View view)
    {
        if (nama.getText().toString().equals("")||
                email.getText().toString().equals("")||
                al.getText().toString().equals("")||
                phone.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi semua !", Toast.LENGTH_LONG).show();
        }else {
            mydb.insertContact(nama.getText().toString(), email.getText().toString(),al.getText().toString(),
                    phone.getText().toString());
            Toast.makeText(getApplicationContext(), "Insert data berhasil",Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }
}