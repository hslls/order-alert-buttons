package lfa.mapleleaf.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import lfa.mapleleaf.R;
import lfa.mapleleaf.orderalertbuttons.AlertDialogBuilder;
import lfa.mapleleaf.orderalertbuttons.AlertDialogClickListener;
import lfa.mapleleaf.orderalertbuttons.AlertDialogParams;
import lfa.mapleleaf.orderalertbuttons.BUTTON_ALIGN;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.positive_left).setOnClickListener(this);
        findViewById(R.id.positive_right).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.positive_left:
                buttonClicked(BUTTON_ALIGN.POSITIVE_BUTTON_LEFT);
                break;
            case R.id.positive_right:
                buttonClicked(BUTTON_ALIGN.POSITIVE_BUTTON_RIGHT);
                break;
            default:
                break;
        }
    }

    private void buttonClicked(BUTTON_ALIGN align) {
        AlertDialogParams params = new AlertDialogParams();
        params.mTitle = "title";
        params.mMessage = "message";
        params.mPositiveText = "Ok";
        params.mNegativeText = "Cancel";
        params.mCancelable = true;
        params.mAlign = align;
        params.mClickListener = new AlertDialogClickListener() {
            @Override
            public void onPositiveClicked() {
                Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNegativeClicked() {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        };

        AlertDialog dialog = AlertDialogBuilder.createAlertDialog(this, params);
    }
}
