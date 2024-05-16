package FootballGame.Items;

import java.awt.*;
import java.lang.Math;
import java.awt.image.BufferedImage;

import FootballGame.Maps.Map;
import FootballGame.RefLinks;
import FootballGame.Graphics.Assets;
import FootballGame.States.PlayState;

import static FootballGame.States.PlayState.*;


    /*! \class public class PlayerCity extends Character
        \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

        Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
            imaginea (acest atribut poate fi ridicat si in clasa de baza)
            deplasarea
            atacul (nu este implementat momentan)
            dreptunghiul de coliziune
     */
    public class PlayerArsenal extends Character
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
        public PlayerArsenal(RefLinks refLink, float x, float y, byte id)
        {
            ///Apel al constructorului clasei de baza
            super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
            ///Seteaza imaginea de start a eroului
            image = Assets.heroRight4;
            id_team = 2;
            ID = id;

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
                //behavior = "Control Player";
                HasBall = true;
                ball.setDirection(false);
                for(int i=0;i<NoPlayers;i++)
                    if(Player[i].behavior == "Receive Ball"){
                        Player[i].behavior = "Wait";
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
                        if (behavior == "Control Player") {
                            SetSpeed(2.12f);
                            ///Verifica daca a fost apasata o tasta
                            GetInput();
                            ///Actualizeaza pozitia
                            Move();
                        } else       // Daca nu este jucatorul controlat de utilizator, va actiona singur
                        {
                            if (status == "attacking") {

                            }
                            if (status == "defending") {
                                if (behavior == "Chase Ball") {
                                    for (int j = 0; j < PlayState.NoPlayers; j++)
                                        if (Player[j].HasBall)
                                            if (Math.abs(Player[j].GetX() - x) > Math.abs(Player[j].GetY() - y)) {
                                                if (Player[j].GetX() > x)
                                                    xMove = 2.1f;
                                                else
                                                    xMove = -2.1f;
                                                if (Player[j].GetY() > y || xMove < 0)
                                                    yMove = (Math.abs(Player[j].GetY() - y) * xMove) / Math.abs(Player[j].GetX() - x);
                                                else
                                                    yMove = -(Math.abs(Player[j].GetY() - y) * xMove) / Math.abs(Player[j].GetX() - x);
                                            } else {
                                                if (Player[j].GetY() > y)
                                                    yMove = 2.1f;
                                                else
                                                    yMove = -2.1f;
                                                if (Player[j].GetX() > x || yMove < 0)
                                                    xMove = (Math.abs(Player[j].GetX() - x) * yMove) / Math.abs(Player[j].GetY() - y);
                                                else
                                                    xMove = -(Math.abs(Player[j].GetX() - x) * yMove) / Math.abs(Player[j].GetY() - y);
                                            }
                                    x += xMove;
                                    y += yMove;
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
                        } else if (refLink.GetKeyManager().down) {
                            image = Assets.block4;
                            SetNormalMode();
                            SetSpeed(3.0f);
                            if (-camY < (Map.height - 768) - 5)
                                camY -= speed;
                        }

                    }
                }//Daca jucatorul este al echipei oaspete, cea controlata in totalitate de CPU, executa instructiunile primite
                else if (flag == 2) {
                    if (!HasBall) {
                        if (behavior == "Chase Ball") {
                            for (int j = 0; j < PlayState.NoPlayers; j++)
                                if (Player[j].HasBall) {
                                    if (Math.abs(Player[j].GetX() - x) > Math.abs(Player[j].GetY() - y)) {
                                        if (Player[j].GetX() > x)
                                            xMove = 2.1f;
                                        else
                                            xMove = -2.1f;
                                        if (Player[j].GetY() > y || xMove < 0)
                                            yMove = (Math.abs(Player[j].GetY() - y) * xMove) / Math.abs(Player[j].GetX() - x);
                                        else
                                            yMove = -(Math.abs(Player[j].GetY() - y) * xMove) / Math.abs(Player[j].GetX() - x);
                                    } else {
                                        if (Player[j].GetY() > y)
                                            yMove = 2.1f;
                                        else
                                            yMove = -2.1f;
                                        if (Player[j].GetX() > x || yMove < 0)
                                            xMove = (Math.abs(Player[j].GetX() - x) * yMove) / Math.abs(Player[j].GetY() - y);
                                        else
                                            xMove = -(Math.abs(Player[j].GetX() - x) * yMove) / Math.abs(Player[j].GetY() - y);
                                    }
                                    if ((Player[j].GetX() <= x) && (x <= Player[j].GetX() + 48) && (Player[j].GetY() <= y + 24) && (y + 24 <= Player[j].GetY() + 48)) {
                                        behavior = "Control Player";
                                        HasBall = true;
                                        Player[j].HasBall = false;
                                        ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                        ///////// Setez animatia pentru tackling //////////////////////////////////////////////////////////////////////////
                                    }
                                }
                            x += xMove;
                            y += yMove;
                        }
                        if (behavior == "Support Attacker") {
                            for (int i = 0; i < PlayState.NoPlayers; i++)
                                if (Player[i].HasBall) {
                                    // Setez pozitia tinta a jucatorului unde se va deplasa
                                    TargetPosition(false, Player[i].GetX(), Player[i].GetY());
                                }
                            //Actualizam Pozitia jucatorului
                            x += xMove;
                            y += yMove;
                        }
                        if (behavior == "Mark Support Attacker") {
                            for (int i = 0; i < PlayState.NoPlayers; i++)
                                if (Player[i].behavior == "Support Attacker") {
                                    // Daca adversarul targetat are si mingea, aparatorul il va ataca, daca nu, doar il va marca
                                    if (Player[i].HasBall) {
                                        TargetPositionDefender(false, Player[i].GetX(), Player[i].GetY(), true);
                                        //Daca in urma apelarii functiei anterioare, jucatorul intra in posesia mingii, ajustez starea adversarului
                                        if (HasBall)
                                            Player[i].HasBall = false;
                                    } else
                                        TargetPositionDefender(false, Player[i].GetX(), Player[i].GetY(), false);
                                }
                            //Actualizam Pozitia jucatorului
                            x += xMove;
                            y += yMove;
                        }
                    }
                    else
                    {

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
                if (x == Player[j].GetX() && y == Player[j].GetY()) {
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
        private void TargetPosition(boolean HomeTeam, float xTeamMate, float yTeamMate)
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

            if(Math.abs(x-targetX) < 3.0f)
                xMove = 0.0f;
            if(Math.abs(y-targetY) < 3.0f)
                yMove = 0.0f;

        }

        private void TargetPositionDefender(boolean HomeTeam, float xOpponent, float yOpponent, boolean TargetOponentHasBall)
        {
            float targetX, targetY;

            // In functie de echipa gazda sau nu, setam pozitia aparatorului in spatele atacantului
            if(HomeTeam)
                targetX = xOpponent - 85.0f;
            else
                targetX = xOpponent + 85.0f;
            targetY = yOpponent; // Aparatorul va fi pe aceeasi linie cu atacantul advers

            // Daca adversarul in marcaj primeste mingea, fundasul il ataca
            if(TargetOponentHasBall) {
                targetX = xOpponent;
                if((xOpponent <= x) && (x <= xOpponent + 48) && (yOpponent <= y + 24) && (y+24 <= yOpponent + 48)) {
                    HasBall = true;
                    ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                    ///////// Setez animatia pentru tackling //////////////////////////////////////////////////////////////////////////
                }
            }

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
            g.setColor(Color.blue);
            g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
        }
    }

