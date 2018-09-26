package com.zhjy.cultural.services.patrol.app;

import android.app.Application;

import com.google.gson.Gson;
import com.zhjy.cultural.services.patrol.ui.LoginActivity;
import com.zhjy.cultural.services.patrol.ui.MainActivity;
import com.zhjy.cultural.services.patrol.ui.MainViewModel;
import com.zhjy.cultural.services.patrol.ui.StartViewModel;
import com.zhjy.cultural.services.patrol.ui.culture.CultureLocationActivity;
import com.zhjy.cultural.services.patrol.ui.culture.CultureMainActivity;
import com.zhjy.cultural.services.patrol.ui.culture.CultureMainViewModel;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionViewModel;
import com.zhjy.cultural.services.patrol.ui.home.main.MainListViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.CulturalRelicsMapViewModel;
import com.zhjy.cultural.services.patrol.ui.home.map.search.MapSearchViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.abnormality.InspectionAbnormalityViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.add.InspectionAddActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.add.InspectionAddViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.addcontinue.InspectionAddContinueActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.addcontinue.InspectionAddContinueViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.addresult.InspectionAddResultActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.addresult.InspectionAddResultViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.feedback.InspectionFeedBackActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.feedback.InspectionFeedBackViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.info.InspectionActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.info.RecordInfoViewModel;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;
import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockViewModel;
import com.zhjy.cultural.services.patrol.ui.list.CultureListActivity;
import com.zhjy.cultural.services.patrol.ui.list.CultureListViewModel;
import com.zhjy.cultural.services.patrol.ui.map.BaiduMapActivity;
import com.zhjy.cultural.services.patrol.ui.photo.MyTakePhotoActivity;
import com.zhjy.cultural.services.patrol.ui.route.LocationService;
import com.zhjy.cultural.services.patrol.ui.route.RouteViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.AboutUsActivity;
import com.zhjy.cultural.services.patrol.ui.setup.aboutus.AboutUsViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackActivity;
import com.zhjy.cultural.services.patrol.ui.setup.feedback.FeedBackViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.information.InformationViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.information.MyInformationActivity;
import com.zhjy.cultural.services.patrol.ui.setup.notice.info.NoticeInfoActivity;
import com.zhjy.cultural.services.patrol.ui.setup.notice.info.NoticeInfoViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.notice.list.MyNoticeActivity;
import com.zhjy.cultural.services.patrol.ui.setup.notice.list.NoticeViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.personaldata.PersonalDataActivity;
import com.zhjy.cultural.services.patrol.ui.setup.personaldata.PersonalDataViewModel;
import com.zhjy.cultural.services.patrol.ui.setup.resetpass.ResetPassActivity;
import com.zhjy.cultural.services.patrol.ui.setup.resetpass.ResetPassViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.choice.TreasuresChoiceViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.info.TreasuresViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.list.TreasuresListViewModel;
import com.zhjy.cultural.services.patrol.ui.treasures.map.TreasuresMapSearchActivity;
import com.zhjy.cultural.services.patrol.ui.treasures.map.TreasuresMapSearchViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


/**
 * AppCompoent <br/>
 * Created by xiaqiulei on 2016-06-29.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    Gson gson();

    void inject(AppContext context);

    void inject(StartViewModel model);

    void inject(MainViewModel model);

    void inject(MainListViewModel model);

    void inject(InspectionViewModel model);

    void inject(TreasuresListViewModel model);

    void inject(AboutUsViewModel model);

    void inject(FeedBackViewModel model);

    void inject(ResetPassViewModel model);

    void inject(NoticeViewModel model);

    void inject(InformationViewModel model);

    void inject(NoticeInfoViewModel model);

    void inject(TreasuresViewModel model);

    void inject(PunchClockViewModel model);

    void inject(InspectionAbnormalityViewModel model);

    void inject(InspectionAddContinueViewModel model);

    void inject(InspectionFeedBackViewModel model);

    void inject(InspectionAddResultViewModel  model);

    void inject(TreasuresChoiceViewModel model);

    void inject(TreasuresMapSearchViewModel  model);

    void inject(PersonalDataViewModel model);

    void inject(InspectionAddViewModel model);

    void inject(RecordInfoViewModel model);

    void inject(CulturalRelicsMapViewModel model);

    void inject(MapSearchViewModel model);

    void inject(MainActivity mainActivity);

    void inject(CultureMainActivity cultureMainActivity);

    void inject(CultureMainViewModel cultureMainViewModel);

    void inject(LocationService locationService);

    void inject(RouteViewModel routViewModel);

    void inject(CultureListActivity cultureListActivity);

    void inject(CultureListViewModel cultureListViewModel);

    void inject(CultureLocationActivity cultureLocationActivity);

    void inject(MyTakePhotoActivity myTakePhotoActivity);

    void inject(BaiduMapActivity baiduMapActivity);

    void inject(LoginActivity loginActivity);

    void inject(AboutUsActivity aboutUsActivity);

    void inject(FeedBackActivity feedBackActivity);

    void inject(ResetPassActivity resetPassActivity);

    void inject(MyInformationActivity myInformationActivity);

    void inject(MyNoticeActivity myNoticeActivity);

    void inject(NoticeInfoActivity noticeInfoActivity);

    void inject(TreasuresActivity treasuresActivity);

    void inject(PunchClockActivity punchClockActivity);

    void inject(InspectionAbnormalityActivity inspectionAbnormalityActivity);

    void inject(InspectionAddContinueActivity inspectionAbnormalityFeedBackActivity);

    void inject(InspectionFeedBackActivity inspectionFeedBackActivity);

    void inject(InspectionAddResultActivity inspectionFeedBackResultActivity);

    void inject(InspectionActivity inspectionActivity);

    void inject(TreasuresChoiceActivity treasuresChoiceActivity);

    void inject(TreasuresMapSearchActivity treasuresMapSearchActivity);

    void inject(PersonalDataActivity personalDataActivity);

    void inject(InspectionAddActivity inspectionAddActivity);

}