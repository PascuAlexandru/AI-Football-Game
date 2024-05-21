package FootballGame.States;

import FootballGame.Items.*;
import FootballGame.Items.Character;
import FootballGame.Items.Utilities.CursorPoint;
import FootballGame.Maps.ManCityMap;
import FootballGame.RefLinks;
import FootballGame.Maps.Map;

import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class  PlayState extends State
{
    public static PlayerCity speed;/*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    public static final int NoPlayers = 12;
    private Map map = null;     /*!< Referinta catre harta curenta.*/
    public static Ball ball;    /*!< Referinta catre obiectul minge.*/
    public static Character[] Player = new Character[NoPlayers];
    public static float camX, camY; //coordonatele camerei de filmat
    private CursorPoint cursor;
    public static double contor;
    public static ControlCenter controlCenter;

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new ManCityMap(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        PlayerCity.flag = 1;  // astea se stabilesc in MenuState /////////////////////////////////////////////////
        PlayerArsenal.flag = 2;

        controlCenter = new ControlCenter(refLink, Player);

        ball = new Ball(refLink,-48,-48,false,false);

        cursor = new CursorPoint(refLink,Player[0].GetX(),Player[0].GetY());
        camX = -(Map.width - 1536) >> 1;
        camY = -(Map.height - 768) >> 1;

        contor = 0;
    }





    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        /// Daca batalia s-a terminat, jocul revine la starea de meniu.

        map.Update();
        ball.Update();
        controlCenter.Update();
        for(int j=0;j<NoPlayers;j++) {
            Player[j].Update();
        }
        cursor.Update();
        contor ++;
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.translate((int) camX,(int)camY);
        map.Draw(g);
        ball.Draw(g);
        for(int j=0;j<NoPlayers;j++) {
            Player[j].Draw(g);
        }
        cursor.Draw(g);
    }
}
