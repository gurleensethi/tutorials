package app.com.thetechnocafe.tutorials;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnimatorActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private String[] arrayOfWords = new String[] {"Dog", "Cat", "Fish", "Animal", "Whale"};
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        mTextView = (TextView) findViewById(R.id.text_view);
        mButton = (Button) findViewById(R.id.button);

        typeValueAnimator();
    }

    private void valueAnimator() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int visibility = mTextView.getVisibility();

                if (visibility == View.VISIBLE) {
                    mTextView.animate()
                            .setDuration(1000)
                            .alpha(0)
                            .translationX(1000f)
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    mTextView.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                } else {
                    mTextView.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                    mTextView.animate().setDuration(1000).alpha(1).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
        });
    }

    private void objectAnimator() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofInt(AnimatorActivity.this, "wordPosition", 0, arrayOfWords.length);
                valueAnimator.setDuration(1000);
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.start();
                mButton.setEnabled(false);
            }
        });
    }

    private void typeValueAnimator() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ObjectAnimator.ofFloat(mTextView, "alpha", 0, 1);
                animator.setDuration(500);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.start();
            }
        });
    }

    public void setWordPosition(int position) {
        this.position = position;
        mTextView.setText(arrayOfWords[position]);
    }

    public int getWordPosition() {
        return this.position;
    }

}
