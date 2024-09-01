package FootballGame.Items;

import java.awt.*;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import FootballGame.Astar.AStar;
import FootballGame.Astar.Node;
import FootballGame.Items.Utilities.GoalNet;
import FootballGame.Maps.Map;
import FootballGame.RefLinks;
import FootballGame.Graphics.Assets;
import FootballGame.States.PlayState;
import FootballGame.Items.ControlCenter.*;

import static FootballGame.Items.ControlCenter.*;
import static FootballGame.Maps.Map.LeftGoal;
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
    private int fanion;
    public static String status;  /*!< Retine statusul echipei: defending or attacking  */
    public static byte flag = 0;
    public static String attackStrategy;
    public static String defenceStrategy;

    /*! \fn public PlayerCity(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei PlayerCity.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public PlayerCity(RefLinks refLink, float x, float y, byte id, boolean GK)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        if(flag == 1)
            image = Assets.manCityWait1;
        else
            image = Assets.manCityWait2;
        id_team = 1;
        attackStrategy = "Possession Game";
        defenceStrategy = "Aggressive";
        ID = id;
        Gk = GK;
        fanion = 0;

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
        if(haveBeenTakled != 0)
            haveBeenTakled --;
        else {
            if(behavior=="Wait" && !Gk)
                behavior = "Return to Home Region";
            ///Actualizeaza imaginea
            /// Actualizeaza daca eroul a fost selectat ca Player 1(care foloseste ArrowKeys)

            /// Ajustez imaginea in adancime(la indepartare)
            if (y < Map.height * 2 / 7) {
                width = 40;
                height = 40;
            } else if (y < Map.height * 3 / 7) {
                width = 42;
                height = 42;
            } else if (y < Map.height * 4 / 7) {
                width = 44;
                height = 44;
            } else if (y < Map.height * 5 / 7) {
                width = 46;
                height = 46;
            } else {
                width = 48;
                height = 48;
            }

            /// Daca un jucator intercepteaza mingea, stabilim comportamentul dupa caz
            if (ball.GetX() <= GetX()+width/2 && (GetX()+width/2 <= ball.GetX() + width) && (ball.GetY() <= GetY() + height/2) && (GetY() + height/2 <= ball.GetY() + height)) {
                fanion = 0;
                HasBall = true;
                ball.setDirection(false);
                ///Schimbam comportamentul jucatorului care era destinat sa primeasca mingea
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].behavior == "Receive Ball") {
                        Player[i].behavior = "Wait";
                        /// Daca este echipa controlata de utilizator, primeste drepturi depline de control
                        if((Player[i].id_team==1 && PlayerCity.flag == 1) || (Player[i].id_team==2 && PlayerArsenal.flag == 1) || (Player[i].id_team==3 && PlayerChelsea.flag == 1)  || (Player[i].id_team==4 && PlayerLiverpool.flag == 1))
                            Player[i].behavior = "Control Player";
                        }
                // Daca a interceptat o pasa de la echipa adversa, se vor schimba rolurile tactice
                if (status == "defending")
                    ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                if(shoot && !Gk) {
                    shoot = false;
                    if(goal)
                        goal = false;
                }
            }
            if (behavior == "Receive Ball") {
                //ReceiveBall();
                //x -= xMove;
                //y -= yMove;
                AttackBall();
            }

            if(refLink.GetKeyManager().pass || refLink.GetKeyManager().shoot || refLink.GetKeyManager().tackle) {
                fanion = 0;
            }

            if (fanion == 0) {   /// Daca s-a terminat animatia, poate incepe una noua
                if (flag == 1)   /// Daca jucatorul este al echipei gazda, cea controlata de utilizator, executa instructiunile primite
                {
                    if (!HasBall) {
                        if(Gk && behavior != "Receive Ball")
                        {
                            if(ball.GetDirection())
                            {
                                GkMovement(ball.GetY());
                                image = Assets.heroLeft4;
                                fanion = 0;
                            }
                            else
                            {
                                for(int i=0; i<NoPlayers; i++)
                                    if(Player[i].HasBall)
                                        GkMovement(Player[i].GetY());
                                image = Assets.heroLeft4;
                                fanion = 0;
                            }
                            ///Actualizeaza pozitia
                            Move();
                            if(shoot){
                                if(ball.GetX() < x + width/2) {
                                    ControlCenter.change = true;
                                    ball.setDirection(false);
                                    if (goal) {
                                        goal = false;
                                        if (ball.GetY() < Map.height / 2)
                                            LeftGoal.SetAnimation(48, (byte) 1);
                                        else
                                            LeftGoal.SetAnimation(48, (byte) 2);
                                        image = Assets.heroLeft4; // Animatie pentru aruncare
                                        fanion = 24;
                                    } else {
                                        HasBall = true;
                                        image = Assets.heroLeft42; // Animatie pentru parada
                                        fanion = 24;
                                    }
                                    shoot = false;
                                }
                            }
                        }
                        else {
                            if (behavior == "Control Player" && refLink.GetKeyManager().switchPlayer) {
                                SwitchPlayer();
                                haveBeenTakled = 30;
                            } else if (behavior == "Control Player") {
                                SetSpeed(2.12f);
                                ///Verifica daca a fost apasata o tasta
                                GetInput();
                                ///Actualizeaza pozitia
                                Move();
                                ///Daca a fost detectata miscare, setam fanionul pentru animatie
                                if(xMove!=0 || yMove != 0)
                                    fanion = 24;
                                if(refLink.GetKeyManager().left && refLink.GetKeyManager().up)
                                    image = Assets.manCityRunningWithoutBallNW1;
                                else if(refLink.GetKeyManager().left && refLink.GetKeyManager().down)
                                    image = Assets.manCityRunningWithoutBallSW1;
                                else if(refLink.GetKeyManager().right && refLink.GetKeyManager().up)
                                    image = Assets.manCityRunningWithoutBallNE1;
                                else if(refLink.GetKeyManager().right && refLink.GetKeyManager().down)
                                    image = Assets.manCityRunningWithoutBallSE1;
                                else if(refLink.GetKeyManager().right)
                                    image = Assets.manCityRunningWithoutBallE1;
                                else if(refLink.GetKeyManager().left)
                                    image = Assets.manCityRunningWithoutBallW1;
                                else if(refLink.GetKeyManager().up)
                                    image = Assets.manCityRunningWithoutBallN1;
                                else if(refLink.GetKeyManager().down)
                                    image = Assets.manCityRunningWithoutBallS1;
                                //Daca e apasata tasta pentru deposedare in jurul oponentului, recupereaza mingea
                                if (refLink.GetKeyManager().tackle) {
                                    for (int i = 0; i < NoPlayers; i++)
                                        if (Player[i].HasBall && Player[i].GetX() <= GetX() && (GetX() <= Player[i].GetX() + 48) && (Player[i].GetY() <= GetY() + 24) && (GetY() + 24 <= Player[i].GetY() + 48)) {
                                            Player[i].HasBall = false;
                                            Player[i].haveBeenTakled = 80;
                                            haveBeenTakled = 30;
                                            if(x >= Player[i].GetX() && y >= Player[i].GetY())
                                                image = Assets.manCitytackleNW;
                                            else if(x <= Player[i].GetX() && y >= Player[i].GetY())
                                                image = Assets.manCitytackleNE;
                                            else if(x <= Player[i].GetX() && y <= Player[i].GetY())
                                                image = Assets.manCitytackleSE;
                                            else if(x >= Player[i].GetX() && y <= Player[i].GetY())
                                                image = Assets.manCitytackleSW;
                                            HasBall = true;
                                            ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                        }
                                }


                            } else       // Daca nu este jucatorul controlat de utilizator, va actiona singur
                            {
                                if (behavior == "Chase Ball") {
                                    for (int j = PlayState.NoPlayers / 2; j < PlayState.NoPlayers; j++)
                                        if (Player[j].HasBall && !Player[j].Gk) {
                                            if (Math.abs(Player[j].GetX() - x) > Math.abs(Player[j].GetY() - y)) {
                                                if (Player[j].GetX() > x)
                                                    xMove = 2.1f;
                                                else
                                                    xMove = -2.1f;
                                                if (Player[j].GetY() > y || xMove < 0)
                                                    yMove = (Math.abs(Player[j].GetY() - y) * xMove) / (Math.abs(Player[j].GetX() - x) + 0.1f);
                                                else
                                                    yMove = -(Math.abs(Player[j].GetY() - y) * xMove) / (Math.abs(Player[j].GetX() - x) + 0.1f);
                                            } else {
                                                if (Player[j].GetY() > y)
                                                    yMove = 2.1f;
                                                else
                                                    yMove = -2.1f;
                                                if (Player[j].GetX() > x || yMove < 0)
                                                    xMove = (Math.abs(Player[j].GetX() - x) * yMove) / (Math.abs(Player[j].GetY() - y) + 0.1f);
                                                else
                                                    xMove = -(Math.abs(Player[j].GetX() - x) * yMove) / (Math.abs(Player[j].GetY() - y) + 0.1f);
                                            }
                                            if ((Player[j].GetX() <= x) && (x <= Player[j].GetX() + 48) && (Player[j].GetY() <= y + 24) && (y + 24 <= Player[j].GetY() + 48)) {
                                                HasBall = true;
                                                Player[j].HasBall = false;
                                                Player[j].haveBeenTakled = 80;
                                                haveBeenTakled = 30;
                                                if(x >= Player[j].GetX() && y >= Player[j].GetY())
                                                    image = Assets.manCitytackleNW;
                                                else if(x <= Player[j].GetX() && y >= Player[j].GetY())
                                                    image = Assets.manCitytackleNE;
                                                else if(x <= Player[j].GetX() && y <= Player[j].GetY())
                                                    image = Assets.manCitytackleSE;
                                                else if(x >= Player[j].GetX() && y <= Player[j].GetY())
                                                    image = Assets.manCitytackleSW;
                                                ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                            }
                                        }
                                    ///Actualizeaza pozitia
                                    Move();
                                }

                                if (behavior == "Link Up Player") {
                                    for (int i = 0; i < PlayState.NoPlayers; i++)
                                        if (Player[i].behavior == "Chase Ball") {
                                            for (int j = 0; j < PlayState.NoPlayers; j++)
                                                if (Player[j].HasBall)
                                                    if (Math.abs(Player[i].GetXMove()) < Math.abs(Player[i].GetYMove())) {
                                                        if (y > Player[j].GetY())
                                                            y += Player[i].GetYMove() / NoOfChaseBallers;
                                                        x -= Player[i].GetXMove() / NoOfChaseBallers;
                                                        //System.out.println(Player[i].GetXMove() + "," + Player[i].GetYMove());
                                                    } else {
                                                        if (x > Player[j].GetX())
                                                            x += Player[i].GetXMove() / NoOfChaseBallers;
                                                        y -= Player[i].GetYMove() / NoOfChaseBallers;
                                                        //System.out.println(Player[i].GetXMove()+","+Player[i].GetYMove());
                                                    }


                                        }
                                }
                                if (behavior == "Support Attacker") {
                                    for (int i = 0; i < PlayState.NoPlayers; i++)
                                        if (Player[i].HasBall) {
                                            // Setez pozitia tinta a jucatorului unde se va deplasa
                                            TargetPositionAttacker(true, Player[i].GetX(), Player[i].GetY());
                                        }
                                    //Actualizam Pozitia jucatorului
                                    x += xMove;
                                    y += yMove;
                                }

                                if (behavior == "Mark Support Attacker") {
                                    for (int i = 0; i < PlayState.NoPlayers; i++)
                                        // Aparatorul va marca jucatorul din atacul echipei adverse
                                        if (Player[i].behavior == "Support Attacker")
                                            TargetPositionDefender(true, Player[i].GetX(), Player[i].GetY());

                                    //Actualizam Pozitia jucatorului
                                    x += xMove;
                                    y += yMove;
                                }


                                if (behavior == "Return to Home Region") {
                                    // Daca a ajuns pe pozitia lui aproximativa de baza, nu face nimic
                                    if (Math.abs(homeRegionX - x) < 3 && Math.abs(homeRegionY - y) < 3) {
                                        behavior = "Wait";
                                    }
                                    // Altfel, se indreapta catre pozitia de baza
                                    else {
                                        if (Math.abs(homeRegionX - x) > Math.abs(homeRegionY - y)) {
                                            if (homeRegionX > x)
                                                xMove = 2.1f;
                                            else
                                                xMove = -2.1f;
                                            if (homeRegionY > y || xMove < 0)
                                                yMove = (Math.abs(homeRegionY - y) * xMove) / Math.abs(homeRegionX - x);
                                            else
                                                yMove = -(Math.abs(homeRegionY - y) * xMove) / Math.abs(homeRegionX - x);
                                        } else {
                                            if (homeRegionY > y)
                                                yMove = 2.1f;
                                            else
                                                yMove = -2.1f;
                                            if (homeRegionX > x || yMove < 0)
                                                xMove = (Math.abs(homeRegionX - x) * yMove) / Math.abs(homeRegionY - y);
                                            else
                                                xMove = -(Math.abs(homeRegionX - x) * yMove) / Math.abs(homeRegionY - y);
                                        }
                                        x += xMove;
                                        y += yMove;
                                    }
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
                            image = Assets.manCityPassNW;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("NW");
                            ball.SetX(x - 24);
                            ball.SetY(y - 24);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassSW;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("SW");
                            ball.SetX(x - 24);
                            ball.SetY(y + 48);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().down) {
                            image = Assets.manCityRunningWithBallSW1;
                            SetAttackMode();
                            SetSpeed(2.12f);
                            fanion = 24;
                            if (-camX > 5 && -camY < (Map.height - 768) - 5) {
                                camX += speed;
                                camY -= speed;
                            } else if (-camX > 5) camX += speed;
                            else if (-camY < (Map.height - 768) - 5) camY -= speed;
                        } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().up) {
                            image = Assets.manCityRunningWithBallNW1;
                            SetAttackMode();
                            SetSpeed(2.12f);
                            fanion = 24;
                            if (-camX > 5 && -camY > 5) {
                                camX += speed;
                                camY += speed;
                            } else if (-camX > 5) camX += speed;
                            else if (-camY > 5) camY += speed;
                        } else if (refLink.GetKeyManager().left && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassW;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("W");
                            ball.SetX(x - 48);
                            ball.SetY(y);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().left) {
                            image = Assets.manCityRunningWithBallW1;
                            SetNormalMode();
                            SetSpeed(3.0f);
                            fanion = 24;
                            if (-camX > 5)
                                camX += speed;
                        } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().up && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassNE;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("NE");
                            ball.SetX(x+24);
                            ball.SetY(y);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassSE;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("SE");
                            ball.SetX(x + 24);
                            ball.SetY(y + 24);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().down) {
                            image = Assets.manCityRunningWithBallSE1;
                            SetAttackMode();
                            SetSpeed(2.12f);
                            fanion = 24;
                            if (-camX < Map.width - 1536 - 5 && -camY < (Map.height - 768) - 5) {
                                camX -= speed;
                                camY -= speed;
                            } else if (-camX < Map.width - 1536 - 5) camX -= speed;
                            else if (-camY < (Map.height - 768) - 5) camY -= speed;
                        } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().up) {
                            image = Assets.manCityRunningWithBallNE1;
                            SetAttackMode();
                            SetSpeed(2.12f);
                            fanion = 24;
                            if (-camX < Map.width - 1536 - 5 && -camY > 5) {
                                camX -= speed;
                                camY += speed;
                            } else if (-camX < Map.width - 1536 - 5) camX -= speed;
                            else if (-camY > 5) camY += speed;
                        } else if (refLink.GetKeyManager().right && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassE;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("E");
                            ball.SetX(x+24);
                            ball.SetY(y);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().right) {
                            image = Assets.manCityRunningWithBallE1;
                            SetNormalMode();
                            SetSpeed(3.0f);
                            fanion = 24;
                            if (-camX < Map.width - 1536 - 5)
                                camX -= speed;
                        } else if (refLink.GetKeyManager().up && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassN;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("N");
                            ball.SetX(x - 24);
                            ball.SetY(y - 24);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().up) {
                            image = Assets.manCityRunningWithBallN1;
                            SetNormalMode();
                            SetSpeed(3.0f);
                            fanion = 24;
                            if (-camY > 5)
                                camY += speed;
                        } else if (refLink.GetKeyManager().down && refLink.GetKeyManager().pass) {
                            image = Assets.manCityPassS;
                            SetAttackMode();
                            SetSpeed(0.0f);
                            fanion = 24;
                            PassingBallCoordonations("S");
                            ball.SetX(x);
                            ball.SetY(y + 48);
                            ball.setDirection(true);
                            HasBall = false;
                            behavior = "Return to Home Region";
                        } else if (refLink.GetKeyManager().down) {
                            image = Assets.manCityRunningWithBallS1;
                            SetNormalMode();
                            SetSpeed(3.0f);
                            fanion = 24;
                            if (-camY < (Map.height - 768) - 5)
                                camY -= speed;
                        }
                        if(refLink.GetKeyManager().shoot)
                        {
                            contorShotsTeam1++;
                            shoot = true;
                            // Daca jucatorul suteaza din careu, va marca
                            if ((int) (x / 48) >= 30 && (int) (y / 48) >= 6 && (int) (y / 48) <= 13)
                                goal = true;
                            // Daca jucatorul suteaza din afara careului, pozitie laterala, va marca
                            if ((int) (x / 48) >= 26 && (int) (x / 48) < 30 && ((int) (y / 48) <= 7 || (int) (y / 48) >= 12))
                                goal = true;
                            ball.setDirection(true);
                            ball.SetX(x + 48);
                            ball.SetY(y);
                            HasBall = false;
                            behavior = "Wait";
                            haveBeenTakled = 25;

                            //Daca jucatorul se afla in flancul stang de atac, va suta la coltul opus
                            if (y < Map.height/2) {

                                Ball.actualizareX = ((Map.width-250.0f) - ball.GetX()) / 55;
                                Ball.actualizareY = (580.0f - ball.GetY()) / 55; // y=580.0f este coordonata coltului dreapta
                                image = Assets.manCityPassSE;
                            }else
                            {   //Daca jucatorul se afla in flancul drept de atac, va suta la coltul opus
                                Ball.actualizareX = ((Map.width-250.0f) - ball.GetX()) / 55;
                                Ball.actualizareY = (450.0f - ball.GetY()) / 55; // y=450.0f este coordonata coltului stanga
                                image = Assets.manCityPassNE;
                            }
                        }
                    }
                }//Daca jucatorul este al echipei oaspete, cea controlata in totalitate de CPU, executa instructiunile primite
                else if (flag == 2) {
                    if (!HasBall) {
                        if(Gk && behavior != "Receive Ball") {
                            System.out.println("x: " + x + "xMove: " + xMove);
                            if (ball.GetDirection()) {
                                GkMovement(ball.GetY());
                                image = Assets.heroLeft4;
                                fanion = 0;
                            } else {
                                for (int i = 0; i < NoPlayers; i++)
                                    if (Player[i].HasBall)
                                        GkMovement(Player[i].GetY());
                                image = Assets.heroLeft4;
                                fanion = 0;
                            }
                            ///Actualizeaza pozitia
                            Move();
                            if (shoot) {
                                if (ball.GetX() > x - width) {
                                    ball.setDirection(false);
                                    ControlCenter.change = true;
                                    if (goal) {
                                        goal = false;
                                        if (ball.GetY() < Map.height / 2)
                                            LeftGoal.SetAnimation(48, (byte) 1); /////////////// RightGoal*
                                        else
                                            LeftGoal.SetAnimation(48, (byte) 2); /////////////// RightGoal*
                                        image = Assets.heroLeft4; // Animatie pentru aruncare
                                        fanion = 24;
                                    } else {
                                        HasBall = true;
                                        image = Assets.heroLeft42; // Animatie pentru parada
                                        fanion = 24;
                                    }
                                    shoot = false;
                                }
                            }
                        }else {
                            if (behavior == "Chase Ball") {
                                for (int j = 0; j < PlayState.NoPlayers / 2; j++)
                                    if (Player[j].HasBall) {
                                        if (Math.abs(Player[j].GetX() - x) > Math.abs(Player[j].GetY() - y)) {
                                            if (Player[j].GetX() > x)
                                                xMove = 2.1f;
                                            else
                                                xMove = -2.1f;
                                            if (Player[j].GetY() > y || xMove < 0)
                                                yMove = (Math.abs(Player[j].GetY() - y) * xMove) / (Math.abs(Player[j].GetX() - x) + 0.1f);
                                            else
                                                yMove = -(Math.abs(Player[j].GetY() - y) * xMove) / (Math.abs(Player[j].GetX() - x) + 0.1f);
                                        } else {
                                            if (Player[j].GetY() > y)
                                                yMove = 2.1f;
                                            else
                                                yMove = -2.1f;
                                            if (Player[j].GetX() > x || yMove < 0) {
                                                xMove = (Math.abs(Player[j].GetX() - x) * yMove) / (Math.abs(Player[j].GetY() - y) + 0.1f);
                                            } else {
                                                xMove = -(Math.abs(Player[j].GetX() - x) * yMove) / (Math.abs(Player[j].GetY() - y) + 0.1f);
                                            }
                                        }
                                        if ((Player[j].GetX() <= x) && (x <= Player[j].GetX() + 48) && (Player[j].GetY() <= y + 24) && (y + 24 <= Player[j].GetY() + 48)) {
                                            HasBall = true;
                                            Player[j].HasBall = false;
                                            Player[j].haveBeenTakled = 80;
                                            haveBeenTakled = 30;
                                            if(x >= Player[j].GetX() && y >= Player[j].GetY())
                                                image = Assets.manCitytackleNW;
                                            else if(x <= Player[j].GetX() && y >= Player[j].GetY())
                                                image = Assets.manCitytackleNE;
                                            else if(x <= Player[j].GetX() && y <= Player[j].GetY())
                                                image = Assets.manCitytackleSE;
                                            else if(x >= Player[j].GetX() && y <= Player[j].GetY())
                                                image = Assets.manCitytackleSW;
                                            ControlCenter.change = true; // Se produce schimbarea: Cele doua echipe vor schimba rolurile ofesive si defensive
                                        }
                                    }

                                x += xMove;
                                y += yMove;
                            }

                            if (behavior == "Link Up Player") {
                                for (int i = 0; i < PlayState.NoPlayers / 2; i++)
                                    if (Player[i].behavior == "Chase Ball") {
                                        for (int j = 0; j < PlayState.NoPlayers; j++)
                                            if (Player[j].HasBall)
                                                if (Math.abs(Player[i].GetXMove()) < Math.abs(Player[i].GetYMove())) {
                                                    if (y > Player[j].GetY())
                                                        y += Player[i].GetYMove() / NoOfChaseBallers;
                                                    x -= Player[i].GetXMove() / NoOfChaseBallers;
                                                    //System.out.println(Player[i].GetXMove() + "," + Player[i].GetYMove());
                                                } else {
                                                    if (x > Player[j].GetX())
                                                        x += Player[i].GetXMove() / NoOfChaseBallers;
                                                    y -= Player[i].GetYMove() / NoOfChaseBallers;
                                                    //System.out.println(Player[i].GetXMove()+","+Player[i].GetYMove());
                                                }


                                    }
                            }

                            if (behavior == "Support Attacker") {
                                for (int i = 0; i < PlayState.NoPlayers; i++)
                                    if (Player[i].HasBall) {
                                        // Setez pozitia tinta a jucatorului unde se va deplasa
                                        TargetPositionAttacker(false, Player[i].GetX(), Player[i].GetY());
                                    }
                                //Actualizam Pozitia jucatorului
                                x += xMove;
                                y += yMove;
                            }
                            if (behavior == "Mark Support Attacker") {
                                for (int i = 0; i < PlayState.NoPlayers; i++)
                                    if (Player[i].behavior == "Support Attacker") {
                                        TargetPositionDefender(false, Player[i].GetX(), Player[i].GetY());
                                    }
                                //Actualizam Pozitia jucatorului
                                x += xMove;
                                y += yMove;
                            }
                            if (behavior == "Return to Home Region") {
                                // Daca a ajuns pe pozitia lui aproximativa de baza, nu face nimic
                                if (Math.abs(homeRegionX - x) < 3 && Math.abs(homeRegionY - y) < 3) {
                                    behavior = "Wait";
                                }
                                // Altfel, se indreapta catre pozitia de baza
                                else {
                                    if (Math.abs(homeRegionX - x) > Math.abs(homeRegionY - y)) {
                                        if (homeRegionX > x)
                                            xMove = 2.1f;
                                        else
                                            xMove = -2.1f;
                                        if (homeRegionY > y || xMove < 0)
                                            yMove = (Math.abs(homeRegionY - y) * xMove) / Math.abs(homeRegionX - x);
                                        else
                                            yMove = -(Math.abs(homeRegionY - y) * xMove) / Math.abs(homeRegionX - x);
                                    } else {
                                        if (homeRegionY > y)
                                            yMove = 2.1f;
                                        else
                                            yMove = -2.1f;
                                        if (homeRegionX > x || yMove < 0)
                                            xMove = (Math.abs(homeRegionX - x) * yMove) / Math.abs(homeRegionY - y);
                                        else
                                            xMove = -(Math.abs(homeRegionX - x) * yMove) / Math.abs(homeRegionY - y);
                                    }
                                    x += xMove;
                                    y += yMove;
                                }
                            }
                        }
                    } else // Daca are mingea, actioneaza in consecinta
                    {
                        // Daca este portarul, paseaza mingea catre link Up Player
                        if (Gk) {
                            for(int i=NoPlayers/2; i<NoPlayers; i++)
                                if(Player[i].behavior=="Link Up Player") {
                                    float raport = (Math.abs(Player[i].GetX() - x)) / Math.abs((Player[i].GetY() - y));
                                    /* Daca raportul dintre distantele intre coordonatele x, respectiv y ale punctelor sursa si destinatie pasa
                                    este intre 1/2 si 2, consideram pasa ca fiind pe diagonala, altfel invers */
                                    if ((0.5f < raport) && (raport < 2.0f)) {
                                        if (x < Player[i].GetX()) {
                                            if (y < Player[i].GetY())
                                                CPUpass("SE", i);
                                            else
                                                CPUpass("NE", i);
                                        } else {
                                            if (y < Player[i].GetY())
                                                CPUpass("SW", i);
                                            else
                                                CPUpass("NW", i);
                                        }
                                    } else {
                                    /* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mare
                                     decat distanta intre coordonatele y, pasa este pe orizontala */
                                        if (raport >= 2.0f) {
                                            if (x > Player[i].GetX())
                                                CPUpass("W", i);
                                            else
                                                CPUpass("E", i);
                                        }/* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mic
                                     decat distanta intre coordonatele y, pasa este pe verticala */
                                        else if (raport <= 0.5f) {
                                            if (y > Player[i].GetY())
                                                CPUpass("N", i);
                                            else
                                                CPUpass("S", i);
                                        }
                                    }
                                }

                        } else { // Daca este jucator de camp, actioneaza ca atare

                            // Stabilim coordonatele vechii pozitii. Ajuta la mobilitatea camerei de filmat
                            float lastX = x, lastY = y;

                            float xRange, yRange;
                            // xRange reprezinta distanta pe axa X intre jucatorul curent si poarta adversa, impartita pe 55 frameuri pt animatie
                            xRange = (x - 250.0f) / 55;
                            // Daca jucatorul intra in ultima treime de teren, ia in considerare sa suteze
                            if (x < Map.width/3)
                                //Daca jucatorul se afla in flancul drept de atac, va suta la coltul lung
                                if (y < Map.height/2 && y > 80 ) {
                                    // Consideram initial ca va suta
                                    shoot = true;
                                    for (int i = 1; i <= 55; i++)
                                        for (int adv = 0; adv < NoPlayers / 2; adv++) {
                                            // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (y - 580.0f) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 48) == (int) (x + (i * xRange)) / 48) && ((int) (Player[adv].GetY() / 48) == (int) (y + (i * yRange)) / 48)) {
                                                shoot = false;
                                            }
                                        }
                                    //Daca jucatorul are culoar de sut, va suta
                                    if (shoot) {
                                        // Daca jucatorul suteaza din careu, va marca
                                        if ((int) (x / 48) <= 10 && (int) (y / 48) >= 6 && (int) (y / 48) <= 13)
                                            goal = true;
                                        // Daca jucatorul suteaza din afara careului, pozitie laterala, va marca
                                        if ((int) (x / 48) <= 14 && (int) (x / 48) > 10 &&(int) (y / 48) <= 7)
                                            goal = true;
                                        ball.setDirection(true);
                                        ball.SetX(x - 48);
                                        ball.SetY(y);
                                        Ball.actualizareX = (250.0f - ball.GetX()) / 55;
                                        Ball.actualizareY = (580.0f - ball.GetY()) / 55;
                                        HasBall = false;
                                        behavior = "Wait";
                                        image = Assets.manCityPassSW;
                                        haveBeenTakled = 25;
                                    }

                                    //Daca jucatorul se afla in flancul stang de atac, va suta la coltul lung
                                } else if(y > Map.height/2 && y < 972)
                                {
                                    // Consideram initial ca va suta
                                    shoot = true;
                                    for (int i = 1; i <= 55; i++)
                                        for (int adv = 0; adv < NoPlayers / 2; adv++) {
                                            // xRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (y - 450.0f) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 48) == (int) (x + (i * xRange)) / 48) && ((int) (Player[adv].GetY() / 48) == (int) (y + (i * yRange)) / 48)) {
                                                shoot = false;
                                            }
                                        }
                                    //Daca jucatorul are culoar de sut, va suta
                                    if (shoot) {
                                        // Daca jucatorul suteaza din careu, va marca
                                        if ((int) (x / 48) <= 10 && (int) (y / 48) >= 6 && (int) (y / 48) <= 13)
                                            goal = true;
                                        // Daca jucatorul suteaza din afara careului, pozitie laterala, va marca
                                        if ((int) (x / 48) <= 14 && (int) (x / 48) > 10 && (int) (y / 48) >= 12)
                                            goal = true;
                                        ball.setDirection(true);
                                        ball.SetX(x - 48);
                                        ball.SetY(y);
                                        Ball.actualizareX = (250.0f - ball.GetX()) / 55;
                                        Ball.actualizareY = (450.0f - ball.GetY()) / 55;
                                        HasBall = false;
                                        behavior = "Wait";
                                        image = Assets.manCityPassNW;
                                        haveBeenTakled = 25;
                                    }
                                }

                            // Setam distanta dintre atacant si jucatorul curent
                            int aux = NoPlayers/2;
                            int linkUpPlayerID = NoPlayers/2;
                            float distanceToAttacker = 5000.0f;
                            // Aflam distanta catre atacantul suport si id-ul acestuia
                            for (int i = NoPlayers / 2; i < NoPlayers; i++)
                                if (Player[i].behavior == "Support Attacker") {
                                    distanceToAttacker = (float) Math.sqrt((Player[i].GetX() - x) * (Player[i].GetX() - x) + (Player[i].GetY() - y) * (Player[i].GetY() - y));
                                    aux = i;
                                }

                            //Daca jucatorul este aflat sub presiune si nu poate trece de adversar, va pasa catre cel mai apropiat coechipier liber
                            if (pressingContor > 15) {
                                boolean passToLinkUpPlayer = true;
                                for (int i = NoPlayers / 2; i < NoPlayers; i++)
                                    if (Player[i].behavior == "Link Up Player") {
                                        for (int n = (int) ((Player[i].GetX() / 48) - 1); n <= (int) ((Player[i].GetX() / 48) + 1); n++)
                                            for (int m = (int) ((Player[i].GetY() / 48) - 1); m <= (int) ((Player[i].GetY() / 48) + 1); m++)
                                                for (int adv = 0; adv < NoPlayers / 2; adv++) {
                                                    if ((int) (Player[adv].GetX() / 48) == n && (int) (Player[adv].GetY() / 48) == m) {
                                                        passToLinkUpPlayer = false;
                                                    }
                                                }
                                        linkUpPlayerID = i;
                                    }
                                // Daca jucatorul link up player este disponibil, se va pasa catre acesta daca este mai apropiat decat atacantul coechipier
                                if (passToLinkUpPlayer) {
                                    if (distanceToAttacker < (float) Math.sqrt((Player[linkUpPlayerID].GetX() - x) * (Player[linkUpPlayerID].GetX() - x) + (Player[linkUpPlayerID].GetY() - y) * (Player[linkUpPlayerID].GetY() - y))) {
                                        float raport = (Math.abs(Player[aux].GetX() - x)) / Math.abs((Player[aux].GetY() - y));
                                /* Daca raportul dintre distantele intre coordonatele x, respectiv y ale punctelor sursa si destinatie pasa
                                este intre 1/2 si 2, consideram pasa ca fiind pe diagonala, altfel invers */
                                        if ((0.5f < raport) && (raport < 2.0f)) {
                                            if (x < Player[aux].GetX()) {
                                                if (y < Player[aux].GetY())
                                                    CPUpass("SE", aux);
                                                else
                                                    CPUpass("NE", aux);
                                            } else {
                                                if (y < Player[aux].GetY())
                                                    CPUpass("SW", aux);
                                                else
                                                    CPUpass("NW", aux);
                                            }
                                        } else {
                                    /* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mare
                                     decat distanta intre coordonatele y, pasa este pe orizontala */
                                            if (raport >= 2.0f) {
                                                if (x > Player[aux].GetX())
                                                    CPUpass("W", aux);
                                                else
                                                    CPUpass("E", aux);
                                            }/* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mic
                                     decat distanta intre coordonatele y, pasa este pe verticala */ else if (raport <= 0.5f) {
                                                if (y > Player[aux].GetY())
                                                    CPUpass("N", aux);
                                                else
                                                    CPUpass("S", aux);
                                            }
                                        }
                                    } else
                                    //Daca atacantul este mai indepartat decat LinkUpPlayer, pasam catre cel din urma
                                    {
                                        float raport = (Math.abs(Player[aux].GetX() - x)) / Math.abs((Player[aux].GetY() - y));
                                /* Daca raportul dintre distantele intre coordonatele x, respectiv y ale punctelor sursa si destinatie pasa
                                este intre 1/2 si 2, consideram pasa ca fiind pe diagonala, altfel invers */
                                        if ((0.5f < raport) && (raport < 2.0f)) {
                                            if (x < Player[linkUpPlayerID].GetX()) {
                                                if (y < Player[linkUpPlayerID].GetY())
                                                    CPUpass("SE", linkUpPlayerID);
                                                else
                                                    CPUpass("NE", linkUpPlayerID);
                                            } else {
                                                if (y < Player[linkUpPlayerID].GetY())
                                                    CPUpass("SW", linkUpPlayerID);
                                                else
                                                    CPUpass("NW", linkUpPlayerID);
                                            }
                                        } else {
                                    /* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mare
                                     decat distanta intre coordonatele y, pasa este pe orizontala */
                                            if (raport >= 2.0f) {
                                                if (x > Player[linkUpPlayerID].GetX())
                                                    CPUpass("W", linkUpPlayerID);
                                                else
                                                    CPUpass("E", linkUpPlayerID);
                                            }/* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mic
                                     decat distanta intre coordonatele y, pasa este pe verticala */ else if (raport <= 0.5f) {
                                                if (y > Player[linkUpPlayerID].GetY())
                                                    CPUpass("N", linkUpPlayerID);
                                                else
                                                    CPUpass("S", linkUpPlayerID);
                                            }
                                        }

                                    }//pasa to link up

                                } // Daca jucatorul Link Up Player nu este disponibil, cautam alti coechipieri disponibili
                                else {

                                    //Verificam disponibilitatea fiecarui coechipier
                                    for (int i = NoPlayers / 2; i < NoPlayers; i++) {
                                        boolean freePlayer = true;
                                        if (ID == Player[i].GetID()) {
                                            // Daca este jucatorul curent, nu face nimic
                                            freePlayer = false;
                                        } else {
                                            // aflam cadranele adiacente fiecarui coechipier
                                            for (int n = (int) ((Player[i].GetX() / 48) - 1); n <= (int) ((Player[i].GetX() / 48) + 1); n++)
                                                for (int m = (int) ((Player[i].GetY() / 48) - 1); m <= (int) ((Player[i].GetY() / 48) + 1); m++)
                                                    // verificam daca exista un adversar in perimetrul coechipierului si aflam daca este liber sau nu
                                                    for (int adv = 0; adv < NoPlayers / 2; adv++) {
                                                        if ((int) (Player[adv].GetX() / 48) == n && (int) (Player[adv].GetY() / 48) == m)
                                                            freePlayer = false;
                                                    }

                                        }
                                        if (freePlayer)
                                            if ((float) Math.sqrt((Player[i].GetX() - x) * (Player[i].GetX() - x) + (Player[i].GetY() - y) * (Player[i].GetY() - y)) < distanceToAttacker) {
                                                distanceToAttacker = (float) Math.sqrt((Player[i].GetX() - x) * (Player[i].GetX() - x) + (Player[i].GetY() - y) * (Player[i].GetY() - y));
                                                aux = i;
                                            }
                                    }
                            /* Se paseaza catre cel mai apropiat jucator disponibil, daca este vreunul
                            Daca nu este niciun jucator disponibil, se arunca mingea catre atacant */
                                    float raport = (Math.abs(Player[aux].GetX() - x)) / Math.abs((Player[aux].GetY() - y));
                                /* Daca raportul dintre distantele intre coordonatele x, respectiv y ale punctelor sursa si destinatie pasa
                                este intre 1/2 si 2, consideram pasa ca fiind pe diagonala, altfel invers */
                                    if ((0.5f < raport) && (raport < 2.0f)) {
                                        if (x < Player[aux].GetX()) {
                                            if (y < Player[aux].GetY())
                                                CPUpass("SE", aux);
                                            else
                                                CPUpass("NE", aux);
                                        } else {
                                            if (y < Player[aux].GetY())
                                                CPUpass("SW", aux);
                                            else
                                                CPUpass("NW", aux);
                                        }
                                    } else {
                                    /* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mare
                                     decat distanta intre coordonatele y, pasa este pe orizontala */
                                        if (raport >= 2.0f) {
                                            if (x > Player[aux].GetX())
                                                CPUpass("W", aux);
                                            else
                                                CPUpass("E", aux);
                                        }/* Daca pasa nu este pe diagonala iar raportul dintre distanta intre coordonatele x este mai mic
                                     decat distanta intre coordonatele y, pasa este pe verticala */ else if (raport <= 0.5f) {
                                            if (y > Player[aux].GetY())
                                                CPUpass("N", aux);
                                            else
                                                CPUpass("S", aux);
                                        }
                                    }

                                }
                            }


                            /// Daca nu poate suta sau pasa, va dribla/avansa catre poarta pe traseul cel mai optim

                            int[][] grid = new int[40][24]; // Terenul impartit in noduri de 40x24
                            Node start = new Node((int) (x / 48), (int) (y / 48)); // Poziția de start (pozitia actuala a jucatorului)
                            Node goal = new Node(3, 7); // Poziția țintă (poarta adversa)
                            Node[] oponents = new Node[NoPlayers / 2]; //Cream lista cu adversarii
                            // adversarii sunt din prima echipa si cream lista cu acestia pentru a fi adaugati la "obstacole"
                            for (int i = 0; i < NoPlayers / 2; i++)
                                oponents[i] = new Node((int) (Player[i].GetX() / 48), (int) (Player[i].GetY() / 48));

                            //Adaugam adversarii in lista cu nodurile indisponibile (obstacole)
                            java.util.List<Node> obstacles = Arrays.asList(oponents[0], oponents[1], oponents[2], oponents[3], oponents[4], oponents[5]); // Obstacolele

                            for (Node obstacle : obstacles) {
                                grid[obstacle.x][obstacle.y] = 1; // Marca obstacolele pe teren
                            }

                            AStar aStar = new AStar(grid, start, goal); // Inițializează algoritmul A* cu terenul impartit pe noduri, startul(jucatorul) și ținta(poarta)
                            List<Node> path = aStar.findPath(); // Găsește calea

                            if (path != null) {
                                int ok = -1;
                                //System.out.println("Path found:");
                                for (Node node : path) {
                                    //System.out.println("(" + node.x + ", " + node.y + ")"); // Afișează calea găsită
                                    ok++;
                                    if (ok == 1)  /// Jucatorul se va deplasa catre urmatorul nod din drumul catre poarta in functie de obstacole
                                    {
                                        int i = 0;
                                        boolean quit = false;
                                        while (!quit && i != NoPlayers / 2) {
                                            if (node.x > (int) (x / 48))
                                                /// Daca oponentul se afla pe directia de mers a jucatorului pe urmatorul nod din traseul catre poarta sau pe unul dintre cele 2 noduri adiacente pe verticala, nu inainteaza pe orizontala decat atunci cand gaseste are oportunitatea
                                                if ((Player[i].behavior == "Chase Ball" || Player[i].behavior == "Control Player") && node.x == (int) (Player[i].GetX() / 48) && (node.y == (int) (Player[i].GetY() / 48) || node.y == (int) (Player[i].GetY() / 48) + 1 || node.y == (int) (Player[i].GetY() / 48) - 1)) {
                                                    xMove = 0.01f;
                                                    quit = true;
                                                    pressingContor += (i + 5);
                                                } else {
                                                    xMove = 2.1f;
                                                    if (pressingContor > 0)
                                                        pressingContor--;
                                                }
                                            if (node.x < (int) (x / 48))
                                                if ((Player[i].behavior == "Chase Ball" || Player[i].behavior == "Control Player") && node.x == (int) (Player[i].GetX() / 48) && (node.y == (int) (Player[i].GetY() / 48) || node.y == (int) (Player[i].GetY() / 48) + 1 || node.y == (int) (Player[i].GetY() / 48) - 1)) {
                                                    xMove = -0.01f;
                                                    quit = true;
                                                    pressingContor += (i + 5);
                                                } else {
                                                    xMove = -2.1f;
                                                    if (pressingContor > 0)
                                                        pressingContor--;
                                                }
                                            if (node.y > (int) (y / 48))
                                                if ((Player[i].behavior == "Chase Ball" || Player[i].behavior == "Control Player") && node.y == (int) (Player[i].GetY() / 48) && (node.x == (int) (Player[i].GetX() / 48) || node.x == (int) (Player[i].GetX() / 48) + 1 || node.x == (int) (Player[i].GetX() / 48) - 1)) {
                                                    yMove = 0.01f;
                                                    quit = true;
                                                    pressingContor += (i + 5);
                                                } else {
                                                    yMove = 2.1f;
                                                    if (pressingContor > 0)
                                                        pressingContor--;
                                                }
                                            if (node.y < (int) (y / 48))
                                                if ((Player[i].behavior == "Chase Ball" || Player[i].behavior == "Control Player") && node.y == (int) (Player[i].GetY() / 48) && (node.x == (int) (Player[i].GetX() / 48) || node.x == (int) (Player[i].GetX() / 48) + 1 || node.x == (int) (Player[i].GetX() / 48) - 1)) {
                                                    yMove = -0.01f;
                                                    quit = true;
                                                    pressingContor += (i + 5);
                                                } else {
                                                    yMove = -2.1f;
                                                    if (pressingContor > 0)
                                                        pressingContor--;
                                                }
                                            i++;
                                        }
                                        x += xMove;
                                        y += yMove;
                                        fanion = 24;
                                        if(xMove > 0.01f && yMove > 0.01f)
                                            image = Assets.manCityRunningWithBallSE1;
                                        else if(xMove > 0.01f && yMove < -0.01f)
                                            image = Assets.manCityRunningWithBallNE1;
                                        else if(xMove < -0.01f && yMove > 0.01f)
                                            image = Assets.manCityRunningWithBallSW1;
                                        else if(xMove < -0.01f && yMove < -0.01f)
                                            image = Assets.manCityRunningWithBallNW1;
                                        else if(xMove > 0.01f )
                                            image = Assets.manCityRunningWithBallE1;
                                        else if(xMove < -0.01f )
                                            image = Assets.manCityRunningWithBallW1;
                                        else if(yMove > 0.01f)
                                            image = Assets.manCityRunningWithBallS1;
                                        else if(yMove < -0.01f)
                                            image = Assets.manCityRunningWithBallN1;
                                    }
                                }
                                // System.out.println("--------------------------- " + pressingContor);
                            } else {
                                // System.out.println("No path found"); // Dacă nu a găsit calea, afișează un mesaj corespunzător
                            }

                            /// Daca mingea inainteaza pe directia NE, ajustam camera
                            if (x > lastX && y < lastY) {
                                if (-camX < Map.width - 1536 - 5)
                                    camX -= xMove;
                                if (-camY > 5)
                                    camY -= yMove;
                            }
                            /// Daca mingea inainteaza pe directia NW, ajustam camera
                            if (x < lastX && y < lastY) {
                                if (-camX > 5)
                                    camX -= xMove;
                                if (-camY > 5)
                                    camY -= yMove;
                            }
                            /// Daca mingea inainteaza pe directia SW, ajustam camera
                            if (x < lastX && y > lastY) {
                                if (-camX > 5)
                                    camX -= xMove;
                                if (-camY < (Map.height - 768) - 5)
                                    camY -= yMove;
                            }
                            /// Daca mingea inainteaza pe directia SE, ajustam camera
                            if (x > lastX && y > lastY) {
                                if (-camX < Map.width - 1536 - 5)
                                    camX -= xMove;
                                if (-camY < (Map.height - 768) - 5)
                                    camY -= yMove;
                            }
                        }
                    }
                }

                //Setez startul de animatie fara minge
                if(!HasBall && behavior != "Control Player"){
                    if(xMove != 0 || yMove != 0)
                        fanion = 24;
                    if(xMove > 0 && yMove > 0)
                        image = Assets.manCityRunningWithoutBallSE1;
                    else if(xMove > 0 && yMove < 0)
                        image = Assets.manCityRunningWithoutBallNE1;
                    else if(xMove < 0 && yMove > 0)
                        image = Assets.manCityRunningWithoutBallSW1;
                    else if(xMove < 0 && yMove < 0)
                        image = Assets.manCityRunningWithoutBallNW1;
                    else if(xMove > 0 )
                        image = Assets.manCityRunningWithoutBallE1;
                    else if(xMove < 0 )
                        image = Assets.manCityRunningWithoutBallW1;
                    else if(yMove > 0)
                        image = Assets.manCityRunningWithoutBallS1;
                    else if(yMove < 0)
                        image = Assets.manCityRunningWithoutBallN1;
                    else if(flag == 1)
                        image = Assets.manCityWait1;
                    else
                        image = Assets.manCityWait2;
                }

            } else {      /// Actualizez animatiile pentru fiecare situatie
                fanion--;
                ///Actualizeaza pozitia
                Move();

                /// Animatie cu minge

                ///Animatie stanga-jos
                if (image == Assets.manCityRunningWithBallSW1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallSW2;
                }
                if (image == Assets.manCityRunningWithBallSW2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallSW3;
                }
                if (image == Assets.manCityRunningWithBallSW3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallSW4;
                }///Animatie stanga-sus
                if (image == Assets.manCityRunningWithBallNW1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallNW2;
                }
                if (image == Assets.manCityRunningWithBallNW2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallNW3;
                }
                if (image == Assets.manCityRunningWithBallNW3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallNW4;
                }///Animatie dreapta-jos
                if (image == Assets.manCityRunningWithBallSE1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallSE2;
                }
                if (image == Assets.manCityRunningWithBallSE2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallSE3;
                }
                if (image == Assets.manCityRunningWithBallSE3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallSE4;
                }///Animatie dreapta-sus
                if (image == Assets.manCityRunningWithBallNE1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallNE2;
                }
                if (image == Assets.manCityRunningWithBallNE2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallNE3;
                }
                if (image == Assets.manCityRunningWithBallNE3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallNE4;
                }
                ///Animatie dreapta
                if (image == Assets.manCityRunningWithBallE1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallE2;
                }
                if (image == Assets.manCityRunningWithBallE2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallE3;
                }
                if (image == Assets.manCityRunningWithBallE3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallE4;
                }
                ///Animatie stanga
                if (image == Assets.manCityRunningWithBallW1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallW2;
                }
                if (image == Assets.manCityRunningWithBallW2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallW3;
                }
                if (image == Assets.manCityRunningWithBallW3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallW4;
                }
                ///Animatie sus
                if (image == Assets.manCityRunningWithBallN1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallN2;
                }
                if (image == Assets.manCityRunningWithBallN2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallN3;
                }
                if (image == Assets.manCityRunningWithBallN3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallN4;
                }
                ///Animatie jos
                if (image == Assets.manCityRunningWithBallS1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallS2;
                }
                if (image == Assets.manCityRunningWithBallS2 && fanion == 12) {
                    image = Assets.manCityRunningWithBallS3;
                }
                if (image == Assets.manCityRunningWithBallS3 && fanion == 6) {
                    image = Assets.manCityRunningWithBallS4;
                }

                /// Animatie fara minge

                ///Animatie stanga-jos
                if (image == Assets.manCityRunningWithoutBallSW1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallSW2;
                }
                if (image == Assets.manCityRunningWithoutBallSW2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallSW3;
                }
                if (image == Assets.manCityRunningWithoutBallSW3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallSW4;
                }///Animatie stanga-sus
                if (image == Assets.manCityRunningWithoutBallNW1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallNW2;
                }
                if (image == Assets.manCityRunningWithoutBallNW2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallNW3;
                }
                if (image == Assets.manCityRunningWithoutBallNW3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallNW4;
                }///Animatie dreapta-jos
                if (image == Assets.manCityRunningWithoutBallSE1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallSE2;
                }
                if (image == Assets.manCityRunningWithoutBallSE2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallSE3;
                }
                if (image == Assets.manCityRunningWithoutBallSE3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallSE4;
                }///Animatie dreapta-sus
                if (image == Assets.manCityRunningWithoutBallNE1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallNE2;
                }
                if (image == Assets.manCityRunningWithoutBallNE2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallNE3;
                }
                if (image == Assets.manCityRunningWithoutBallNE3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallNE4;
                }
                ///Animatie dreapta
                if (image == Assets.manCityRunningWithoutBallE1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallE2;
                }
                if (image == Assets.manCityRunningWithoutBallE2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallE3;
                }
                if (image == Assets.manCityRunningWithoutBallE3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallE4;
                }
                ///Animatie stanga
                if (image == Assets.manCityRunningWithBallW1 && fanion == 18) {
                    image = Assets.manCityRunningWithBallW2;
                }
                if (image == Assets.manCityRunningWithoutBallW2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallW3;
                }
                if (image == Assets.manCityRunningWithoutBallW3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallW4;
                }
                ///Animatie sus
                if (image == Assets.manCityRunningWithoutBallN1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallN2;
                }
                if (image == Assets.manCityRunningWithoutBallN2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallN3;
                }
                if (image == Assets.manCityRunningWithoutBallN3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallN4;
                }
                ///Animatie jos
                if (image == Assets.manCityRunningWithoutBallS1 && fanion == 18) {
                    image = Assets.manCityRunningWithoutBallS2;
                }
                if (image == Assets.manCityRunningWithoutBallS2 && fanion == 12) {
                    image = Assets.manCityRunningWithoutBallS3;
                }
                if (image == Assets.manCityRunningWithoutBallS3 && fanion == 6) {
                    image = Assets.manCityRunningWithoutBallS4;
                }
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
        }else {
            Player[aux].behavior = "Receive Ball";
            // Daca mingea a fost pasata catre un jucator din propria jumatate de teren, contabilizam pasa
            if(Player[aux].GetX() < Map.width/2)
                contorPassesTeam1++;

        }
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
        haveBeenTakled = 25;
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
        else //Daca un adversar are mingea, il setam pe el ca target: Vom schimba pe cel mai apropiat jucator de adversarul care se afla in
        // posesia mingii
        {
            for(int i=0;i<NoPlayers;i++){
                if(Player[i].id_team == id_team) {
                    if (ID == Player[i].GetID()) {
                        /// Daca jucatorul tinta este tot jucatorul curent, nu face nimic
                    }
                    else if (Math.sqrt(Math.pow(Player[j].GetX() - Player[i].GetX(), 2) + Math.pow(Player[j].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        if(Player[i].GetX() < x ) {
                            closest_player = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[j].GetX() - Player[i].GetX(), 2) + Math.pow(Player[j].GetY() - Player[i].GetY(), 2));
                        }
                    }
                }
            }
        }
        if(closest_player != -1)
            if(!Player[closest_player].Gk){
                Player[closest_player].behavior = "Control Player";
                Player[closest_player].haveBeenTakled = 40;
                behavior = "Wait";
            }
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

    private void GkMovement(float yItem)
    {
        float targetX = homeRegionX, targetY; // coordonatele pozitiei tinta a jucatorului
        if(yItem > homeRegionY + LeftGoal.height/2)
            targetY = homeRegionY + LeftGoal.height/2 - 25;
        else if(yItem < homeRegionY - LeftGoal.height/2)
            targetY = homeRegionY - LeftGoal.height/2 + 25;
        else
            targetY = yItem; // Goalkeeperul nu va inainta mai mult de 10 sutimi din distanta laterala dintre el si minge/adversarul cu minge

        ///  Daca jucatorul a ajuns in pozitia target, ramane acolo si nu avanseaza
        if(Math.abs(x-targetX) < 2.0f)
            xMove = 0.0f;
        else
        if(targetX < x)
            xMove = -1.5f;
        else
            yMove = 1.5f;
        if(Math.abs(y-targetY) < 3.0f)
            yMove = 0.0f;
        else
        if(targetY < y)
            yMove = -2.9f;
        else
            yMove = 2.9f;
    }

    private void CPUpass(String dir, int targetPlayerID)
    {
        if(dir == "SW")
        {
            ball.SetX(x - 24);
            ball.SetY(y + 48);
            image = Assets.manCityPassSW;
            SetSpeed(0.0f);
        }
        if(dir == "NW")
        {
            ball.SetX(x-24);
            ball.SetY(y-24);
            image = Assets.manCityPassNW;
            SetSpeed(0.0f);
        }
        if(dir == "SE")
        {
            ball.SetX(x + 24);
            ball.SetY(y + 24);
            image = Assets.manCityPassSE;
            SetSpeed(0.0f);
        }
        if(dir == "NE")
        {
            ball.SetX(x);
            ball.SetY(y);
            image = Assets.manCityPassNE;
            SetSpeed(0.0f);
        }
        if(dir == "S")
        {
            ball.SetX(x);
            ball.SetY(y + 48);
            image = Assets.manCityPassS;
            SetSpeed(0.0f);
        }
        if(dir == "N")
        {
            ball.SetX(x - 24);
            ball.SetY(y - 24);
            image = Assets.manCityPassN;
            SetSpeed(0.0f);
        }
        if(dir == "E")
        {
            ball.SetX(x);
            ball.SetY(y);
            image = Assets.manCityPassE;
            SetSpeed(0.0f);
        }
        if(dir == "W")
        {
            ball.SetX(x - 48);
            ball.SetY(y);
            image = Assets.manCityPassW;
            SetSpeed(0.0f);
        }
        ball.setDirection(true);
        Ball.actualizareX = (Player[targetPlayerID].GetX()-ball.GetX())/55;
        Ball.actualizareY = (Player[targetPlayerID].GetY()-ball.GetY())/55;
        behavior="Return to Home Region";
        HasBall = false;
        Player[targetPlayerID].behavior = "Receive Ball";
        haveBeenTakled = 25;
        fanion = 24;
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
