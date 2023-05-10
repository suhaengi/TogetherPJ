// Retrieve the data
const posts = fetch('https://example.com/api/posts')
    .then(response => response.json())
    .then(data => {
        // Populate the HTML with data
        const postElements = document.querySelectorAll('.post');
        postElements.forEach((element, index) => {
            const post = data[index];
            const titleElement = element.querySelector('.post-title');
            const contentElement = element.querySelector('.post-content');
            titleElement.innerText = post.title;
            contentElement.innerText = post.content;
        });
    });
