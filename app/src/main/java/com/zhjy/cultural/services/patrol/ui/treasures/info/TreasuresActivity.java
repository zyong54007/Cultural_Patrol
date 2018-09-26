package com.zhjy.cultural.services.patrol.ui.treasures.info;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhjy.cultural.services.patrol.NavigationActivity;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.biz.api.Constants;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.CultureListBean;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetRecordListRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.request.ext.GetTreasuresInfoRequest;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetRecordListResponse;
import com.zhjy.cultural.services.patrol.biz.pojo.response.ext.GetTreasuresInfoResponse;
import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresBinding;
import com.zhjy.cultural.services.patrol.map.NavigationMapActivity;
import com.zhjy.cultural.services.patrol.network.Contant;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;
import com.zhjy.cultural.services.patrol.ui.view.RecycleViewDivider;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import java.util.List;

/**
 * 文物详情  zpf  2018-7-12
 */
public class TreasuresActivity extends AacBaseActivity<ActivityTreasuresBinding> {

    private static String TAG = "TreasuresActivity";

    TreasuresViewModel treasuresViewModel;    //

    TreasuresAdapter treasuresAdapter;

    TreasuresErrorAdapter treasuresErrorAdapter;


    private int page = 0;

    private int id = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_treasures;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    public void detailsmap(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra(Contant.NAVIGTYPE, "map");
        intent.putExtra(Contant.ADDRESS, address);
        startActivity(intent);
    }



//    public void detailsmap(View view) {
//        Intent intent = new Intent(this, NavigationMapActivity.class);
////        intent.putExtra(Contant.NAVIGTYPE, "map");
////        intent.putExtra(Contant.ADDRESS, address);
//        startActivity(intent);
//    }










    private void init() {
        InjectHelp.appComponent().inject(this);
        treasuresViewModel = ViewModelProviders.of(this, viewModelFactory()).get(TreasuresViewModel.class);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        initView();
    }

    private void initView() {
        initListView();
        initListen();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }

    private void initData() {
        treasuresAdapter.clearData();
        treasuresErrorAdapter.clearData();
        loadInfodata();
        LoadListData();
    }


    private void initListView() {
        treasuresErrorAdapter = new TreasuresErrorAdapter(getSupportFragmentManager(), listMoreListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.ycRecyclerList.setLayoutManager(linearLayoutManager);
        binding.ycRecyclerList.setNestedScrollingEnabled(false);
        binding.ycRecyclerList.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));
        binding.ycRecyclerList.setAdapter(treasuresErrorAdapter);

