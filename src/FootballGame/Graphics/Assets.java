package FootballGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage cursor;
    public static BufferedImage corner;
    public static BufferedImage goalNetLeft;

    public static BufferedImage heroLeft4;
    public static BufferedImage heroLeft41;
    public static BufferedImage heroLeft42;
    public static BufferedImage heroLeft43;

    public static BufferedImage heroRight4;
    public static BufferedImage heroRight41;
    public static BufferedImage heroRight42;
    public static BufferedImage heroRight43;

    public static BufferedImage block4;

    public static BufferedImage ball1;
    public static BufferedImage ball2;
    public static BufferedImage ball3;
    public static BufferedImage ball4;
    public static BufferedImage ball5;
    public static BufferedImage ball6;
    public static BufferedImage ball7;
    public static BufferedImage ball8;
    public static BufferedImage ball9;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet ball = new SpriteSheet(ImageLoader.LoadImage("/textures/ball.png"));
        SpriteSheet fotbal = new SpriteSheet(ImageLoader.LoadImage("/textures/Football.png"));
        SpriteSheet utilities = new SpriteSheet(ImageLoader.LoadImage("/textures/Utilities.png"));

        goalNetLeft = ImageLoader.LoadImage("/textures/GoalNetLeft.png");

        /// Se obtin subimaginile corespunzatoare elementelor necesare.

        heroRight4=fotbal.crop(3,1);
        heroRight41=fotbal.crop(2,1);
        heroRight42=fotbal.crop(1,1);
        heroRight43=fotbal.crop(0,1);

        heroLeft4=fotbal.crop(0,0);
        heroLeft41=fotbal.crop(1,0);
        heroLeft42=fotbal.crop(2,0);
        heroLeft43=fotbal.crop(3,0);

        block4=fotbal.crop(0,0);

        corner = utilities.crop(0,0);
        cursor = utilities.crop(1,0);

        ball1 = ball.crop(0,0);
        ball2 = ball.crop(1,0);
        ball3 = ball.crop(2,0);
        ball4 = ball.crop(3,0);
        ball5 = ball.crop(0,1);
        ball6 = ball.crop(1,1);
        ball7 = ball.crop(2,1);
        ball8 = ball.crop(3,1);
        ball9 = ball.crop(0,2);

    }
}
