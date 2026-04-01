// QUESTION: 2751. Robot Collisions
// LINK: https://leetcode.com/problems/robot-collisions/description/?envType=daily-question&envId=2026-04-01

// SOLUTION: SIMULATION

class Solution {

    class Robot{
        int position;
        int health;
        char direction;
        int index;  // original index for ordering result

        Robot(int position, int health, char direction, int index)
        {
            this.position = position;
            this.health = health;
            this.direction = direction;
            this.index = index;
        }
    }
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;
        List<Robot> robots = new ArrayList<>();
        for(int i= 0; i < n; i++)
        {
            robots.add(new Robot(positions[i], healths[i], directions.charAt(i), i));
        }

        robots.sort((a, b) -> a.position - b.position);


        // Process Collision using stack
        Stack<Robot> stack = new Stack<>();

        for(Robot robot : robots)
        {
            // If stack is empty or current robot is moving right
            if(stack.isEmpty() || robot.direction == 'R')
            {
                stack.push(robot);
            }

            // Current robot moving left, potential collision with right-moving robots
            else 
            {
                // Check for collisions with robots moving right
                while(!stack.isEmpty() && stack.peek().direction == 'R')

                {
                    Robot right = stack.pop();

                    if(right.health > robot.health)
                    {
                        right.health--;
                        stack.push(right);
                        robot = null;
                        break;
                    }

                    else if(right.health < robot.health)
                    {
                        robot.health--;
                        // continue to check next robot in stack
                    }
                    else
                    {
                        // Both die
                        robot = null;
                        break;
                    }
                }
                // If robot still exists and not destroyed, push it
                if(robot != null)
                {
                    stack.push(robot);
                }
            }
        }

        // Collect survivors in original index order
        List<Robot> survivor = new ArrayList<>(stack);
        survivor.sort((a, b) -> a.index - b.index);

        List<Integer> result = new ArrayList<>();
        for(Robot robot : survivor)
        {
            result.add(robot.health);
        }  
        return result;
    }
}