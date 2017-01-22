package xyz.lgvalle.radiosevilla;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button_start);
    }

    public void playRadio(View view) {
        if (!isPlaying) {
            String url = "http://19093.live.streamtheworld.com/SER_SEVILLA.mp3";
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            isPlaying = true;
            button.setText("Pause");
        } else {
            // Pausar
            mediaPlayer.stop();
            isPlaying = false;
            button.setText("Play");
        }
    }

    public void exit(View view) {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        finish();
    }
}
