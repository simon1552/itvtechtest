import scala.collection.mutable

class Checkout(pricingRules: Map[Char, (Int, Int, Int)]) {
  def calculateTotalPrice(items: String): Int = {
    val itemCounts = mutable.Map[Char, Int]().withDefaultValue(0)

    for (item <- items) {
      itemCounts(item) += 1
    }

    var totalPrice = 0

    for ((item, count) <- itemCounts) {
      val (unitPrice, specialPrice, specialOfferQty) = pricingRules(item)
      val specialOfferCount = count / specialOfferQty
      val remainingCount = count % specialOfferQty

      totalPrice += (specialOfferCount * specialPrice) + (remainingCount * unitPrice)
    }

    totalPrice
  }
}
