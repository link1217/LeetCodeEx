package leetcode8;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gumingjie
 * @date 2020/3/9 8:41 下午
 * @description https://leetcode.com/problems/walking-robot-simulation/
 */
public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0;
        int y = 0;
        int face = 0;
        int max = 0;
        Set<Integer> set = new HashSet<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int[] ob : obstacles) {
            set.add((ob[0] << 16) + ob[1]);
        }

        for (int command : commands) {
            if (command == -1) {
                face = (face + 1) % 4;
            } else if (command == -2) {
                face = (face + 3) % 4;
            } else {
                while (command-- > 0 && !set.contains(((x + dx[face]) << 16) + y + dy[face])) {
                    x += dx[face];
                    y += dy[face];
                    max = Math.max(max, x * x + y * y);
                }
            }
        }
        return max;
    }

}
