package hcmute.edu.vn.mssv18110278.Users.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import hcmute.edu.vn.mssv18110278.Entity.User;


public class ButtonAddItemController implements View.OnClickListener {
    private Context appContext;

    public ButtonAddItemController(Context context) {
        appContext = context;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(appContext, AddItemActivity.class);
        appContext.startActivity(intent);

        
    }
}
