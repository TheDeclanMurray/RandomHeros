module RandomHeros {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.headroyce.declanm2022 to javafx.fxml;
    exports org.headroyce.declanm2022;
    exports Heros;
    opens Heros to javafx.fxml;
}