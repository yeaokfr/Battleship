import java.util.Scanner;
public class Battleship {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean done = false;
        boolean doneTwo = false;
        String menuOne;
        String menuTwo;
        int row;
        int col;
        int len;
        int shoot;
        boolean horizontal;
        Board ship = new Board();

        while (!done) {
            menuOne = InputHelper.getNonZeroLenString(scan, "Choose: \na: Add Ship \nb: See Board \np: Play \nq: Quit Game");

            if (menuOne.equalsIgnoreCase("a")) {
                row = InputHelper.getInt(scan, "Enter a row you want your ship to be on:");
                col = InputHelper.getInt(scan, "Enter a column you want your ship to be on:");
                len = InputHelper.getRangedInt(scan, "Enter the length of your ship(3 or 4):", 3, 4);
                horizontal = InputHelper.getYNConfirm(scan, "Enter Y if you want your ship to be horizontal, enter N if you want your ship to be vertical:");
                if (ship.addShip(row, col, len, horizontal)) {
                    System.out.println("Ship Added!");
                } else if (!ship.addShip(row, col, len, horizontal)) {
                    System.out.println("ARE YOU STUPID?? YOU CAN'T PUT A SHIP THERE!!");
                }
            } else if (menuOne.equalsIgnoreCase("b")) {
                System.out.println(ship.toString());
            } else if (menuOne.equalsIgnoreCase("p")) {
                if (ship.foundShip(3) && ship.foundShip(4)) {
                    System.out.println("Ok, let's play!");
                    done = true;
                } else {
                    System.out.println("You need 2 ships of length 3 and 4 before you can play");
                }
            } else if (menuOne.equalsIgnoreCase("q")) {
                done = true;
            }
        }

        while (!doneTwo) {
            menuTwo = InputHelper.getNonZeroLenString(scan, "Choose: \ns: Shoot \nb: See Board \nq: Quit Game");

            if (menuTwo.equalsIgnoreCase("s")) {
                row = InputHelper.getInt(scan, "Enter a row you want your shot to be in:");
                col = InputHelper.getInt(scan, "Enter a column you want your shot to be in:");
                shoot = ship.shoot(row, col);
                if (shoot == -1) {
                    System.out.println("Invalid spot");
                } else if (shoot == 0) {
                    System.out.println("You missed");
                } else if (shoot == 1) {
                    System.out.println("Hit!");
                    if (ship.gameOver()) {
                        System.out.println("You win!");
                        doneTwo = true;
                    }
                } else if (shoot == 2) {
                    System.out.println("You already shot there dummy");
                }
            } else if (menuTwo.equalsIgnoreCase("b")) {
                System.out.println(ship.toString());
            } else if (menuTwo.equalsIgnoreCase("q")) {
                doneTwo = true;
            }
        }
    }
}