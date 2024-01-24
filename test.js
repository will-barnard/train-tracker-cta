function formatDate(dateString) {

    const date = new Date(dateString);
    const minutes = (date - Date.now()) / 1000 / 60;
    let difference = Math.floor(minutes);
    let display;

    if (difference > 24) {
        display = date.toLocaleDateString();
    } else {
        if (minutes < 2) {
            display = 'Less than two minutes';
        } else {
            display = `${difference} minutes`;
        }
    }
    
    return display;
}

console.log(formatDate('2024-01-23T22:57:23'))