public class EasyGameItemsFactory implements GameItemsFactory {
    @Override
    public String getWeapon() {
        return "Basic Sword";
    }

    @Override
    public String getPowerUp() {
        return "Small Health Pack";
    }
}
