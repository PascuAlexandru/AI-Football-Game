package FootballGame.Maps;

import FootballGame.Items.Utilities.FanFlags;
import FootballGame.Items.Utilities.Stands;
import FootballGame.RefLinks;

import java.awt.*;

import static FootballGame.Maps.Menu.highGraphicsSettings;
/*! \class public class DiamondMap extends Map
    \brief Implementeaza notiunea de taram de viteza a jocului.
 */


public class ChelseaMap extends Map {

    private Stands Crowd;
    private FanFlags fanFlags;

    /*! \fn public ManCityMap(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ChelseaMap(RefLinks refLink) {
        super(refLink);
        Crowd = new Stands(refLink, 0, 0);
        if(highGraphicsSettings)
        {
            fanFlags = new FanFlags(refLink,0,0);
        }

    }

    public  void Update()
    {
        LeftGoal.Update();
        RightGoal.Update();
        if(highGraphicsSettings) {
            fanFlags.Update();
        }
    }

    @Override
    public void Draw(Graphics g) {
        super.Draw(g);
        LeftGoal.Draw(g);
        RightGoal.Draw(g);
        corner[0].Draw(g);
        corner[1].Draw(g);
        corner[2].Draw(g);
        corner[3].Draw(g);
        Crowd.Draw(g);
        if(highGraphicsSettings) {
            fanFlags.Draw(g);
        }
    }
}
