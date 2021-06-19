package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110278.Entity.User;
import hcmute.edu.vn.mssv18110278.R;

public class AddItemActivity extends AppCompatActivity {
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent();

        submit_btn = (Button)findViewById(R.id.employee_insert_item_submit_button);
        submit_btn.setOnClickListener(new AddSubmitController(this));

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
