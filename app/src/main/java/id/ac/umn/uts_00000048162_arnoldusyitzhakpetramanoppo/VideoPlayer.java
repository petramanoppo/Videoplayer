package id.ac.umn.uts_00000048162_arnoldusyitzhakpetramanoppo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity {

    private TextView tvJudul, tvDesc;
    private String name, desc;
    private int linkVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        intentCatch();
        setVideoPlayer();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void intentCatch() {
        name = getIntent().getStringExtra("NAME");
        desc = getIntent().getStringExtra("DESC");
        linkVideo = getIntent().getIntExtra("LINK", 0);
        tvJudul = findViewById(R.id.judulVideo);
        tvDesc = findViewById(R.id.deskripsiVideo);

        tvJudul.setText(name);
        tvDesc.setText(desc);
    }

    private void  setVideoPlayer(){
        VideoView videoView = findViewById(R.id.videoPlayer);
        String videoPath = "android.resource://" + getPackageName()+"/" + linkVideo;
        Uri uri = Uri.parse(videoPath);

        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:
                Intent i = new Intent(this, Profile.class);
                startActivity(i);
                return true;
            case R.id.mainMenu:
                Intent m = new Intent(this, MainActivity.class);
                startActivity(m);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}