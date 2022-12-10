package id.ac.umn.uts_00000048162_arnoldusyitzhakpetramanoppo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.LinkedList;

public class Library extends AppCompatActivity implements RecyclerInterface{

    private LinkedList<ModelVideo> vid = new LinkedList<ModelVideo>();
    public RecyclerViewAdapter adapter;
    private BottomSheetDialog dialog;
    private String gintamaDesc, video3Desc, video4Desc;
    private String supermanDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        gintamaDesc = "The Gintama manga is authored by Sorachi Hideaki for Shounen Jump, " +
                "while its anime adaptation was created by Sunrise/Bandai Namco. It is at its core a post-modernist comedy " +
                "with period drama and science-fiction mixed in.";

        supermanDesc = "Superman (real name Clark Kent, born Kal-El) is one of the last children of Krypton, sent as the dying " +
                "planet's last hope to Earth, where he grew to become its kind, noble protector. Using his flight, enhanced strength, heat vision, " +
                "and various other powers, he protects the planet and the universe.";

        video3Desc = "Ini merupakan video 3 animasi singkat.";

        video4Desc = "Ini merupakan video 4 animasi singkat yang digambar menggunakan tangan.";


        dialog = new BottomSheetDialog(this);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        Bundle bundle = getIntent().getExtras();
        String halo = bundle.getString("message");
        Toast.makeText(this, "Welcome, " + halo, Toast.LENGTH_SHORT).show();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#000000"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Hello, " + halo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        createObject();

        adapter = new RecyclerViewAdapter(getApplicationContext(), vid, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void createObject() {
        vid.add(new ModelVideo("Gintama", gintamaDesc, R.drawable.gintoki, R.raw.gintama));
        vid.add(new ModelVideo("Superman", supermanDesc, R.drawable.superman, R.raw.superman));
        vid.add(new ModelVideo("Video 3", video3Desc, R.drawable.gambar3, R.raw.video1));
        vid.add(new ModelVideo("Video 4", video4Desc, R.drawable.gambar4, R.raw.video2));

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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, VideoPlayer.class);

        intent.putExtra("NAME", vid.get(position).getVideoName());
        intent.putExtra("DESC", vid.get(position).getDescription());
        intent.putExtra("LINK", vid.get(position).getLinkVideo());

        startActivity(intent);

    }

    @Override
    public void onDelete(int position) {
//        vid.remove(position);
//        adapter.notifyItemRemoved(position);
        createBottomDialog(position);
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    private void createBottomDialog(int pos) {

        View view = getLayoutInflater().inflate(R.layout.bottom_dialog, null, false);

        LinearLayout llConfirm = view.findViewById(R.id.confirmDeletion);
        LinearLayout llCancel = view.findViewById(R.id.cancelDeletion);

        llConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                vid.remove(pos);
                adapter.notifyItemRemoved(pos);
                dialog.dismiss();
            }
        });

        llCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);

    }
}