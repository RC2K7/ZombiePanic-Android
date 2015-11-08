package io.github.rc2k7.zombiepanic;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MapView
        MapView mv = new MapView(this);
        mv.addLayer(new ArcGISTiledMapServiceLayer(
                "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"));
        LocationDisplayManager ldm = mv.getLocationDisplayManager();

        // Activate Location Services
        ldm.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
        ldm.setShowLocation(true);
        ldm.start();

        // Add Graphics Layer
        GraphicsLayer gl = new GraphicsLayer();
        mv.addLayer(gl);

        // Test Points
        SimpleMarkerSymbol sms = new SimpleMarkerSymbol(Color.BLUE, 5, SimpleMarkerSymbol.STYLE.CIRCLE);

        // Calculate Point Geometry
        Point ptGeosms = new Point(2124214, -1211234);

        gl.addGraphic(new Graphic(ptGeosms, sms));

        // Push Content To Activity
        setContentView(mv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
