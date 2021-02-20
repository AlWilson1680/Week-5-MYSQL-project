package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cards;

public class CardsDao {

	private Connection connection;
	private final String GET_CARDS_QUERY = "SELECT * FROM cards";
	private final String GET_CARD_BY_ID_QUERY = "SELECT * FROM cards WHERE id = ?";
	private final String ADD_NEW_CARD_QUERY = "INSERT INTO cards(name, type, description, stats) VALUE(?,?,?,?)";
	private final String UPDATE_CARD_BY_ID_QUERY = "UPDATE cards SET name = ?, type = ?, description = ?, stats = ? WHERE id = ?";
	private final String DELETE_CARD_BY_ID_QUERY = "DELETE FROM cards WHERE id = ?";

	public CardsDao() {
		connection = DBConnection.getConnection();
	}

	public List<Cards> getCards() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CARDS_QUERY).executeQuery();
		List<Cards> cards = new ArrayList<>();

		while (rs.next()) {
			cards.add(populateCards(rs));
		}

		return cards;
	}

	public Cards getCardsById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CARD_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateCards(rs);
	}

	private Cards populateCards(ResultSet rs) throws SQLException {
		return new Cards(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	}

	public void addCard(String name, String type, String description, String stats) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ADD_NEW_CARD_QUERY);
		ps.setString(1, name);
		ps.setString(2, type);
		ps.setString(3, description);
		ps.setString(4, stats);
		ps.executeUpdate();

	}

	public void updateCardById(String name, String type, String description, String stats, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_CARD_BY_ID_QUERY);
		ps.setString(1, name);
		ps.setString(2, type);
		ps.setString(3, description);
		ps.setString(4, stats);
		ps.setInt(5, id);
		ps.executeUpdate();

	}

	public void deleteCardById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CARD_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

}
