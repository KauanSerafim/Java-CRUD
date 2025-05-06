package repository;

import conn.ConnectionFactory;
import domain.Dorama;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class DoramaRepository {
    public static List<Dorama> findByTitle(String title) {
        List<Dorama> doramas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementFindByTitle(conn, title);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Dorama dorama = Dorama.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .releaseYear(rs.getInt("release_year"))
                        .score(rs.getInt("score"))
                        .build();
                doramas.add(dorama);
            }

        } catch (SQLException e) {
            log.error("Error while trying to find the Dorama", e);
        }
        return doramas;
    }

    private static PreparedStatement createPrepareStatementFindByTitle(Connection conn, String title) throws SQLException {
        String sql = "SELECT * FROM k_dorama WHERE title like ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", title));
        return ps;
    }

    public static void save(Dorama dorama) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementSave(conn, dorama)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to save Dorama.", e);
        }
    }

    private static PreparedStatement createPrepareStatementSave(Connection conn, Dorama dorama) throws SQLException {
        String sql = "INSERT INTO `dorama`.`k_dorama` (`title`, `score`, `release_year`) VALUES (?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dorama.getTitle());
        ps.setDouble(2, dorama.getScore());
        ps.setInt(3, dorama.getReleaseYear());
        return ps;
    }

    public static void updateTitle(Dorama dorama) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementUpdateTitle(conn, dorama)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to update Dorama.", e);
        }
    }

    private static PreparedStatement createPrepareStatementUpdateTitle(Connection conn, Dorama dorama) throws SQLException {
        String sql = "UPDATE `dorama`.`k_dorama` SET `title` = ? WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, dorama.getTitle());
        ps.setInt(2, dorama.getId());
        return ps;
    }

    public static Optional<Dorama> findById(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) Optional.empty();
            return Optional.of(Dorama.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .build());
        } catch (SQLException e) {
            log.error("Error while trying to find the Dorama", e);
        }
        return Optional.empty();
    }

    private static PreparedStatement createPrepareStatementFindById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM dorama.`k_dorama` WHERE `id` = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void delete(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPrepareStatementDelete(conn, id)) {
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to delete the Dorama", e);
        }
    }

    private static PreparedStatement createPrepareStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `dorama`.`k_dorama` WHERE (`id` = ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }
}
