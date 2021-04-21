package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1;
    ImageView jogador2;



    ImageButton botaoPedra;
    ImageButton botaoPapel;
    ImageButton botaoTesoura;
    Animation teste;
    Animation aparece;

    int jogada1, jogada2 = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alex_play);


        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        botaoPapel = findViewById(R.id.botaoPapel);
        botaoPedra = findViewById(R.id.botaoPedra);
        botaoTesoura = findViewById(R.id.botaoTesoura);

        teste = new AlphaAnimation (1,0);
        aparece = new AlphaAnimation(0,1);

        teste.setDuration(1000);
        aparece.setDuration(1000);

        teste.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaJogador2();



            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
                verificaJogada();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void tocouBotao(View view) {
        tocaSom();
        jogador1.setScaleX(-1);
            switch (view.getId()){
                case(R.id.botaoPedra):jogador1.setImageResource(R.drawable.pedra);
                jogada1 =1;
                break;
                case(R.id.botaoPapel):jogador1.setImageResource(R.drawable.papel);
                jogada1 =2;
                break;
                case(R.id.botaoTesoura):jogador1.setImageResource(R.drawable.tesoura);
                jogada1 =3;
                break;
                }

                jogador2.setImageResource(R.drawable.interrogacao);
                jogador2.startAnimation(teste);

    }

   public void verificaJogada(){
        if (jogada1==jogada2) {
            Toast.makeText(this, "TIE", Toast.LENGTH_SHORT).show();
         }
        if ((jogada1==1&&jogada2==3) ||(jogada1==2&&jogada2==1) || (jogada1==3&&jogada2==2)) {
            Toast.makeText(this, "YOU WIN", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "YOU LOSE", Toast.LENGTH_SHORT).show();
        }



   }
   public void sorteiaJogadaJogador2(){
       Random r = new Random ();
       int numRandom = r.nextInt(3);
       switch (numRandom) {
           case 0: jogador2.setImageResource(R.drawable.pedra);
           jogada2 =1;
           break;
           case 1: jogador2.setImageResource(R.drawable.papel);
           jogada2 =2;
           break;
           case 2: jogador2.setImageResource(R.drawable.tesoura);
           jogada2 =3;
           break;
       }

   }

   public void tocaSom () {
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
   }


}