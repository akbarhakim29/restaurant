package com.bymankind.restaurant.Menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.bymankind.restaurant.AdminActivity;
import com.bymankind.restaurant.R;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CreateMenu extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    ImageView ivCamera,ivGallery ,ivImage;
    CameraPhoto cameraPhoto ;
    GalleryPhoto galleryPhoto ;

    final int CAMERA_REQUEST = 10000  ;
    final int GALLERY_REQUEST = 40000;

    String selectedPhoto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_menu);

        final EditText etID_Types_of_Menu = (EditText) findViewById(R.id.etIDToM);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        final EditText etPicture = (EditText) findViewById(R.id.etPicture);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final Button buttonCreated = (Button) findViewById(R.id.btnCreate);

        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        ivCamera  = (ImageView) findViewById(R.id.ivCamera);
        ivGallery = (ImageView) findViewById(R.id.ivGallery);
        ivImage   = (ImageView) findViewById(R.id.ivImage);

        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),
                            "Something Wrong while taking Photos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);
            }
        });

        buttonCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPhoto == null || selectedPhoto.equals("")) {  // show messages when no images selected
                    Toast.makeText(getApplicationContext(),
                            "No Image Selected.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        final int id_types_of_menu = Integer.parseInt(etID_Types_of_Menu.getText().toString());
                        final String name = etName.getText().toString();
                        final int price = Integer.parseInt(etPrice.getText().toString());
                        final String description = etDescription.getText().toString();
                        final String namePicture = etPicture.getText().toString();
                        // call selected image to variable
                        Bitmap bitmap = ImageLoader.init().from(selectedPhoto).requestSize(1024, 1024).getBitmap();
                        String encodedImage = ImageBase64.encode(bitmap);  // encode image to string base64
                        Log.d(TAG, encodedImage);

                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    int code = jsonResponse.getInt("code");

                                    if (code == 200) {
                                        Toast.makeText(CreateMenu.this, "data inserted", Toast.LENGTH_LONG).show();
                                        Intent successIntent = new Intent(CreateMenu.this, AdminActivity.class);
                                        CreateMenu.this.startActivity(successIntent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateMenu.this);
                                        builder.setMessage("insert data failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        CreateMenuRequest createMenuRequest = new CreateMenuRequest(id_types_of_menu, name, price, namePicture,encodedImage, description, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(CreateMenu.this);
                        queue.add(createMenuRequest);

                    }
                    catch (FileNotFoundException e){
                        Toast.makeText(getApplicationContext(),
                                "Something Wrong while encoding ptotos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //method for select photo from camera or gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == CAMERA_REQUEST){
                String photoPath = cameraPhoto.getPhotoPath();
                selectedPhoto = photoPath;

                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(100,100).getBitmap();
                    ivImage.setImageBitmap(getRotateBitmap(bitmap,90)); //use method rotate image
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "Something Wrong while loading ptotos",Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG,photoPath);
            }
            else if (requestCode == GALLERY_REQUEST){
                Uri uri = data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath = galleryPhoto.getPath();
                selectedPhoto = photoPath;
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(100,100).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "Something Wrong while choosing ptotos",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // method for rotate image
    private Bitmap getRotateBitmap(Bitmap source ,float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap bitmap1 = Bitmap.createBitmap(source, 0,0,source.getWidth(),source.getHeight(),matrix,true);
        return bitmap1;
    }
}
