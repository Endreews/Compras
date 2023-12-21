import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o limite do seu Cartão: ");
        double limite = scanner.nextDouble();
        CartaoCredito cartao = new CartaoCredito(limite);

        int sair = 1;
        while (sair != 0) {
            System.out.println("Digite o produto: ");
            String descricao = scanner.next();

            System.out.println("Digite o valor: ");
            double valor = scanner.nextDouble();

            Compra compra = new Compra(descricao, valor);
            boolean compraSucesso = cartao.compraVF(compra);

            if (compraSucesso) {
                System.out.println("Compra realizada com sucesso!");
                System.out.println("Digite 0 para Sair ou Voltar para Continuar!");
                String opcao = scanner.next();
                if ("voltar".equalsIgnoreCase(opcao)) {
                    continue; // Volta ao início do loop
                } else {
                    sair = 0; // Sair do loop
                }
            } else {
                System.out.println("Saldo insuficiente!");
                if (cartao.getSaldo() > 0) {
                    String opcao;
                    do {
                        System.out.println("Digite 'voltar' para retornar!");
                        opcao = scanner.next();
                    } while (!opcao.equalsIgnoreCase("voltar") && !opcao.equals("0"));

                    if ("voltar".equalsIgnoreCase(opcao)) {
                        continue; // Volta ao início do loop
                    } else {
                        sair = 0; // Sair do loop
                    }
                } else {
                    sair = 0; // Sair do loop se o saldo for zero ou negativo
                }
            }
        }

        System.out.println("###################################");
        System.out.println("HISTÓRICO DE COMPRAS");

        for (Compra c : cartao.getCompras()) {
            System.out.println(c.getDescricao() + " - " + c.getValor());
        }

        System.out.println("\n###################################");
        System.out.println("\nSaldo restante: " + cartao.getSaldo());
    }
}
