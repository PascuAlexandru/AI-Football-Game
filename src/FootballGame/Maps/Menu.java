package FootballGame.Maps;

import FootballGame.Graphics.Assets;
import FootballGame.Graphics.ImageLoader;
import FootballGame.Items.PlayerArsenal;
import FootballGame.Items.PlayerChelsea;
import FootballGame.Items.PlayerCity;
import FootballGame.Items.PlayerLiverpool;
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
    public static int mapType;/*!< O referinta Pentru Id-ul hartii ce va fi incarcate.*/
    public static boolean highGraphicsSettings;/*!< O referinta de care se va tine cont in sporirea graficii prin elemente grafice suplimentare ce vor fi incarcate.*/

    /// Se incarca toate imaginile ce vor procesa "meniul"
    ImageLoader BF= new ImageLoader();

    BufferedImage playgame0 = BF.LoadImage("/textures/Main Menu/Play Game 0.png");
    BufferedImage playgame1 = BF.LoadImage("/textures/Main Menu/Play Game 1.png");
    BufferedImage playgame2 = BF.LoadImage("/textures/Main Menu/Play Game 2.png");
    BufferedImage playgame3 = BF.LoadImage("/textures/Main Menu/Play Game 3.png");
    BufferedImage playgame4 = BF.LoadImage("/textures/Main Menu/Play Game 4.png");
    BufferedImage playgame5 = BF.LoadImage("/textures/Main Menu/Play Game 5.png");
    BufferedImage exitgame0 = BF.LoadImage("/textures/Main Menu/Exit Game 0.png");
    BufferedImage exitgame1 = BF.LoadImage("/textures/Main Menu/Exit Game 1.png");
    BufferedImage exitgame2 = BF.LoadImage("/textures/Main Menu/Exit Game 2.png");
    BufferedImage exitgame3 = BF.LoadImage("/textures/Main Menu/Exit Game 3.png");
    BufferedImage exitgame4 = BF.LoadImage("/textures/Main Menu/Exit Game 4.png");
    BufferedImage exitgame5 = BF.LoadImage("/textures/Main Menu/Exit Game 5.png");
    BufferedImage settings0 = BF.LoadImage("/textures/Main Menu/Settings 0.png");
    BufferedImage settings1 = BF.LoadImage("/textures/Main Menu/Settings 1.png");
    BufferedImage settings2 = BF.LoadImage("/textures/Main Menu/Settings 2.png");
    BufferedImage settings3 = BF.LoadImage("/textures/Main Menu/Settings 3.png");
    BufferedImage settings4 = BF.LoadImage("/textures/Main Menu/Settings 4.png");
    BufferedImage settings5 = BF.LoadImage("/textures/Main Menu/Settings 5.png");
    BufferedImage playgameenter = BF.LoadImage("/textures/Main Menu/Play Game Enter.png");
    BufferedImage exitgameenter = BF.LoadImage("/textures/Main Menu/Exit Game Enter.png");
    BufferedImage settingsenter = BF.LoadImage("/textures/Main Menu/Settings Enter.png");

    BufferedImage exitmenu1 = BF.LoadImage("/textures/Main Menu/Exit Menu 1.png");
    BufferedImage exitmenu2 = BF.LoadImage("/textures/Main Menu/Exit Menu 2.png");
    BufferedImage exitmenu3 = BF.LoadImage("/textures/Main Menu/Exit Menu 3.png");
    BufferedImage exitmenu4 = BF.LoadImage("/textures/Main Menu/Exit Menu 4.png");
    BufferedImage exitmenu5 = BF.LoadImage("/textures/Main Menu/Exit Menu 5.png");
    BufferedImage exitmenu6 = BF.LoadImage("/textures/Main Menu/Exit Menu 6.png");
    BufferedImage exitmenu7 = BF.LoadImage("/textures/Main Menu/Exit Menu 7.png");
    BufferedImage exitmenu8 = BF.LoadImage("/textures/Main Menu/Exit Menu 8.png");
    BufferedImage exitmenu9 = BF.LoadImage("/textures/Main Menu/Exit Menu 9.png");
    BufferedImage exitmenu10 = BF.LoadImage("/textures/Main Menu/Exit Menu 10.png");
    BufferedImage playmenu1 = BF.LoadImage("/textures/Main Menu/Play Menu 1.png");
    BufferedImage playmenu2 = BF.LoadImage("/textures/Main Menu/Play Menu 2.png");
    BufferedImage playmenu3 = BF.LoadImage("/textures/Main Menu/Play Menu 3.png");
    BufferedImage playmenu4 = BF.LoadImage("/textures/Main Menu/Play Menu 4.png");
    BufferedImage playmenu5 = BF.LoadImage("/textures/Main Menu/Play Menu 5.png");
    BufferedImage playmenu6 = BF.LoadImage("/textures/Main Menu/Play Menu 6.png");
    BufferedImage playmenu7 = BF.LoadImage("/textures/Main Menu/Play Menu 7.png");
    BufferedImage playmenu8 = BF.LoadImage("/textures/Main Menu/Play Menu 8.png");
    BufferedImage playmenu9 = BF.LoadImage("/textures/Main Menu/Play Menu 9.png");
    BufferedImage playmenu10 = BF.LoadImage("/textures/Main Menu/Play Menu 10.png");
    BufferedImage settingsmenu1 = BF.LoadImage("/textures/Main Menu/Settings Menu 1.png");
    BufferedImage settingsmenu2 = BF.LoadImage("/textures/Main Menu/Settings Menu 2.png");
    BufferedImage settingsmenu3 = BF.LoadImage("/textures/Main Menu/Settings Menu 3.png");
    BufferedImage settingsmenu4 = BF.LoadImage("/textures/Main Menu/Settings Menu 4.png");
    BufferedImage settingsmenu5 = BF.LoadImage("/textures/Main Menu/Settings Menu 5.png");
    BufferedImage settingsmenu6 = BF.LoadImage("/textures/Main Menu/Settings Menu 6.png");
    BufferedImage settingsmenu7 = BF.LoadImage("/textures/Main Menu/Settings Menu 7.png");
    BufferedImage settingsmenu8 = BF.LoadImage("/textures/Main Menu/Settings Menu 8.png");
    BufferedImage settingsmenu9 = BF.LoadImage("/textures/Main Menu/Settings Menu 9.png");
    BufferedImage settingsmenu10 = BF.LoadImage("/textures/Main Menu/Settings Menu 10.png");




    /*! \fn public Menu(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Menu(RefLinks refLink)  {
        this.refLink = refLink;
        highGraphicsSettings = true; // initial, la pornirea aplicatiei, graficele sunt setate implicit la High

        mapType = 2;
        image=playgame0;
    }

    /*! \fn public void Update()
       \brief Actualizeaza meniul jocului cu imagini.
    */
    public  void Update() {

        if (image == playgame0)
        {
            somn();
            image = playgame1;
        }else if (image == playgame1)
        {
            somn();
            image = playgame2;
        }else if (image == playgame2)
        {
            somn();
            image = playgame3;
        }else if (image == playgame3)
        {
            somn();
            image = playgame4;
        }else if (image == playgame4)
        {
            somn();
            image = playgame5;
        }else if (image == playgame5 && refLink.GetKeyManager().enter)
        {
            somn();
            image = playgameenter;
        }else if (image == playgameenter)
        {
            somn();
            image = playmenu1;
        }else if (image == playmenu1)
        {
            somn();
            image = playmenu2;
        }else if (image == playmenu2)
        {
            somn();
            image = playmenu3;
        }else if (image == playmenu3)
        {
            somn();
            image = playmenu4;
        }else if (image == playmenu4)
        {
            somn();
            image = playmenu5;
        }else if (image == playmenu5)
        {
            somn();
            image = playmenu6;
        }else if (image == playmenu6)
        {
            somn();
            image = playmenu7;
        }else if (image == playmenu7)
        {
            somn();
            image = playmenu8;
        }else if (image == playmenu8)
        {
            somn();
            image = playmenu9;
        }else if (image == playmenu9)
        {
            somn();
            image = playmenu10;
        }else if (image == playmenu10)
        {
            somn();
            image = Assets.P1_Arsenal;
        }else if (image == exitgame0)
        {
            somn();
            image = exitgame1;
        }else if (image == exitgame1)
        {
            somn();
            image = exitgame2;
        }else if (image == exitgame2)
        {
            somn();
            image = exitgame3;
        }else if (image == exitgame3)
        {
            somn();
            image = exitgame4;
        }else if (image == exitgame4)
        {
            somn();
            image = exitgame5;
        }else if (image == exitgame5 && refLink.GetKeyManager().enter)
        {
            somn();
            image = exitgameenter;
        }else if (image == exitgameenter)
        {
            somn();
            image = exitmenu1;
        }else if (image == exitmenu1)
        {
            somn();
            image = exitmenu2;
        }else if (image == exitmenu2)
        {
            somn();
            image = exitmenu3;
        }else if (image == exitmenu3)
        {
            somn();
            image = exitmenu4;
        }else if (image == exitmenu4)
        {
            somn();
            image = exitmenu5;
        }else if (image == exitmenu5)
        {
            somn();
            image = exitmenu6;
        }else if (image == exitmenu6)
        {
            somn();
            image = exitmenu7;
        }else if (image == exitmenu7)
        {
            somn();
            image = exitmenu8;
        }else if (image == exitmenu8)
        {
            somn();
            image = exitmenu9;
        }else if (image == exitmenu9)
        {
            somn();
            image = exitmenu10;
        }else if (image == exitmenu10)
        {
            somn();
            State.SetState(new PlayState(refLink)); ///////////////////////////////////////////////
        }
        else if (image == settings0)
        {
            somn();
            image = settings1;
        }else if (image == settings1)
        {
            somn();
            image = settings2;
        }else if (image == settings2)
        {
            somn();
            image = settings3;
        }else if (image == settings3)
        {
            somn();
            image = settings4;
        }else if (image == settings4)
        {
            somn();
            image = settings5;
        }else if (image == settings5 && refLink.GetKeyManager().enter)
        {
            somn();
            image = settingsenter;
        }else if (image == settingsenter)
        {
            somn();
            image = settingsmenu1;
        }else if (image == settingsmenu1)
        {
            somn();
            image = settingsmenu2;
        }else if (image == settingsmenu2)
        {
            somn();
            image = settingsmenu3;
        }else if (image == settingsmenu3)
        {
            somn();
            image = settingsmenu4;
        }else if (image == settingsmenu4)
        {
            somn();
            image = settingsmenu5;
        }else if (image == settingsmenu5)
        {
            somn();
            image = settingsmenu6;
        }else if (image == settingsmenu6)
        {
            somn();
            image = settingsmenu7;
        }else if (image == settingsmenu7)
        {
            somn();
            image = settingsmenu8;
        }else if (image == settingsmenu8)
        {
            somn();
            image = settingsmenu9;
        }else if (image == settingsmenu9)
        {
            somn();
            image = settingsmenu10;
        }else if (image == settingsmenu10)
        {
            somn();
            image = Assets.controls;
        }else if (image == playgame5 && refLink.GetKeyManager().right)
        {
            somn();
            image = settings0;
        }else if (image == settings5 && refLink.GetKeyManager().left)
        {
            somn();
            image = playgame0;
        }else if (image == settings5 && refLink.GetKeyManager().right)
        {
            somn();
            image = exitgame0;
        }else if (image == exitgame5 && refLink.GetKeyManager().left)
        {
            somn();
            image = settings0;
        } else if (image == Assets.controls && refLink.GetKeyManager().right)
        {
            somn();
            image = Assets.graphicsSettings;
        }else if (image == Assets.graphicsSettings && refLink.GetKeyManager().left)
        {
            somn();
            image = Assets.controls;
        }else if (image == Assets.controls && refLink.GetKeyManager().enter)
        {
            somn();
            image = Assets.controlsEnter;
        }else if (image == Assets.controlsEnter && refLink.GetKeyManager().esc)
        {
            somn();
            image = Assets.controls;
        }else if (image == Assets.graphicsSettings && refLink.GetKeyManager().enter)
        {
            somn();
            image = Assets.graphicsHigh;
        }else if (image == Assets.graphicsHigh && refLink.GetKeyManager().right)
        {
            somn();
            image = Assets.graphicsLow;
        }else if (image == Assets.graphicsLow && refLink.GetKeyManager().left)
        {
            somn();
            image = Assets.graphicsHigh;
        }else if (image == Assets.graphicsLow && refLink.GetKeyManager().esc)
        {
            somn();
            image = Assets.graphicsSettings;
            highGraphicsSettings = false;
        }else if (image == Assets.graphicsHigh && refLink.GetKeyManager().esc)
        {
            somn();
            image = Assets.graphicsSettings;
            highGraphicsSettings = true;
        }else if (image == Assets.graphicsSettings && refLink.GetKeyManager().esc)
        {
            somn();
            image = settings0;
        }else if (image == Assets.controls && refLink.GetKeyManager().esc)
        {
            somn();
            image = settings0;
        }else if(image == Assets.P1_Arsenal && refLink.GetKeyManager().right){
            somn();
            image = Assets.P1_Arsenal_Chelsea;
        }else if(image == Assets.P1_Arsenal && refLink.GetKeyManager().enter){
            somn();
            image = Assets.P1_Arsenal_Enter;
        }else if(image == Assets.P1_Arsenal_Chelsea){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Chelsea && refLink.GetKeyManager().left){
            somn();
            image = Assets.P1_Arsenal_Chelsea_Reverse;
        }else if(image == Assets.P1_Chelsea && refLink.GetKeyManager().right){
            somn();
            image = Assets.P1_Chelsea_Liverpool;
        }else if(image == Assets.P1_Chelsea && refLink.GetKeyManager().enter){
            somn();
            image = Assets.P1_Chelsea_Enter;
        }else if(image == Assets.P1_Arsenal_Chelsea_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal;
        }else if(image == Assets.P1_Chelsea_Liverpool){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Liverpool && refLink.GetKeyManager().left){
            somn();
            image = Assets.P1_Chelsea_Liverpool_Reverse;
        }else if(image == Assets.P1_Liverpool && refLink.GetKeyManager().right){
            somn();
            image = Assets.P1_Liverpool_ManCity;
        }else if(image == Assets.P1_Liverpool && refLink.GetKeyManager().enter){
            somn();
            image = Assets.P1_Liverpool_Enter;
        }else if(image == Assets.P1_Chelsea_Liverpool_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Liverpool_ManCity){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity;
        }else if(image == Assets.P1_ManCity && refLink.GetKeyManager().left){
            somn();
            image = Assets.P1_Liverpool_ManCity_Reverse;
        }else if(image == Assets.P1_ManCity && refLink.GetKeyManager().enter){
            somn();
            image = Assets.P1_ManCity_Enter;
        }else if(image == Assets.P1_Liverpool_ManCity_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Arsenal_Enter){
            somn();
            image = Assets.P1_Arsenal_Enter2;
        }else if(image == Assets.P1_Arsenal_Enter2){
            somn();
            image = Assets.P1_Arsenal_Enter3;
        }else if(image == Assets.P1_Arsenal_Enter3){
            somn();
            image = Assets.P1_Arsenal_P2_Arsenal;
        }else if(image == Assets.P1_Chelsea_Enter){
            somn();
            image = Assets.P1_Chelsea_Enter2;
        }else if(image == Assets.P1_Chelsea_Enter2){
            somn();
            image = Assets.P1_Chelsea_Enter3;
        }else if(image == Assets.P1_Chelsea_Enter3){
            somn();
            image = Assets.P1_Chelsea_P2_Arsenal;
        }else if(image == Assets.P1_Liverpool_Enter){
            somn();
            image = Assets.P1_Liverpool_Enter2;
        }else if(image == Assets.P1_Liverpool_Enter2){
            somn();
            image = Assets.P1_Liverpool_Enter3;
        }else if(image == Assets.P1_Liverpool_Enter3){
            somn();
            image = Assets.P1_Liverpool_P2_Arsenal;
        }else if(image == Assets.P1_ManCity_Enter){
            somn();
            image = Assets.P1_ManCity_Enter2;
        }else if(image == Assets.P1_ManCity_Enter2){
            somn();
            image = Assets.P1_ManCity_Enter3;
        }else if(image == Assets.P1_ManCity_Enter3){
            somn();
            image = Assets.P1_ManCity_P2_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Arsenal_Chelsea;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Arsenal_Enter;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal_Chelsea){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Arsenal_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Arsenal_Enter;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Chelsea){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Chelsea;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Arsenal_Chelsea;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Arsenal_Enter;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Chelsea){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Chelsea;
        }else if(image == Assets.P1_ManCity_P2_Arsenal && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Arsenal_Chelsea;
        }else if(image == Assets.P1_ManCity_P2_Arsenal && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Arsenal_Enter;
        }else if(image == Assets.P1_ManCity_P2_Arsenal && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Chelsea){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Chelsea;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Chelsea_Liverpool;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Arsenal_Chelsea_Reverse;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Chelsea_Enter;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Liverpool){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Liverpool;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal_Chelsea_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Arsenal;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Chelsea_Liverpool;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Arsenal_Chelsea_Reverse;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Chelsea_Enter;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea_Liverpool){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Liverpool;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Chelsea_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Arsenal;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Chelsea_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Arsenal_Chelsea_Reverse;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Chelsea_Enter;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Liverpool){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Chelsea_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Arsenal;
        }else if(image == Assets.P1_ManCity_P2_Chelsea && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Chelsea_Liverpool;
        }else if(image == Assets.P1_ManCity_P2_Chelsea && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Arsenal_Chelsea_Reverse;
        }else if(image == Assets.P1_ManCity_P2_Chelsea && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Chelsea_Enter;
        }else if(image == Assets.P1_ManCity_P2_Chelsea && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Liverpool){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Liverpool;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Chelsea_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Liverpool_ManCity;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Chelsea_Liverpool_Reverse;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Liverpool_Enter;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_ManCity){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_ManCity;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Liverpool_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Liverpool_ManCity;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Chelsea_Liverpool_Reverse;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Liverpool_Enter;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_ManCity){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_ManCity;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea_Liverpool_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Chelsea;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Liverpool_ManCity;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Chelsea_Liverpool_Reverse;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Liverpool_Enter;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool_ManCity){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_ManCity;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Liverpool_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Chelsea;
        }else if(image == Assets.P1_ManCity_P2_Liverpool && refLink.GetKeyManager().right){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Liverpool_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Liverpool && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Chelsea_Liverpool_Reverse;
        }else if(image == Assets.P1_ManCity_P2_Liverpool && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Liverpool_Enter;
        }else if(image == Assets.P1_ManCity_P2_Liverpool && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_ManCity){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Liverpool_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Chelsea;
        }else if(image == Assets.P1_Arsenal_P2_ManCity && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Liverpool_ManCity_Reverse;
        }else if(image == Assets.P1_Arsenal_P2_ManCity && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_ManCity_Enter;
        }else if(image == Assets.P1_Arsenal_P2_ManCity && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Arsenal;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_ManCity_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Arsenal_P2_Liverpool;
        }else if(image == Assets.P1_Chelsea_P2_ManCity && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Liverpool_ManCity_Reverse;
        }else if(image == Assets.P1_Chelsea_P2_ManCity && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_ManCity_Enter;
        }else if(image == Assets.P1_Chelsea_P2_ManCity && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Chelsea;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_ManCity_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Chelsea_P2_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_ManCity && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Liverpool_ManCity_Reverse;
        }else if(image == Assets.P1_Liverpool_P2_ManCity && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_ManCity_Enter;
        }else if(image == Assets.P1_Liverpool_P2_ManCity && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_Liverpool;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool_ManCity_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_Liverpool_P2_Liverpool;
        }else if(image == Assets.P1_ManCity_P2_ManCity && refLink.GetKeyManager().left){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Liverpool_ManCity_Reverse;
        }else if(image == Assets.P1_ManCity_P2_ManCity && refLink.GetKeyManager().enter){
            somn();
            somn();
            image = Assets.P1_ManCity_P2_ManCity_Enter;
        }else if(image == Assets.P1_ManCity_P2_ManCity && refLink.GetKeyManager().esc){
            somn();
            somn();
            image = Assets.P1_ManCity;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_ManCity_Reverse){
            somn();
            somn();
            somn();
            somn();
            image = Assets.P1_ManCity_P2_Liverpool;
        }else if(image == Assets.P1_Arsenal_P2_Arsenal_Enter){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Arsenal;
        }else if(image == Assets.P1_Chelsea_P2_Chelsea_Enter){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Chelsea;
        }else if(image == Assets.P1_Liverpool_P2_Liverpool_Enter){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Liverpool;
        }else if(image == Assets.P1_ManCity_P2_ManCity_Enter){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_ManCity;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Chelsea_Enter2;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter2){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Chelsea_Enter3;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter3){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Chelsea_Enter4;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter4){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Chelsea_Enter5;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter5){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Chelsea_Enter6;
        }else if(image == Assets.P1_Arsenal_P2_Chelsea_Enter6){
            somn();
            somn();
            mapType = 2;
            PlayerArsenal.flag = 1;
            PlayerChelsea.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Liverpool_Enter2;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter2){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Liverpool_Enter3;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter3){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Liverpool_Enter4;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter4){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Liverpool_Enter5;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter5){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_Liverpool_Enter6;
        }else if(image == Assets.P1_Arsenal_P2_Liverpool_Enter6){
            somn();
            somn();
            mapType = 2;
            PlayerArsenal.flag = 1;
            PlayerLiverpool.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_ManCity_Enter2;
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter2){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_ManCity_Enter3;
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter3){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_ManCity_Enter4;
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter4){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_ManCity_Enter5;
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter5){
            somn();
            somn();
            image=Assets.P1_Arsenal_P2_ManCity_Enter6;
        }else if(image == Assets.P1_Arsenal_P2_ManCity_Enter6){
            somn();
            somn();
            mapType = 2;
            PlayerArsenal.flag = 1;
            PlayerCity.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Arsenal_Enter2;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter2){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Arsenal_Enter3;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter3){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Arsenal_Enter4;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter4){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Arsenal_Enter5;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter5){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Arsenal_Enter6;
        }else if(image == Assets.P1_Chelsea_P2_Arsenal_Enter6){
            somn();
            somn();
            mapType = 3;
            PlayerChelsea.flag = 1;
            PlayerArsenal.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Liverpool_Enter2;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter2){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Liverpool_Enter3;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter3){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Liverpool_Enter4;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter4){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Liverpool_Enter5;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter5){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_Liverpool_Enter6;
        }else if(image == Assets.P1_Chelsea_P2_Liverpool_Enter6){
            somn();
            somn();
            mapType = 3;
            PlayerChelsea.flag = 1;
            PlayerLiverpool.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_ManCity_Enter2;
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter2){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_ManCity_Enter3;
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter3){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_ManCity_Enter4;
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter4){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_ManCity_Enter5;
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter5){
            somn();
            somn();
            image=Assets.P1_Chelsea_P2_ManCity_Enter6;
        }else if(image == Assets.P1_Chelsea_P2_ManCity_Enter6){
            somn();
            somn();
            mapType = 3;
            PlayerChelsea.flag = 1;
            PlayerCity.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Arsenal_Enter2;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter2){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Arsenal_Enter3;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter3){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Arsenal_Enter4;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter4){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Arsenal_Enter5;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter5){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Arsenal_Enter6;
        }else if(image == Assets.P1_Liverpool_P2_Arsenal_Enter6){
            somn();
            somn();
            mapType = 4;
            PlayerLiverpool.flag = 1;
            PlayerArsenal.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Chelsea_Enter2;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter2){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Chelsea_Enter3;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter3){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Chelsea_Enter4;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter4){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Chelsea_Enter5;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter5){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_Chelsea_Enter6;
        }else if(image == Assets.P1_Liverpool_P2_Chelsea_Enter6){
            somn();
            somn();
            mapType = 4;
            PlayerLiverpool.flag = 1;
            PlayerChelsea.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_ManCity_Enter2;
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter2){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_ManCity_Enter3;
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter3){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_ManCity_Enter4;
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter4){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_ManCity_Enter5;
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter5){
            somn();
            somn();
            image=Assets.P1_Liverpool_P2_ManCity_Enter6;
        }else if(image == Assets.P1_Liverpool_P2_ManCity_Enter6){
            somn();
            somn();
            mapType = 4;
            PlayerLiverpool.flag = 1;
            PlayerCity.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Arsenal_Enter2;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter2){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Arsenal_Enter3;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter3){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Arsenal_Enter4;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter4){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Arsenal_Enter5;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter5){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Arsenal_Enter6;
        }else if(image == Assets.P1_ManCity_P2_Arsenal_Enter6){
            somn();
            somn();
            mapType = 1;
            PlayerCity.flag = 1;
            PlayerArsenal.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Chelsea_Enter2;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter2){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Chelsea_Enter3;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter3){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Chelsea_Enter4;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter4){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Chelsea_Enter5;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter5){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Chelsea_Enter6;
        }else if(image == Assets.P1_ManCity_P2_Chelsea_Enter6){
            somn();
            somn();
            mapType = 1;
            PlayerCity.flag = 1;
            PlayerChelsea.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            State.SetState(new PlayState(refLink));
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Liverpool_Enter2;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter2){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Liverpool_Enter3;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter3){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Liverpool_Enter4;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter4){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Liverpool_Enter5;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter5){
            somn();
            somn();
            image=Assets.P1_ManCity_P2_Liverpool_Enter6;
        }else if(image == Assets.P1_ManCity_P2_Liverpool_Enter6){
            somn();
            somn();
            mapType = 1;
            PlayerCity.flag = 1;
            PlayerLiverpool.flag = 2;
            ////////////////////////////////////////////////////////////////////////////////////////////////////
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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
