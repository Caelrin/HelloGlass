package caelrin.HelloGlass;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Caelrin on 1/26/14.
 */
public class GesturesView extends FrameLayout {
    private static final long DELAY_MILLIS = 41;
    private final TextView mSecondsView;
    private final Handler mHandler = new Handler();

    public void setListener(GesturesListener mListener) {
        this.mListener = mListener;
    }

    private GesturesListener mListener;

    private Runnable mUpdateViewRunnable= new Runnable() {
        @Override
        public void run() {
            mSecondsView.setText("Hello World!");
            mListener.onTick(500);
            mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
        }
    };

    private boolean mStarted = false;


    public GesturesView(Context context) {
        this(context, null, 0);
    }

    public GesturesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GesturesView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);

        LayoutInflater.from(context).inflate(R.layout.main, this);

        mSecondsView =  (TextView) findViewById(R.id.hello_view);

        mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
    }

    public void start() {
        if (!mStarted) {
            mStarted = true;
            mHandler.postDelayed(mUpdateViewRunnable, DELAY_MILLIS);
        }
    }

    public interface GesturesListener {
        /**
         * Notified of a tick, indicating a layout change.
         */
        public void onTick(long millisUntilFinish);

        /**
         * Notified when the countdown is finished.
         */
        public void onFinish();
    }
}
