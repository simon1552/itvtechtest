import org.scalatest.flatspec.AnyFlatSpec

class CheckoutTest extends AnyFlatSpec {

  val pricingRules = Map('A' -> (50, 130, 3), 'B' -> (30, 45, 2), 'C' -> (20, 20, 1), 'D' -> (15, 15, 1))
  val checkout = new Checkout(pricingRules)

  "calculateTotalPrice" should "return 0 for empty input" in {
    val totalPrice = checkout.calculateTotalPrice("")
    assert(totalPrice == 0)
  }

  it should "return correct price for single items" in {
    val totalPrice = checkout.calculateTotalPrice("A")
    assert(totalPrice == 50)
  }

  it should "return correct price for one of each items" in {
    val totalPrice = checkout.calculateTotalPrice("ABCD")
    assert(totalPrice == 115)
  }

  it should "apply special offer for item A" in {
    val totalPrice = checkout.calculateTotalPrice("AAA")
    assert(totalPrice == 130)
  }

  it should "apply special offer for item B" in {
    val totalPrice = checkout.calculateTotalPrice("BB")
    assert(totalPrice == 45)
  }

  it should "Recognise special offer for item B with A in any order" in {
    val totalPrice = checkout.calculateTotalPrice("BAB")
    assert(totalPrice == 95)
  }

  it should "Recognise special offers for both item A and item B in any order" in {
    val totalPrice = checkout.calculateTotalPrice("BAABA")
    assert(totalPrice == 175)
  }

}
