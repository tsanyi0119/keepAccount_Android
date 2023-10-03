package com.example.it_demo.user.dateBrowse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.example.it_demo.R;
import com.example.it_demo.user.keepAccount.UserKeepAccountActivity;
import com.example.it_demo.user.statisticsMain.UserStatisticsMainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UserDateBrowseActivity extends AppCompatActivity implements UserDateBrowseContract.UserDateBrowseActivity {

    private ImageView img_add ,img_back_statistics;
    private CalendarView calendarView;
    private String selectedDateStr;
    private UserDateBrowseContract.UserDateBrowsePresenter presenter;
    private RecyclerView recyclerView;
    private UserDateBrowseAdapter recyclerViewAdapter;
    private List<ExpensesData> expensesDataList = new ArrayList<>();
    private List<AssetsData> assetsDataList = new ArrayList<>();
    private List<TotalRecordData> totalRecordList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_date_browse);
        init();
    }

    private void init(){
        presenter = new UserDateBrowsePresenter(this);
        img_add = findViewById(R.id.img_add);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        img_back_statistics = findViewById(R.id.img_back_statistics);

        setupUI();
    }

    private void setupUI(){
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDateBrowseActivity.this, UserKeepAccountActivity.class);
                intent.putExtra("date" , selectedDateStr);
                startActivity(intent);
                finish();
            }
        });
        img_back_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDateBrowseActivity.this, UserStatisticsMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerViewAdapter = new UserDateBrowseAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.LEFT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if(totalRecordList.get(position).getClassification().equals("assets"))
                    presenter.deleteAssetsData(totalRecordList.get(position).getId());
                if (totalRecordList.get(position).getClassification().equals("expenses"))
                    presenter.deleteExpensesData(totalRecordList.get(position).getId());
                totalRecordList.remove(position);
                recyclerViewAdapter.deleteItem(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // 滑动期间更改背景颜色
                    if (dX < 0) {
                        viewHolder.itemView.setBackgroundColor(Color.parseColor("#FF8888")); // 设置为浅红色
                    } else {
                        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Calendar currentDate = Calendar.getInstance();
        calendarView.setDate(currentDate.getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDateStr = sdf.format(currentDate.getTime());
        presenter.loadExpensesData(selectedDateStr);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 在這裡獲取選取的日期
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                // 使用 SimpleDateFormat 將日期格式化為所需的字串
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                selectedDateStr = sdf.format(selectedDate.getTime());

                totalRecordList.clear();
                presenter.loadExpensesData(selectedDateStr);

                // 在此處執行你需要的操作，如日誌輸出或其他處理
                Log.d("SelectedDate", "選取的日期：" + selectedDateStr);
            }
        });

    }

    @Override
    public void showExpenses(List<ExpensesData> expensesDataList) {

        this.expensesDataList = expensesDataList;
        for(int i = 0 ; i < expensesDataList.size() ; i++){
            TotalRecordData totalRecordData = new TotalRecordData();
            totalRecordData.setId(expensesDataList.get(i).getId());
            totalRecordData.setType(expensesDataList.get(i).getExpensesType());
            totalRecordData.setName(expensesDataList.get(i).getExpensesName());
            totalRecordData.setValue(expensesDataList.get(i).getExpensesValue());
            totalRecordData.setClassification("expenses");
            totalRecordList.add(totalRecordData);
        }
        presenter.loadAssetsData(selectedDateStr);

    }

    @Override
    public void showAssets(List<AssetsData> assetsDataList) {
        this.assetsDataList = assetsDataList;
        for(int i = 0 ; i < assetsDataList.size() ; i++){
            TotalRecordData totalRecordData = new TotalRecordData();
            totalRecordData.setId(assetsDataList.get(i).getId());
            totalRecordData.setType(assetsDataList.get(i).getAssetsType());
            totalRecordData.setName(assetsDataList.get(i).getAssetsName());
            totalRecordData.setValue(assetsDataList.get(i).getAssetsValue());
            totalRecordData.setClassification("assets");
            totalRecordList.add(totalRecordData);
        }
        recyclerViewAdapter.setTotalRecordList(totalRecordList);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}