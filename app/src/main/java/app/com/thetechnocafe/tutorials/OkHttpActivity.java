package app.com.thetechnocafe.tutorials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        ButterKnife.bind(this);

        sendRequest();
    }

    private void sendRequest() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(mediaType, "{\"url\" : \"thetechnocafe.com\"}");

        Request request = new Request.Builder()
                .url("https://updown.io/api/checks")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                final String headers = response.headers().value(0);

                OkHttpActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(data);
                    }
                });
            }
        });
    }
}
