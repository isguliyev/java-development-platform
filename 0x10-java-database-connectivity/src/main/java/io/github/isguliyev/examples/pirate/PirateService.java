package io.github.isguliyev.examples.pirate;

import java.util.List;
import java.util.Objects;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class PirateService {
    private final String url;
    private final PirateDAO pirateDAO;

    public PirateService(String url, PirateDAO pirateDAO) {
        this.url = url;
        this.pirateDAO = pirateDAO;
    }

    private void executeInTransaction(TransactionalOperation transactionalOperation) {
        try (Connection connection = DriverManager.getConnection(url)) {
            try {
                connection.setAutoCommit(false);
                transactionalOperation.execute(connection);
                connection.commit();
            } catch (SQLException exception) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    exception.addSuppressed(rollbackException);
                }

                throw exception;
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void create(Pirate pirate) {
        Objects.requireNonNull(pirate, "Failed to create pirate: pirate is null");
        this.executeInTransaction(connection -> this.pirateDAO.create(connection, pirate));
    }

    public Optional<Pirate> findById(Long id) {
        Objects.requireNonNull(id, "Failed to find pirate by id: id is null");

        if (id <= 0) {
            throw new IllegalArgumentException("Failed to find pirate by id: id is not positive");
        }

        try (Connection connection = DriverManager.getConnection(url)) {
            return this.pirateDAO.findById(connection, id);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Pirate> findAll() {
        try (Connection connection = DriverManager.getConnection(url)) {
            return this.pirateDAO.findAll(connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void update(Pirate pirate) {
        Objects.requireNonNull(pirate, "Failed to update pirate: pirate is null");

        if (pirate.getId() == null) {
            throw new IllegalArgumentException("Failed to update pirate: pirate id is null");
        }

        this.executeInTransaction(connection -> this.pirateDAO.update(connection, pirate));
    }

    public void deleteById(Long id) {
        Objects.requireNonNull(id, "Failed to delete pirate by id: id is null");

        if (id <= 0) {
            throw new IllegalArgumentException("Failed to delete pirate by id: id is not positive");
        }

        this.executeInTransaction(connection -> this.pirateDAO.deleteById(connection, id));
    }
}