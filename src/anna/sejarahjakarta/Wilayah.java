package anna.sejarahjakarta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import anna.sejarahjakarta.helper.TempatAdapter;
import anna.sejarahjakarta.model.Tempat;

public class Wilayah extends Activity{

	private ListView jakarta;
	private TempatAdapter db;
	TypedArray images;
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuutama);
        TextView hak = (TextView)findViewById(R.id.hak);
        hak.setText("Pilih Wilayah");
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Wilayah");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setIcon(R.drawable.wisata);
        jakarta = (ListView) findViewById(R.id.listmenu);
        String[] from = new String[] {"icon", "title", "id"};
        int[] to = new int[] { R.id.kateimg, R.id.katetext, R.id.idk};
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        db = new TempatAdapter(this);
        List<Tempat> tempatkan = db.getKategori();
	        for(Tempat tempat : tempatkan){
	            HashMap<String, String> map = new HashMap<String, String>();
	            images = getResources().obtainTypedArray(R.array.kategori);
		        int resourceId = images.getResourceId(tempat.getID(), -1);
	            map.put("icon", Integer.toString(resourceId));
	            images.recycle();
	            map.put("title", tempat.getNama());
	            map.put("id", Integer.toString(tempat.getID()));
	            fillMaps.add(map);
		    }
        db.closeDB();   
            
            
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.listkate, from, to);
        jakarta.setAdapter(adapter);
        
        jakarta.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String idkt = ((TextView)view.findViewById(R.id.idk)).getText().toString();
				String idkts = ((TextView)view.findViewById(R.id.katetext)).getText().toString();
				Intent in = new Intent(Wilayah.this, Wisata.class);
				in.putExtra("id", idkt);
				in.putExtra("title", idkts);
				db.closeDB();
			    images.recycle();
			    System.gc();
			    Runtime.getRuntime().gc();
				startActivity(in);
			}
		});
	 }
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	db.closeDB();
		    images.recycle();
		    System.gc();
		    Runtime.getRuntime().gc();
		    this.finish();
	     }
	     return super.onKeyDown(keyCode, event);
	 }
}
