import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StormTrooperTest extends Trooper {
   StormTrooper st;
    @BeforeEach
    void setUp() {
        st = new StormTrooper("kd", 123);
    }

    @AfterEach
    void tearDown() {
        st = null;
    }

    @org.junit.jupiter.api.Test
    void testGetUnit() {
    }

    @org.junit.jupiter.api.Test
    void testSetUnit() {
    }

    @org.junit.jupiter.api.Test
    void testGetNumber() {
    }

    @org.junit.jupiter.api.Test
    void testSetNumber() {
    }

    @org.junit.jupiter.api.Test
    void testGetTrooperKind() {
    }

    @org.junit.jupiter.api.Test
    void testSetTrooperKind() {
    }

    @org.junit.jupiter.api.Test
    void testGetMarchSpeed() {
    }

    @org.junit.jupiter.api.Test
    void testSetMarchSpeed() {
    }

    @org.junit.jupiter.api.Test
    void testGetMarchModifier() {
    }

    @org.junit.jupiter.api.Test
    void testSetMarchModifier() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        System.out.println(st);
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
    }

    @org.junit.jupiter.api.Test
    void testAddToUnit() {
    }

    @org.junit.jupiter.api.Test
    void testAttack() {
    }

    @org.junit.jupiter.api.Test
    void getName() {
    }

    @org.junit.jupiter.api.Test
    void setName() {
    }

    @org.junit.jupiter.api.Test
    void getSoldierCount() {
    }

    @org.junit.jupiter.api.Test
    void testToString1() {
    }

    @org.junit.jupiter.api.Test
    void testMarch() {
    }

    @Override
    public double march(double duration) {
        return 0;
    }
}