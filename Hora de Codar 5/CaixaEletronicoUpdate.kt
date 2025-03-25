import java.util.*
import kotlin.system.exitProcess

var saldo: Float = 100.5F
var nome: String = ""
var extratos: MutableList<String> = mutableListOf()
var i = 0
val senha = 3589

fun main() {

    extratos.addAll(listOf("- Saque de R$ 10.0","- Deposito de R$ 20.0", "- Transferencia de R$ 30.0 para a conta: 45123", "- Saque de R$ 40.0", "- Saque de R$ 1.0"))

    println(" ---Caixa Eletrônico--- \n")
    println("Como gosta de ser chamado? ")
    nome = readln()
    println("\nSeja Bem-vindo(a) $nome! \n")

    inicio()
}

fun inicio() {

    println("\nO que deseja fazer? " +
            "\n1 - Ver saldo" +
            "\n2 - Ver extrato" +
            "\n3 - Fazer saque" +
            "\n4 - Fazer deposito" +
            "\n5 - Fazer transferência" +
            "\n6 - Sair")
    val escolha = readlnOrNull()?.toIntOrNull()

    when (escolha) {
        1 -> verSaldo()
        2 -> extrato(extratos)
        3 -> fazerSaque()
        4 -> fazerDeposito()
        5 -> fazerTransferencia()
        6 -> sair()
        else -> erro()
    }
}

fun verSaldo() {
    println("Insira sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    if(senhaDigitada != senha) {
        println("Senha incorreta, tente novamente.")
        verSaldo()
    }
    else {
        println("Seu saldo atual é: $saldo")
        inicio()
    }
}

fun fazerDeposito() {
    println("Insira sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    if(senhaDigitada != senha) {
        println("Senha incorreta, tente novamente.")
        fazerDeposito()
    }
    else {
        print("Qual o valor para depósito? ")
        val deposito = readLine()?.toFloatOrNull()

        if (deposito == null || deposito <= 0F) {
            println("Por favor, informe um número válido.")
            fazerDeposito()
        } else {
            saldo += deposito
            println("\nDepósito realizado com sucesso!\n")
            extratos.add("- Deposito de R$ $deposito")
            println("\nSeu saldo atual é: $saldo")
            inicio()
        }
    }
}

fun fazerSaque() {
    println("Insira sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    if(senhaDigitada!=senha) {
        println("Senha incorreta, tente novamente.")
        fazerSaque()
    }
    else {
        print("Qual o valor para saque? ")
        val saque = readLine()?.toFloatOrNull()

        if (saque == null || saque <= 0F) {
            println("Por favor, informe um número válido.")
            fazerSaque()
            if (saque != null) {
                if (saque > saldo) {
                    println("Saldo insuficiente, Operação não autorizada.")
                    inicio()
                }
            }
        } else {
            saldo -= saque
            println("\nSaque realizado com sucesso!\n")
            extratos.add("- Saque de R$ $saque")
            println("\nSeu saldo atual é: $saldo")
            inicio()
        }
    }
}

fun extrato(extratos: MutableList<String>) {
    println("Insira sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    if(senhaDigitada!=senha) {
        println("Senha incorreta, tente novamente.")
        extrato(extratos)
    }
    else {
        if (extratos.isNotEmpty()) {
            println("\nExtrato: ")
            for (i in extratos) {
                println(i)
            }
            inicio()
        } else {
            println("\nExtrato vazio.")
            inicio()
        }
    }
}

fun fazerTransferencia() {
    println("Insira sua senha: ")
    val senhaDigitada = readlnOrNull()?.toIntOrNull()

    if(senhaDigitada!=senha) {
        println("Senha incorreta, tente novamente.")
        fazerTransferencia()
    }
    else {
        println("Digite o número da conta que deseja transferir: ")
        var conta = readlnOrNull()?.toIntOrNull()

        if (conta != null) {
            print("Qual o valor para saque? ")
            val transferir = readLine()?.toFloatOrNull()

            if (transferir == null || transferir <= 0F) {
                println("Por favor, informe um número válido.")
                fazerTransferencia()
                if (transferir != null) {
                    if (transferir > saldo) {
                        println("Saldo insuficiente, Operação não autorizada.")
                        inicio()
                    }
                }
            } else {
                saldo -= transferir
                println("\nTransferencia realizada com sucesso!\n")
                extratos.add("- Transferencia de R$ $transferir para a conta: $conta")
                println("\nSeu saldo atual é: $saldo")
                inicio()
            }
        } else {
            println("Conta inválida")
            fazerTransferencia()
        }
    }
}

fun erro() {
    println("Por favor, informe um número entre 1 e 6.")
    inicio()
}

fun sair() {
    print("Você deseja sair? (S/N) ")
    val confirma = readLine()?.uppercase(Locale.getDefault())

    when (confirma) {
        "S" -> {println("${nome}, foi um prazer ter você por aqui!")
            exitProcess(0) }
        "N" -> inicio()
        else -> sair()
    }
}

