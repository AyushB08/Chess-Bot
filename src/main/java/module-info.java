module bot.chessbot {
    requires javafx.controls;
    requires javafx.fxml;


    opens bot.chessbot to javafx.fxml;
    exports bot.chessbot;
}