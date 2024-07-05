package csv;

import java.util.Map;

public class ReportGenerator {

    public void generateReport(ShopData shopData) {
        System.out.println("Relatório de Faturamento por Canal:");

        Map<String, Double> conversionRates = shopData.getConversionRates();
        Map<String, Double> cacByChannel = shopData.getCacByChannel();

        for (int i = 0; i < shopData.getChannelData().length; i++) {
            double currentEarnings = shopData.getChannelData()[i][0];
            double forecastedEarnings = shopData.getChannelData()[i][1] + shopData.getChannelData()[i][2];

            String subId = String.valueOf(i);

            System.out.printf("Canal: %s\n", subId);
            System.out.printf("Faturamento Atual: %.2f\n", currentEarnings);
            System.out.printf("Previsão de Faturamento (Free Trial e Paid Trial): %.2f\n", forecastedEarnings);

            // Adicionar taxa de conversão, se existir para o canal
            if (conversionRates != null && conversionRates.containsKey(subId)) {
                double conversionRate = conversionRates.get(subId);
                System.out.printf("Taxa de Conversão: %.2f%%\n", conversionRate);
            } else {
                System.out.println("Taxa de Conversão não disponível para este canal.");
            }

            // Adicionar CAC, se existir para o canal
            if (cacByChannel != null && cacByChannel.containsKey(subId)) {
                double cac = cacByChannel.get(subId);
                System.out.printf("CAC (Custo por Aquisição): %.2f\n", cac);
            } else {
                System.out.println("CAC não disponível para este canal.");
            }

            System.out.println();
        }
    }
}
