package io.github.isguliyev.examples.pirate;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface TransactionalOperation {
    void execute(Connection connection) throws SQLException;
}