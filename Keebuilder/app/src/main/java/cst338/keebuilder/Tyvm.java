package cst338.keebuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransitionImpl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tyvm extends AppCompatActivity {
    TextView tyvm;
    Button gohome;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyvm);
        wireUp();
    }

    private void wireUp(){
        tyvm = findViewById(R.id.tankyou);
        gohome = findViewById(R.id.Back_home);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TopLevelMenu.getTopLevelMenuIntent(getApplicationContext(),-1));
            }
        });
        String s = "\n\n  ____ ____ ____ ____ ____ \n" +
                   " ||t |||h |||a |||n |||k ||\n" +
                   " ||__|||__|||__|||__|||__||\n" +
                   " |/__\\|/__\\|/__\\|/__\\|/__\\|\n" +
                   "  ____ ____ ____ ____\n" +
                   " ||y |||o |||u |||! ||\n" +
                   " ||__|||__|||__|||__||\n" +
                    " |/__\\|/__\\|/__\\|/__\\|";
        tyvm.setText(s);
    }
    public static Intent getIntent (Context c){
        return new Intent(c, Tyvm.class);
    }



}