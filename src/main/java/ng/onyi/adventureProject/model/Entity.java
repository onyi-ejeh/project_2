package ng.onyi.adventureProject.model;

public abstract class Entity {

    private String role;
    private int health;
    private int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }


    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void punch (Entity toPunch) {
     toPunch.takeHit(this.damage);
    }

    public void takeHit(int damage) {
        this.health -= damage;
    }

    public boolean isConscious() {
      return health > 0;

    }

    public void addDamage(int damage){
        this.damage += damage;
    }


}
