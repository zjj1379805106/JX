package zjj.bwie.com.jx.homepage;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;
import static android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_IDLE;

public class GrideSnapHelper {
    RecyclerView recyclerView;
    int horizon, column;
    private GridLayoutManager layoutManager;
    private ArrayList<Integer> positionLisit;
    private final int screen_width;

    public GrideSnapHelper(int horizon, int column, Activity ctx) {
        this.horizon = horizon;
        this.column = column;
        WindowManager wm1 = ctx.getWindowManager();
        screen_width = wm1.getDefaultDisplay().getWidth();
    }

    public void attachToRecycleView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        setListener();
    }

    private void setListener() {
        if (recyclerView == null) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        System.out.println("recyclerview已经停止滚动");
                        snapView();
                        break;
//                    case SCROLL_STATE_DRAGGING:
//                        System.out.println("recyclerview正在被拖拽");
//                        break;
//                    case SCROLL_STATE_SETTLING:
//                        System.out.println("recyclerview正在依靠惯性滚动");
//                        break;
                }
            }
        });


    }

    private void snapView() {
        if (layoutManager ==null) {
            layoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
            positionLisit = new ArrayList<>();
        }
        //寻找锚点
        positionLisit.clear();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        for (int i = firstVisibleItem;i<=lastVisibleItem;i++) {
            if (i%(column*horizon)==0)
                positionLisit.add(i);
        }
        //选择锚点
        int targetPosition = 0;
        //有1个锚点  锚点X坐标大于屏幕一半取上一个锚点,锚点X坐标小于屏幕一半取当前锚点
        if (positionLisit.size() == 1) {
            View item = layoutManager.findViewByPosition(positionLisit.get(0));
            if (item==null) return;
            final int []location=new int[2];
            item.getLocationOnScreen(location);
            int x=location[0];//获取当前位置的横坐标
            int y=location[1];//获取当前位置的纵坐标
            if (x > screen_width / 2) {
                targetPosition = positionLisit.get(0) - column * horizon;
            } else {
                targetPosition = positionLisit.get(0);
            }
        }
        //有2个锚点  取小锚点
        else if (positionLisit.size() == 2) {
            targetPosition = positionLisit.get(0);
        }
//        recyclerView.smoothScrollToPosition(targetPosition);
        layoutManager.scrollToPositionWithOffset(targetPosition, 0);
    }

}
