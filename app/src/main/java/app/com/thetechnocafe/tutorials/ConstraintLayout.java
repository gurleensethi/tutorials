package app.com.thetechnocafe.tutorials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstraintLayout extends AppCompatActivity {

    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button)
    Button mButton1;
    @BindView(R.id.image_view)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        ButterKnife.bind(this);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = mImageView.getVisibility();

                mImageView.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }
}
