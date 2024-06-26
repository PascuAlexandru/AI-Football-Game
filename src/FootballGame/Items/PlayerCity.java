package FootballGame.Items;

import java.awt.*;
import java.lang.Math;
import java.awt.image.BufferedImage;

import FootballGame.Maps.Map;
import FootballGame.RefLinks;
import FootballGame.Graphics.Assets;
import FootballGame.States.PlayState;
import FootballGame.Items.ControlCenter;

import static FootballGame.States.PlayState.*;


/*! \class public class PlayerCity extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class PlayerCity extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private int fanion=0;
    public static String status;  /*!< Retine statusul echipei: defending or attacking  */
    public static byte flag = 0;

    /*! \fn public PlayerCity(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei PlayerCity.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public PlayerCity(RefLinks refLink, float x, float y, byte id)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.heroRight4;
        id_team = 1;
        ID = id;

        homeRegionX = x;
        homeRegionY = y;


        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() {
        ///Actualizeaza imaginea
        /// Actualizeaza daca eroul a fost selectat ca Player 1(care foloseste ArrowKeys)

        /// Ajustez imaginea in adancime(la indepartare)
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

        /// Daca un jucator intercepteaza mingea, stabilim comportamentul dupa caz
        if(ball.GetX() <= GetX() && (GetX() <= ball.GetX()+48) && (ball.GetY() <= GetY()+24) && (GetY()+24 <=ball.GetY()+48))
        {
            HasBall = true;
            ball.setDirection(false);
            ///Schimbam comportamentul jucatorului care era destinat sa primeasca mingea
            for(int i=0;i<NoPlayers;i++)
                if(Player[i].behavior == "Receive Ball"){
                    Player[i].behavior = "Wait";
                    /// Daca
                    if(flag==1)
                        Player[i].behavior = "Control Player";
                }
        }
        if(behavior == "Receive Ball")
        {
            ReceiveBall();
            x -= xMove;
            y -= yMove;
        }


        if(fanion==0) {   /// Daca s-a terminat animatia, poate incepe una noua
            if (flag == 1)   /// Daca jucatoru este al echipei gazda, cea controlata de utilizator, executa instructiunile primite
            {
                if (!HasBall) {
                    if (behavior == "Control Player" && refLink.GetKeyManager().switchPlayer){
                            SwitchPlayer();
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if(behavior == "Control Player"){
                        SetSpeed(2.12f);
                        ///Verifica daca a fost apasata o tasta
                        GetInput();
                        ///Actualizeaza pozitia
                        Move();
                        //Daca e apasata tasta pentru deposedare in jurul oponentului, recupereaza mingea
                        if (refLink.GetKeyManager().tackle){
                            for(int i=0;i<NoPlayers;i++)
                                if(Player[i].HasBall && Player[i].GetX() <= GetX() && (GetX() <= Player[i].GetX()+48) && (Player[i].GetY() <= GetY()+24) && (GetY()+24 <=Player[i].GetY()+48))
                                {
                                    Player[i].HasBall = false;
                                    HasBall = true;
                                    ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                }
                        }


                    }else       // Daca nu este jucatorul controlat de utilizator, va actiona singur
                        {
                            if (behavior == "Chase Ball")
                            {
                                for(int j = 0; j< PlayState.NoPlayers; j++)
                                    if(Player[j].HasBall){
                                        if (Math.abs(Player[j].GetX()-x) > Math.abs(Player[j].GetY()-y))
                                        {
                                            if(Player[j].GetX() > x)
                                                xMove = 2.1f;
                                            else
                                                xMove = -2.1f;
                                            if(Player[j].GetY() > y || xMove < 0)
                                                yMove = (Math.abs(Player[j].GetY()-y) * xMove) / Math.abs(Player[j].GetX()-x);
                                            else
                                                yMove = -(Math.abs(Player[j].GetY()-y) * xMove) / Math.abs(Player[j].GetX()-x);
                                        }else
                                        {
                                            if(Player[j].GetY() > y)
                                                yMove = 2.1f;
                                            else
                                                yMove = -2.1f;
                                            if(Player[j].GetX() > x || yMove < 0)
                                                xMove = (Math.abs(Player[j].GetX()-x) * yMove) / Math.abs(Player[j].GetY()-y);
                                            else
                                                xMove = -(Math.abs(Player[j].GetX()-x) * yMove) / Math.abs(Player[j].GetY()-y);
                                        }
                                        if((Player[j].GetX() <= x) && (x <= Player[j].GetX() + 48) && (Player[j].GetY() <= y + 24) && (y+24 <= Player[j].GetY() + 48)) {
                                            HasBall = true;
                                            Player[j].HasBall = false;
                                            ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                            ///////// Setez animatia pentru tackling //////////////////////////////////////////////////////////////////////////
                                        }
                                    }
                                x+=xMove;
                                y+=yMove;
                            }

                            if(behavior == "Link Up Player"){
                                for(int i = 0; i< PlayState.NoPlayers; i++)
                                    if(Player[i].behavior == "Chase Ball") {
                                        for (int j = 0; j < PlayState.NoPlayers; j++)
                                            if (Player[j].HasBall)
                                                if (Math.abs(Player[i].GetXMove()) < Math.abs(Player[i].GetYMove())) {
                                                    if (y > Player[j].GetY())
                                                        y += Player[i].GetYMove()/3;
                                                    x -= Player[i].GetXMove()/3;
                                                    System.out.println(Player[i].GetXMove() + "," + Player[i].GetYMove());
                                                } else {
                                                if (x > Player[j].GetX())
                                                    x += Player[i].GetXMove()/3;
                                                y -= Player[i].GetYMove()/3;
                                                 System.out.println(Player[i].GetXMove()+","+Player[i].GetYMove());
                                            }


                                }
                            }
                            if(behavior == "Support Attacker"){
                                for(int i = 0; i< PlayState.NoPlayers; i++)
                                    if(Player[i].HasBall){
                                        // Setez pozitia tinta a jucatorului unde se va deplasa
                                        TargetPositionAttacker(true, Player[i].GetX(), Player[i].GetY());
                                    }
                                //Actualizam Pozitia jucatorului
                                x+=xMove;
                                y+=yMove;
                            }

                            if(behavior == "Mark Support Attacker"){
                                for(int i = 0; i< PlayState.NoPlayers; i++)
                                    // Aparatorul va marca jucatorul din atacul echipei adverse
                                    if(Player[i].behavior == "Support Attacker")
                                        TargetPositionDefender(true, Player[i].GetX(), Player[i].GetY());

                                //Actualizam Pozitia jucatorului
                                x+=xMove;
                                y+=yMove;
                            }


                            if(behavior == "Return to Home Region"){
                                // Daca a ajuns pe pozitia lui aproximativa de baza, nu face nimic
                                if(Math.abs(homeRegionX-x) < 3 && Math.abs(homeRegionY-y) < 3)
                                {
                                    behavior = "Wait";
                                }
                                // Altfel, se indreapta catre pozitia de baza
                                else{
                                    if (Math.abs(homeRegionX-x) > Math.abs(homeRegionY-y))
                                    {
                                        if(homeRegionX > x)
                                            xMove = 2.1f;
                                        else
                                            xMove = -2.1f;
                                        if(homeRegionY > y || xMove < 0)
                                            yMove = (Math.abs(homeRegionY-y) * xMove) / Math.abs(homeRegionX-x);
                                        else
                                            yMove = -(Math.abs(homeRegionY-y) * xMove) / Math.abs(homeRegionX-x);
                                    }else
                                    {
                                        if(homeRegionY > y)
                                            yMove = 2.1f;
                                        else
                                            yMove = -2.1f;
                                        if(homeRegionX > x || yMove < 0)
                                            xMove = (Math.abs(homeRegionX-x) * yMove) / Math.abs(homeRegionY-y);
                                        else
                                            xMove = -(Math.abs(homeRegionX-x) * yMove) / Math.abs(homeRegionY-y);
                                    }
                                    x+=xMove;
                                    y+=yMove;
                                }
                            }

                        }
                    //Daca jucatorul este in posesia mingii, utilizatorul ii controleaza toate actiunile
                } else {
                    ///Verifica daca a fost apasata o tasta
                    GetInput();
                    ///Actualizeaza pozitia
                    Move();

                    if (refLink.GetKeyManager().left && refLink.GetKeyManager().up && refLink.GetKeyManager().pass) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("NW");
                        ball.SetX(x - 24);
                        ball.SetY(y - 24);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("SW");
                        ball.SetX(x - 24);
                        ball.SetY(y + 48);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().down) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(2.12f);
                        fanion = 0;
                        if (-camX > 5 && -camY < (Map.height - 768) - 5) {
                            camX += speed;
                            camY -= speed;
                        } else if (-camX > 5) camX += speed;
                        else if (-camY < (Map.height - 768) - 5) camY -= speed;
                    } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().up) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(2.12f);
                        fanion = 0;
                        if (-camX > 5 && -camY > 5) {
                            camX += speed;
                            camY += speed;
                        } else if (-camX > 5) camX += speed;
                        else if (-camY > 5) camY += speed;
                    } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().pass) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("W");
                        ball.SetX(x - 48);
                        ball.SetY(y);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().left) {
                        image = Assets.heroLeft4;
                        SetNormalMode();
                        SetSpeed(3.0f);
                        fanion = 0;
                        if (-camX > 5)
                            camX += speed;
                    } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().up && refLink.GetKeyManager().pass) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("NE");
                        ball.SetX(x);
                        ball.SetY(y);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                        image = Assets.heroLeft4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("SE");
                        ball.SetX(x + 24);
                        ball.SetY(y + 24);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().down) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(2.12f);
                        fanion = 0;
                        if (-camX < Map.width - 1536 - 5 && -camY < (Map.height - 768) - 5) {
                            camX -= speed;
                            camY -= speed;
                        } else if (-camX < Map.width - 1536 - 5) camX -= speed;
                        else if (-camY < (Map.height - 768) - 5) camY -= speed;
                    } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().up) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(2.12f);
                        fanion = 0;
                        if (-camX < Map.width - 1536 - 5 && -camY > 5) {
                            camX -= speed;
                            camY += speed;
                        } else if (-camX < Map.width - 1536 - 5) camX -= speed;
                        else if (-camY > 5) camY += speed;
                    } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().pass) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("E");
                        ball.SetX(x);
                        ball.SetY(y);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().right) {
                        image = Assets.heroRight4;
                        SetNormalMode();
                        SetSpeed(3.0f);
                        fanion = 0;
                        if (-camX < Map.width - 1536 - 5)
                            camX -= speed;
                    } else if (refLink.GetKeyManager().up && refLink.GetKeyManager().pass) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("N");
                        ball.SetX(x - 24);
                        ball.SetY(y - 24);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().up) {
                        image = Assets.block4;
                        SetNormalMode();
                        SetSpeed(3.0f);
                        if (-camY > 5)
                            camY += speed;
                    } else if (refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                        image = Assets.block4;
                        SetAttackMode();
                        SetSpeed(0.0f);
                        fanion = 0;
                        PassingBallCoordonations("S");
                        ball.SetX(x);
                        ball.SetY(y + 48);
                        ball.setDirection(true);
                        HasBall = false;
                        behavior="Return to Home Region";
                    } else if (refLink.GetKeyManager().down) {
                        image = Assets.block4;
                        SetNormalMode();
                        SetSpeed(3.0f);
                        if (-camY < (Map.height - 768) - 5)
                            camY -= speed;
                    }

                }
            }//Daca jucatorul este al echipei oaspete, cea controlata in totalitate de CPU, executa instructiunile primite
            else if(flag==2)
            {
                if (behavior == "Chase Ball")
                {
                    for(int j = 0; j< PlayState.NoPlayers; j++)
                        if(Player[j].HasBall){
                            if (Math.abs(Player[j].GetX()-x) > Math.abs(Player[j].GetY()-y))
                            {
                                if(Player[j].GetX() > x)
                                    xMove = 2.1f;
                                else
                                    xMove = -2.1f;
                                if(Player[j].GetY() > y || xMove < 0)
                                    yMove = (Math.abs(Player[j].GetY()-y) * xMove) / Math.abs(Player[j].GetX()-x);
                                else
                                    yMove = -(Math.abs(Player[j].GetY()-y) * xMove) / Math.abs(Player[j].GetX()-x);
                            }else
                            {
                                if(Player[j].GetY() > y)
                                    yMove = 2.1f;
                                else
                                    yMove = -2.1f;
                                if(Player[j].GetX() > x || yMove < 0)
                                    xMove = (Math.abs(Player[j].GetX()-x) * yMove) / Math.abs(Player[j].GetY()-y);
                                else
                                    xMove = -(Math.abs(Player[j].GetX()-x) * yMove) / Math.abs(Player[j].GetY()-y);
                            }
                            if((Player[j].GetX() <= x) && (x <= Player[j].GetX() + 48) && (Player[j].GetY() <= y + 24) && (y+24 <= Player[j].GetY() + 48)) {
                                HasBall = true;
                                Player[j].HasBall = false;
                                ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                ///////// Setez animatia pentru tackling //////////////////////////////////////////////////////////////////////////
                            }
                        }

                    x+=xMove;
                    y+=yMove;
                }

            }

        }
        else
        {      /// Actualizez animatiile pentru fiecare situatie
            fanion--;
            ///Animatie stanga
            if(image == Assets.heroLeft4 && fanion==10){
                image = Assets.heroLeft41;
                SetSpeed(6.0f);
            }
            if(image == Assets.heroLeft41 && fanion==6) {
                image = Assets.heroLeft42;
                SetSpeed(4.5f);
            }
            if(image == Assets.heroLeft42 && fanion==2) {
                image = Assets.heroLeft43;
                SetSpeed(3.7f);
            }
            ///Animatie dreapta
            if(image == Assets.heroRight4 && fanion==10){
                image = Assets.heroRight41;
                SetSpeed(3.0f);
            }
            if(image == Assets.heroRight41 && fanion==6) {
                image = Assets.heroRight42;
                SetSpeed(3.0f);
            }
            if(image == Assets.heroRight42 && fanion==2) {
                image = Assets.heroRight43;
                SetSpeed(3.0f);
            }
        }
    }






    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        ///Verificare apasare tasta "sus"
        if (refLink.GetKeyManager().up) {
            yMove = -speed;
        }
        ///Verificare apasare tasta "jos"
        if (refLink.GetKeyManager().down) {
            yMove = speed;
        }
        ///Verificare apasare tasta "left"
        if (refLink.GetKeyManager().left) {
            xMove = -speed;
        }
        ///Verificare apasare tasta "dreapta"
        if (refLink.GetKeyManager().right) {
            xMove = speed;
        }

    }

    private void PassingBallCoordonations(String dir){
        float distFromPointToLine = 2000.0f; /// Distanta de la pozitia jucatorului destinatie catre dreapta invizibila ce reprezinta directia
                                             /// dorita la pasa. Initializam cu 2000, reprezentand distanta maxima in pixeli pe harta
        float distanceFromPl2Pl = 5000.0f;   /// Distanta de la jucatorul cu mingea la jucatorul destinatie. Initializat cu 5000 ce reprezinta
                                             /// distanta maxima in pixeli intre doi jucatori
        int framer;                          /// Ajuta la impartirea pe frameuri a animatiei mingii
        int mx=0, my=0;                      /// Reprezinta
        int aux=-1;   ///// voi avea nevoie de el pt functionalitatea WAIT->ReceiveBall la pase
        for(int j = 0; j< PlayState.NoPlayers; j++) {
            if (ID == Player[j].GetID()) {
                /// Daca jucatorul tinta este tot jucatorul curent, nu face nimic
            }
            else
                if(Player[j].GetIdTeam()==id_team)
                {
                    if(dir=="NE")
                        if(x<=Player[j].GetX() && y>=Player[j].GetY())
                            if(DistanceFromPointToStraightLine(x,y,"NE",Player[j].GetX(),Player[j].GetY()) < distFromPointToLine)
                            {
                                distFromPointToLine = DistanceFromPointToStraightLine(x,y,"NE",Player[j].GetX(),Player[j].GetY());
                                Ball.actualizareX = Player[j].GetX()-x;
                                Ball.actualizareY = Player[j].GetY()-y;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="NW")
                        if(x>=Player[j].GetX() && y>=Player[j].GetY())
                            if(DistanceFromPointToStraightLine(x,y,"NW",Player[j].GetX(),Player[j].GetY()) < distFromPointToLine)
                            {
                                distFromPointToLine = DistanceFromPointToStraightLine(x,y,"NW",Player[j].GetX(),Player[j].GetY());
                                Ball.actualizareX = Player[j].GetX()-x;
                                Ball.actualizareY = Player[j].GetY()-y;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="SW")
                        if(x>=Player[j].GetX() && y<=Player[j].GetY())
                            if(DistanceFromPointToStraightLine(x,y,"SW",Player[j].GetX(),Player[j].GetY()) < distFromPointToLine)
                            {
                                distFromPointToLine = DistanceFromPointToStraightLine(x,y,"SW",Player[j].GetX(),Player[j].GetY());
                                Ball.actualizareX = Player[j].GetX()-x-12;
                                Ball.actualizareY = Player[j].GetY()-y-24;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="SE")
                        if(x<=Player[j].GetX() && y<=Player[j].GetY())
                            if(DistanceFromPointToStraightLine(x,y,"SE",Player[j].GetX(),Player[j].GetY()) < distFromPointToLine)
                            {
                                distFromPointToLine = DistanceFromPointToStraightLine(x,y,"SE",Player[j].GetX(),Player[j].GetY());
                                Ball.actualizareX = Player[j].GetX()-x-24;
                                Ball.actualizareY = Player[j].GetY()-y;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="N")
                        if(y>=Player[j].GetY())
                            if(Math.abs(Player[j].GetX()-x) < distFromPointToLine)
                            {
                                distFromPointToLine = Math.abs(Player[j].GetX()-x);
                                Ball.actualizareX = Player[j].GetX()-x;
                                Ball.actualizareY = Player[j].GetY()-y;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="S")
                        if(y<=Player[j].GetY())
                            if(Math.abs(Player[j].GetX()-x) < distFromPointToLine)
                            {
                                distFromPointToLine = Math.abs(Player[j].GetX()-x);
                                Ball.actualizareX = Player[j].GetX()-x-24;
                                Ball.actualizareY = Player[j].GetY()-y-24;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                    if(dir=="W")
                    if(x>=Player[j].GetX())
                        if(Math.abs(Player[j].GetY()-y) < distFromPointToLine)
                        {
                            distFromPointToLine = Math.abs(Player[j].GetY()-y);
                            Ball.actualizareX = Player[j].GetX()-x;
                            Ball.actualizareY = Player[j].GetY()-y;
                            aux = j;
                            distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                        }
                    if(dir=="E")
                        if(x<=Player[j].GetX())
                            if(Math.abs(Player[j].GetY()-y) < distFromPointToLine)
                            {
                                distFromPointToLine = Math.abs(Player[j].GetY()-y);
                                Ball.actualizareX = Player[j].GetX()-x;
                                Ball.actualizareY = Player[j].GetY()-y;
                                aux = j;
                                distanceFromPl2Pl = (float) Math.sqrt((Player[j].GetX()-x)*(Player[j].GetX()-x) + (Player[j].GetY()-y)*(Player[j].GetY()-y));
                            }
                }
        }


        /// Ajustez coeficientii pentru directia mingii pe axele OX si OY in functie de caz
        if(dir.contains("N")) my = -1;
        if(dir.contains("E")) mx = 1;
        if(dir.contains("W")) mx = -1;
        if(dir.contains("S")) my = 1;

        if (aux == -1) {
            Ball.actualizareX = mx*150.0f;
            Ball.actualizareY = my*150.0f;
        }else
            Player[aux].behavior = "Receive Ball";
        /// Daca distanta este mica, ajustam puterea si animatia ShortPass, altfel ajustam LongPass
        if(distanceFromPl2Pl < 500) {
            framer = 62;
            Ball.power = false;
            Ball.fanion = 0;  /// Setam fanionul 0 pentru animatia de inceput a mingii
            Ball.actualizareX = Ball.actualizareX / framer;
            Ball.actualizareY = Ball.actualizareY / framer;
        }
        else {
            framer = 55;
            Ball.power = true;
            Ball.fanion = 0;  /// Setam fanionul 0 pentru animatia de inceput a mingii
            Ball.actualizareX = Ball.actualizareX / framer;
            Ball.actualizareY = Ball.actualizareY / framer;

        }
        // dupa ce a fost data pasa, jucatorul nu ma este cel controlat de utilizator
        behavior = "Wait";
    }

    private float DistanceFromPointToStraightLine(float xSrc, float ySrc, String dir, float x, float y)
    {
        int m=0;
        /// Stabilim panta in functie de gradul dintre dreapta si axa OX
        if(dir == "NE" || dir == "SW") m=-1;
        if(dir == "NW" || dir == "SE") m=1;
        float a=-m, b=1, c=m*xSrc-ySrc;
        return (float) (Math.abs(a*x+b*y+c) / Math.sqrt(a*a+b*b));
      //  return (float) (Math.abs( (xDest-xSrc)*(ySrc-y)-(xSrc-x)*(yDest-y) ) / (Math.sqrt( (xDest-xSrc)*((xDest-xSrc)+(yDest-ySrc)*(yDest-ySrc)) )));
    }

    // Se indreapta  jucatorul spre minge
    private void ReceiveBall()
    {
        if (Math.abs(Ball.actualizareX) > Math.abs(Ball.actualizareY))
        {
            if(Ball.actualizareX > 0)
                xMove = 1.5f;
            else
                xMove = -1.5f;
            yMove = (Ball.actualizareY * xMove) / Ball.actualizareX;
        }else
        {
            if(Ball.actualizareY > 0)
                yMove = 1.5f;
            else
                yMove = -1.5f;
            xMove = (Ball.actualizareX * yMove) / Ball.actualizareY;
        }
    }

    /// Functie care va fi apelata cand utilizatorul va dori sa schimbe jucatorul controlat manual cu cel mai apropiat jucator de minge
    private void SwitchPlayer()
    {
        int closest_player = -1;
        int j = -1;
        double shortest_distance = 5000.0f;
        // Verificam daca un jucator are mingea pentru a il seta ca target
        for(int i=0;i<NoPlayers;i++) {
            if (Player[i].HasBall)
                j = i;
        }
        //Daca niciun jucator nu are mingea, setam mingea ca target: Vom schimba pe cel mai apropiat jucator de minge atunci cand nu este in
        // posesia unui fotbalist
        if(j==-1)
            for(int i=0;i<NoPlayers;i++){
                if(Player[i].id_team == id_team) {
                    if (ID == Player[i].GetID()) {
                        /// Daca jucatorul tinta este tot jucatorul curent, nu face nimic
                    }
                    else if (Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            closest_player = i;
                            shortest_distance = Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2));
                    }
                }
            }
        else //Daca un jucator are mingea, il setam pe el ca target: Vom schimba pe cel mai apropiat jucator de adversarul care se afla in
        // posesia mingii
        {
            for(int i=0;i<NoPlayers;i++){
                if(Player[i].id_team == id_team) {
                    if (ID == Player[i].GetID()) {
                        /// Daca jucatorul tinta este tot jucatorul curent, nu face nimic
                    }
                    else if (Math.sqrt(Math.pow(Player[j].GetX() - Player[i].GetX(), 2) + Math.pow(Player[j].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        closest_player = i;
                        shortest_distance = Math.sqrt(Math.pow(Player[j].GetX() - Player[i].GetX(), 2) + Math.pow(Player[j].GetY() - Player[i].GetY(), 2));
                    }
                }
            }
        }
        Player[closest_player].behavior = "Control Player";
        behavior = "Wait";
    }

    //Jucatorul care urca in atac se va pozitiona mereu la jumatatea distantei dintre coepichierul cu mingea si poarta pe faza de
    // constructie, dar fara sa depaseasca anumite limite teritoriale
    private void TargetPositionAttacker(boolean HomeTeam, float xTeamMate, float yTeamMate)
    {
        float targetX, targetY, xGoal, yGoal = 440.0f;

        // Setam poarta si coordonatele la care va ataca jucatorul in fucntie daca este la echipa gazda sau nu
        if(!HomeTeam) {
            xGoal = 150.0f; /////////////////////////////////////////////////////////// de revenit
            // Daca jumatatea dintre coleg si poarta este prea apropiata de cea din urma, se va opri la limita impusa
            if((xTeamMate + xGoal)/2 < xGoal + 300.0f)
                targetX = xGoal + 300.0f;
            // Daca jumatatea dintre coleg si poarta este prea departata de cea din urma, se va opri la limita impusa
            else if((xTeamMate + xGoal)/2 > xGoal + 850.0f)
                targetX = xGoal + 850.0f;
            // Altfel, targetul va fi chiar jumatatea distantei dintre coepichier si poarta
            else
                targetX = (xTeamMate + xGoal)/2;
        }
        else {
            xGoal = 1800.0f;/////////////////////////////////////////////////////////// de revenit
            // Daca jumatatea dintre coleg si poarta este prea apropiata de cea din urma, se va opri la limita impusa
            if((xTeamMate + xGoal)/2 > xGoal - 300.0f)
                targetX = xGoal - 300.0f;
                // Daca jumatatea dintre coleg si poarta este prea departata de cea din urma, se va opri la limita impusa
            else if((xTeamMate + xGoal)/2 < xGoal - 850.0f)
                targetX = xGoal - 850.0f;
                // Altfel, targetul va fi chiar jumatatea distantei dintre coepichier si poarta
            else
                targetX = (xTeamMate + xGoal)/2;
        }

        // Daca jumatatea dintre coleg si poarta este prea departata de cea din urma, se va opri la limita inferioara impusa
        if((yTeamMate + yGoal)/2 > yGoal + 150.0f)
            targetY = yGoal + 150.0f;
        // Daca jumatatea dintre coleg si poarta este prea departata de cea din urma, se va opri la limita inferioara impusa
        else if((yTeamMate + yGoal)/2 < yGoal - 150.0f)
            targetY = yGoal - 150.0f;
        // Altfel, targetul va fi chiar jumatatea distantei dintre coepichier si poarta
        else
            targetY = (yTeamMate + yGoal)/2;


        //Acum ca avem setate coordonatele target X si Y, actualizam xMove si yMove daca este cazul
        //Ajustam viteza maxima pt yMove si xMove
        if(Math.abs(x-targetX) > Math.abs(y-targetY)){
            if(x < targetX)
                xMove = 2.1f;
            else
                xMove = -2.1f;
            yMove = ((targetY-y) * xMove)/(targetX-x);
        }
        else{
            if(y < targetY)
                yMove = 2.1f;
            else
                yMove = -2.1f;
            xMove = ((targetX-x) * yMove)/(targetY-y);
        }

        ///  Daca jucatorul a ajuns in pozitia target, ramane acolo si nu avanseaza
        if(Math.abs(x-targetX) < 3.0f)
            xMove = 0.0f;
        if(Math.abs(y-targetY) < 3.0f)
            yMove = 0.0f;

    }

    //Jucatorul advers care urca in atac va fi insotit mereu de un fundas care sa il marcheze
    private void TargetPositionDefender(boolean HomeTeam, float xOpponent, float yOpponent)
    {
        float targetX, targetY; // coordonatele pozitiei tinta a jucatorului

        // In functie de echipa gazda sau nu, setam pozitia aparatorului in spatele atacantului
        if(HomeTeam)
            targetX = xOpponent - 85.0f;
        else
            targetX = xOpponent + 85.0f;
        targetY = yOpponent; // Aparatorul va fi pe aceeasi linie cu atacantul advers


        /* Acum ca avem setate coordonatele target X si Y, actualizam xMove si yMove daca este cazul
        ajustam viteza maxima pt yMove si xMove */
        if(Math.abs(x-targetX) > Math.abs(y-targetY)){
            if(x < targetX)
                xMove = 2.1f;
            else
                xMove = -2.1f;
            yMove = ((targetY-y) * xMove)/(targetX-x);
        }
        else{
            if(y < targetY)
                yMove = 2.1f;
            else
                yMove = -2.1f;
            xMove = ((targetX-x) * yMove)/(targetY-y);
        }

        ///  Daca jucatorul a ajuns in pozitia target, ramane acolo si nu avanseaza
        if(Math.abs(x-targetX) < 3.0f)
            xMove = 0.0f;
        if(Math.abs(y-targetY) < 3.0f)
            yMove = 0.0f;

    }



    /////////////////////////////////////////////////////
    public BufferedImage GetImage(){return image;}
    /////////////////////////////////////////////////////

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        //pentru Debug al echipei adverse
    }
}
