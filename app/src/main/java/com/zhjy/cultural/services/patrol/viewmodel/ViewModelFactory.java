package com.zhjy.cultural.services.patrol.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.zhjy.cultural.services.patrol.app.ViewModelSubComponent;
import com.zhjy.cultural.services.patrol.ui.MainViewModel;
import com.zhjy.cultural.services.patrol.ui.StartViewModel;
import com.zhjy.cultural.services.patrol.ui.culture.CultureMainViewModel;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionViewModel;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMapViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.search.MapSearchViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.add.InspectionAddViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.addcontinue.InspectionAddContinueViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.addresult.InspectionAddResultViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.feedback.InspectionFeedBackViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.info.RecordInfoViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockViewModel;
import com.zhjy.cultural.services.patrol.ui.list.CultureListViewModel;
import com.zhjy.cultural.services.patrol.ui.route.RouteViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.AboutUsViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.information.InformationViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.notice.info.NoticeInfoViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.notice.list.NoticeViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.personaldata.PersonalDataViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.resetpass.ResetPassViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.map.TreasuresMapSearchViewModel;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public ViewModelFactory(final ViewModelSubComponent component) {
        creators = new ArrayMap<>();
        creators.put(StartViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.startViewModel();
            }
        });
        creators.put(MainViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.mainViewModel();
            }
        });

        creators.put(CultureMainViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.cultureMainViewModel();
            }
        });

        creators.put(RouteViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.routeViewModel();
            }
        });

        creators.put(CultureListViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.cultureListViewModel();
            }
        });

        creators.put(MainListViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.mainListViewModel();
            }
        });

        creators.put(InspectionViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionViewModel();
            }
        });

        creators.put(TreasuresListViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.treasuresListViewModel();
            }
        });

        creators.put(AboutUsViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.aboutUsViewModel();
            }
        });

        creators.put(FeedBackViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.feedBackViewModel();
            }
        });

        creators.put(ResetPassViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.resetPassViewModel();
            }
        });

        creators.put(NoticeViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.noticeViewModel();
            }
        });

        creators.put(InformationViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.informationViewModel();
            }
        });

        creators.put(NoticeInfoViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.noticeInfoViewModel();
            }
        });

        creators.put(TreasuresViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.treasuresViewModel();
            }
        });

        creators.put(PunchClockViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.punchClockViewModel();
            }
        });

        creators.put(InspectionAbnormalityViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionAbnormalityViewModel();
            }
        });

        creators.put(InspectionAddContinueViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionAddContinueViewModel();
            }
        });

        creators.put(InspectionFeedBackViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionFeedBackViewModel();
            }
        });

        creators.put(InspectionAddResultViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionAddResultViewModel();
            }
        });

        creators.put(TreasuresChoiceViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.treasuresChoiceViewModel();
            }
        });

        creators.put(TreasuresMapSearchViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.treasuresMapSearchViewModel();
            }
        });

        creators.put(PersonalDataViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.personalDataViewModel();
            }
        });

        creators.put(InspectionAddViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.inspectionAddViewModel();
            }
        });

        creators.put(RecordInfoViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.recordInfoViewModel();
            }
        });

        creators.put(CulturalRelicsMapViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.culturalRelicsMapViewModel();
            }
        });

        creators.put(MapSearchViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return component.mapSearchViewModel();
            }
        });

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}