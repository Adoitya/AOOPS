public class EnemyFactory {
    public static Enemy createEnemy(String type) {
        switch (type.toLowerCase()) {
            case "zombie":
                return new Zombie();
            case "alien":
                return new Alien();
            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
