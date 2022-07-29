package CLI;

import Game.Action;
import Game.PlayerChoice;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public Action getAction(){
        char input = scanner.next().charAt(0);
        Action action = null;
        while (action == null) {
            switch (input) {
                case 'a':
                    action = Action.LEFT;
                    break;
                case 'A':
                    action = Action.LEFT;
                    break;
                case 'd':
                    action = Action.RIGHT;
                    break;
                case 'D':
                    action = Action.RIGHT;
                    break;
                case 'w':
                    action = Action.UP;
                    break;
                case 'W':
                    action = Action.UP;
                    break;
                case 's':
                    action = Action.DOWN;
                    break;
                case 'S':
                    action = Action.DOWN;
                    break;
                case 'e':
                    action = Action.SPECIAL_ABILITY;
                    break;
                case 'E':
                    action = Action.SPECIAL_ABILITY;
                    break;
                case 'q':
                    action = Action.NONE;
                    break;
                case 'Q':
                    action = Action.NONE;
                    break;
                default:
                    input = scanner.next().charAt(0);
                    break;
            }
        }
        return action;
    }

    public int getPlayerChoice() {//while & Menu
        int input = 0;
        while (input < 1 || input > 7) {
            System.out.println("Select player:\n" +
                    "1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1\n" +
                    "        Experience: 0/50                Cooldown: 0/3\n" +
                    "2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1\n" +
                    "        Experience: 0/50                Cooldown: 0/5\n" +
                    "3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1\n" +
                    "        Experience: 0/50                Mana: 75/300            Spell Power: 15\n" +
                    "4. Thoros of Myr                Health: 250/250         Attack: 25              Defense: 4              Level: 1\n" +
                    "                Experience: 0/50                Mana: 37/150            Spell Power: 20\n" +
                    "5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1\n" +
                    "        Experience: 0/50                Energy: 100/100\n" +
                    "6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1\n" +
                    "        Experience: 0/50                Energy: 100/100\n" +
                    "7. Ygritte              Health: 220/220         Attack: 30              Defense: 2              Level: 1\n" +
                    "        Experience: 0/50                Arrows: 10              Range: 6");

            input = scanner.nextInt();
        }

        return input;
    }

    public void print(String message){
        System.out.println(message);
    }

}
