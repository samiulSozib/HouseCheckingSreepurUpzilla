package com.example.housecheckingsreepur;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.housecheckingsreepur.Configuration.ADD_USER_URL;
import static com.example.housecheckingsreepur.Configuration.KEY_ACTION;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_NAME;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_PITA_SAMI;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_UNION;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_WORD;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_GRAM;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_NATIONAL_ID;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_MOBILE;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_JOMI;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_MONTOBBO;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_JACAIKARI;
import static com.example.housecheckingsreepur.Configuration.KEY_UPOKAR_VOGI_IMAGE;

public class Goysespur extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    EditText upokar_vogir_nam,upokar_vogir_pita_samir_nam,national_id,confirm_national_id,mobile,confirm_mobile,montobbo,jacaikari;
    ImageView imageViewUserImage;
    Spinner gram_para_spinner,word_no_spinner,mot_jomi_spinner;
    String[] gram_spinner={"চাকদহ","চরচাকদহ","নাঙ্গলবাধ","ইছাপুর","কুশাইছাপুর","নবগ্রাম","চন্ডিবর","বড় উদাস","কালিনগর","পাড় কালিনগর","চন্ডিখালি",
            "চতুরিয়া","মাশালিয়া","ছাবিনগর","ছােতুলবাড়িয়া","গোয়ালবাড়ি","গয়েশপুর","বড়তলা","জোকা","নবজোকা","লাট রামনগর","চরজাকা","বাগবাড়িয়া","সভাসতডাঙ্গা"};
    String[] word_number={"১","২","৩","৪","৫","৬","৭","৮","৯"};
    String[] jomi_number={"ভূমিহীন","১ শতক","২ শতক","৩ শতক","৪ শতক","৫ শতক","৬ শতক","৭ শতক","৮ শতক","৯ শতক","১০ শতক"};

    private int PICK_IMAGE_REQUEST = 1;

    Bitmap rbitmap;
    String userImage;

    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME="pref";
    private static final String KEY_REMEMBER="remember";
    private static final String KEY_USERNAME="user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goysespur);

        upokar_vogir_nam=findViewById(R.id.uokar_vogi_id);
        upokar_vogir_pita_samir_nam=findViewById(R.id.upkar_vodi_pita_sami_id);
        national_id=findViewById(R.id.national_id);
        confirm_national_id=findViewById(R.id.confirm_national_id);
        mobile=findViewById(R.id.mobile_number);
        confirm_mobile=findViewById(R.id.condirm_mobile_number);

        montobbo=findViewById(R.id.montobbo_id);
        jacaikari=findViewById(R.id.jacaikari_id);
        imageViewUserImage=findViewById(R.id.image_id);

        gram_para_spinner=findViewById(R.id.gram_id_spinner);
        word_no_spinner=findViewById(R.id.word_number_spinner);
        mot_jomi_spinner=findViewById(R.id.jomir_poriman_id);

        /////////////////////////////
        checkBox=findViewById(R.id.checkbox_id);
        sharedPreferences=getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if (sharedPreferences.getBoolean(KEY_REMEMBER,false)){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }

        jacaikari.setText(sharedPreferences.getString(KEY_USERNAME,""));


        jacaikari.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(this);

        /////////////////////////////

        final ArrayAdapter<String> gram_adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,gram_spinner);
        //
        gram_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //
        gram_para_spinner.setAdapter(gram_adapter);

        final ArrayAdapter<String> word_adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,word_number);
        ////
        word_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ////
        word_no_spinner.setAdapter(word_adapter);

        final ArrayAdapter<String> jomi_adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,jomi_number);
        ////
        jomi_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ////
        mot_jomi_spinner.setAdapter(jomi_adapter);

        ///-------------------stop copy-------------------//
        ///
        national_id.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        mobile.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        ////
        ///-------------------stop copy-------------------//

        ///---------------check 2 string-------------------//
        //
        confirm_national_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1=national_id.getText().toString().trim();
                String s2=confirm_national_id.getText().toString().trim();
                if (s1.equals(s2)){
                    confirm_national_id.setTextColor(Color.GREEN);
                }else {
                    confirm_national_id.setTextColor(Color.RED);
                }
            }
        });




        confirm_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String m1=mobile.getText().toString().trim();
                String m2=confirm_mobile.getText().toString().trim();
                if (m1.equals(m2)){
                    confirm_mobile.setTextColor(Color.GREEN);
                }else{
                    confirm_mobile.setTextColor(Color.RED);
                }
            }
        });
        //
        ///---------------check 2 string-------------------//
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                rbitmap = getResizedBitmap(bitmap,800);//Setting the Bitmap to ImageView
                userImage = getStringImage(rbitmap);
                imageViewUserImage.setImageBitmap(rbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Goysespur.this,MainActivity.class));
        finish();
        super.onBackPressed();
    }

    public void add_user_details(View view) {
        adduser();
    }

    private void adduser() {
    if (upokar_vogir_nam.getText().toString().equals("") || upokar_vogir_pita_samir_nam.getText().toString().equals("") || national_id.getText().toString().equals("") || mobile.getText().toString().equals("")|| montobbo.getText().toString().equals("")||jacaikari.getText().toString().equals("")){
        Toast.makeText(Goysespur.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
    }else if(!national_id.getText().toString().equals(confirm_national_id.getText().toString())){
        Toast.makeText(Goysespur.this,"National Id does not match",Toast.LENGTH_SHORT).show();
    }else if(!mobile.getText().toString().equals(confirm_mobile.getText().toString())){
        Toast.makeText(Goysespur.this,"Moobile Number Does not match",Toast.LENGTH_SHORT).show();
    }else if (mobile.getText().toString().length()!=11){
        Toast.makeText(Goysespur.this,"Mobile Number Must be 11 digit",Toast.LENGTH_SHORT).show();
    }else if((national_id.getText().toString().length()>17) || (national_id.getText().toString().length()<10)){
        Toast.makeText(Goysespur.this,"National id recheck",Toast.LENGTH_SHORT).show();
    }else if(userImage==null){
        Toast.makeText(Goysespur.this,"Image Must not be empty",Toast.LENGTH_SHORT).show();
    }
    else{
        //addItemToSheet();
        ///---------------------
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        final String upokar_vogi=upokar_vogir_nam.getText().toString().trim();
        final String upokar_vogi_pita_sami=upokar_vogir_pita_samir_nam.getText().toString().trim();
        final String union="গয়েশপুর";
        final String word=word_no_spinner.getSelectedItem().toString().trim();
        final String gram=gram_para_spinner.getSelectedItem().toString().trim();
        final String national_id=confirm_national_id.getText().toString().trim();
        final String mobile=confirm_mobile.getText().toString().trim();
        final String jomi=mot_jomi_spinner.getSelectedItem().toString().trim();
        final String comment=montobbo.getText().toString().trim();
        final String jacaikari_nam=jacaikari.getText().toString().trim();
        Log.e("null","values"+userImage);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,ADD_USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(Goysespur.this,response,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Goysespur.this,Goysespur.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Goysespur.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_ACTION,"insert");
                params.put(KEY_UPOKAR_VOGI_NAME,upokar_vogi);
                params.put(KEY_UPOKAR_VOGI_PITA_SAMI,upokar_vogi_pita_sami);
                params.put(KEY_UPOKAR_VOGI_UNION,union);
                params.put(KEY_UPOKAR_VOGI_WORD,word);
                params.put(KEY_UPOKAR_VOGI_GRAM,gram);
                params.put(KEY_UPOKAR_VOGI_NATIONAL_ID,national_id);
                params.put(KEY_UPOKAR_VOGI_MOBILE,mobile);
                params.put(KEY_UPOKAR_VOGI_JOMI,jomi);
                params.put(KEY_UPOKAR_VOGI_IMAGE,userImage);
                params.put(KEY_UPOKAR_VOGI_MONTOBBO,comment);
                params.put(KEY_UPOKAR_VOGI_JACAIKARI,jacaikari_nam);
                return params;
            }

        };

        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
        ///---------------------
    }

    }


    public void selectImage(View view) {
        showFileChooser();
    }

    public void showData(View view) {
        startActivity(new Intent(Goysespur.this,UserList.class));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();
    }
    public void managePrefs(){
        if (checkBox.isChecked()){
            editor.putString(KEY_USERNAME,jacaikari.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER,true);
            editor.apply();
        }else {
            editor.putBoolean(KEY_REMEMBER,false);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }
}
