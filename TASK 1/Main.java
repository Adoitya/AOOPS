public class Main {
    public static void main(String[] args) {
        // Initialize Game State
        GameState gameState = GameState.getInstance();
        System.out.println("Starting Level: " + gameState.getLevel());

        // Create enemies for the level
        System.out.println("\nEnemies in the Level:");
        Enemy zombie = EnemyFactory.createEnemy("zombie");
        Enemy alien = EnemyFactory.createEnemy("alien");
        zombie.attack();
        alien.attack();

        // Generate items based on difficulty
        System.out.println("\nEasy Mode Items:");
        GameItemsFactory easyFactory = new EasyGameItemsFactory();
        System.out.println("Weapon: " + easyFactory.getWeapon());
        System.out.println("Power-Up: " + easyFactory.getPowerUp());

        System.out.println("\nHard Mode Items:");
        GameItemsFactory hardFactory = new HardGameItemsFactory();
        System.out.println("Weapon: " + hardFactory.getWeapon());
        System.out.println("Power-Up: " + hardFactory.getPowerUp());
    }
}
