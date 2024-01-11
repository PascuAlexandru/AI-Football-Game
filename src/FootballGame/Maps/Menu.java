package FootballGame.Maps;

import FootballGame.Graphics.ImageLoader;
import FootballGame.RefLinks;
import FootballGame.States.PlayState;
import FootballGame.States.State;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Menu
    \brief Implementeaza notiunea de meniu activ a jocului.
 */


public class Menu {
    private RefLinks refLink; /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    BufferedImage image; /*!< O referinta catre imaginea ce va fi actualizata pe ecran.*/
    /// Se incarca toate imaginile ce vor procesa "meniul"
    ImageLoader BF= new ImageLoader();

    BufferedImage fight = BF.LoadImage("/textures/Stadium.png");

    /*! \fn public Menu(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Menu(RefLinks refLink)  {
        this.refLink = refLink;
        image=fight;

    }

    /*! \fn public void Update()
       \brief Actualizeaza meniul jocului cu imagini.
    */
    public  void Update() {

        if (image == fight && refLink.GetKeyManager().enter)
        {
            somn();
            State.SetState(new PlayState(refLink));
        }
    }



             /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) meniul jocului.

        \param g Contextul grafic in care trebuie sa deseneze meniul jocului pe ecran.
     */

    public void Draw(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }

    /*! \fn public static void somn()
        \brief Opreste firul de executie pentru 200 de milisecunde numai cat a lua degetul de pe tasta.
     */

    public static void somn(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
