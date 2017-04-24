package app.com.thetechnocafe.tutorials;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    private String[] activities = new String[]{"ScrollingFABActivity", "OkHttpActivity", "RecyclerViewPagerActivity", "ConstraintLayout",
    "PaintingWIthViewsActivity", "MovingBackgroundGradientActivity", "HighlightTextActivity", "AnimatorActivity", "DrawableActivity",
    "RetroLambdaActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Custom Adapter
        MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.main_item_list, activities);

        setListAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter {
        MyAdapter(Context context, int layout, String[] list) {
            super(context, layout, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.main_item_list, parent, false);
            ((TextView) v).setText(activities[position]);
            return v;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String app = "app.com.thetechnocafe.tutorials." + activities[position];

        try {
            Class c = Class.forName(app);
            Intent intent = new Intent(this, c);
            startActivity(intent);
        } catch (ClassNotFoundException exc) {
        }
    }
}
