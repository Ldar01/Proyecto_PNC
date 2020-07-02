window.onbeforeunload = function(e) {
    return window.location.replace("/logout");
};