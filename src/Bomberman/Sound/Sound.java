package Bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private static final String background = "res/music/background.wav";
    private static final String explosion = "res/music/explosion.wav";
    private static final String gameover = "res/music/die.wav";
    private static final String getItem = "res/music/getitem.wav";
    private static final String mobDie = "res/music/mobdie.wav";
    private static final String pass = "res/music/pass.wav";

    private static File bg ;
    private static File exp;
    private static File go;
    private static File gi;
    private static File md;
    private static File pa;

    private static Clip _bg;
    private static Clip _exp;
    private static Clip _go;
    private static Clip _gi;
    private static Clip _md;
    private static Clip _pa;

    public static void initial(){
        bg = new File(background);
        exp = new File(explosion);
        go = new File(gameover);
        gi = new File(getItem);
        md = new File(mobDie);
        pa = new File(pass);
    }

    public static void playMusic(File file,Clip clip, int loop) throws Exception{
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(loop);
        clip.start();
    }

    public static void stopBackground(){
        try {
            if (_bg != null && _bg.isRunning()) {
                _bg.stop();
            }
//            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBackground(){
        try{
            playMusic(bg, _bg, 100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public static void stopBackground(){
//        stopMusic(_bg);
//    }

    public static void playExplosion(){
        try{
            playMusic(exp, _exp, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playGameOver(){
        try{
            playMusic(go, _go,  0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void playGetItem(){
        try{
            playMusic(gi, _gi,  0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playMobDie(){
        try{
            playMusic(md, _md,  0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void playPass(){
        try{
            playMusic(pa, _pa,  0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
