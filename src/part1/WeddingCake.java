package part1;

public class WeddingCake extends Cake{
    private int tiers;

    public WeddingCake(String flavor) {
        super(flavor);
    }

    public int getTiers() {
        return tiers;
    }

    public void setTiers(int tiers) {
        this.tiers = tiers;
    }
}