        treasuresAdapter = new TreasuresAdapter(getSupportFragmentManager(), listMoreListener);
        LinearLayoutManager linearLayoutManagerB = new LinearLayoutManager(getContext());
        binding.xjRecyclerList.setLayoutManager(linearLayoutManagerB);
        binding.xjRecyclerList.setNestedScrollingEnabled(false);
        binding.xjRecyclerList.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.gray_back)));
        binding.xjRecyclerList.setAdapter(treasuresAdapter);

    }


    private void loadInfodata() {
        GetTreasuresInfoRequest request = new GetTreasuresInfoRequest(id);
        treasuresViewModel.getTreasuresInfoResult(request).observe(this, new Observer<GetTreasuresInfoResponse>() {
            @Override
            public void onChanged(@Nullable GetTreasuresInfoResponse response) {
                updateView(response);
            }
        });
    }


    private String point;  //当前文物的经纬度
    private String address;

    private void updateView(GetTreasuresInfoResponse response) {
        if (null == response) {
//            Toast.makeText(TreasuresActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return;
        }
        point = response.getPoint();
//        address = response.getAddress();
        address = response.getTitle();

        binding.setTreasuresBean(response);
        String picturePath = response.getPicturePath();

        if (!TextUtils.isEmpty(picturePath)) {
            if (picturePath.startsWith("uploadfile")) {
                binding.setImageUrlPath(Constants.BASE_URL_UP_IMG + response.getPicturePath());
            } else {
                binding.setImageUrlPath(Constants.BASEIMGURL + response.getPicturePath());
            }
        }

//        binding.setImageUrlPath(Constants.BASEURL + response.getPicturePath());
    }

    private void LoadListData() {
        GetRecordListRequest request = new GetRecordListRequest(id, "r", 0);
        treasuresViewModel.getRecordListResult(request).observe(this, new Observer<GetRecordListResponse>() {
            @Override
            public void onChanged(@Nullable GetRecordListResponse getRecordListResponse) {
                updateListView(getRecordListResponse);
            }
        });
        GetRecordListRequest requestError = new GetRecordListRequest(id, "y", 0);
        treasuresViewModel.getRecordListResult(requestError).observe(this, new Observer<GetRecordListResponse>() {
            @Override
            public void onChanged(@Nullable GetRecordListResponse getRecordListResponse) {
                updateListErrorView(getRecordListResponse);
            }
        });
    }

    private GetRecordListResponse recordListResponse;    //正常巡检的

    private void updateListView(GetRecordListResponse response) {
        if (null == response) {
//            Toast.makeText(TreasuresActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

            return;
        }
        if (response.getPageSize() == 0) {
            binding.notdate.setVisibility(View.VISIBLE);


        }
        recordListResponse = response;
        treasuresAdapter.notifyDataSetChanged(response, 1);
        int size = response.getTotal();
        if (size > 3) {
            binding.lineBottomB.setVisibility(View.VISIBLE);
            binding.lineBottomB.requestFocus();
        } else {
            binding.lineBottomB.setVisibility(View.GONE);


        }
//        Log.i("TAG", "=========" + size);
//        if (treasuresAdapter.getItemCount() > 3) {
//        if (size > 3) {
////            treasuresAdapter.Setsize(size);
////            treasuresAdapter.notifyDataSetChanged(response);
//            binding.lineBottomB.setVisibility(View.VISIBLE);
//            binding.lineBottomB.requestFocus();
//        } else {
//            binding.lineBottomB.setVisibility(View.GONE);
//        }
    }


    private GetRecordListResponse responseerror;


    private void updateListErrorView(GetRecordListResponse response) {
        if (null == response) {
            Toast.makeText(TreasuresActivity.this, R.string.network_error, Toast.LENGTH_LONG).show();
            return;
        }
        if (response.getPageSize() == 0) {
            binding.notdateerror.setVisibility(View.VISIBLE);
        }
        responseerror = response;
        treasuresErrorAdapter.notifyDataSetChanged(response, 1);
        int size = response.getTotal();
//        if (treasuresErrorAdapter.getItemCount() > 3) {
        if (size > 3) {
            binding.lineBottom.setVisibility(View.VISIBLE);
            binding.lineBottom.requestFocus();
        } else {
            binding.lineBottom.setVisibility(View.GONE);
        }
    }

    private OnListMoreListener listMoreListener = new OnListMoreListener() {

        @Override
        public void OnListMoreListener(CultureListBean bean) {

        }
    };

    private void initListen() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnAddIa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TreasuresActivity.this, PunchClockActivity.class);
                intent.putExtra("wwId", id);
                intent.putExtra("recordId", 0);
                intent.putExtra(Contant.POINT, point);
                startActivity(intent);
            }
        });


        binding.lineBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastMsg("数据加载完毕");
                treasuresErrorAdapter.clearData();
                treasuresErrorAdapter.notifyDataSetChanged(responseerror, 0);
                binding.lineBottom.setVisibility(View.INVISIBLE);
//                binding.ycRecyclerList
//                treasuresErrorAdapter.setSize(4);
//                treasuresErrorAdapter.notifyDataSetChanged();

            }
        });

        binding.lineBottomB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToastMsg("数据加载完毕");
                treasuresAdapter.clearData();
                treasuresAdapter.notifyDataSetChanged(recordListResponse, 0);
                binding.lineBottomB.setVisibility(View.INVISIBLE);
            }
        });

    }

}
