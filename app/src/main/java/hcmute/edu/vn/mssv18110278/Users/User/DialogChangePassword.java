package hcmute.edu.vn.mssv18110278.Users.User;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import hcmute.edu.vn.mssv18110278.R;

public class DialogChangePassword extends AppCompatDialogFragment {
    private EditText edit_passwordnew1;
    private EditText edit_passwordnew2;
    private EditText edit_password;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        builder.setView(view)
                .setTitle("")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String parse_edit_password = edit_password.getText().toString();
                        String parse_edit_passwordnew1 = edit_passwordnew1.getText().toString();
                        String parse_edit_passwordnew2 = edit_passwordnew2.getText().toString();
                        listener.applyTexts(parse_edit_password, parse_edit_passwordnew1,parse_edit_passwordnew2);
                    }
                });

        edit_password = view.findViewById(R.id.edit_password);
        edit_passwordnew1 = view.findViewById(R.id.edit_passwordnew1);
        edit_passwordnew2 = view.findViewById(R.id.edit_passwordnew2);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applyTexts(String parse_edit_password, String parse_edit_passwordnew1,String parse_edit_passwordnew2);
    }
}
