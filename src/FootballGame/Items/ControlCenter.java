package FootballGame.Items;

import FootballGame.Maps.Map;
import FootballGame.RefLinks;

import static FootballGame.States.PlayState.Player;

public class ControlCenter {
    protected RefLinks refLink; /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    public int NoOfChaseBallers;
    public static boolean change;

    public ControlCenter(RefLinks refLink, Character[] Player)
    {
        this.refLink = refLink;
        change = false;

        // if(PlayerCity.flag != 0 || PlayerLiverpool.flag != 0) NoOfChaseBallers = 2 else NoOfChaseBallers=1; ///////////////////

        if (PlayerCity.flag == 1){
            PlayerCity.status = "attacking";
            Player[0] = new PlayerCity(refLink, Map.width / 2,Map.height / 2, (byte) 0);
            Player[1] = new PlayerCity(refLink,672,78, (byte) 1);
            Player[2] = new PlayerCity(refLink,972,500, (byte) 2);
            Player[3] = new PlayerCity(refLink,750,178, (byte) 3);
            Player[4] = new PlayerCity(refLink,815,600, (byte) 4);
            Player[5] = new PlayerCity(refLink,750,878, (byte) 5);
        }
        if(PlayerArsenal.flag == 1){
            PlayerArsenal.status = "attacking";
            Player[0] = new PlayerArsenal(refLink, Map.width / 2,Map.height / 2, (byte) 0);
            Player[1] = new PlayerArsenal(refLink,672,78, (byte) 1);
            Player[2] = new PlayerArsenal(refLink,972,500, (byte) 2);
            Player[3] = new PlayerArsenal(refLink,750,178, (byte) 3);
            Player[4] = new PlayerArsenal(refLink,815,600, (byte) 4);
            Player[5] = new PlayerArsenal(refLink,750,896, (byte) 5);
        }
        if(PlayerCity.flag == 2){
            PlayerCity.status = "defending";
            Player[6] = new PlayerCity(refLink, 456,698, (byte) 6);
            Player[7] = new PlayerCity(refLink,272,78, (byte) 7);
            Player[8] = new PlayerCity(refLink,272,500, (byte) 8);
            Player[9] = new PlayerCity(refLink,350,178, (byte) 9);
            Player[10] = new PlayerCity(refLink,315,600, (byte) 10);
            Player[11] = new PlayerCity(refLink,187,878, (byte) 11);
        }
        if(PlayerArsenal.flag == 2){
            PlayerArsenal.status = "defending";
            Player[6] = new PlayerArsenal(refLink, 456,698, (byte) 6);
            Player[7] = new PlayerArsenal(refLink,272,78, (byte) 7);
            Player[8] = new PlayerArsenal(refLink,272,500, (byte) 8);
            Player[9] = new PlayerArsenal(refLink,350,178, (byte) 9);
            Player[10] = new PlayerArsenal(refLink,315,600, (byte) 10);
            Player[11] = new PlayerArsenal(refLink,187,878, (byte) 11);
        }



        Player[0].HasBall = true;
        Player[0].behavior = "Control Player";

        Player[10].behavior = "Mark Support Attacker";

        Player[2].behavior = "Link Up Player";
        Player[4].behavior = "Support Attacker";

        Player[7].behavior = "Chase Ball";
        Player[8].behavior = "Chase Ball";

    }

    public void Update()
    {
        if(change){
            change = false;
            //////  Setez noile statusuri ale celor doua echipe ///////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////
        }

        System.out.println("Chase ball: "+Player[7].GetXMove() + ", " + Player[7].GetYMove());

        System.out.println("Link Up ball: "+Player[2].GetX() + ", " + Player[2].GetY());

    }
}


