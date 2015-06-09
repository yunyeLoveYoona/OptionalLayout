package com.example.administrator.optionallayout.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.optionallayout.R;

/**
 * Created by Administrator on 15-6-8.
 */
public class OptionalLayout extends RelativeLayout{
    private final static int CHECK_BUTTON_WHDTH = 30;
    private final static int CHECK_BUTTON_HEIGHT = 30;
    private final static int PADDING = 20;
    private ImageView checkView;
    private AnimatorSet visibilityAnimator,goneAnimator;
    private boolean isVisibility =false;
    private RelativeLayout contentLayout;
    private OnCheckedChangeListenter onCheckedChangeListenter;
    private boolean isContentReady =false ;

    public OptionalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCheckView(context);
    }
    private void initCheckView(Context context){
        contentLayout = new RelativeLayout(context);
        checkView = new ImageView(context);
        checkView.setImageResource(R.drawable.ic_radiobutton);
        checkView.setAdjustViewBounds(true);
        checkView.setScaleType(ImageView.ScaleType.FIT_XY);
        visibilityAnimator = new AnimatorSet();
        visibilityAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                checkView.setImageResource(R.drawable.ic_radiobutton);
                click(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        goneAnimator = new AnimatorSet();
        visibilityAnimator.play(ObjectAnimator.ofFloat(checkView,"translationX",
                0-(CHECK_BUTTON_WHDTH+PADDING),PADDING)).
                with(ObjectAnimator.ofFloat(contentLayout, "translationX", 0, CHECK_BUTTON_WHDTH + PADDING*2));
        visibilityAnimator.setDuration(300);
        goneAnimator.play(ObjectAnimator.ofFloat(checkView,"translationX",PADDING,0-(CHECK_BUTTON_WHDTH+PADDING)))
        .with(ObjectAnimator.ofFloat(contentLayout,"translationX",CHECK_BUTTON_WHDTH+PADDING*2,0));
        goneAnimator.setDuration(300);
    }
    public void click(boolean isChecked){
                if(!isChecked){
                    checkView.setImageResource(R.drawable.ic_radiobutton);
                }else{
                    checkView.setImageResource(R.drawable.ic_radiobutton_selected);
                }
                if(onCheckedChangeListenter!=null){
                    onCheckedChangeListenter.onCheckedChangeListenter(checkView.getTag(),isChecked);
                }
    }



    private void visibilityCheckButton(){
        visibilityAnimator.start();
    }
    private  void goneCheckButton(){
        goneAnimator.start();
    }
    public void  visibilityOrGoneCheckButton(boolean isOptional){
        if(isOptional!=isVisibility) {
            if (isVisibility) {
                goneCheckButton();
            } else {
                visibilityCheckButton();
            }
            isVisibility =isOptional;
        }
    }
    public void setTag(Object tag){
        checkView.setTag(tag);
    }
    public void setOnCheckedChangeListenter(OnCheckedChangeListenter onCheckedChangeListenter){
        this.onCheckedChangeListenter = onCheckedChangeListenter ;
    }
    public interface OnCheckedChangeListenter{
        public void onCheckedChangeListenter(Object tag,boolean isChecked);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(!isContentReady) {
            loadContentView(widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private void loadContentView(int widthMeasureSpec, int heightMeasureSpec){
            isContentReady = true;
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                removeView(view);
                contentLayout.addView(view);
            }
            addView(contentLayout);
            addView(checkView);
            checkView.getLayoutParams().height = CHECK_BUTTON_HEIGHT;
            checkView.getLayoutParams().width = CHECK_BUTTON_WHDTH + PADDING;
            checkView.setPadding(0, 0, PADDING, 0);
            checkView.setTranslationX(0 - (CHECK_BUTTON_WHDTH + PADDING));
            RelativeLayout.LayoutParams checkViewLayoutParams= (LayoutParams) checkView.getLayoutParams();
            checkViewLayoutParams.addRule(CENTER_VERTICAL,1);
            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            RelativeLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            switch (widthMode){
                case MeasureSpec.UNSPECIFIED:
                    layoutParams.width =  MeasureSpec.getSize(widthMeasureSpec);
                    break;
                case MeasureSpec.EXACTLY:
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    break;
                case MeasureSpec.AT_MOST:
                    layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
            }
            switch (heightMode){
                case MeasureSpec.UNSPECIFIED:
                    layoutParams.height =  MeasureSpec.getSize(heightMeasureSpec);
                    break;
                case MeasureSpec.EXACTLY:
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    break;
                case MeasureSpec.AT_MOST:
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
            }
        }
}
