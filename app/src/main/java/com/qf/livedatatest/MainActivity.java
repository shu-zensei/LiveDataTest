package com.qf.livedatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewModelWithLiveData myViewModel;
    TextView textView;
    ImageButton imageButtonLike, imageButtonDotLike;
    private String TAG = "custom_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : worked ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview1);
        imageButtonLike = findViewById(R.id.imageButtonUp);
        imageButtonDotLike = findViewById(R.id.imageButtonDown);
        // 这句话的意思是，把myViewModel和这个Activity绑定到一起了，this代表当前Activity
        myViewModel = new ViewModelProvider(this).get(ViewModelWithLiveData.class);

        // 监视数据的改变,这里添加观察后，是不需要取消观察的，系统自动结束观察，this代表当前Activity
        // 也就是说当 ViewModelWithLiveData 的mutableLiveData值改变的时候就会调用这个函数
        // 这里添加了对 viewMOdel的likedNumber的监视，一点数字改变则调用这个方法
        myViewModel.getLikedNumber().observe(this, new Observer<Integer>() {

            // 当数据发生改变的时候，就会调用这个函数
            @Override
            public void onChanged(Integer integer) {

                Log.d(TAG, "observe : worked ");
                textView.setText(String.valueOf(integer));
            }

        });

        imageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "imageButtonLike listener : worked ");
                myViewModel.addMutableLiveData(1);
            }
        });

        imageButtonDotLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "imageButtonDotLike listener : worked ");
                myViewModel.addMutableLiveData(-1);
            }
        });
    }
}
