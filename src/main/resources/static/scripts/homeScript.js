document.addEventListener('DOMContentLoaded', function() {
    const postForm = document.getElementById('postForm');
    const postContent = document.getElementById('postContent');
    const postArea = document.getElementById('postArea');

    postForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const content = postContent.value.trim();
        const currentTime = new Date();
        const hours = currentTime.getHours();
        const minutes = currentTime.getMinutes();
        const formattedTime = hours + ':' + (minutes < 10 ? '0' : '') + minutes;
        if (content !== '') {
            const postElement = document.createElement('div');
            postElement.classList.add('post');

            const postContentElement = document.createElement('div');
            postContentElement.classList.add('post-content');
            postContentElement.textContent = content;

            const postTimeElement = document.createElement('div');
            postTimeElement.classList.add('post-time');
            postTimeElement.textContent = formattedTime;

            postElement.appendChild(postContentElement);
            postElement.appendChild(postTimeElement);

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
        const hours = currentTime.getHours();
        const minutes = currentTime.getMinutes();
        postTimeElement.textContent = hours + ':' + (minutes < 10 ? '0' : '') + minutes;
    });
});