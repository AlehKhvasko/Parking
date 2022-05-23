package repository;

import com.sun.jdi.Value;
import model.Car;
import model.Make;
import utils.PropertyReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CarRepository {
    //TODO do we need a connection to be opened the whole time?
    private Connection connection;

    public CarRepository() {


        try {
            Properties properties = PropertyReader.getProperties();
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }

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

    public void delete(Car car){
        String SQL = "DELETE FROM CAR WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(SQL)){
            statement.setLong(1, car.getId());
            statement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Car> readAll(){
        String SQL = " SELECT * FROM car";
        ArrayList<Car> carList = new ArrayList<>();

        try(Statement preparedStatement = connection.createStatement()){
            ResultSet result = preparedStatement.executeQuery(SQL);

            while(result.next()){
                Car car = new Car(
                        result.getLong("id"),
                        Make.valueOf(result.getString("make")),
                        result.getString("model"));
                carList.add(car);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return carList;
    }
}
