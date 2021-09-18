package com.example.GCD17243.photogeotag.CreatePOIActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class PointOfInterestDetailsActivity extends AppCompatActivity  {
  
  private ImageView imageView;
  private PointOfInterest poi;
  private Fragment fragment;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  
    android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    //Get the fragment from the saved stated if possible
    if(savedInstanceState != null) {
      fragment = getFragmentManager().getFragment(savedInstanceState, "fragment");
      fragmentTransaction.replace(R.id.details_fragment, fragment);
    } else {
      //Make a new one if there is no saved fragment
      PointOfInterest poi = getIntent().getParcelableExtra("poi");
      Log.d("details", String.valueOf(poi == null));
      Bundle bundle = new Bundle();
      bundle.putParcelable("poi", poi);
      fragment = new DetailsFragment();
      fragment.setArguments(bundle);
      fragmentTransaction.add(R.id.details_fragment, fragment);
    }
    fragmentTransaction.commit();
  
  
    setContentView(R.layout.activity_point_of_interest_details);
    
  }
  
  @Override
  protected void onStart() {
    super.onStart();
  
  }
  
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    //Save the fragment
    getFragmentManager().putFragment(outState, "fragment", fragment);
  }
}
