document.addEventListener('DOMContentLoaded', function () {
    var alertCloseButtons = document.querySelectorAll('.alert .close');
    alertCloseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var alert = this.parentElement;
            alert.style.display = 'none';
        });
    });
});
