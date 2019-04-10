package serviceclone.mx.com.cesarcorona.coronautils.general.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import serviceclone.mx.com.cesarcorona.coronautils.R;
import serviceclone.mx.com.cesarcorona.coronautils.general.customviews.ViewDialog;

public abstract class BaseActivity extends AppCompatActivity {



    protected abstract int getLayoutResourceId();
    private boolean isShowing;
    private ViewDialog viewDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getLayoutResourceId());
        isShowing = false;
    }









    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void gotoActivity(Class clase,boolean finish){
        startActivity(new Intent(this,clase));
        if(finish){
            finish();
        }
    }

    public void showCustomLoadingDialog() {

        //..show gif
        if(!isShowing){
            if(viewDialog!= null){
                viewDialog.showDialog();
                isShowing = true;
            }

        }

    }

    public void hideCustomLoadigDialog(){
        if(isShowing){
            if(viewDialog != null){
                viewDialog.hideDialog();
                isShowing = false;
            }
        }
    }


    public void setViewDialog(ViewDialog viewDialog) {
        this.viewDialog = viewDialog;
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

    }


   /* @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/





}
