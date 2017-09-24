package space2p;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Naves {

    //PLAYER 1 ATRIBUTOS
    private boolean movingP1;
    private boolean turningP1;
    private int hpP1;
    private int xP1, yP1;
    private float dirXp1, dirYp1;
    private float dirAngleP1;
    private float angleP1;
    private Image spriteP1;
    private boolean deadP1;
    private int lifeTimeExplosionP1;

    //PLAYER 2 ATRIBUTOS
    private boolean movingP2;
    private boolean turningP2;
    private int hpP2;
    private int xP2, yP2;
    private float dirXp2, dirYp2;
    private float dirAngleP2;
    private float angleP2;
    private Image spriteP2;
    private boolean deadP2;
    private int lifeTimeExplosionP2;

    //LISTA DE PROJETEIS
    private List<Projetil> projeteis;

    public Naves(int screenSizeX, int screenSizeY) {
        //sprite da nave getClass().getResource("/res/NaveMain.png")))

        //criar nave player 1
        ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/NaveP1.png"));
        spriteP1 = refImage.getImage();

        this.angleP1 = 0;
        this.xP1 = 1;
        this.yP1 = screenSizeY / 2;
        this.hpP1 = 1000;
        this.lifeTimeExplosionP1 = 50000;
        this.turningP1 = true;

        //criar nave player 2
        ImageIcon refImage2 = new ImageIcon(getClass().getResource("/space2p/res/NaveP2.png"));
        spriteP2 = refImage2.getImage();

        this.angleP2 = 180;
        this.xP2 = screenSizeX - 70;
        this.yP2 = screenSizeY / 2;
        this.hpP2 = 1000;
        this.lifeTimeExplosionP2 = 50000;
        this.turningP2 = true;
        
        //
        //
        projeteis = new ArrayList<Projetil>();

    }
//============================== ATTACK ========================================
    public void p1Fire() {
        this.getProjeteis().add(new Projetil(getP1x(), getP1y(), getP1sprite().getHeight(null) / 2, getP1sprite().getWidth(null) / 2, (float) getP1angle(), 1, 1));
    }

    public void p2Fire() {
        this.getProjeteis().add(new Projetil(getP2x(), getP2y(), getP2sprite().getHeight(null) / 2, getP2sprite().getWidth(null) / 2, (float) getP2angle(), 1, 2));
    }
//-------------------------------MOVIMENTOS-------------------------------------
    public void moveTurn() {
        float tempAngle;
        if (isMovingP1()) {
            setP1x((int) (getP1x() + getP1dirX()));
            if ((getxP1() < Main.screenSizeX - spriteP1.getHeight(null) && getxP1() > 0) == false) {
                setP1x((int) (getP1x() - getP1dirX()));
            }

            setP1y((int) (getP1y() + getP1dirY()));
            if ((getyP1() < Main.screenSizeY - spriteP1.getWidth(null) && getyP1() > 0) == false) {
                setP1y((int) (getP1y() - getP1dirY()));
            }
        }
        if (isTurningP1()) {
            //player controla o angulo
            //setAngleP1(getAngleP1() + getDirAngleP1());
            //angulo automatico
            tempAngle = (float) Math.toDegrees(Math.atan2(getyP2() - getyP1(), getxP2() - getxP1()));
            if (tempAngle < 0) {
                tempAngle += 360;
            }
            setAngleP1(tempAngle);
        }
        //--
        if (isMovingP2()) {
            setP2x((int) (getP2x() + getP2dirX()));
            if ((getxP2() < Main.screenSizeX - spriteP2.getHeight(null) && getxP2() > 0) == false) {
                setP2x((int) (getP2x() - getP2dirX()));
            }

            setP2y((int) (getP2y() + getP2dirY()));
            if ((getyP2() < Main.screenSizeY - spriteP2.getWidth(null) && getyP2() > 0) == false) {
                setP2y((int) (getP2y() - getP2dirY()));
            }
        }
        if (isTurningP2()) {
            //player controla o angulo
            //setAngleP2(getAngleP2() + getDirAngleP2());
            //angulo automatico
            tempAngle = (float) Math.toDegrees(Math.atan2(getyP1() - getyP2(), getxP1() - getxP2()));
            if (tempAngle < 0) {
                tempAngle += 360;
            }
            setAngleP2(tempAngle);
        }
    }
//=========================================getters setters=======================================================================
    public int getP1x() {
        return getxP1();
    }

    public int getP1y() {
        return getyP1();
    }

    public int getP2x() {
        return getxP2();
    }

    public int getP2y() {
        return getyP2();
    }

    public Image getP1sprite() {
        return getSpriteP1();
    }

    public Image getP2sprite() {
        return getSpriteP2();
    }

    public void setP1x(int p1x) {
        this.setxP1(p1x);
    }

    public void setP1y(int p1y) {
        this.setyP1(p1y);
    }

    public float getP1dirX() {
        return getDirXp1();
    }

    public void setP1dirX(float p1dirX) {
        this.setDirXp1(p1dirX);
    }

    public float getP1dirY() {
        return getDirYp1();
    }

    public void setP1dirY(float p1dirY) {
        this.setDirYp1(p1dirY);
    }

    public double getP1angle() {
        return getAngleP1();
    }

    public void setP1angle(float p1angle) {
        this.setAngleP1(p1angle);
    }

    public void setP1sprite(Image p1sprite) {
        this.setSpriteP1(p1sprite);
    }

    public void setP2x(int p2x) {
        this.setxP2(p2x);
    }

    public void setP2y(int p2y) {
        this.setyP2(p2y);
    }

    public float getP2dirX() {
        return getDirXp2();
    }

    public void setP2dirX(float p2dirX) {
        this.setDirXp2(p2dirX);
    }

    public float getP2dirY() {
        return getDirYp2();
    }

    public void setP2dirY(float p2dirY) {
        this.setDirYp2(p2dirY);
    }

    public double getP2angle() {
        return getAngleP2();
    }

    public void setP2angle(float p2angle) {
        this.setAngleP2(p2angle);
    }

    public void setP2sprite(Image p2sprite) {
        this.setSpriteP2(p2sprite);
    }

    /**
     * @return the projeteis
     */
    public List<Projetil> getProjeteis() {
        return projeteis;
    }

    /**
     * @param projeteis the projeteis to set
     */
    public void setProjeteis(List<Projetil> projeteis) {
        this.projeteis = projeteis;
    }

    /**
     * @return the xP1
     */
    public int getxP1() {
        return xP1;
    }

    /**
     * @param xP1 the xP1 to set
     */
    public void setxP1(int xP1) {
        this.xP1 = xP1;
    }

    /**
     * @return the yP1
     */
    public int getyP1() {
        return yP1;
    }

    /**
     * @param yP1 the yP1 to set
     */
    public void setyP1(int yP1) {
        this.yP1 = yP1;
    }

    /**
     * @return the dirXp1
     */
    public float getDirXp1() {
        return dirXp1;
    }

    /**
     * @param dirXp1 the dirXp1 to set
     */
    public void setDirXp1(float dirXp1) {
        this.dirXp1 = dirXp1;
    }

    /**
     * @return the dirYp1
     */
    public float getDirYp1() {
        return dirYp1;
    }

    /**
     * @param dirYp1 the dirYp1 to set
     */
    public void setDirYp1(float dirYp1) {
        this.dirYp1 = dirYp1;
    }

    /**
     * @return the angleP1
     */
    public double getAngleP1() {
        return angleP1;
    }

    /**
     * @param angleP1 the angleP1 to set
     */
    public void setAngleP1(float angleP1) {
        this.angleP1 = angleP1;
    }

    /**
     * @return the spriteP1
     */
    public Image getSpriteP1() {
        return spriteP1;
    }

    /**
     * @param spriteP1 the spriteP1 to set
     */
    public void setSpriteP1(Image spriteP1) {
        this.spriteP1 = spriteP1;
    }

    /**
     * @return the xP2
     */
    public int getxP2() {
        return xP2;
    }

    /**
     * @param xP2 the xP2 to set
     */
    public void setxP2(int xP2) {
        this.xP2 = xP2;
    }

    /**
     * @return the yP2
     */
    public int getyP2() {
        return yP2;
    }

    /**
     * @param yP2 the yP2 to set
     */
    public void setyP2(int yP2) {
        this.yP2 = yP2;
    }

    /**
     * @return the dirXp2
     */
    public float getDirXp2() {
        return dirXp2;
    }

    /**
     * @param dirXp2 the dirXp2 to set
     */
    public void setDirXp2(float dirXp2) {
        this.dirXp2 = dirXp2;
    }

    /**
     * @return the dirYp2
     */
    public float getDirYp2() {
        return dirYp2;
    }

    /**
     * @param dirYp2 the dirYp2 to set
     */
    public void setDirYp2(float dirYp2) {
        this.dirYp2 = dirYp2;
    }

    /**
     * @return the angleP2
     */
    public double getAngleP2() {
        return angleP2;
    }

    /**
     * @param angleP2 the angleP2 to set
     */
    public void setAngleP2(float angleP2) {
        this.angleP2 = angleP2;
    }

    /**
     * @return the spriteP2
     */
    public Image getSpriteP2() {
        return spriteP2;
    }

    /**
     * @param spriteP2 the spriteP2 to set
     */
    public void setSpriteP2(Image spriteP2) {
        this.spriteP2 = spriteP2;
    }

    /**
     * @return the dirAngle
     */
    public float getDirAngle() {
        return getDirAngleP1();
    }

    /**
     * @param dirAngle the dirAngle to set
     */
    public void setDirAngle(float dirAngle) {
        this.setDirAngleP1(dirAngle);
    }

    /**
     * @return the turning
     */
    public boolean isTurningP1() {
        return turningP1;
    }

    /**
     * @param turning the turning to set
     */
    public void setTurningP1(boolean turning) {
        this.turningP1 = turning;
    }

    /**
     * @return the turningP2
     */
    public boolean isTurningP2() {
        return turningP2;
    }

    /**
     * @param turningP2 the turningP2 to set
     */
    public void setTurningP2(boolean turningP2) {
        this.turningP2 = turningP2;
    }

    /**
     * @return the dirAngleP1
     */
    public float getDirAngleP1() {
        return dirAngleP1;
    }

    /**
     * @param dirAngleP1 the dirAngleP1 to set
     */
    public void setDirAngleP1(float dirAngleP1) {
        this.dirAngleP1 = dirAngleP1;
    }

    /**
     * @return the dirAngleP2
     */
    public float getDirAngleP2() {
        return dirAngleP2;
    }

    /**
     * @param dirAngleP2 the dirAngleP2 to set
     */
    public void setDirAngleP2(float dirAngleP2) {
        this.dirAngleP2 = dirAngleP2;
    }

    /**
     * @return the movingP1
     */
    public boolean isMovingP1() {
        return movingP1;
    }

    /**
     * @param movingP1 the movingP1 to set
     */
    public void setMovingP1(boolean movingP1) {
        this.movingP1 = movingP1;
    }

    /**
     * @return the movingP2
     */
    public boolean isMovingP2() {
        return movingP2;
    }

    /**
     * @param movingP2 the movingP2 to set
     */
    public void setMovingP2(boolean movingP2) {
        this.movingP2 = movingP2;
    }

    /**
     * @return the hpP1
     */
    public int getHpP1() {
        return hpP1;
    }

    /**
     * @param hpP1 the hpP1 to set
     */
    public void setHpP1(int hpP1) {
        this.hpP1 = hpP1;
    }

    /**
     * @return the hpP2
     */
    public int getHpP2() {
        return hpP2;
    }

    /**
     * @param hpP2 the hpP2 to set
     */
    public void setHpP2(int hpP2) {
        this.hpP2 = hpP2;
    }

    /**
     * @return the deadP1
     */
    public boolean isDeadP1() {
        return deadP1;
    }

    /**
     * @param deadP1 the deadP1 to set
     */
    public void setDeadP1(boolean deadP1) {
        this.deadP1 = deadP1;
    }

    /**
     * @return the deadP2
     */
    public boolean isDeadP2() {
        return deadP2;
    }

    /**
     * @param deadP2 the deadP2 to set
     */
    public void setDeadP2(boolean deadP2) {
        this.deadP2 = deadP2;
    }

    /**
     * @return the lifeTimeExplosionP1
     */
    public int getLifeTimeExplosionP1() {
        return lifeTimeExplosionP1;
    }

    /**
     * @param lifeTimeExplosionP1 the lifeTimeExplosionP1 to set
     */
    public void setLifeTimeExplosionP1(int lifeTimeExplosionP1) {
        this.lifeTimeExplosionP1 = lifeTimeExplosionP1;
    }

    /**
     * @return the lifeTimeExplosionP2
     */
    public int getLifeTimeExplosionP2() {
        return lifeTimeExplosionP2;
    }

    /**
     * @param lifeTimeExplosionP2 the lifeTimeExplosionP2 to set
     */
    public void setLifeTimeExplosionP2(int lifeTimeExplosionP2) {
        this.lifeTimeExplosionP2 = lifeTimeExplosionP2;
    }

}
