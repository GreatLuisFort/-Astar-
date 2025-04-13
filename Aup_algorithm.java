public class Aup_algorithm{
    private int beginningx = -2;
    private int beginningy = -2;
    private int endingx = -2;
    private int endingy = -2;
    private final int[][] recordArray;
    private int times = 0;
    private final int[][] log;
    private boolean noResult = false;
    public Rong_siqiang_algorithm(int [][]maze)
    {
        log = new int[maze.length * maze[0].length][2];
        recordArray = new int[maze.length][maze[0].length];
        findBeginning(maze);
        findEnding(maze);
        createRecord(maze);
    }
    private void findBeginning(int[][] maze)
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j] == -1)
                {
                    this.beginningx = i;
                    this.beginningy = j;
                    return;
                }
            } 
        }
        this.beginningx = -1;
        this.beginningy = -1;
        this.noResult = true;
    }
    private void findEnding(int[][] maze)
    {
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[0].length; j++)
            {
                if (maze[i][j] == 2)
                {
                    this.endingx = i;
                    this.endingy = j;
                    return;
                }
            }
        }
        this.endingx = -1;
        this.endingy = -1;
        this.noResult = true;
    }
    private void createRecord(int[][] maze)
    {
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++)
            {
                this.recordArray[i][j] = 0;
            } 
        }
    }
    private void recordPoint(int x, int y)
    {
        this.recordArray[x][y] = 1;
        this.log[this.times][0] = x;
        this.log[this.times][1] = y;
        this.times = this.times + 1;
    }
    public void printBeginning(){
        if (this.beginningx == -1 && this.beginningy == -1)
        {
            System.out.println("error: not found beginning");
        }
        else 
        {
            System.out.println("x = " + this.beginningx);
            System.out.println("y = " + this.beginningy);
        }

    }
    public void printEnding()
    {
        if (this.endingx == -1 && this.endingy == -1)
        {
            System.out.println("error: not found ending");
        }
        else 
        {
            System.out.println("x = " + this.endingx);
            System.out.println("y = " + this.endingy);
        }
    }
    public void printPathOfMaze(int[][] maze, int x, int y)
    {
        if (this.times == 0)
        {
            return;
        }
        if (maze[x][y] != 2)
        {
            for (int i = 0; i < maze.length; i++)
            {
                for (int j = 0; j < maze[0].length; j++)
                {
                    System.out.print(maze[i][j] + " ");
                } 
                System.out.println(" ");
            }
            maze[x][y] = -2;
            System.out.println(" ");
        }
    }

    public void printLog()
    {
        if (this.times == 0)
        {
            return;
        }
        for(int i = 0; i < this.times; i++)
        {
            System.out.print(i + ": ");
            for (int j = 0; j < this.log[i].length; j++)
            {
                System.out.print(this.log[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(" ");
    }
    public void printRecord()
    {
        if (this.times == 0)
        {
            return;
        }
        for (int i = 0; i < this.recordArray.length; i++)
        {
            for (int j = 0; j < this.recordArray[i].length; j++)
            {
                System.out.print(this.recordArray[i][j] + " ");
            } 
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    public void printResult(int[][] maze)
    {
        if (noResult == true)
        {
            System.out.println("Not Found!!!");
        }
        if (this.times == 0)
        {
            return;
        }
        int[][] result = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                    result[i][j] = 0;
            }
        }
        for (int i = 0; i < this.times; i++)
        {
            result[this.log[i][0]][this.log[i][1]] = 1;
        }
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze[i].length; j++)
            {
                System.out.print(result[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    private boolean noway(int x, int y) 
    {
        boolean noway = true;
        if (x + 1 < this.recordArray.length)
        {
            noway &= this.recordArray[x + 1][y] == 1;
        }
        if (x - 1 >= 0) 
        {
            noway &= this.recordArray[x - 1][y] == 1;
        }
        if (y + 1 < this.recordArray[0].length) 
        {
            noway &= this.recordArray[x][y + 1] == 1;
        }
        if (y - 1 >= 0)
        {
            noway &= this.recordArray[x][y - 1] == 1;
        }
        return noway;
    }
    public void searchEnding(int[][] maze, int x, int y)
    {
        if(noResult == true)
        {
        }
        else if (maze[x][y] == 2)
        {
            System.out.println("Congratulations!!");
            recordPoint(x, y);
        }
        else {
            recordPoint(x, y);
            int distancex = this.endingx - x;
            int distancey = this.endingy - y;
            if (distancex > 0 && this.recordArray[x + 1][y] != 1 && maze[x + 1][y] != 1)
            {
                searchEnding(maze, x + 1, y);
            }
            else if (distancex < 0 && this.recordArray[x - 1][y] != 1 && maze[x - 1][y] != 1)
            {
                searchEnding(maze, x - 1, y);
            }
            else if (distancey > 0 && this.recordArray[x][y + 1] != 1 && maze[x][y + 1] != 1)
            {
                searchEnding(maze, x, y + 1);
            }
            else if (distancey < 0 && this.recordArray[x][y - 1] != 1 && maze[x][y - 1] != 1)
            {
                searchEnding(maze, x, y - 1);
            }
            else if (!noway(x, y)) 
            {
                if (x + 1 < this.recordArray.length && this.recordArray[x + 1][y] != 1 && maze[x + 1][y] != 1)
                {
                    searchEnding(maze, x + 1, y);
                }
                else if (x - 1 >= 0 && this.recordArray[x - 1][y] != 1 && maze[x - 1][y] != 1)
                {
                    searchEnding(maze, x - 1, y);
                }
                else if (y + 1 < this.recordArray.length && this.recordArray[x][y + 1] != 1 && maze[x][y + 1] != 1)
                {
                    searchEnding(maze, x, y + 1);
                }
                else if(y - 1 >= 0 && this.recordArray[x][y - 1] != 1 && maze[x][y - 1] != 1)
                {
                    searchEnding(maze, x, y - 1);
                }
                else
                {
                    this.times = this.times - 2;
                    searchEnding(maze, this.log[this.times][0], this.log[this.times][1]);
                }
            }
            else{
                this.noResult = true;
            }
        }
    }
    public void searchEnding(int[][]maze)
    {
        searchEnding(maze, this.beginningx, this.beginningy);
    }
    public static void main(String[] args) 
    {
        int[][] maze = 
        {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0},
            {0, 1, 2, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, -1, 0, 0},
            {0, 1, 1, 1, 0, 1, 0, 0, 0},
        };
        Rong_siqiang_algorithm invoke = new Rong_siqiang_algorithm(maze);
        invoke.searchEnding(maze);
        invoke.printResult(maze);
    }
}