/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.playlist;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author anushka
 */
public class music {
    
    static music player = new music();
    static Clip clip;
    
    private music(){
    }
    
    public static music getInstance(){
        return player;
    }
    
    public static void playMusic(String filepath){
        
        try{
            File musicPath = new File(filepath);

            if(musicPath.exists()){

                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

            }
            
            else{
            System.out.println("Can't find file");
            }
            }
        
        
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println(e.getMessage()); 
        }
    }
    }
