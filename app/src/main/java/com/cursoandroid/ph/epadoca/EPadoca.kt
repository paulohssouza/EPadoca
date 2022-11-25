package com.cursoandroid.ph.epadoca

private const val Breads = 1
private const val Snacks = 2
private const val Candies = 3
private const val Finish = 0
private const val Line = ".........."

const val valueFrenchBread = 0.60
const val valueMilkBread = 0.40
const val valueCornBread = 0.50

const val frenchBread = "Pão Francês"
const val milkBread = "Pão de Leite"
const val cornBread = "Pão de Milho"

val breads: List<Pair<String, Double>> = listOf(
    Pair(frenchBread, valueFrenchBread),
    Pair(milkBread, valueMilkBread),
    Pair(cornBread, valueCornBread)
)

val categorys = """
    Digite a opção desejada no Menu:
        1....................Pães
        2................Salgados
        3...................Doces
        0...............Finalizar
""".trimIndent()

val menuBreads = """
    1 - $frenchBread...........R$ $valueFrenchBread
    2 - $milkBread..........R$ $valueMilkBread
    3 - $cornBread..........R$ $valueCornBread
    0 - Voltar
""".trimIndent()

var orderedItems: MutableList<String> = mutableListOf()
var totalOrder: Double = 0.0
var discount: Double = 0.0
var discountValid = false

fun main () {
    do {
        principalMenu()
        val finishOrder: String = if (orderedItems.isEmpty()) {
            println("Sua comanda está vazia.\nDeseja finalizar sua compra? (S/N)")
            readln().uppercase()
        } else {
            discountValid = false
            printOrder(discountValid, discount)
            do{
                println("Deseja aplicar um cupom de desconto?")
                var applyDiscount = readln().uppercase()
                if (applyDiscount == "S"){
                    println("Digite seu cupom de desconto:")
                    when (readln().uppercase()) {
                        "5PADOCA" -> {
                            discount = totalOrder * 0.05
                            applyDiscount = "N"
                            discountValid = true
                        }
                        "10PADOCA" -> {
                            discount = totalOrder * 0.10
                            applyDiscount = "N"
                            discountValid = true
                        }
                        "5OFF" -> {
                            discount = 5.00
                            applyDiscount = "N"
                            discountValid = true
                        }
                        else -> println("Cupom de desconto inválido!")
                    }
                }
                printOrder(discountValid, discount)
            } while (applyDiscount != "N")
            println("Deseja finalizar seu pedido?")
            readln().uppercase()
        }
    } while (finishOrder != "S")
}

fun principalMenu () {
    println("Bem vindo à padaria E-Padoca!")
    do {
        println(categorys)
        println("Opção: ")
        val category = readln().toInt()
        when (category) {
            Breads -> categoryMenu(menuBreads, breads)
            else -> Unit
        }
    } while (category != Finish)

}

fun categoryMenu (menu: String, products: List<Pair<String, Double>>) {
    do {
        println(menu)
        println("Opção: ")
        val productSelected = readln().toInt()
        for((i, product) in products.withIndex()) {
            if(i.inc() == productSelected) {
                registerRequest(product)
            }
        }
    } while (productSelected != 0)
}

fun registerRequest (product: Pair<String, Double>) {
    println("Digite a quantidade:")
    val amount = readln().toInt()
    val totalItems = amount * product.second
    val item = orderedItem(product.first, amount, product.second, totalItems)
    orderedItems.add(item)
    totalOrder += totalItems
}

fun orderedItem (
    product: String,
    amount: Int,
    unitValue: Double,
    total: Double
): String = "${orderedItems.size.inc()}$Line$product$Line$amount$Line$unitValue${Line}R$ $total"

fun printOrder ( validDiscount: Boolean, setDiscount: Double) {
    if (validDiscount) {
        println("=======================Comanda E-Padoca=======================\n")
        orderedItems.forEach { item -> println(item) }
        println()
        println("Valor total - R$ $totalOrder - R$ $setDiscount = R$ ${totalOrder - setDiscount}\n")
    } else {
        println("=======================Comanda E-Padoca=======================\n")
        orderedItems.forEach { item -> println(item) }
        println()
        println("Valor total - R$ $totalOrder\n")
    }
}