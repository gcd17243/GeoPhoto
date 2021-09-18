package com.example.GCD17243.photogeotag.CreatePOIActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
  
  private FirebaseAuth mAuth;
  //Request code
  private static final int RC_SING_IN = 777;
  
  //List of all the providers (Just email for now)
  private List<AuthUI.IdpConfig> providers = Arrays.asList(
      new AuthUI.IdpConfig.EmailBuilder().build()
  );
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    
    mAuth = FirebaseAuth.getInstance();
  }
  
  @Override
  protected void onStart() {
    super.onStart();
    //Get the current user and update accordingly
    FirebaseUser user = mAuth.getCurrentUser();
    updateUI(user);
  }
  
  //Start the main activity
  public void startMapActivity() {
    Intent mapActivity = new Intent(this, MapActivity.class);
    startActivity(mapActivity);
  }
  
  public void updateUI(FirebaseUser user) {
    //If there's no user
    if(user == null) {
      //Start the login activity
      startActivityForResult(AuthUI.getInstance()
              .createSignInIntentBuilder()
              .setAvailableProviders(providers)
              .build(),
          RC_SING_IN);
    } else {
      //Otherwise start the map activity
      startMapActivity();
    }
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    
    if(requestCode == RC_SING_IN) {
      IdpResponse response = IdpResponse.fromResultIntent(data);
      
      if(resultCode == RESULT_OK) {
        //If the login was successful, launch the main activity
        startMapActivity();
      } else {
      }
    }
  }
  
  @Override
  public void onBackPressed() {
    return;
  }
}
