package com.example.webeinardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class encryptdecrp extends AppCompatActivity {
EditText key,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryptdecrp);
        key=findViewById(R.id.et_text_key);
        message=findViewById(R.id.et_value);


    }
    public void encrypt(View view){
        try {
            String encry= AESCrypt.encrypt(key.getText().toString(),message.getText().toString());
            ClipboardManager clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("label",encry);
            clipboardManager.setPrimaryClip(clipData);
            key.setText("");
            message.setText("");
            Toast.makeText(encryptdecrp.this,encry,Toast.LENGTH_LONG).show();

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
    public void decrypt(View view){
        try {
            String encry= AESCrypt.decrypt(key.getText().toString(),message.getText().toString());
            ClipboardManager clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("label",encry);
            clipboardManager.setPrimaryClip(clipData);
            key.setText("");
            message.setText("");
            Toast.makeText(encryptdecrp.this,encry,Toast.LENGTH_LONG).show();

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }
}
