package ng.onyi.adventureProject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EntityTest {

    private Resident resident;
    private Burglar burglar;

    @BeforeEach
    public void setUp() {
        resident = new Resident("Player", 12, 3);
        burglar = new Burglar("Enemy", 12, 4);
    }

    @Test
    public void testPunch() {

        resident.punch(burglar);

        assertEquals(9, burglar.getHealth(), "Burglar's health should decrease by the resident's damage amount.");
    }

    @Test
    public void testAddDamage() {
        // Arrange
        int initialDamage = resident.getDamage();

        // Act
        resident.addDamage(3);  // Increases damage by 3

        // Assert
        assertEquals(initialDamage + 3, resident.getDamage(), "Resident's damage should increase by 3 after picking up the frying pan.");
    }

    @Test
    public void testIsConscious() {
        // Act
        resident.takeHit(12);  // Decrease health to 0

        // Assert
        assertFalse(resident.isConscious(), "Resident should be unconscious when health is 0.");
    }
}