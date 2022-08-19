<template>
  <div class="footer-style">
    <p>We accept the following forms of payment</p>

    <p>
      <img
        alt="Visa"
        title="Visa"
        src="https://d12yp66odzcfr0.cloudfront.net/images/payment-logos/visa.png"
      />
    </p>
    <p>
      <img
        alt="AMEX"
        title="AMEX"
        src="https://d12yp66odzcfr0.cloudfront.net/images/payment-logos/amex.png"
      />
    </p>
    <p>
      <img
        alt="MasterCard"
        title="MasterCard"
        src="https://d12yp66odzcfr0.cloudfront.net/images/payment-logos/mastercard.png"
      />
    </p>
    <p>
      <img
        alt="Discover"
        title="Discover"
        src="https://d12yp66odzcfr0.cloudfront.net/images/payment-logos/discover.png"
      />
    </p>
    <p>
      <img
        alt="Cash"
        title="Cash"
        src="https://d12yp66odzcfr0.cloudfront.net/images/payment-logos/cash.png"
      />
    </p>
  </div>
</template>

<script>
export default {
  name: "bottom",
};
</script>

<style>
.footer-style {
  display: flex;
  flex-direction: row;
  background: silver;
  width: 100%;
  gap: 30px;
  margin-top: 50px;
  justify-content: flex-start;
  position: fixed;
  bottom: 0;
  font-family: "Assistant", Arial, Helvetica, sans-serif;
}
</style>
