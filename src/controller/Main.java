package controller;

import view.Menu;

import java.sql.SQLException;

// CLASSE MAIN PARA RODAR O CÓDIGO
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("\n" +
                " ▄████▄   ▄▄▄       ██▓    ▓█████▄  ██▓ ███▄    █  ██░ ██  ▒█████  \n" +
                "▒██▀ ▀█  ▒████▄    ▓██▒    ▒██▀ ██▌▓██▒ ██ ▀█   █ ▓██░ ██▒▒██▒  ██▒\n" +
                "▒▓█    ▄ ▒██  ▀█▄  ▒██░    ░██   █▌▒██▒▓██  ▀█ ██▒▒██▀▀██░▒██░  ██▒\n" +
                "▒▓▓▄ ▄██▒░██▄▄▄▄██ ▒██░    ░▓█▄   ▌░██░▓██▒  ▐▌██▒░▓█ ░██ ▒██   ██░\n" +
                "▒ ▓███▀ ░ ▓█   ▓██▒░██████▒░▒████▓ ░██░▒██░   ▓██░░▓█▒░██▓░ ████▓▒░\n" +
                "░ ░▒ ▒  ░ ▒▒   ▓▒█░░ ▒░▓  ░ ▒▒▓  ▒ ░▓  ░ ▒░   ▒ ▒  ▒ ░░▒░▒░ ▒░▒░▒░ \n" +
                "  ░  ▒     ▒   ▒▒ ░░ ░ ▒  ░ ░ ▒  ▒  ▒ ░░ ░░   ░ ▒░ ▒ ░▒░ ░  ░ ▒ ▒░ \n" +
                "░          ░   ▒     ░ ░    ░ ░  ░  ▒ ░   ░   ░ ░  ░  ░░ ░░ ░ ░ ▒  \n" +
                "░ ░            ░  ░    ░  ░   ░     ░           ░  ░  ░  ░    ░ ░  \n" +
                "░                           ░                                      \n" +
                "   ▓█████▄  ▒█████      ▄████▄   ██░ ██  ██▓ ▄████▄   ▒█████       \n" +
                "   ▒██▀ ██▌▒██▒  ██▒   ▒██▀ ▀█  ▓██░ ██▒▓██▒▒██▀ ▀█  ▒██▒  ██▒     \n" +
                "   ░██   █▌▒██░  ██▒   ▒▓█    ▄ ▒██▀▀██░▒██▒▒▓█    ▄ ▒██░  ██▒     \n" +
                "   ░▓█▄   ▌▒██   ██░   ▒▓▓▄ ▄██▒░▓█ ░██ ░██░▒▓▓▄ ▄██▒▒██   ██░     \n" +
                "   ░▒████▓ ░ ████▓▒░   ▒ ▓███▀ ░░▓█▒░██▓░██░▒ ▓███▀ ░░ ████▓▒░     \n" +
                "    ▒▒▓  ▒ ░ ▒░▒░▒░    ░ ░▒ ▒  ░ ▒ ░░▒░▒░▓  ░ ░▒ ▒  ░░ ▒░▒░▒░      \n" +
                "    ░ ▒  ▒   ░ ▒ ▒░      ░  ▒    ▒ ░▒░ ░ ▒ ░  ░  ▒     ░ ▒ ▒░      \n" +
                "    ░ ░  ░ ░ ░ ░ ▒     ░         ░  ░░ ░ ▒ ░░        ░ ░ ░ ▒       \n" +
                "      ░        ░ ░     ░ ░       ░  ░  ░ ░  ░ ░          ░ ░       \n" +
                "    ░                  ░                    ░                      \n");
        Menu.menu();
    }
}