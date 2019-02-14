package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.myapplication.adapter.SearchAdapter;
import com.example.dell.myapplication.bean.Query;
import com.example.dell.myapplication.presenter.IPersentermpl;
import com.example.dell.myapplication.view.IView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{
    private SearchAdapter searchAdapter;
    private String path="commodity/v1/findCommodityByKeyword?page=1&count=10&keyword=editText.getText().toString()";
    private IPersentermpl iPersentermpl;
    private RecyclerView recyclerView;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPersentermpl=new IPersentermpl(this);
        recyclerView = findViewById(R.id.recyc);
        editText=findViewById(R.id.serch);


        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPersentermpl.getRequest(path,Query.class);
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);
    }
    @Override
    public void onSuccess(Object data) {
     Query bean= (Query) data;
        List<Query.ResultBean> result = bean.getResult();
        searchAdapter.setList(result);
    }
}
