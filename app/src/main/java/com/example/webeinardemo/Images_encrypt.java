package com.example.webeinardemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.webeinardemo.Utils.MyEncrypter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class Images_encrypt extends AppCompatActivity {
//private final static int READ_WRITE_BLOCK_BUFFER=1024;
//    private final static String ALGO_IMAGE_ENCRYPTOR="AES/CBC/PKCS5Padding";
//    private final static String ALGO_SECRET_KEY="AES";
    private static final String FILE_NAMME_ENC="hemanth_enc";
    private static final String FILE_NAME_DEC = "hemanth_dec";
    Button enc,dec;
    File myDir;
    String my_key="6uevqFisWJpyglKS";
    String my_spec_key="ddCLM4SQZkqCZgth";

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_encrypt);
        enc=findViewById(R.id.btn_encrypt);
        myDir=new File(Environment.getExternalStorageDirectory().toString()+"/saved_images");  //storage/emulated/0/
        Toast.makeText(Images_encrypt.this,Environment.getExternalStorageDirectory().getAbsolutePath().toString(),Toast.LENGTH_LONG).show();
        dec=findViewById(R.id.btn_decrypt);
        imageView=findViewById(R.id.imageView);
        Dexter.withActivity(this).withPermissions(new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        }).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
dec.setEnabled(true);
enc.setEnabled(true);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                Toast.makeText(Images_encrypt.this,"You must enable permission",Toast.LENGTH_LONG).show();
            }
        }).check();


        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outPutfile=new File(myDir,FILE_NAME_DEC);
                File encFile=new File(myDir,FILE_NAMME_ENC);
try {
    MyEncrypter.decryptToFile(my_key,my_spec_key,new FileInputStream(encFile),new FileOutputStream(outPutfile));
    imageView.setImageURI(Uri.fromFile(outPutfile));
    outPutfile.delete();
    Toast.makeText(Images_encrypt.this,"Decrypt",Toast.LENGTH_LONG).show();
}catch (Exception e){

}
            }
        });

enc.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Drawable drawable= ContextCompat.getDrawable(Images_encrypt.this,R.drawable.hemanthpic);
        BitmapDrawable bitmapDrawable=(BitmapDrawable) drawable;
        Bitmap bitmap=bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        InputStream is=new ByteArrayInputStream(stream.toByteArray());
        File outputfileenc=new File(myDir,FILE_NAMME_ENC);
        try{
            MyEncrypter.encryptToFile(my_key,my_spec_key,is,new FileOutputStream(outputfileenc));
            Toast.makeText(Images_encrypt.this,"Encripted!",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
});
    }
}
