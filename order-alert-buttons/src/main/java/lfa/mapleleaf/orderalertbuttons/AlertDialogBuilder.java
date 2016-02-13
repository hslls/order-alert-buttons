package lfa.mapleleaf.orderalertbuttons;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;

/**
 * mapleleaf
 *
 * lifengang@gmail.com
 */
public final class AlertDialogBuilder {

    private AlertDialogBuilder() {

    }

    public static AlertDialog createAlertDialog(Context context, final AlertDialogParams params) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(params.mTitle)) {
            builder.setTitle(params.mTitle);
        }
        if (!TextUtils.isEmpty(params.mMessage)) {
            builder.setMessage(params.mMessage);
        }
        builder.setCancelable(params.mCancelable);

        builder.setPositiveButton(params.mPositiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (params.mClickListener != null) {
                    params.mClickListener.onPositiveClicked();
                }
            }
        });
        builder.setNegativeButton(params.mNegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (params.mClickListener != null) {
                    params.mClickListener.onNegativeClicked();
                }
            }
        });
        final AlertDialog alertDialog = builder.show();

        if ((params.mAlign == BUTTON_ALIGN.POSITIVE_BUTTON_LEFT) ||
                (params.mAlign == BUTTON_ALIGN.POSITIVE_BUTTON_RIGHT)) {
            Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            if (positiveButton != null) {
                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                if (negativeButton != null) {
                    ViewParent parentView = positiveButton.getParent();
                    if (parentView instanceof ViewGroup) {
                        ViewGroup buttonPanelContainer = (ViewGroup) positiveButton.getParent();
                        int positiveButtonIndex = buttonPanelContainer.indexOfChild(positiveButton);
                        int negativeButtonIndex = buttonPanelContainer.indexOfChild(negativeButton);
                        boolean revert = false;
                        if (positiveButtonIndex > negativeButtonIndex) {
                            if (params.mAlign == BUTTON_ALIGN.POSITIVE_BUTTON_LEFT) {
                                revert = true;
                            }
                        } else {
                            if (params.mAlign == BUTTON_ALIGN.POSITIVE_BUTTON_RIGHT) {
                                revert = true;
                            }
                        }
                        if (revert) {
                            switchAlertDialogButton(alertDialog, positiveButton, negativeButton,
                                    params);
                        }
                    }
                }
            }
        }

        return alertDialog;
    }


    private static void switchAlertDialogButton(final AlertDialog alertDialog, Button positiveButton,
                                                Button negativeButton, final AlertDialogParams params) {
        positiveButton.setText(params.mNegativeText);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params.mClickListener != null) {
                    params.mClickListener.onNegativeClicked();
                }
                alertDialog.dismiss();
            }
        });

        negativeButton.setText(params.mPositiveText);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params.mClickListener != null) {
                    params.mClickListener.onPositiveClicked();
                }
                alertDialog.dismiss();
            }
        });
    }

}
