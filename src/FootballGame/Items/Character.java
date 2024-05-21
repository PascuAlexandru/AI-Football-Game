package FootballGame.Items;

import FootballGame.RefLinks;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final float DEFAULT_SPEED         = 0.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 48;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 48;   /*!< Inaltimea implicita a imaginii caracterului.*/

    public boolean HasBall;/*!< Fanion ce tine evidenta caracterului ce poseda mingea.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    public byte id_team;     /*!< Retine id-ul specific echipei jucatorului */
    public String behavior;     /*!< Retine comportamentul jucatorului in functie de situatiile din teren. */
    public byte ID;     /*!< Retine id-ul specific fiecarui jucator */
    public float homeRegionX;
    public float homeRegionY;

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor al clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viteza si distantele de deplasare
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move()
    {
            ///Modifica pozitia caracterului pe axa X.
            ///Modifica pozitia caracterului pe axa Y.
        MoveX();
        MoveY();
    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        x += xMove;
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public  float GetSpeed()
    {
        return speed;
    }



    /*! \fn public void SetSpeed(float speed)
        \brief Seteaza viteza cu care se va deplasa caracterul.
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public int GetIdTeam()
        \brief Returneaza id-ul specific echipei din care face parte caracterul.
     */
    public int GetIdTeam() {  return id_team; }/*! \fn public int GetIdTeam()

        \brief Returneaza id-ul specific fiecarui caracter.
     */
    public int GetID() {  return ID; }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}

