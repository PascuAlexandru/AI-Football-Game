package FootballGame.Items.Utilities;

import FootballGame.Items.Item;
import FootballGame.Items.PlayerCity;
import FootballGame.Maps.Map;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import static FootballGame.Graphics.Assets.cursor;
import static FootballGame.States.PlayState.*;

public class CursorPoint extends Item {


    private BufferedImage image;

    public CursorPoint(RefLinks refLink, float x, float y){
        super(refLink, x, y, 48, 48);
        this.image = cursor;
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

        boolean outOfPlay = true;
        for(int i=0;i<NoPlayers;i++)
        {
            //Daca echipa Man City este cea folosita de utilizator, Cursorul va fi doar pe jucatorii acesteia
            if(Player[i].id_team == 1 && PlayerCity.flag==1)
                if(Player[i].HasBall) {
                    x = Player[i].GetX();
                    y = Player[i].GetY() - height;
                    outOfPlay = false;
                }else if(Player[i].behavior == "Receive Ball"){
                    x = Player[i].GetX();
                    y = Player[i].GetY() - height;
                    outOfPlay = false;
                }else if(Player[i].behavior == "Control Player"){
                    x = Player[i].GetX();
                    y = Player[i].GetY() - height;
                    outOfPlay = false;
                }
        }
        // Daca meciul este oprit sau mingea de joc nu este in teren, nu apare cursorul jucatorului curent
        if(outOfPlay)
            x = -1234.0f; // cursorul va avea pozitia in afara campului vizual

    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, width, height, null);
    }
}
