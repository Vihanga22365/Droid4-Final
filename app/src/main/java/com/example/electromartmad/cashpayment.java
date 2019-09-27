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

public class cashpayment extends AppCompatActivity {

    DatabaseHelp myDb;
    EditText Cname , Caddr ,Cpostal , Cphone , Cid;
    Button button_add;
    Button button_show;
    Button button_update;
    Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashpayment);

        myDb =new DatabaseHelp(this);

        Cname = (EditText) findViewById(R.id.Cname);
        Caddr = (EditText) findViewById(R.id.Caddr);
        Cpostal = (EditText) findViewById(R.id.Cpostal);
        Cphone = (EditText) findViewById(R.id.Cphone);
        Cid = (EditText) findViewById(R.id.Cid);

        button_add = (Button) findViewById(R.id.button_add);
        button_show = (Button) findViewById(R.id.button_show);
        button_update = (Button) findViewById(R.id.button_update);
        button_delete = (Button) findViewById(R.id.button_delete);

        addData();
        viewAll();
        UpdateData();
        DeleteData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Integer deletedRows = myDb.deleteData(Cid.getText().toString());

                if (deletedRows > 0)
                {
                    Toast.makeText(cashpayment.this,"Data Deleted" , Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(cashpayment.this,"Data Not Deleted" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void UpdateData()
    {
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Cid.getText().toString();
                String name = Cname.getText().toString();
                String addr = Caddr.getText().toString();
                String postal = Cpostal.getText().toString();
                String phone = Cphone.getText().toString();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(addr) || TextUtils.isEmpty(postal) || TextUtils.isEmpty(phone))
                {
                    Toast.makeText(cashpayment.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean isUpdate = myDb.updateData(id,name,addr,postal,phone);

                if (isUpdate == true)
                {
                    Toast.makeText(cashpayment.this,"Update Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(cashpayment.this,"Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addData()
    {
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = Cname.getText().toString();
                String addr = Caddr.getText().toString();
                String postal = Cpostal.getText().toString();
                String phone = Cphone.getText().toString();

                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(addr) || TextUtils.isEmpty(postal) || TextUtils.isEmpty(phone))
                {
                    Toast.makeText(cashpayment.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = myDb.insertData(name,addr,postal,phone);

                if(isInserted == true)
                {
                    Toast.makeText(cashpayment.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(cashpayment.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
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
                    buffer.append("Name :" +res.getString(1) + "\n");
                    buffer.append("Address :" +res.getString(2) + "\n");
                    buffer.append("Postal Code :" +res.getString(3) + "\n");
                    buffer.append("Phone Number :" +res.getString(4) + "\n\n");

                }

                showMessage("Your Credit Details",buffer.toString());
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
