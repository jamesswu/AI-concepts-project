

public class building {

    private final int[][] base;
    private String hashKey;
    private int g;


    public static int GRIDSIZE = 25;

    public building() {
        this.base = new int[GRIDSIZE][GRIDSIZE];
        for (int i = 0; i < GRIDSIZE;i++) {
            for (int j = 0; j < GRIDSIZE;j++) {
                this.base[i][j] = 0;
            }
        }
        for (int i = 1; i < GRIDSIZE-1; i++) {
            this.base[0][i] = -1;
            this.base[i][0] = -1;
            
            this.base[GRIDSIZE-1][i] = -1;
            this.base[i][GRIDSIZE-1] = -1;
        }
        
        this.base[0][24] = -1;
        this.base[24][0] = -1;
        this.base[24][24] = -1;
        this.base[12][24] = -2;
        for (int i = 1; i < 11;i++){
            this.base[i][12] = -1;
        }
        for (int i = 0; i < 11;i++) {
            for (int j = 0;j < 5;j++) {
                this.base[i][j] = -1;
            }
        }
        for (int i = 6;i < 11;i++) {
            for (int j = 7; j < 12;j++) {
                this.base[i][j] = -1;
            }
        }
        for (int i = 14;i < 23;i++) {
            this.base[10][i] = -1;
        }
        for (int i = 18;i < 24;i++) {
            this.base[14][i] = -1;
        }
        for (int i = 14; i < 17;i++){
            this.base[14][i] = -1;
        }
        for (int i = 10; i < 13;i++) {
            this.base[14][i] = -1;
        }
        for (int i = 3; i < 6; i++) {
            this.base[14][i] = -1;
        }
        for (int i = 14; i < 24; i++) {
            this.base[i][7] = -1;
        }
        for (int i = 15; i < 24; i++) {
            this.base[i][3] = -1;
        }
        for (int i = 16; i < 24; i++) {
            this.base[i][14] = -1;
        }

        this.sethashKey(this);
        this.setg(0);
    }
    public building(building b) {

        base = b.base;
        hashKey = b.hashKey;
        g = b.g;
    }


    public void sethashKey(building b) {
        // convert type to string
        String str = "";
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                str = str + Integer.toString(b.base[i][j]);
            }
        }
        this.hashKey = str;
    }

    public int[][] getbase() {
        return base;
    }
    public String gethashKey() {
        return this.hashKey;
    }
    public void setg(int g) {
        this.g = g;
    }
    public int getg() {
        return this.g;
    }
    public void setbase(int i, int j, int type) {
        this.base[i][j] = type;
    }
    public void setBuilding(int oi, int oj, int i, int j, int otype, int type) {
        this.base[oi][oj] = otype;
        this.base[i][j] = type;
        this.sethashKey(this);
    }
    
    public void printBuilding() {
        for (int i = 0; i < GRIDSIZE;i++) {
            // System.out.printf("%2d",i);
            // System.out.print(" ");
            for (int j = 0; j < GRIDSIZE;j++) {
                System.out.printf("%2d",this.getbase()[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}