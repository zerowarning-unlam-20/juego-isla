package tools;

public class IdManager
{
    private static int lastId = 0;

    public static int getNext()
    {
        lastId++;
        return lastId;
    }
    public static void setLastId(int n) {
    	lastId=n;
    }
}