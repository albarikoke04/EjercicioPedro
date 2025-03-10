package Controller;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author Veikr
 */
public class ScoreTableModel extends AbstractTableModel {

    Database d = Database.getDatabase();
    private ArrayList<String[]> datos;
    private String[] columnas;

    public ScoreTableModel() {
        columnas = new String[]{"Usuario", "Victorias", "Juegos", "Porcentaje"};
        try {
            datos = d.getHighscore();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "False Connection", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setModel(String[] result) {
        datos.clear();
        datos.add(result);
        fireTableDataChanged();
    }
    
    public void reboot() throws SQLException {
        datos = d.getHighscore();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
    
    public void ordenarDatos(int columna) {
        Comparator<String[]> comparador = null;
        ArrayList<String[]> data = new ArrayList<>();

        switch (columna) {
            case 0: // Ordenar por username
                comparador = Comparator.comparing(a -> a[0]);
                break;
            case 1: // Ordenar por wins
                comparador = Comparator.comparingInt(a -> Integer.parseInt(a[1]));
                comparador = comparador.reversed();
                break;
            case 2: // Ordenar por games played
                comparador = Comparator.comparingInt(a -> Integer.parseInt(a[2]));
                comparador = comparador.reversed();
                break;
            case 3: // Ordenar por percentage of wins
                comparador = Comparator.comparingDouble(a -> {
                    try {
                        return Double.parseDouble(a[3]); // Me daba error, el integer no, así que yo que sé
                    } catch (NumberFormatException e) {
                        return 0.0;  // Valor por defecto en caso de un error de formato
                    }
                });
                comparador = comparador.reversed();
                break;
        }

        if (comparador != null) {
            Collections.sort(datos, comparador);
            fireTableDataChanged();  // Notifica a la tabla que los datos han cambiado
        }
    }
}
