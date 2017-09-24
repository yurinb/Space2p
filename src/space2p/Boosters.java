package space2p;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Boosters {

    int x, y;

    int typeNum;
    
    Image imagem;

    Random r;

    public Boosters(int typeNum) {
        r = new Random();
        
        this.x = r.nextInt(25 + Main.screenSizeX - 50);
        this.y = r.nextInt(25 + Main.screenSizeY - 50);

        this.typeNum = typeNum;

        if (this.typeNum == 1) {//SPEED BOOSTER
            ImageIcon tempImage = new ImageIcon(getClass().getResource("/space2p/res/SpeedBooster.png"));
            this.imagem = tempImage.getImage();
        }

    }

}
