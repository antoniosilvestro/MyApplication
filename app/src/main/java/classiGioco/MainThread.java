package classiGioco;

import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private static final String TAG = MainThread.class.getSimpleName();
    private SurfaceHolder surfaceHolder;
    private MainGamePanel gamePanel;

    public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    private boolean running;
    public void setRunning (boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long tickCount = 0L;
        Log.d(TAG, "Starting game loop");
        while (running) {
            tickCount++;
            //aggiornamento stato di gioco
            //rendering dello stato di gioco su display
        }
        Log.d(TAG, "Game loop executed " + tickCount + " times");
    }

}

