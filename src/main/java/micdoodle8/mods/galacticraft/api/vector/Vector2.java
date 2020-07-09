package micdoodle8.mods.galacticraft.api.vector;

/**
 * Vector2 Class is used for defining objects in a 2D space.
 *
 * @author Calclavia
 */

public class Vector2 implements Cloneable
{
    public float x;
    public float y;

    public Vector2()
    {
        this(0, 0);
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the integer floor value.
     *
     * @return
     */
    public int intX()
    {
        return (int) Math.floor(this.x);
    }

    public int intY()
    {
        return (int) Math.floor(this.y);
    }

    /**
     * Makes a new copy of this Vector. Prevents variable referencing problems.
     */
    @Override
    public final Vector2 clone()
    {
        return new Vector2(this.x, this.y);
    }

    public static float distance(Vector2 point1, Vector2 point2)
    {
        float xDifference = point1.x - point2.x;
        float yDiference = point1.y - point2.y;
        return (float) Math.sqrt(xDifference * xDifference + yDiference * yDiference);
    }

    public static float slope(Vector2 point1, Vector2 point2)
    {
        float xDifference = point1.x - point2.x;
        float yDiference = point1.y - point2.y;
        return yDiference / xDifference;
    }

    public float distanceTo(Vector2 target)
    {
        float xDifference = this.x - target.x;
        float yDifference = this.y - target.y;
        return (float) Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    public Vector2 add(Vector2 par1)
    {
        this.x += par1.x;
        this.y += par1.y;
        return this;
    }

    public Vector2 add(float par1)
    {
        this.x += par1;
        this.y += par1;
        return this;
    }

    public Vector2 invert()
    {
        this.multiply(-1);
        return this;
    }

    public Vector2 multiply(float amount)
    {
        this.x *= amount;
        this.y *= amount;
        return this;
    }

    public Vector2 round()
    {
        return new Vector2(Math.round(this.x), Math.round(this.y));
    }

    public Vector2 ceil()
    {
        return new Vector2((float) Math.ceil(this.x), (float) Math.ceil(this.y));
    }

    public Vector2 floor()
    {
        return new Vector2((float) Math.floor(this.x), (float) Math.floor(this.y));
    }

    @Override
    public int hashCode()
    {
        return ("X:" + this.x + "Y:" + this.y).hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Vector2)
        {
            Vector2 vector = (Vector2) o;
            return this.x == vector.x && this.y == vector.y;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return "Vector2 [" + this.x + "," + this.y + "]";
    }
}