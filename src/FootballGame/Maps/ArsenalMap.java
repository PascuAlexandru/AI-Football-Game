package FootballGame.Maps;

import FootballGame.Items.Utilities.FanFlags;
import FootballGame.Items.Utilities.Lights;
import FootballGame.Items.Utilities.Staff;
import FootballGame.Items.Utilities.Stands;
import FootballGame.RefLinks;

import java.awt.*;

import static FootballGame.Maps.Menu.highGraphicsSettings;
/*! \class public class DiamondMap extends Map
    \brief Implementeaza notiunea de taram de viteza a jocului.
 */


public class ArsenalMap extends Map {

    private Stands Crowd;
    private FanFlags fanFlags;
    private Staff staff;
    private Lights light;

    /*! \fn public ManCityMap(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ArsenalMap(RefLinks refLink) {
        super(refLink);
        Crowd = new Stands(refLink, 0, 0);
        if(highGraphicsSettings)
        {
            fanFlags = new FanFlags(refLink,0,0);
            staff = new Staff(refLink, 0, 0);
            light = new Lights(refLink, -192, -156);
        }

    }

    public  void Update()
    {
        LeftGoal.Update();
        RightGoal.Update();
        if(highGraphicsSettings) {
            fanFlags.Update();
            staff.Update();
            light.Update();
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
        if(highGraphicsSettings) //Stafful echipelor vor trebuie desenat inainte de tribune, ceele din urma fiind mai apropiate de camera
            staff.Draw(g);
        Crowd.Draw(g);
        if(highGraphicsSettings) {
            fanFlags.Draw(g);
            light.Draw(g);
        }
    }
}
