package com.example.electromartmad;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cardpayment extends AppCompatActivity {

    Database myDb;
    EditText CRtype , CRname ,CRnumber , CRid;
    Button button_add;
    Button button_show;
    Button button_update;
    Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardpayment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb =new Database(this);

        CRname = (EditText) findViewById(R.id.CRname);
        CRtype = (EditText) findViewById(R.id.CRtype);
        CRnumber = (EditText) findViewById(R.id.CRnumber);
        CRid = (EditText) findViewById(R.id.CRid);

        button_add = (Button) findViewById(R.id.button_add);
        button_show = (Button) findViewById(R.id.button_show);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);

        addData();
        viewAll();
        UpdateData();
        DeleteData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void DeleteData()
    {
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(CRid.getText().toString());

                if (deletedRows > 0)
                {
                    Toast.makeText(cardpayment.this,"Data Deleted" , Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(cardpayment.this,"Data Not Deleted" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void UpdateData()
    {
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = CRid.getText().toString();
                String type = CRtype.getText().toString();
                String name = CRname.getText().toString();
                String number = CRnumber.getText().toString();

                if(TextUtils.isEmpty(type)||TextUtils.isEmpty(name) || TextUtils.isEmpty(number))
                {
                    Toast.makeText(cardpayment.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean isUpdate = myDb.updateData(id,type,name,number);

                if (isUpdate == true)
                {
                    Toast.makeText(cardpayment.this,"Update Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(cardpayment.this,"Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addData()
    {
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type = CRtype.getText().toString();
                String name = CRname.getText().toString();
                String number = CRnumber.getText().toString();

                if(TextUtils.isEmpty(type)||TextUtils.isEmpty(name) || TextUtils.isEmpty(number) )
                {
                    Toast.makeText(cardpayment.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = myDb.insertData(type,name,number);

                if(isInserted == true)
                {
                    Toast.makeText(cardpayment.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(cardpayment.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewAll()
    {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();

                if (res.getCount() == 0)
                {
                    showMessage("Error","No Data");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("ID :" +res.getString(0) + "\n");
                    buffer.append("Type :" +res.getString(1) + "\n");
                    buffer.append("Name :" +res.getString(2) + "\n");
                    buffer.append("Number :" +res.getString(3) + "\n");

                }

                showMessage("Your Card Details",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
