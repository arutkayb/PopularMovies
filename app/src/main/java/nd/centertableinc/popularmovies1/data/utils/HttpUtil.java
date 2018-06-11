package nd.centertableinc.popularmovies1.data.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class HttpUtil{
    private HttpUtil(Context context)
    {
    }

    public static void getRequest(final AsyncDataListener listener, final Context context, String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {
                        ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Network error", Toast.LENGTH_LONG).show();
                                Log.e(getClass().getName(), "onFailure, " + e.toString());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                    ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onDataLoad(response.body().string().toString());
                                } catch (IOException ex) {
                                    Log.e(getClass().getName(), "onResponse IO exception: " + ex.toString());
                                }
                            }
                        });
                    }
                });
    }

    public static void navigateToUri(Context context, Uri uri){
        if(uri != null ) {
            Intent httpIntent = new Intent(Intent.ACTION_VIEW);
            httpIntent.setData(uri);

            context.startActivity(httpIntent);
        }else
        {
            Log.d(context.getClass().getName(), "navigateToUri, uri is empty");
        }
    }

}
