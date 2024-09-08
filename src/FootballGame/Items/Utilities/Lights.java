package FootballGame.Items.Utilities;

import FootballGame.Graphics.Assets;
import FootballGame.Items.Item;
import FootballGame.Maps.Map;
import FootballGame.RefLinks;
import static FootballGame.States.PlayState.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lights extends Item {
    private BufferedImage image;
    private float lastCamX, lastCamY;

    public Lights(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 1920, 1080);
        image = Assets.lights;
        lastCamX = x;
        lastCamY = y;
    }

    @Override
    public void Update() {
        if(camX!= lastCamX)
            x -= (-camX) - (-lastCamX);
        if(camY!= lastCamY)
            y -= (-camY) - (-lastCamY);

        lastCamY = camY;
        lastCamX = camX;


        /*
        if(camX!= lastCamX || camY != lastCamY){
            /// Daca mingea inainteaza pe directia NE, ajustam camera
            if(-camX > -lastCamX  && -camY < -lastCamY )
            {
                if(-camX < Map.width -1536 -5)
                    x += camX - lastCamX;
                if(-camY > 5)
                    y += camY - lastCamY;
            }
            /// Daca mingea inainteaza pe directia NW, ajustam camera
            if(camX < lastCamX  && camY < lastCamY)
            {
                if(-camX > 5)
                    x += camX - lastCamX;
                if(-camY > 5)
                    y += camY - lastCamY;
            }
            /// Daca mingea inainteaza pe directia SW, ajustam camera
            if(camX < lastCamX  && camY > lastCamY)
            {
                if(-camX > 5)
                    x += camX - lastCamX;
                if (-camY < (Map.height -768) - 5)
                    y += camY - lastCamY;
            }
            /// Daca mingea inainteaza pe directia SE, ajustam camera
            if(camX > lastCamX  && camY > lastCamY)
            {
                if(-camX < Map.width -1536 -5)
                    x += camX - lastCamX;
                if(-camY < (Map.height -768) - 5)
                    y += camY - lastCamY;
            }
        }
        lastCamY = camY;
        lastCamX = camX;
*/
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}

