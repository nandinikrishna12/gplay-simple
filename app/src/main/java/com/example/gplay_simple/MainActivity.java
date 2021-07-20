package com.example.gplay_simple;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readfile();
        int num = apps.size();
        changedata(apps.get(i).getIcon_filename());

        Button skipButton = findViewById(R.id.skipbutton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i < num - 1){
                    i++;
                    changedata(apps.get(i).getIcon_filename());
                }
            }
        });
        Button backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i >= 1){
                    i--;
                    changedata(apps.get(i).getIcon_filename());
                }
            }
        });
        Button installButton = findViewById(R.id.installbutton);
        Button uninstallButton = findViewById(R.id.uninstallbutton);
        installButton.setVisibility(View.VISIBLE);
        uninstallButton.setVisibility(View.GONE);

        installButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(apps.get(i).isWarning_display()){
                    warningdialog();
                }
                else{
                    apps.get(i).setInstalled(true);
                    whichButton(i);
                }
            }
        });
        uninstallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apps.get(i).setInstalled(false);
                whichButton(i);
            }
        });


    }

    int i = 0;
    private List<App> apps = new ArrayList<>();

    private void readfile()
    {
        InputStream inputstream = getResources().openRawResource(R.raw.appslist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));

        String line;
        try{
            //skip first line
            reader.readLine();

            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                boolean warn;
                if(row[7].trim().equals("TRUE") || row[7].trim().equals("\"TRUE\"")){
                    warn = true;
                }
                else{
                    warn = false;
                }

                App temp = new App(row[0].trim().substring(1, row[0].length() -1), row[1].trim().substring(1, row[1].length() -2),
                        row[2].trim().substring(1, row[2].length() -2), row[3].trim().substring(1, row[3].length() -2),
                        Integer.parseInt(row[4].trim()), Integer.parseInt(row[5].trim()), Double.parseDouble(row[6].trim()),
                        warn, row[8].trim().substring(1, row[8].length() -2));
                apps.add(temp);

            }
        }  catch(IOException e){
            throw new RuntimeException("Error in reading CSV file");
        }
    }
    private void changeImage(String filename)
    {
        try{
            InputStream inputstream = getAssets().open(filename);
            Drawable temp = Drawable.createFromStream(inputstream, null);
            ImageView icon = findViewById(R.id.icon);
            icon.setImageDrawable(temp);
        }
        catch(IOException ex){
            return;
        }
    }

    private void changedata(String filename){
        TextView appname = findViewById(R.id.appname);
        appname.setText("" + apps.get(i).getAppname());

        changeImage(filename);

        TextView developer = findViewById(R.id.developer);
        developer.setText("" + apps.get(i).getDeveloper());

        TextView rating = findViewById(R.id.rating);
        rating.setText(apps.get(i).getRating() + "★");

        TextView reviews = findViewById(R.id.reviews);
        reviews.setText(apps.get(i).getTotal_reviews() + " reviews");

        TextView downloads = findViewById(R.id.downloads);
        downloads.setText(apps.get(i).getTotal_downloads() + "");

        TextView downloadstext = findViewById(R.id.downloadstext);
        downloadstext.setText("downloads");

        whichButton(i);
    }

    private void warningdialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(""+apps.get(i).getWarning_message()).setTitle("Installation Warning");
        builder.setPositiveButton("Install", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                apps.get(i).setInstalled(true);
                whichButton(i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void whichButton(int i){
        Button installButton = findViewById(R.id.installbutton);
        Button uninstallButton = findViewById(R.id.uninstallbutton);

        if(apps.get(i).isInstalled()){
            installButton.setVisibility(View.GONE);
            uninstallButton.setVisibility(View.VISIBLE);
        }
        else{
            installButton.setVisibility(View.VISIBLE);
            uninstallButton.setVisibility(View.GONE);
        }
    }
}