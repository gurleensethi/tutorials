package app.com.thetechnocafe.tutorials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetroLambdaActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_lambda);

        ButterKnife.bind(this);

        mButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "You clicked this innocent button.", Toast.LENGTH_SHORT).show();
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You clicked this innocent button.", Toast.LENGTH_SHORT).show();
            }
        });

        function(new TestingLongName() {
            @Override
            public void test() {

            }
        });

    }

    public interface TestingLongName {
        void test();
    }

    public void function(TestingLongName e) {

    }
}
