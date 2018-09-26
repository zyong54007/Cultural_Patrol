package com.zhjy.cultural.services.patrol.ui.treasures.map;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.app.InjectHelp;
import com.zhjy.cultural.services.patrol.databinding.ActivityTreasuresMapSearchBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacBaseActivity;

public class TreasuresMapSearchActivity extends AacBaseActivity<ActivityTreasuresMapSearchBinding> {

    TreasuresMapSearchViewModel treasuresMapSearchViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_treasures_map_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectHelp.appComponent().inject(this);
        treasuresMapSearchViewModel = ViewModelProviders.of(this, viewModelFactory()).get(TreasuresMapSearchViewModel.class);
    }
}
