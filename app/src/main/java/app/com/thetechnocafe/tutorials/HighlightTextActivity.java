package app.com.thetechnocafe.tutorials;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

public class HighlightTextActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight_text);

        mSearchEditText = (EditText) findViewById(R.id.search_edit_text);
        mTextView = (TextView) findViewById(R.id.text_view);

        //Add a TextWatcher to EditText to listen to text change event
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                highlightString(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void highlightString(String input) {
        //Get the text from text view and create a spannable string
        SpannableString spannableString = new SpannableString(mTextView.getText());

        //Get the previous spans and remove them
        BackgroundColorSpan[] backgroundSpans = spannableString.getSpans(0, spannableString.length(), BackgroundColorSpan.class);

        for (BackgroundColorSpan span : backgroundSpans) {
            spannableString.removeSpan(span);
        }

        //Search for all occurrences of the keyword in the string
        int indexOfKeyword = spannableString.toString().indexOf(input);

        while (indexOfKeyword > 0) {
            //Create a background color span on the keyword
            spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), indexOfKeyword, indexOfKeyword + input.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //Get the next index of the keyword
            indexOfKeyword = spannableString.toString().indexOf(input, indexOfKeyword + input.length());
        }

        //Set the final text on TextView
        mTextView.setText(spannableString);
    }
}
