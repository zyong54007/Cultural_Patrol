package com.zhjy.cultural.services.patrol.app;

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

import dagger.Subcomponent;


@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    StartViewModel startViewModel();

    MainViewModel mainViewModel();

    CultureMainViewModel cultureMainViewModel();

    RouteViewModel routeViewModel();

    CultureListViewModel cultureListViewModel();

    MainListViewModel mainListViewModel();

    InspectionViewModel inspectionViewModel();

    TreasuresListViewModel treasuresListViewModel();

    AboutUsViewModel aboutUsViewModel();

    FeedBackViewModel feedBackViewModel();

    ResetPassViewModel resetPassViewModel();

    NoticeViewModel noticeViewModel();

    InformationViewModel informationViewModel();

    NoticeInfoViewModel noticeInfoViewModel();

    TreasuresViewModel treasuresViewModel();

    PunchClockViewModel punchClockViewModel();

    InspectionAbnormalityViewModel inspectionAbnormalityViewModel();

    InspectionAddContinueViewModel inspectionAddContinueViewModel();

    InspectionFeedBackViewModel inspectionFeedBackViewModel();

    InspectionAddResultViewModel inspectionAddResultViewModel();

    TreasuresChoiceViewModel treasuresChoiceViewModel();

    TreasuresMapSearchViewModel  treasuresMapSearchViewModel();

    PersonalDataViewModel personalDataViewModel();

    InspectionAddViewModel inspectionAddViewModel();

    RecordInfoViewModel recordInfoViewModel();

    CulturalRelicsMapViewModel culturalRelicsMapViewModel();

    MapSearchViewModel mapSearchViewModel();
}