package anna.sejarahjakarta.splash;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingTask extends AsyncTask<String, Integer, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished();
	}
	private final ProgressBar progressBar;
	private final TextView txt;
	private final LoadingTaskFinishedListener finishedListener;

	public LoadingTask(ProgressBar progressBar, TextView txt, LoadingTaskFinishedListener finishedListener) {
		this.progressBar = progressBar;
		this.txt = txt;
		this.finishedListener = finishedListener;
	}
	@Override
	protected Integer doInBackground(String... params) {
		if(resourcesDontAlreadyExist()){
			downloadResources();
		}
		return 1234;
	}
	private boolean resourcesDontAlreadyExist() {
		return true;
	}
	private void downloadResources() {
		int count = 3;
		for (int i = 0; i < count; i++) {
			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);

			try { 
				Thread.sleep(1000); 
			}catch (InterruptedException ignore) {}
		}
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		progressBar.setProgress(values[0]); 
		txt.setText("Loading: "+values[0]+" %");
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		finishedListener.onTaskFinished(); 
	}
}