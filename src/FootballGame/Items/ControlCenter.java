package FootballGame.Items;

import FootballGame.Maps.Map;
import FootballGame.RefLinks;
import FootballGame.States.PlayState;

import static FootballGame.Maps.Map.LeftGoal;
import static FootballGame.Maps.Map.RightGoal;
import static FootballGame.Maps.Menu.highGraphicsSettings;
import static FootballGame.States.PlayState.*;

public class ControlCenter {
    protected RefLinks refLink; /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    public static int NoOfChaseBallers;
    public static boolean change;
    public static boolean shoot;
    public static boolean goal;
    public static boolean goalScored1;
    public static boolean goalScored2;
    public static int goalScoredTeam1;
    public static int goalScoredTeam2;
    public static int contorPassesTeam1;
    public static int contorShotsTeam1;
    private long startTime;

    public ControlCenter(RefLinks refLink, Character[] Player)
    {
        this.refLink = refLink;
        change = false;
        shoot = false;
        goal = false;
        goalScored1 = false;
        goalScored2 = false;
        NoOfChaseBallers = 1;
        goalScoredTeam1 = 0;
        goalScoredTeam2 = 0;
        contorPassesTeam1 = 0;
        contorShotsTeam1 = 0;

        if (PlayerCity.flag == 1){
            PlayerCity.status = "attacking";
            Player[0] = new PlayerCity(refLink, Map.width / 2,Map.height / 2, (byte) 0, false);
            Player[1] = new PlayerCity(refLink,450,250, (byte) 1, false);
            Player[2] = new PlayerCity(refLink,400,500, (byte) 2, false);
            Player[3] = new PlayerCity(refLink,450,750, (byte) 3, false);
            Player[4] = new PlayerCity(refLink,700,500, (byte) 4, false);
            Player[5] = new PlayerCity(refLink,850,600, (byte) 5, false);
            Player[6] = new PlayerCity(refLink,250,500, (byte) 6, true);
        }
        if(PlayerArsenal.flag == 1){
            PlayerArsenal.status = "attacking";
            Player[0] = new PlayerArsenal(refLink, Map.width / 2,Map.height / 2, (byte) 0, false);
            Player[1] = new PlayerArsenal(refLink,450,250, (byte) 1, false);
            Player[2] = new PlayerArsenal(refLink,400,500, (byte) 2, false);
            Player[3] = new PlayerArsenal(refLink,450,750, (byte) 3, false);
            Player[4] = new PlayerArsenal(refLink,700,500, (byte) 4, false);
            Player[5] = new PlayerArsenal(refLink,850,600, (byte) 5, false);
            Player[6] = new PlayerArsenal(refLink,250,500, (byte) 6, true);
        }
        if (PlayerChelsea.flag == 1) {
            PlayerChelsea.status = "attacking";
            Player[0] = new PlayerChelsea(refLink, Map.width / 2, Map.height / 2, (byte) 0, false);
            Player[1] = new PlayerChelsea(refLink, 450, 250, (byte) 1, false);
            Player[2] = new PlayerChelsea(refLink, 400, 500, (byte) 2, false);
            Player[3] = new PlayerChelsea(refLink, 450, 750, (byte) 3, false);
            Player[4] = new PlayerChelsea(refLink, 700, 500, (byte) 4, false);
            Player[5] = new PlayerChelsea(refLink, 850, 600, (byte) 5, false);
            Player[6] = new PlayerChelsea(refLink, 250, 500, (byte) 6, true);
        }
        if (PlayerLiverpool.flag == 1) {
            PlayerLiverpool.status = "attacking";
            Player[0] = new PlayerLiverpool(refLink, Map.width / 2, Map.height / 2, (byte) 0, false);
            Player[1] = new PlayerLiverpool(refLink, 450, 250, (byte) 1, false);
            Player[2] = new PlayerLiverpool(refLink, 400, 500, (byte) 2, false);
            Player[3] = new PlayerLiverpool(refLink, 450, 750, (byte) 3, false);
            Player[4] = new PlayerLiverpool(refLink, 700, 500, (byte) 4, false);
            Player[5] = new PlayerLiverpool(refLink, 850, 600, (byte) 5, false);
            Player[6] = new PlayerLiverpool(refLink, 250, 500, (byte) 6, true);
        }
        if(PlayerCity.flag == 2){
            PlayerCity.status = "defending";
            Player[7] = new PlayerCity(refLink, 1000,636, (byte) 7, false);
            Player[8] = new PlayerCity(refLink,1300,676, (byte) 8, false);
            Player[9] = new PlayerCity(refLink,1500,300, (byte) 9, false);
            Player[10] = new PlayerCity(refLink,1250,400, (byte) 10, false);
            Player[11] = new PlayerCity(refLink,1000,300, (byte) 11, false);
            Player[12] = new PlayerCity(refLink,1500,650, (byte) 12, false);
            Player[13] = new PlayerCity(refLink,Map.width-300,500, (byte) 13, true);
        }
        if(PlayerArsenal.flag == 2){
            PlayerArsenal.status = "defending";
            Player[7] = new PlayerArsenal(refLink, 1000,636, (byte) 7, false);
            Player[8] = new PlayerArsenal(refLink,1300,676, (byte) 8, false);
            Player[9] = new PlayerArsenal(refLink,1500,300, (byte) 9, false);
            Player[10] = new PlayerArsenal(refLink,1250,400, (byte) 10, false);
            Player[11] = new PlayerArsenal(refLink,1000,300, (byte) 11, false);
            Player[12] = new PlayerArsenal(refLink,1500,650, (byte) 12, false);
            Player[13] = new PlayerArsenal(refLink,Map.width-300,500, (byte) 13, true);
        }
        if(PlayerChelsea.flag == 2){
            PlayerChelsea.status = "defending";
            Player[7] = new PlayerChelsea(refLink, 1000,636, (byte) 7, false);
            Player[8] = new PlayerChelsea(refLink,1300,676, (byte) 8, false);
            Player[9] = new PlayerChelsea(refLink,1500,300, (byte) 9, false);
            Player[10] = new PlayerChelsea(refLink,1250,400, (byte) 10, false);
            Player[11] = new PlayerChelsea(refLink,1000,300, (byte) 11, false);
            Player[12] = new PlayerChelsea(refLink,1500,650, (byte) 12, false);
            Player[13] = new PlayerChelsea(refLink,Map.width-300,500, (byte) 13, true);
        }
        if(PlayerLiverpool.flag == 2){
            PlayerLiverpool.status = "defending";
            Player[7] = new PlayerLiverpool(refLink, 1000,636, (byte) 7, false);
            Player[8] = new PlayerLiverpool(refLink,1300,676, (byte) 8, false);
            Player[9] = new PlayerLiverpool(refLink,1500,300, (byte) 9, false);
            Player[10] = new PlayerLiverpool(refLink,1250,400, (byte) 10, false);
            Player[11] = new PlayerLiverpool(refLink,1000,300, (byte) 11, false);
            Player[12] = new PlayerLiverpool(refLink,1500,650, (byte) 12, false);
            Player[13] = new PlayerLiverpool(refLink,Map.width-300,500, (byte) 13, true);
        }

        for(int i=0;i<NoPlayers; i++) {
            Player[i].haveBeenTakled = 100;
            //Daca jucatorul se afla in echipa din prima jumatate de teren inaintea inceperii meciului, inseamna ca va ataca si vom ajusta regiunea de atac in consecinta
            if (Player[i].GetX() < Map.width / 2)
                if (!Player[i].Gk)
                    Player[i].homeRegionX += 300.0f;
        }



        Player[0].HasBall = true;
        Player[0].behavior = "Control Player";
        // Măsurăm durata unui meci de fotbal
        startTime = System.nanoTime();

    }

