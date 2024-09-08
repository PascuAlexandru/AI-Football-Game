package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

import static FootballGame.Maps.Menu.mapType;
import static FootballGame.Graphics.Assets.tribune_Blue;
import static FootballGame.Graphics.Assets.tribune_Red;

public class Stands extends Item {
    private BufferedImage image;

    public Stands(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 1920, 1080);
        if(mapType == 1 || mapType == 3)
            this.image = tribune_Blue;
        else
            this.image = tribune_Red;
    }

    @Override
    public void Update() {
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x,(int)y,null);
    }
}
