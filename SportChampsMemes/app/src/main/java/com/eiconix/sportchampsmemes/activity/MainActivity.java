package com.eiconix.sportchampsmemes.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.eiconix.sportchampsmemes.R;
import com.eiconix.sportchampsmemes.adapter.MemesListAdapter;
import com.eiconix.sportchampsmemes.api.ApiEndPoints;
import com.eiconix.sportchampsmemes.api.MemesApiResponse;
import com.eiconix.sportchampsmemes.api.RetrofitClient;
import com.eiconix.sportchampsmemes.model.Meme;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private ListView listViewMemes;
    private MemesListAdapter adapterMemes;
    private List<Meme> arrayListMemes;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        bindData();
        getDataFromApi();
    }

    private void bindViews() {
        listViewMemes = findViewById(R.id.listView);
    }

    private void bindData() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void getDataFromApi() {
        ApiEndPoints apiService =
                RetrofitClient.getRetrofitClient().create(ApiEndPoints.class);

        Call<MemesApiResponse> call = apiService.getMemes();

        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<MemesApiResponse>() {
            @Override
            public void onResponse(Call<MemesApiResponse> call, Response<MemesApiResponse> response) {
                MemesApiResponse apiResponse = response.body();
                if (apiResponse != null && apiResponse.getData() != null) {
                    arrayListMemes = apiResponse.getData().getMemes();
                    updateData();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MemesApiResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                showDialog(getString(R.string.api_failure), t.getMessage());
            }
        });
    }

    private void updateData() {
        adapterMemes = new MemesListAdapter(arrayListMemes, this);
        listViewMemes.setAdapter(adapterMemes);
    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}