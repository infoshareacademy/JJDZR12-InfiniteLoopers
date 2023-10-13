window.onload = function() {
    setTimeout(function() {
        var preloader = document.querySelector('.preloader');
        if (preloader) {
            preloader.style.display = 'none';
        }
    }, 1000);
};