package hcmute.edu.vn.mssv18110278.Signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hcmute.edu.vn.mssv18110278.MainActivity;
import hcmute.edu.vn.mssv18110278.R;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView name = findViewById(R.id.welcome_name);
        TextView role = findViewById(R.id.welcome_role);


        Intent intent = getIntent();
        Button continueButton = findViewById(R.id.welcome_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        String role_name = intent.getStringExtra("role");
        name.setText(intent.getStringExtra("name"));
        role.setText(role_name);
    }
}
