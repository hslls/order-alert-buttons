# order-alert-buttons
Custom the order of positive and negative buttons in AlertDialog

# Usage
        dependencies {
            compile 'com.github.hslls:order-alert-buttons:1.0.0'
        }


        AlertDialogParams params = new AlertDialogParams();
        params.mTitle = "title";
        params.mMessage = "message";
        params.mPositiveText = "Ok";
        params.mNegativeText = "Cancel";
        params.mCancelable = true;
        params.mAlign = BUTTON_ALIGN.POSITIVE_BUTTON_LEFT;  // fix button position
        params.mClickListener = new AlertDialogClickListener() {
            @Override
            public void onPositiveClicked() {
                
            }

            @Override
            public void onNegativeClicked() {
                
            }
        };

        AlertDialog dialog = AlertDialogBuilder.createAlertDialog(this, params);
