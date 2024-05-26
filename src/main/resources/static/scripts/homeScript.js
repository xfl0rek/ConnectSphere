document.addEventListener('DOMContentLoaded', function() {
    const postForm = document.getElementById('postForm');
    const postContent = document.getElementById('postContent');
    const postArea = document.getElementById('postArea');

    postForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const content = postContent.value.trim();
        const currentTime = new Date();
        const hours = currentTime.getHours().toString().padStart(2, '0');
        const minutes = currentTime.getMinutes().toString().padStart(2, '0');
        const formattedTime = `${hours}:${minutes} ${currentTime.getDate()}.${currentTime.getMonth() + 1}.${currentTime.getFullYear()}`;
        const loggedInUser = postArea.getAttribute("data-username");

        if (content !== '') {
            const postElement = document.createElement('div');
            postElement.classList.add('post');

            const postContentElement = document.createElement('div');
            postContentElement.classList.add('post-content');
            postContentElement.textContent = content;

            const postTimeElement = document.createElement('div');
            postTimeElement.classList.add('post-time');
            postTimeElement.textContent = formattedTime;

            const postUserElement = document.createElement('div');
            postUserElement.classList.add('post-user');
            postUserElement.textContent = "@" + loggedInUser;

            postElement.appendChild(postContentElement);
            postElement.appendChild(postTimeElement);
            postElement.appendChild(postUserElement);

            postArea.prepend(postElement);

            postContent.value = '';
        } else {
            alert('The post content cannot be empty.');
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const postArea = document.getElementById('postArea');
    const posts = postArea.querySelectorAll('.post');

    posts.forEach(post => {
        const postTimeElement = post.querySelector('.post-time');
        const currentTime = new Date();
        const formattedTime = `${currentTime.getHours()}:${currentTime.getMinutes()} ${currentTime.getDate()}.${currentTime.getMonth() + 1}.${currentTime.getFullYear()}`;
        postTimeElement.textContent = formattedTime;
    });
});
