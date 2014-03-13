package caelrin.HelloGlass;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.View;

public class HelloGlassSurfaceHolderCallback implements SurfaceHolder.Callback {
    private final HelloView helloView;
    private SurfaceHolder mHolder;

    public HelloGlassSurfaceHolderCallback(Context context) {
        helloView = new HelloView(context);
        helloView.setListener(new DisplayViewCallback() {
            @Override
            public void onTick(long millisUntilFinish) {
                draw(helloView);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
        helloView.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        helloView.measure(measuredWidth, measuredHeight);
        helloView.layout(
                0, 0, helloView.getMeasuredWidth(), helloView.getMeasuredHeight());
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void draw(View view) {
        Canvas canvas;
        try {
            canvas = mHolder.lockCanvas();
        } catch (Exception e) {
            return;
        }
        if (canvas != null) {
            view.draw(canvas);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }
}
