package hcmute.edu.vn.mssv18110278.Signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;

import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.database.DatabaseInsertHelper;
import hcmute.edu.vn.mssv18110278.Validation.Validator;

public class SignUpButtonController implements View.OnClickListener {
    private Context appContext;
    private String role;
    private String access;

    public SignUpButtonController(Context context, String role, String access) {
        appContext = context;
        this.role = role;
        this.access = access;
    }

    @Override
    public void onClick(View view) {
        boolean Iserror = false;

        EditText username = ((SignUpActivity)appContext).findViewById(R.id.username);
        EditText email = ((SignUpActivity)appContext).findViewById(R.id.email);
        EditText password = ((SignUpActivity)appContext).findViewById(R.id.password);
        EditText confirm_password = ((SignUpActivity)appContext).findViewById(R.id.confirm_password);
        TextView error = ((SignUpActivity) appContext).findViewById(R.id.error);
        ImageView image =((SignUpActivity) appContext).findViewById(R.id.image_sign_up);


        String parse_username = username.getText().toString();
        String parse_email = email.getText().toString();
        String parse_password = password.getText().toString();
        String parse_confirm_password = confirm_password.getText().toString();



        if (!Validator.validateUserName(parse_username)) {
            error.setText(R.string.name_error);
            Iserror = true;
        } else if (!Validator.validateEmail(parse_email)) {
            error.setText(R.string.email_error);
            Iserror = true;
        } else if (!Validator.validatePassword(parse_password)) {
            error.setText(R.string.password_error);
            Iserror = true;
        } else if (!Validator.validatePassword2(parse_password,parse_confirm_password)) {
            error.setText(R.string.confirm_password_error);
            Iserror = true;
        }
        if (!Iserror) {
            int roleId = DatabaseInsertHelper.insertRole(role, appContext);
            DatabaseInsertHelper.insertNewUser(parse_username, parse_email, parse_password, roleId,ConverttoArrayByte(image), appContext);

        }

            Intent intent = new Intent(appContext, WelcomeActivity.class);
            intent.putExtra("name", parse_username);
            intent.putExtra("role", role);
            intent.putExtra("access", access);
            appContext.startActivity(intent);
            ((SignUpActivity) appContext).finish();

    }
    public byte[] ConverttoArrayByte(ImageView img)
    {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
