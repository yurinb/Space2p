package space2p;

public class HPbar {

    private String hpBarP1;
    private String hpBarP2;

    public HPbar() {
        hpBarP1 = "[";
        hpBarP2 = "[";
        for (int i = 1; i <= 100; i++) {
            hpBarP1 += "|";
            hpBarP2 += "|";
        }

    }

    public void updateHp(int hpP1, int hpP2) {
        hpBarP1 = "[";
        hpBarP2 = "[";
        //P1
        for (int i = 1; i <= hpP1 / 10; i++) {
            hpBarP1 = hpBarP1 + "|";
        }
        //P2
        for (int i = 1; i <= hpP2 / 10; i++) {
            hpBarP2 = hpBarP2 + "|";
        }
    }

    /**
     * @return the hpBarP1
     */
    public String getHpBarP1() {
        return hpBarP1;
    }

    /**
     * @param hpBarP1 the hpBarP1 to set
     */
    public void setHpBarP1(String hpBarP1) {
        this.hpBarP1 = hpBarP1;
    }

    /**
     * @return the hpBarP2
     */
    public String getHpBarP2() {
        return hpBarP2;
    }

    /**
     * @param hpBarP2 the hpBarP2 to set
     */
    public void setHpBarP2(String hpBarP2) {
        this.hpBarP2 = hpBarP2;
    }

}
