package aruite.tanoshiku.bousaikunren;

import com.google.android.gms.maps.GoogleMap;



import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

import com.google.android.gms.maps.MapFragment;



public class MainActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
	
		return MapFragment.newInstance();
		
	}

}

