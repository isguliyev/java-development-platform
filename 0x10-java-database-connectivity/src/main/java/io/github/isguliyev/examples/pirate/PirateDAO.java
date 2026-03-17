package io.github.isguliyev.examples.pirate;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class PirateDAO {
    public void create(Connection connection, Pirate pirate) throws SQLException {
        Objects.requireNonNull(connection, "Failed to create pirate: connection is null");
        Objects.requireNonNull(pirate, "Failed to create pirate: pirate is null");

        final String sql = "INSERT INTO pirate (name, role, crew) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pirate.getName());
            preparedStatement.setString(2, pirate.getRole());
            preparedStatement.setString(3, pirate.getCrew());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Failed to create pirate: no rows were affected");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    pirate.setId(resultSet.getLong(1));
                } else {
                    throw new SQLException("Failed to create pirate: database did not return generated id");
                }
            }
        }
    }

    public Optional<Pirate> findById(Connection connection, Long id) throws SQLException {
        Objects.requireNonNull(connection, "Failed to find pirate by id: connection is null");
        Objects.requireNonNull(id, "Failed to find pirate by id: id is null");

        if (id <= 0) {
            throw new IllegalArgumentException("Failed to find pirate by id: id is not positive");
        }

        final String sql = "SELECT * FROM pirate WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(
                        new Pirate(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("role"),
                            resultSet.getString("crew")
                        )
                    );
                }

                return Optional.empty();
            }
        }
    }

    public List<Pirate> findAll(Connection connection) throws SQLException {
        Objects.requireNonNull(connection, "Failed to find all pirates: connection is null");

        final ArrayList<Pirate> pirates = new ArrayList<Pirate>();
        final String sql = "SELECT * FROM pirate";

        try (Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    pirates.add(
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

        return pirates;
    }

    public void update(Connection connection, Pirate pirate) throws SQLException {
        Objects.requireNonNull(connection, "Failed to update pirate: connection is null");
        Objects.requireNonNull(pirate, "Failed to update pirate: pirate is null");

        if (pirate.getId() == null) {
            throw new IllegalArgumentException("Failed to update pirate: pirate id is null");
        }

        final String sql = "UPDATE pirate SET name=?, role=?, crew=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pirate.getName());
            preparedStatement.setString(2, pirate.getRole());
            preparedStatement.setString(3, pirate.getCrew());
            preparedStatement.setLong(4, pirate.getId());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Failed to update pirate: no rows were affected");
            }
        }
    }

    public void deleteById(Connection connection, Long id) throws SQLException {
        Objects.requireNonNull(connection, "Failed to delete pirate by id: connection is null");
        Objects.requireNonNull(id, "Failed to delete pirate by id: id is null");

        if (id <= 0) {
            throw new IllegalArgumentException("Failed to delete pirate by id: id is not positive");
        }

        final String sql = "DELETE FROM pirate WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}