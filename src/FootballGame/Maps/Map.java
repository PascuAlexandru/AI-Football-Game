package FootballGame.Maps;

import FootballGame.Graphics.ImageLoader;
import FootballGame.Items.Utilities.Corner;
import FootballGame.Items.Utilities.GoalNet;
import FootballGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public abstract class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    public final static int width = 1920;          /*!< Latimea hartii in numar de dale.*/
    public final static int height = 1080;         /*!< Inaltimea hartii in numar de dale.*/
    public static GoalNet LeftGoal;
    public static GoalNet RightGoal;
    private Corner[] corner = new Corner[4];

    ImageLoader BF= new ImageLoader();
    BufferedImage image;
    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink)
    {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
       image = BF.LoadImage("/textures/Stadium.png");
       LeftGoal = new GoalNet(refLink,170,344, (byte) 1);
       RightGoal = new GoalNet(refLink,1650,344, (byte) 2);
       corner[0] = new Corner(refLink,266-48,78-48);
       corner[1] = new Corner(refLink,231-48,973-48);
       corner[2] = new Corner(refLink,1680-48,972-48);
       corner[3] = new Corner(refLink,1646-48,80-48);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {
        LeftGoal.Update();
        RightGoal.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
            g.drawImage(image, 0, 0, width, height, null);
            LeftGoal.Draw(g);
            RightGoal.Draw(g);
            corner[0].Draw(g);
            corner[1].Draw(g);
            corner[2].Draw(g);
            corner[3].Draw(g);
    }
}