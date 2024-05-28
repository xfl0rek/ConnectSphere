document.addEventListener('DOMContentLoaded', function() {
    const posts = document.querySelectorAll('.post');

    posts.forEach(post => {
        const postTimeElement = post.querySelector('.post-time');
        const createdAt = new Date(postTimeElement.textContent);

        const hours = createdAt.getHours().toString().padStart(2, '0');
        const minutes = createdAt.getMinutes().toString().padStart(2, '0');
        const day = createdAt.getDate().toString().padStart(2, '0');
        const month = (createdAt.getMonth() + 1).toString().padStart(2, '0');
        const year = createdAt.getFullYear().toString();

        const formattedTime = `${hours}:${minutes} ${day}.${month}.${year}`;
        postTimeElement.textContent = formattedTime;
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var alertCloseButtons = document.querySelectorAll('.alert .close');
    alertCloseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var alert = this.parentElement;
            alert.style.display = 'none';
        });
    });
});