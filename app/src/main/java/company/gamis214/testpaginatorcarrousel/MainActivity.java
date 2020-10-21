package company.gamis214.testpaginatorcarrousel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator2;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mSnappingRecyclerView;
    private CircleIndicator2 circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSnappingRecyclerView   = findViewById(R.id.recyclerView);
        circleIndicator         = findViewById(R.id.indicatorCircle);
        //mSnappingRecyclerView.enableViewScaling(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomAdapter sampleAdapter = new CustomAdapter();
        CenterZoomLayoutManager layoutManager =
                new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mSnappingRecyclerView.setLayoutManager(layoutManager);
        mSnappingRecyclerView.setAdapter(sampleAdapter);
        layoutManager.scrollToPosition(1);
        mSnappingRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                int dx = (mSnappingRecyclerView.getWidth() - mSnappingRecyclerView.getChildAt(0).getWidth()) / 2;
                mSnappingRecyclerView.scrollBy(-dx, 0);
                // Assign the LinearSnapHelper that will initially snap the near-center view.
                LinearSnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(mSnappingRecyclerView);
                circleIndicator.attachToRecyclerView(mSnappingRecyclerView, snapHelper);
            }
        });
    }

    private List<String> getSampleList() {
        List<String> sampleList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            sampleList.add(i + "");
        }

        return sampleList;
    }
}