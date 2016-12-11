package edu.bsuir.DAO;

/**
 * Created by Alesha on 01.10.2016.
 */
public interface Queries {

    String GET_ALL_FILMS = "SELECT idFilm, nameFilm, duration, genre, country, year, idPicture " +
            "FROM Film";
    String GET_FILM_BY_ID = "SELECT idFilm, nameFilm, duration, genre, country, year, idPicture " +
            "FROM Film WHERE idFilm = ?";
    String INSERT_INTO_FILMS = "INSERT INTO Film(idFilm, nameFilm, duration, genre, country, year, idPicture) VALUES" +
            "(?,?,?,?,?,?,?)";
    String DELETE_FROM_FILM = "DELETE FROM Film WHERE idFilm = ?";
    String UPDATE_FILM = "UPDATE Film SET nameFilm = ?, duration = ?, genre = ?, country = ?, year = ?, idPicture = ?" +
            " WHERE idFilm = ?";

    String GET_ALL_PICTURES = "SELECT idPicture, fileName, uploadedName, width, height FROM Picture";
    String GET_PICTURE_BY_ID = "SELECT idPicture, fileName, uploadedName, width, height FROM Picture WHERE idPicture " +
            "= ?";
    String INSERT_INTO_PICTURE = "INSERT INTO Picture(idPicture, fileName, uploadedName, width, height) VALUES(?,?,?," +
            "?,?)";
    String DELETE_FROM_PICTURE = "DELETE FROM Picture WHERE idPicture = ?";
    String UPDATE_PICTURE = "UPDATE Picture SET fileName = ?, uploadedName = ?, width = ?, height = ? WHERE idPicture" +
            " = ?";

    String GET_ALL_SESSION = "SELECT idSession, idFilm, startTime FROM Session";
    String GET_SESSION_BY_ID = "SELECT idSession, idFilm, startTime FROM Session WHERE idSession = ?";
    String INSERT_INTO_SESSION = "INSERT INTO Session(idSession, idFilm, startTime) VALUES(?,?,?)";
    String DELETE_FROM_SESSION = "DELETE FROM Session WHERE idSession = ?";
    String UPDATE_SESSION = "UPDATE Session SET idFilm = ?, startTime = ? WHERE idSession = ?";

    String RESULT_QUERY =
            "SELECT * " +
            "FROM film " +
            "INNER JOIN Session s ON film.idFilm = s.idFilm " +
            "WHERE (film.duration BETWEEN ? AND ?) " +
            "      AND film.genre IN (?) " +
            "      AND film.country = ? " +
            "      AND film.year = ? " +
            "      AND s.startTime >= ? " +
            "      AND film.nameFilm LIKE ?";
}
