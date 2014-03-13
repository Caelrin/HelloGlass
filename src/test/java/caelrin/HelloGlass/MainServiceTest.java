package caelrin.HelloGlass;

import android.content.Context;
import android.content.Intent;
import com.google.android.glass.timeline.TimelineManager;
import glassShadows.ShadowTimelineManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


/**
 * Created by Caelrin on 2/22/14.
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows=ShadowTimelineManager.class)
public class MainServiceTest {
    @Spy
    @InjectMocks
    MainService underTest;
    @Mock
    private TimelineManager manager;
    @Mock
    private Context context;
    @Mock
    private Intent intent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onCreateGetsATimeLineManager() {
//        PowerMockito.mockStatic(TimelineManager.class);
//        when(TimelineManager.from(context)).thenReturn(manager);
//        Robolectric.buildActivity(MenuActivity.class);


//        underTest.onCreate();

        assertThat(underTest.getTimelineManager(), is(manager));
    }

    @Test
    public void onStartCommandShouldCreateNewCard() {


        underTest.onStartCommand(intent, 0, 0);
    }



}
