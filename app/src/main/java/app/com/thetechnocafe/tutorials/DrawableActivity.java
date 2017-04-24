package app.com.thetechnocafe.tutorials;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawableActivity extends AppCompatActivity {

    @BindView(R.id.transition_button)
    Button mTransitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        ButterKnife.bind(this);

        TransitionDrawable drawable = (TransitionDrawable) mTransitionButton.getBackground();
        drawable.setCrossFadeEnabled(true);
        drawable.startTransition(2000);
    }
}
