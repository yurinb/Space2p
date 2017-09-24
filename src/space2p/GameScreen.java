package space2p;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import static space2p.Main.screenSizeX;
import static space2p.Main.screenSizeY;

public class GameScreen extends JPanel implements KeyListener, ActionListener {

    Timer timer;
    int delayUpdate = 5;
    int boostSpawnDelay;
    boolean boostOnScreen;
    Boosters boost;
    Naves naves;
    HPbar hpBar;
    private float speedBoostP1;
    private float speedBoostP2;

    private List<Estrelas> estrelas;

//CONSTRUTOR
    public GameScreen(Naves naves) {

        setDoubleBuffered(true);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);

        this.naves = naves;

        timer = new Timer(delayUpdate, this);
        timer.start();

        // NEW ESTRELAS ********************
        estrelas = new ArrayList();
        for (int i = 0; i < 100; i++) {
            estrelas.add(new Estrelas());
        }
        //HP BAR
        hpBar = new HPbar();
    }

    public void paint(Graphics g) {
        //FUNDO
        Graphics2D fundo = (Graphics2D) g;
        fundo.setColor(Color.black);
        fundo.fillRect(0, 0, screenSizeX, screenSizeY);
        //ESTRELAS PRINTA E MOVE
        for (int i = 0; i < estrelas.size(); i++) {
            fundo.setColor(Color.getHSBColor(estrelas.get(i).getB(), 1.0f, estrelas.get(i).getB()));
            fundo.drawString(estrelas.get(i).getEstrela(), estrelas.get(i).getX(), estrelas.get(i).getY());
            estrelas.get(i).setX(estrelas.get(i).getX() + 1);
            if (estrelas.get(i).getX() > Main.screenSizeX) {
                estrelas.get(i).setX(0);
            }
        }

        //NAVES
        AffineTransform naveP1t = new AffineTransform();
        naveP1t.translate(naves.getP1x(), naves.getP1y());
        naveP1t.rotate(Math.toRadians(naves.getP1angle()), naves.getP1sprite().getWidth(this) / 2, naves.getP1sprite().getHeight(this) / 2);
        fundo.drawImage(naves.getP1sprite(), naveP1t, this);

        AffineTransform naveP2t = new AffineTransform();
        naveP2t.translate(naves.getP2x(), naves.getP2y());
        naveP2t.rotate(Math.toRadians(naves.getP2angle()), naves.getP2sprite().getWidth(this) / 2, naves.getP2sprite().getHeight(this) / 2);
        fundo.drawImage(naves.getP2sprite(), naveP2t, this);

        //Projeteis
        List<Projetil> projeteis = naves.getProjeteis();
        for (int i = 0; i < projeteis.size(); i++) {
            Projetil p = (Projetil) projeteis.get(i);

            AffineTransform projTrans = new AffineTransform();
            if (p.isIsVisible() == true && p.getPlayerNum() == 1) {
                projTrans.translate(p.getX() + p.getNaveHeigh(), p.getY() + p.getNaveWidth());
                projTrans.rotate(Math.toRadians(p.getAngle()), p.getSprite().getWidth(this) / 2, p.getSprite().getHeight(this) / 2);
                fundo.drawImage(p.getSprite(), projTrans, this);

            } else if (p.isIsVisible() == true && p.getPlayerNum() == 2) {
                projTrans.translate(p.getX() + p.getNaveHeigh(), p.getY() + p.getNaveWidth());
                projTrans.rotate(Math.toRadians(p.getAngle()), p.getSprite().getWidth(this) / 2, p.getSprite().getHeight(this) / 2);
                fundo.drawImage(p.getSprite(), projTrans, this);

            }

        }

        // INTERFACE
        //HP BAR
        fundo.setColor(Color.blue);
        fundo.drawString(hpBar.getHpBarP1(), 0, Main.screenSizeY - 50);
        fundo.setColor(Color.red);
        fundo.drawString(hpBar.getHpBarP2(), Main.screenSizeX - 305, Main.screenSizeY - 50);
        //BOOSTERS
        if (boostOnScreen) {
            fundo.drawImage(boost.imagem, boost.x, boost.y, null);
        } else {
            boostSpawnDelay += 5;
            if (boostSpawnDelay >= 5000) {
                boost = new Boosters(1);
                boostOnScreen = true;
                boostSpawnDelay = 0;
            }
        }

        //WEAPONS
        //BOTOES
        ImageIcon refImageESC = new ImageIcon(getClass().getResource("/space2p/res/Esc.png"));
        fundo.drawImage(refImageESC.getImage(), 5, 3, this);

        fundo.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) { // AÇÃO QUE O TIMER EXECUTA QUANDO O TEMPO ACABA;
        timer.start();
        //MOVE NAVES
//*************************************************************************************************************        
        naves.moveTurn();
//*************************************************************************************************************        
        //MOVE PROJETILS
//*************************************************************************************************************        
        List<Projetil> proj = naves.getProjeteis();
        for (int i = 0; i < proj.size(); i++) {
            Projetil p = proj.get(i);
            if (p.isRunning()) {
                p.move();
            } else {//remove (executa 1 vez por projetil)
                //proj.remove(i);
                if (p.getProjetilTypeNum() == 1 && p.getPlayerNum() == 1) {
                    ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/explosao1P1.png"));
                    p.setSprite(refImage.getImage());
                }
                if (p.getProjetilTypeNum() == 1 && p.getPlayerNum() == 2) {
                    ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/explosao1P2.png"));
                    p.setSprite(refImage.getImage());
                }
            }
        }
//*************************************************************************************************************        
        //--===========----------------------COLISION---------------------------------------------------------C O L I S I O N
//*************************************************************************************************************        
        Point tempPointProj = new Point();//projetil ponto/local
        Point tempPointNaveP1 = new Point();//nave player1 ponto/local
        tempPointNaveP1.setLocation(naves.getxP1(), naves.getyP1());
        Point tempPointNaveP2 = new Point();//nave player2 ponto/local
        tempPointNaveP2.setLocation(naves.getxP2(), naves.getyP2());
        //if distancia for menor ou igual a soma dos "raios" dos objetos, entao = colisao
        for (int i = 0; i < proj.size(); i++) {
            Projetil p = proj.get(i);
            //Posicao do projetil(i)
            tempPointProj.setLocation(proj.get(i).getX(), proj.get(i).getY());

            //NAVE PLAYER 1 ATINGIDA
            if (p.getPlayerNum() == 2 && tempPointProj.distance(tempPointNaveP1) <= (naves.getSpriteP1().getWidth(null) / 2) + (p.getSprite().getWidth(null) / 2)) {
                if (p.isRunning()) {
                    naves.setHpP1(naves.getHpP1() - p.getDamage());
                    if (naves.getHpP1() <= 0) {
                        hpBar.updateHp(naves.getHpP1(), naves.getHpP2());
                        ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/explosaoG1P1.png"));
                        naves.setSpriteP1(refImage.getImage());
                        naves.setDeadP1(true);
                    } else {
                        hpBar.updateHp(naves.getHpP1(), naves.getHpP2());
                    }
                }
                p.setRunning(false);
            }
            //NAVE PLAYER 2 ATINGIDA
            if (p.getPlayerNum() == 1 && tempPointProj.distance(tempPointNaveP2) <= (naves.getSpriteP2().getWidth(null) / 2) + (p.getSprite().getWidth(null) / 2)) {
                if (p.isRunning()) {
                    hpBar.updateHp(naves.getHpP1(), naves.getHpP2());
                    naves.setHpP2(naves.getHpP2() - p.getDamage());
                    if (naves.getHpP2() <= 0) {
                        ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/explosaoG1P2.png"));
                        naves.setSpriteP2(refImage.getImage());
                        naves.setDeadP2(true);
                    } else {
                        hpBar.updateHp(naves.getHpP1(), naves.getHpP2());
                    }
                }
                p.setRunning(false);
            }
            //BOOSTER COLISION
            //P1
            if (boostOnScreen == true) {
                Point boostTempPoint = new Point(boost.x, boost.y);//boost ponto/local
                if (tempPointNaveP1.distance(boostTempPoint) <= (naves.getSpriteP1().getWidth(null) / 2) + (boost.imagem.getWidth(null)) / 2) {
                    speedBoostP1 += 0.25;
                    boostOnScreen = false;
                }
                if (tempPointNaveP2.distance(boostTempPoint) <= (naves.getSpriteP2().getWidth(null) / 2) + (boost.imagem.getWidth(null)) / 2) {
                    speedBoostP2 += 0.25;
                    boostOnScreen = false;
                }
            }
//*************************************************************************************************************        
//------PROJETIL REMOVE (LIFE ENDS)
//*************************************************************************************************************        
            if (p.isRunning() == false) {
                p.setLifeTimeExplosion(p.getLifeTimeExplosion() - 250);
                if (p.getLifeTimeExplosion() <= 0) {
                    p.setIsVisible(false);
                    proj.remove(p);
                }
            }
//*************************************************************************************************************        
//------P1 DIES
//*************************************************************************************************************        
            if (naves.isDeadP1()) {
                naves.setLifeTimeExplosionP1(naves.getLifeTimeExplosionP1() - 250);
                if (naves.getLifeTimeExplosionP1() <= 0) {
                    naves.setxP1(25);
                    naves.setyP1(screenSizeY - 300);
                    naves.setAngleP1(0);
                    naves.setTurningP1(false);
                    ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/wasted.png"));
                    naves.setSpriteP1(refImage.getImage());
                    speedBoostP1 = -2;
                }
            }
//------P2 DIES
            if (naves.isDeadP2()) {
                naves.setLifeTimeExplosionP2(naves.getLifeTimeExplosionP2() - 250);
                if (naves.getLifeTimeExplosionP2() <= 0) {
                    naves.setxP2(Main.screenSizeX - 300);
                    naves.setyP2(Main.screenSizeY - 300);
                    naves.setAngleP2(0);
                    naves.setTurningP2(false);
                    ImageIcon refImage = new ImageIcon(getClass().getResource("/space2p/res/wasted.png"));
                    naves.setSpriteP2(refImage.getImage());
                    speedBoostP2 = -2;
                }
            }

        }
//*************************************************************************************************************        
//----------------------------REPAINT  *************************        
//*************************************************************************************************************        
        repaint();
//*************************************************************************************************************        
//-----------------------------------------------------------------------------------------------------------------
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
//======================= TECLADO ==============================================

    @Override
    public void keyPressed(KeyEvent e) {
        int teclaCod = e.getKeyCode();
        //------------------------------------P1 TECLAS

        if (teclaCod == KeyEvent.VK_W) {//UP
            naves.setMovingP1(true);
            naves.setP1dirY(-3 + -speedBoostP1);
        }
        if (teclaCod == KeyEvent.VK_S) {//DOWN
            naves.setMovingP1(true);
            naves.setP1dirY(+3 + speedBoostP1);
        }
        if (teclaCod == KeyEvent.VK_A) {//LEFT
            naves.setMovingP1(true);
            naves.setP1dirX(-3 + -speedBoostP1);
        }
        if (teclaCod == KeyEvent.VK_D) {//RIGHT
            naves.setMovingP1(true);
            naves.setP1dirX(+3 + speedBoostP1);
        }

//        if (teclaCod == KeyEvent.VK_I) {//PARAR
//            naves.setTurningP1(false);
//        }
//        //girar angulo/nave
//        if (teclaCod == KeyEvent.VK_L) {//GIRAR HORARIO
//            if (naves.isTurningP1()) {
//                naves.setDirAngleP1(1);
//            } else {
//                naves.setTurningP1(false);
//            }
//            naves.setTurningP1(true);
//        }
//        if (teclaCod == KeyEvent.VK_J) {//GIRAR ANT-HORARIO
//            if (naves.isTurningP1()) {
//                naves.setDirAngleP1(-1);
//            } else {
//                naves.setTurningP1(false);
//            }
//            naves.setTurningP1(true);
//        }
        //------------------------------------P1 FIRE
        if (teclaCod == KeyEvent.VK_K) {//RIGHT
            if (naves.isDeadP1() == false) {
                naves.p1Fire();
            }
        }
        //
        //------------------------------------P2 TECLAS

        if (teclaCod == KeyEvent.VK_UP) {//UP
            naves.setMovingP2(true);
            naves.setP2dirY(-3 + -speedBoostP2);
        }
        if (teclaCod == KeyEvent.VK_DOWN) {//DOWN
            naves.setMovingP2(true);
            naves.setP2dirY(+3 + speedBoostP2);
        }
        if (teclaCod == KeyEvent.VK_LEFT) {//LEFT
            naves.setMovingP2(true);
            naves.setP2dirX(-3 + -speedBoostP2);
        }
        if (teclaCod == KeyEvent.VK_RIGHT) {//RIGHT
            naves.setMovingP2(true);
            naves.setP2dirX(+3 + speedBoostP2);
        }

//        if (teclaCod == KeyEvent.VK_I) {//PARAR
//            naves.setTurningP2(false);
//        }
//        //girar angulo/nave
//        if (teclaCod == KeyEvent.VK_NUMPAD6) {//GIRAR HORARIO
//            if (naves.isTurningP2()) {
//                naves.setDirAngleP2(1);
//
//            } else {
//                naves.setTurningP1(false);
//            }
//            naves.setTurningP2(true);
//        }
//        if (teclaCod == KeyEvent.VK_NUMPAD4) {//GIRAR ANT-HORARIO
//            if (naves.isTurningP2()) {
//                naves.setDirAngleP2(-1);
//            } else {
//                naves.setTurningP2(false);
//            }
//            naves.setTurningP2(true);
//        }
//        //------------------------------------P2 FIRE
        if (teclaCod == KeyEvent.VK_NUMPAD5) {//RIGHT
            if (naves.isDeadP2() == false) {
                naves.p2Fire();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int teclaCod = e.getKeyCode();
        //EXIT GAME
        if (teclaCod == KeyEvent.VK_ESCAPE) {// FECHAR JOGO ---------------------------------------------------- ENDS
            System.exit(0);
        }
//--------------------------PARA NAVE QUANDO TECLA FOR LARGADA------------------
//        //------------------------------------P1 TECLAS
//        if (teclaCod == KeyEvent.VK_W) {//UP
//            naves.setP1dirY(0);
//        }
//        if (teclaCod == KeyEvent.VK_S) {//DOWN
//            naves.setP1dirY(0);
//        }
//        if (teclaCod == KeyEvent.VK_A) {//LEFT
//            naves.setP1dirX(0);
//        }
//        if (teclaCod == KeyEvent.VK_D) {//RIGHT
//            naves.setP1dirX(0);
//        }
//        //------------------------------------P2 TECLAS
//        if (teclaCod == KeyEvent.VK_UP) {//UP
//            naves.setP2dirY(0);
//        }
//        if (teclaCod == KeyEvent.VK_DOWN) {//DOWN
//            naves.setP2dirY(0);
//        }
//        if (teclaCod == KeyEvent.VK_LEFT) {//LEFT
//            naves.setP2dirX(0);
//        }
//        if (teclaCod == KeyEvent.VK_RIGHT) {//RIGHT
//            naves.setP2dirX(0);
//        }

    }

}
