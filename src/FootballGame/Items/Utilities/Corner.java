package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.RefLinks;
import static FootballGame.Graphics.Assets.corner;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Corner extends Item {

    private BufferedImage image;

    public Corner(RefLinks refLink, float x, float y) {
        super(refLink, x, y, 48, 48);
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
