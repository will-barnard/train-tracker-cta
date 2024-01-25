export default {
    formatDate(dateTimeString) {
        const time = new Date(dateTimeString);
        const minutes = (time - Date.now()) / 1000 / 60;
        let difference = Math.floor(minutes);
        let display;

        if (minutes < 2) {
            display = 'Less than two minutes';            
        } else {
                display = `${difference} minutes`;
        }
        return display;
    },
    timeToMin(time) {
        return time / 1000 / 60;
    },
    getArrivalTime(arrival) {
        const time = new Date(arrival);
        return time - Date.now();
    },
    getArrivalToMinute(arrival) {
        const time = new Date(arrival);
        return Math.floor(((time - Date.now()) / 1000 / 60));
    },
    stringToTime(dateTimeString) {
        return new Date(dateTimeString);
    }
}