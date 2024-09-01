package FootballGame.Maps;

import FootballGame.RefLinks;

public class MapFactory {
    RefLinks refLink ; /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    /*! \fn public MapFactory(RefLinks refLink)
       \brief Constructorul de initializare al clasei.

       \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    public MapFactory(RefLinks refLink){
        this.refLink = refLink;
    }
    /*! \fn public Map CreateMap(int M)
       \brief Cu ajutorul sablonului factory method se alege harta ce va fi incarcata pentru meci.

       \param type Id-ul hartii ce va fi incarcate.
    */
    public Map CreateMap(int type){
        if(type==1)
            return new ManCityMap(refLink);
        else if(type==2)
            return new ArsenalMap(refLink);
        else if(type==3)
            return new ChelseaMap(refLink);
        else if(type==4)
            return new LiverpoolMap(refLink);
        else
            return null;
    }
}
