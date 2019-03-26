package com.mding.chatfeng.main_code.ui.about_contacts.about_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mding.chatfeng.R;

import java.util.ArrayList;

public class LetterBar  extends View
{

    private Paint mpaint;
    ArrayList<String>  ABCList = new ArrayList<String>();
    private int height;
    private int width;
    private int index;
    private Paint mpaint2;
    private boolean isTouch=false;
//    字体大小
    int Text_Size=25;
    Context context;
    public LetterBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context=context;
        initPaint();
        initABC();
    }

    public LetterBar(Context context)
    {
        super(context);
    }

    private void initABC()
    {
        ABCList.add("");
        ABCList.add("⇧");
        for (char i='A';i<='Z';i++)
        {
            ABCList.add(i+"");
        }
        ABCList.add("#");
    }
    public void initPaint()
    {
//        Paint.Style.STROKE 只绘制图形轮廓（描边）
//Paint.Style.FILL 只绘制图形内容
//Paint.Style.FILL_AND_STROKE 既绘制轮廓也绘制内容
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setStrokeWidth(10);
        mpaint.setTextAlign(Align.CENTER);
        mpaint.setTextSize(Text_Size);
		mpaint.setColor(context.getResources().getColor(R.color.gray666));
        //
        mpaint2 = new Paint();
        mpaint2.setStyle(Paint.Style.FILL);
        mpaint2.setStrokeWidth(10);
        mpaint2.setTextAlign(Align.CENTER);
        mpaint2.setTextSize(Text_Size);
        mpaint2.setColor(context.getResources().getColor(R.color.doubleq_theme));
//        mpaint2.setColor(context.getResources().getColor(R.color.normal));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.translate(width/2, 0);

        for (int i = 0; i < ABCList.size(); i++)
        {
            canvas.drawText(ABCList.get(i), 0, Math.abs((height/ABCList.size()*i)-10), mpaint);
        }
        if (isTouch)
        {
            canvas.drawText(ABCList.get(index), 0, Math.abs((height/ABCList.size()*index)-10), mpaint2);
        }

        invalidate();
//		

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        float y2 = event.getY();
        //获取触碰字母下标
        index = (int) (y2/(height/ABCList.size()));
        if (index>ABCList.size()-1)
        {
            index=ABCList.size()-1;
        }
        if (index<0)
        {
            index=0;
        }
        switch (action)
        {
            //监听按下
            case MotionEvent.ACTION_DOWN:
                if (index!=0)
                listener.onTouchDown(ABCList.get(index));
//		Toast.makeText(getContext(), ABCList.get(index), Toast.LENGTH_SHORT).show();
                isTouch=true;
                break;
            //监听移动
            case MotionEvent.ACTION_MOVE:
                if (index!=0)
                listener.onTouchDown(ABCList.get(index));
//		Toast.makeText(getContext(),ABCList.get(index), Toast.LENGTH_SHORT).show();
                isTouch=true;
                break;
            //手指移开
            case MotionEvent.ACTION_UP:
                listener.onTouchUp();
//		Toast.makeText(getContext(), "移开", Toast.LENGTH_LONG).show();
                isTouch=false;
                break;

            default:
                break;
        }
        invalidate();
        return true;
    }

    //1设置监听的方法
    public void setonTouchLetterListener(onTouchLetterListener l)
    {
        if (l!=null)
        {
            listener=l;
        }

    }

    //2定义一个接口
    public  interface onTouchLetterListener{
        //按下

        void onTouchDown(String letter);

        //移动

        void onTouchUp();
    }
    //定义一个变量
    public onTouchLetterListener  listener;

    public ArrayList<String> getABCList()
    {
        return ABCList;
    }
    public void setABCList(ArrayList<String> aBCList)
    {
        ABCList = aBCList;
    }
    public int getIndex()
    {
        return index;
    }
    public void setIndex(int index)
    {
        this.index = index;
    }

    public boolean getisTouch()
    {
        return isTouch;
    }

    public void setTouch(boolean isTouch)
    {
        this.isTouch = isTouch;
    }

//   canvas.drawColor(Color.CYAN);//绘制画布的颜色
//	canvas.drawCircle(width, height, radius, mpaint);//绘制 圆  圆心坐标，半径，画笔
//	canvas.drawText("帅比", 100, 100, mpaint);
//	//绘制矩形
//	canvas.drawRect(10, 10, 210, 110, mpaint);
//	//绘制图片
//	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.id.icon);
//	canvas.drawBitmap(bitmap, 200, 200, mpaint);
//	
    //移动画布
//	canvas.translate(dx, dy);
//	//绘制线
//	canvas.drawLine(0, 0, canvas.getWidth(), 0, mpaint);
}
