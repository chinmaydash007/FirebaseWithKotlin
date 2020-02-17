package com.example.firebasewithkotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasewithkotlin.Adapter.UserAdapter;
import com.example.firebasewithkotlin.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    EditText name, number;
    Button write_btn;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    private static final String TAG = "mytag";
    List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name_edittext);
        number = findViewById(R.id.number_edittext);
        write_btn = findViewById(R.id.writeBtn);
        recyclerView = findViewById(R.id.recyclerView);

        userList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        write_btn.setOnClickListener(this::writeData);
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                user.setUid(dataSnapshot.getKey());
                userList.add(user);
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                user.setUid(dataSnapshot.getKey());
                userList.remove(user);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    private void writeData(View view) {
        String user_name = name.getText().toString();
        String user_number = number.getText().toString();
        User user = new User(user_name, user_number);


        databaseReference.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Main2Activity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(childEventListener);
    }
}
