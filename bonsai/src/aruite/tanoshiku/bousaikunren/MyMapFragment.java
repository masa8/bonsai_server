package aruite.tanoshiku.bousaikunren;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MyMapFragment extends MapFragment implements  android.location.LocationListener {
	
	private LocationManager mLocationManager;
	
	public static MapFragment newInstance(){
		return new MyMapFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putFloat("ZOOMLEVEL", getMap().getCameraPosition().zoom);
		outState.putDouble("LAT", getMap().getCameraPosition().target.latitude);
		outState.putDouble("LNG", getMap().getCameraPosition().target.longitude);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		
		
		setHasOptionsMenu(true);
		
		View view =  super.onCreateView(inflater, 
					container ,
					savedInstanceState);
		


		
		
		
		
		return view;
	
	}

	@Override
	public void onResume() {
		super.onResume();

		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getApplicationContext());
		if (result == ConnectionResult.SUCCESS){
			mLocationManager =
			        (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
			if ( mLocationManager != null ){
				mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						10000,          // 10-second interval.
						10,             // 10 meters.
						this);
			}

			
		}else{
		}

	}

	@Override
	public void onPause() {
		/** Save all the model data */
		
		if ( mLocationManager != null ){
			mLocationManager.removeUpdates(this);
		}
		super.onPause();
	}



	@Override
	public void onLocationChanged(Location location) {
	    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
        getMap().moveCamera(CameraUpdateFactory.newLatLng(latLng));

	}
	
	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg1 == 0){
			int number = arg2.getIntExtra("SAIKORO_RESULT",-1);
			if ( number != -1){
			}
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}