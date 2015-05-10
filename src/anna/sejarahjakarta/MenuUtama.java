package anna.sejarahjakarta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MenuUtama extends Activity{

	private ListView jakarta;
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuutama);
        getActionBar().setTitle("Sejarah Jakarta");
        jakarta = (ListView) findViewById(R.id.listmenu);
        FrameLayout judul = (FrameLayout)findViewById(R.id.judul);
        judul.setVisibility(View.GONE);
        String[] from = new String[] {"icon", "title"};
        int[] to = new int[] { R.id.listimage, R.id.listtext};
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        //Info Jakarta    
        HashMap<String, String> map = new HashMap<String, String>();
            int jkt = R.drawable.map;
            map.put("icon", Integer.toString(jkt));
            map.put("title", "Profil Jakarta");
            fillMaps.add(map);
        
        // Menu kate
        HashMap<String, String> map2 = new HashMap<String, String>();
            int wisata = R.drawable.wisata;
            map2.put("icon", Integer.toString(wisata));
            map2.put("title", "Wisata Sejarah");
            fillMaps.add(map2);
        
        //About
        HashMap<String, String> map3 = new HashMap<String, String>();
            int about = R.drawable.about;
            map3.put("icon", Integer.toString(about));
            map3.put("title", "Tentang");
            fillMaps.add(map3);
            
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.listview, from, to);
        jakarta.setAdapter(adapter);
        
        jakarta.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					Intent i = new Intent(MenuUtama.this, Profile.class);
					i.putExtra("id", "0");
					startActivity(i);
				}else if (position == 1) {
					Intent i = new Intent(MenuUtama.this, Wilayah.class);
					startActivity(i);
				}else{
					Intent i = new Intent(MenuUtama.this, AboutMe.class);
					startActivity(i);
				}
			}
		});
	 }
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	       	System.exit(0);
	     }
	     return super.onKeyDown(keyCode, event);
	 }
}
