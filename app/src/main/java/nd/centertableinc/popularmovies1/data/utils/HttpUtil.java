package nd.centertableinc.popularmovies1.data.utils;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.recycler_view_items.MovieItem;
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
                                    JSONObject res = JsonUtil.createJsonObjFromJsonString(response.body().string());

                                    if(res != null)
                                    {
                                        List<MovieItem> tempMovieItems = MovieItemUtil.getMovieItemsFromTheMovieDbJson(res);

                                        listener.onDataLoad(tempMovieItems);
                                    }

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
