package main.java.com.controller;

import main.java.com.model.Filme;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;

public class FilmeController {
    @FXML private TextField txtTitulo;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAno;
    @FXML private TableView <Filme> tabelaFilmes;
    @FXML private TableColumn <Filme, String> columnFilme;
    @FXML private TableColumn <Filme, String> columnGenero;
    @FXML private TableColumn <Filme, Integer> columnAno;

    private final ObservableList <Filme> listaFilmes = FXCollections.observableArrayList();
}
