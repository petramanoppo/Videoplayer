package id.ac.umn.uts_00000048162_arnoldusyitzhakpetramanoppo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginDialog.ExampleDialogListener {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }



    public void openLibrary(View view) {
        openModal();
    }

    public void openProfile(View view) {
        Intent prof =new Intent(this , Profile.class);
        startActivity(prof);
    }

    public void openModal() {
        LoginDialog exampleDialog = new LoginDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");

    }

    public void login(String username){
        this.username = username;
        if(username.equals("")){
            Toast.makeText(this, "Name must be entered", Toast.LENGTH_SHORT).show();
            openModal();
        }else{
            Intent intent = new Intent(this, Library.class);
            intent.putExtra("message", username);
            startActivity(intent);

        }
    }
}