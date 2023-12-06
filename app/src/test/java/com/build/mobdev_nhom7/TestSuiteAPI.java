package com.build.mobdev_nhom7;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddFavoriteHotelTest.class,
        DeleteFavoriteHotelTest.class,
        GetAllHotelTest.class,
        GetSuggestedCityTest.class,
        GetAllFeedbackTest.class
})
public class TestSuiteAPI {

}
