package hcmute.edu.vn.mssv18110278;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hcmute.edu.vn.mssv18110278.Users.Admin.AdminActivity;
import hcmute.edu.vn.mssv18110278.Users.User.HomeActivity;
import hcmute.edu.vn.mssv18110278.database.DatabaseSelectHelper;
import hcmute.edu.vn.mssv18110278.Entity.Roles;
import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.Validation.Validator;

public class LoginButtonController implements View.OnClickListener {
    private Context appContext;

    public LoginButtonController(Context context) {
        appContext = context;
    }

    @Override
    public void onClick(View v) {

        EditText username = ((MainActivity) appContext).findViewById(R.id.username_login);
        EditText password = ((MainActivity) appContext).findViewById(R.id.password_login);
        TextView error = ((MainActivity) appContext).findViewById(R.id.error_login);

        String parse_username = username.getText().toString();
        String parse_password = password.getText().toString();
        boolean Iserror =false;
        if (!Validator.validateUserName(parse_username)) {
            error.setText(R.string.name_error_login);
            Iserror = true;
        } else if (!Validator.validateEmail(parse_password)) {
            error.setText(R.string.password_error_login);
            Iserror = true;
        }
        if (!Iserror) {
            User user = DatabaseSelectHelper.getUserDetails(parse_username, appContext);
            if (user != null && Validator.validatePassword_login(user.getId(), parse_password, appContext)) {


                String roleName = DatabaseSelectHelper.getRoleName(user.getRole(), appContext);
                //Toast.makeText(appContext,"Thành công"+user.getUsername(),Toast.LENGTH_SHORT).show();
                if (roleName.equals(Roles.ADMIN.name())) {
                    Intent intent = new Intent(appContext,AdminActivity.class);
                    intent.putExtra("user", (Parcelable) user);
                    appContext.startActivity(intent);
                } else if (roleName.equals(Roles.CUSTOMER.name())) {
                    Intent intent = new Intent(appContext, HomeActivity.class);
                    intent.putExtra("user", (Parcelable) user);
                    appContext.startActivity(intent);
                }
            } else {
                error.setText(R.string.login_error);
            }
        }




    }
}
