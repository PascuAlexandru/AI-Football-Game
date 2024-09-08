package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static FootballGame.Maps.Menu.mapType;
import static FootballGame.Graphics.Assets.tribune_ManCity1;
import static FootballGame.Graphics.Assets.tribune_ManCity2;
import static FootballGame.Graphics.Assets.tribune_ManCity3;
import static FootballGame.Graphics.Assets.tribune_ManCity4;
import static FootballGame.Graphics.Assets.tribune_ManCity5;
import static FootballGame.Graphics.Assets.tribune_ManCity6;
import static FootballGame.Graphics.Assets.tribune_Chelsea1;
import static FootballGame.Graphics.Assets.tribune_Chelsea2;
import static FootballGame.Graphics.Assets.tribune_Chelsea3;
import static FootballGame.Graphics.Assets.tribune_Chelsea4;
import static FootballGame.Graphics.Assets.tribune_Chelsea5;
import static FootballGame.Graphics.Assets.tribune_Chelsea6;
import static FootballGame.Graphics.Assets.tribune_Liverpool1;
import static FootballGame.Graphics.Assets.tribune_Liverpool2;
import static FootballGame.Graphics.Assets.tribune_Liverpool3;
import static FootballGame.Graphics.Assets.tribune_Liverpool4;
import static FootballGame.Graphics.Assets.tribune_Liverpool5;
import static FootballGame.Graphics.Assets.tribune_Liverpool6;
import static FootballGame.Graphics.Assets.tribune_Arsenal1;
import static FootballGame.Graphics.Assets.tribune_Arsenal2;
import static FootballGame.Graphics.Assets.tribune_Arsenal3;
import static FootballGame.Graphics.Assets.tribune_Arsenal4;
import static FootballGame.Graphics.Assets.tribune_Arsenal5;
import static FootballGame.Graphics.Assets.tribune_Arsenal6;

public class FanFlags extends Item {
    private BufferedImage image;
    private int fanion;

    public FanFlags(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 1920, 1080);
        if(mapType == 1)
            this.image = tribune_ManCity1;
        else if(mapType == 2)
            this.image = tribune_Arsenal1;
        else if(mapType == 3)
            this.image = tribune_Chelsea1;
        else
            this.image = tribune_Liverpool1;
        fanion = 30;
    }

    @Override
    public void Update() {
        if(fanion==0) {
            fanion = 30;
            if(mapType == 1)
                image = tribune_ManCity1;
            else if(mapType == 2)
                image = tribune_Arsenal1;
            else if(mapType == 3)
                image = tribune_Chelsea1;
            else
                image = tribune_Liverpool1;
        }
        else
        {
            if(mapType == 1) {
                if (fanion == 25)
                    image = tribune_ManCity2;
                else if (fanion == 20)
                    image = tribune_ManCity3;
                else if (fanion == 15)
                    image = tribune_ManCity4;
                else if (fanion == 10)
                    image = tribune_ManCity5;
                else if (fanion == 5)
                    image = tribune_ManCity6;
            }
            else if(mapType == 2){
                if (fanion == 25)
                    image = tribune_Arsenal2;
                else if (fanion == 20)
                    image = tribune_Arsenal3;
                else if (fanion == 15)
                    image = tribune_Arsenal4;
                else if (fanion == 10)
                    image = tribune_Arsenal5;
                else if (fanion == 5)
                    image = tribune_Arsenal6;
            }
            else if(mapType == 3){
                if (fanion == 25)
                    image = tribune_Chelsea2;
                else if (fanion == 20)
                    image = tribune_Chelsea3;
                else if (fanion == 15)
                    image = tribune_Chelsea4;
                else if (fanion == 10)
                    image = tribune_Chelsea5;
                else if (fanion == 5)
                    image = tribune_Chelsea6;
            }
            else {
                if (fanion == 25)
                    image = tribune_Liverpool2;
                else if (fanion == 20)
                    image = tribune_Liverpool3;
                else if (fanion == 15)
                    image = tribune_Liverpool4;
                else if (fanion == 10)
                    image = tribune_Liverpool5;
                else if (fanion == 5)
                    image = tribune_Liverpool6;
            }


            fanion--;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}
