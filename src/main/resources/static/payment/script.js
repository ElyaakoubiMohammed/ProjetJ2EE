$(document).ready(function () {

    Stripe.setPublishableKey('pk_test_9D43kM3d2vEHZYzPzwAblYXl');

    var cardNumber, cardMonth, cardYear, cardCVC, cardHolder;

    function findEmpty() {
        var emptyText = $('#form-container input').filter(function () {
            return $(this).val() === '';
        });
        emptyText.addClass('invalid');
    }

    $('#card-number').blur(function (event) {
        event.preventDefault();
        checkCardType();
    });

    $('#card-btn').click(function (event) {
        var cardNumber = $('#card-number').val();
        var isValidNo = Stripe.card.validateCardNumber(cardNumber);
        var expMonth = $('#card-month').val();
        var expYear = $('#card-year').val();
        var isValidExpiry = Stripe.card.validateExpiry(expMonth, expYear);
        var cardCVC = $('#card-cvc').val();
        var isValidCVC = Stripe.card.validateCVC(cardCVC);
        var cardHolder = $('#card-holder').val();
        event.preventDefault();

        // Reset form errors
        $('#form-errors').addClass('hidden');
        $('#card-error').text('');

        if (!cardNumber || !cardCVC || !cardHolder || !expMonth || !expYear) {
            $('#form-errors').removeClass('hidden');
            $('#card-error').text('Please complete all fields.');
            findEmpty();
            return; // Exit function if any field is empty
        }

        if (!isValidNo || !isValidExpiry || !isValidCVC) {
            $('#form-errors').removeClass('hidden');
            if (!isValidExpiry) {
                $('#card-error').text('Invalid expiration date.');
            } else if (!isValidCVC) {
                $('#card-error').text('Invalid CVC code.');
            }
            return; // Exit function if any validation fails
        }

        // If all fields are valid, hide the form errors completely
        $('#form-errors').addClass('hidden');
        // Proceed with form submission or other actions
        $('#card-success').removeClass('hidden');
    });
});
