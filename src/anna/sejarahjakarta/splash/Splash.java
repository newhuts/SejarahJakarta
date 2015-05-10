package anna.sejarahjakarta.splash;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import anna.sejarahjakarta.MenuUtama;
import anna.sejarahjakarta.R;
import anna.sejarahjakarta.helper.TempatHelper;
import anna.sejarahjakarta.splash.LoadingTask.LoadingTaskFinishedListener;

public class Splash extends Activity implements LoadingTaskFinishedListener {

	private TempatHelper myDbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        TextView txt = (TextView) findViewById(R.id.load);
        new LoadingTask(progressBar, txt, this).execute("splash");
        ngopidatabase();
    }

    @Override
	public void onTaskFinished() {
		completeSplash();
	}

    private void completeSplash(){
		startApp();
		finish();
    }

    private void startApp() {
		Intent intent = new Intent(Splash.this, MenuUtama.class);
		startActivity(intent);
		this.finish();
	}
    private void ngopidatabase(){
		   myDbHelper = new TempatHelper(this);
	       try {
	       	myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
		myDbHelper.close();
	   }
    
}