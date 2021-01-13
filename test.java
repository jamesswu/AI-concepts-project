
import java.util.*;

public class test {

    public static int GRIDSIZE = 25;


    public static int hammingD(building cur, building goal) {
        int hammingD = 0;
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                if (goal.getbase()[i][j] != cur.getbase()[i][j]) hammingD += 1;
            }
        }
        return hammingD;
    }

    // public static manhattenD()
    public static void AstarSearch(building test,building goal) {
        int exiti = 0;
        int exitj = 0;
        int people = 0;
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                people++;
            }
        }
        int count = 0;
        person[] path = new person[people];
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                if (test.getbase()[i][j] == -2) {
                    if (i == 0) {
                        exiti = 1;
                        exitj = j;
                    }
                    if (j == 0) {
                        exiti = i;
                        exitj = 1; }
                    if (i == 14) {
                        exiti = 13;
                        exitj = j; }
                    if (j == 14) {
                        exiti = i;
                        exitj = 13; }
                }
                if (test.getbase()[i][j] == 1) {
                    path[count] = new person(i, j,0);
                    path[count].setDist(( Math.abs( (int) (exiti-path[count].getXcor() ) )
                                        +(Math.abs( (int) (exitj-path[count].getYcor() ) )) ));
                    count++;
                }
            }
        }
        insertionSort(path, count);

        System.out.println("path[0].x="+path[0].getXcor());
        System.out.println("path[0].y="+path[0].getYcor());

        Vector<building> frontier = new Vector<building>();
        Set<String> explored = new HashSet<String>();
        frontier.add(test);
        explored.add(test.gethashKey());
        while (!frontier.isEmpty()) {
            Iterator<building> it = frontier.iterator();
            building i = it.next(); // first value of frontier
            int bestH = i.getg() + hammingD(i,goal);
            for (int a = 0; a < frontier.size(); a++) {
                building k = frontier.get(a); // first value of j
                int currentH = k.getg() + hammingD(k, goal);
                if (currentH <= bestH) {
                    bestH = currentH;
                    i = k;
                }
            }
            building best = i;
            best.sethashKey(best);
            frontier.remove(i);
            best.printBuilding();
            Scanner in = new Scanner(System.in);
            in.nextLine();
            //best.printBuilding();
            if (best.gethashKey().equals(goal.gethashKey())) {
                best.printBuilding();
                in.close();
                return;
            }
            Vector<building> possibleStates = new Vector<building>();
            for (int c = 0; c < count; c++) {
                trySearching(possibleStates,best,path[c],
                            path[c].getXcor(),path[c].getYcor(),path[c].getXcor()-1,path[c].getYcor());
                trySearching(possibleStates,best,path[c],
                            path[c].getXcor(),path[c].getYcor(),path[c].getXcor()+1,path[c].getYcor());
                trySearching(possibleStates,best,path[c],
                            path[c].getXcor(),path[c].getYcor(),path[c].getXcor(),path[c].getYcor()-1);
                trySearching(possibleStates,best,path[c],
                             path[c].getXcor(),path[c].getYcor(),path[c].getXcor(),path[c].getYcor()+1);

                // for (int d = 0; d < possibleStates.size();d++) {
                //     possibleStates.get(d).printBuilding();
                // }

                
                for (int a = 0; a < possibleStates.size(); a++) {
                    building pbuilding = new building(possibleStates.get(a));
                    pbuilding.sethashKey(pbuilding);
                    pbuilding.setg(pbuilding.getg()+1);
                    if (!explored.contains(pbuilding.gethashKey())) {
                        frontier.add(pbuilding);
                        explored.add(pbuilding.gethashKey());
                    }
                }
            }
        }
    }

    static void trySearching(Vector<building> pstates, building b,person p, int oi, int oj, int i, int j) {
        building temp = new building();
        System.out.println(p.getXcor()+","+p.getYcor());
        for (int x = 0; x < GRIDSIZE; x++) {
            for (int y = 0; y < GRIDSIZE; y++) {
                temp.getbase()[x][y] = b.getbase()[x][y];
            }
        }
        if (b.getbase()[i][j] != -1) {
            temp.getbase()[oi][oj] = 0;
            temp.getbase()[i][j] = 1;
            p.setXcor(i);
            p.setYcor(j);
            temp.sethashKey(temp);
            temp.setg(temp.getg()+1);
            pstates.add(temp);
        }
        
        // if (b.getbase()[i][j] == -2) {
        //     temp.getbase()[oi][oj] = 0;
        //     temp.getbase()[i][j] = -2;
        //     p.setXcor(i);
        //     p.setYcor(j);
        //     temp.sethashKey(temp);
        //     temp.setg(temp.getg()+1);
        //     pstates.add(temp);
        // }
    }

    static void insertionSort(person[] unsorted_array, int n) {
        int i,j,temp;
        i = 1;
        while (i < n) {
            temp = unsorted_array[i].getDist();
            j = i;
            while (j > 0 && unsorted_array[j-1].getDist() > temp) {
                unsorted_array[j] = unsorted_array[j-1];
                j = j-1;
            }
            unsorted_array[j] = unsorted_array[i];
            i = i+1;
        }
        return;

    }


    public static void main(String args[]) {
        building test = new building();
        building goal = new building();
        goal.getbase()[12][23] = 1;
        //goal.printBuilding();

        test.getbase()[23][2] = 1;
        // test.getbase()[9][13] = 1;
        test.printBuilding();
        System.out.println("initial");
        test.sethashKey(test);

        AstarSearch(test,goal);
        
        System.exit(0);
    }


    static void bfs(building test, building goal) {
        Queue<building> frontier = new LinkedList<building>();
        Set<String> explored = new HashSet<String>();

        frontier.add(test);
        explored.add(test.gethashKey());
    }

} 




        // Random r = new Random();
        // int n = r.nextInt(10) +1;

        // for (int i = 1; i < GRIDSIZE-2; i++) {
        //     for (int j = 1; j < GRIDSIZE-2;j++) {
        //         n = r.nextInt(10)+1;
        //         if (n > 7) {
        //             building[i][j].settype(1); // n is percentage capacity
        //         }
        //     }
        // }