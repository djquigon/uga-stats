package com.techzilla.ugastats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.techzilla.ugastats.entities.Player;
import com.techzilla.ugastats.repositories.PlayerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class PlayerRepositoryTests {

    @Autowired
    private PlayerRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        Player user = new Player();
        user.setId("test-player-2");
        user.setFirstName("Test");
        user.setLastName("Player");
        Player savedUser = repo.save(user);

        Player existUser = entityManager.find(Player.class, savedUser.getId());

        assertThat(existUser.getFirstName()).isEqualTo(user.getFirstName());
    }

    @Test
    public void testFindUserByEmail() {
        List <Player> player = repo.findAll();//findById("zion-logue-1");
        System.out.println(player==null);
        System.out.println(player);
        assertThat(player).isNotNull();
    }
    
}
