package space2p;

import java.util.Random;

public class Estrelas {

    private int x;
    private int y;
    private float b;

    private String estrela;
    private Random r;

    public Estrelas() {
        r = new Random();
        b = r.nextFloat();
        x = r.nextInt(Main.screenSizeX);
        y = r.nextInt(Main.screenSizeY);
        estrela = ".";
    }
//===================GETTER AND SETTER==========================================
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the estrela
     */
    public String getEstrela() {
        return estrela;
    }

    /**
     * @param estrela the estrela to set
     */
    public void setEstrela(String estrela) {
        this.estrela = estrela;
    }

    /**
     * @return the r
     */
    public Random getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(Random r) {
        this.r = r;
    }

    /**
     * @return the b
     */
    public float getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(float b) {
        this.b = b;
    }

}
