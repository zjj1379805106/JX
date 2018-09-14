package zjj.bwie.com.jx;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import zjj.bwie.com.jx.homepage.HomePageFragment;
import zjj.bwie.com.jx.sort.SortFragment;

public class MainActivity extends AppCompatActivity {


    private BottomTabBar btbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btbar = findViewById(R.id.buttom_tabbar);
        btbar.init(getSupportFragmentManager())
                .setFontSize(0)
                .setTabPadding(0,0,0)
                .addTabItem("1",R.drawable.ac1,R.drawable.ac0,HomePageFragment.class)
                .addTabItem("2",R.drawable.abx,R.drawable.abw,SortFragment.class)
                .addTabItem("3",R.drawable.abz,R.drawable.aby,SortFragment.class)
                .addTabItem("4",R.drawable.abv,R.drawable.abu,SortFragment.class)
                .addTabItem("5",R.drawable.ac3,R.drawable.ac2,SortFragment.class)
                .isShowDivider(false);

    }
}
