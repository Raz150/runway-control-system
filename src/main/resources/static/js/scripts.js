document.addEventListener('DOMContentLoaded', () => {
    const video = document.getElementById('bg-video');
    document.querySelector('.welcome-section').addEventListener('click', () => {
        if (video.paused) {
            video.play();
        } else {
            video.pause();
        }
    });
});
