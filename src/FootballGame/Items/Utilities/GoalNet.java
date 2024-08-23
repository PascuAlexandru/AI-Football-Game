package FootballGame.Items.Utilities;

import FootballGame.Graphics.Assets;
import FootballGame.Graphics.ImageLoader;
import FootballGame.Items.ControlCenter;
import FootballGame.Items.Item;
import FootballGame.RefLinks;
import static FootballGame.Graphics.Assets.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GoalNet extends Item {

    private BufferedImage image;
    private int fanion;
    private byte type;

    public GoalNet(RefLinks refLink, float x, float y, byte flag) {
        super(refLink, x, y, 48, 192);
        fanion = 0;
        type = 0;
        if(flag==1)
            this.image = Assets.goalNetLeft;
        else
            this.image = Assets.goalNetRight;
    }

    public void SetAnimation(int Fanion, byte Type) {
        fanion = Fanion;
        type = Type;
    }


    @Override
    public void Update() {
        if(fanion!=0)
        {
            if (type == 1) {
                //animatie pt gol dreapta
                if (image == Assets.goalNetLeft)
                    image = Assets.goalNetLeft21;
                if (image == Assets.goalNetRight)
                    image = Assets.goalNetRight21;
            }else if (type == 2) {
                //animatie pt gol stanga
                if (image == Assets.goalNetLeft)
                    image = Assets.goalNetLeft1;
                if (image == Assets.goalNetRight)
                    image = Assets.goalNetRight1;
            }

            if(image == Assets.goalNetLeft21 && fanion == 44)
                image = Assets.goalNetLeft22;
            if(image == Assets.goalNetLeft22 && fanion == 38)
                image = Assets.goalNetLeft23;
            if(image == Assets.goalNetLeft23 && fanion == 32)
                image = Assets.goalNetLeft24;
            if(image == Assets.goalNetLeft24 && fanion == 26)
                image = Assets.goalNetLeft25;
            if(image == Assets.goalNetLeft25 && fanion == 20)
                image = Assets.goalNetLeft26;
            if(image == Assets.goalNetLeft26 && fanion == 14)
                image = Assets.goalNetLeft27;
            if(image == Assets.goalNetLeft27 && fanion == 8)
                image = Assets.goalNetLeft28;
            if(image == Assets.goalNetLeft28 && fanion == 1) {
                image = Assets.goalNetLeft;
                ControlCenter.goalScored2 = true;
                ControlCenter.goalScoredTeam2++;
            }

            if(image == Assets.goalNetLeft1 && fanion == 45)
                image = Assets.goalNetLeft2;
            if(image == Assets.goalNetLeft2 && fanion == 41)
                image = Assets.goalNetLeft3;
            if(image == Assets.goalNetLeft3 && fanion == 37)
                image = Assets.goalNetLeft4;
            if(image == Assets.goalNetLeft4 && fanion == 32)
                image = Assets.goalNetLeft5;
            if(image == Assets.goalNetLeft5 && fanion == 27)
                image = Assets.goalNetLeft6;
            if(image == Assets.goalNetLeft6 && fanion == 21)
                image = Assets.goalNetLeft7;
            if(image == Assets.goalNetLeft7 && fanion == 16)
                image = Assets.goalNetLeft8;
            if(image == Assets.goalNetLeft8 && fanion == 11)
                image = Assets.goalNetLeft9;
            if(image == Assets.goalNetLeft9 && fanion == 6)
                image = Assets.goalNetLeft10;
            if(image == Assets.goalNetLeft10 && fanion == 1) {
                image = Assets.goalNetLeft;
                ControlCenter.goalScored2 = true;
                ControlCenter.goalScoredTeam2++;
            }

            if(image == Assets.goalNetRight21 && fanion == 44)
                image = Assets.goalNetRight22;
            if(image == Assets.goalNetRight22 && fanion == 38)
                image = Assets.goalNetRight23;
            if(image == Assets.goalNetRight23 && fanion == 32)
                image = Assets.goalNetRight24;
            if(image == Assets.goalNetRight24 && fanion == 26)
                image = Assets.goalNetRight25;
            if(image == Assets.goalNetRight25 && fanion == 20)
                image = Assets.goalNetRight26;
            if(image == Assets.goalNetRight26 && fanion == 14)
                image = Assets.goalNetRight27;
            if(image == Assets.goalNetRight27 && fanion == 8)
                image = Assets.goalNetRight28;
            if(image == Assets.goalNetRight28 && fanion ==1) {
                image = Assets.goalNetRight;
                ControlCenter.goalScored1 = true;
                ControlCenter.goalScoredTeam1++;
            }

            if(image == Assets.goalNetRight1 && fanion == 45)
                image = Assets.goalNetRight2;
            if(image == Assets.goalNetRight2 && fanion == 41)
                image = Assets.goalNetRight3;
            if(image == Assets.goalNetRight3 && fanion == 37)
                image = Assets.goalNetRight4;
            if(image == Assets.goalNetRight4 && fanion == 32)
                image = Assets.goalNetRight5;
            if(image == Assets.goalNetRight5 && fanion == 27)
                image = Assets.goalNetRight6;
            if(image == Assets.goalNetRight6 && fanion == 21)
                image = Assets.goalNetRight7;
            if(image == Assets.goalNetRight7 && fanion == 16)
                image = Assets.goalNetRight8;
            if(image == Assets.goalNetRight8 && fanion == 11)
                image = Assets.goalNetRight9;
            if(image == Assets.goalNetRight9 && fanion == 6)
                image = Assets.goalNetRight10;
            if(image == Assets.goalNetRight10 && fanion ==1) {
                image = Assets.goalNetRight;
                ControlCenter.goalScored1 = true;
                ControlCenter.goalScoredTeam1++;
            }




            // Setez animatiile pentru fiecare poarta
            fanion--;
            type=0;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}
