package io.github.isguliyev.examples.pirate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class PirateDAOTest {
    private static final String DATABASE_NAME = "test.db";
    private Connection connection;
    private PirateDAO pirateDAO;
    private Pirate pirate;
    private List<Pirate> pirates;

    @BeforeAll
    void setUpAll() throws SQLException, IOException {
        this.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s?foreign_keys=on", DATABASE_NAME));
        final String sql = Files.readString(Path.of("schema.sql"));

        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        }

        this.pirateDAO = new PirateDAO();
    }

    @AfterAll
    void tearDownAll() throws SQLException, IOException {
        this.connection.close();
        Files.deleteIfExists(Path.of(DATABASE_NAME));
    }

    @BeforeEach
    void setUp() {
        this.pirate = new Pirate("Monkey D. Luffy", "Captain", "Straw Hat Pirates");
        this.pirates = List.of(
            new Pirate("Roronoa Zoro", "Combatant", "Straw Hat Pirates"),
            new Pirate("'Cat Burglar' Nami", "Navigator", "Straw Hat Pirates"),
            new Pirate("'God' Usopp", "Senior Officer", "Straw Hat Pirates"),
            new Pirate("'Black Leg' Sanji", "Cook", "Straw Hat Pirates"),
            new Pirate("Tony Tony Chopper", "Doctor", "Straw Hat Pirates")
        );
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute("DELETE FROM pirate");
            statement.execute("DELETE FROM sqlite_sequence WHERE name='pirate'");
        }
    }

    private void insertPirate(Pirate pirate) throws SQLException {
        final String sql = "INSERT INTO pirate (name, role, crew) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pirate.getName());
            preparedStatement.setString(2, pirate.getRole());
            preparedStatement.setString(3, pirate.getCrew());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    pirate.setId(resultSet.getLong(1));
                }
            }
        }
    }

    @Nested
    class CreateTest {
        @Test
        void create_insertsPirate_whenConnectionAndPirateIsNotNull() throws SQLException {
            pirateDAO.create(connection, pirate);

            final String sql = "SELECT * FROM pirate WHERE id=?";
            Pirate actualPirate = null;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, pirate.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        actualPirate = new Pirate(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("role"),
                            resultSet.getString("crew")
                        );
                    } else {
                        throw new AssertionError("Pirate not found in database");
                    }
                }
            }

            assertEquals(pirate, actualPirate);
        }

        @Test
        void create_throwsNullPointerException_whenConnectionOrPirateIsNull() throws SQLException {
            assertAll(
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.create(null, pirate)),
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.create(connection, null))
            );
        }

        @Test
        void create_setsGeneratedId_whenConnectionAndPirateIsNotNull() throws SQLException {
            for (Pirate pirate : pirates) {
                pirateDAO.create(connection, pirate);
            }

            assertAll(
                () -> assertTrue(pirates.get(0).getId() > 0),
                () -> assertTrue(pirates.get(1).getId() > pirates.get(0).getId()),
                () -> assertTrue(pirates.get(2).getId() > pirates.get(1).getId()),
                () -> assertTrue(pirates.get(3).getId() > pirates.get(2).getId()),
                () -> assertTrue(pirates.get(4).getId() > pirates.get(3).getId())
            );
        }
    }

    @Nested
    class FindByIdTest {
        @Test
        void findById_returnsPirateWithGivenId_whenConnectionAndIdIsNotNull() throws SQLException {
            insertPirate(pirate);

            final Pirate actualPirate = pirateDAO.findById(connection, pirate.getId()).get();

            assertEquals(pirate, actualPirate);
        }

        @Test
        void findById_throwsNullPointerException_whenConnectionOrIdIsNull() {
            assertAll(
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.findById(null, 1L)),
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.findById(connection, null))
            );
        }

        @Test
        void findById_throwsIllegalArgumentException_whenIdIsNotPositive() {
            assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> pirateDAO.findById(connection, 0L)),
                () -> assertThrows(IllegalArgumentException.class, () -> pirateDAO.findById(connection, -1L))
            );
        }
    }

    @Nested
    class FindAllTest {
        @Test
        void findAll_returnsListOfAllPirates_whenConnectionIsNotNull() throws SQLException {
            for (Pirate pirate : pirates) {
                insertPirate(pirate);
            }

            final List<Pirate> actualPirates = pirateDAO.findAll(connection);

            assertEquals(pirates, actualPirates);
        }

        @Test
        void findAll_throwsNullPointerException_whenConnectionIsNull() throws SQLException {
            assertThrows(NullPointerException.class, () -> pirateDAO.findAll(null));
        }
    }

    @Nested
    class UpdateTest {
        @Test
        void update_updatesPirate_whenConnectionAndPirateIsNotNull() throws SQLException {
            insertPirate(pirate);

            pirate.setRole("Best Captain");
            pirateDAO.update(connection, pirate);

            final String sql = "SELECT * FROM pirate WHERE id=?";
            Pirate actualPirate = null;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, pirate.getId());

                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        actualPirate = new Pirate(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("role"),
                            resultSet.getString("crew")
                        );
                    } else {
                        throw new AssertionError("Pirate not found in database");
                    }
                }
            }

            assertEquals(pirate, actualPirate);
        }

        @Test
        void update_throwsNullPointerException_whenConnectionOrPirateIsNull() throws SQLException {
            assertAll(
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.update(null, pirate)),
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.update(connection, null))
            );
        }

        @Test
        void update_throwsIllegalArgumentException_whenPirateIdIsNull() throws SQLException {
            assertThrows(IllegalArgumentException.class, () -> pirateDAO.update(connection, pirate));
        }
    }

    @Nested
    class DeleteByIdTest {
        @Test
        void deleteById_deletesPirateWithGivenId_whenConnectionAndIdIsNotNull() throws SQLException {
            insertPirate(pirate);
            pirateDAO.deleteById(connection, pirate.getId());

            final ArrayList<Pirate> actualPirates = new ArrayList<Pirate>();
            final String sql = "SELECT * FROM pirate";

            try (Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        actualPirates.add(
                            new Pirate(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                resultSet.getString("role"),
                                resultSet.getString("crew")
                            )
                        );
                    }
                }
            }

            assertTrue(actualPirates.isEmpty());
        }

        @Test
        void deleteById_throwsNullPointerException_whenConnectionOrIdIsNull() {
            assertAll(
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.deleteById(null, 1L)),
                () -> assertThrows(NullPointerException.class, () -> pirateDAO.deleteById(connection, null))
            );
        }

        @Test
        void deleteById_throwsIllegalArgumentException_whenIdIsNotPositive() {
            assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> pirateDAO.deleteById(connection, 0L)),
                () -> assertThrows(IllegalArgumentException.class, () -> pirateDAO.deleteById(connection, -1L))
            );
        }
    }
}