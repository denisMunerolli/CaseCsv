package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvProcessor {

    public ShopData processCsv(String path) {
        ShopData shopData = new ShopData(1000, 3); // Exemplo de tamanho de matriz, ajuste conforme necessário

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // Ignorar o cabeçalho

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Processar os dados do CSV e atualizar o ShopData
                String eventType = fields[3].trim();
                String subId = fields[9].trim();
                double earnings = Double.parseDouble(fields[6].trim());

                // Verificar se subId é numérico antes de processar
                if (isNumeric(subId)) {
                    shopData.processEvent(eventType, subId, earnings);
                } else {
                    System.err.println("Erro: subId não é numérico - " + subId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return shopData;
    }

    // Função auxiliar para verificar se uma string é numérica
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
