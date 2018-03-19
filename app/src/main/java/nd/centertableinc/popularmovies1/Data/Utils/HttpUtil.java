package nd.centertableinc.popularmovies1.Data.Utils;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import nd.centertableinc.popularmovies1.Interfaces.AsyncDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class HttpUtil{
    AsyncDataListener listener;
    Context context;

    public HttpUtil(Context context, AsyncDataListener listener)
    {
        this.listener = listener;
        this.context = context;
    }

    public void getRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        ((AppCompatActivity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onDataLoad(response.body().string());
                                }catch (IOException ex){}
                            }
                        });
                    }
                });
    }

}
