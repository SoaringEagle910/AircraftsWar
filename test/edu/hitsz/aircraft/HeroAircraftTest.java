package edu.hitsz.aircraft;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    @BeforeEach
    void setUp() {
        System.out.println("test begin");
    }

    @AfterEach
    void tearDown() {
        System.out.println("test end");
    }

    @DisplayName("getHp test")
    @Test
    void getHpTest() {
        int expect=1000;
        int actual=HeroAircraft.getHeroAircraft().getHp();
        assertEquals(expect,actual);
    }

    @DisplayName("addHp test")
    @ParameterizedTest()
    @ValueSource(ints = {10,30,40})
    void addHpTest(int num) {
        int expect;
        if(num==10)expect=990;
        else expect=1000;
        HeroAircraft.getHeroAircraft().addHp(-20);
        //System.out.println(HeroAircraft.getHeroAircraft().getHp());
        HeroAircraft.getHeroAircraft().addHp(num);
        int actual=HeroAircraft.getHeroAircraft().getHp();
        assertEquals(expect,actual);
    }

    @DisplayName("getLocationX test")
    @Test
    void getLocationXTest() {
        int expect=256;
        int actual=HeroAircraft.getHeroAircraft().getLocationX();
        assertEquals(expect,actual);
    }
}