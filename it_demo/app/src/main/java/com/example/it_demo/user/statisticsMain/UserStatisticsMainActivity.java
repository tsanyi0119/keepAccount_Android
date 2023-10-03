package com.example.it_demo.user.statisticsMain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.it_demo.R;
import com.example.it_demo.user.dateBrowse.ExpensesData;
import com.example.it_demo.user.dateBrowse.TotalRecordData;
import com.example.it_demo.user.dateBrowse.UserDateBrowseActivity;
import com.example.it_demo.user.noteBook.UserNoteBookActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class UserStatisticsMainActivity extends AppCompatActivity implements UserStatisticsMainContract.UserStatisticsMainActivity{

    private Button btn_mode_assets , btn_mode_expenses;
    private View view_assets , view_expenses;
    private ImageView img_intent_dateBrowse , img_intent_note;
    private PieChart pieChart;
    private ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
    private TextView tv_food_percentage,
            tv_food_sum,
            tv_traffic_percentage,
            tv_traffic_sum,
            tv_medical_percentage,
            tv_medical_sum,
            tv_pie_percentage,
            tv_pie_sum,
            tv_life_percentage,
            tv_life_sum,
            tv_3c_percentage,
            tv_3c_sum;
    int totalPrice_food = 0,totalPrice_traffic = 0,totalPrice_medical = 0, totalPrice_pie = 0,totalPrice_life = 0,totalPrice_3c = 0;
    private List<TotalRecordData> totalRecordList = new ArrayList<>();
    private UserStatisticsMainContract.UserStatisticsMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_statistics_main);
        init();

    }
    private void init(){
        presenter = new UserStatisticsMainPresenter(this);
        presenter.loadExpensesData();
        view_assets = findViewById(R.id.view_assets);
        view_expenses = findViewById(R.id.view_expenses);
        img_intent_dateBrowse = findViewById(R.id.img_intent_dateBrowse);
        img_intent_note = findViewById(R.id.img_intent_note);
        btn_mode_assets = findViewById(R.id.btn_mode_assets);
        btn_mode_expenses = findViewById(R.id.btn_mode_expenses);
        tv_food_percentage =findViewById(R.id.tv_food_percentage);
        tv_food_sum=findViewById(R.id.tv_food_sum);
        tv_traffic_percentage=findViewById(R.id.tv_traffic_percentage);
        tv_traffic_sum=findViewById(R.id.tv_traffic_sum);
        tv_medical_percentage=findViewById(R.id.tv_medical_percentage);
        tv_medical_sum=findViewById(R.id.tv_medical_sum);
        tv_pie_percentage=findViewById(R.id.tv_pet_percentage);
        tv_pie_sum=findViewById(R.id.tv_pet_sum);
        tv_life_percentage=findViewById(R.id.tv_life_percentage);
        tv_life_sum=findViewById(R.id.tv_life_sum);
        tv_3c_percentage=findViewById(R.id.tv_3c_percentage);
        tv_3c_sum=findViewById(R.id.tv_3c_sum);
        pieChart = findViewById(R.id.bar_piechart);
        setupUI();
    }

    private void setupUI() {
        btn_mode_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAssetsView();
            }
        });

        btn_mode_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setExpensesView();
            }
        });

        img_intent_dateBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserStatisticsMainActivity.this, UserDateBrowseActivity.class);
                startActivity(intent);
            }
        });

        img_intent_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserStatisticsMainActivity.this, UserNoteBookActivity.class);
                startActivity(intent);
            }
        });

        setExpensesView();

    }

    //設定資產UI配置
    private void setAssetsView() {
        view_assets.setVisibility(View.VISIBLE);
        view_expenses.setVisibility(View.GONE);

    }

    //設定支出UI配置
    private void setExpensesView() {
        view_assets.setVisibility(View.GONE);
        view_expenses.setVisibility(View.VISIBLE);
        btn_mode_assets.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFB")));
        btn_mode_expenses.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAD689")));
        setExpensesPieChart();
    }

    //設定支出圖
    private void setExpensesPieChart() {
        tv_food_sum.setText(String.valueOf(totalPrice_food));
        tv_traffic_sum.setText(String.valueOf(totalPrice_traffic));
        tv_medical_sum.setText(String.valueOf(totalPrice_medical));
        tv_pie_sum.setText(String.valueOf(totalPrice_pie));
        tv_life_sum.setText(String.valueOf(totalPrice_life));
        tv_3c_sum.setText(String.valueOf(totalPrice_3c));
        double totalSum = totalPrice_food + totalPrice_traffic + totalPrice_medical + totalPrice_pie + totalPrice_life + totalPrice_medical;
        double percentage_food = (totalPrice_food / totalSum) * 100;
        double percentage_traffic = (totalPrice_traffic / totalSum) * 100;
        double percentage_medical = (totalPrice_medical / totalSum) * 100;
        double percentage_pie = (totalPrice_pie / totalSum) * 100;
        double percentage_life = (totalPrice_life / totalSum) * 100;
        double percentage_3c = (totalPrice_3c / totalSum) * 100;
        percentage_food = Math.round(percentage_food * 10) / 10.0;
        percentage_traffic = Math.round(percentage_traffic * 10) / 10.0;
        percentage_medical = Math.round(percentage_medical * 10) / 10.0;
        percentage_pie = Math.round(percentage_pie * 10) / 10.0;
        percentage_life = Math.round(percentage_life * 10) / 10.0;
        percentage_3c = Math.round(percentage_3c * 10) / 10.0;
        tv_food_percentage.setText(Double.toString(percentage_food) + "%");
        tv_traffic_percentage.setText(Double.toString(percentage_traffic) + "%");
        tv_medical_percentage.setText(Double.toString(percentage_medical) + "%");
        tv_pie_percentage.setText(Double.toString(percentage_pie) + "%");
        tv_life_percentage.setText(Double.toString(percentage_life) + "%");
        tv_3c_percentage.setText(Double.toString(percentage_3c) + "%");
        entries.clear();
        entries.add(new PieEntry(totalPrice_food,"飲食"));
        entries.add(new PieEntry(totalPrice_traffic,"交通"));
        entries.add(new PieEntry(totalPrice_medical,"醫療"));
        entries.add(new PieEntry(totalPrice_pie,"寵物"));
        entries.add(new PieEntry(totalPrice_life,"生活"));
        entries.add(new PieEntry(totalPrice_3c,"數位"));

        pieChart.getDescription().setEnabled(false);//設置PieChart圖表的描述
        pieChart.setExtraOffsets(20, 0, 20, 0);//圓餅圖上下左右的間距

        pieChart.setDragDecelerationFrictionCoef(0.95f);//設置PieChart圖表轉動阻力摩擦係數
        pieChart.setDrawCenterText(true);//設置是否顯示内部圓文字
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);//設置pieChart中間文字粗體
        pieChart.setCenterTextSize(15f);//設置pieChart中間文字大小
        pieChart.setCenterTextColor(Color.GRAY);//設置pieChart中間文字顏色
        pieChart.setCenterText("支出比例");//設置PieChart内部圓文字的内容，只有環形圖看的出來

        pieChart.setEntryLabelColor(Color.BLACK);//設置pieChart圖表文字顏色
        pieChart.setEntryLabelTextSize(20f);//設置pieChart圖表文字大小
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);//設置pieChart中間文字粗體

        pieChart.setDrawHoleEnabled(true);//true是環形圖 false是圓餅圖
        pieChart.setHoleColor(Color.WHITE);//設置中間空白顏色，只有環形圖看的出來

        pieChart.setTransparentCircleColor(Color.WHITE);//設置透明圓環的顏色，只有環形圖看的出來
        pieChart.setTransparentCircleAlpha(50);//設置PieChart透明圆環透明度

        pieChart.setHoleRadius(40f);//設置內部圓的大小，只有環形圖看的出來

        pieChart.setTransparentCircleRadius(50f);//設置PieChart内部透明圆環的半徑

        pieChart.setRotationAngle(0);//設置pieChart圖表起始角度

        pieChart.setRotationEnabled(true);//設置pieChart是否能夠手動旋轉
        pieChart.setHighlightPerTapEnabled(true);//設置piecahrt區塊點擊突出
        pieChart.animateY(1400, Easing.EaseInOutQuad);// 設置pieChart動態

        Legend l = pieChart.getLegend();
        l.setEnabled(false);//是否顯示圖例

        PieDataSet dataSet = new PieDataSet(entries, "");//設定數據

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setValueLinePart2Length(0.3f);//設置第一部分線長度
        dataSet.setValueLinePart1Length(0.5f);//設置第二部分線長度

        dataSet.setSliceSpace(3f);//設置Item之間的縫隙
        dataSet.setIconsOffset(new MPPointF(0, 40));

        dataSet.setSelectionShift(15f);//設置Item被選中位置彈起距離

        ArrayList<Integer>colors= new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(false);            //設置是否顯示數據
        data.setValueTextColor(Color.BLACK);  //設置全部DataSet百分比文字顏色
        data.setValueTextSize(20f);          //設置全部DataSet百分比文字大小
        data.setValueFormatter(new PercentFormatter());

        pieChart.setData(data);//為圖表添加數據

        pieChart.highlightValues(null);//設置高亮顯示
        pieChart.setDrawEntryLabels(false);
        pieChart.invalidate();//將圖表刷新以顯示設定的屬性與數值

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            //點開事件
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                PieEntry pieEntry = (PieEntry) e;
                Log.e("test",e.toString());

            }
            //關閉事件
            @Override
            public void onNothingSelected() {
                Log.e("test","關閉");
            }
        });
    }

    @Override
    public void showExpenses(List<ExpensesData> expensesDataList) {
        for(int i = 0 ; i < expensesDataList.size() ; i++){
            TotalRecordData totalRecordData = new TotalRecordData();
            totalRecordData.setId(expensesDataList.get(i).getId());
            totalRecordData.setType(expensesDataList.get(i).getExpensesType());
            totalRecordData.setName(expensesDataList.get(i).getExpensesName());
            totalRecordData.setValue(expensesDataList.get(i).getExpensesValue());
            totalRecordData.setClassification("expenses");
            totalRecordList.add(totalRecordData);

            totalPrice_food = 0;
            totalPrice_traffic = 0;
            totalPrice_medical = 0;
            totalPrice_pie = 0;
            totalPrice_life = 0;
            totalPrice_3c = 0;
            for (ExpensesData expenses : expensesDataList) {
                if (expenses.getExpensesType().equals("food")) {
                    totalPrice_food += expenses.getExpensesValue();
                }
                if (expenses.getExpensesType().equals("traffic")) {
                    totalPrice_traffic += expenses.getExpensesValue();
                }
                if (expenses.getExpensesType().equals("medical")) {
                    totalPrice_medical += expenses.getExpensesValue();
                }
                if (expenses.getExpensesType().equals("pie")) {
                    totalPrice_pie += expenses.getExpensesValue();
                }
                if (expenses.getExpensesType().equals("life")) {
                    totalPrice_life += expenses.getExpensesValue();
                }
                if (expenses.getExpensesType().equals("3c")) {
                    totalPrice_3c += expenses.getExpensesValue();
                }
            }
        }
        setExpensesPieChart();

    }
}