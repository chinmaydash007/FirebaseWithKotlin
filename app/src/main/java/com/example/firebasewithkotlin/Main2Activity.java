package com.example.firebasewithkotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasewithkotlin.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {
EditText name,age,profession;
Button write;
RecyclerView recyclerView;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.name_edittext);
        age=findViewById(R.id.age_editext);
        profession=findViewById(R.id.profession_editext);
        write=findViewById(R.id.writeBtn);
        recyclerView=findViewById(R.id.recyclerView);

        write.setOnClickListener(this::writeData);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");


    }

    private void writeData(View view) {
        String user_name=name.getText().toString();
        String user_age=age.getText().toString();
        String user_profession=profession.getText().toString();
        if(user_age.equals("")|| user_name.equals("") || user_profession.equals("")){
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            String uid=databaseReference.push().getKey();
            User user=new User(user_age,user_name,user_profession,uid);
            databaseReference.child(uid).setValue(user);

        }
    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}
