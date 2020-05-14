package entidades;

public class IdManager
{
    private int ultimoId = 0;

    public int getSiguiente()
    {
        this.ultimoId++;
        return this.ultimoId;
    }
}