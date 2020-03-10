package leetcode9;

/**
 * @author gumingjie
 * @date 2020/3/8 12:09 下午
 * @description https://leetcode.com/problems/regions-cut-by-slashes/
 */
public class RegionsCutBySlashes {
    private int[][] arr;

    public int regionsBySlashes(String[] grid) {
        int regions = 0;
        if (grid == null || grid.length < 1) {
            return regions;
        }
        arr = new int[grid.length * grid.length * 2][2];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                int index = (row * grid.length + col) * 2;
                arr[index][0] = index;
                arr[index][1] = 1;
                arr[index + 1][1] = 1;
                arr[index + 1][0] = index + 1;
                char ch = grid[row].charAt(col);
                if (ch == ' ') {
                    union(index + 1, index);   // self
                }
                if (row > 0) {
                    int cur = grid[row].charAt(col) == '\\' ? 1 : 0;
                    int last = grid[row - 1].charAt(col) == '/' ? 1 : 0;
                    union(index + cur, index + last - grid.length * 2);
                }
                if (col > 0) {
                    union(index, index - 1);   // left
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] == i) {
                regions++;
            }
        }
        return regions;
    }

    private void union(int from, int to) {
        if (to < 0) {
            return;
        }
        from = getHead(from);
        to = getHead(to);
        if (from != to) {
            if (arr[from][1] >= arr[to][1]) {
                arr[to][0] = from;
                arr[from][1] += arr[to][1];
            } else {
                arr[from][0] = to;
                arr[to][1] += arr[from][1];
            }
        }
    }

    private int getHead(int index) {
        while (index != arr[index][0]) {
            arr[index][0] = arr[arr[index][0]][0];
            index = arr[index][0];
        }
        return index;
    }

    public static void main(String[] args) {
        String[] input = {" /", "/ "};
        int result = new RegionsCutBySlashes().regionsBySlashes(input);
        System.out.println(result);
    }
}
