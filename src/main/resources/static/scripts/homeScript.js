document.addEventListener('DOMContentLoaded', function() {
    const postArea = document.getElementById('postArea');
    const posts = postArea.querySelectorAll('.post');

    posts.forEach(post => {
        const postTimeElement = post.querySelector('.post-time');
        const currentTime = new Date();
        const hours = currentTime.getHours().toString().padStart(2, '0');
        const minutes = currentTime.getMinutes().toString().padStart(2, '0');
        const formattedTime = `${hours}:${minutes} ${currentTime.getDate()}.${currentTime.getMonth() + 1}.${currentTime.getFullYear()}`;
        postTimeElement.textContent = formattedTime;
    });
});
