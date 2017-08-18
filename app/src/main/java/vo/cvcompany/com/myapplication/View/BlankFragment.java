package vo.cvcompany.com.myapplication.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends YouTubePlayerSupportFragment {
    private static final String TAG =BlankFragment.class.getSimpleName() ;
    private String api_video="";
    final String API= "AIzaSyDit9M3C_qiRtBMnfuzklCglwl7oEIn_-w";
    public BlankFragment() {


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null)
            api_video = getArguments().getString("api_video");
        else
            api_video = savedInstanceState.getString("api_video");

        initialize(API, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.cueVideo(api_video);
                    Log.i(TAG, "onInitializationSuccess: "+api_video);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("api_video", api_video);
    }
}
