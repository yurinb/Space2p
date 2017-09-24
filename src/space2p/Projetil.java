package space2p;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Projetil {

    private Image sprite;

    private float x, y;
    private int naveHeigh;
    private int naveWidth;

    private int projetilTypeNum;

    private int damage;

    private float angle;

    private float vel;

    private int lifeTimeExplosion;

    private boolean Visible;
    private boolean running;

    private int playerNum;

    //
    //Inicializacao do projetil
    public Projetil(int x, int y, int naveHeigh, int naveWidth, float angle, int tipoMissil, int playerNum) {
        //posições iniciais
        this.playerNum = playerNum;
        this.x = x;
        this.y = y;
        this.naveHeigh = naveHeigh;
        this.naveWidth = naveWidth;
        //angulo
        this.angle = angle;

        //imagem do missil 1
        if (tipoMissil == 1) {
            this.projetilTypeNum = 1;
            if (playerNum == 1) {
                ImageIcon refImagem = new ImageIcon(getClass().getResource("/space2p/res/Projetil1P1.png"));
                this.sprite = refImagem.getImage();
            }else{
                ImageIcon refImagem = new ImageIcon(getClass().getResource("/space2p/res/Projetil1P2.png"));
                this.sprite = refImagem.getImage();
            }
            //atributos do missil 1
            this.vel = 10;
            this.damage = 10;
            //tempo de vida após colisao (explosao time)(ms)
            this.lifeTimeExplosion = 10000;
        }
        this.running = true;
        this.Visible = true;
    }

    //
    // MOVIMENTO DO MISSIL
    public void move() {
        setX((float) (this.getX() + this.getVel() * Math.cos(Math.toRadians(getAngle()))));
        setY((float) (this.getY() + this.getVel() * Math.sin(Math.toRadians(getAngle()))));
    }

    //
    //
    //
    //
    //
//============================================GETTERS SETTERS====================================================================
    /**
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.setX(x);
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.setY(y);
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * @return the vel
     */
    public float getVel() {
        return vel;
    }

    /**
     * @param vel the vel to set
     */
    public void setVel(int vel) {
        this.vel = vel;
    }

    /**
     * @return the isVisible
     */
    public boolean isIsVisible() {
        return isVisible();
    }

    /**
     * @param isVisible the isVisible to set
     */
    public void setIsVisible(boolean isVisible) {
        this.setVisible(isVisible);
    }

    /**
     * @return the playerNum
     */
    public int getPlayerNum() {
        return playerNum;
    }

    /**
     * @param playerNum the playerNum to set
     */
    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    /**
     * @return the naveHeigh
     */
    public int getNaveHeigh() {
        return naveHeigh;
    }

    /**
     * @param naveHeigh the naveHeigh to set
     */
    public void setNaveHeigh(int naveHeigh) {
        this.naveHeigh = naveHeigh;
    }

    /**
     * @return the naveWidth
     */
    public int getNaveWidth() {
        return naveWidth;
    }

    /**
     * @param naveWidth the naveWidth to set
     */
    public void setNaveWidth(int naveWidth) {
        this.naveWidth = naveWidth;
    }

    /**
     * @return the lifeTimeExplosion
     */
    public int getLifeTimeExplosion() {
        return lifeTimeExplosion;
    }

    /**
     * @param lifeTimeExplosion the lifeTimeExplosion to set
     */
    public void setLifeTimeExplosion(int lifeTimeExplosion) {
        this.lifeTimeExplosion = lifeTimeExplosion;
    }

    /**
     * @return the Visible
     */
    public boolean isVisible() {
        return Visible;
    }

    /**
     * @param Visible the Visible to set
     */
    public void setVisible(boolean Visible) {
        this.Visible = Visible;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the projetilTypeNum
     */
    public int getProjetilTypeNum() {
        return projetilTypeNum;
    }

    /**
     * @param projetilTypeNum the projetilTypeNum to set
     */
    public void setProjetilTypeNum(int projetilTypeNum) {
        this.projetilTypeNum = projetilTypeNum;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

}
