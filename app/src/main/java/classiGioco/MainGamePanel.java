package classiGioco;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import example.antoniosilvestro.myapplication.MainActivity;

public class MainGamePanel extends SurfaceView implements Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private MainThread thread;

    public MainGamePanel(Context context) {
        super(context);
        // Aggiungiamo callback(this) alla superfice per intercettare gli eventi
        getHolder().addCallback(this);
        // Rendiamo attivo il nostro pannello di gioco in modo che possa gestire gli eventi
        setFocusable(true);

        thread = new MainThread(getHolder(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Il thread deve arrestarsi e aspettare che finisca
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // riproviamo ad arrestare il thread
            }
        }
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getY() > getHeight() - 50) {
                thread.setRunning(false);
                ((MainActivity)getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    }

}