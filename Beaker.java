import java.util.ArrayList;

/**
 * Created by Tashi on 7/10/2014.
 */
public class Beaker
{
    public int currentVolume;
    public int maximumVolume;
    public int precmd = -1;
    public Beaker(int paraMaximumVolume)
    {
        maximumVolume = paraMaximumVolume;
        currentVolume = 0;
    }

    public Beaker( Beaker b)
    {
        maximumVolume = b.maximumVolume;
        currentVolume = b.currentVolume;
    }

    public String toString()
    {
        return "" + maximumVolume + "mL " + "beaker ";
    }

    public boolean isFILLABLE(ArrayList<Beaker> beakers)
    {
        boolean fillable = isEMPTY();
        if ( fillable)
        {
            ArrayList<Beaker> beakers2 = new ArrayList<Beaker>();
            beakers2.addAll(beakers);
            beakers2.remove(this);
            if ( isALLFULL(beakers2))
            {
                fillable = false;
            }

        }
        return fillable;
    }
    public boolean isEMPTYABLE(ArrayList<Beaker> beakers)
    {
        boolean emptyable = currentVolume != 0 && precmd != Path.FILL;
        if(emptyable)
        {
            ArrayList<Beaker> beakers2 = new ArrayList<Beaker>();
            beakers2.addAll(beakers);
            beakers2.remove(this);
            if( isALLEMPTY(beakers2))
            {
                emptyable = false;
            }
        }
        return emptyable;
    }

    public boolean isSPLITTABLE()
    {
        return currentVolume % 2 == 0;
    }
    public static boolean isALLFULL(ArrayList<Beaker> beakers)
    {
        boolean result = true;
        for (Beaker b: beakers)
        {
            if (!b.isFULL())
            {
                result = false;
            }
        }
        return result;
    }

    public static boolean isALLEMPTY(ArrayList<Beaker> beakers)
    {
        boolean result = true;
        for (Beaker b: beakers)
        {
            if(!b.isEMPTY())
            {
                result = false;
            }
        }
        return result;
    }

    public boolean isFULL()
    {
        return currentVolume == maximumVolume;
    }
    public boolean isEMPTY()
    {
        return currentVolume == 0;
    }
    public boolean isPOURABLE(Beaker other)
    {
        boolean pourable = true;
        if((isEMPTY()) ||(other.isFULL()))
        {
            pourable = false;
        }
        return pourable;
    }

    public void pourInto( Beaker other)
    {
        int aval = other.maximumVolume - other.currentVolume;
        int amount = (aval < currentVolume) ? aval:currentVolume;
        currentVolume -=amount;
        other.currentVolume +=amount;
    }
}
