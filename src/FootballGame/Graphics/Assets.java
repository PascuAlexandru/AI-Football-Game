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
    public static BufferedImage goalNetLeft1;
    public static BufferedImage goalNetLeft2;
    public static BufferedImage goalNetLeft3;
    public static BufferedImage goalNetLeft4;
    public static BufferedImage goalNetLeft5;
    public static BufferedImage goalNetLeft6;
    public static BufferedImage goalNetLeft7;
    public static BufferedImage goalNetLeft8;
    public static BufferedImage goalNetLeft9;
    public static BufferedImage goalNetLeft10;
    public static BufferedImage goalNetLeft21;
    public static BufferedImage goalNetLeft22;
    public static BufferedImage goalNetLeft23;
    public static BufferedImage goalNetLeft24;
    public static BufferedImage goalNetLeft25;
    public static BufferedImage goalNetLeft26;
    public static BufferedImage goalNetLeft27;
    public static BufferedImage goalNetLeft28;

    public static BufferedImage goalNetRight;
    public static BufferedImage goalNetRight1;
    public static BufferedImage goalNetRight2;
    public static BufferedImage goalNetRight3;
    public static BufferedImage goalNetRight4;
    public static BufferedImage goalNetRight5;
    public static BufferedImage goalNetRight6;
    public static BufferedImage goalNetRight7;
    public static BufferedImage goalNetRight8;
    public static BufferedImage goalNetRight9;
    public static BufferedImage goalNetRight10;
    public static BufferedImage goalNetRight21;
    public static BufferedImage goalNetRight22;
    public static BufferedImage goalNetRight23;
    public static BufferedImage goalNetRight24;
    public static BufferedImage goalNetRight25;
    public static BufferedImage goalNetRight26;
    public static BufferedImage goalNetRight27;
    public static BufferedImage goalNetRight28;



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

        goalNetLeft = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft.png");
        goalNetLeft1 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft1.png");
        goalNetLeft2 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft2.png");
        goalNetLeft3 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft3.png");
        goalNetLeft4 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft4.png");
        goalNetLeft5 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft5.png");
        goalNetLeft6 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft6.png");
        goalNetLeft7 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft7.png");
        goalNetLeft8 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft8.png");
        goalNetLeft9 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft9.png");
        goalNetLeft10 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft10.png");
        goalNetLeft21 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft21.png");
        goalNetLeft22 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft22.png");
        goalNetLeft23 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft23.png");
        goalNetLeft24 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft24.png");
        goalNetLeft25 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft25.png");
        goalNetLeft26 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft26.png");
        goalNetLeft27 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft27.png");
        goalNetLeft28 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetLeft28.png");

        goalNetRight = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight.png");
        goalNetRight1 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight1.png");
        goalNetRight2 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight2.png");
        goalNetRight3 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight3.png");
        goalNetRight4 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight4.png");
        goalNetRight5 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight5.png");
        goalNetRight6 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight6.png");
        goalNetRight7 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight7.png");
        goalNetRight8 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight8.png");
        goalNetRight9 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight9.png");
        goalNetRight10 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight10.png");
        goalNetRight21 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight3.png");
        goalNetRight22 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight22.png");
        goalNetRight23 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight23.png");
        goalNetRight24 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight24.png");
        goalNetRight25 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight25.png");
        goalNetRight26 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight26.png");
        goalNetRight27 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight27.png");
        goalNetRight28 = ImageLoader.LoadImage("/textures/GoalPost/GoalNetRight28.png");


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
