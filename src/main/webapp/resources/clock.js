const hour = document.getElementById("hour");
const minute = document.getElementById("minute");
const seconds = document.getElementById("seconds");

// Обновление времени сразу при первой загрузке
time();

// Обновление времени каждые 12 секунд
const clock = setInterval(time, 12000);

function time() {
    const dateNow = new Date();
    let hr = dateNow.getHours();
    let min = dateNow.getMinutes();
    let sec = dateNow.getSeconds();
    hr = hr.toString().padStart(2, "0");
    min = min.toString().padStart(2, "0");
    sec = sec.toString().padStart(2, "0");
    const timeString = `${hr}:${min}:${sec}`;
    hour.textContent = hr;
    minute.textContent = min;
    seconds.textContent = sec;
}

updateTime(); // Запуск обновления времени
