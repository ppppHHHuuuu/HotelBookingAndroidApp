package com.example.mobdev_nhom7.remote;

import android.content.Context;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestService {
    private static APIService apiService = APIUtils.getUserService();

    public static List request(Context context, int requestCode, List<String> params) {
        Call<SearchHotelResponseData> callHotel = null;
        final List[] result = {null};
        switch (requestCode) {
            case 1:
//                callHotel = apiService.searchHotels(params.get(0), params.get(1), params.get(2));
            case 2:
                Call<HotelNameResponseData> callHotelName = null;

        }

        callHotel.enqueue(new Callback<SearchHotelResponseData>() {
            @Override
            public void onResponse(Call<SearchHotelResponseData> call, Response<SearchHotelResponseData> response) {
                result[0] = response.body().getData();
            }

            @Override
            public void onFailure(Call<SearchHotelResponseData> call, Throwable t) {
            }
        } );
        return result[0];
    }
}

