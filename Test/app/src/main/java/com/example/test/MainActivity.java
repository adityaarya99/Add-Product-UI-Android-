package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener ,View.OnClickListener{
    ImageView viewImage;

    Button  imageButtonSelector,imageButtonSelector2,imageButtonSelector3,btnSelectPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCategory();
        spinnerBrand();
        spinnerSize();
        spinnerCondition();

        addListenerOnButton();


        imageButtonSelector = (Button) findViewById(R.id.imageButtonSelector);

        btnSelectPhoto = (Button) findViewById(R.id.btnSelectPhoto);
        imageButtonSelector2 = (Button) findViewById(R.id.imageButtonSelector2);
        imageButtonSelector3 = (Button) findViewById(R.id.imageButtonSelector3);
        btnSelectPhoto.setOnClickListener(this);
        imageButtonSelector.setOnClickListener(this);
        imageButtonSelector2.setOnClickListener(this);
        imageButtonSelector3.setOnClickListener(this);
    }

    private void spinnerCondition() {
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);
        List<String> categories4 = new ArrayList<String>();
        categories4.add(getString(R.string.condition));
        categories4.add("Business Services");
        categories4.add("Computers");
        categories4.add("Education");
        categories4.add("Personal");
        categories4.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories4);

        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner4.setAdapter(dataAdapter4);

    }

    private void spinnerBrand() {
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        List<String> categories3 = new ArrayList<String>();
        categories3.add("Brand  (optional)");
        categories3.add("Business Services");
        categories3.add("Computers");
        categories3.add("Education");
        categories3.add("Personal");
        categories3.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner3.setAdapter(dataAdapter3);
    }

    private void spinnerCategory() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Category (required)");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner.setAdapter(dataAdapter);

    }



    private void spinnerSize() {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Size (required)");
        categories2.add("Business Services");
        categories2.add("Computers");
        categories2.add("Education");
        categories2.add("Personal");
        categories2.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner2.setAdapter(dataAdapter2);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    viewImage.setImageBitmap(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath + "");
                viewImage.setImageBitmap(thumbnail);
            }
        }


    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinnerbasic.xml item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinnerbasic.xml item

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



    public void addListenerOnButton() {

    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btnSelectPhoto:
                selectImage();
                break;
            case R.id.imageButtonSelector:
                imageButtonSelector.setBackground(getResources().getDrawable(R.drawable.red));
               /* imageButtonSelector2.setBackground(getResources().getDrawable(R.drawable.grey));
                imageButtonSelector3.setBackground(getResources().getDrawable(R.drawable.grey));*/
                Toast.makeText(MainActivity.this,
                        "Male Button is  clicked!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonSelector2:
                imageButtonSelector2.setBackground(getResources().getDrawable(R.drawable.red));
                imageButtonSelector.setBackground(getResources().getDrawable(R.drawable.grey));
                imageButtonSelector3.setBackground(getResources().getDrawable(R.drawable.grey));
                Toast.makeText(MainActivity.this,
                        "Female Button is clicked!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageButtonSelector3:
                imageButtonSelector3.setBackground(getResources().getDrawable(R.drawable.red));
                imageButtonSelector.setBackground(getResources().getDrawable(R.drawable.grey));
                imageButtonSelector2.setBackground(getResources().getDrawable(R.drawable.grey));
                Toast.makeText(MainActivity.this,
                        "Others Button is clicked!",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

