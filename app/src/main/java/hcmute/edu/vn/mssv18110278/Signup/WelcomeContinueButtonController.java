package hcmute.edu.vn.mssv18110278.Signup;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import hcmute.edu.vn.mssv18110278.MainActivity;

public class WelcomeContinueButtonController implements View.OnClickListener {
    private Context appContext;
    private String access;

    public WelcomeContinueButtonController(Context context, String access) {
        appContext = context;
        this.access = access;
    }

    @Override
    public void onClick(View v) {
        if (access.equals("employeeAccess")) {
            ((WelcomeActivity) appContext).finish();
        } else {
            Intent intent = new Intent(appContext, MainActivity.class);
            appContext.startActivity(intent);
        }
    }
}
