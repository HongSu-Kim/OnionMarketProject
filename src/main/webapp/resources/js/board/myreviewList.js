function zoomIn(event) {
    event.target.style.transform = "scale(5)";
    event.target.style.zIndex = 10;
    event.target.style.transition = "all 0.1s";// 속도
}

function zoomOut(event) {
    event.target.style.transform = "scale(1)";
    event.target.style.zIndex = 0;
    event.target.style.transition = "all 0.5s";
}