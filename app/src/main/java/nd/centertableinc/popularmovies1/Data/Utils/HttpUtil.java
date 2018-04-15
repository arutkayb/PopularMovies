package nd.centertableinc.popularmovies1.Data.Utils;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class HttpUtil{
    Context context;

    public HttpUtil(Context context)
    {
        this.context = context;
    }

    public void getRequest(String url, final AsyncDataListener listener) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {
                        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                                Log.e("HttpUtil", "onFailure, " + e.toString());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onDataLoad(response.body().string());
                                }catch (IOException e){
                                    Toast.makeText(context, "Network error: " + e.toString(), Toast.LENGTH_LONG).show();
                                    Log.e("HttpUtil", "onResponse, error: " + e.toString());
                                }
                            }
                        });
                    }
                });
    }

}
