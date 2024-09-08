package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static FootballGame.Graphics.Assets.*;

public class Staff extends Item {
    private BufferedImage image;
    private int fanion;

    public Staff(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 1920, 1080);
        fanion = 196;
    }

    @Override
    public void Update() {
        if(fanion==0) {
            fanion = 196;
            image = staff1;
        }
        else
        {
            if(fanion == 192)
                image = staff2;
            if(fanion == 188)
                image = staff3;
            if(fanion == 184)
                image = staff4;
            if(fanion == 180)
                image = staff5;
            if(fanion == 176)
                image = staff6;
            if(fanion == 172)
                image = staff7;
            if(fanion == 168)
                image = staff8;
            if(fanion == 164)
                image = staff9;
            if(fanion == 160)
                image = staff10;
            if(fanion == 156)
                image = staff11;
            if(fanion == 152)
                image = staff12;
            if(fanion == 148)
                image = staff13;
            if(fanion == 144)
                image = staff14;
            if(fanion == 140)
                image = staff15;
            if(fanion == 136)
                image = staff16;
            if(fanion == 132)
                image = staff17;
            if(fanion == 128)
                image = staff18;
            if(fanion == 124)
                image = staff19;
            if(fanion == 120)
                image = staff20;
            if(fanion == 116)
                image = staff21;
            if(fanion == 112)
                image = staff22;
            if(fanion == 108)
                image = staff23;
            if(fanion == 104)
                image = staff24;
            if(fanion == 100)
                image = staff25;
            if(fanion == 96)
                image = staff26;
            if(fanion == 92)
                image = staff27;
            if(fanion == 88)
                image = staff28;
            if(fanion == 84)
                image = staff29;
            if(fanion == 80)
                image = staff30;
            if(fanion == 76)
                image = staff31;
            if(fanion == 72)
                image = staff32;
            if(fanion == 68)
                image = staff33;
            if(fanion == 64)
                image = staff34;
            if(fanion == 60)
                image = staff35;
            if(fanion == 56)
                image = staff36;
            if(fanion == 52)
                image = staff37;
            if(fanion == 48)
                image = staff38;
            if(fanion == 44)
                image = staff39;
            if(fanion == 40)
                image = staff40;
            if(fanion == 36)
                image = staff41;
            if(fanion == 32)
                image = staff42;
            if(fanion == 28)
                image = staff43;
            if(fanion == 24)
                image = staff44;
            if(fanion == 20)
                image = staff45;
            if(fanion == 16)
                image = staff46;
            if(fanion == 12)
                image = staff47;
            if(fanion == 8)
                image = staff48;
            if(fanion == 4)
                image = staff49;

            fanion--;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}


