import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Config{
    public static final int qtdConsultas = 10;
    public static final int qtdPacientes = 100;
    public static final int qtdEspecialidades = 50;
    public static final int qtdProfissionais = 100;

    

    public static LocalDateTime lerDataHora() {
        LocalDate hoje = LocalDate.now();
        while (true) {
            int dia;
            int mes;
            
            while (true) {
                IO.print("Digite o dia: ");
                String diaStr = IO.readln();

                if (!diaStr.matches("\\d{1,2}")) {
                    IO.println("Dia inválido!");
                    continue;
                }

                dia = Integer.parseInt(diaStr);

                if (dia < 1 || dia > 31) {
                    IO.println("Dia inválido!");
                    continue;
                }

                break;
            }

            while (true) {
                IO.print("Digite o mês: ");
                String mesStr = IO.readln();

                if (!mesStr.matches("\\d{1,2}")) {
                    IO.println("Mês inválido!");
                    continue;
                }

                mes = Integer.parseInt(mesStr);

                if (mes < 1 || mes > 12) {
                    IO.println("Mês inválido!");
                    continue;
                }

                break;
            }

            int ano = hoje.getYear();

            if (mes < hoje.getMonthValue() ||
                (mes == hoje.getMonthValue() && dia < hoje.getDayOfMonth())) {
                ano++;
            }

            int maxDias = 31;

            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                maxDias = 30;
            } else if (mes == 2) {
                maxDias = 28;

                if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
                    maxDias = 29;
                }
            }

            if (dia > maxDias) {
                IO.println("Essa data não existe!");
                continue;
            }

            while (true) {

                IO.print("Digite o horário(ex:10:30): ");
                String horaStr = IO.readln();

                if (horaStr.matches("\\d{2}:\\d{2}")) {
                    horaStr = horaStr.replace(":", "");
                }

                if (!horaStr.matches("\\d{4}")) {
                    IO.println("Horário inválido!");
                    continue;
                }
                
                int hora = Integer.parseInt(horaStr.substring(0, 2));
                int minuto = Integer.parseInt(horaStr.substring(2, 4));

                if (hora > 23 || minuto > 59) {
                    IO.println("Horário inválido!");
                    continue;
                }

                LocalDateTime dataHora = LocalDateTime.of(
                        ano, mes, dia, hora, minuto
                );

                IO.println("\nData e horário:");
                IO.println(String.format(
                        "%02d/%02d/%04d às %02d:%02d",
                        dia, mes, ano, hora, minuto
                ));

                IO.print("Deseja confirmar? (S/N): ");

                if (IO.readln().equalsIgnoreCase("S")) {
                    return dataHora;
                }

                break;
            }
        }
    }

    public static final DateTimeFormatter formatoDataHora =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
}
