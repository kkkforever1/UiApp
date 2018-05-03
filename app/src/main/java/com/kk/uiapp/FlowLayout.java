package com.kk.uiapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    //用来保存每行view的列表
    private List<List<View>> mViewLinesList = new ArrayList<>();
    //保存行高
    private List<Integer> mLineHeights = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth = 0;
        int measureHeight = 0;
        int curLineW = 0;
        int curLineH = 0;

        if(widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY){
            measureWidth = widthSpecSize;
            measureHeight = heightSpecSize;
        }else {
            int childWidth;
            int childHeight;
            int childCount = getChildCount();
            List<View> viewList = new ArrayList<>();
            for(int i=0;i<childCount;i++){
                View childView = getChildAt(i);
                measureChild(childView,widthMeasureSpec,heightMeasureSpec);
                MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
                childWidth = childView.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                childHeight = childView.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;

                if(curLineW+childWidth > widthSpecSize){
                    //换行
                    measureWidth = Math.max(measureWidth,curLineW);
                    measureHeight += curLineH;

                    mViewLinesList.add(viewList);
                    mLineHeights.add(curLineH);

                    curLineW = childWidth;
                    curLineH = childHeight;

                    viewList = new ArrayList<>();
                    viewList.add(childView);


                }else {
                    //行内
                    curLineW += childWidth;
                    curLineH = Math.max(curLineH,childHeight);
                    viewList.add(childView);
                }

                //
                if(i==childCount-1){
                    measureWidth = Math.max(measureWidth,curLineW);
                    measureHeight += curLineH;
                    mViewLinesList.add(viewList);
                    mLineHeights.add(curLineH);
                }
            }
        }

        setMeasuredDimension(measureWidth,measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left,top,right,bottom;
        int curTop = 0;
        int curLeft = 0;

        int lineCount = mViewLinesList.size();
        for(int i=0;i<lineCount;i++){
            List<View> viewList = mViewLinesList.get(i);
            int lineViewSize = viewList.size();
            for(int j=0;j<lineViewSize;j++){
                View childView = viewList.get(j);
                MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
                left = curLeft+params.leftMargin;
                top = curTop + params.topMargin;
                right = left+childView.getMeasuredWidth()+params.rightMargin;
                bottom = top+childView.getMeasuredHeight()+params.bottomMargin;
                childView.layout(left,top,right,bottom);
                curLeft += childView.getMeasuredWidth()+params.leftMargin+params.rightMargin;
            }
            curLeft = 0;
            curTop += mLineHeights.get(i);
        }
        mViewLinesList.clear();
        mLineHeights.clear();
    }
}
