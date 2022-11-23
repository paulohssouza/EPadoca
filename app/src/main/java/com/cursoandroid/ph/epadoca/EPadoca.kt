package com.cursoandroid.ph.epadoca
// Variáveis constantes do menu principal
private const val Breads = 1
private const val Snacks = 2
private const val Candies = 3
private const val Finish = 0
private const val Line = ".........."

val valueFrenchBread = 0.60
val valueMilkBread = 0.40
val valueCornBread = 0.50

val frenchBread = "Pão Francês"
val milkBread = "Pão de Leite"
val cornBread = "Pão de Milho"

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

val orderedItems: MutableList<String> = mutableListOf<String>()
var total: Double = 0.0

fun main () {
    do {
        var finishOrder = "N"
        principalMenu()
        if (orderedItems.isEmpty()) {
            println("Sua comanda está vazia.\nDeseja finalizar sua compra? (S/N)")
            finishOrder = readln().uppercase()
        } else {
            println("=======================Comanda E-Padoca=======================\n")
            orderedItems.forEach { item -> println(item) }
            println()
            println("Valor total - R$ $total\n")
            println("Deseja finalizar seu pedido?")
            finishOrder = readln().uppercase()
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
    total += totalItems
}

fun orderedItem (
    product: String,
    amount: Int,
    unitValue: Double,
    total: Double
): String = "${orderedItems.size.inc()}$Line$product$Line$amount$Line$unitValue${Line}R$ $total"