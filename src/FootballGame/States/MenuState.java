package FootballGame.States;

import FootballGame.Items.PlayerCity;
import FootballGame.Maps.Menu;
import FootballGame.RefLinks;

import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
   private Menu map;
    public static PlayerCity P;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)   // throws IOException
    {
        super(refLink);
        map = new Menu(refLink);
        P = new PlayerCity(refLink,300,300);
        P.HasBall=true;
    }

    @Override
    public void Update()
    {
        map.Update();
        P.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
        P.Draw(g);
    }
}
