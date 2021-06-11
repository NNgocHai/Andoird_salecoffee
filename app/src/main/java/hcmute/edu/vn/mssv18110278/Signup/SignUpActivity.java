package hcmute.edu.vn.mssv18110278.Signup;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;

public class SignUpActivity  extends Activity {
    Button back_to_login,take_photo_btn,sign_up_btn;
    ImageView image_Profile;
    public static final int CAMERA_PERM_CODE=101;
    public static final int CAMERA_REQUEST_CODE=102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Intent intent = getIntent();
        String role = intent.getStringExtra("role");
        String access = intent.getStringExtra("access");


        TextView sign_up = findViewById(R.id.sign_up);
        back_to_login=(Button)findViewById(R.id.back_login_btn);
        take_photo_btn=(Button)findViewById(R.id.take_photo_btn);
        image_Profile=(ImageView)findViewById(R.id.image_sign_up);
        sign_up_btn=(Button)findViewById(R.id.sign_up_btn);


        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        take_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }
        });
        sign_up.setText(intent.getStringExtra("sign_up_display"));

        sign_up_btn.setOnClickListener(new SignUpButtonController(this, role, access));

    }
    private void askCameraPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_PERM_CODE);
        }
        else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==CAMERA_PERM_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                openCamera();
            else
            {
                Toast.makeText(this,"Camera Permission is Required to Use camera",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openCamera()
    {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMERA_REQUEST_CODE)
        {
            Bitmap image=(Bitmap) data.getExtras().get("data");
            image_Profile.setImageBitmap(image);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        if (intent.getStringExtra("backPress").equals("no"))
        {
            return;
        } else {
            super.onBackPressed();
        }
    }


}
