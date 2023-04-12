package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends SuperObject{
    public OBJ_Coin() {
        name = "Coin";
        try {
            image[0] = ImageIO.read(getClass().getResourceAsStream("/objects/coin01.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/objects/coin02.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/objects/coin03.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/objects/coin04.png"));
            image[4] = ImageIO.read(getClass().getResourceAsStream("/objects/coin05.png"));
            image[5] = ImageIO.read(getClass().getResourceAsStream("/objects/coin06.png"));
            image[6] = ImageIO.read(getClass().getResourceAsStream("/objects/coin07.png"));
            image[7] = ImageIO.read(getClass().getResourceAsStream("/objects/coin08.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
