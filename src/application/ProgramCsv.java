package application;

import csv.CsvProcessor;
import csv.ReportGenerator;
import csv.ShopData;

public class ProgramCsv {
    public static void main(String[] args) {
        String path = "/Users/leyza/Desktop/wc_exercicios/csv/impact-report.csv"; // Caminho correto do arquivo CSV

        CsvProcessor csvProcessor = new CsvProcessor();
        ShopData shopData = csvProcessor.processCsv(path);

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(shopData);
    }
}
