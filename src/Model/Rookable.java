package Model;

public interface Rookable extends Siegeable, Movable{

    public void change_form(Rookable rk);
    
    public int skill(Base a, Base b);
}