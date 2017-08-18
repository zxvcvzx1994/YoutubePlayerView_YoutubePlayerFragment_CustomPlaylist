package vo.cvcompany.com.myapplication.View;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vo.cvcompany.com.myapplication.MainActivity;
import vo.cvcompany.com.myapplication.Module.MyVolley;
import vo.cvcompany.com.myapplication.Module.MyYoutube;
import vo.cvcompany.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentList extends Fragment {
    private ArrayList<MyYoutube> arrayList;
    private static final String TAG = MainActivity.class.getSimpleName();
    final String PLAYLIS_API= "PLNCta_i9bcyI8pRui-j7c-5_cCQ7LHTEx";
    final String API= "AIzaSyDit9M3C_qiRtBMnfuzklCglwl7oEIn_-w";
    final String VIDEO_API = "N0mEwb7UOfk";
    //    @BindView(R.id.youtuvePlayer)
    YouTubePlayerView youTubePlayerView;

    private  String url  ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+PLAYLIS_API+"&key="+API;
    private MyAdapter adapter;
    private int i = 0;
    private boolean checkSmooth = false;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        arrayList = new ArrayList<MyYoutube>();
        adapter = new MyAdapter(getActivity(), arrayList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(adapter);

        MyVolley.getMyVolley(getActivity()).startRequestQueue();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                try {
                    JSONArray arraySItems =  response.getJSONArray("items");
                    for(int i  = 0; i< arraySItems.length(); i++){
                        JSONObject objectSnippets = arraySItems.getJSONObject(i);
                        JSONObject objectsnippet = objectSnippets.getJSONObject("snippet");
                        String title = objectsnippet.getString("title");
                        Log.i(TAG, "onResponse: "+title);
                        JSONObject objectThumbnails = objectsnippet.getJSONObject("thumbnails");
                        JSONObject objectMedium = objectThumbnails.getJSONObject("medium");
                        String urlMedium = objectMedium.getString("url");
                        Log.i(TAG, "onResponse: "+urlMedium);
                        JSONObject resouceID  = objectsnippet.getJSONObject("resourceId");
                        String videoID = resouceID.getString("videoId");
                        Log.i(TAG, "onResponse: "+videoID);
                        arrayList.add(new MyYoutube(videoID, title, urlMedium));

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error.getMessage());;
            }
        });

        MyVolley.getMyVolley(getActivity()).addRequest(stringRequest);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(arrayList.size()>0) {

                    handler.postDelayed(this, 1000);
                    if (!checkSmooth) {
                        i++;
                        if (i > arrayList.size() - 1) {
                            i--;
                            checkSmooth = true;
                        }
                    } else {
                        i--;
                        if (i < 0) {
                            i++;
                            checkSmooth = false;
                        }
                    }


                    recyclerView.smoothScrollToPosition(i);
                }}

        },1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyVolley.getMyVolley(getActivity()).stopREquest();
    }
}
