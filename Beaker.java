import java.util.ArrayList;

/**
 * Created by Tashi on 7/10/2014.
 */
public class Beaker
{
    public int currentVolume;
    public int maximumVolume;

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
        boolean fillable = false;
        if ( isEMPTY())
        {
            ArrayList<Beaker> beakers2 = new ArrayList<Beaker>();
            beakers2.addAll(beakers);
            beakers2.remove(this);
            for( Beaker b: beakers2)
            {
                if (b.isFULL() == false)
                {
                    fillable = true;
                }
            }
        }
        return fillable;
    }
    public boolean isEMPTYABLE()
    {
        return  currentVolume != 0;
    }

    public boolean isSPLITTABLE()
    {
        return currentVolume % 2 == 0;
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
