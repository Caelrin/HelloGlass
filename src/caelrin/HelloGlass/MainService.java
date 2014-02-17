package caelrin.HelloGlass;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;

/**
 * Created by agibson on 1/11/14.
 */
public class MainService extends Service {

    private static final String TAG = "GesturesTag";
    private static final String LIVE_CARD_TAG = "gestures";

    private TimelineManager mTimelineManager;
    private LiveCard mLiveCard;
    private HelloGlassSurfaceHolderCallback mCallback;

    @Override
    public void onCreate() {
        super.onCreate();
        mTimelineManager = TimelineManager.from(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mLiveCard == null) {
            mLiveCard = mTimelineManager.createLiveCard(LIVE_CARD_TAG);

            mCallback = new HelloGlassSurfaceHolderCallback(this);
            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(mCallback);

            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
            mLiveCard.publish(LiveCard.PublishMode.REVEAL);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mLiveCard != null && mLiveCard.isPublished()) {
            if (mCallback != null) {
                mLiveCard.getSurfaceHolder().removeCallback(mCallback);
            }
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }
}
