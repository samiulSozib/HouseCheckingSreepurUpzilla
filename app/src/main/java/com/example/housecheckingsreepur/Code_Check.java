package com.example.housecheckingsreepur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Code_Check extends AppCompatActivity {

    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
    DatabaseReference databaseReference,databaseReference2,databaseReference3,databaseReference4,databaseReference5,databaseReference6,databaseReference7,databaseReference8;
    EditText codeText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code__check);

        textView=findViewById(R.id.text_id);
        codeText=findViewById(R.id.code_check_id);
        button=findViewById(R.id.code_check_button);
        textView2=findViewById(R.id.text_id_2);
        textView3=findViewById(R.id.text_id_3);
        textView4=findViewById(R.id.text_id_4);
        textView5=findViewById(R.id.text_id_5);
        textView6=findViewById(R.id.text_id_6);
        textView7=findViewById(R.id.text_id_7);
        textView8=findViewById(R.id.text_id_8);

        databaseReference= FirebaseDatabase.getInstance().getReference("Goyespur");
        databaseReference2= FirebaseDatabase.getInstance().getReference("Amolsar");
        databaseReference3= FirebaseDatabase.getInstance().getReference("Sreekhol");
        databaseReference4= FirebaseDatabase.getInstance().getReference("Sreepur");
        databaseReference5= FirebaseDatabase.getInstance().getReference("Dariapur");
        databaseReference6= FirebaseDatabase.getInstance().getReference("Kadirpara");
        databaseReference7= FirebaseDatabase.getInstance().getReference("Sobdalpur");
        databaseReference8= FirebaseDatabase.getInstance().getReference("Nakol");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView2.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView3.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView4.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView5.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView6.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView7.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2:dataSnapshot.getChildren()){
                    Code_Class code_class=dataSnapshot2.getValue(Code_Class.class);
                    textView8.setText(code_class.code);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String union_code=codeText.getText().toString().trim();
                String union_goyaspur=textView.getText().toString().trim();
                String union_amolsar=textView2.getText().toString().trim();
                String union_sreekol=textView3.getText().toString().trim();
                String union_sreepur=textView4.getText().toString().trim();
                String union_dariapur=textView5.getText().toString().trim();
                String union_kadirpara=textView6.getText().toString().trim();
                String union_sobdalpur=textView7.getText().toString().trim();
                String union_nakol=textView8.getText().toString().trim();


                if (union_code.equals(union_goyaspur)){
                    startActivity(new Intent(getApplicationContext(),Goysespur.class));
                    finish();
                }else if(union_code.equals(union_amolsar)){
                    startActivity(new Intent(getApplicationContext(),Amolsar_Union.class));
                    finish();
                }else if(union_code.equals(union_sreekol)){
                    startActivity(new Intent(getApplicationContext(),Sreekol_Union.class));
                    finish();
                }else if(union_code.equals(union_sreepur)){
                    startActivity(new Intent(getApplicationContext(),Sreepur_Union.class));
                    finish();
                }
                else if(union_code.equals(union_dariapur)){
                    startActivity(new Intent(getApplicationContext(),Dariapur_Union.class));
                    finish();
                }
                else if(union_code.equals(union_kadirpara)){
                    startActivity(new Intent(getApplicationContext(),Kadirpara_Union.class));
                    finish();
                }
                else if(union_code.equals(union_sobdalpur)){
                    startActivity(new Intent(getApplicationContext(),Sobdalpur_Union.class));
                    finish();
                }
                else if(union_code.equals(union_nakol)){
                    startActivity(new Intent(getApplicationContext(),Nakol_Union.class));
                    finish();
                }else if(union_code.equals("")){
                    Toast.makeText(getApplicationContext(),"Your union code empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Your union code wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
