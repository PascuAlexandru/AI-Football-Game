package FootballGame.Items;

import FootballGame.Graphics.Assets;
import FootballGame.Maps.Map;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static FootballGame.Graphics.Assets.*;
import static FootballGame.States.PlayState.camX;
import static FootballGame.States.PlayState.camY;

public class Ball extends Item {

    private BufferedImage image;
    private boolean direction;
    public static int fanion = 0;
    public static boolean power; ///////// flag pentru animatie minge (pasa lunga-scurta)
    public static float actualizareX = 0.0f;
    public static float actualizareY = 0.0f;

    public Ball(RefLinks refLink, float x, float y, boolean direction, boolean power) {
        super(refLink, x, y, 48, 48);
        image = Assets.ball9;
        this.direction = direction;
        this.power = power;
    }

    @Override
    public void Update() {

        if(y < Map.height *2 /7){
            width=40;
            height = 40;}
        else if(y < Map.height *3 /7){
            width=42;
            height = 42;}
        else if(y < Map.height *4 /7){
            width=44;
            height = 44;}
        else if(y < Map.height *5 /7){
            width=46;
            height = 46;}
        else{
            width=48;
            height = 48;}

        float lastX = x, lastY = y;
        if (!direction)
            SetX(2211.0f);
        else
        {
            x = x + actualizareX;
            y = y + actualizareY;
            System.out.println(camX);
          /* if(-camX > 5 || -camX > Tile.TILE_WIDTH * Map.BigMapWidth -1536 -5 || -camY > 5 || -camY < (Tile.TILE_HEIGHT * Map.BigMapHeight -768) - 5){
                camX -= actualizareX;
                camY -= actualizareY;
            }*/

          /// Daca mingea inainteaza pe directia NE, ajustam camera
           if(x > lastX  && y < lastY )
           {
               if(-camX < Map.width -1536 -5)
                   camX -= actualizareX;
               if(-camY > 5)
                   camY -= actualizareY;
           }
            /// Daca mingea inainteaza pe directia NW, ajustam camera
            if(x < lastX  && y < lastY)
            {
                if(-camX > 5)
                    camX -= actualizareX;
                if(-camY > 5)
                    camY -= actualizareY;
            }
            /// Daca mingea inainteaza pe directia SW, ajustam camera
            if(x < lastX  && y > lastY)
            {
                if(-camX > 5)
                    camX -= actualizareX;
                if (-camY < (Map.height -768) - 5)
                    camY -= actualizareY;
            }
            /// Daca mingea inainteaza pe directia SE, ajustam camera
            if(x > lastX  && y > lastY)
            {
                if(-camX < Map.width -1536 -5)
                    camX -= actualizareX;
                if(-camY < (Map.height -768) - 5)
                    camY -= actualizareY;
            }
        }
        /// Animatie short pass/shoot
        if(!power)
            if(fanion==0){
                fanion = 35;
                image = ball1;
            }
            else
            {
                fanion--;
                if(image == ball1 && fanion == 33) image = ball2;
                if(image == ball2 && fanion == 27) image = ball3;
                if(image == ball3 && fanion == 21) image = ball4;
                if(image == ball4 && fanion == 15) image = ball3;
                if(image == ball3 && fanion == 9) image = ball2;
                if(image == ball2 && fanion == 3) image = ball1;
                if(image == ball1 && fanion == 2) image = ball9;
            }
        /// Animatie long pass/shoot
        else{
            if(fanion==0){
                fanion = 55;
                image = ball1;
            }
            else
            {
                fanion--;
                if(image == ball1 && fanion == 54) image = ball2;
                if(image == ball2 && fanion == 51) image = ball3;
                if(image == ball3 && fanion == 48) image = ball4;
                if(image == ball4 && fanion == 44) image = ball5;
                if(image == ball5 && fanion == 40) image = ball6;
                if(image == ball6 && fanion == 35) image = ball7;
                if(image == ball7 && fanion == 30) image = ball8;
                if(image == ball8 && fanion == 25) image = ball7;
                if(image == ball7 && fanion == 21) image = ball6;
                if(image == ball6 && fanion == 17) image = ball5;
                if(image == ball5 && fanion == 13) image = ball4;
                if(image == ball4 && fanion == 10) image = ball3;
                if(image == ball3 && fanion == 7) image = ball2;
                if(image == ball2 && fanion == 4) image = ball1;
                if(image == ball1 && fanion == 2) image = ball9;
            }
        }

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }


}
