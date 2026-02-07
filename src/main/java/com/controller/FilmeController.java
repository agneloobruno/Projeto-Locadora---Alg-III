package com.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.model.Filme;

public class FilmeController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAno;
    @FXML private TableView<Filme> tabelaFilmes;
    @FXML private TableColumn<Filme, String> colTitulo;
    @FXML private TableColumn<Filme, String> colGenero;
    @FXML private TableColumn<Filme, Integer> colAno;

    private final ObservableList<Filme> listaFilmes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Vincula as colunas da tabela aos atributos do modelo
        colTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        colGenero.setCellValueFactory(cellData -> cellData.getValue().generoProperty());
        colAno.setCellValueFactory(cellData -> cellData.getValue().anoProperty().asObject());

        tabelaFilmes.setItems(listaFilmes);

        // Listener para carregar dados ao clicar na tabela (Ajuda no Update)
        tabelaFilmes.getSelectionModel().selectedItemProperty().addListener(
            (obs, antigo, novo) -> selecionarFilme(novo));
    }

    @FXML // OPERAÇÃO: CREATE
    public void adicionarFilme() {
        try {
            Filme f = new Filme(txtTitulo.getText(), txtGenero.getText(), Integer.parseInt(txtAno.getText()));
            listaFilmes.add(f);
            limparCampos();
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido!");
        }
    }

    @FXML // OPERAÇÃO: DELETE
    public void excluirFilme() {
        Filme selecionado = tabelaFilmes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            listaFilmes.remove(selecionado);
        }
    }

    @FXML // OPERAÇÃO: UPDATE
    public void atualizarFilme() {
        Filme selecionado = tabelaFilmes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            selecionado.setTitulo(txtTitulo.getText());
            selecionado.setGenero(txtGenero.getText());
            selecionado.setAno(Integer.parseInt(txtAno.getText()));
            tabelaFilmes.refresh(); // Atualiza a visualização
        }
    }

    private void selecionarFilme(Filme f) {
        if (f != null) {
            txtTitulo.setText(f.getTitulo());
            txtGenero.setText(f.getGenero());
            txtAno.setText(String.valueOf(f.getAno()));
        }
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtGenero.clear();
        txtAno.clear();
    }
}