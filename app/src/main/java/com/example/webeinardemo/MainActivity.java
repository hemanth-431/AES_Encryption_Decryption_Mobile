package com.example.webeinardemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
DatabaseReference databaseReference;
EditText helo,hello1;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
helo=findViewById(R.id.editText);
        hello1=findViewById(R.id.editText2);
button=findViewById(R.id.button);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        databaseReference= FirebaseDatabase.getInstance().getReference("artist");
        final String pa=databaseReference.push().getKey().toString();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int result=(Integer.parseInt(helo.getText().toString())) * (Integer.parseInt(hello1.getText().toString()));
                Map<String,String> h=new HashMap<String,String>();
                h.put("Multiply",String.valueOf(result));
                databaseReference.child(pa).setValue(h);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
});
    }
}
