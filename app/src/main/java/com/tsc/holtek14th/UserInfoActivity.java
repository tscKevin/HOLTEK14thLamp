package com.tsc.holtek14th;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookActivity;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.tsc.holtek14th.FIrebaseAddData.AddTestData;

public class UserInfoActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private String TAG = UserInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_me);

        ImageView userPhoto = findViewById(R.id.imgUserPhoto);
        TextView txName = findViewById(R.id.txName);
        TextView txEmail = findViewById(R.id.txEmail);

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        Uri photoUri = Uri.parse(sharedPreferences.getString("PHOTO", null));
        Log.d(TAG, "onCreate: " + photoUri.toString());
        if (photoUri != null) {
            Picasso.get().load(photoUri).resize(300,300).into(userPhoto);
        }
        txName.setText(sharedPreferences.getString("NAME","null"));
        txEmail.setText(sharedPreferences.getString("EMAIL","null"));
    }

    public void logout(View view){
        Log.d(TAG, "auth is logout");
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    //---navigation listener
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_myLibrary:
//                    mTextMessage.setText(R.string.title_my_library);
//                    Log.d(TAG, "onNavigationItemSelected: "+ new AddTestData());
                    Intent mainPage = new Intent(UserInfoActivity.this, MainActivity.class);
                    startActivity(mainPage);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                    return true;
                case R.id.navigation_discover:
                    Intent discoverPage = new Intent(UserInfoActivity.this,DiscoverActivity.class);
                    startActivity(discoverPage);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_left);
                    return true;
                case R.id.navigation_me:
                    return true;
                case R.id.navigation_more:
//                    mTextMessage.setText(R.string.title_more);
                    return true;
            }
            return false;
        }
    };
}
