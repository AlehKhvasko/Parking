package repository;

import model.Car;
import model.Make;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepository {
    private final Connection connection;

    public CarRepository(Connection connection) {
        this.connection = connection;
    }

    public void add(Car car) {
        String SQL = "INSERT INTO car( id, make, model) " +
                "VALUES (?, ?, ?) ";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, car.getId());
            statement.setString(2, car.getMake().name());
            statement.setString(3, car.getModel());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Car car) {
        String SQL = "DELETE FROM CAR WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, car.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> readAll() {
        String SQL = "SELECT * FROM car";
        List<Car> carList = new ArrayList<>();

        try (Statement preparedStatement = connection.createStatement()) {
            ResultSet result = preparedStatement.executeQuery(SQL);

            while (result.next()) {
                Car car = new Car(
                        result.getLong("id"),
                        Make.valueOf(result.getString("make")),
                        result.getString("model"));
                carList.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(carList);
    }
}
