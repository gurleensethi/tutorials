package app.com.thetechnocafe.tutorials;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private PagerAdapter adapter;
    private final SnapHelper snapHelper = new PagerSnapHelper();
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_pager);

        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        snapHelper.attachToRecyclerView(mRecyclerView);

        adapter = new PagerAdapter(mRecyclerView, getLayoutInflater());
        mRecyclerView.setAdapter(adapter);

        for (int i = 0; i < adapter.getItemCount(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(String.valueOf(i)));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mRecyclerView.smoothScrollToPosition(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int tab = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if (tab >= 0 && tab < mTabLayout.getTabCount()) {
                    mTabLayout.getTabAt(tab).select();
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle bundle = new Bundle();
        adapter.onSaveInstanceState(bundle);
        outState.putBundle("ADAPTER", bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState.getBundle("ADAPTER"));
    }

    class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.PagerController> {
        private static final String STATE_BUFFERS = "buffers";
        private static final int PAGE_COUNT = 10;
        private final RecyclerView pager;
        private final LayoutInflater layoutInflater;
        private ArrayList<String> buffers = new ArrayList<>();

        public PagerAdapter(RecyclerView pager, LayoutInflater inflater) {
            this.pager = pager;
            this.layoutInflater = inflater;

            for (int i = 0; i < 10; i++) {
                buffers.add("");
            }
        }


        @Override
        public PagerController onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PagerController(layoutInflater.inflate(R.layout.editor, parent, false));
        }

        @Override
        public void onBindViewHolder(PagerController holder, int position) {
            holder.setText(buffers.get(position));
        }

        @Override
        public int getItemCount() {
            return PAGE_COUNT;
        }

        class PagerController extends RecyclerView.ViewHolder {
            private EditText mEditText;

            public PagerController(View itemView) {
                super(itemView);
                mEditText = (EditText) itemView.findViewById(R.id.edit_text);
            }

            public void setText(String text) {
                mEditText.setText(text);
                mEditText.setHint(String.valueOf(getAdapterPosition()));
            }

            public String getText() {
                return mEditText.getText().toString();
            }
        }

        @Override
        public void onViewDetachedFromWindow(PagerController holder) {
            super.onViewDetachedFromWindow(holder);
            buffers.set(holder.getAdapterPosition(), holder.getText());
        }

        public void onSaveInstanceState(Bundle bundle) {
            for (int i = 0; i < 10; i++) {
                PagerController pagerController = (PagerController) pager.findViewHolderForAdapterPosition(i);

                if (pagerController != null) {
                    buffers.set(i, pagerController.getText());
                }
            }

            bundle.putStringArrayList(STATE_BUFFERS, buffers);
        }

        public void onRestoreInstanceState(Bundle bundle) {
            buffers = bundle.getStringArrayList(STATE_BUFFERS);
        }
    }
}
