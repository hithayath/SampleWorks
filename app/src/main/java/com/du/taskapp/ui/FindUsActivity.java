package com.du.taskapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import com.du.taskapp.R;
import com.du.taskapp.data.findus.PayLoad;
import com.du.taskapp.network.ApiServiceImpl;
import com.du.taskapp.permission.RuntimePermissionController;
import com.du.taskapp.ui.mvp.FindUsPresenter;
import com.du.taskapp.ui.mvp.FindUsPresenterImpl;
import com.du.taskapp.ui.mvp.FindUsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.smartlocation.SmartLocation;

public class FindUsActivity extends AppCompatActivity implements FindUsView {

    public static final String LAT_LONG = "latitude_longitude";
    public static final String MARKER = "marker";

    @BindView(R.id.btn_shops)
    Button mBtnShops;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.loadingView)
    ProgressBar mLoadingView;

    private FindUsPresenter presenter;
    private FindUsShopsAdapter mAdapter;
    private Animation mAnimLeftSlide;
    private boolean mFromLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra(HomeActivity.TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        createPresenter();
        initializeViews();
        presenter.onAttachView(this);
        mBtnShops.callOnClick();// To have initial click event for shops, so we can add other buttons functionality later.
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mFromLocation && mBtnShops.isSelected()) { //To handle return from location settings.
            loadShops();
        }
        mFromLocation = false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();

        mAdapter.clearData();
        mAdapter = null;
        mRecyclerView = null;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void createPresenter() {
        presenter = new FindUsPresenterImpl(new ApiServiceImpl()); // Dagger can be used for DI.
    }

    private void initializeViews() {

        mAdapter = new FindUsShopsAdapter(this, presenter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mAnimLeftSlide = AnimationUtils.loadAnimation(this,
                R.anim.left_slide);
        mAnimLeftSlide.setDuration(700);

    }

    public void onBtnShopClicked(View view) {
        boolean selected = !mBtnShops.isSelected();
        mBtnShops.setSelected(selected);
        if (selected) {
            mBtnShops.setTextColor(ContextCompat.getColor(this, R.color.white));
            loadShops();
        } else {
            mBtnShops.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }

    private void loadShops() {
        presenter.loadShops(getLatitudeAndLongitude());
    }

    @Override
    public void showLoading(boolean show) {
        mLoadingView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setData(List<PayLoad> data) {
        if (data != null && data.size() > 0) {
            mAdapter.updateData(data);
            mRecyclerView.startAnimation(mAnimLeftSlide);
        } else {
            showErrorMessageForFailure(R.string.error_no_items);
        }
    }

    @Override
    public void showErrorMessageForFailure(int msgId) {
        Snackbar.make(mRecyclerView, msgId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessageForFailure(String msg) {
        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG).show();
    }

    public boolean checkLocationEnabled(boolean showDialog) {
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean isLocationEnabled = false;
        try {
            isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex){}

        if(!isLocationEnabled && showDialog) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(getResources().getString(R.string.location_not_enabled));
            dialog.setPositiveButton(getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    mFromLocation = true;
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            });

            dialog.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    onBackPressed();

                }
            });

            dialog.show();

        }
        return isLocationEnabled;
    }

    private double[] getLatitudeAndLongitude() {
        double latLong[] = null;
        try {
            SmartLocation smartLocation = SmartLocation.with(this);
            SmartLocation.LocationControl location = smartLocation != null ? smartLocation.location() : null;
            Location lastLocation = location != null ? location.getLastLocation() : null;
            if (lastLocation != null) {
                latLong = new double[2];
                latLong[0] = lastLocation.getLatitude();
                latLong[1] = lastLocation.getLongitude();
            } else if(checkLocationEnabled(false)) {
                /**
                 * https://developer.android.com/training/location/retrieve-current.html
                 *
                 * The getLastLocation() method returns a Location object with the latitude and longitude coordinates
                 * of a geographic location. The location object may be null in the following situations:

                 1. Location is turned off in the device settings. The result could be null even if the last location
                 was previously retrieved because disabling location also clears the cache.
                 2. The device never recorded its location, which could be the case of a new device or a device that
                 has been restored to factory settings.
                 3. Google Play services on the device has restarted, and there is no active Fused Location Provider client
                 that has requested location after the services restarted.
                 */
                showErrorMessageForFailure(R.string.location_not_found);
            }

        } catch (Throwable t) {
            if(checkLocationEnabled(false)) {
                showErrorMessageForFailure(R.string.location_not_found);
            }
        }
        return latLong;
    }

    public boolean needPermission() {
        return RuntimePermissionController.getInstance().needPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void requestPermission() {
        RuntimePermissionController.getInstance().requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, RuntimePermissionController.REQUEST_ACCESS_LOCATION);
    }

    @Override
    public void onShopListClicked(String marker, double[] latLong) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(MARKER, marker);
        intent.putExtra(LAT_LONG, latLong);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == RuntimePermissionController.REQUEST_ACCESS_LOCATION) {
            if (grantResults.length > 0) {
                int result = grantResults[0];
                if (result == PackageManager.PERMISSION_GRANTED) {
                    loadShops();
                } else if (result == PackageManager.PERMISSION_DENIED) {
                    showErrorMessageForFailure(R.string.location_permission_denied);
                }
            }
        }
    }
}
