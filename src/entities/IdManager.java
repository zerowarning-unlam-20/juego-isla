package entities;

public class IdManager
{
    private int lastId = 0;

    public int getNext()
    {
        this.lastId++;
        return this.lastId;
    }
}