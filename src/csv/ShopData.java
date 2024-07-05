package csv;

import java.util.HashMap;
import java.util.Map;

public class ShopData {
    private final double[][] channelData;
    private Map<String, Double> conversionRates;
    private Map<String, Double> cacByChannel;

    public ShopData(int numRows, int numCols) {
        this.channelData = new double[numRows][numCols];
        this.conversionRates = new HashMap<>();
        this.cacByChannel = new HashMap<>();
    }

    public void processEvent(String eventType, String subId, double earnings) {
        // Verificar se subId é numérico antes de processar
        if (isNumeric(subId)) {
            // Atualiza o faturamento atual por canal
            channelData[Integer.parseInt(subId)][0] += earnings;

            // Calcula e atualiza a previsão de faturamento por canal para Free Trial e Paid Trial
            switch (eventType) {
                case "Free Trial API":
                    channelData[Integer.parseInt(subId)][1] += earnings; // Coluna 1 para Free Trial
                    break;
                case "Paid Trial API":
                    channelData[Integer.parseInt(subId)][2] += earnings; // Coluna 2 para Paid Trial
                    break;
                case "Online Conversion API":
                    // Não há atualização para Online Conversion conforme solicitado
                    break;
            }
        } else {
            System.err.println("Erro: subId não é numérico - " + subId);
        }
    }

    public double[][] getChannelData() {
        return channelData;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public Map<String, Double> getCacByChannel() {
        return cacByChannel;
    }

    public void setCacByChannel(Map<String, Double> cacByChannel) {
        this.cacByChannel = cacByChannel;
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
