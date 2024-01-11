package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.RefLinks;
import static FootballGame.Graphics.Assets.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GoalNet extends Item {

    private BufferedImage image;

    public GoalNet(RefLinks refLink, float x, float y, byte flag) {
        super(refLink, x, y, 48, 48);
        if(flag==1)
            this.image = goalNetLeft;
        else
            this.image = corner;
    }

    @Override
    public void Update() {
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}
