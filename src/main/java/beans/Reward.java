package beans;

/**
 * Created by JoyHwong on 8/23/16.
 * copyright @ 2016 All right reserved.
 * follow me on github https://github.com/JoyHwong
 */
public class Reward {
    private int [] ca = new int [3], cb  = new int [3], ja = new int [3], jb = new int [3];
    private int cat = 0, cbt = 0, jat = 0, jbt = 0;

    public int[] getCa() {
        return ca;
    }

    public void addCa(int index) {
        ca[index]++;
        cat++;
    }

    public int[] getCb() {
        return cb;
    }

    public void addCb(int index) {
        cb[index]++;
        cbt++;
    }

    public int[] getJa() {
        return ja;
    }

    public void addJa(int index) {
        ja[index]++;
        jat++;
    }

    public int[] getJb() {
        return jb;
    }

    public void addJb(int index) {
        jb[index]++;
        jbt++;
    }

    public int getCat() {
        return cat;
    }


    public int getCbt() {
        return cbt;
    }


    public int getJat() {
        return jat;
    }

    public int getJbt() {
        return jbt;
    }
}