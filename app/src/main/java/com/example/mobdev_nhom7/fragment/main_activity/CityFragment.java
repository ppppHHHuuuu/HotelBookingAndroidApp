package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.databinding.CardCityBinding;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Alert;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Restaurant;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Todo;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Transportation;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    CardCityBinding cardCityBinding;
    RecyclerView recyclerView;

    // Dump Attribute, for demo only
    private Button cityDetail;
    private TextView citySearchBar;
    private List<CityItem> cityItems;

    public CityFragment() {
        // Required empty public constructor
    }

    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_city, container, false);
        cityDetail = view.findViewById(R.id.button4);
        citySearchBar = view.findViewById(R.id.searchbar);

        cityDetail.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line
            getContext().startActivity(intent);
        });

        citySearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditTextContent();
                getSuggestedCityActivity();
            }
        });

        ImageView imageView_1 = view.findViewById(R.id.image_1);
        BitmapUtil.ggDriveConverter("https://drive.google.com/file/d/1skt3lc9lLpMxZgV-tgQFjnf-VvWA3za3/view?usp=share_link", imageView_1);
        imageView_1.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.putExtra("city_id", "Z6YyrwkuyVbsyaLxOE7E");
            intent.putExtra("city_name", "Ha Noi");
            intent.putExtra("city_country", "Vietnam");
            startActivity(intent);

        });
        ImageView imageView_2 = view.findViewById(R.id.image_2);
        BitmapUtil.ggDriveConverter("https://drive.google.com/file/d/1a9wbKYCyyBQUeZLPa8zzqQJuJlhvtWRa/view", imageView_2);
        imageView_2.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.putExtra("city_id", "aSQphDmzTKedOvUrkQPR");
            intent.putExtra("city_name", "New York");
            intent.putExtra("city_country", "USA");
            startActivity(intent);

        });

        ImageView imageView_3 = view.findViewById(R.id.image_3);
        BitmapUtil.ggDriveConverter("https://drive.google.com/file/d/14TD0Cxq_TtErCaAO6eW8Ocb1wOnsYoZi/view", imageView_3);
        imageView_3.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.putExtra("city_id", "AOLUEoqmAt5lN8IhpQ50");

            intent.putExtra("city_name", "Rome");
            intent.putExtra("city_country", "Italy");
            startActivity(intent);
        });

        ImageView imageView_4 = view.findViewById(R.id.image_4);
        BitmapUtil.ggDriveConverter("https://drive.google.com/file/d/1y-yjZCk44EXPaLMZkvZQW1aZ2xf8-YXj/view?usp=share_link", imageView_4);
        imageView_4.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.putExtra("city_id", "a3gN4bdOKVEgpEjxi5nU");

            intent.putExtra("city_name", "Paris");
            intent.putExtra("city_country", "France");
            startActivity(intent);

        });

        return view;
    }


    private void saveEditTextContent() {
        String editTextContent = citySearchBar.getText().toString();

        // Use SharedPreferences to save the content
        SharedPreferences preferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("destination", editTextContent);
        editor.apply();
    }
    public void getSuggestedCityActivity() {
        Intent intent = new Intent(getContext(), SuggestedCityActivity.class);
        startActivity(intent);
    }
}