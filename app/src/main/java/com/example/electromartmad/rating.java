package com.example.electromartmad;

import android.database.Cursor;
import android.os.Bundle;

import com.example.layodyaelectrostore.DatabaseSQLite;
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

public class rating extends AppCompatActivity {

    DatabaseSQLite myDb;
    EditText editName,editEmail,editComments,editRating,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseSQLite(this);


        editName = (EditText)findViewById(R.id.editText_name);
        editEmail = (EditText)findViewById(R.id.editText_email);
        editComments = (EditText)findViewById(R.id.editText_comments);
        editRating = (EditText)findViewById(R.id.editText_rating);
        editTextId =  (EditText)findViewById(R.id.edit_id);
        btnAddData = (Button)findViewById(R.id.AddData);
        btnviewAll = (Button)findViewById(R.id.ViewAll);
        btnviewUpdate = (Button)findViewById(R.id.ViewUpdate);
        btnDelete = (Button)findViewById(R.id.Delete);
        AddData();
        ViewAll();
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

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(rating.this, "Data is deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(rating.this, "Data is not deleted", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }


    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                        String id = editTextId.getText().toString();
                        String name = editName.getText().toString();
                        String email = editEmail.getText().toString();
                        String comment = editComments.getText().toString();
                        String rating = editRating.getText().toString();

                        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email) || TextUtils.isEmpty(comment) || TextUtils.isEmpty(rating))
                        {
                            Toast.makeText(rating.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean isUpdate = myDb.updateData(id,name,email,comment,rating);
                        if(isUpdate == true) {
                            Toast.makeText(rating.this, "Data is updated", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(rating.this, "Data is not updated", Toast.LENGTH_LONG).show();
                        }


                    }
                }

        );

    }
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = editName.getText().toString();
                        String email = editEmail.getText().toString();
                        String comment = editComments.getText().toString();
                        String rating = editRating.getText().toString();

                        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(email) || TextUtils.isEmpty(comment) || TextUtils.isEmpty(rating))
                        {
                            Toast.makeText(rating.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean isInserted = myDb.insertData(name,email,comment,rating
                        );
                        if (isInserted = true)
                            Toast.makeText(rating.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(rating.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                    }
                }

        );
    }
    public void ViewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {

                            showMessage("Error","Nothing Found");

                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Email :"+ res.getString(2)+"\n");
                            buffer.append("Comments :"+ res.getString(3)+"\n");
                            buffer.append("Rating :"+ res.getString(4)+"\n\n");

                        }
                        showMessage("Data",buffer.toString());
                    }

                }
        );

    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
