package serviceclone.mx.com.cesarcorona.coronautils.general.customviews;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import serviceclone.mx.com.cesarcorona.coronautils.R;

public class ViewDialog {


    private final int DEFAULT_VIEW = 999;
    private final int DEFAULT_ICON = 9999;
    private final int DEFAULT_TEXT = 99999;
    private final int DEFAULT_COLOR = 999999;


    private Activity activity;
    private Dialog dialog;
    private int layout_resource;
    private int icon_resource;
    private int text_resource;
    private boolean showText;
    private int textColor;
    private boolean animate;



    public ViewDialog(Activity activity) {
        this.activity = activity;
        this.layout_resource =  DEFAULT_VIEW;
        this.showText = true;
        this.text_resource = DEFAULT_TEXT;
        this.icon_resource = DEFAULT_ICON;
        this.textColor = DEFAULT_COLOR;
        this.animate = true;
    }


    public ViewDialog(Activity activity, int icon_resource,int text_resource){
        this.activity = activity;
        this.icon_resource = icon_resource;
        this.text_resource = text_resource;
        this.showText = true;
        this.textColor = DEFAULT_COLOR;
        this.layout_resource = DEFAULT_VIEW;
        this.animate = true;

    }

    public ViewDialog(Activity activity, int icon_resource,int text_resource , int text_color){
        this.activity = activity;
        this.icon_resource = icon_resource;
        this.text_resource = text_resource;
        this.showText = true;
        this.textColor = text_color;
        this.layout_resource = DEFAULT_VIEW;
        this.animate = true;


    }

    public ViewDialog(Activity activity, int layout_resource){
        this.activity = activity;
        this.layout_resource = layout_resource;

    }

    public void showDialog() {

        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
           if(dialog.getWindow() != null){
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
           }

        //...set cancelable false so that it's never get hidden
        dialog.setCancelable(false);
        //...that's the layout i told you will inflate later

        if(layout_resource == DEFAULT_VIEW) {
            dialog.setContentView(R.layout.default_view_dialog_layout);
              if(this.icon_resource != DEFAULT_ICON){
                  ImageView icon = dialog.findViewById(R.id.icon_loading);
                  icon.setImageResource(this.icon_resource);
              }
              if(this.textColor != DEFAULT_COLOR){
                  TextView textView = dialog.findViewById(R.id.text_loading);
                  textView.setTextColor(this.textColor);
              }
              if(this.text_resource != DEFAULT_TEXT){
                  TextView textView = dialog.findViewById(R.id.text_loading);
                  textView.setTextColor(this.text_resource);
              }
              if(!showText){
                  TextView textView = dialog.findViewById(R.id.text_loading);
                  textView.setVisibility(View.GONE);
              }

              if(animate){
                  animate();
              }
        }else{
            dialog.setContentView(this.layout_resource);
        }


        dialog.show();



    }


    private void animate(){
        RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(700);

        // Start animating the image
          if(dialog != null){
              final ImageView splash = (ImageView) dialog.findViewById(R.id.icon_loading);
              splash.startAnimation(anim);

              // Later.. stop the animation
              splash.setAnimation(null);

          }


    }

    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        dialog.dismiss();
    }


    public void setLayout_resource(int layout_resource) {
        this.layout_resource = layout_resource;
    }

    public void setIcon_resource(int icon_resource) {
        this.icon_resource = icon_resource;
    }

    public void setText_resource(int text_resource) {
        this.text_resource = text_resource;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}