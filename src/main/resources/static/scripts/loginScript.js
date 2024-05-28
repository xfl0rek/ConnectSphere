const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', ()=>{
    container.classList.add("active");
})

loginBtn.addEventListener('click', ()=>{
    container.classList.remove("active");
})

document.addEventListener('DOMContentLoaded', function () {
    var alertCloseButtons = document.querySelectorAll('.alert .close');
    alertCloseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var alert = this.parentElement;
            alert.style.display = 'none';
        });
    });
});