package caelrin.HelloGlass;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HelloView extends FrameLayout {
    private static final long DELAY_MILLIS = 41;
    private final TextView helloView;
    private final Handler mHandler = new Handler();

    public void setListener(DisplayViewCallback mListener) {
        this.mListener = mListener;
    }

    private DisplayViewCallback mListener;

    private Runnable mUpdateViewRunnable= new Runnable() {
        @Override
        public void run() {
            helloView.setText("Hello Glass!");
            mListener.onTick(500);
            mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
        }
    };

    private boolean mStarted = false;


    public HelloView(Context context) {
        this(context, null, 0);
    }

    public HelloView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);

        LayoutInflater.from(context).inflate(R.layout.main, this);
        helloView =  (TextView) findViewById(R.id.hello_view);

        mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
    }

    public void start() {
        if (!mStarted) {
            mStarted = true;
            mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
        }
    }

}
