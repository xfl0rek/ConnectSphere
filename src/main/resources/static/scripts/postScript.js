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