package dataBase;

import java.sql.*;

public class DatabaseInsertExample {

    private static final String URL = "jdbc:mariadb://localhost:3306/caldinho_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public static void insertDataCardapio(int idItem, String nome, String descricao, double preco) {
        String query = "insert into itemcardapio values (?,?,?,?) ";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idItem);
                statement.setString(2, nome);
                statement.setString(3, descricao);
                statement.setDouble(4, preco);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public static void removeDataCardapio(int idItem) {
        String query = "delete from itemcardapio where idItem = ?";
        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, idItem);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("remoção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover dados: " + e.getMessage());
        }
    }

    public static void listarDataCardapio() throws SQLException {
        String query = "select * from itemcardapio";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);

                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Itens do Cardápio:");
                System.out.println("-----------------------------------------");

                while (resultSet.next()) {
                    int idItem = resultSet.getInt("idItem");
                    String nome = resultSet.getString("nome");
                    String descricao = resultSet.getString("descricao");
                    double preco = resultSet.getDouble("preco");

                    System.out.printf("ID: %d\nNome: %s\nDescrição: %s\nPreço: %.2f\n", idItem, nome, descricao, preco);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens do cardápio: " + e.getMessage());
        }
    }

    public static void insertDataMesa(int numero, String livre) {
        String query = "insert into mesa values (?,?) ";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numero);
                statement.setString(2, livre);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Inserção bem-sucedida!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }

    public static void listarDataMesa() throws SQLException {
        String query = "select * from mesa";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query);

                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Lista de mesas:");
                System.out.println("-----------------------------------------");

                // Itera pelos resultados
                while (resultSet.next()) {
                    int numero = resultSet.getInt("numero");
                    String livre = resultSet.getString("livre");

                    System.out.printf("Numero: %d\nSituação: %s\n", numero,livre);
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar mesas: " + e.getMessage());
        }
    }

    public static void ocuparMesa(int numero) {
        String query = "update mesa set livre = 'ocupado' where numero = ?";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numero);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Status da mesa atualizado com sucesso!");
                } else {
                    System.out.println("Mesa não encontrada para atualizar.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar dados: " + e.getMessage());
        }
    }

    public static void desocuparMesa(int numero) {
        String query = "update mesa set livre = 'livre' where numero = ?";

        try (Connection connection = connect()) {
            assert connection != null;
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, numero);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Status da mesa atualizado com sucesso!");
                } else {
                    System.out.println("Mesa não encontrada para atualizar.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar dados: " + e.getMessage());
        }
    }

}
