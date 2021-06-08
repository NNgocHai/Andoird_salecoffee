package hcmute.edu.vn.mssv18110278.Signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import hcmute.edu.vn.mssv18110278.R;
import hcmute.edu.vn.mssv18110278.Entity.Roles;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        TextView name = findViewById(R.id.welcome_name);
        TextView role = findViewById(R.id.welcome_role);


        Intent intent = getIntent();
        String access = intent.getStringExtra("access");
        Button continueButton = findViewById(R.id.welcome_continue_button);
        continueButton.setOnClickListener(new WelcomeContinueButtonController(this, access));

        String role_name = intent.getStringExtra("role");
        if (role_name.equals(Roles.CUSTOMER.name())) {
            role.setText(getResources().getString(R.string.welcome_role));
        }

        name.setText(intent.getStringExtra("name"));
        role.setText(role_name);
    }
}
