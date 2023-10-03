package com.example.it_demo.user.noteBook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.it_demo.R;

public class UserNoteBookActivity extends AppCompatActivity {

    ImageView img_add, img_back_noteBook;

    RecyclerView recyclerView;
    UserNoteBookAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_note_book);
        init();
    }
    private void init(){
        img_add = findViewById(R.id.img_add);
        setupUI();
    }
    private void setupUI(){
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDuplicateUserDialog("test","test");
            }
        });
    }

    private void showDuplicateUserDialog(String title , String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 建立自訂的佈局檔案的 View 物件
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_notebook, null);

        // 取得佈局中的元件
        EditText edit_note_title = dialogView.findViewById(R.id.edit_note_title);
        EditText edit_note_msg = dialogView.findViewById(R.id.edit_note_msg);
        Button btn_note_summit = dialogView.findViewById(R.id.btn_manage_summit);


        // 設定 AlertDialog 的視圖為自訂的佈局
        builder.setView(dialogView);

        // 建立並顯示 AlertDialog
        AlertDialog alertDialog = builder.create();

        btn_note_summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                insertUserNoteBook(String.valueOf(edit_note_title.getText()), String.valueOf(edit_note_msg.getText()));
                alertDialog.dismiss();
            }
        });


        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }
}