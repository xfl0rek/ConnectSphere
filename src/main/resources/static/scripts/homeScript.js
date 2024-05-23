const postForm = document.getElementById('postForm');
const postContent = document.getElementById('postContent');
const postArea = document.getElementById('postArea');

postForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const content = postContent.value.trim();

    if (content !== '') {
        const postElement = document.createElement('div');
        postElement.classList.add('post');

        const postContentElement = document.createElement('div');
        postContentElement.classList.add('post-content');
        postContentElement.textContent = content;

        postElement.appendChild(postContentElement);

        postArea.prepend(postElement);

        postContent.value = '';
    } else {
        alert('The post content cannot be empty.');
    }
});