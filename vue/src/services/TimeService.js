export default {
    formatDate(dateString) {
        const date = new Date(dateString);
        const minutes = (date - Date.now()) / 1000 / 60;
        let difference = Math.floor(minutes);
        let display;

        if (minutes < 2) {
            display = 'Less than two minutes';            
        } else {
                display = `${difference} minutes`;
        }
        return display;
    }
}