    public void Update()
    {
        if(change){
            change = false;
            //////  Setez noile statusuri ale celor doua echipe ///////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////
            if(PlayerCity.flag != 0) {
                if (PlayerCity.status == "defending") {
                    PlayerCity.status = "attacking";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 1 && !Player[i].Gk)
                            if(PlayerCity.flag == 1)
                                Player[i].homeRegionX += 300.0f;
                            else
                                Player[i].homeRegionX -= 300.0f;
                }
                else if (PlayerCity.status == "attacking") {
                    PlayerCity.status = "defending";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 1 && !Player[i].Gk)
                            if(PlayerCity.flag == 1)
                                Player[i].homeRegionX -= 300.0f;
                            else
                                Player[i].homeRegionX += 300.0f;
                }
            }
            if(PlayerArsenal.flag != 0) {
                if (PlayerArsenal.status == "defending") {
                    PlayerArsenal.status = "attacking";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 2 && !Player[i].Gk)
                            if(PlayerArsenal.flag == 1)
                                Player[i].homeRegionX += 300.0f;
                            else
                                Player[i].homeRegionX -= 300.0f;
                }
                else if (PlayerArsenal.status == "attacking") {
                    PlayerArsenal.status = "defending";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 2 && !Player[i].Gk)
                            if(PlayerArsenal.flag == 1)
                                Player[i].homeRegionX -= 300.0f;
                            else
                                Player[i].homeRegionX += 300.0f;
                }
            }
            if(PlayerChelsea.flag != 0) {
                if (PlayerChelsea.status == "defending") {
                    PlayerChelsea.status = "attacking";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 3 && !Player[i].Gk)
                            if(PlayerChelsea.flag == 1)
                                Player[i].homeRegionX += 300.0f;
                            else
                                Player[i].homeRegionX -= 300.0f;
                }
                else if (PlayerChelsea.status == "attacking") {
                    PlayerChelsea.status = "defending";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 3 && !Player[i].Gk)
                            if(PlayerChelsea.flag == 1)
                                Player[i].homeRegionX -= 300.0f;
                            else
                                Player[i].homeRegionX += 300.0f;
                }
            }
            if(PlayerLiverpool.flag != 0) {
                if (PlayerLiverpool.status == "defending") {
                    PlayerLiverpool.status = "attacking";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 4 && !Player[i].Gk)
                            if(PlayerLiverpool.flag == 1)
                                Player[i].homeRegionX += 300.0f;
                            else
                                Player[i].homeRegionX -= 300.0f;
                }
                else if (PlayerLiverpool.status == "attacking") {
                    PlayerLiverpool.status = "defending";
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 4 && !Player[i].Gk)
                            if(PlayerLiverpool.flag == 1)
                                Player[i].homeRegionX -= 300.0f;
                            else
                                Player[i].homeRegionX += 300.0f;
                }
            }
        }

        // Daca a fost marcat un gol sau un autogol la poarta CPU, ajustam coordonatele jucatorilor
        // pentru reluarea jocului de la mijlocul terenului.
        if (goalScored1) {
            goalScored1 = false;
            // Setam coordonatalele atacantului utilizatorului in afara centrului terenului.
            Player[0].SetX(Map.width/2 - 300);
            Player[0].SetY(Map.height/2);
            Player[0].behavior = "Control Player";
            for (int i=1; i<NoPlayers; i++)
            {
                Player[i].SetX(Player[i].homeRegionX);
                Player[i].SetY(Player[i].homeRegionY);
                Player[i].haveBeenTakled = 100; // Toti jucatorii asteapta 1 min inceperea jocului, timp in care nu vor face nimic .
            }

            // Setam coordonatalele atacantului advers la centrul terenului.
            Player[NoPlayers/2].SetX(Map.width/2);
            Player[NoPlayers/2].SetY(Map.height/2);
            Player[NoPlayers/2].HasBall = true;
            // Va forta atacantul CPU sa paseze mingea de la centru .
            Player[NoPlayers/2].SetPressing(16);
        }

        // Daca a fost marcat un gol sau un autogol la poarta utilizatorului, ajustam coordonatele jucatorilor
        // pentru reluarea jocului de la mijlocul terenului.
        if (goalScored2) {
            goalScored2 = false;
            // Setam coordonatalele atacantului utilizatorului la centrul terenului.
            Player[0].SetX(Map.width/2);
            Player[0].SetY(Map.height/2);
            Player[0].HasBall = true;
            Player[0].behavior = "Control Player";
            PlayState.cursor.SetX(Player[0].GetX());
            PlayState.cursor.SetY(Player[0].GetY());
            for (int i=1; i<NoPlayers; i++)
            {
                Player[i].SetX(Player[i].homeRegionX);
                Player[i].SetY(Player[i].homeRegionY);
                Player[i].haveBeenTakled = 100; // Toti jucatorii asteapta 1 min inceperea jocului, timp in care nu vor face nimic .
            }
        }


        if(ball.GetDirection())
        {
            // Verificam daca mingea a trecut de marginea din partea dreapta a terenului
            if(ball.GetX()>1670) {
                // Daca a trecut prin poarta, este gol
                if (ball.GetY() > 450 && ball.GetY() < 550){
                    // Este gol, actualizam scorul
                    ball.setDirection(false);
                    RightGoal.SetAnimation(48, (byte) 1);
                }
                    else{ // Daca nu este gol, va fi out sau corner, dupa caz

                        //verificam daca este out de poarta
                    if ((PlayerCity.flag == 2 && PlayerCity.status == "defending") || (PlayerArsenal.flag == 2 && PlayerArsenal.status == "defending") || (PlayerChelsea.flag == 2 && PlayerChelsea.status == "defending") || (PlayerLiverpool.flag == 2 && PlayerLiverpool.status == "defending")) {
                        for (int i = 0; i < NoPlayers; i++) {
                            Player[i].SetX(Player[i].homeRegionX);
                            Player[i].SetY(Player[i].homeRegionY);
                        }
                        ball.setDirection(false);
                        //Setam mingea portarului echipei oaspete
                        Player[NoPlayers - 1].HasBall = true;
                        Player[NoPlayers - 1].haveBeenTakled = 40;
                        change = true;
                    }

                        // verificam daca este corner
                    if ((PlayerCity.flag == 2 && PlayerCity.status == "attacking") || (PlayerArsenal.flag == 2 && PlayerArsenal.status == "attacking") || (PlayerLiverpool.flag == 2 && PlayerLiverpool.status == "attacking") || (PlayerChelsea.flag == 2 && PlayerChelsea.status == "attacking"))
                    { // Setam coordonatele jucatorilor din careu
                        if(ball.GetY() < Map.height/2) {
                            Player[0].SetX(1646);
                            Player[0].SetY(30);
                        }else
                        {
                            Player[0].SetX(1665);
                            Player[0].SetY(972);
                        }
                        Player[0].HasBall = true;
                        Player[0].behavior = "Control Player";
                        PlayState.cursor.SetX(Player[0].GetX());
                        PlayState.cursor.SetY(Player[0].GetY());
                        ball.setDirection(false);
                        change = true;

                        Player[1].SetX(1450);
                        Player[1].SetY(440);
                        Player[2].SetX(1500);
                        Player[2].SetY(655);
                        for(int i = 3; i < NoPlayers; i++)
                        {
                            Player[i].SetX(Player[i].homeRegionX);
                            Player[i].SetY(Player[i].homeRegionY);
                        }
                    }
                }
            }

            // Verificam daca mingea a trecut de marginea din partea stanga a terenului
            if(ball.GetX() < 230) {
                // Daca a trecut prin poarta, este gol
                if (ball.GetY() > 450 && ball.GetY() < 550){
                    // Este gol, actualizam scorul
                    LeftGoal.SetAnimation(48, (byte) 1);
                    ball.setDirection(false);
                }
                else{ // Daca nu este gol, este out sau corner
                    //verificam daca este out de poarta
                    if ((PlayerCity.flag == 1 && PlayerCity.status == "defending") || (PlayerArsenal.flag == 1 && PlayerArsenal.status == "defending") || (PlayerChelsea.flag == 1 && PlayerChelsea.status == "defending") || (PlayerLiverpool.flag == 1 && PlayerLiverpool.status == "defending")) {
                        for (int i = 0; i < NoPlayers; i++) {
                            Player[i].SetX(Player[i].homeRegionX);
                            Player[i].SetY(Player[i].homeRegionY);
                        }
                        ball.setDirection(false);
                        //Setam mingea portarului echipei gazda
                        Player[NoPlayers/2 -1].HasBall = true;
                        Player[NoPlayers/2 -1].haveBeenTakled = 40;
                        change = true;
                    }
                    // verificam daca este corner
                    if ((PlayerCity.flag == 1 && PlayerCity.status == "attacking") || (PlayerArsenal.flag == 1 && PlayerArsenal.status == "attacking") || (PlayerLiverpool.flag == 1 && PlayerLiverpool.status == "attacking") || (PlayerChelsea.flag == 1 && PlayerChelsea.status == "attacking"))
                    { // Setam coordonatele jucatorilor din careu
                        if(ball.GetY() < Map.height/2) {
                            Player[NoPlayers/2].SetX(266);
                            Player[NoPlayers/2].SetY(30);
                            camX = -5;
                            camY = -5;
                        }else
                        {
                            Player[NoPlayers/2].SetX(280);
                            Player[NoPlayers/2].SetY(972);
                            camX = -5;
                            camY = -(Map.height - 768);
                        }
                        Player[NoPlayers/2].HasBall = true;
                        Player[NoPlayers/2].haveBeenTakled = 150;
                        ball.setDirection(false);
                        change = true;
                        Player[NoPlayers/2].SetPressing(16);

                        Player[NoPlayers/2+1].SetX(380);
                        Player[NoPlayers/2+1].SetY(440);
                        Player[NoPlayers/2+2].SetX(455);
                        Player[NoPlayers/2+2].SetY(455);
                        for(int i = 0; i < NoPlayers; i++)
                        {
                            if(i!= NoPlayers/2 && i!= NoPlayers/2+1 && i!=NoPlayers/2+2) {
                                Player[i].SetX(Player[i].homeRegionX);
                                Player[i].SetY(Player[i].homeRegionY);
                            }
                        }
                    }
                }
            }

            // Verificam daca mingea a trecut de marginea superioara sau inferioara a terenului. Daca a trecut, este out lateral.
            if(ball.GetY() < 30 || ball.GetY() > 972)
            {
                change = true;
                // Daca echipa gazda beneficiaza de out, isi seteaza jucatorul care sa il execute
                if ((PlayerCity.flag == 1 && PlayerCity.status == "defending") || (PlayerArsenal.flag == 1 && PlayerArsenal.status == "defending") || (PlayerChelsea.flag == 1 && PlayerChelsea.status == "defending") || (PlayerLiverpool.flag == 1 && PlayerLiverpool.status == "defending"))
                {
                    Player[0].HasBall = true;
                    Player[0].haveBeenTakled = 40;
                    Player[0].SetX(ball.GetX());
                    if(ball.GetY() < 30)
                        Player[0].SetY(30);
                    else
                        Player[0].SetY(972);
                    for(int i = 1; i < NoPlayers; i++)
                    {
                        Player[i].SetX(Player[i].homeRegionX);
                        Player[i].SetY(Player[i].homeRegionY);
                    }
                    ball.setDirection(false);

                }// Daca echipa oaspete beneficiaza de out, isi seteaza jucatorul care sa il execute
                else if((PlayerCity.flag == 2 && PlayerCity.status == "defending") || (PlayerArsenal.flag == 2 && PlayerArsenal.status == "defending") || (PlayerChelsea.flag == 2 && PlayerChelsea.status == "defending") || (PlayerLiverpool.flag == 2 && PlayerLiverpool.status == "defending"))
                {
                    Player[NoPlayers/2].HasBall = true;
                    Player[NoPlayers/2].haveBeenTakled = 40;
                    Player[NoPlayers/2].SetPressing(16);
                    Player[NoPlayers/2].SetX(ball.GetX());
                    if(ball.GetY() < 30)
                        Player[NoPlayers/2].SetY(30);
                    else
                        Player[NoPlayers/2].SetY(972);
                    for(int i = 0; i < NoPlayers; i++)
                    {
                        if(i!=NoPlayers/2)
                        {
                            Player[i].SetX(Player[i].homeRegionX);
                            Player[i].SetY(Player[i].homeRegionY);
                        }
                    }
                    ball.setDirection(false);
                }

            }

        }


        if (PlayerCity.status == "attacking")
        {
            if (!ball.GetDirection()) {
                float xRange, yRange;
                int hasBall = 0;
                int SupAtt = 0;
                int LinkUp = 0;
                int LinkUp2 = -1;
                double shortest_distance = 5000.0f;
                float mostAdvancedPlayer;
                if (PlayerCity.flag == 1)
                    mostAdvancedPlayer = 0.0f;
                else
                    mostAdvancedPlayer = 2000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;

                    //Se cauta cel mai avansat jucator fara minge al echipei pentru a il face Support Attacker
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1 && i != hasBall)
                        if (PlayerCity.flag == 1 && Player[i].GetX() > mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        } else if (PlayerCity.flag == 2 && Player[i].GetX() < mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        }

                //Se cauta cel mai apropiat liber coechipier pentru a il face Link Up Player
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1 && i != hasBall && i != SupAtt && !Player[i].Gk) {
                        xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            boolean FreePlayer = true;
                            for (int j = 1; j <= 55; j++)
                                for (int adv = 0; adv < NoPlayers; adv++) {
                                    if (Player[adv].id_team != Player[i].id_team) {
                                        // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                        yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                        // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                        if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                            FreePlayer = false;
                                        }
                                    }
                                }
                            if (FreePlayer) {
                                LinkUp = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                        }
                    }

                // Daca echipa are ca strategie ofensiva jocul de posesie, va avea un linkUp Player in plus
                if(PlayerCity.attackStrategy == "Possession Game"){
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 1 && i != hasBall && i != SupAtt && !Player[i].Gk && i!=LinkUp) {
                            xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                boolean FreePlayer = true;
                                for (int j = 1; j <= 55; j++)
                                    for (int adv = 0; adv < NoPlayers; adv++) {
                                        if (Player[adv].id_team != Player[i].id_team) {
                                            // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                                FreePlayer = false;
                                            }
                                        }
                                    }
                                if (FreePlayer) {
                                    LinkUp2 = i;
                                    shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                                }
                            }
                        }
                }

                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1 && i != hasBall) {
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == LinkUp)
                            Player[i].behavior = "Link Up Player";
                        if (i == LinkUp2)
                            Player[i].behavior = "Link Up Player";
                        if (i == SupAtt)
                            Player[i].behavior = "Support Attacker";

                    }
            }
        }
        if (PlayerArsenal.status == "attacking")
        {
            if (!ball.GetDirection()) {
                float xRange, yRange;

                int hasBall = 0;
                int SupAtt = 0;
                int LinkUp = 0;
                int LinkUp2 = -1;
                double shortest_distance = 5000.0f;
                float mostAdvancedPlayer;
                if (PlayerArsenal.flag == 1)
                    mostAdvancedPlayer = 0.0f;
                else
                    mostAdvancedPlayer = 2000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;

                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2 && i != hasBall)
                        if (PlayerArsenal.flag == 1 && Player[i].GetX() > mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        } else if (PlayerArsenal.flag == 2 && Player[i].GetX() < mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        }
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2 && i != hasBall && i != SupAtt && !Player[i].Gk) {
                        xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            boolean FreePlayer = true;
                            for (int j = 1; j <= 55; j++)
                                for (int adv = 0; adv < NoPlayers; adv++) {
                                    if(Player[adv].id_team!= Player[i].id_team) {
                                        // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                        yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                        // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                        if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                            FreePlayer = false;
                                        }
                                    }
                                }
                            if(FreePlayer) {
                                LinkUp = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                        }
                    }

                // Daca echipa are ca strategie ofensiva jocul de posesie, va avea un linkUp Player in plus
                if(PlayerArsenal.attackStrategy == "Possession Game") {
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 2 && i != hasBall && i != SupAtt && !Player[i].Gk && i!=LinkUp) {
                            xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                boolean FreePlayer = true;
                                for (int j = 1; j <= 55; j++)
                                    for (int adv = 0; adv < NoPlayers; adv++) {
                                        if(Player[adv].id_team!= Player[i].id_team) {
                                            // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                                FreePlayer = false;
                                            }
                                        }
                                    }
                                if(FreePlayer) {
                                    LinkUp2 = i;
                                    shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                                }
                            }
                        }
                }

                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2 && i != hasBall) {
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == LinkUp)
                            Player[i].behavior = "Link Up Player";
                        if (i == LinkUp2)
                            Player[i].behavior = "Link Up Player";
                        if (i == SupAtt)
                            Player[i].behavior = "Support Attacker";
                    }
            }
        }
        if (PlayerChelsea.status == "attacking")
        {
            if (!ball.GetDirection()) {
                float xRange, yRange;
                int hasBall = 0;
                int SupAtt = 0;
                int LinkUp = 0;
                int LinkUp2 = -1;
                double shortest_distance = 5000.0f;
                float mostAdvancedPlayer;
                if (PlayerChelsea.flag == 1)
                    mostAdvancedPlayer = 0.0f;
                else
                    mostAdvancedPlayer = 2000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;

                //Se cauta cel mai avansat jucator fara minge al echipei pentru a il face Support Attacker
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3 && i != hasBall)
                        if (PlayerChelsea.flag == 1 && Player[i].GetX() > mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        } else if (PlayerChelsea.flag == 2 && Player[i].GetX() < mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        }

                //Se cauta cel mai apropiat liber coechipier pentru a il face Link Up Player
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3 && i != hasBall && i != SupAtt && !Player[i].Gk) {
                        xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            boolean FreePlayer = true;
                            for (int j = 1; j <= 55; j++)
                                for (int adv = 0; adv < NoPlayers; adv++) {
                                    if (Player[adv].id_team != Player[i].id_team) {
                                        // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                        yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                        // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                        if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                            FreePlayer = false;
                                        }
                                    }
                                }
                            if (FreePlayer) {
                                LinkUp = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                        }
                    }

                // Daca echipa are ca strategie ofensiva jocul de posesie, va avea un linkUp Player in plus
                if(PlayerChelsea.attackStrategy == "Possession Game"){
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 3 && i != hasBall && i != SupAtt && !Player[i].Gk && i!=LinkUp) {
                            xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                boolean FreePlayer = true;
                                for (int j = 1; j <= 55; j++)
                                    for (int adv = 0; adv < NoPlayers; adv++) {
                                        if (Player[adv].id_team != Player[i].id_team) {
                                            // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                                FreePlayer = false;
                                            }
                                        }
                                    }
                                if (FreePlayer) {
                                    LinkUp2 = i;
                                    shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                                }
                            }
                        }
                }

                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3 && i != hasBall) {
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == LinkUp)
                            Player[i].behavior = "Link Up Player";
                        if (i == LinkUp2)
                            Player[i].behavior = "Link Up Player";
                        if (i == SupAtt)
                            Player[i].behavior = "Support Attacker";

                    }
            }
        }
        if (PlayerLiverpool.status == "attacking")
        {
            if (!ball.GetDirection()) {
                float xRange, yRange;
                int hasBall = 0;
                int SupAtt = 0;
                int LinkUp = 0;
                int LinkUp2 = -1;
                double shortest_distance = 5000.0f;
                float mostAdvancedPlayer;
                if (PlayerLiverpool.flag == 1)
                    mostAdvancedPlayer = 0.0f;
                else
                    mostAdvancedPlayer = 2000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;

                //Se cauta cel mai avansat jucator fara minge al echipei pentru a il face Support Attacker
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4 && i != hasBall)
                        if (PlayerLiverpool.flag == 1 && Player[i].GetX() > mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        } else if (PlayerLiverpool.flag == 2 && Player[i].GetX() < mostAdvancedPlayer) {
                            SupAtt = i;
                            mostAdvancedPlayer = Player[i].GetX();
                        }

                //Se cauta cel mai apropiat liber coechipier pentru a il face Link Up Player
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4 && i != hasBall && i != SupAtt && !Player[i].Gk) {
                        xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            boolean FreePlayer = true;
                            for (int j = 1; j <= 55; j++)
                                for (int adv = 0; adv < NoPlayers; adv++) {
                                    if (Player[adv].id_team != Player[i].id_team) {
                                        // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                        yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                        // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                        if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                            FreePlayer = false;
                                        }
                                    }
                                }
                            if (FreePlayer) {
                                LinkUp = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                        }
                    }

                // Daca echipa are ca strategie ofensiva jocul de posesie, va avea un linkUp Player in plus
                if(PlayerLiverpool.attackStrategy == "Possession Game"){
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 4 && i != hasBall && i != SupAtt && !Player[i].Gk && i!=LinkUp) {
                            xRange = (Player[hasBall].GetX() - Player[i].GetX()) / 55;
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                boolean FreePlayer = true;
                                for (int j = 1; j <= 55; j++)
                                    for (int adv = 0; adv < NoPlayers; adv++) {
                                        if (Player[adv].id_team != Player[i].id_team) {
                                            // yRange reprezinta distanta pe axa Y intre jucatorul curent si coltul lung al portii adverse, impartita pe 55 frameuri pt animatie
                                            yRange = (Player[hasBall].GetY() - Player[i].GetY()) / 55;
                                            // Daca se afla vreun jucator advers pe unul dintre cadranele pe directia sutului, nu va suta
                                            if (((int) (Player[adv].GetX() / 36) == (int) (Player[i].GetX() + (j * xRange)) / 36) && ((int) (Player[adv].GetY() / 36) == (int) (Player[i].GetY() + (j * yRange)) / 36)) {
                                                FreePlayer = false;
                                            }
                                        }
                                    }
                                if (FreePlayer) {
                                    LinkUp2 = i;
                                    shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                                }
                            }
                        }
                }

                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4 && i != hasBall) {
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == LinkUp)
                            Player[i].behavior = "Link Up Player";
                        if (i == LinkUp2)
                            Player[i].behavior = "Link Up Player";
                        if (i == SupAtt)
                            Player[i].behavior = "Support Attacker";

                    }
            }
        }

        if (PlayerCity.status == "defending"){
            if (!ball.GetDirection()) {
                int hasBall = 0;
                int SupAtt = 0;
                int MarkAtt = 0;
                int ChaseBall = 0;
                int ChaseBall2 = -1;
                double shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].behavior == "Support Attacker")
                        SupAtt = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            ChaseBall = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                        }

                if(PlayerCity.defenceStrategy == "Aggressive")
                {
                    NoOfChaseBallers = 2;
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 1 && Player[i].behavior != "Control Player" && i!= ChaseBall)
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                ChaseBall2 = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                }else
                    NoOfChaseBallers = 1;


                shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1 && i != ChaseBall && i != ChaseBall2 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2)) < shortest_distance) {
                            MarkAtt = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2));
                        }
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 1) {
                        if (Player[i].behavior == "Mark Support Attacker")
                            Player[i].behavior = "Return to Home Region";
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == ChaseBall)
                            Player[i].behavior = "Chase Ball";
                        if (i == ChaseBall2)
                            Player[i].behavior = "Chase Ball";
                        if (i == MarkAtt)
                            Player[i].behavior = "Mark Support Attacker";
                    }
            }
        }

        if (PlayerArsenal.status == "defending"){
            if (!ball.GetDirection()) {
                int hasBall = 0;
                int SupAtt = 0;
                int MarkAtt = 0;
                int ChaseBall = 0;
                int ChaseBall2 = -1;
                double shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].behavior == "Support Attacker")
                        SupAtt = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            ChaseBall = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                        }

                if(PlayerArsenal.defenceStrategy == "Aggressive")
                {
                    NoOfChaseBallers = 2;
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 2 && Player[i].behavior != "Control Player" && i!= ChaseBall)
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                ChaseBall2 = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                }else
                    NoOfChaseBallers = 1;

                shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2 && i != ChaseBall && i != ChaseBall2 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2)) < shortest_distance) {
                            MarkAtt = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2));
                        }
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 2) {
                        if (Player[i].behavior == "Mark Support Attacker")
                            Player[i].behavior = "Return to Home Region";
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == ChaseBall)
                            Player[i].behavior = "Chase Ball";
                        if (i == ChaseBall2)
                            Player[i].behavior = "Chase Ball";
                        if (i == MarkAtt)
                            Player[i].behavior = "Mark Support Attacker";
                    }
            }
        }
        if (PlayerChelsea.status == "defending"){
            if (!ball.GetDirection()) {
                int hasBall = 0;
                int SupAtt = 0;
                int MarkAtt = 0;
                int ChaseBall = 0;
                int ChaseBall2 = -1;
                double shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].behavior == "Support Attacker")
                        SupAtt = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            ChaseBall = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                        }

                if(PlayerChelsea.defenceStrategy == "Aggressive")
                {
                    NoOfChaseBallers = 2;
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 3 && Player[i].behavior != "Control Player" && i!= ChaseBall)
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                ChaseBall2 = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                }else
                    NoOfChaseBallers = 1;

                shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3 && i != ChaseBall && i != ChaseBall2 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2)) < shortest_distance) {
                            MarkAtt = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2));
                        }
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 3) {
                        if (Player[i].behavior == "Mark Support Attacker")
                            Player[i].behavior = "Return to Home Region";
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == ChaseBall)
                            Player[i].behavior = "Chase Ball";
                        if (i == ChaseBall2)
                            Player[i].behavior = "Chase Ball";
                        if (i == MarkAtt)
                            Player[i].behavior = "Mark Support Attacker";
                    }
            }
        }
        if (PlayerLiverpool.status == "defending"){
            if (!ball.GetDirection()) {
                int hasBall = 0;
                int SupAtt = 0;
                int MarkAtt = 0;
                int ChaseBall = 0;
                int ChaseBall2 = -1;
                double shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].HasBall)
                        hasBall = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].behavior == "Support Attacker")
                        SupAtt = i;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                            ChaseBall = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                        }

                if(PlayerLiverpool.defenceStrategy == "Aggressive")
                {
                    NoOfChaseBallers = 2;
                    shortest_distance = 5000.0f;
                    for (int i = 0; i < NoPlayers; i++)
                        if (Player[i].id_team == 4 && Player[i].behavior != "Control Player" && i!= ChaseBall)
                            if (Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                                ChaseBall2 = i;
                                shortest_distance = Math.sqrt(Math.pow(Player[hasBall].GetX() - Player[i].GetX(), 2) + Math.pow(Player[hasBall].GetY() - Player[i].GetY(), 2));
                            }
                }else
                    NoOfChaseBallers = 1;

                shortest_distance = 5000.0f;
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4 && i != ChaseBall && i != ChaseBall2 && Player[i].behavior != "Control Player")
                        if (Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2)) < shortest_distance) {
                            MarkAtt = i;
                            shortest_distance = Math.sqrt(Math.pow(Player[SupAtt].GetX() - Player[i].homeRegionX, 2) + Math.pow(Player[SupAtt].GetY() - Player[i].homeRegionY, 2));
                        }
                for (int i = 0; i < NoPlayers; i++)
                    if (Player[i].id_team == 4) {
                        if (Player[i].behavior == "Mark Support Attacker")
                            Player[i].behavior = "Return to Home Region";
                        if (Player[i].behavior != "Return to Home Region" && Player[i].behavior != "Control Player")
                            Player[i].behavior = "Wait";
                        if (i == ChaseBall)
                            Player[i].behavior = "Chase Ball";
                        if (i == ChaseBall2)
                            Player[i].behavior = "Chase Ball";
                        if (i == MarkAtt)
                            Player[i].behavior = "Mark Support Attacker";
                    }
            }
        }


        //Daca mingea este pasata sau sutata, va fi atacata/blocata de catre cel mai apropiat jucator care se apara
        if(ball.GetDirection()){
            int aux=-1;
            double shortest_distance = 5000.0f;
            for(int i=0; i<NoPlayers; i++) {
                if (PlayerCity.flag != 0 && PlayerCity.status == "defending" && Player[i].id_team == 1 && !Player[i].Gk)
                    if (Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        aux = i;
                        shortest_distance = Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2));
                    }
                if (PlayerArsenal.flag != 0 && PlayerArsenal.status == "defending" && Player[i].id_team == 2 && !Player[i].Gk)
                    if (Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        aux = i;
                        shortest_distance = Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2));
                    }
                if (PlayerChelsea.flag != 0 && PlayerChelsea.status == "defending" && Player[i].id_team == 3 && !Player[i].Gk)
                    if (Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        aux = i;
                        shortest_distance = Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2));
                    }
                if (PlayerLiverpool.flag != 0 && PlayerLiverpool.status == "defending" && Player[i].id_team == 4 && !Player[i].Gk)
                    if (Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2)) < shortest_distance) {
                        aux = i;
                        shortest_distance = Math.sqrt(Math.pow(ball.GetX() - Player[i].GetX(), 2) + Math.pow(ball.GetY() - Player[i].GetY(), 2));
                    }
            }
            if(Player[aux].behavior != "Control Player")
                Player[aux].AttackBall();
        }

        boolean noControlledPlayer = true;
        for(int i = 0; i< NoPlayers; i++)
            if(Player[i].behavior=="Control Player")
                noControlledPlayer = false;
            if(noControlledPlayer && refLink.GetKeyManager().switchPlayer)
                Player[0].behavior="Control Player";


        // Dupa 20 minute de joc, CPU ia decizii tactice daca este nevoie
        if(timePlayed > 20)
        {
            //Daca echipa gazda a jucat mai mult de o pasa per minut de joc in propria jumatate, echipa adversa schimba tactica defensiva daca este cazul
            if (contorPassesTeam1 >= timePlayed) {
                if (PlayerCity.flag == 2 && PlayerCity.defenceStrategy == "Conservative")
                    PlayerCity.defenceStrategy = "Aggressive";
                if (PlayerArsenal.flag == 2 && PlayerArsenal.defenceStrategy == "Conservative")
                    PlayerArsenal.defenceStrategy = "Aggressive";
                if (PlayerChelsea.flag == 2 && PlayerChelsea.defenceStrategy == "Conservative")
                    PlayerChelsea.defenceStrategy = "Aggressive";
                if (PlayerLiverpool.flag == 2 && PlayerLiverpool.defenceStrategy == "Conservative")
                    PlayerLiverpool.defenceStrategy = "Aggressive";
            }

            //Daca echipa gazda a avut mai mult de un sut o data la 5 minute de joc, echipa adversa schimba tactica defensiva daca este cazul
            if(contorShotsTeam1 >= timePlayed / 5)
            {
                if (PlayerCity.flag == 2 && PlayerCity.defenceStrategy == "Aggressive")
                    PlayerCity.defenceStrategy = "Conservative";
                if (PlayerArsenal.flag == 2 && PlayerArsenal.defenceStrategy == "Aggressive")
                    PlayerArsenal.defenceStrategy = "Conservative";
                if (PlayerChelsea.flag == 2 && PlayerChelsea.defenceStrategy == "Aggressive")
                    PlayerChelsea.defenceStrategy = "Conservative";
                if (PlayerLiverpool.flag == 2 && PlayerLiverpool.defenceStrategy == "Aggressive")
                    PlayerLiverpool.defenceStrategy = "Conservative";
            }

        }

        // Dupa 70 minute de joc, echipa controlata de CPU ia decizii tactice in functie de scor
        if(timePlayed > 70)
        {
            // Daca echipa controlata de CPU este in avantaj, va conserva scorul
            if (goalScoredTeam1 < goalScoredTeam2){
                if (PlayerCity.flag == 2 && PlayerCity.defenceStrategy == "Aggressive")
                    PlayerCity.defenceStrategy = "Conservative";
                if (PlayerArsenal.flag == 2 && PlayerArsenal.defenceStrategy == "Aggressive")
                    PlayerArsenal.defenceStrategy = "Conservative";
                if (PlayerChelsea.flag == 2 && PlayerChelsea.defenceStrategy == "Aggressive")
                    PlayerChelsea.defenceStrategy = "Conservative";
                if (PlayerLiverpool.flag == 2 && PlayerLiverpool.defenceStrategy == "Aggressive")
                    PlayerLiverpool.defenceStrategy = "Conservative";
            }else // Daca este scor egal sau echipa controlata de CPU este in dezavantaj, va forta victoria
            {
                if (PlayerCity.flag == 2 && PlayerCity.defenceStrategy == "Conservative")
                    PlayerCity.defenceStrategy = "Aggressive";
                if (PlayerArsenal.flag == 2 && PlayerArsenal.defenceStrategy == "Conservative")
                    PlayerArsenal.defenceStrategy = "Aggressive";
                if (PlayerChelsea.flag == 2 && PlayerChelsea.defenceStrategy == "Conservative")
                    PlayerChelsea.defenceStrategy = "Aggressive";
                if (PlayerLiverpool.flag == 2 && PlayerLiverpool.defenceStrategy == "Conservative")
                    PlayerLiverpool.defenceStrategy = "Aggressive";
            }


        }

        if(timePlayed == 90)
        {
            /// S-a terminat meciul. Inapoi catre meniu.
            gameFinished = true;

            /// Masuram durata meciului
            long stopTime = System.nanoTime();
            long duration = stopTime - startTime;

            System.out.println("Grafica a fost setata pe High? " + highGraphicsSettings);
            System.out.println("Durata meciului a fost de " + duration + " nanosecunde");
            double frameDuration = duration / contor;
            System.out.println("Durata unui frame a fost de " + frameDuration + " nanosecunde");

        }

    }

}


