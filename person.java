public class person {
    private int xcor;
    private int ycor;
    private int dist;

    public person() {
    }
    public person(int xcor,int ycor,int dist) {
        this.xcor = xcor;
        this.ycor = ycor;
        this.dist = 0;
    }
    public person(person p) {
        xcor = p.xcor;
        ycor = p.ycor;
        dist = p.dist;
    }

    public void setXcor(int xcor) {
        this.xcor = xcor;
    }
    public void setYcor(int ycor) {
        this.ycor = ycor;
    }
    public void setDist(int dist) {
        this.dist = dist;
    }
    public int getXcor() {
        return this.xcor;
    }
    public int getYcor() {
        return this.ycor;
    }
    public int getDist() {
        return this.dist;
    }
}