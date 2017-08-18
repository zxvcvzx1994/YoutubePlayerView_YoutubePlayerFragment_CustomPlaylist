package vo.cvcompany.com.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vo.cvcompany.com.myapplication.Presenter.Communicator;
import vo.cvcompany.com.myapplication.View.BlankFragment;
import vo.cvcompany.com.myapplication.View.FragmentList;

public class MainActivity extends AppCompatActivity implements Communicator{

//@BindView(R.id.youtubePlayerView)
//    YouTubePlayerView youTubePlayerView;
    private Fragment fragmentList;
    final String PLAYLIS_API= "PLNCta_i9bcyI8pRui-j7c-5_cCQ7LHTEx";
    final String API= "AIzaSyDit9M3C_qiRtBMnfuzklCglwl7oEIn_-w";
    final String VIDEO_API = "N0mEwb7UOfk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if(savedInstanceState==null){
            fragmentList = new FragmentList();
           getSupportFragmentManager().beginTransaction().add(R.id.lineRecycler, fragmentList,"fragmentlist").commit();
        }else{
            fragmentList  = getSupportFragmentManager().findFragmentByTag("fragmentlist");
        }
//        youTubePlayerView.initialize(API, this);

    }


    @Override
    public void startVideo(String api) {
        BlankFragment myFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("api_video", api);
        myFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.lineRecycler,myFragment, "myfragment" ).commit();
    }

    @OnClick(R.id.btnBack)
    public void btnBack(){
        getSupportFragmentManager().beginTransaction().replace(R.id.lineRecycler, fragmentList).commit();
    }

}
